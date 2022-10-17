package com.android.gameofthrones.di

import android.content.Context
import androidx.room.Room
import com.android.gameofthrones.data.local.dao.CharacterDao
import com.android.gameofthrones.data.local.database.CharacterDatabase
import com.android.gameofthrones.data.source.local.LocalDataSource
import com.android.gameofthrones.data.source.local.LocalDataSourceImpl
import com.android.gameofthrones.utils.Constants.DATABASE_NAME
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CacheModule {

    @Binds
    @Singleton
    abstract fun localDataSource(localDataSourceImpl: LocalDataSourceImpl): LocalDataSource
}

@Module
@InstallIn(SingletonComponent::class)
object DbModule {

    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext context: Context): CharacterDatabase {
        return Room.databaseBuilder(
            context,
            CharacterDatabase::class.java, DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideDao(db: CharacterDatabase): CharacterDao {
        return db.weatherCityDao()
    }
}
