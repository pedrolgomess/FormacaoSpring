package med.voll.api.models.medico;
/*
    Esses dados que estão chegando na API,
    usaremos o recurso de record (disponível nas últimas versões do Java).
    Este recurso funciona como se fosse uma classe imutável, para deixarmos o código simples.

    Isso para não usarmos uma classe tradicional, pois seria necessário digitarmos os métodos getters e setters,
    criar construtor, e todas as outras verbosidades do Java.

    Esse tipo de classe se encaixa perfeitamente para representar classes DTO,
    já que seu objetivo é apenas representar dados que serão recebidos ou devolvidos pela API,
    sem nenhum tipo de comportamento.

    Link da classe record = https://docs.oracle.com/en/java/javase/16/language/records.html
 */

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.models.endereco.DadosEndereco;
import med.voll.api.models.enums.Especialidade;

public record DadosCadastraisMedico(
        @NotBlank
        String nome,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String telefone,
        @NotBlank
        @Pattern(regexp = "\\d{4,6}")
        String crm,
        @NotNull
        Especialidade especialidade,
        @NotNull
        @Valid
        DadosEndereco dadosEndereco) {
}
