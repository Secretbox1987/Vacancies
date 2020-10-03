package com.example.mobile_ui.vacancy_list

import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import co.popov.mobile_ui.R
import com.example.mobile_ui.vacancy_list.holders.ErrorHolder
import com.example.mobile_ui.vacancy_list.holders.HeaderHolder
import com.example.mobile_ui.vacancy_list.holders.PageLoaderHolder
import com.example.mobile_ui.vacancy_list.holders.VacancyHolder
import com.example.presentation.model.ErrorView
import com.example.presentation.model.ItemType
import com.example.presentation.model.Type

class VacancyAdapter(
    var vacancyList: List<Type> = mutableListOf(),
    private val itemClick: (data: Type) -> Unit = {},
    private val refresh: () -> Unit = {}
): RecyclerView.Adapter<BaseHolder>() {

    override fun getItemCount() = vacancyList.size

    override fun getItemViewType(position: Int) = vacancyList[position].type

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder {
        when(viewType){
            ItemType.HEADER.type -> {
                val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_header, parent, false)
                return HeaderHolder(itemView)
            }
            ItemType.VACANCY.type -> {
                val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_vacancy, parent, false)
                return VacancyHolder(itemView, itemClick)
            }
            ItemType.PAGE_LOADER.type -> {
                val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_page_loader, parent, false)
                return PageLoaderHolder(itemView)
            }
            ItemType.ERROR.type -> {
                val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_error, parent, false)
                return ErrorHolder(itemView, refresh)
            }
        }
        return BaseHolder(View(parent.context))
    }

    override fun onBindViewHolder(holder: BaseHolder, position: Int) {
        val data = vacancyList[position]
        holder.bind(data)
    }

    fun insertData(data: List<Type>){
        val oldSize = (vacancyList as MutableList).size
        (vacancyList as? MutableList)?.addAll(data)
        val newSize = (vacancyList as? MutableList)?.size ?: 0
        if(newSize > 1){
            notifyItemRangeInserted(oldSize, newSize - 1)
        }else{
            notifyDataSetChanged()
        }
    }

    fun showError(message: String){
        if(vacancyList.isEmpty()) {
            (vacancyList as? MutableList)?.add(
                ErrorView(message = message)
            )
            notifyItemInserted(vacancyList.size - 1)
        }
    }

    fun hideError(){
        if(vacancyList.isNotEmpty()) {
            val item = (vacancyList as? MutableList)?.get(vacancyList.size - 1)
            if(item?.type == ItemType.ERROR.type){
                (vacancyList as? MutableList)?.removeAt(vacancyList.size - 1)
                notifyItemRemoved(vacancyList.size + 1)
            }
        }
    }

    fun clean(){
        vacancyList = mutableListOf()
        notifyDataSetChanged()
    }

    fun showPageLoader(){
        (vacancyList as? MutableList)?.add(
            Type().apply { type = ItemType.PAGE_LOADER.type }
        )
        notifyItemInserted(vacancyList.size - 1)
    }

    fun hidePageLoader(){
        if(vacancyList.isNotEmpty()){
            val item = (vacancyList as? MutableList)?.get(vacancyList.size - 1)
            if(item?.type == ItemType.PAGE_LOADER.type){
                (vacancyList as? MutableList)?.removeAt(vacancyList.size - 1)
                notifyItemRemoved(vacancyList.size)
            }
        }
    }

}