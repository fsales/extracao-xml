package com.example.extracaoxml.repository

import java.sql.Clob


interface XmlProjection {
    fun getXml(): Clob
    fun getToken(): String
}