package com.foodemi.foodemimenu.data.repository

import com.foodemi.foodemimenu.data.source.AppDataSource
import com.foodemi.foodemimenu.data.source.local.LocalData
import com.foodemi.foodemimenu.data.source.local.db.repository.DBRepository
import com.foodemi.foodemimenu.data.source.remote.RemoteData
import javax.inject.Inject

class AppRepository @Inject constructor(
    private val remoteData: RemoteData,
    private val localData: LocalData,
    private val dbRepository: DBRepository,
): AppDataSource {

    fun getRemoteData(): RemoteData {
        return remoteData
    }

    fun getDbRepository(): DBRepository {
        return dbRepository
    }

    fun getLocalData(): LocalData {
        return localData
    }

}