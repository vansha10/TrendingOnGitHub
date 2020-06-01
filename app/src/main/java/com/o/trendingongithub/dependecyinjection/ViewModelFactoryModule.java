package com.o.trendingongithub.dependecyinjection;

import androidx.lifecycle.ViewModelProvider;

import com.o.trendingongithub.viewModel.ViewModelProviderFactory;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class ViewModelFactoryModule {

    @Binds
    public abstract ViewModelProvider.Factory bindFactory(ViewModelProviderFactory viewModelProviderFactory);
}
