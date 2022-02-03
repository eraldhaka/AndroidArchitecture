package com.example.eraldhaka.androidarchitecture.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.eraldhaka.androidarchitecture.database.MainDao
import com.example.eraldhaka.androidarchitecture.database.asDomainModel
import com.example.eraldhaka.androidarchitecture.domain.RepositoryModel
import com.example.eraldhaka.androidarchitecture.network.MainNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainRepository(private val network: MainNetwork, private val mainDao: MainDao) {
    private val tag = "MainRepository"

    val data: LiveData<List<RepositoryModel>> =
        Transformations.map(mainDao.dataLiveData) {
            it.asDomainModel()
        }

    suspend fun refreshData() {
        try {
            Log.d(tag, "refreshData: try ")
            withContext(Dispatchers.IO) {
                val repos = network.fetchDataTest()
                mainDao.insertAllData(repos)
            }
        } catch (error: Throwable) {
            Log.d(tag, "refreshData catch: "+error.localizedMessage)
        }
    }

}