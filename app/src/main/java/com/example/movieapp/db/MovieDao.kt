package com.example.movieapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.movieapp.models.Result

@Dao
interface MovieDao {
    @Insert(onConflict = REPLACE)
    suspend fun upsert(movie:Result):Long

    @Query("SELECT * FROM result")
    fun getAllResults(): LiveData<List<Result>>

    @Delete
    suspend fun deleteResult(movie: Result)




}