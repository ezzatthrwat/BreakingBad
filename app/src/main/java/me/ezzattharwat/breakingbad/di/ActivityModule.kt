package me.ezzattharwat.breakingbad.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.ActivityScoped
import me.ezzattharwat.breakingbad.ui.CharactersAdapter

@Module
@InstallIn(ActivityComponent::class)
object ActivityModule {

    @ActivityScoped
    @Provides
    fun provideCharactersAdapter(@ActivityContext context: Context) : CharactersAdapter = CharactersAdapter(context)

}