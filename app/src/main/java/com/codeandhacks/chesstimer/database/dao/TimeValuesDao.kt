package com.codeandhacks.chesstimer.database.dao

import androidx.room.*
import com.codeandhacks.chesstimer.database.entities.TimeValues

@Dao
interface TimeValuesDao {

    @Query("SELECT * FROM TimeValues WHERE id=1")
    fun findFirst() : TimeValues

    @Insert
    fun insertValues(timevalues : TimeValues)

    @Update
    fun updateValues(timevalues : TimeValues)

    @Delete
    fun deleteValues(timevalues : TimeValues)
}