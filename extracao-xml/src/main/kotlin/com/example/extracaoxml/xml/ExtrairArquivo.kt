package com.example.extracaoxml.xml

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Configuration
import java.io.FileOutputStream
import java.nio.charset.StandardCharsets

import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream

data class Arquivo(val nome: String, val extensao: String, val file: ByteArray)

interface ExtrairArquivo {
    fun salvar(
        diretorio: String,
        zipFileName: String = diretorio.substring(diretorio.lastIndexOf("/")),
        arquivos: ArrayList<Arquivo>
    )
}

@Configuration
@Qualifier("ExtrairArquivoXML")
class ExtrairArquivoXML : ExtrairArquivo {
    override fun salvar(diretorio: String, zipFileName: String, arquivos: ArrayList<Arquivo>) {
        if (arquivos.size <= 0) {
            return
        }
        val fout = FileOutputStream(diretorio + zipFileName + ".zip")
        val zos = ZipOutputStream(fout, StandardCharsets.ISO_8859_1)

        arquivos.forEach { arq ->
            val blob: ByteArray = arq.file
            val entry = ZipEntry(arq.nome + arq.extensao).apply {
                size = blob.size.toLong()
            }
            val a = String(blob, StandardCharsets.ISO_8859_1)
            zos.putNextEntry(entry)
            zos.write(blob)
        }

        zos.closeEntry()
        zos.close()
        fout.close()
    }
}