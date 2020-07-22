package com.merttutsak.starter.data.local.db.model.dao

import androidx.room.*
import com.merttutsak.starter.data.local.db.model.entity.Data

@Dao
interface DataDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertData(data: Data)

    @Update
    fun updateData(data: Data)

    @Delete
    fun deleteData(data: Data)

    @Query("DELETE FROM Data")
    fun deleteAllDatas()

    @Query("SELECT * FROM Data WHERE data == :name")
    fun getDataByName(name: String): List<Data>

    @Query("SELECT * FROM Data")
    fun getDatas(): List<Data>
}