package com.template.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.template.data.local.utils.DBConstants

@Entity(tableName = DBConstants.DATABASE_NAME)
data class TempEnities(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "name")
    val name: String
)