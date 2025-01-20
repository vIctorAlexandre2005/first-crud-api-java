package med.voll.api.domain.consulta.validations;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosAgendamentos;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidateAppointmentDateAndTime implements ValidarAgendamentoDeConsulta {

    public void valid(DadosAgendamentos dados) {
        var dataConsulta = dados.data();

        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var beforeOpenClinica = dataConsulta.getHour() < 7;
        var afterClosedClinica = dataConsulta.getHour() > 18;

        if (domingo || beforeOpenClinica || afterClosedClinica) {
            throw new ValidacaoException("Consulta fora do dia ou horário de funcionamento da clínica ");
        }
    }

}
