package med.voll.api.models.paciente;

import med.voll.api.models.medico.DadosListagemMedico;

public record DadosListagemPaciente(String nome, String email, String cpf) {

    public DadosListagemPaciente(Paciente paciente){
        this(paciente.getNome(), paciente.getEmail(), paciente.getCpf());
    }
}
