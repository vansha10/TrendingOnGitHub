package com.o.trendingongithub.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.o.trendingongithub.R;
import com.o.trendingongithub.adapters.RecyclerViewAdapter;
import com.o.trendingongithub.model.RepoData;
import com.o.trendingongithub.viewModel.PageViewModel;

import java.util.List;

public class TrendingRepositoriesFragment extends Fragment {

    private View root;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<RepoData> dataset;

    private PageViewModel pageViewModel;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(getActivity()).get(PageViewModel.class);
        pageViewModel.init();
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_main, container, false);
        pageViewModel.getRepoData().observe(getViewLifecycleOwner(), new Observer<List<RepoData>>() {
            @Override
            public void onChanged(List<RepoData> repoData) {
                Log.d("trending_fragmemnt",String.valueOf(repoData.size()));
                dataset.clear();
                dataset.addAll(repoData);
                mAdapter.notifyDataSetChanged();
            }
        });
        initRecyclerView();
        return root;
    }

    private void initRecyclerView() {
        recyclerView =  root.findViewById(R.id.recycler_view);

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        dataset = pageViewModel.getRepoData().getValue();
        mAdapter = new RecyclerViewAdapter(dataset);
        recyclerView.setAdapter(mAdapter);
    }
}