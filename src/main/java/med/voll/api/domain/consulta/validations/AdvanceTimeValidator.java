package med.voll.api.domain.consulta.validations;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosAgendamentos;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class AdvanceTimeValidator implements ValidarAgendamentoDeConsulta {
    public void valid(DadosAgendamentos dados) {
        var dataConsulta = dados.data();
        var now = LocalDateTime.now();
        var differenceInMinutes = Duration.between(now, dataConsulta).toMinutes();

        if (differenceInMinutes < 30) {
            throw new ValidacaoException("Consulta deve ser agendada apenas com 30 minutos de antecedência no mínimo.");
        }
    }
}
