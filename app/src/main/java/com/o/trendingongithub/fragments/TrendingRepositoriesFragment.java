package com.o.trendingongithub.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.o.trendingongithub.R;
import com.o.trendingongithub.adapters.RepoRecyclerViewAdapter;
import com.o.trendingongithub.model.RepoData;
import com.o.trendingongithub.viewModel.RepoViewModel;

import java.util.List;

public class TrendingRepositoriesFragment extends Fragment {

    private View root;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ProgressBar progressBar;
    private List<RepoData> dataset;

    private RepoViewModel repoViewModel;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        repoViewModel = ViewModelProviders.of(getActivity()).get(RepoViewModel.class);
        repoViewModel.init();
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_trending_repo, container, false);
        repoViewModel.getRepoData().observe(getViewLifecycleOwner(), new Observer<List<RepoData>>() {
            @Override
            public void onChanged(List<RepoData> repoData) {
                Log.d("trending_fragmemnt",String.valueOf(repoData.size()));
                dataset.clear();
                dataset.addAll(repoData);
                mAdapter.notifyDataSetChanged();

            }
        });
        repoViewModel.getIsLoading().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    progressBar.setVisibility(View.VISIBLE);
                } else {
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
        initRecyclerView();
        return root;
    }

    private void initRecyclerView() {
        recyclerView =  root.findViewById(R.id.recycler_view);

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        dataset = repoViewModel.getRepoData().getValue();
        //sending Glide object here itself for displaying avatar and better lifecycle management
        mAdapter = new RepoRecyclerViewAdapter(Glide.with(this), dataset);
        recyclerView.setAdapter(mAdapter);

        progressBar = root.findViewById(R.id.progress_bar);
    }
}