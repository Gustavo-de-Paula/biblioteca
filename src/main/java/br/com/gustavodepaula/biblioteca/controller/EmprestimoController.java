package br.com.gustavodepaula.biblioteca.controller;

import br.com.gustavodepaula.biblioteca.controller.form.AtualizaEmprestimoForm;
import br.com.gustavodepaula.biblioteca.controller.form.EmprestimoForm;
import br.com.gustavodepaula.biblioteca.dto.EmprestimoDto;
import br.com.gustavodepaula.biblioteca.model.Emprestimo;
import br.com.gustavodepaula.biblioteca.repository.EmprestimoRepository;
import br.com.gustavodepaula.biblioteca.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/emprestimos")
public class EmprestimoController {
    @Autowired
    private EmprestimoRepository emprestimoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    @Cacheable(value = "listaDeEmprestimos")
    public Page<EmprestimoDto> listarEmprestimos(@PageableDefault(sort = "id")Pageable pageable) {
        Page<Emprestimo> emprestimos = emprestimoRepository.findAll(pageable);
        return EmprestimoDto.converter(emprestimos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmprestimoDto> buscarPorId(@PathVariable Long id) {
        Optional<Emprestimo> emprestimo = emprestimoRepository.findById(id);

        if (emprestimo.isPresent())
            return ResponseEntity.ok(new EmprestimoDto(emprestimo.get()));

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/usuario/id/{usuarioId}")
    public Page<EmprestimoDto> buscarPorIdUsuario(@PathVariable Long usuarioId, @PageableDefault(sort = "id") Pageable pageable) {
        Page<Emprestimo> emprestimos = emprestimoRepository.findByUsuario_Id(usuarioId, pageable);
        return EmprestimoDto.converter(emprestimos);
    }

    @GetMapping("/usuario/nome/{usuarioNome}")
    public Page<EmprestimoDto> buscarPorNomeUsuario(@PathVariable String usuarioNome, @PageableDefault(sort = "id") Pageable pageable) {
        Page<Emprestimo> emprestimos = emprestimoRepository.findByUsuario_Nome(usuarioNome.toUpperCase(), pageable);
        return EmprestimoDto.converter(emprestimos);
    }

    @PostMapping
    @Transactional
    @CacheEvict(value = "listaDeEmprestimos", allEntries = true)
    public ResponseEntity<EmprestimoDto> cadastrarEmprestimo(@RequestBody @Valid EmprestimoForm form, UriComponentsBuilder uriBuilder) {
        Emprestimo emprestimo = form.converter(usuarioRepository);
        emprestimoRepository.save(emprestimo);

        URI uri = uriBuilder.path("/emprestimos/{id}").buildAndExpand(emprestimo.getId()).toUri();
        return ResponseEntity.created(uri).body(new EmprestimoDto(emprestimo));
    }

    @PutMapping("/{id}")
    @Transactional
    @CacheEvict(value = "listaDeEmprestimos", allEntries = true)
    public ResponseEntity<EmprestimoDto> atualizarEmprestimo(@PathVariable Long id, @RequestBody @Valid AtualizaEmprestimoForm form) {
        Optional<Emprestimo> emprestimo = emprestimoRepository.findById(id);

        if (emprestimo.isPresent()){
            form.atualizar(emprestimo.get());
            return ResponseEntity.ok(new EmprestimoDto(emprestimo.get()));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    @CacheEvict(value = "listaDeEmprestimos", allEntries = true)
    public ResponseEntity<?> removerEmprestimo(@PathVariable Long id) {
        Optional<Emprestimo> emprestimo = emprestimoRepository.findById(id);

        if (emprestimo.isPresent()) {
            emprestimoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }
}
