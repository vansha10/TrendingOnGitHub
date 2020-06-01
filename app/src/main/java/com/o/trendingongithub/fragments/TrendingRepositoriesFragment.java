package com.o.trendingongithub.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.o.trendingongithub.R;
import com.o.trendingongithub.RepoDetailsActivity;
import com.o.trendingongithub.Utils.RecyclerItemClickListener;
import com.o.trendingongithub.adapters.RepoRecyclerViewAdapter;
import com.o.trendingongithub.model.RepoData;
import com.o.trendingongithub.viewModel.RepoViewModel;
import com.o.trendingongithub.viewModel.ViewModelProviderFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class TrendingRepositoriesFragment extends DaggerFragment {

    private View root;

    private RecyclerView recyclerView;
    private RepoRecyclerViewAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ProgressBar progressBar;
    private List<RepoData> dataset;
    private MaterialSearchView searchView;

    private RepoViewModel repoViewModel;

    @Inject
    ViewModelProviderFactory viewModelProviderFactory;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        repoViewModel = ViewModelProviders.of(this, viewModelProviderFactory).get(RepoViewModel.class);

        repoViewModel.getRepoData().observe(getViewLifecycleOwner(), new Observer<List<RepoData>>() {
            @Override
            public void onChanged(List<RepoData> repoData) {
                Log.d("trending_fragmemnt", String.valueOf(repoData.size()));
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
        initSearchView();
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_trending_repo, container, false);
        return root;
    }

    private void initRecyclerView() {
        recyclerView = root.findViewById(R.id.recycler_view);

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(getContext(), RepoDetailsActivity.class);
                        Gson gson = new Gson();
                        String objJson = gson.toJson(dataset.get(position));
                        intent.putExtra("objJson", objJson);
                        startActivity(intent);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

                    }
                })
        );

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        dataset = repoViewModel.getRepoData().getValue();
        //sending Glide object here itself for displaying avatar and better lifecycle management
        mAdapter = new RepoRecyclerViewAdapter(Glide.with(this), dataset);
        recyclerView.setAdapter(mAdapter);

        progressBar = root.findViewById(R.id.progress_bar);
    }

    private void initSearchView() {
        View view = Objects.requireNonNull(getActivity()).findViewById(R.id.viewid);
        searchView = view.findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filterData(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
            }

            @Override
            public void onSearchViewClosed() {
                mAdapter.updateList(repoViewModel.getRepoData().getValue());
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu, menu);
        super.onCreateOptionsMenu(menu, inflater);

        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);
    }

    private void filterData(String text) {
        List<RepoData> temp = new ArrayList<>();
        for (RepoData rd : dataset) {
            if (rd.getName().toLowerCase().contains(text) ||
                    rd.getAuthor().toLowerCase().contains(text))
                temp.add(rd);
        }
        mAdapter.updateList(temp);
    }

}