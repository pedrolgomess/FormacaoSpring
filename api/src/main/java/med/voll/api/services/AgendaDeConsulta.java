package med.voll.api.services;

import med.voll.api.domain.consulta.Consulta;
import med.voll.api.domain.consulta.DadosConsulta;
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
        var medico = medicoRepository.findById(dadosConsulta.medicoId()).get();
        var paciente = pacienteRepository.findById(dadosConsulta.pacienteId()).get();

        var consulta = new Consulta(null, medico,paciente, dadosConsulta.timestamp());
        consultaRepository.save(consulta);
    }
}
