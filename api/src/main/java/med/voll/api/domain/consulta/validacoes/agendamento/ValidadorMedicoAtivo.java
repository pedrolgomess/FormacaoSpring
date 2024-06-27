package med.voll.api.domain.consulta.validacoes.agendamento;

import med.voll.api.domain.consulta.DadosConsulta;
import med.voll.api.domain.consulta.exception.ConsultaException;
import med.voll.api.repositories.MedicoRepository;
import med.voll.api.repositories.ValidadorAgendamentoDeConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoAtivo implements ValidadorAgendamentoDeConsulta {
    @Autowired
    private MedicoRepository medicoRepository;
    @Override
    public void validar(DadosConsulta dadosConsulta) {
        //escolha do médico opcional
        if (dadosConsulta.medicoId() == null){
            return;
        }
        var medicoEstaTivo = medicoRepository.findAtivoId(dadosConsulta.medicoId());

        if (!medicoEstaTivo){
            throw new ConsultaException("Consulta não pode ser agendada com o médico");
        }

    }
}
