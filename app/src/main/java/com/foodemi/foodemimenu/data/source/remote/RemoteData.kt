package com.foodemi.foodemimenu.data.source.remote

import com.foodemi.foodemimenu.data.model.api.ApiResponse
import com.foodemi.foodemimenu.data.model.response.ModelMenuSectioned
import com.foodemi.foodemimenu.data.source.remote.network.ApiService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RemoteData @Inject constructor(private val apiService: ApiService): RemoteDataSource {

    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO

    override suspend fun getSectionedMenu(): ApiResponse<ModelMenuSectioned>  =
        withContext(ioDispatcher) {
            try {
                val loginResponse = apiService.getSectionedMenu()
                return@withContext ApiResponse.Success(loginResponse)
            }
            catch (e: Exception) {
                return@withContext ApiResponse.Error(e.localizedMessage)
            }
        }


}