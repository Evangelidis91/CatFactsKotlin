package com.evangelidis.catsfacts.model

import com.evangelidis.catsfacts.di.DaggerApiComponent
import io.reactivex.Single
import javax.inject.Inject

class CatfactsService {

    @Inject
    lateinit var api: CatfactsApi

    init {
        DaggerApiComponent.create().inject(this)
    }

    fun getCatfacts() : Single<CatfactsResponse> {
        return api.getCatfacts()
    }
}