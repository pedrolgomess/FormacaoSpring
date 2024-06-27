package med.voll.api.domain.consulta.validacoes.agendamento;

import med.voll.api.domain.consulta.DadosConsulta;
import med.voll.api.domain.consulta.exception.ConsultaException;
import med.voll.api.repositories.ValidadorAgendamentoDeConsulta;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
// Poderíamos colocar a anotação @Service para indicar que é um serviço, uma validação. Mas vamos usar a anotação @Component
// que é para componentes genéricos. Porque às vezes temos uma classe que não é nem uma classe de configuração,
// nem um controller e nem uma service. Então podemos colocar o @Component para indicar ao Spring que essa classe é um
// componente genérico e ele carregá-la na inicialização do projeto.
@Component
public class ValidadorHorarioAntecedencia  implements ValidadorAgendamentoDeConsulta {
    @Override
    public void validar(DadosConsulta dados) {
        var dataConsulta = dados.data();
        var agora = LocalDateTime.now();
        var diferencaEmMinutos = Duration.between(agora, dataConsulta).toMinutes();

        if (diferencaEmMinutos < 30) {
            throw new ConsultaException("Consulta deve ser agendada com antecedência mínima de 30 minutos");
        }

    }
}
