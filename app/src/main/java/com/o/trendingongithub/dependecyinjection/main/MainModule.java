package com.o.trendingongithub.dependecyinjection.main;

import com.bumptech.glide.RequestManager;
import com.o.trendingongithub.Utils.GithubAPI;
import com.o.trendingongithub.adapters.DeveloperRecyclerViewAdapter;
import com.o.trendingongithub.adapters.RepoRecyclerViewAdapter;
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
    static DeveloperRecyclerViewAdapter providesDeveloperRecyclerViewAdapter(RequestManager glide) {
        return new DeveloperRecyclerViewAdapter(glide);
    }

    @Provides
    static RepoRecyclerViewAdapter providesRepoRecyclerViewAdapter(RequestManager glide) {
        return new RepoRecyclerViewAdapter(glide);
    }

}
