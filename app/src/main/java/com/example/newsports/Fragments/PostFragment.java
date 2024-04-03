package com.example.newsports.Fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.newsports.Models.CommentModel;
import com.example.newsports.Models.PostDisplayModel;
import com.example.newsports.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.List;

public class PostFragment extends Fragment {

    private static final int PICK_IMAGE_REQUEST = 1;
    private static final int PICK_VIDEO_REQUEST = 2;

    private FirebaseAuth mAuth;
    private FirebaseStorage storage;
    private StorageReference storageReference;
    private DatabaseReference databaseReference;

    private ImageView imageView;
    private EditText editTextTitle;
    private EditText editTextDescription;
    private Button buttonChooseImage;
    private Button buttonChooseVideo;
    private Button buttonUpload;

    private Uri imageUri;
    private Uri videoUri;
    private String postId;

    public PostFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_post, container, false);

        mAuth = FirebaseAuth.getInstance();

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        databaseReference = FirebaseDatabase.getInstance().getReference("posts");

        imageView = view.findViewById(R.id.imageView);
        editTextTitle = view.findViewById(R.id.editTextTitle);
        editTextDescription = view.findViewById(R.id.editTextDescription);
        buttonChooseImage = view.findViewById(R.id.buttonChooseImage);
        buttonChooseVideo = view.findViewById(R.id.buttonChooseVideo);
        buttonUpload = view.findViewById(R.id.buttonUpload);

        buttonChooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser(PICK_IMAGE_REQUEST);
            }
        });

        buttonChooseVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser(PICK_VIDEO_REQUEST);
            }
        });

        buttonUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadFile();
            }
        });

        return view;
    }

    private void openFileChooser(int requestCode) {
        Intent intent = new Intent();
        if (requestCode == PICK_IMAGE_REQUEST) {
            intent.setType("image/*");
        } else if (requestCode == PICK_VIDEO_REQUEST) {
            intent.setType("video/*");
        }
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, requestCode);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == getActivity().RESULT_OK && data != null && data.getData() != null) {
            if (requestCode == PICK_IMAGE_REQUEST) {
                imageUri = data.getData();
                imageView.setImageURI(imageUri);
            } else if (requestCode == PICK_VIDEO_REQUEST) {
                videoUri = data.getData();
                // Handle video preview if needed
            }
        }
    }

    private void uploadFile() {
        ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Uploading...");
        progressDialog.setCancelable(false); // Prevent dismiss on touch outside
        progressDialog.show();

        if (imageUri != null || videoUri != null) {
            Uri fileUri = imageUri != null ? imageUri : videoUri;
            String fileExtension = getFileExtension(fileUri);
            StorageReference fileReference = storageReference.child(System.currentTimeMillis() + "." + fileExtension);

            fileReference.putFile(fileUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            taskSnapshot.getStorage().getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    String fileUrl = uri.toString();

                                    String title = editTextTitle.getText().toString().trim();
                                    String description = editTextDescription.getText().toString().trim();
                                    String userId = mAuth.getCurrentUser().getUid();

                                    postId = databaseReference.push().getKey();
                                    if (postId != null) {
                                        // Add comments
                                        List<CommentModel> comments = new ArrayList<>();
                                        // Add comments to the list as needed
                                      //  comments.add(new CommentModel(commentId, userId, postId, commentText, timestamp));

                                        // Create a new post model
                                        PostDisplayModel postModel = new PostDisplayModel(title, description, fileUrl, userId, description, postId, null, null, 0, false, comments);

                                        // Store the post in the database
                                        databaseReference.child(postId).setValue(postModel);

                                        Toast.makeText(getActivity(), "Upload successful", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getActivity(), "Upload failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            Toast.makeText(getActivity(), "No file selected", Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();
        }
    }


    private String getFileExtension(Uri uri) {
        return MimeTypeMap.getSingleton().getExtensionFromMimeType(getActivity().getContentResolver().getType(uri));
    }
}
