package com.example.extracaoxml.service

import com.example.extracaoxml.repository.XmlRepository
import com.example.extracaoxml.vo.XmlVO
import com.example.extracaoxml.xml.Arquivo
import com.example.extracaoxml.xml.ExtrairArquivo
import lombok.extern.log4j.Log4j2
import org.apache.commons.io.IOUtils
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service


@Service
@Log4j2
class XmlService(
    private val xmlRepository: XmlRepository,
    private @Qualifier("ExtrairArquivoXML") val extrairArquivo: ExtrairArquivo,
    private @Value("\${extracao.xml.diretorio}") val diretorio: String
) {
    fun extrairXML(xmlVO: XmlVO) {

        val xmls = xmlRepository.findByTokenqIn(xmlVO.token)
        val arquivos = arrayListOf<Arquivo>()

        xmls?.forEach { xml ->

            arquivos.add(
                Arquivo(
                    nome = xml.getToken(),
                    extensao = ".xml",
                    file = IOUtils.toByteArray(xml.getXml().getAsciiStream())
                )
            )
        }
        extrairArquivo.salvar(diretorio, "extracao_xml", arquivos)
    }
}