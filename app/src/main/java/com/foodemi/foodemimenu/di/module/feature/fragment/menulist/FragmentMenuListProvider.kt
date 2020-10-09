package com.foodemi.foodemimenu.di.module.feature.fragment.menulist

import com.foodemi.foodemimenu.ui.view.feature.fragment.menu.menulist.FragmentMenuList
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentMenuListProvider {
    @ContributesAndroidInjector(modules = [FragmentMenuListModule::class])
    abstract fun provideFragmentMenuListFactory(): FragmentMenuList
}