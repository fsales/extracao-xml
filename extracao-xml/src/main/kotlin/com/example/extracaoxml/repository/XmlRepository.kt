package com.example.extracaoxml.repository

import com.example.extracaoxml.entity.Xml
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface XmlRepository : JpaRepository<Xml, Long> {

    @Query(
        value = """ 
        /*+ PARALLEL(6) */  SELECT
            x.xml.getclobval() Xml, -- A coluna foi definida com o tipo xmltype no banco oracle
            x.tokens tokens 
        FROM
            xml x
        WHERE
            x.token IN :tokens
            """, nativeQuery = true
    )
    fun findByTokenqIn(@Param("tokens") tokens: Array<String?>?): ArrayList<XmlProjection>?

}
