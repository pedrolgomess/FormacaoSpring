package med.voll.api.repositories;

import med.voll.api.domain.consulta.DadosConsulta;

public interface ValidadorAgendamentoDeConsulta {
    void validar(DadosConsulta dados);
}
