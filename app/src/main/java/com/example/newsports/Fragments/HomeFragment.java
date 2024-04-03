// HomeFragment.java
package com.example.newsports.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsports.PostData.PostAdapter;
import com.example.newsports.PostData.PostModel;
import com.example.newsports.R;
import com.example.newsports.Models.PostDisplayModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private PostAdapter postAdapter;
    private List<PostDisplayModel> postDisplayModelList;
    private DatabaseReference databaseReference;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        postDisplayModelList = new ArrayList<>();
        postAdapter = new PostAdapter(getActivity(), postDisplayModelList);
        recyclerView.setAdapter(postAdapter);

        databaseReference = FirebaseDatabase.getInstance().getReference("posts");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                postDisplayModelList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    PostModel postModel = snapshot.getValue(PostModel.class);
                    postDisplayModelList.add(postModel.toPostDisplayModel()); // Convert to PostDisplayModel
                }
                postAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle errors
                Toast.makeText(getActivity(), "Failed to retrieve posts.", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}