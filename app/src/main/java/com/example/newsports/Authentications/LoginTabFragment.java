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

import com.example.newsports.MainActivity;
import com.example.newsports.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginTabFragment extends Fragment {

    TextInputLayout login_email, login_password;
    TextInputEditText log_email, log_pass;
    Button login_btn;
    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login_tab, container, false);

        login_email = view.findViewById(R.id.login_email);
        login_password = view.findViewById(R.id.login_password);
        log_email = view.findViewById(R.id.log_email);
        log_pass = view.findViewById(R.id.log_pass);
        login_btn = view.findViewById(R.id.login_btn);

        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(getActivity());

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user_email = log_email.getText().toString().trim();
                String user_pass = log_pass.getText().toString().trim();

                if (user_email.isEmpty()) {
                    login_email.setError("Please Enter Your Registered Email");
                    return;
                }

                if (user_pass.isEmpty()) {
                    login_password.setError("Please Enter Registered Password");
                    return;
                }

                progressDialog.setMessage("Logging in...");
                progressDialog.show();

                firebaseAuth.signInWithEmailAndPassword(user_email, user_pass)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressDialog.dismiss();
                                if (task.isSuccessful()) {
                                    startActivity(new Intent(getActivity(), MainActivity.class));
                                    Toast.makeText(getActivity(), "Login successful", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getActivity(), "Login failed. Please check your credentials.", Toast.LENGTH_SHORT).show();
                                }
                        }
                });
            }
        });
        return view;
    }
}
