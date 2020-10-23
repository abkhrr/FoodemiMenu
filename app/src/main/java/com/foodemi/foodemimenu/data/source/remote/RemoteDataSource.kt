package com.foodemi.foodemimenu.data.source.remote

import com.foodemi.foodemimenu.data.model.api.ApiResponse
import com.foodemi.foodemimenu.data.model.response.BooleanResponse
import com.foodemi.foodemimenu.data.model.response.ModelFeedback
import com.foodemi.foodemimenu.data.model.response.ModelMenuSectioned
import com.foodemi.foodemimenu.data.model.response.ModelOrder
import retrofit2.http.Body

interface RemoteDataSource {

    suspend fun getSectionedMenu()                      : ApiResponse<ModelMenuSectioned>
    suspend fun postNewOrder(body: ModelOrder)          : ApiResponse<BooleanResponse>
    suspend fun postNewFeedback(body: ModelFeedback)    : ApiResponse<BooleanResponse>
}