package com.o.trendingongithub.dependecyinjection.main;

import com.o.trendingongithub.Utils.GithubAPI;
import com.o.trendingongithub.dataRepository.DeveloperDataRepository;
import com.o.trendingongithub.dataRepository.RepoDataRepository;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public abstract class MainModule {

    @Provides
    static GithubAPI providesGitHubApi(Retrofit retrofit) {
        return retrofit.create(GithubAPI.class);
    }

    @Provides
    static DeveloperDataRepository providesDeveloperDataRepository(GithubAPI githubAPI) {
        return new DeveloperDataRepository(githubAPI);
    }

    @Provides
    static RepoDataRepository providesRepoDataRepository(GithubAPI githubAPI) {
        return new RepoDataRepository(githubAPI);
    }

}
