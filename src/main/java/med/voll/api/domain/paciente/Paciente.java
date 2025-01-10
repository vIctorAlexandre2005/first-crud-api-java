package med.voll.api.domain.paciente;

import jakarta.persistence.*;
import jakarta.validation.Valid;
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

    private boolean active;

    @Embedded
    private PacienteEndereco endereco;

    public Paciente(DadosDetalhePaciente dadosPaciente) {
    this.active = true;
    this.nome = dadosPaciente.nome();
    this.email = dadosPaciente.email();
    this.telefone = dadosPaciente.telefone();
    this.cpf = dadosPaciente.cpf();
    this.endereco = new PacienteEndereco(dadosPaciente.endereco());

    }

    public Paciente() {}

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

    public void updateInfoPacientes(@Valid dtoUpdatePaciente updatePaciente) {

        if (updatePaciente.nome() != null) {
            this.nome = updatePaciente.nome();
        }

        if (updatePaciente.email() != null) {
            this.email = updatePaciente.email();
        }

        if (updatePaciente.telefone() != null) {
            this.telefone = updatePaciente.telefone();
        }

        if (updatePaciente.cpf() != null) {
            this.cpf = updatePaciente.cpf();
        }

        if (updatePaciente.endereco() != null) {
            this.endereco.updateEnderecoPaciente(updatePaciente.endereco());
        }

    }

    public void delete() {
        this.active = false;
    }
}
