package com.example.mobile_ui.injection.module

import com.example.domain.executor.PostExecutionThread
import com.example.mobile_ui.activity.MainActivity
import com.example.mobile_ui.UiThread
import com.example.mobile_ui.injection.module.main.MainFragmentBuildersModule
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module
abstract class UiModule {

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun providePostExecutionThread(uiThread: UiThread): PostExecutionThread = uiThread
    }

    @ContributesAndroidInjector(modules = [MainFragmentBuildersModule::class])
    abstract fun contributesMainActivity(): MainActivity

}