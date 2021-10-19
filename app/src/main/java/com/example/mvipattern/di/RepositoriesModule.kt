package com.example.mvipattern.di;

import com.example.mvipattern.repos.*
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
abstract class RepositoriesModule {

    @Binds
    @Singleton
    abstract fun bindHomeRepo(repo: HomeRepo): HomeRepoInterface

}