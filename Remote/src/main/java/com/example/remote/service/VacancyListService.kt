package com.example.remote.service

import com.example.remote.model.VacancyModel
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface VacancyListService {

    //https://jobs.github.com/positions.json?page=1
    @GET("positions.json")
    fun getVacancyList(@Query("page") page: Int): Flowable<List<VacancyModel>>

}