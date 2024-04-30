package med.voll.api.models.medico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.models.endereco.Endereco;
import med.voll.api.models.enums.Especialidade;

/*
    DTO É DIFERENTE DE JPA
    DTO <Data Transfer Object> Sendo o Record
    JPA <Java Persistence Api> Sendo essa classe atual

    Vamos importar a biblioteca Lombok, para gerar os códigos Java que faltam automaticamente.
    Adicionaremos @Getter, @NoArgsConstructor, @AllArgsConstructor, @EqualsAndHashCode(of = "id")
 */

@Table(name = "medicos")
@Entity(name = "Medico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;
    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;
    @Embedded
    private Endereco endereco;

    public Medico(DadosCadastraisMedico dadosCadastroMedico) {
        this.nome = dadosCadastroMedico.nome();
        this.email = dadosCadastroMedico.email();
        this.telefone = dadosCadastroMedico.telefone();
        this.crm = dadosCadastroMedico.crm();
        this.especialidade = dadosCadastroMedico.especialidade();
        this.endereco = new Endereco(dadosCadastroMedico.dadosEndereco());
    }

    public void atualizaInfoMedico(DadosAlteraMedico dadosAlteraMedico) {
        if (dadosAlteraMedico.nome() != null) {
            this.nome = dadosAlteraMedico.nome();
        }
        if (dadosAlteraMedico.telefone() != null) {
            this.telefone = dadosAlteraMedico.telefone();
        }
        if (dadosAlteraMedico.dadosEndereco() != null){
            this.endereco.atualizaInfo(dadosAlteraMedico.dadosEndereco());
        }
    }
}
