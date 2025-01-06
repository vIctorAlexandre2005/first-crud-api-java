package med.voll.api.domain.paciente;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.adress.DataAdress;
import med.voll.api.domain.doctor.Especialidade;

public record DadosDetalhePaciente(
        @NotBlank
        String nome,

        @NotBlank
        @Email
        String email,

        @NotBlank
        String telefone,

        @NotBlank
        String cpf,

        @NotNull
        @Valid
        EnderecoPaciente endereco
) {
}
