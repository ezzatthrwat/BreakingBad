package me.ezzattharwat.breakingbad.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.ezzattharwat.breakingbad.data.repository.CharactersRepoImp
import me.ezzattharwat.breakingbad.NetworkConnection
import me.ezzattharwat.breakingbad.data.remotedata.ApiService
import me.ezzattharwat.breakingbad.data.repository.CharactersRepo
import javax.inject.Singleton

// Tells Dagger this is a Dagger module
@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Singleton
    @Provides
    fun provideCharacterRepository(apiService: ApiService) =  CharactersRepoImp(apiService) as CharactersRepo
}