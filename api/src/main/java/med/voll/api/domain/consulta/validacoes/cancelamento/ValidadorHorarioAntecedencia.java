package med.voll.api.domain.consulta.validacoes.cancelamento;

import med.voll.api.domain.consulta.DadosCancela;
import med.voll.api.domain.consulta.exception.ConsultaException;
import med.voll.api.repositories.ConsultaRepository;
import med.voll.api.repositories.ValidadorCancelamentoDeConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
@Component("ValidadorHorarioAntecedenciaCancelamento")
public class ValidadorHorarioAntecedencia implements ValidadorCancelamentoDeConsulta {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Override
    public void validar(DadosCancela dadosCancela) {
        var consulta = consultaRepository.getReferenceById(dadosCancela.idConsulta());
        var agora = LocalDateTime.now();
        var diferencaEmHoras = Duration.between(agora, consulta.getData()).toHours();

        if (diferencaEmHoras < 24) {
            throw new ConsultaException("Consulta somente pode ser cancelada com antecedência mínima de 24h!");
        }
    }
}
