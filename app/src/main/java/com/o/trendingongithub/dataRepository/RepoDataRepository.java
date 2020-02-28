package com.o.trendingongithub.dataRepository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.o.trendingongithub.Utils.GithubAPI;
import com.o.trendingongithub.model.RepoData;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepoDataRepository {

    private static RepoDataRepository instance;
    private List<RepoData> dataSet = new ArrayList<>();
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    private GithubAPI githubAPI;

    public RepoDataRepository() {
        githubAPI = RetrofitService.createService(GithubAPI.class);
    }

    public static RepoDataRepository getInstance() {
        if (instance == null) {
            instance = new RepoDataRepository();
        }
        return instance;
    }

    public MutableLiveData<List<RepoData>> getRepoData() {

        isLoading.setValue(true);

        final MutableLiveData<List<RepoData>> mRepoData = new MutableLiveData<>();
        Call<List<RepoData>> call = githubAPI.getRepositories();

        call.enqueue(new Callback<List<RepoData>>() {
            @Override
            public void onResponse(Call<List<RepoData>> call, Response<List<RepoData>> response) {
                isLoading.setValue(false);
                if (!response.isSuccessful()) {
                    //TODO: error message
                    Log.d("retrofit",response.toString());
                } else {
                    //dataSet = response.body();
                    mRepoData.setValue(response.body());
                    Log.d("retrofit", response.toString());
                }
            }

            @Override
            public void onFailure(Call<List<RepoData>> call, Throwable t) {
                //TODO: error message
                Log.d("retrofit 123", t.getMessage());
                isLoading.setValue(false);
            }
        });;
        if (mRepoData != null) {
            //placeholder data
            dataSet.add(new RepoData("","","","", "", "", 0, 0));
            mRepoData.setValue(dataSet);
        }
        return mRepoData;
    }

    public MutableLiveData<Boolean> getIsLoading() {
        return isLoading;
    }

}
