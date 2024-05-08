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
    private Boolean ativo;

    public Paciente(DadosCadastraisPaciente dadosCadastraisPaciente) {
        this.nome = dadosCadastraisPaciente.nome();
        this.email = dadosCadastraisPaciente.email();
        this.telefone = dadosCadastraisPaciente.telefone();
        this.cpf = dadosCadastraisPaciente.cpf();
        this.endereco = new Endereco(dadosCadastraisPaciente.dadosEndereco());
        this.ativo = true;
    }

    public void atualizaInfoPaciente(DadosAlteraPaciente dadosAlteraPaciente) {
        if (dadosAlteraPaciente.nome()!= null){
            this.nome = dadosAlteraPaciente.nome();
        }
        if (dadosAlteraPaciente.telefone()!= null){
            this.telefone = dadosAlteraPaciente.telefone();
        }
        if (dadosAlteraPaciente.dadosEndereco()!= null){
            this.endereco.atualizaInfo(dadosAlteraPaciente.dadosEndereco());
        }
    }

    public void desativar() {
        this.ativo = false;
    }
}
