package com.example.domain.repository

import com.example.domain.model.Vacancy
import io.reactivex.Observable


interface VacancyListRepository {

    fun getVacancyList(page: Int): Observable<List<Vacancy>>

}