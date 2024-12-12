package med.voll.api.domain.medico;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.adress.DataAdress;

public record DadosAtualizacaoMedicos(
        @NotNull
        Long id,
        String telefone,
        String email,
        String nome,
        DataAdress endereco
) {
}
