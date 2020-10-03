package com.example.mobile_ui.injection.module

import co.popov.mobile_ui.BuildConfig
import com.example.data.repository.VacancyListRemote
import com.example.remote.VacancyListRemoteImpl
import com.example.remote.service.VacancyListService
import com.example.remote.service.VacancyListServiceFactory
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class RemoteModule {

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun provideGithubService(): VacancyListService {
            return VacancyListServiceFactory.makeGithubTrendingService(BuildConfig.DEBUG)
        }
    }

    @Binds
    abstract fun bindProjectsRemote(projectsRemote: VacancyListRemoteImpl): VacancyListRemote
}