package com.application.uvencotest.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.application.uvencotest.R

@Entity(tableName = "data_cups_table")
data class DataItemCard(
    @PrimaryKey val id: Int,
    val title: String = "",
    val imageId: Int = R.drawable.background_card_cup,
    val volume: String = "",
    val price: String = ""
) {
    fun isDifferentFrom(other: DataItemCard): Boolean {
        return id != other.id ||
                title != other.title ||
                imageId != other.imageId ||
                volume != other.volume ||
                price != other.price
    }
}
