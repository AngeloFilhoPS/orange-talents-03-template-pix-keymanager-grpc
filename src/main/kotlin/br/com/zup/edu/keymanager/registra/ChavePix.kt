import br.com.zup.edu.keymanager.ChaveTipo
import br.com.zup.edu.keymanager.ContaAssociada
import br.com.zup.edu.keymanager.TipoConta
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*
import javax.validation.Valid
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Entity
@Table(uniqueConstraints = [UniqueConstraint(
    name = "uk_chave_pix",
    columnNames = ["chave"]
)])
//Ainda nao entendo porque o IDE fica cobrando isso Class 'ChavePix' should have [public, protected] no-arg constructor
class ChavePix(
    @field:NotNull
    @Column(nullable = false)
    val clienteId: UUID,

    @field:NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val tipo: ChaveTipo,

    @field:NotBlank
    @Column(unique = true, nullable = false)
    var chave: String,

    @field:NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val tipoDeConta: TipoConta,

    @field:Valid
    @Embedded
    val conta: ContaAssociada
) {
    @Id
    @GeneratedValue
    val id: UUID? = null

    @Column(nullable = false)
    val criadaEm: LocalDateTime = LocalDateTime.now()

    override fun toString(): String {
        return "ChavePix(clienteId=$clienteId, tipo=$tipo, chave='$chave', tipoDeConta=$tipoDeConta, conta=$conta, id=$id, criadaEm=$criadaEm)"
    }

    /**
     * Virificacao para se tal chave é de tal client
     */

    fun pertenceAo(clienteId: UUID) = this.clienteId.equals(clienteId)

    /**
     * Verifica se é aleatória
     */
    fun isAleatoria(): Boolean {
        return tipo == ChaveTipo.ALEATORIA
    }

    /**
     * Atualiza a valor da chave. Somente chave do tipo ALEATORIA pode
     * ser alterado.
     */
    fun atualiza(chave: String): Boolean {
        if (isAleatoria()) {
            this.chave = chave
            return true
        }
        return false
    }

}