package com.evangelidis.catsfacts.model

import com.evangelidis.catsfacts.di.DaggerApiComponent
import io.reactivex.Single
import javax.inject.Inject

class CatFactsService {

    @Inject
    lateinit var api: CatFactsApi

    init {
        DaggerApiComponent.create().inject(this)
    }

    fun getCatFacts() : Single<CatFactsResponse> {
        return api.getCatFacts()
    }
}