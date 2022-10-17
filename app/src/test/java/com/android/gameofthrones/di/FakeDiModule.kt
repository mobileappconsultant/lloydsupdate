package com.android.gameofthrones.di

import android.content.Context
import androidx.room.Room
import com.android.gameofthrones.data.local.dao.CharacterDao
import com.android.gameofthrones.data.local.database.CharacterDatabase
import com.android.gameofthrones.dispatchers.TestDispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import java.util.concurrent.Executors
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [NetworkModule::class]
)
class FakeNetworkModule : NetworkModule() {
    override fun getBaseUrl() = "http://127.0.0.1:8080"
    override fun getDispatchers() = TestDispatcherProvider()
}

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [DbModule::class]
)
object FakeDbModule {

    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext context: Context): CharacterDatabase {
        return Room
            .inMemoryDatabaseBuilder(context, CharacterDatabase::class.java)
            .setTransactionExecutor(Executors.newSingleThreadExecutor())
            .build()
    }

    @Provides
    @Singleton
    fun provideDao(db: CharacterDatabase): CharacterDao {
        return db.weatherCityDao()
    }
}
