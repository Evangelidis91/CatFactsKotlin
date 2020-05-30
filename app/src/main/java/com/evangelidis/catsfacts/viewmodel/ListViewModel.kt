package com.evangelidis.catsfacts.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.evangelidis.catsfacts.di.DaggerApiComponent
import com.evangelidis.catsfacts.model.CatFactsResponse
import com.evangelidis.catsfacts.model.CatFactsService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ListViewModel : ViewModel() {

    @Inject
    lateinit var catfactsService: CatFactsService

    private val disposable = CompositeDisposable()

    val catfacts = MutableLiveData<CatFactsResponse>()
    val catfactLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    init {
        DaggerApiComponent.create().inject(this)
    }

    fun refresh(){
        fetchCatFacts()
    }

    private fun fetchCatFacts(){
        loading.value = true
        disposable.add(
            catfactsService.getCatFacts()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<CatFactsResponse>(){
                    override fun onSuccess(response: CatFactsResponse) {
                        catfacts.value = response
                        catfactLoadError.value = false
                        loading.value = false
                    }

                    override fun onError(e: Throwable) {
                        catfactLoadError.value = true
                        loading.value = false
                    }
                })
        )
    }
}