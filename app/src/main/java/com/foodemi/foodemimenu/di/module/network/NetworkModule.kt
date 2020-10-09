package com.foodemi.foodemimenu.di.module.network

import android.app.Application
import android.content.Context
import com.foodemi.foodemimenu.BuildConfig
import com.foodemi.foodemimenu.data.source.remote.network.ApiService
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application
    }

    @Provides
    @Singleton
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.BASE_URL)
            .client(buildRetrofitClient())
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    private fun buildRetrofitClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        builder.readTimeout(120, TimeUnit.SECONDS)
        builder.connectTimeout(120, TimeUnit.SECONDS)

        if (BuildConfig.DEBUG) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addInterceptor(interceptor)
            builder.addInterceptor { chain ->
                val request = chain.request()
                val newRequest = request.newBuilder().build()
                chain.proceed(newRequest)
            }
        }
        return builder.build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    //@Provides
    //@DBQualifier
    //fun provideDatabaseName(): String {
    //    return Const.DB_NAME
    //}

    //@Provides
    //@Singleton
    //fun provideAppDatabase(@DBQualifier dbName: String, context: Context): AppDatabase {
    //    return Room.databaseBuilder(context, AppDatabase::class.java, dbName)
    //        .fallbackToDestructiveMigration().build()
    //}

    //@Provides
    //@PreferenceQualifier
    //fun providePreferenceName(): String {
    //    return Const.PREFS_NAME
    //}

    //@Provides
    //@Singleton
    //fun provideSharedPreferences(@PreferenceQualifier prefName: String, context: Context): SharedPreferences {
    //    return context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
    //}

}