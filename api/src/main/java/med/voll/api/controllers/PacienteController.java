package med.voll.api.controllers;

import jakarta.transaction.Transactional;
import med.voll.api.domain.paciente.*;
import med.voll.api.repositories.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("pacientes")
public class PacienteController {
    @Autowired
    private PacienteRepository pacienteRepository;

    @PostMapping
    @Transactional
    public ResponseEntity postPaciente(@RequestBody DadosCadastraisPaciente dadosCadastraisPaciente, UriComponentsBuilder uriBuilder){
        var novoPaciente = new Paciente(dadosCadastraisPaciente);
        pacienteRepository.save(novoPaciente);
        var uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(novoPaciente.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosRetGtPtPtPaciente(novoPaciente));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemPaciente>> getPacientes(@PageableDefault(size = 10,sort = {"nome"}) Pageable paginacao){
        var page = pacienteRepository.findAll(paginacao).map(DadosListagemPaciente::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity putPaciente(@RequestBody DadosAlteraPaciente dadosAlteraPaciente){
        /*
        busca o médico atual no banco de dados através do ID,
        criar método de atualizar informacoes no objeto Medico.
        recebendo o objeto que DTO com novos valores
        */
        var paciente = pacienteRepository.getReferenceById(dadosAlteraPaciente.id());
        paciente.atualizaInfoPaciente(dadosAlteraPaciente);
        return ResponseEntity.ok(new DadosRetGtPtPtPaciente(paciente));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletePaciente(@PathVariable Long id){
        var paciente = pacienteRepository.getReferenceById(id);
        paciente.desativar();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity getPorPaciente(@PathVariable Long id){
        var paciente = pacienteRepository.getReferenceById(id);
        return ResponseEntity.ok(new DadosRetGtPtPtPaciente(paciente));
    }
}
