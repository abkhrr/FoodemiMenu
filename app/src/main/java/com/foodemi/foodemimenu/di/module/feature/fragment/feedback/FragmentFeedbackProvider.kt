package com.foodemi.foodemimenu.di.module.feature.fragment.feedback

import com.foodemi.foodemimenu.ui.view.feature.fragment.feedback.FragmentFeedback
import com.foodemi.foodemimenu.ui.view.feature.fragment.menu.menudetail.FragmentMenuDetail
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentFeedbackProvider {
    @ContributesAndroidInjector(modules = [FragmentFeedbackModule::class])
    abstract fun provideFragmentFeedbackFactory(): FragmentFeedback
}