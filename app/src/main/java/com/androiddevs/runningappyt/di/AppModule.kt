package com.androiddevs.runningappyt.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.androiddevs.runningappyt.db.RunningDatabase
import com.androiddevs.runningappyt.other.Constants.DATABASE_NAME
import com.androiddevs.runningappyt.other.Constants.KEY_FIRST_TIME_TOGGLE
import com.androiddevs.runningappyt.other.Constants.KEY_NAME
import com.androiddevs.runningappyt.other.Constants.KEY_WEIGHT
import com.androiddevs.runningappyt.other.Constants.SHARED_PREFS_NAME
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Suppress("unused")
@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        RunningDatabase::class.java,
        DATABASE_NAME
    ).build()

    @Singleton
    @Provides
    fun provideRunDAO(db: RunningDatabase) = db.runDAO()

    @Singleton
    @Provides
    fun provideSharedPreferences(
        @ApplicationContext app: Context
    ): SharedPreferences = app.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)

    @Reusable
    @Provides
    @KeyFirstOpen
    fun provideIsFirstOpen(sharedPrefs: SharedPreferences): Boolean =
        sharedPrefs.getBoolean(KEY_FIRST_TIME_TOGGLE, true)

    @Reusable
    @Provides
    @KeyName
    fun provideName(sharedPrefs: SharedPreferences): String =
        sharedPrefs.getString(KEY_NAME, "") ?: ""

    @Reusable
    @Provides
    @KeyWeight
    fun provideWeight(sharedPrefs: SharedPreferences): Float =
        sharedPrefs.getFloat(KEY_WEIGHT, 80f)

}