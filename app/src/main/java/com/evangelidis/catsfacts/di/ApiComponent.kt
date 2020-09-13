package com.evangelidis.catsfacts.di

import com.evangelidis.catsfacts.model.CatFactsService
import com.evangelidis.catsfacts.viewmodel.ListViewModel
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiComponent {

    fun inject(service: CatFactsService)

    fun inject(viewModel: ListViewModel)
}