package com.example.data.store

import com.example.data.model.VacancyEntity
import com.example.data.repository.VacancyListDataStore
import com.example.data.repository.VacancyListRemote
import io.reactivex.Flowable
import javax.inject.Inject

open class VacancyListRemoteDataStore @Inject constructor(
        private val projectsRemote: VacancyListRemote
) : VacancyListDataStore {

    override fun getVacancyList(page: Int): Flowable<List<VacancyEntity>> {
        return projectsRemote.getVacancyList(page)
    }

}