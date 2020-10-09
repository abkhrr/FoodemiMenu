package com.foodemi.foodemimenu.ui.viewmodel.feature.fragment.state.failed

import android.app.Application
import com.foodemi.foodemimenu.data.repository.AppRepository
import com.foodemi.foodemimenu.ui.navigation.AppNavigation
import com.foodemi.foodemimenu.ui.view.base.BaseViewModel

class OrderFailedViewModel(
    application: Application,
    appDataManager: AppRepository
) : BaseViewModel<AppNavigation>(application, appDataManager)