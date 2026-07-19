package com.jetpack.compose.github.github.cruise.di

import android.content.Context
import com.jetpack.compose.github.github.cruise.data.preferences.FavoritesPreferences
import com.jetpack.compose.github.github.cruise.data.preferences.ThemePreferences
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Hilt module for providing preferences dependencies
 */
@Module
@InstallIn(SingletonComponent::class)
object PreferencesModule {

    @Provides
    @Singleton
    fun provideThemePreferences(@ApplicationContext context: Context): ThemePreferences {
        return ThemePreferences(context)
    }

    @Provides
    @Singleton
    fun provideFavoritesPreferences(
        @ApplicationContext context: Context,
        moshi: Moshi
    ): FavoritesPreferences {
        return FavoritesPreferences(context, moshi)
    }
}
