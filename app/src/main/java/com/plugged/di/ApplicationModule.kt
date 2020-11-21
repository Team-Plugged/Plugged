package com.plugged.di

import com.plugged.api.ApiHelper
import com.plugged.api.ApiHelperImplementation
import com.plugged.api.PluggedApi
import com.plugged.utils.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
class ApplicationModule {

//    @Provides
//    @Singleton
//    fun provideApiHelper(apiHelper: ApiHelperImplementation): ApiHelper = apiHelper



    @Provides
    @Singleton
    fun provideRetrofit(): PluggedApi {

        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(client)
            .build()
            .create(PluggedApi::class.java)
    }
}