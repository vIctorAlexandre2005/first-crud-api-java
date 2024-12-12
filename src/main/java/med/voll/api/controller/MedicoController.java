package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.doctor.Medico;
import med.voll.api.doctor.MedicoRepository;
import med.voll.api.doctor.RegisterDataMedical;
import med.voll.api.medico.DadosAtualizacaoMedicos;
import med.voll.api.medico.DadosListagemMedico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public void register(@RequestBody @Valid RegisterDataMedical dataMedical) {
        repository.save(new Medico(dataMedical));
    }

    @GetMapping
    public Page<DadosListagemMedico> listar(Pageable paginacao) {
        return repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico:: new);
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizacaoMedicos dados) {
        var medicoId = repository.getReferenceById(dados.id()); // busca o id recebido nessa rota de api
        medicoId.atualizarInformacoes(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void excluir(@PathVariable Long id) {
        var medicoId = repository.getReferenceById(id); // busca o id recebido nessa rota de api
        medicoId.excluir();
    }
}
