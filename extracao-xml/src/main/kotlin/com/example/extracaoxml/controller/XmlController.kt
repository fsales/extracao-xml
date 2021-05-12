package com.example.extracaoxml.controller

import com.example.extracaoxml.service.XmlService
import com.example.extracaoxml.vo.XmlVO
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/xml")
class XmlController(val xmlService: XmlService) {

    @PostMapping("/extrair-xml")
    fun extrairXml(@RequestBody xmlVo: XmlVO) {
        xmlService.extrairXML(xmlVo)
    }
}