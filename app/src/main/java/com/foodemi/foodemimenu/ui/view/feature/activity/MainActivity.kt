package com.foodemi.foodemimenu.ui.view.feature.activity

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.ui.AppBarConfiguration
import com.foodemi.foodemimenu.R
import com.foodemi.foodemimenu.BR
import com.foodemi.foodemimenu.databinding.ActivityMainBinding
import com.foodemi.foodemimenu.ui.view.base.CoreActivity
import com.foodemi.foodemimenu.ui.viewmodel.factory.ViewModelFactory
import com.foodemi.foodemimenu.ui.viewmodel.feature.activity.ActivityMainViewModel
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class MainActivity : CoreActivity<ActivityMainBinding, ActivityMainViewModel>(),
    HasAndroidInjector {

    @Inject
    lateinit var factory: ViewModelFactory

    private lateinit var appBarConfig: AppBarConfiguration

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.activity_main

    override val viewModel: ActivityMainViewModel
        get() = ViewModelProvider(this, factory).get(ActivityMainViewModel::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupOrientation()
    }

    private fun setupOrientation(){
        if(resources.getBoolean(R.bool.portrait_only)){
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }
    }

}