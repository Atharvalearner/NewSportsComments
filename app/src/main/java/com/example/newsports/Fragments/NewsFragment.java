package com.example.newsports.Fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.SearchView;
import android.widget.Toast;
import com.example.newsports.Adapter.CustomAdapter;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsports.DetailsFragment;
import com.example.newsports.RequestManager;
import com.example.newsports.Models.NewsApiResponse;
import com.example.newsports.Models.NewsHeadlines;
import com.example.newsports.SelectListener;
import com.example.newsports.R;
import java.util.List;

public class NewsFragment extends Fragment implements SelectListener {
    RecyclerView recyclerView;
    CustomAdapter adapter;
    ProgressDialog progressDialog;
    androidx.appcompat.widget.SearchView searchView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);

        searchView = view.findViewById(R.id.search_view);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                progressDialog.setTitle("Fetching news articles of " + query);
                progressDialog.dismiss();
                RequestManager manager = new RequestManager(requireContext());
                manager.getNewsHeadlines(listener, "sports", query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        progressDialog = new ProgressDialog(requireContext());
        progressDialog.setTitle("Fetching new News...");
        progressDialog.show();

        RequestManager manager = new RequestManager(requireContext());
        manager.getNewsHeadlines(listener, "sports", null);

        recyclerView = view.findViewById(R.id.recycler_main);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(requireContext(), 1));

        return view;
    }

    private final OnFetchDataListener<NewsApiResponse> listener = new OnFetchDataListener<NewsApiResponse>() {
        @Override
        public void onFetchData(List<NewsHeadlines> list, String message) {
            if (list.isEmpty()) {
                Toast.makeText(requireContext(), "No data found of your query !", Toast.LENGTH_SHORT).show();
            } else {
                showNews(list);
                progressDialog.dismiss();
            }
        }

        @Override
        public void onError(String message) {
            // Handle error
            Toast.makeText(requireContext(), "Fetching error occurred !", Toast.LENGTH_SHORT).show();
        }
    };

    private void showNews(List<NewsHeadlines> list) {
        adapter = new CustomAdapter(requireContext(), list, this);
        recyclerView.setAdapter(adapter);
    }
    @Override
    public void OnNewsClicked(NewsHeadlines headlines) {
        startActivity(new Intent(getActivity(), DetailsFragment.class).putExtra("data",headlines));
    }
}
