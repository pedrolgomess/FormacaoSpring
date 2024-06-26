package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.consulta.DadosConsulta;
import med.voll.api.domain.consulta.exception.ConsultaException;
import med.voll.api.repositories.ValidadorAgendamentoDeConsulta;

import java.time.Duration;
import java.time.LocalDateTime;

public class ValidadorHorarioAntecedencia  implements ValidadorAgendamentoDeConsulta {
    @Override
    public void validar(DadosConsulta dados) {
        var dataConsulta = dados.localDateTime();
        var agora = LocalDateTime.now();
        var diferencaEmMinutos = Duration.between(agora, dataConsulta).toMinutes();

        if (diferencaEmMinutos < 30) {
            throw new ConsultaException("Consulta deve ser agendada com antecedência mínima de 30 minutos");
        }

    }
}
