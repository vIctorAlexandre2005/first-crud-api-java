package med.voll.api.domain.consulta;

import lombok.AllArgsConstructor;
import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.doctor.Medico;
import med.voll.api.domain.doctor.MedicoRepository;
import med.voll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgendaDeConsultas {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;


    public void agendar(DadosAgendamentos dadosAgendamentos) {

        if (!pacienteRepository.existsById(dadosAgendamentos.idPaciente())) {
            throw new ValidacaoException("Id do paciente informado não existe");
        }

        if (dadosAgendamentos.idMedico() != null && !medicoRepository.existsById(dadosAgendamentos.idMedico())) {
            throw new ValidacaoException("Id do médico informado não existe");
        }

        var paciente = pacienteRepository.findById(dadosAgendamentos.idPaciente()).get();
        var medico = chooseRandomMedico(dadosAgendamentos);
        var consulta = new Consulta(null, medico, paciente, dadosAgendamentos.data());

        consultaRepository.save(consulta);
    }

    private Medico chooseRandomMedico(DadosAgendamentos dadosAgendamentos) {
        // Se o id de medico existir, carregar no banco de dados o medico pelo id fornecido
        if (dadosAgendamentos.idMedico() != null) {
            return medicoRepository.getReferenceById(dadosAgendamentos.idMedico());
        }

        //Quando não enviado o id de medico, é necessário enviar a Especialidade. Caso não enviado tambem, lançar exception
        if (dadosAgendamentos.especialidade() == null) {
            throw new ValidacaoException("Especialidade é obrigatória quando médico não for escolhido");
        }

        // Escolher medico aleatorio
        return medicoRepository.chooseRandomDateMedico(dadosAgendamentos.especialidade(), dadosAgendamentos.data());
    }

}
