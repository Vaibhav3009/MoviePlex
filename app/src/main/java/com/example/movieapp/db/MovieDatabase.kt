package com.example.movieapp.db

import android.content.Context
import androidx.room.*
import com.example.movieapp.db.MovieDao
import com.example.movieapp.models.Result


@Database(
    entities = [Result::class],
    version = 1
)

abstract class MovieDatabase : RoomDatabase() {

    abstract fun getMovieDao(): MovieDao

    companion object {
        @Volatile
        private var instance: MovieDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                MovieDatabase::class.java,
                "movies_db.db"
            ).build()
    }
}