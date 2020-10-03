package com.example.remote.mapper

import com.example.data.model.VacancyEntity
import com.example.remote.model.VacancyModel
import javax.inject.Inject

class VacancyModelMapper @Inject constructor(): ModelMapper<VacancyModel, VacancyEntity> {

    override fun mapFromModel(model: VacancyModel): VacancyEntity {
        return VacancyEntity(
            id = model.id, title = model.title, company = model.company,
            created_at = model.created_at, description = model.description,
            how_to_apply = model.how_to_apply
        )
    }

}