package com.evangelidis.catsfacts.model

import io.reactivex.Single
import retrofit2.http.GET

interface CatfactsApi {

    @GET("facts?limit=1000")
    fun getCatfacts(): Single<CatfactsResponse>
}