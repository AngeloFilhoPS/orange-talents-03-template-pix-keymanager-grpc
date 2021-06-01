package br.com.zup.edu.pix.registra


import br.com.zup.edu.keymanager.KeymanagerRegistraGrpcServiceGrpc
import br.com.zup.edu.keymanager.RegistraChavePixRequest
import br.com.zup.edu.keymanager.RegistraChavePixResponse
import io.grpc.stub.StreamObserver
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RegistraChaveEndpoint(@Inject private val service: NovaChavePixService,) // 1
    : KeymanagerRegistraGrpcServiceGrpc.KeymanagerRegistraGrpcServiceImplBase() { // 1

    // 8
    override fun registra(
        request: RegistraChavePixRequest, // 1
        responseObserver: StreamObserver<RegistraChavePixResponse> // 1
    ) {

        val novaChave = request.toModel() // 2
        val chaveCriada = service.registra(novaChave) // 1

        responseObserver.onNext(RegistraChavePixResponse.newBuilder() // 1
                                    .setClientId(chaveCriada.clienteId.toString())
                                    .setPixId(chaveCriada.id.toString())
                                    .build())
        responseObserver.onCompleted()
    }

}