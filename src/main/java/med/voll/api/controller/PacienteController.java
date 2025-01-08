package med.voll.api.controller;

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
    public Page<DadosListagemPaciente> getPacientes(Pageable pagination) {
        return repository.findAll(pagination).map(DadosListagemPaciente::new);
    }

}
