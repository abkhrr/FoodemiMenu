package com.foodemi.foodemimenu.di.builder

import com.foodemi.foodemimenu.di.module.feature.fragment.confirm_order.FragmentConfirmOrderProvider
import com.foodemi.foodemimenu.di.module.feature.fragment.feedback.FragmentFeedbackProvider
import com.foodemi.foodemimenu.di.module.feature.fragment.menudetail.FragmentMenuDetailProvider
import com.foodemi.foodemimenu.di.module.feature.fragment.menulist.FragmentMenuListProvider
import com.foodemi.foodemimenu.di.module.feature.fragment.picktable.FragmentPickTableProvider
import com.foodemi.foodemimenu.di.module.feature.fragment.state.failed.FragmentOrderFailedProvider
import com.foodemi.foodemimenu.di.module.feature.fragment.state.success.FragmentOrderSuccessProvider
import com.foodemi.foodemimenu.di.module.feature.fragment.thankyou.FragmentThankyouProvider
import com.foodemi.foodemimenu.ui.view.feature.activity.MainActivity
import com.foodemi.foodemimenu.ui.view.feature.fragment.confirm_order.FragmentConfirmOrder
import com.foodemi.foodemimenu.ui.view.feature.fragment.state.failed.FragmentOrderFailed
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    // Main Activity
    @ContributesAndroidInjector(
        modules = [
            FragmentMenuListProvider::class,
            FragmentMenuDetailProvider::class,
            FragmentConfirmOrderProvider::class,
            FragmentPickTableProvider::class,
            FragmentThankyouProvider::class,
            FragmentOrderSuccessProvider::class,
            FragmentOrderFailedProvider::class,
            FragmentFeedbackProvider::class
        ])
    abstract fun bindMainActivity(): MainActivity

}