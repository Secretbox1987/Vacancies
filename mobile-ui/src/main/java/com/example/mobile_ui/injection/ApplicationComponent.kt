package com.example.mobile_ui.injection

import android.app.Application
import com.example.mobile_ui.injection.module.ApplicationModule
import com.example.mobile_ui.injection.module.DataModule
import com.example.mobile_ui.injection.module.PresentationModule
import com.example.mobile_ui.injection.module.RemoteModule
import com.example.mobile_ui.app.GithubVacanciesApplication
import com.example.mobile_ui.injection.module.UiModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ApplicationModule::class,
        UiModule::class,
        PresentationModule::class,
        DataModule::class,
        RemoteModule::class
    ])
interface AppComponent: AndroidInjector<GithubVacanciesApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }
    override fun inject(app: GithubVacanciesApplication)
}