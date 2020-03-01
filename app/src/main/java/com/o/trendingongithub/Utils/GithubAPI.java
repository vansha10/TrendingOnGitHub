package com.o.trendingongithub.Utils;

import com.o.trendingongithub.model.DeveloperData;
import com.o.trendingongithub.model.RepoData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GithubAPI {

    String BASE_URL = "https://github-trending-api.now.sh/";

    @GET("repositories")
    Call<List<RepoData>> getRepositories();

    @GET("developers")
    Call<List<DeveloperData>> getDevelopers();
}
