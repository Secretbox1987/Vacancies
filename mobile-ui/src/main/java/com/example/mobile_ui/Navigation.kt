package com.example.mobile_ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import co.popov.mobile_ui.R
import com.example.mobile_ui.vacancy_detail.VacancyDetailFragment
import com.example.presentation.model.VacancyView

class Navigation(
    private val activity: FragmentActivity? = null,
    private var containerId: Int = R.id.container,
    private var fragmentManager: FragmentManager? = activity?.supportFragmentManager
) {

    fun add(fragment: Fragment, tag: String){
        val bt = fragmentManager?.beginTransaction()
        bt?.add(containerId, fragment, tag)
            ?.addToBackStack(tag)
            ?.commitAllowingStateLoss()
    }

    fun replace(fragment: Fragment, tag: String = ""){
        fragmentManager?.beginTransaction()
            ?.replace(containerId, fragment, tag)
            ?.commitAllowingStateLoss()
    }

    fun replaceWithBack(fragment: Fragment, tag: String = ""){
        fragmentManager?.beginTransaction()
            ?.replace(containerId, fragment, tag)
            ?.addToBackStack(tag)
            ?.commitAllowingStateLoss()
    }

    fun goBack() {
        activity?.onBackPressed()
    }

    fun goToVacancyDetail(vacancyView: VacancyView){
        val fragment = VacancyDetailFragment().apply {
            arguments = Bundle().apply {
                putParcelable("vacancy", vacancyView)
            }
        }
        replaceWithBack(fragment)
    }


}