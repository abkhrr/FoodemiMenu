package com.foodemi.foodemimenu.di.module.feature.fragment.menudetail

import com.foodemi.foodemimenu.ui.view.feature.fragment.menu.menudetail.FragmentMenuDetail
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentMenuDetailProvider {
    @ContributesAndroidInjector(modules = [FragmentMenuDetailModule::class])
    abstract fun provideFragmentMenuDetailFactory(): FragmentMenuDetail
}