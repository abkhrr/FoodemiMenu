package com.foodemi.foodemimenu.data.source.remote.network

import com.foodemi.foodemimenu.data.model.api.ApiResponse
import com.foodemi.foodemimenu.data.model.response.ModelMenu
import com.foodemi.foodemimenu.data.model.response.ModelMenuSectioned
import retrofit2.Response
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

}