package com.backend.api.repo.projections
interface StatsProjection {
    val avg_hp : Double
    val avg_hp_plus : Double
    val avg_hp5 : Double
    val avg_hp5_plus : Double
    val avg_mp : Double
    val avg_mp_plus : Double
    val avg_mp5 : Double
    val avg_mp5_plus : Double
}