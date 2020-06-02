package com.o.trendingongithub.dependecyinjection;

import android.app.Application;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.o.trendingongithub.R;
import com.o.trendingongithub.Utils.GithubAPI;
import com.o.trendingongithub.dataRepository.DeveloperDataRepository;
import com.o.trendingongithub.dataRepository.RepoDataRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApplicationModule {

    @Singleton
    @Provides
    static Retrofit providesRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(GithubAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Singleton
    @Provides
    static GithubAPI providesGitHubApi(Retrofit retrofit) {
        return retrofit.create(GithubAPI.class);
    }

    @Singleton
    @Provides
    static RequestOptions provideRequestOption() {
        return RequestOptions.placeholderOf(R.drawable.avatar_placeholder)
                .error(R.drawable.ic_launcher_foreground);
    }

    @Singleton
    @Provides
    static RequestManager provideGlideInstance(Application application, RequestOptions requestOptions) {
        return Glide.with(application)
                .setDefaultRequestOptions(requestOptions);
    }

    @Singleton
    @Provides
    static DeveloperDataRepository providesDeveloperDataRepository(GithubAPI githubAPI) {
        return new DeveloperDataRepository(githubAPI);
    }

    @Singleton
    @Provides
    static RepoDataRepository providesRepoDataRepository(GithubAPI githubAPI) {
        return new RepoDataRepository(githubAPI);
    }
}
