package br.com.zup.edu.keymanager.registra

import NovaChavePix
import br.com.zup.edu.keymanager.*
import br.com.zup.edu.keymanager.extensions.toModel
import io.grpc.stub.StreamObserver
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RegistraChaveEndpoint(@Inject private val service:NovaChavePixService):KeymanagerRegistraGrpcServiceGrpc.KeymanagerRegistraGrpcServiceImplBase() {


    override fun registra(
        request: RegistraChavePixRequest?,
        responseObserver: StreamObserver<RegistraChavePixResponse>?
    ) {

        val novaChave = request.toModel()
        val chaveCriada = service.registra(novaChave)


        responseObserver!!.onNext(RegistraChavePixResponse.newBuilder()
            .setClientId(chaveCriada.clienteId.toString())
            .setPixId(chaveCriada.id.toString())
            .build())
        responseObserver.onCompleted()

    }
}
