package com.example.mobile_ui.vacancy_list.holders

import android.view.View
import android.widget.TextView
import co.popov.mobile_ui.R
import com.example.mobile_ui.vacancy_list.BaseHolder
import com.example.presentation.model.ErrorView
import com.example.presentation.model.Type

class ErrorHolder(
    private val view: View,
    private val refresh: () -> Unit = {}
): BaseHolder(view){

    private val messageView: TextView? by lazy { view.findViewById(R.id.message) }
    private val refreshView: TextView? by lazy { view.findViewById(R.id.refresh) }

    override fun bind(obj: Type) {
        val vacancy = obj as ErrorView
        messageView?.text = vacancy.message

        refreshView?.setOnClickListener {
            refresh()
        }
    }

}