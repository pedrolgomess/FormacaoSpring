package med.voll.api.controllers;

import med.voll.api.models.paciente.DadosCadastraisPaciente;
import med.voll.api.models.paciente.Paciente;
import med.voll.api.repositories.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("pacientes")
public class PacienteController {
    @Autowired
    private PacienteRepository pacienteRepository;
    @PostMapping
    public void postPaciente(@RequestBody DadosCadastraisPaciente dadosCadastraisPaciente){
        pacienteRepository.save(new Paciente(dadosCadastraisPaciente));
    }
}
