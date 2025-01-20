package med.voll.api.domain.paciente;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    Page<Paciente> findByActiveTrue(Pageable pagination);

    @Query("""
            select p.active from Paciente p where p.id = :id
            """)
    Boolean findAtivoById(Long id);
}
