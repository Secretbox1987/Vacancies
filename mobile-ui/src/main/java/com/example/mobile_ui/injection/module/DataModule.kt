package com.example.mobile_ui.injection.module

import com.example.data.VacancyListDataRepository
import com.example.domain.repository.VacancyListRepository
import dagger.Binds
import dagger.Module

@Module
abstract class DataModule {

    @Binds
    abstract fun bindDataRepository(dataRepository: VacancyListDataRepository): VacancyListRepository

}