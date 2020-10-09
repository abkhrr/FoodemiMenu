package com.foodemi.foodemimenu.ui.viewmodel.feature.activity

import android.app.Application
import com.foodemi.foodemimenu.data.repository.AppRepository
import com.foodemi.foodemimenu.ui.view.base.BaseViewModel

class ActivityMainViewModel(
    application: Application,
    appDataManager: AppRepository
) : BaseViewModel<Any>(application, appDataManager)