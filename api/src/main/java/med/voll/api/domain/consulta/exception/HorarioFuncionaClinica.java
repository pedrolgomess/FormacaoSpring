package med.voll.api.domain.consulta.exception;

import med.voll.api.domain.consulta.DadosConsulta;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class HorarioFuncionaClinica {
    private LocalDate ld;

    public void validar(DadosConsulta dados) {

        var dataConsulta = dados.data();
        var agora = LocalDateTime.now();
        var diferencaEmMinutos = Duration.between(agora, dataConsulta).toMinutes();

        if (diferencaEmMinutos < 30) {
            throw new ConsultaException("Consulta deve ser agendada com antecedência mínima de 30 minutos");
        }
    }
}
