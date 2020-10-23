package com.foodemi.foodemimenu.data.source.remote.network

import com.foodemi.foodemimenu.data.model.response.BooleanResponse
import com.foodemi.foodemimenu.data.model.response.ModelFeedback
import com.foodemi.foodemimenu.data.model.response.ModelMenuSectioned
import com.foodemi.foodemimenu.data.model.response.ModelOrder
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    companion object{
        fun getApiService(): Class<ApiService> {
            return ApiService::class.java
        }
    }

    // GET ALL MENU WITH SECTION
    @GET("admin/api/widget/v1/get/list/sectioned/menu")
    suspend fun getSectionedMenu(): ModelMenuSectioned

    @POST("admin/api/widget/v1/add/order")
    suspend fun postNewOrder(@Body body: ModelOrder): BooleanResponse

    @POST("admin/api/widget/v1/foodemi/add/feedback")
    suspend fun postNewFeedback(@Body body: ModelFeedback): BooleanResponse

}