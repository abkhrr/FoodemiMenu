package com.foodemi.foodemimenu.di.module.feature.fragment.state.success

import com.foodemi.foodemimenu.ui.view.feature.fragment.state.success.FragmentOrderSuccess
import com.foodemi.foodemimenu.ui.view.feature.fragment.thankyou.FragmentThankyou
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentOrderSuccessProvider {
    @ContributesAndroidInjector(modules = [FragmentOrderSuccessModule::class])
    abstract fun provideFragmentOrderSuccessFactory(): FragmentOrderSuccess
}