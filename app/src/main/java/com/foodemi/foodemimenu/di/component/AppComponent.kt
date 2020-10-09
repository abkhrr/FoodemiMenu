package com.foodemi.foodemimenu.di.component

import android.app.Application
import com.foodemi.foodemimenu.di.application.CoreApplication
import com.foodemi.foodemimenu.di.builder.ActivityBuilder
import com.foodemi.foodemimenu.di.module.network.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, NetworkModule::class, ActivityBuilder::class])
interface AppComponent {
    fun inject(app: CoreApplication)
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}