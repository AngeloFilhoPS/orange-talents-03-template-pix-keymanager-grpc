package br.com.zup.edu.pix.registra

import br.com.zup.edu.keymanager.ChaveTipo
import br.com.zup.edu.keymanager.TipoConta
import br.com.zup.edu.keymanager.pix.ChavePix
import br.com.zup.edu.keymanager.pix.ContaAssociada
import io.micronaut.core.annotation.Introspected
import java.util.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Introspected
data class NovaChavePix(
    @field:NotBlank
    val clienteId: String?,
    @field:NotNull
    val tipo: ChaveTipo?,
    @field:Size(max = 77)
    val chave: String?,
    @field:NotNull
    val tipoDeConta: TipoConta?
) {

    fun toModel(conta: ContaAssociada): ChavePix {
        return ChavePix(
            clienteId = UUID.fromString(this.clienteId),
            tipo = ChaveTipo.valueOf(this.tipo!!.name),
            chave = if (this.tipo == ChaveTipo.ALEATORIA) UUID.randomUUID().toString() else this.chave!!,
            tipoDeConta = TipoConta.valueOf(this.tipoDeConta!!.name),
            conta = conta
        )
    }

}
