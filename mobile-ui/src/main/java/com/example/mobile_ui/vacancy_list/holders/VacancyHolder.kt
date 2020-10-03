package com.example.mobile_ui.vacancy_list.holders

import android.view.View
import android.widget.TextView
import co.popov.mobile_ui.R
import com.example.mobile_ui.vacancy_list.BaseHolder
import com.example.presentation.model.Type
import com.example.presentation.model.VacancyView

class VacancyHolder(
    private val view: View,
    private val itemClick: (data: Type) -> Unit = {}
): BaseHolder(view){

    private val titleView: TextView? by lazy { view.findViewById(R.id.title) }
    private val companyView: TextView? by lazy { view.findViewById(R.id.company) }
    private val applyView: TextView? by lazy { view.findViewById(R.id.apply) }

    override fun bind(obj: Type) {
        val vacancy = obj as VacancyView
        titleView?.text = vacancy.title
        companyView?.text = vacancy.company
        applyView?.text = vacancy.how_to_apply

        view.setOnClickListener {
            itemClick(vacancy)
        }
    }

}