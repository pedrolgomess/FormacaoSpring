package med.voll.api.domain.consulta.validacoes.agendamento;

import med.voll.api.domain.consulta.DadosConsulta;
import med.voll.api.domain.consulta.exception.ConsultaException;
import med.voll.api.repositories.ConsultaRepository;
import med.voll.api.repositories.ValidadorAgendamentoDeConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteSemOutraConsultaNoDia implements ValidadorAgendamentoDeConsulta {
    @Autowired
    private ConsultaRepository consultaRepository;
    @Override
    public void validar(DadosConsulta dadosConsulta) {
        var primeiroHorario = dadosConsulta.data().withHour(7);
        var ultimoHorario = dadosConsulta.data().withHour(18);
        var pacientePossuiOutraConsultaNoDia = consultaRepository.existsByPacienteIdAndDataBetween(dadosConsulta.pacienteId(), primeiroHorario, ultimoHorario);
        if (pacientePossuiOutraConsultaNoDia) {
            throw new ConsultaException("Paciente já possui uma consulta agendada nesse dia");
        }

    }
}
