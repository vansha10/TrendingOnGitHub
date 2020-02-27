package com.o.trendingongithub.dataRepository;

import androidx.lifecycle.MutableLiveData;

import com.o.trendingongithub.model.RepoData;

import java.util.ArrayList;
import java.util.List;

public class RepoDataRepository {

    private static RepoDataRepository instance;
    private ArrayList<RepoData> dataSet = new ArrayList<>();

    public static RepoDataRepository getInstance() {
        if (instance == null) {
            instance = new RepoDataRepository();
        }
        return instance;
    }

    public MutableLiveData<List<RepoData>> getRepoData() {
        setData();
        MutableLiveData<List<RepoData>> mRepoData = new MutableLiveData<>();
        mRepoData.setValue(dataSet);
        return mRepoData;
    }

    private void setData() {
        dataSet.add(new RepoData("Test"));
        dataSet.add(new RepoData("Test2"));
        dataSet.add(new RepoData("Test3"));
        dataSet.add(new RepoData("Test4"));
    }

}
