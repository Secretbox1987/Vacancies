package com.example.remote

import com.example.data.model.VacancyEntity
import com.example.data.repository.VacancyListRemote
import com.example.remote.mapper.VacancyModelMapper
import com.example.remote.service.VacancyListService
import io.reactivex.Flowable
import javax.inject.Inject

class VacancyListRemoteImpl @Inject constructor(
        private val service: VacancyListService,
        private val mapper: VacancyModelMapper
) : VacancyListRemote {

    override fun getVacancyList(page: Int): Flowable<List<VacancyEntity>> {
        return service.getVacancyList(page)
                .map { list ->
                    list.map { vacancy -> mapper.mapFromModel(vacancy) }
                }
    }
}