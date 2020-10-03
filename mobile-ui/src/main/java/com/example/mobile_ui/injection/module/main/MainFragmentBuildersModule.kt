package com.example.mobile_ui.injection.module.main

import com.example.mobile_ui.vacancy_list.VacancyListFragment
import com.example.mobile_ui.injection.scopes.FragmentScoped
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentBuildersModule {

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeVacancyListFragment(): VacancyListFragment

}