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

import com.o.trendingongithub.R;
import com.o.trendingongithub.model.RepoData;
import com.o.trendingongithub.viewModel.PageViewModel;

import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class TrendingRepositoriesFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

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
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        final TextView textView = root.findViewById(R.id.section_label);
        pageViewModel.getRepoData().observe(getViewLifecycleOwner(), new Observer<List<RepoData>>() {
            @Override
            public void onChanged(List<RepoData> repoData) {
                //TODO: replace with notify dataset changes
                textView.setText(repoData.get(0).getName());
            }
        });
        return root;
    }
}