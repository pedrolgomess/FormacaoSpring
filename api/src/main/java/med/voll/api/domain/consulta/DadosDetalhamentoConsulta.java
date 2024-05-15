package med.voll.api.domain.consulta;

import java.sql.Timestamp;

public record DadosDetalhamentoConsulta(Long id, Long medicoId, Long pacienteId, Timestamp timestamp) {
}
