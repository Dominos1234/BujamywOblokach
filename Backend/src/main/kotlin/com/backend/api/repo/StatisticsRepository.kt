package com.backend.api.repo

import com.backend.api.repo.projections.StatsProjection
import org.springframework.data.jpa.repository.Query

interface StatisticsRepository {

    @Query("Select " +
            "avg(hp) as avg_hp," +
            "avg(hp_plus) as avg_hp_plus," +
            "avg(hp5) as avg_hp5," +
            "avg(hp5_plus) as avg_hp5_plus," +
            "avg(mp) as avg_mp," +
            "avg(mp_plus) as avg_mp_plus," +
            "avg(mp5) as avg_mp5," +
            "avg(mp5_plus) as avg_mp5_plus " +
            "from champions", nativeQuery = true)
    fun getStats() : StatsProjection
}
