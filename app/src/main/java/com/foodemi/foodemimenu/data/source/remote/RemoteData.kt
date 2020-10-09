package com.foodemi.foodemimenu.data.source.remote

import com.foodemi.foodemimenu.data.source.remote.network.ApiService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RemoteData @Inject constructor(private val apiService: ApiService): RemoteDataSource {

    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO


}