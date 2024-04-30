package med.voll.api.models.medico;


import jakarta.validation.constraints.NotNull;
import med.voll.api.models.endereco.DadosEndereco;

public record DadosAlteraMedico(
        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco) {
}
