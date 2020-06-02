package com.o.trendingongithub.fragments;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.o.trendingongithub.R;
import com.o.trendingongithub.Utils.RecyclerItemClickListener;
import com.o.trendingongithub.adapters.DeveloperRecyclerViewAdapter;
import com.o.trendingongithub.model.DeveloperData;
import com.o.trendingongithub.viewModel.DeveloperViewModel;
import com.o.trendingongithub.viewModel.ViewModelProviderFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class TrendingDeveloperFragment extends DaggerFragment {

    private View root;

    private RecyclerView recyclerView;
    private DeveloperRecyclerViewAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ProgressBar progressBar;
    private List<DeveloperData> dataset = new ArrayList<>();

    private DeveloperViewModel developerViewModel;

    @Inject
    ViewModelProviderFactory viewModelProviderFactory;

    @Override
    public void onCreate(Bundle savedInstances) {
        super.onCreate(savedInstances);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        developerViewModel = ViewModelProviders.of(this, viewModelProviderFactory).get(DeveloperViewModel.class);

        developerViewModel.getDeveloperData().observe(getViewLifecycleOwner(), new Observer<List<DeveloperData>>() {
            @Override
            public void onChanged(List<DeveloperData> developerData) {
                Log.d("trending_fragmemnt", String.valueOf(developerData.size()));
                if (dataset != null) {
                    dataset.clear();
                    dataset.addAll(developerData);
                    mAdapter.notifyDataSetChanged();
                }
            }
        });
        //for displaying progressbar
        developerViewModel.getIsLoading().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_trending_developer, container, false);
        return root;
    }

    private void initRecyclerView() {
        recyclerView = root.findViewById(R.id.recycler_view);

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(dataset.get(position).getUrl()));
                        startActivity(i);
                        Toast.makeText(getContext(), "Opening Profile on GitHub", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

                    }
                })
        );

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        dataset = developerViewModel.getDeveloperData().getValue();
        //sending Glide object here itself for displaying avatar and better lifecycle management
        mAdapter = new DeveloperRecyclerViewAdapter(Glide.with(this));
        mAdapter.setmDataset(dataset);
        recyclerView.setAdapter(mAdapter);

        progressBar = root.findViewById(R.id.progress_bar);
    }

}
