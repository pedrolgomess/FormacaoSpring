package med.voll.api.controllers;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.models.medico.DadosCadastroMedico;
import med.voll.api.models.medico.DadosListagemMedico;
import med.voll.api.models.medico.Medico;
import med.voll.api.repositories.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("medicos")
public class MedicoController {
    @Autowired
    private MedicoRepository medicoRepository;

    @PostMapping
    @Transactional
    public void postMedico(@RequestBody @Valid DadosCadastroMedico dadosCadastroMedico){
        medicoRepository.save(new Medico(dadosCadastroMedico));
    }

    @GetMapping
    public List<DadosListagemMedico> getMedicos() {
        return medicoRepository.findAll().stream().map(DadosListagemMedico::new).toList();
    }
}
