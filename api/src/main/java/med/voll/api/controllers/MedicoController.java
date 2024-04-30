package med.voll.api.controllers;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.models.medico.DadosAlteraMedico;
import med.voll.api.models.medico.DadosCadastraisMedico;
import med.voll.api.models.medico.DadosListagemMedico;
import med.voll.api.models.medico.Medico;
import med.voll.api.repositories.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("medicos")
public class MedicoController {
    @Autowired
    private MedicoRepository medicoRepository;

    @PostMapping
    @Transactional
    public void postMedico(@RequestBody @Valid DadosCadastraisMedico dadosCadastroMedico){
        medicoRepository.save(new Medico(dadosCadastroMedico));
    }

    @GetMapping
    public Page<DadosListagemMedico> getMedicos(@PageableDefault(size = 10,sort = {"nome"}) Pageable paginacao) {
        return medicoRepository.findAll(paginacao).map(DadosListagemMedico::new);
    }

    @PutMapping
    @Transactional
    public void putMedico(@RequestBody @Valid DadosAlteraMedico dadosAlteraMedico){
        /*
        busca o médico atual no banco de dados através do ID,
        criar método de atualizar informacoes no objeto Medico.
        recebendo o objeto que DTO com novos valores

        */
        var medico = medicoRepository.getReferenceById(dadosAlteraMedico.id());
        medico.atualizaInfoMedico(dadosAlteraMedico);
    }
}
