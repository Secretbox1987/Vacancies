package com.example.remote.service

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object VacancyListServiceFactory {

    fun makeGithubTrendingService(isDebug: Boolean): VacancyListService {
        val okHttpClient = makeOkHttpClient(
                makeLoggingInterceptor((isDebug))
        )
        return makeGithubTrendingService(okHttpClient, Gson())
    }

    private fun makeGithubTrendingService(okHttpClient: OkHttpClient, gson: Gson): VacancyListService {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://jobs.github.com/")
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        return retrofit.create(VacancyListService::class.java)
    }

    private fun makeOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .build()
    }

    private fun makeLoggingInterceptor(isDebug: Boolean): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = if (isDebug) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
        return logging
    }

}