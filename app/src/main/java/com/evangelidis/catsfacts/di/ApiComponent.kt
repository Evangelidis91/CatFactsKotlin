package com.evangelidis.catsfacts.di

import com.evangelidis.catsfacts.model.CatfactsService
import com.evangelidis.catsfacts.viewmodel.ListViewModel
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiComponent {
    fun inject(service: CatfactsService)
    fun inject(viewModel: ListViewModel)
}