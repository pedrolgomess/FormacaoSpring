package med.voll.api.services;

import med.voll.api.domain.consulta.Consulta;
import med.voll.api.domain.consulta.DadosCancela;
import med.voll.api.domain.consulta.DadosConsulta;
import med.voll.api.domain.consulta.DadosDetalhamentoConsulta;
import med.voll.api.domain.consulta.exception.ConsultaException;
import med.voll.api.domain.medico.Medico;
import med.voll.api.repositories.ConsultaRepository;
import med.voll.api.repositories.MedicoRepository;
import med.voll.api.repositories.PacienteRepository;
import med.voll.api.repositories.ValidadorAgendamentoDeConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaDeConsulta {
    @Autowired
    private ConsultaRepository consultaRepository;
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private List<ValidadorAgendamentoDeConsulta> validadores;

    public DadosDetalhamentoConsulta agendar(DadosConsulta dadosConsulta){
        if (dadosConsulta.medicoId() != null && !medicoRepository.existsById(dadosConsulta.medicoId())){
            throw new ConsultaException("Id do médico não existe");
        }
        if (!pacienteRepository.existsById(dadosConsulta.pacienteId())){
            throw new ConsultaException("Id do paciente não existe");
        }
        validadores.forEach(v -> v.validar(dadosConsulta));

        var paciente = pacienteRepository.getReferenceById(dadosConsulta.pacienteId());
        var medico = escolherMedico(dadosConsulta);
        if (medico == null){
            throw new ConsultaException("Não existe médico disponível nesta data");
        }
        var consulta = new Consulta(null, medico,paciente, dadosConsulta.data(), null);
        consultaRepository.save(consulta);

        return new DadosDetalhamentoConsulta(consulta);
    }

    private Medico escolherMedico(DadosConsulta dadosConsulta){
        if (dadosConsulta.medicoId() != null) {
            return medicoRepository.getReferenceById(dadosConsulta.medicoId());
        }

        if (dadosConsulta.especialidade() == null) {
            throw new ConsultaException("Especialidade é obrigatória quando médico não for escolhido!");
        }
        return medicoRepository.escolherMedicoAleatorioLivreNaData(dadosConsulta.especialidade(), dadosConsulta.data());
    }

    public void cancelar(DadosCancela dadosCancela){
        if (!consultaRepository.existsById(dadosCancela.idConsulta())){
            throw new ConsultaException("Id da consulta informado não existe!");
        }

        var consulta = consultaRepository.getReferenceById(dadosCancela.idConsulta());

        consulta.cancelar(dadosCancela.motivoCancelamento());
    }
}
