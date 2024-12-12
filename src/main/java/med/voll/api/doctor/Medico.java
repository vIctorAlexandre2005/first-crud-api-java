package med.voll.api.doctor;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.adress.Endereco;
import med.voll.api.medico.DadosAtualizacaoMedicos;

@Table(name = "medicos")
@Entity(name = "Medico")

@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
public class Medico {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Getter
    private String nome;
    @Getter
    private String email;
    private String telefone;
    @Getter
    private String crm;

    private boolean ativo;

    @Getter
    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Embedded
    private Endereco endereco;

    public Medico(RegisterDataMedical dataMedical) {
        this.ativo = true;
        this.nome = dataMedical.nome();
        this.email = dataMedical.email();
        this.telefone = dataMedical.telefone();
        this.crm = dataMedical.crm();
        this.especialidade = dataMedical.especialidade();
        this.endereco = new Endereco(dataMedical.endereco());
    }

    public Medico() {

    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public String getCrm() {
        return crm;
    }

    public Especialidade getEspecialidade() {
        return especialidade;
    }

    public void atualizarInformacoes(@Valid DadosAtualizacaoMedicos dados) {
       if (dados.nome() != null) {
           this.nome = dados.nome();
       }

       if (dados.telefone() != null) {
           this.telefone = dados.telefone();
       }

       if (dados.email() != null) {
           this.email = dados.email();
       }

       if (dados.endereco() != null) {
           this.endereco.atualizarEndereco(dados.endereco());
       }
    }

    public void excluir() {

        this.ativo = false;

    }
}