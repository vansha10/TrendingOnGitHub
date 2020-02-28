package com.o.trendingongithub.Utils;

import com.o.trendingongithub.model.RepoData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GithubAPI {

    @GET("repositories")
    Call<List<RepoData>> getRepositories();
}