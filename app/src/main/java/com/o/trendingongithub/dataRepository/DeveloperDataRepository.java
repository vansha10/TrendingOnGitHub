package com.o.trendingongithub.dataRepository;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.o.trendingongithub.Utils.GithubAPI;
import com.o.trendingongithub.model.DeveloperData;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeveloperDataRepository {

    private static DeveloperDataRepository instance;
    private MutableLiveData<List<DeveloperData>> developerData = new MutableLiveData<>();
    private List<DeveloperData> dataset = new ArrayList<>();
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    private GithubAPI githubAPI;

    public DeveloperDataRepository() {
        //placeholder values
        dataset.add(new DeveloperData("", "", "", ""));
        developerData.setValue(dataset);
        githubAPI = RetrofitService.createService(GithubAPI.class);
    }

    public static DeveloperDataRepository getInstance() {
        if (instance == null) {
            instance = new DeveloperDataRepository();
        }
        return instance;
    }

    public MutableLiveData<List<DeveloperData>> getDeveloperData() {

        isLoading.setValue(true);

        Call<List<DeveloperData>> call = githubAPI.getDevelopers();

        call.enqueue(new Callback<List<DeveloperData>>() {
            @Override
            public void onResponse(@NonNull Call<List<DeveloperData>> call, @NonNull Response<List<DeveloperData>> response) {
                isLoading.setValue(false);
                if (!response.isSuccessful()) {
                    Log.d("retrofit", response.toString());
                } else {
                    dataset = response.body();
                    developerData.setValue(response.body());
                    Log.d("retrofit", response.toString());
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<DeveloperData>> call, @NonNull Throwable t) {
                Log.d("retrofit 123", Objects.requireNonNull(t.getMessage()));
                isLoading.setValue(false);
            }
        });

        return developerData;
    }

    public MutableLiveData<Boolean> getIsLoading() {
        return isLoading;
    }
}