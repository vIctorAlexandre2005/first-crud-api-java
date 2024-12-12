package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.doctor.Medico;
import med.voll.api.domain.doctor.MedicoRepository;
import med.voll.api.domain.doctor.RegisterDataMedical;
import med.voll.api.domain.medico.DadosAtualizacaoMedicos;
import med.voll.api.domain.medico.DadosDetalhamentoMedico;
import med.voll.api.domain.medico.DadosListagemMedico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity register(@RequestBody @Valid RegisterDataMedical dataMedical, UriComponentsBuilder uriBuilder) {
        var dadosMedico = new Medico(dataMedical);
        repository.save(dadosMedico);

        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(dadosMedico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(dadosMedico));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemMedico>> listar(Pageable paginacao) {
        var listando = repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico:: new);
        return ResponseEntity.ok(listando);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoMedicos dados) {
        var medicoId = repository.getReferenceById(dados.id()); // busca o id recebido nessa rota de api
        medicoId.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosDetalhamentoMedico(medicoId));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        var medicoId = repository.getReferenceById(id); // busca o id recebido nessa rota de api
        medicoId.excluir();

        return ResponseEntity.noContent().build(); // retorna o status 204 No Content
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        var medicoId = repository.getReferenceById(id); // busca o id recebido nessa rota de api
        return ResponseEntity.ok(new DadosDetalhamentoMedico(medicoId));
    }
}
