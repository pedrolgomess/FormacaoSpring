package med.voll.api.models.medico;

import med.voll.api.models.enums.Especialidade;

public record DadosListagemMedico(Long id, String nome, String email, String crm, Especialidade especialidade, Boolean ativo) {

    public DadosListagemMedico(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade(),medico.getAtivo());
    }

}
