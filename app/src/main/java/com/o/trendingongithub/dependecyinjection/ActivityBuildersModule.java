package com.o.trendingongithub.dependecyinjection;

import com.o.trendingongithub.MainActivity;
import com.o.trendingongithub.dependecyinjection.main.FragmentBuildersModule;
import com.o.trendingongithub.dependecyinjection.main.MainModule;
import com.o.trendingongithub.dependecyinjection.main.MainViewModelsModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(modules = {
            MainModule.class,
            FragmentBuildersModule.class,
            MainViewModelsModule.class
    })
    abstract MainActivity contributeMainActivity();

}
