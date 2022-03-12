package com.backend.api.repo

import com.backend.api.model.Champion
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ChampionsRepository: CrudRepository<Champion, Long>, StatisticsRepository{

    fun findByName(name: String): Optional<Champion>

    fun findByIdAndName(id: Long, name: String): Optional<Champion>

}