package com.foodemi.foodemimenu.di.module.feature.fragment.state.failed

import com.foodemi.foodemimenu.ui.view.feature.fragment.state.failed.FragmentOrderFailed
import com.foodemi.foodemimenu.ui.view.feature.fragment.thankyou.FragmentThankyou
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentOrderFailedProvider {
    @ContributesAndroidInjector(modules = [FragmentOrderFailedModule::class])
    abstract fun provideFragmentOrderFailedFactory(): FragmentOrderFailed
}