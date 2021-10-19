package com.example.mvipattern.di;

import com.example.mvipattern.ui.MoviesActivity
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        ApplicationModule::class,
        RepositoriesModule::class,
        ViewModelModule::class,
    ]
)
@Singleton
interface ApplicationComponent {
    fun inject(target: MoviesActivity)
}