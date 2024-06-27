package med.voll.api.domain.consulta.validacoes;


import jakarta.validation.ValidationException;
import med.voll.api.domain.consulta.DadosConsulta;
import med.voll.api.domain.consulta.exception.ConsultaException;
import med.voll.api.repositories.PacienteRepository;
import med.voll.api.repositories.ValidadorAgendamentoDeConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteAtivo implements ValidadorAgendamentoDeConsulta {
    @Autowired
    private PacienteRepository pacienteRepository;

    @Override
    public void validar(DadosConsulta dadosConsulta) {

        var pacienteEstaAtivo = pacienteRepository.findAtivoById(dadosConsulta.pacienteId());

        if (!pacienteEstaAtivo) {
            throw new ConsultaException("Consulta não pode ser agendada com paciente excluído");
        }
    }
}
