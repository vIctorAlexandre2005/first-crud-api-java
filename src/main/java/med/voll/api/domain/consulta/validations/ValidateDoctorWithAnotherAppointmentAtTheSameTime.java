package med.voll.api.domain.consulta.validations;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosAgendamentos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidateDoctorWithAnotherAppointmentAtTheSameTime implements ValidarAgendamentoDeConsulta {

    @Autowired
    private ConsultaRepository repository;

    public void valid(DadosAgendamentos dados) {
       var doctorWithAnotherAppointmentAtTheSameTime = repository.existsByMedicoIdAndData(dados.idMedico(), dados.data());

       if (doctorWithAnotherAppointmentAtTheSameTime) {
           throw new ValidacaoException("Esse médico já tem outra consulta marcada para esse horário. Escolha outro.");
       }
    }
}
