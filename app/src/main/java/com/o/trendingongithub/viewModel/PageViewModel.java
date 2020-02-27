package com.o.trendingongithub.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.o.trendingongithub.dataRepository.RepoDataRepository;
import com.o.trendingongithub.model.RepoData;

import java.util.List;

public class PageViewModel extends ViewModel {

    private MutableLiveData<List<RepoData>> mRepoData;
    private RepoDataRepository mRepo;

    public void init() {
        if (mRepoData != null) {
            return;
        }
        mRepo = RepoDataRepository.getInstance();
        mRepoData = mRepo.getRepoData();
    }

    public LiveData<List<RepoData>> getRepoData() {
        return mRepoData;
    }
}