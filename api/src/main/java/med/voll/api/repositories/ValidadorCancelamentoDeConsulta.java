package med.voll.api.repositories;

import med.voll.api.domain.consulta.DadosCancela;

public interface ValidadorCancelamentoDeConsulta {
    void validar(DadosCancela dadosCancela);
}
