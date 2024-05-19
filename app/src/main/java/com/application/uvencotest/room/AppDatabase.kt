package com.application.uvencotest.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.application.uvencotest.data.DataItemCard

@Database(entities = [DataItemCard::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dataCupsDao(): DataCupsDao
}