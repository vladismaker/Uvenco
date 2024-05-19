package com.application.uvencotest.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.application.uvencotest.data.DataItemCard

@Dao
interface DataCupsDao {
    @Insert
    suspend fun insert(dataCups: DataItemCard)

    @Insert
    suspend fun insertAll(dataCupsList: List<DataItemCard>)

    @Query("SELECT * FROM data_cups_table")
    suspend fun getAllDataItemCard(): MutableList<DataItemCard>

    @Query("DELETE FROM data_cups_table")
    fun deleteAll()
}