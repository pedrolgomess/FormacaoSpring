package med.voll.api.services;

import med.voll.api.domain.consulta.Consulta;
import med.voll.api.domain.consulta.DadosConsulta;
import med.voll.api.domain.consulta.exception.ConsultaException;
import med.voll.api.domain.medico.Medico;
import med.voll.api.repositories.ConsultaRepository;
import med.voll.api.repositories.MedicoRepository;
import med.voll.api.repositories.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgendaDeConsulta {
    @Autowired
    private ConsultaRepository consultaRepository;
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private PacienteRepository pacienteRepository;

    public void agendar(DadosConsulta dadosConsulta){
        if (dadosConsulta.medicoId() != null && !medicoRepository.existsById(dadosConsulta.medicoId())){
            throw new ConsultaException("Id do médico não existe");
        }
        if (!pacienteRepository.existsById(dadosConsulta.pacienteId())){
            throw new ConsultaException("Id do paciente não existe");
        }

        var medico = escolherMedico(dadosConsulta);
        var paciente = pacienteRepository.findById(dadosConsulta.pacienteId()).get();

        var consulta = new Consulta(null, medico,paciente, dadosConsulta.timestamp());
        consultaRepository.save(consulta);
    }

    private Medico escolherMedico(DadosConsulta dadosConsulta){
        return null;
    }
}
