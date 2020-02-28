package com.o.trendingongithub.dataRepository;

import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.o.trendingongithub.Utils.GithubAPI;
import com.o.trendingongithub.model.RepoData;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RepoDataRepository {

    private static RepoDataRepository instance;
    private List<RepoData> dataSet = new ArrayList<>();
    private GithubAPI githubAPI;

    public RepoDataRepository() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://github-trending-api.now.sh/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        githubAPI = retrofit.create(GithubAPI.class);
    }

    public static RepoDataRepository getInstance() {
        if (instance == null) {
            instance = new RepoDataRepository();
        }
        return instance;
    }

    public MutableLiveData<List<RepoData>> getRepoData() {

        final MutableLiveData<List<RepoData>> mRepoData = new MutableLiveData<>();
        Call<List<RepoData>> call = githubAPI.getRepositories();

        call.enqueue(new Callback<List<RepoData>>() {
            @Override
            public void onResponse(Call<List<RepoData>> call, Response<List<RepoData>> response) {
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
            }
        });;
        if (mRepoData != null) {
            dataSet.add(new RepoData("loading","","","",-1));
            mRepoData.setValue(dataSet);
        }
        return mRepoData;
    }


}
