package br.com.zup.edu.pix.registra

import br.com.zup.edu.keymanager.pix.ChavePix
import br.com.zup.edu.keymanager.pix.ChavePixExistenteException
import br.com.zup.edu.keymanager.pix.ChavePixRepository
import br.com.zup.edu.keymanager.pix.ContasDeClientesNoItauClient
import io.micronaut.validation.Validated
import org.slf4j.LoggerFactory
import javax.inject.Inject
import javax.inject.Singleton
import javax.transaction.Transactional
import javax.validation.Valid

@Validated
@Singleton
class NovaChavePixService(@Inject val repository: ChavePixRepository, // 1
                          @Inject val itauClient: ContasDeClientesNoItauClient, // 1
                         ) { // 1

    private val LOGGER = LoggerFactory.getLogger(this::class.java)

    @Transactional
    fun registra(@Valid novaChave: NovaChavePix): ChavePix { // 2

        println(novaChave)
        // 1. verifica se chave já existe no sistema
        if (repository.existsByChave(novaChave.chave)) // 1
            throw ChavePixExistenteException("Chave Pix '${novaChave.chave}' existente") // 1

        // 2. busca dados da conta no ERP do ITAU
        val response = itauClient.buscaContaPorTipo(novaChave.clienteId!!, novaChave.tipoDeConta!!.name) // 1
        val conta = response.body()?.toModel() ?: throw IllegalStateException("Cliente não encontrado no Itau") // 1

        // 3. grava no banco de dados
        val chave = novaChave.toModel(conta)
        repository.save(chave)

        return chave
    }

}