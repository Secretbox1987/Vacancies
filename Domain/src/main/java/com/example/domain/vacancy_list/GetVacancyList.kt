package com.example.domain.vacancy_list

import com.example.domain.executor.PostExecutionThread
import com.example.domain.interactor.ObservableUseCase
import com.example.domain.model.Vacancy
import com.example.domain.repository.VacancyListRepository
import io.reactivex.Observable
import javax.inject.Inject

open class GetVacancyList @Inject constructor(
        private val vacancyListRepository: VacancyListRepository,
        postExecutionThread: PostExecutionThread
) : ObservableUseCase<List<Vacancy>, Int?>(postExecutionThread) {

    public override fun buildUseCaseObservable(params: Int?): Observable<List<Vacancy>> {
        val page = params ?: 0
        return vacancyListRepository.getVacancyList(page)
    }

}