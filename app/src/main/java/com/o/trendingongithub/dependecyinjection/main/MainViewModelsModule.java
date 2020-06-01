package com.o.trendingongithub.dependecyinjection.main;

import androidx.lifecycle.ViewModel;

import com.o.trendingongithub.dependecyinjection.ViewModelKey;
import com.o.trendingongithub.viewModel.DeveloperViewModel;
import com.o.trendingongithub.viewModel.RepoViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class MainViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(DeveloperViewModel.class)
    abstract ViewModel bindsDeveloperViewModel(DeveloperViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(RepoViewModel.class)
    abstract ViewModel bindsRepoViewModel(RepoViewModel viewModel);
}
