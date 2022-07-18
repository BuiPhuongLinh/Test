package com.cmc.data.di

import android.content.Context
import androidx.room.Room
import com.cmc.data.local.dao.TempDao
import com.cmc.data.local.database.TempDatabase
import com.cmc.data.local.utils.DBConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): TempDatabase =
        Room.databaseBuilder(
            context,
            TempDatabase::class.java,
            DBConstants.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun provideTempDao(db: TempDatabase): TempDao = db.tempDao()

}