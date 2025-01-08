package med.voll.api.domain.paciente;

import jakarta.validation.constraints.NotNull;

public record dtoUpdatePaciente(
        @NotNull
        Long id,
        String nome,
        String email,
        String telefone,
        String cpf,
        EnderecoPaciente endereco
) {

}
