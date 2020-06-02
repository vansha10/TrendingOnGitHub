package com.o.trendingongithub.dataRepository;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.o.trendingongithub.Utils.GithubAPI;
import com.o.trendingongithub.model.RepoData;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Singleton
public class RepoDataRepository {

    private MutableLiveData<List<RepoData>> mRepoData = new MutableLiveData<>();
    private List<RepoData> dataSet = new ArrayList<>();
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();

    private  GithubAPI githubAPI;


    @Inject
    public RepoDataRepository(GithubAPI githubAPI) {
        this.githubAPI = githubAPI;
    }


    public MutableLiveData<List<RepoData>> getRepoData() {

        isLoading.setValue(true);

        Call<List<RepoData>> call = githubAPI.getRepositories();

        call.enqueue(new Callback<List<RepoData>>() {
            @Override
            public void onResponse(@NonNull Call<List<RepoData>> call, @NonNull Response<List<RepoData>> response) {
                isLoading.setValue(false);
                if (!response.isSuccessful()) {
                    Log.d("retrofit", response.toString());
                } else {
                    //dataSet = response.body();
                    mRepoData.setValue(response.body());
                    Log.d("retrofit", response.toString());
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<RepoData>> call, @NonNull Throwable t) {
                Log.d("retrofit 123", Objects.requireNonNull(t.getMessage()));
                isLoading.setValue(false);
            }
        });

        return mRepoData;
    }

    public MutableLiveData<Boolean> getIsLoading() {
        return isLoading;
    }

}