package med.voll.api.domain.consulta.validations;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosAgendamentos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidatePatientWithoutAnotherAppointmentOnTheDay implements ValidarAgendamentoDeConsulta {

    @Autowired
    private ConsultaRepository repository;

    public void valid(DadosAgendamentos dados) {
        var startHour = dados.data().withHour(7);
        var endHour = dados.data().withHour(18);
        var pacienteWithoutAnother = repository.existsByPacienteIdAndDataBetween(dados.idPaciente(), startHour, endHour);

        if (pacienteWithoutAnother) {
            throw new ValidacaoException("Paciente já possui uma consulta agendada nesse dia");
        }
    }
}
