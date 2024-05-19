package com.application.uvencotest.repositories

import com.application.uvencotest.App
import com.application.uvencotest.data.DataItemCard
import com.application.uvencotest.room.DatabaseBuilder

class RepositoryCupsImpl private constructor() {
    private val db = DatabaseBuilder.getInstance(App.context).dataCupsDao()

    companion object {
        private var INSTANCE: RepositoryCupsImpl? = null


        fun getInstance(): RepositoryCupsImpl = INSTANCE ?: kotlin.run {
            INSTANCE = RepositoryCupsImpl()
            INSTANCE!!
        }
    }

    suspend fun getRepositoryArrayCups():MutableList<DataItemCard>{
        return db.getAllDataItemCard()
    }

    suspend fun addRepositoryArrayCupsList(beginCupsList: MutableList<DataItemCard>):Boolean{
        db.insertAll(beginCupsList)
        return true
    }

    fun repositoryDeleteAllArrayCupsList(){
        db.deleteAll()
    }
}