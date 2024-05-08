package med.voll.api.models.paciente;

import med.voll.api.models.endereco.Endereco;

public record DadosRetGtPtPtPaciente(Long id, String nome, String email, String telefone, String cpf, Endereco endereco, Boolean ativo) {
    public DadosRetGtPtPtPaciente(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getTelefone(), paciente.getCpf(), paciente.getEndereco(), paciente.getAtivo());
    }
}
