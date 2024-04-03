package com.example.newsports.Authentications;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.newsports.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignupTabFragment extends Fragment {

    private TextInputLayout signup_email;
    private TextInputLayout signup_password;
    private TextInputEditText sign_email;
    private TextInputEditText sign_pass;
    private Button sign_btn;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signup_tab, container, false);

        signup_email = view.findViewById(R.id.signup_email);
        signup_password = view.findViewById(R.id.signup_password);
        sign_email = view.findViewById(R.id.sign_email);
        sign_pass = view.findViewById(R.id.sign_pass);
        sign_btn = view.findViewById(R.id.signup_btn);
        firebaseAuth = FirebaseAuth.getInstance();

        sign_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });

        return view;
    }

    private void registerUser() {
        String user_email = sign_email.getText().toString().trim();
        String user_pass = sign_pass.getText().toString().trim();

        if (user_email.isEmpty()) {
            signup_email.setError("Please Enter Email");
            return;
        }

        if (user_pass.isEmpty()) {
            signup_password.setError("Please Enter Password");
            return;
        }

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setTitle("Signup Loading...");
//        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(user_email, user_pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()) {
                            Toast.makeText(getActivity(), "Registration Successful", Toast.LENGTH_SHORT).show();
                            // Navigate to another activity after successful registration
                            Intent intent = new Intent(getActivity(), Landing_Page.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(getActivity(), "Registration Failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
