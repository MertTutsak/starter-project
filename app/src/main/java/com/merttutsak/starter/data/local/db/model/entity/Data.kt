package com.merttutsak.starter.data.local.db.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "data")
data class Data(
    @PrimaryKey(autoGenerate = true) val dataId: Long,
    val data: String
)