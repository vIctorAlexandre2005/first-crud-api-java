package med.voll.api.domain.paciente;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Embeddable
@Getter
@AllArgsConstructor
public class PacienteEndereco {
    private String logradouro;
    private String bairro;
    private String cep;
    private String numero;
    private String complemento;
    private String cidade;
    private String uf;

    public PacienteEndereco() {}

    public PacienteEndereco(EnderecoPaciente endereco) {
        this.logradouro = endereco.logradouro();
        this.bairro = endereco.bairro();
        this.cep = endereco.cep();
        this.numero = endereco.numero();
        this.complemento = endereco.complemento();
        this.cidade = endereco.cidade();
        this.uf = endereco.uf();
    }

    public String getLogradouro() {
        return logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getNumero() {
        return numero;
    }

    public String getUf() {
        return uf;
    }

    public String getCep() {
        return cep;
    }

    public void updateEnderecoPaciente(EnderecoPaciente endereco) {

        if (endereco.logradouro() != null) {
            this.logradouro = endereco.logradouro();
        }

        if (endereco.bairro() != null) {
            this.bairro = endereco.bairro();
        }

        if (endereco.cidade() != null) {
            this.cidade = endereco.cidade();
        }

        if (endereco.complemento() != null) {
            this.complemento = endereco.complemento();
        }

        if (endereco.numero() != null) {
            this.numero = endereco.numero();
        }

        if (endereco.uf() != null) {
            this.uf = endereco.uf();
        }

        if (endereco.cep() != null) {
            this.cep = endereco.cep();
        }
    }
}
