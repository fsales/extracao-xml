package com.example.extracaoxml.entity

import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
data class Xml(
    @Id
    val id: Long,

    val token: String
) : Serializable