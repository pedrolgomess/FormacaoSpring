package med.voll.api.domain.consulta;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.enums.Especialidade;

import java.sql.Timestamp;

public record DadosConsulta(
        Long medicoId,
        @NotNull
        Long pacienteId,

        @NotNull
        @Future
        Timestamp timestamp,

        Especialidade especialidade) {
}
