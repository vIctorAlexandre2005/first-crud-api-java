package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.paciente.DadosDetalhamentoPaciente;
import med.voll.api.domain.paciente.DadosDetalhePaciente;
import med.voll.api.domain.paciente.Paciente;
import med.voll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

}
