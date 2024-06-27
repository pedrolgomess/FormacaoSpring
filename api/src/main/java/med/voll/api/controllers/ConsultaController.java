package med.voll.api.controllers;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.consulta.DadosCancela;
import med.voll.api.domain.consulta.DadosConsulta;
import med.voll.api.services.AgendaDeConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("consultas")
public class ConsultaController {

    @Autowired
    private AgendaDeConsulta agendaDeConsulta;

    @PostMapping
    @Transactional
    public ResponseEntity agendar(@RequestBody @Valid DadosConsulta dadosConsulta){
        var dadosDetalhamentoConsulta = agendaDeConsulta.agendar(dadosConsulta);
        return ResponseEntity.ok(dadosDetalhamentoConsulta);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity cancelar(@RequestBody @Valid DadosCancela dadosCancela){
        agendaDeConsulta.cancelar(dadosCancela);
        return ResponseEntity.noContent().build();
    }
}
