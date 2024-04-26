package med.voll.api.controllers;

import jakarta.transaction.Transactional;
import med.voll.api.models.medico.DadosListagemMedico;
import med.voll.api.models.paciente.DadosCadastraisPaciente;
import med.voll.api.models.paciente.DadosListagemPaciente;
import med.voll.api.models.paciente.Paciente;
import med.voll.api.repositories.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("pacientes")
public class PacienteController {
    @Autowired
    private PacienteRepository pacienteRepository;

    @PostMapping
    @Transactional
    public void postPaciente(@RequestBody DadosCadastraisPaciente dadosCadastraisPaciente){
        pacienteRepository.save(new Paciente(dadosCadastraisPaciente));
    }

    @GetMapping
    public Page<DadosListagemPaciente> getPacientes(@PageableDefault(size = 10,sort = {"nome"}) Pageable paginacao){
        return pacienteRepository.findAll(paginacao).map(DadosListagemPaciente::new);
    }
}
