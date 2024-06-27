package med.voll.api.domain.consulta.validacoes.agendamento;

import med.voll.api.domain.consulta.DadosConsulta;
import med.voll.api.domain.consulta.exception.ConsultaException;
import med.voll.api.repositories.ConsultaRepository;
import med.voll.api.repositories.ValidadorAgendamentoDeConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoComOutraConsultaNoMesmoHorario implements ValidadorAgendamentoDeConsulta {
    @Autowired
    private ConsultaRepository consultaRepository;
    @Override
    public void validar(DadosConsulta dadosConsulta) {
        var medicoPossuiOutraConsultaNoMesmoHorario = consultaRepository.existsByMedicoIdAndDataAndMotivoCancelamentoIsNull(dadosConsulta.medicoId(), dadosConsulta.data());
        if (medicoPossuiOutraConsultaNoMesmoHorario) {
            throw new ConsultaException("Médico já possui outra consulta agendada nesse mesmo horário");
        }
    }
}
