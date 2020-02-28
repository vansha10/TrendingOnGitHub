package com.o.trendingongithub.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.LiveData;

import com.o.trendingongithub.dataRepository.DeveloperDataRepository;
import com.o.trendingongithub.model.DeveloperData;

import java.util.ArrayList;
import java.util.List;

public class DeveloperViewModel extends ViewModel {

    private MutableLiveData<List<DeveloperData>> mDeveloperData;
    private DeveloperDataRepository mDeveloper;
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();

    public void init() {
        if (mDeveloperData != null){
            return;
        }
        mDeveloper = DeveloperDataRepository.getInstance();
        mDeveloperData = mDeveloper.getDeveloperData();
    }

    public LiveData<List<DeveloperData>> getDeveloperData() {
        if (mDeveloperData == null) {
            List<DeveloperData> list = new ArrayList<>();
            list.add(new DeveloperData("","","",""));
            mDeveloperData.setValue(list);
        }
        return mDeveloperData;
    }

    public LiveData<Boolean> getIsLoading() {
        isLoading = mDeveloper.getIsLoading();
        return isLoading;
    }
}