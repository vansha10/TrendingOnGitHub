package com.o.trendingongithub.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.o.trendingongithub.dataRepository.RepoDataRepository;
import com.o.trendingongithub.model.RepoData;

import java.util.List;

import javax.inject.Inject;

public class RepoViewModel extends ViewModel {

    private RepoDataRepository mRepo;

    @Inject
    public RepoViewModel(RepoDataRepository repoDataRepository) {
        this.mRepo = repoDataRepository;
    }

    public LiveData<List<RepoData>> getRepoData() {
        return mRepo.getRepoData();
    }

    public LiveData<Boolean> getIsLoading() {
        return mRepo.getIsLoading();
    }
}