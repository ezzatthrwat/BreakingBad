package me.ezzattharwat.breakingbad.di

import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import me.ezzattharwat.breakingbad.TestDataRepository
import me.ezzattharwat.breakingbad.data.repository.CharactersRepo
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [DataModule::class]
)
object TestDataModule {

    @Singleton
    @Provides
    fun provideCharacterRepository() =  TestDataRepository() as CharactersRepo

}
