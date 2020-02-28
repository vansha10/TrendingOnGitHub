package com.o.trendingongithub.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.o.trendingongithub.R;
import com.o.trendingongithub.adapters.DeveloperRecyclerViewAdapter;
import com.o.trendingongithub.model.DeveloperData;
import com.o.trendingongithub.viewModel.DeveloperViewModel;


import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TrendingDeveloperFragment extends Fragment {

    private View root;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ProgressBar progressBar;
    private List<DeveloperData> dataset = new ArrayList<>();

    private DeveloperViewModel developerViewModel;

    @Override
    public void onCreate(Bundle savedInstances) {
        super.onCreate(savedInstances);
        developerViewModel = ViewModelProviders.of(getActivity()).get(DeveloperViewModel.class);
        developerViewModel.init();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       root = inflater.inflate(R.layout.fragment_trending_developer, container, false);
       developerViewModel.getDeveloperData().observe(getViewLifecycleOwner(), new Observer<List<DeveloperData>>() {
           @Override
           public void onChanged(List<DeveloperData> developerData) {
               Log.d("trending_fragmemnt",String.valueOf(developerData.size()));
               if (dataset != null) {
                   dataset.clear();
                   dataset.addAll(developerData);
                   mAdapter.notifyDataSetChanged();
               }
           }
       });
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

       return root;
    }

    private void initRecyclerView() {
        recyclerView =  root.findViewById(R.id.recycler_view);

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        dataset = developerViewModel.getDeveloperData().getValue();
        //sending Glide object here itself for displaying avatar and better lifecycle management
        mAdapter = new DeveloperRecyclerViewAdapter(Glide.with(this), dataset);
        recyclerView.setAdapter(mAdapter);

        progressBar = root.findViewById(R.id.progress_bar);
    }

}
