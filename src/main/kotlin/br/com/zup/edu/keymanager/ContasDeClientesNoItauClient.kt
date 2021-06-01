package br.com.zup.edu.keymanager

import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.QueryValue
import io.micronaut.http.client.annotation.Client
import java.net.http.HttpResponse


@Client("\${itau.contas.url}")
interface ContasDeClientesNoItauClient {

    @Get("/api/v1/clientes/{clientesId}/contas{?tipo}")
    fun buscaContaPorTipo(@PathVariable clientesId:String,@QueryValue tipo: String):HttpResponse<DadosDaContaResponse>

}