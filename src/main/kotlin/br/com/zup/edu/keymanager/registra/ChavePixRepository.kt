package br.com.zup.edu.keymanager.registra

import io.micronaut.data.jpa.repository.JpaRepository

interface ChavePixRepository:JpaRepository<ChavePix,Long> {
    fun existsByChave(chave: String?): Boolean

}