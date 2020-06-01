package com.o.trendingongithub.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.o.trendingongithub.dataRepository.RepoDataRepository;
import com.o.trendingongithub.model.RepoData;

import java.util.List;

import javax.inject.Inject;

public class RepoViewModel extends ViewModel {

    private MutableLiveData<List<RepoData>> mRepoData;
    private RepoDataRepository mRepo;
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();

    @Inject
    public RepoViewModel(RepoDataRepository repoDataRepository) {
        this.mRepo = repoDataRepository;
        mRepoData = mRepo.getRepoData();
    }

    public LiveData<List<RepoData>> getRepoData() {
        return mRepoData;
    }

    public LiveData<Boolean> getIsLoading() {
        isLoading = mRepo.getIsLoading();
        return isLoading;
    }
}