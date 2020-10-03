package com.example.mobile_ui.vacancy_list.holders

import androidx.recyclerview.widget.RecyclerView
import android.view.View
import com.example.presentation.model.Type

open class BaseHolder(view: View): RecyclerView.ViewHolder(view){

    open fun bind(obj: Type) {}

}