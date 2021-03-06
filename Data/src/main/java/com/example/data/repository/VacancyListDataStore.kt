package com.example.data.repository

import com.example.data.model.VacancyEntity
import io.reactivex.Flowable

interface VacancyListDataStore {

    fun getVacancyList(page: Int): Flowable<List<VacancyEntity>>


}