package me.ezzattharwat.breakingbad.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.ezzattharwat.breakingbad.data.repository.CharactersRepo
import me.ezzattharwat.breakingbad.data.repository.CharactersRepoImp
import me.ezzattharwat.breakingbad.data.source.remotedata.ApiService
import me.ezzattharwat.breakingbad.util.NetworkConnection
import javax.inject.Singleton

// Tells Dagger this is a Dagger module
@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Singleton
    @Provides
    fun provideCharacterRepository(apiService: ApiService, networkConnection: NetworkConnection) =  CharactersRepoImp(apiService, networkConnection) as CharactersRepo
}