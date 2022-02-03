package com.example.eraldhaka.androidarchitecture.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eraldhaka.androidarchitecture.database.getDatabase
import com.example.eraldhaka.androidarchitecture.network.getNetworkService
import com.example.eraldhaka.androidarchitecture.repository.MainRepository
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : ViewModel() {
    private val tag = "MainViewModel"
    private val database = getDatabase(application)
    private val network = getNetworkService()
    private val repository = MainRepository(network,database.dataDao)

    init {
        Log.d(tag, ": init")
        viewModelScope.launch {
            repository.refreshData()
        }
    }

    val myData = repository.data

    override fun onCleared() {
        super.onCleared()
        Log.d(tag, ": onCleared ")
    }
}