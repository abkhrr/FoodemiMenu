package com.foodemi.foodemimenu.di.module.feature.fragment.confirm_order

import com.foodemi.foodemimenu.ui.view.feature.fragment.confirm_order.FragmentConfirmOrder
import com.foodemi.foodemimenu.ui.view.feature.fragment.menu.menudetail.FragmentMenuDetail
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentConfirmOrderProvider {
    @ContributesAndroidInjector(modules = [FragmentConfirmOrderModule::class])
    abstract fun provideFragmentConfirmOrderFactory(): FragmentConfirmOrder
}