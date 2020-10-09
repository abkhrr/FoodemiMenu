package com.foodemi.foodemimenu.di.module.feature.fragment.picktable

import com.foodemi.foodemimenu.ui.view.feature.fragment.menu.menulist.FragmentMenuList
import com.foodemi.foodemimenu.ui.view.feature.fragment.picktable.FragmentPickTable
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentPickTableProvider {
    @ContributesAndroidInjector(modules = [FragmentPickTableModule::class])
    abstract fun provideFragmentPickTableFactory(): FragmentPickTable
}