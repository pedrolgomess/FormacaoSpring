package med.voll.api.controllers;

import jakarta.transaction.Transactional;
import med.voll.api.models.paciente.DadosAlteraPaciente;
import med.voll.api.models.paciente.DadosCadastraisPaciente;
import med.voll.api.models.paciente.DadosListagemPaciente;
import med.voll.api.models.paciente.Paciente;
import med.voll.api.repositories.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping
    @Transactional
    public void putPaciente(@RequestBody DadosAlteraPaciente dadosAlteraPaciente){
        /*
        busca o médico atual no banco de dados através do ID,
        criar método de atualizar informacoes no objeto Medico.
        recebendo o objeto que DTO com novos valores
        */
        var paciente = pacienteRepository.getReferenceById(dadosAlteraPaciente.id());
        paciente.atualizaInfoPaciente(dadosAlteraPaciente);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deletePaciente(@PathVariable Long id){
        var paciente = pacienteRepository.getReferenceById(id);
        paciente.desativar();
    }
}
