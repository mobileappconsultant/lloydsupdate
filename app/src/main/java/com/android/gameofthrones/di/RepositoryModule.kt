package com.android.gameofthrones.di

import com.android.gameofthrones.data.repository.CharacterRepository
import com.android.gameofthrones.data.repository.CharacterRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindRepositoryImpl(repositoryImpl: CharacterRepositoryImpl): CharacterRepository
}
