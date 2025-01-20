package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.voll.api.domain.paciente.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/paciente")
@SecurityRequirement(name = "bearer-key")
public class PacienteController {

    @Autowired
    private PacienteRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity register(@RequestBody @Valid DadosDetalhePaciente dadosPaciente, UriComponentsBuilder uriBuilder) {
        var dadosPacientes = new Paciente(dadosPaciente);
        repository.save(dadosPacientes);
        var uri = uriBuilder.path("/paciente/{id}").buildAndExpand(dadosPacientes.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoPaciente(dadosPacientes));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemPaciente>> getPacientes(Pageable pagination) {
        var responseList = repository.findByActiveTrue(pagination).map(DadosListagemPaciente::new);
        return ResponseEntity.ok(responseList);
    }

    @PutMapping
    @Transactional
    public ResponseEntity updateDataPacientes(@Valid @RequestBody dtoUpdatePaciente updatePaciente) {
        var pacienteId = repository.getReferenceById(updatePaciente.id());
        pacienteId.updateInfoPacientes(updatePaciente);

        return ResponseEntity.ok(new DadosDetalhamentoPaciente(pacienteId));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteDataPacientes(@PathVariable Long id) {
        var pacienteId = repository.getReferenceById(id);
        pacienteId.delete();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detailsPaciente(@PathVariable Long id) {
        var pacienteId = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoPaciente(pacienteId));
    }
}
