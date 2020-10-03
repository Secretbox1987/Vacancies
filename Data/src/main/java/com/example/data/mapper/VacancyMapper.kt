package com.example.data.mapper

import co.popov.data.mapper.EntityMapper
import com.example.data.model.VacancyEntity
import com.example.domain.model.Vacancy
import javax.inject.Inject

open class VacancyMapper @Inject constructor() : EntityMapper<VacancyEntity, Vacancy> {

    override fun mapFromEntity(entity: VacancyEntity): Vacancy {
        return Vacancy(
            id = entity.id, title = entity.title, company = entity.company,
            created_at = entity.created_at, description = entity.description,
            how_to_apply = entity.how_to_apply
        )
    }

    override fun mapToEntity(domain: Vacancy): VacancyEntity {
        return VacancyEntity(
            id = domain.id, title = domain.title, company = domain.company,
            created_at = domain.created_at, description = domain.description,
            how_to_apply = domain.how_to_apply
        )
    }

}