package com.cmc.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cmc.data.local.dao.TempDao
import com.cmc.data.local.entity.TempEntity
import com.cmc.data.local.utils.DBConstants

@Database(entities = [TempEntity::class], version = DBConstants.Version, exportSchema = false)
abstract class TempDatabase : RoomDatabase() {
    abstract fun tempDao(): TempDao
}