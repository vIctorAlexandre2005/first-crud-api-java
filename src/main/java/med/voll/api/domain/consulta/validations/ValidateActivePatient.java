package med.voll.api.domain.consulta.validations;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosAgendamentos;
import med.voll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidateActivePatient implements ValidarAgendamentoDeConsulta {

    @Autowired
    private PacienteRepository repository;

    public void valid(DadosAgendamentos dados) {
        var activePaciente = repository.findAtivoById(dados.idPaciente());

        if (!activePaciente) {
            throw new ValidacaoException("Paciente não está ativo");
        }
    }
}
