package com.example.mobile_ui.vacancy_detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import co.popov.mobile_ui.R
import com.example.presentation.model.VacancyView

class VacancyDetailFragment : Fragment() {

    private val titleView: TextView? by lazy { view?.findViewById(R.id.title) }
    private val companyView: TextView? by lazy { view?.findViewById(R.id.company) }
    private val applyView: TextView? by lazy { view?.findViewById(R.id.apply) }
    private val descView: TextView? by lazy { view?.findViewById(R.id.desc) }

    private val vacancy: VacancyView? by lazy { arguments?.getParcelable("vacancy") }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_vacancy_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        titleView?.text = vacancy?.title ?: ""
        companyView?.text = vacancy?.company ?: ""
        applyView?.text = vacancy?.how_to_apply ?: ""
        descView?.text = vacancy?.description ?: ""
    }
}