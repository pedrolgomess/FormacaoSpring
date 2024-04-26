package med.voll.api.models.paciente;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import med.voll.api.models.endereco.DadosEndereco;

public record DadosCadastraisPaciente(
        @NotBlank
        String nome,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String telefone,
        @NotBlank
        @Pattern(regexp = "\\d{15}")
        String cpf,
        @NotBlank
        @Valid
        DadosEndereco dadosEndereco) {
}
