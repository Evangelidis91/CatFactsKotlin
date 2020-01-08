package com.evangelidis.catsfacts.di

import com.evangelidis.catsfacts.model.CatfactsApi
import com.evangelidis.catsfacts.model.CatfactsService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ApiModule {

    private val BASE_URL = "https://catfact.ninja"

    @Provides
    fun provideCountiesApi(): CatfactsApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(CatfactsApi::class.java)
    }

    @Provides
    fun provideCountriesService(): CatfactsService{
        return  CatfactsService()
    }
}