package com.template.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.template.data.local.dao.TempDao
import com.template.data.local.entity.TempEnities
import com.template.data.local.utils.DBConstants

@Database(entities = [TempEnities::class], version = DBConstants.Version, exportSchema = false)
abstract class TempDatabase : RoomDatabase() {
    abstract fun tempDao(): TempDao
}