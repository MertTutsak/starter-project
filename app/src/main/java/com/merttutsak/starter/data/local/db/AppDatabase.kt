package com.merttutsak.starter.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.merttutsak.starter.data.local.db.converter.DateTypeConverter
import com.merttutsak.starter.data.local.db.model.dao.DataDao
import com.merttutsak.starter.data.local.db.model.entity.Data
import com.merttutsak.starter.utility.Constants
import com.merttutsak.starter.utility.extension.isNull

@Database(entities = [Data::class], version = 2)
@TypeConverters(DateTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dataDao(): DataDao

    companion object {
        var instance: AppDatabase? = null

        fun createDatabase(context: Context): AppDatabase {
            instance.isNull {
                synchronized(AppDatabase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        Constants.App.APP_DB_NAME
                    ).fallbackToDestructiveMigration().build()
                }
            }

            return instance!!
        }

        fun destroyDatabase() {
            instance = null
        }
    }
}