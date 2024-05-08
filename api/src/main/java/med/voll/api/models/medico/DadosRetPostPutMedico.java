package med.voll.api.models.medico;

import med.voll.api.models.endereco.Endereco;
import med.voll.api.models.enums.Especialidade;

public record DadosRetPostPutMedico(
        Long id,
        String nome,
        String email,
        String telefone,
        String crm,
        Especialidade especialidade,
        Endereco endereco,
        Boolean ativo) {

    public DadosRetPostPutMedico(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getTelefone(), medico.getCrm(), medico.getEspecialidade(), medico.getEndereco(), medico.getAtivo());
    }
}
