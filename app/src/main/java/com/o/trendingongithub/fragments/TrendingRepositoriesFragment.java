package com.o.trendingongithub.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
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

/**
 * A placeholder fragment containing a simple view.
 */
public class TrendingRepositoriesFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private View root;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private PageViewModel pageViewModel;

    public static TrendingRepositoriesFragment newInstance(int index) {
        TrendingRepositoriesFragment fragment = new TrendingRepositoriesFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
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

        mAdapter = new RecyclerViewAdapter(pageViewModel.getRepoData().getValue());
        recyclerView.setAdapter(mAdapter);
    }
}