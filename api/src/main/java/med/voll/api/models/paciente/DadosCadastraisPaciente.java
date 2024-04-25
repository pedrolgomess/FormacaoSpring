package med.voll.api.models.paciente;

import med.voll.api.models.endereco.DadosEndereco;

public record DadosCadastraisPaciente(String nome, String email, String telefone, String cpf, DadosEndereco dadosEndereco) {
}
