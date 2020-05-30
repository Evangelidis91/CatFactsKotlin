package com.evangelidis.catsfacts.model

import io.reactivex.Single
import retrofit2.http.GET

interface CatFactsApi {

    @GET("facts?limit=1000")
    fun getCatFacts(): Single<CatFactsResponse>
}