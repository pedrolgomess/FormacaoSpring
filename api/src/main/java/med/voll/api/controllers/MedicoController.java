package med.voll.api.controllers;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.models.medico.*;
import med.voll.api.repositories.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder; //classe que gera a URI

@RestController
@RequestMapping("medicos")
public class MedicoController {
    @Autowired
    private MedicoRepository medicoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity postMedico(@RequestBody @Valid DadosCadastraisMedico dadosCadastroMedico, UriComponentsBuilder uriBuilder){
        var novoMedico = new Medico(dadosCadastroMedico);
        medicoRepository.save(novoMedico);
        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(novoMedico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosRetGtPtPtMedico(novoMedico));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemMedico>> getMedicos(@PageableDefault(size = 10,sort = {"nome"}) Pageable paginacao) {
        var page = medicoRepository.findAll(paginacao).map(DadosListagemMedico::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity putMedico(@RequestBody @Valid DadosAlteraMedico dadosAlteraMedico){
        /*
        busca o médico atual no banco de dados através do ID,
        criar método de atualizar informacoes no objeto Medico.
        recebendo o objeto que DTO com novos valores

        */
        var medico = medicoRepository.getReferenceById(dadosAlteraMedico.id());
        medico.atualizaInfoMedico(dadosAlteraMedico);
        return ResponseEntity.ok(new DadosRetGtPtPtMedico(medico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delMedico(@PathVariable Long id){
        var medico = medicoRepository.getReferenceById(id);
        medico.desativar();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity getPorMedico(@PathVariable Long id){
        var medico = medicoRepository.getReferenceById(id);
        return ResponseEntity.ok(new DadosRetGtPtPtMedico(medico));
    }
}
