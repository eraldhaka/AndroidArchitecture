package com.example.eraldhaka.androidarchitecture.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.eraldhaka.androidarchitecture.domain.RepositoryModel

@Entity
data class Repositories constructor(
    @PrimaryKey
    val name: String)

fun List<Repositories>.asDomainModel(): List<RepositoryModel> {
    return map {
        RepositoryModel(
            name = it.name)
    }
}