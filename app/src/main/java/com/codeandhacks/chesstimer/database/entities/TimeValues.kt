package com.codeandhacks.chesstimer.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TimeValues")
data class TimeValues(
    @PrimaryKey(autoGenerate = true)
    var id : Int = 1,
    val hour : Int,
    val minutes: Int,
    val seconds: Int,
    val increment: Int
)
