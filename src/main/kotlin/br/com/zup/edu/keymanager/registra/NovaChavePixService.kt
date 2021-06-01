package br.com.zup.edu.keymanager.registra

import ChavePix
import NovaChavePix
import br.com.zup.edu.keymanager.ContasDeClientesNoItauClient
import io.micronaut.validation.Validated
import org.hibernate.annotations.common.util.impl.LoggerFactory
import java.lang.IllegalStateException
import java.lang.RuntimeException
import javax.inject.Inject
import javax.inject.Singleton
import javax.transaction.Transactional
import javax.validation.Valid

@Validated
@Singleton
class NovaChavePixService(
    @Inject val repository: ChavePixRepository,
    @Inject val itauClient: ContasDeClientesNoItauClient
) {

    private val LOGGER = org.slf4j.LoggerFactory.getLogger(this::class.java)


    @Transactional
    fun registra(@Valid novaChavePix: NovaChavePix):ChavePix{
        //1 verifica se a chave já existe no sistema

        if(repository.existsByChave(novaChavePix.chave)){
            throw ChavePixExistenteException("Chave ${novaChavePix.chave} existente")
        }

        //2 busca dados na conta do ERP do Itau
        val response = itauClient.buscaContaPorTipo(novaChavePix.clienteId!!,novaChavePix.tipoDeConta!!.name)
        val conta = response.body()?.toModel()?:throw IllegalStateException("Client não encontrado no Itau")

        //3 Grava no banco de dados

        val chave = novaChavePix.toModel(conta)

        return chave

    }


}