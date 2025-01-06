package med.voll.api.domain.paciente;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Table(name = "pacientes")
@Entity(name = "Paciente")

@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
public class Paciente {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    private String nome;

    @Getter
    private String email;

    @Getter
    private String telefone;

    @Getter
    private String cpf;

    @Embedded
    private PacienteEndereco endereco;

    public Paciente(DadosDetalhePaciente dadosPaciente) {

    this.nome = dadosPaciente.nome();
    this.email = dadosPaciente.email();
    this.telefone = dadosPaciente.telefone();
    this.cpf = dadosPaciente.cpf();
    this.endereco = new PacienteEndereco(dadosPaciente.endereco());

    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public PacienteEndereco getEndereco() {
        return endereco;
    }
}
