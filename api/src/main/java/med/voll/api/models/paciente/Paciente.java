package med.voll.api.models.paciente;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.models.endereco.Endereco;
@Table(name = "pacientes")
@Entity(name = "Paciente")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String cpf;
    @Embedded
    private Endereco endereco;

    public Paciente(DadosCadastraisPaciente dadosCadastraisPaciente) {
        this.nome = dadosCadastraisPaciente.nome();
        this.email = dadosCadastraisPaciente.email();
        this.telefone = dadosCadastraisPaciente.telefone();
        this.cpf = dadosCadastraisPaciente.cpf();
        this.endereco = new Endereco(dadosCadastraisPaciente.dadosEndereco());
    }
}
