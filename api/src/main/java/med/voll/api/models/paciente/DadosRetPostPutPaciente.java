package med.voll.api.models.paciente;

import med.voll.api.models.endereco.Endereco;

public record DadosRetPostPutPaciente(Long id, String nome, String email, String telefone, String cpf, Endereco endereco, Boolean ativo) {
    public DadosRetPostPutPaciente(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getTelefone(), paciente.getCpf(), paciente.getEndereco(), paciente.getAtivo());
    }
}
