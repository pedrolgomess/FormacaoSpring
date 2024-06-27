package med.voll.api.domain.consulta;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.enums.Especialidade;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public record DadosConsulta(

        Long medicoId,
        @NotNull
        Long pacienteId,

        @NotNull
        @Future
        LocalDateTime data,

        Especialidade especialidade) {
}
