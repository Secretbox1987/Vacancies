package com.example.mobile_ui.vacancy_list

import androidx.recyclerview.widget.DiffUtil
import com.example.presentation.model.HeaderView
import com.example.presentation.model.Type
import com.example.presentation.model.VacancyView

class VacancyDiffCallback(
    val mOldList: List<Type>,
    val mNewList: List<Type>
) : DiffUtil.Callback() {

    override fun getOldListSize() = mOldList.size

    override fun getNewListSize() = mNewList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = mOldList[oldItemPosition]
        val newItem = mNewList[newItemPosition]
        return oldItem.type == newItem.type
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = mOldList[oldItemPosition]
        val newItem = mNewList[newItemPosition]

        when{
            oldItem is VacancyView && newItem is VacancyView -> {
                val isIdSame = oldItem.id == newItem.id
                val isTitleSame = oldItem.title == newItem.title
                val isCompanySame = oldItem.company == newItem.company
                val isCreatedSame = oldItem.created_at == newItem.created_at
                val isApplySame = oldItem.how_to_apply == newItem.how_to_apply
                return isIdSame && isTitleSame && isCompanySame && isCreatedSame && isApplySame
            }
            oldItem is HeaderView && newItem is HeaderView -> {
                return oldItem.created_at == newItem.created_at
            }
            else -> {
                return false
            }
        }

    }
}
