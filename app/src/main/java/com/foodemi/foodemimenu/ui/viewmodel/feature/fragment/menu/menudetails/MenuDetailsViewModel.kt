package com.foodemi.foodemimenu.ui.viewmodel.feature.fragment.menu.menudetails

import android.app.Application
import com.foodemi.foodemimenu.data.repository.AppRepository
import com.foodemi.foodemimenu.ui.navigation.AppNavigation
import com.foodemi.foodemimenu.ui.view.base.BaseViewModel

class MenuDetailsViewModel(
    application: Application,
    appDataManager: AppRepository
) : BaseViewModel<AppNavigation>(application, appDataManager)