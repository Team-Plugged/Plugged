package com.plugged.di

import android.content.Context
import androidx.room.Room
import com.plugged.api.ApiHelper
import com.plugged.api.ApiHelperImplementation
import com.plugged.api.PluggedApi
import com.plugged.db.PatientDao
import com.plugged.db.PatientDb
import com.plugged.utils.Constants.Companion.BASE_URL
import com.plugged.utils.TokenAuthenticator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Boolean.FALSE
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
class ApplicationModule {



    @Provides
    @Singleton
    fun provideArtcleDB(@ApplicationContext context: Context) = Room.databaseBuilder(
        context.applicationContext,
        PatientDb::class.java,
        "patient_db.db"
    ).build()


    @Provides
    @Singleton
    fun provideArticleDAO(patientDb: PatientDb) = patientDb.getPatientDao()


    @Provides
    @Singleton
    fun provideApiHelper(apiHelper: ApiHelperImplementation): ApiHelper = apiHelper



    @Provides
    @Singleton
    fun provideRetrofit(db:PatientDao): PluggedApi {

        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .connectTimeout(120, TimeUnit.SECONDS) //Backend is really slow
            .writeTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
//            .authenticator(TokenAuthenticator(db))
//            .followRedirects(FALSE)
//            .followSslRedirects(FALSE)
            .build()

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(client)
            .build()
            .create(PluggedApi::class.java)
    }
}