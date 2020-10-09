package com.foodemi.foodemimenu.di.module.feature.fragment.thankyou

import com.foodemi.foodemimenu.ui.view.feature.fragment.picktable.FragmentPickTable
import com.foodemi.foodemimenu.ui.view.feature.fragment.thankyou.FragmentThankyou
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentThankyouProvider {
    @ContributesAndroidInjector(modules = [FragmentThankyouModule::class])
    abstract fun provideFragmentThankyouFactory(): FragmentThankyou
}