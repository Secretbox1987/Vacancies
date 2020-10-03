package com.example.mobile_ui.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import co.popov.mobile_ui.R
import com.example.mobile_ui.vacancy_list.VacancyListFragment
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject internal lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    @Suppress("UNCHECKED_CAST")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, VacancyListFragment())
            .commitAllowingStateLoss()
    }
}