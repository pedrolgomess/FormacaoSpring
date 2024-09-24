package med.voll.api.repositories;

import med.voll.api.domain.consulta.Consulta;
import med.voll.api.domain.endereco.DadosEndereco;
import med.voll.api.domain.enums.Especialidade;
import med.voll.api.domain.medico.DadosCadastraisMedico;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.paciente.DadosCadastraisPaciente;
import med.voll.api.domain.paciente.Paciente;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest //A anotação @DataJpaTest é utilizada para testar uma interface Repository.
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test") //queremos que ele carregue o "application-test.properties". Do contrário, ele não carregará automaticamente.
class MedicoRepositoryTest {
    @Autowired
    private MedicoRepository medicoRepositoryTest;
    @Autowired
    private TestEntityManager entityManager;

    @Test
    @DisplayName("Deveria devolver null quando unico medico cadastrado nao esta disponivel na data")// Anotação responsável para descrever o que espera de retorno
    void escolherMedicoAleatorioLivreNaDataCenario1() {

        // given ou arrange
        var proximaSegundaAsDez = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10,0);
        var medico = cadastrarMedico("Medico", "medico@voll.med", "123456", Especialidade.CARDIOLOGIA);
        var paciente = cadastrarPaciente("Paciente", "paciente@email.com", "00000000000");
        cadastrarConsulta(medico, paciente, proximaSegundaAsDez);

        // when ou act
        var medicoLivreTest = medicoRepositoryTest.escolherMedicoAleatorioLivreNaData(Especialidade.CARDIOLOGIA, proximaSegundaAsDez);

        // then ou assert
        assertThat(medicoLivreTest).isNull();
    }

    @Test
    @DisplayName("Deveria devolver MEDICO QUANDO ESTIVER DISPONIVEL na data")// Anotação responsável para descrever o que espera de retorno
    void escolherMedicoAleatorioLivreNaDataCenario2() {

        // given ou arrange
        var proximaSegundaAsDez = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10,0);
        var medico = cadastrarMedico("Medico", "medico@voll.med", "123456", Especialidade.CARDIOLOGIA);

        // when ou act
        var medicoLivreTest = medicoRepositoryTest.escolherMedicoAleatorioLivreNaData(Especialidade.CARDIOLOGIA, proximaSegundaAsDez);

        // then ou assert
        assertThat(medicoLivreTest).isEqualTo(medico);
    }

    private void cadastrarConsulta(Medico medico, Paciente paciente, LocalDateTime data) {
        entityManager.persist(new Consulta(null, medico, paciente, data, null));
    }

    private Medico cadastrarMedico(String nome, String email, String crm, Especialidade especialidade) {
        var medico = new Medico(dadosMedico(nome, email, crm, especialidade));
        entityManager.persist(medico);
        return medico;
    }

    private Paciente cadastrarPaciente(String nome, String email, String cpf) {
        var paciente = new Paciente(dadosPaciente(nome, email, cpf));
        entityManager.persist(paciente);
        return paciente;
    }

    private DadosCadastraisMedico dadosMedico(String nome, String email, String crm, Especialidade especialidade) {
        return new DadosCadastraisMedico(
                nome,
                email,
                "61999999999",
                crm,
                especialidade,
                dadosEndereco()
        );
    }

    private DadosCadastraisPaciente dadosPaciente(String nome, String email, String cpf) {
        return new DadosCadastraisPaciente(
                nome,
                email,
                "61999999999",
                cpf,
                dadosEndereco()
        );
    }

    private DadosEndereco dadosEndereco() {
        return new DadosEndereco(
                "rua xpto",
                "bairro",
                "00000000",
                "Brasilia",
                "DF",
                "PB",
                "58200000"
        );
    }
}