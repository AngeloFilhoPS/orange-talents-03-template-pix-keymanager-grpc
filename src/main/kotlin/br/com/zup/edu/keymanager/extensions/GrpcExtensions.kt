package br.com.zup.edu.keymanager.extensions

import NovaChavePix
import br.com.zup.edu.keymanager.ChaveTipo
import br.com.zup.edu.keymanager.RegistraChavePixRequest
import br.com.zup.edu.keymanager.TipoConta


fun RegistraChavePixRequest?.toModel(): NovaChavePix {
    return NovaChavePix(
        clienteId =  this!!.clientId,
        tipo = when (chaveTipo) {
            ChaveTipo.DESCONHECIDA_CHAVE -> null
            else -> ChaveTipo.valueOf(chaveTipo.name)
        },
        chave = chave,
        tipoDeConta = when(conta){
            TipoConta.UNRECOGNIZED->null
            else -> TipoConta.valueOf(conta.name)
        }
    )
}