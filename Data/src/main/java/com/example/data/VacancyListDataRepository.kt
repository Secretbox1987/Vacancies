package com.example.data

import com.example.data.mapper.VacancyMapper
import com.example.data.store.VacancyListRemoteDataStore
import com.example.domain.model.Vacancy
import com.example.domain.repository.VacancyListRepository
import io.reactivex.Observable
import java.util.*
import javax.inject.Inject

class VacancyListDataRepository @Inject constructor(
    private val mapper: VacancyMapper,
    private val factory: VacancyListRemoteDataStore,
) : VacancyListRepository {

    override fun getVacancyList(page: Int): Observable<List<Vacancy>> {
        return factory.getVacancyList(page).toObservable()
            .map { list ->
                list
                    .map { vacancy ->
                        mapper.mapFromEntity(vacancy)
                    }
                    .sortedByDescending {
                        val calendar1 = Calendar.getInstance()
                        calendar1.timeZone = TimeZone.getTimeZone("UTC");
                        calendar1.time = Date(it.created_at)
                        calendar1
                    }
            }
    }


}