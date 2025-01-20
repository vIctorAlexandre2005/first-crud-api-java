package med.voll.api.domain.consulta.validations;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosAgendamentos;
import med.voll.api.domain.doctor.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidateActiveDoctor implements ValidarAgendamentoDeConsulta {

    @Autowired
    private MedicoRepository repository;

    public void valid(DadosAgendamentos dados) {
        if (dados.idMedico() == null) {
            return;
        }

        var doctorIdRepository = repository.findActiveDoctor(dados.idMedico());

        if (!doctorIdRepository) {
            throw new ValidacaoException("Não foi possível encontrar um médico ativo.");
        }
    }
}
