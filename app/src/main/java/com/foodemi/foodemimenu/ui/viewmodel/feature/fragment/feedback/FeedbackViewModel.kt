package com.foodemi.foodemimenu.ui.viewmodel.feature.fragment.feedback

import android.app.Application
import com.foodemi.foodemimenu.data.model.api.ApiResponse
import com.foodemi.foodemimenu.data.model.response.BooleanResponse
import com.foodemi.foodemimenu.data.model.response.ModelFeedback
import com.foodemi.foodemimenu.data.repository.AppRepository
import com.foodemi.foodemimenu.ui.navigation.AppNavigation
import com.foodemi.foodemimenu.ui.navigation.FeedbackNavigator
import com.foodemi.foodemimenu.ui.view.base.BaseViewModel
import kotlinx.coroutines.launch

class FeedbackViewModel(
    application: Application,
    appDataManager: AppRepository
) : BaseViewModel<FeedbackNavigator>(application, appDataManager){

    fun sendFeedback(body: ModelFeedback){
        launch {
            when(val result = appDataManager.getRemoteData().postNewFeedback(body)){
                is ApiResponse.Success<BooleanResponse> -> {
                    var bool = true
                    result.data.data?.let {
                        bool = it
                    }
                    navigator?.handleFeedbackSuccess(bool)
                }
                is ApiResponse.Error   -> {
                    navigator?.handleFeedbackError(result.message)
                }
            }
        }
    }

}