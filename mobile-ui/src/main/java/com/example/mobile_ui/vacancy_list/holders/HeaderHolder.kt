package com.example.mobile_ui.vacancy_list.holders

import android.view.View
import android.widget.TextView
import co.popov.mobile_ui.R
import com.example.mobile_ui.vacancy_list.BaseHolder
import com.example.presentation.model.HeaderView
import com.example.presentation.model.Type

class HeaderHolder(
    private val view: View
): BaseHolder(view){

    private val createdView: TextView? by lazy { view.findViewById(R.id.created) }

    override fun bind(obj: Type) {
        val data = obj as HeaderView
        createdView?.text = data.created_at
    }

}