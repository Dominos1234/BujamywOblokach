package com.backend.api.model

import org.hibernate.annotations.DynamicUpdate
import javax.persistence.*

@Entity
@DynamicUpdate
@Table(name = "champions")
class Champion(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    val id: Long = -1,

    @Column(name="name")
    var name: String,

    @Column(name="hp")
    var hp: Double,

    @Column(name="hp_plus")
    var hp_plus: Double,

    @Column(name="hp5")
    var hp5: Double,

    @Column(name="hp5_plus")
    var hp5_plus: Double,

    @Column(name="mp")
    var mp: Double,

    @Column(name="mp_plus")
    var mp_plus: Double,

    @Column(name="mp5")
    var mp5: Double,

    @Column(name="mp5_plus")
    var mp5_plus: Double,
)