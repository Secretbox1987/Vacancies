package com.example.presentation.mapper

import com.example.presentation.model.VacancyView
import com.example.domain.model.Vacancy
import com.example.presentation.extentions.escapeHtml
import com.example.presentation.extentions.formatDate
import javax.inject.Inject

open class VacancyViewMapper @Inject constructor() : Mapper<VacancyView, Vacancy> {

    override fun mapToView(type: Vacancy): VacancyView {
        return VacancyView(
            id = type.id,
            title = type.title.escapeHtml(),
            company = type.company.escapeHtml(),
            created_at = type.created_at.formatDate(),
            description = type.description.escapeHtml(),
            how_to_apply = type.how_to_apply.escapeHtml()
        )
    }
}