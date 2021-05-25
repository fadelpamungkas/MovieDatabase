package com.fadelpamungkas.core.data.localsource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.fadelpamungkas.core.data.localsource.AppDAO
import com.fadelpamungkas.core.data.localsource.MovieEntity

@Database(entities = [MovieEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun appDAO(): AppDAO

}