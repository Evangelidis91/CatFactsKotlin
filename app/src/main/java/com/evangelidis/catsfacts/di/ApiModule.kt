package com.evangelidis.catsfacts.di

import com.evangelidis.catsfacts.Constants.BASE_URL
import com.evangelidis.catsfacts.model.CatFactsApi
import com.evangelidis.catsfacts.model.CatFactsService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ApiModule {

    @Provides
    fun provideCountiesApi(): CatFactsApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(CatFactsApi::class.java)
    }

    @Provides
    fun provideCountriesService(): CatFactsService{
        return  CatFactsService()
    }
}