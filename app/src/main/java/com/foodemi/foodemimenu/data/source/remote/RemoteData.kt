package com.foodemi.foodemimenu.data.source.remote

import com.foodemi.foodemimenu.data.model.api.ApiResponse
import com.foodemi.foodemimenu.data.model.response.BooleanResponse
import com.foodemi.foodemimenu.data.model.response.ModelFeedback
import com.foodemi.foodemimenu.data.model.response.ModelMenuSectioned
import com.foodemi.foodemimenu.data.model.response.ModelOrder
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

    override suspend fun postNewOrder(body: ModelOrder): ApiResponse<BooleanResponse> =
        withContext(ioDispatcher) {
            try {
                val orderResponse = apiService.postNewOrder(body)
                return@withContext ApiResponse.Success(orderResponse)
            }
            catch (e: Exception) {
                return@withContext ApiResponse.Error(e.localizedMessage)
            }
        }

    override suspend fun postNewFeedback(body: ModelFeedback): ApiResponse<BooleanResponse> =
        withContext(ioDispatcher) {
            try {
                val feedbackResponse = apiService.postNewFeedback(body)
                return@withContext ApiResponse.Success(feedbackResponse)
            }
            catch (e: Exception) {
                return@withContext ApiResponse.Error(e.localizedMessage)
            }
        }


}