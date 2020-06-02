package com.o.trendingongithub.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.o.trendingongithub.dataRepository.DeveloperDataRepository;
import com.o.trendingongithub.model.DeveloperData;

import java.util.List;

import javax.inject.Inject;

public class DeveloperViewModel extends ViewModel {

    private DeveloperDataRepository mDeveloperRepository;

    @Inject
    public DeveloperViewModel(DeveloperDataRepository mDeveloperRepository) {
        this.mDeveloperRepository = mDeveloperRepository;
    }

    public LiveData<List<DeveloperData>> getDeveloperData() {
        return mDeveloperRepository.getDeveloperData();
    }

    public LiveData<Boolean> getIsLoading() {
        return mDeveloperRepository.getIsLoading();
    }
}