package med.voll.api.domain.paciente;

import med.voll.api.domain.adress.Endereco;
import med.voll.api.domain.doctor.Especialidade;

public record DadosDetalhamentoPaciente(
        Long id, String nome, String email, String telefone, String cpf, PacienteEndereco endereco
) {
    public DadosDetalhamentoPaciente(Paciente dadosPacientes) {
        this(dadosPacientes.getId(), dadosPacientes.getNome(), dadosPacientes.getEmail(), dadosPacientes.getTelefone(), dadosPacientes.getCpf(), dadosPacientes.getEndereco());
    }
}
