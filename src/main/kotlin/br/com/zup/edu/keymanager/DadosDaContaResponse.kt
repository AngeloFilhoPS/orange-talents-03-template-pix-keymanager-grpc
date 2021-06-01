package br.com.zup.edu.keymanager

data class DadosDaContaResponse(
    val tipo:String,
    val instituica:InstituicaoResponse,
    val agencia: String,
    val numero: String,
    val titular: TitularResponse
){

    fun toModel():ContaAssociada{
        return ContaAssociada(
            instituicao = this.instituica.nome,
            nomeDoTitular = this.titular.nome,
            cpfDoTitular = this.titular.cpf,
            agencia = this.agencia,
            numeroConta = this.numero
        )
    }

}




data class TitularResponse(val nome: String, val cpf: String)
data class InstituicaoResponse(val nome: String, val ispb: String)