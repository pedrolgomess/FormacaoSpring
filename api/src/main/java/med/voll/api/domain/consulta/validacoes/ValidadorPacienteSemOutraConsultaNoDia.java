package med.voll.api.domain.consulta.validacoes;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consulta.DadosConsulta;
import med.voll.api.repositories.ConsultaRepository;
import med.voll.api.repositories.PacienteRepository;
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
            throw new ValidationException("Paciente já possui uma consulta agendada nesse dia");
        }

    }
}
