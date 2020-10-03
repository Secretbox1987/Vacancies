package com.example.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.presentation.mapper.VacancyViewMapper
import com.example.presentation.model.VacancyView
import com.example.domain.model.Vacancy
import com.example.domain.vacancy_list.GetVacancyList
import com.example.presentation.model.HeaderView
import com.example.presentation.model.Type
import com.example.presentation.state.Resource
import com.example.presentation.state.ResourceState
import io.reactivex.observers.DisposableObserver
import java.net.UnknownHostException
import javax.inject.Inject

open class VacancyListViewModel @Inject internal constructor(
        private val getVacancyList: GetVacancyList?,
        private val mapper: VacancyViewMapper
): ViewModel() {

    var page = 1

    private val liveData: MutableLiveData<Resource<List<Type>>> = MutableLiveData()

    init {
        fetchVacancyList()
    }

    override fun onCleared() {
        getVacancyList?.dispose()
        super.onCleared()
    }

    fun getVacancyList(): LiveData<Resource<List<Type>>> {
        return liveData
    }

    fun fetchVacancyList() {
        liveData.postValue(Resource(ResourceState.LOADING, null, null))
        getVacancyList?.execute(VacancyListSubscriber(), page)
    }

    inner class VacancyListSubscriber: DisposableObserver<List<Vacancy>>() {
        override fun onNext(t: List<Vacancy>) {
            val resultList = mutableListOf<Type>()
            var headerString = ""
            t.forEach {
                val vacancyView = mapper.mapToView(it)
                val createdAt = vacancyView.created_at
                if(headerString != createdAt){
                    resultList.add(HeaderView(created_at = createdAt))
                    headerString = createdAt ?: ""
                }
                resultList.add(vacancyView)
            }
            page++
            liveData.postValue(
                Resource(ResourceState.SUCCESS, resultList, null)
            )
        }

        override fun onComplete() { }

        override fun onError(e: Throwable) {
            var message = e.localizedMessage
            if(e is UnknownHostException){
                message = "No internet connection"
            }
            liveData.postValue(Resource(ResourceState.ERROR, null, message))
        }

    }

}