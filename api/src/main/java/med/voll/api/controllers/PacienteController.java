package med.voll.api.controllers;

import med.voll.api.models.paciente.DadosCadastraisPaciente;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("pacientes")
public class PacienteController {
    @PostMapping
    public void postPaciente(@RequestBody DadosCadastraisPaciente dadosCadastraisPaciente){
        System.out.println(dadosCadastraisPaciente);
    }
}
