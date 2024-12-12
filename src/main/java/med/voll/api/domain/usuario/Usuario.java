package med.voll.api.domain.usuario;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Table(name = "usuarios")
@Entity(name = "Usuario")

@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter

public class Usuario {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String login;
    private String password;

}
