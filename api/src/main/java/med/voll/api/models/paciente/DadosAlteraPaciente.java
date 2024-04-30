package med.voll.api.models.paciente;

import jakarta.validation.constraints.NotNull;
import med.voll.api.models.endereco.DadosEndereco;

public record DadosAlteraPaciente(
        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEndereco dadosEndereco) {
}
