package med.voll.api.models.paciente;

import med.voll.api.models.medico.DadosListagemMedico;

public record DadosListagemPaciente(Long id, String nome, String email, String cpf, Boolean ativo) {

    public DadosListagemPaciente(Paciente paciente){
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getCpf(), paciente.getAtivo());
    }
}
