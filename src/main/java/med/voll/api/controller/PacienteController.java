package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.doctor.Medico;
import med.voll.api.domain.doctor.RegisterDataMedical;
import med.voll.api.domain.medico.DadosDetalhamentoMedico;
import med.voll.api.domain.paciente.DadosDetalhePaciente;
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

    @PostMapping
    @Transactional
    public ResponseEntity register(@RequestBody @Valid DadosDetalhePaciente dadosPaciente, UriComponentsBuilder uriBuilder) {
        //var dadosMedico = new Medico(dataMedical);
        // repository.save(dadosMedico);
        // var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(dadosMedico.getId()).toUri();
        // return ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(dadosMedico));

        return ResponseEntity.ok().build();
    }

}
