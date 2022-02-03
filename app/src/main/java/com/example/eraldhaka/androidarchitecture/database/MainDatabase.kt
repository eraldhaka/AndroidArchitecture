package com.example.eraldhaka.androidarchitecture.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MainDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllData(databaseMain: List<Repositories>)

    @get:Query("select * from Repositories")
    val dataLiveData: LiveData<List<Repositories>>
}

@Database(entities = [Repositories::class], version = 1, exportSchema = false)
abstract class MainDatabase : RoomDatabase() {
    abstract val dataDao: MainDao
}

private lateinit var INSTANCE: MainDatabase

fun getDatabase(context: Context): MainDatabase {
    synchronized(MainDatabase::class) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room
                .databaseBuilder(
                    context.applicationContext,
                    MainDatabase::class.java,
                    "main_db"
                )
                .fallbackToDestructiveMigration()
                .build()
        }
    }
    return INSTANCE
}
