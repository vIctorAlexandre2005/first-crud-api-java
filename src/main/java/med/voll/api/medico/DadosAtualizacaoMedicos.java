package med.voll.api.medico;

import jakarta.validation.constraints.NotNull;
import med.voll.api.adress.DataAdress;
import med.voll.api.adress.Endereco;

public record DadosAtualizacaoMedicos(
        @NotNull
        Long id,
        String telefone,
        String email,
        String nome,
        DataAdress endereco
) {
}
