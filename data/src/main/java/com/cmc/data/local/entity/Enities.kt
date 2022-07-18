package com.cmc.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cmc.data.local.utils.DBConstants

@Entity(tableName = DBConstants.DATABASE_NAME)
data class TempEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "name")
    val name: String
)