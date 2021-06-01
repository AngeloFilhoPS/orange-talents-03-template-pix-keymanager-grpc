package br.com.zup.edu.pix.registra


import br.com.zup.edu.keymanager.ChaveTipo
import br.com.zup.edu.keymanager.RegistraChavePixRequest
import br.com.zup.edu.keymanager.TipoConta

fun RegistraChavePixRequest.toModel() : NovaChavePix {
    return NovaChavePix( // 1
        clienteId = clientId,
        tipo = when (chaveTipo) {
            ChaveTipo.DESCONHECIDA_CHAVE -> null
            else -> ChaveTipo.valueOf(chaveTipo.name) // 1
        },
        chave = chave,
        tipoDeConta = when (conta) {
            TipoConta.DESCONHECIDA_CONTA -> null
            else -> TipoConta.valueOf(conta.name) // 1
        }
    )
}
