package med.voll.api.domain.paciente;

import med.voll.api.domain.endereco.Endereco;

public record DadosRetGtPtPtPaciente(Long id, String nome, String email, String telefone, String cpf, Endereco endereco, Boolean ativo) {
    public DadosRetGtPtPtPaciente(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getTelefone(), paciente.getCpf(), paciente.getEndereco(), paciente.getAtivo());
    }
}
