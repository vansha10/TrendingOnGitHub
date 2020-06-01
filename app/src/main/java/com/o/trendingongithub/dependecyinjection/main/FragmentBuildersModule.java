package com.o.trendingongithub.dependecyinjection.main;

import com.o.trendingongithub.fragments.TrendingDeveloperFragment;
import com.o.trendingongithub.fragments.TrendingRepositoriesFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract TrendingRepositoriesFragment contributesTrendingRepositoriesFragment();

    @ContributesAndroidInjector
    abstract TrendingDeveloperFragment contributesTrendingDevelopersFragment();
}
