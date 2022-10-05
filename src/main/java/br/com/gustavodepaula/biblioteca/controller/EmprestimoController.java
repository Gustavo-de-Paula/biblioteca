package br.com.gustavodepaula.biblioteca.controller;

import br.com.gustavodepaula.biblioteca.controller.form.AtualizacaoEmprestimoForm;
import br.com.gustavodepaula.biblioteca.controller.form.EmprestimoForm;
import br.com.gustavodepaula.biblioteca.dto.EmprestimoDto;
import br.com.gustavodepaula.biblioteca.model.Emprestimo;
import br.com.gustavodepaula.biblioteca.repository.EmprestimoRepository;
import br.com.gustavodepaula.biblioteca.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/emprestimos")
public class EmprestimoController {
    @Autowired
    private EmprestimoRepository emprestimoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public List<EmprestimoDto> listarEmprestimos() {
        List<Emprestimo> emprestimos = emprestimoRepository.findAll();
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
    public List<EmprestimoDto> buscarPorIdUsuario(@PathVariable Long usuarioId) {
        List<Emprestimo> emprestimos = emprestimoRepository.findByUsuario_Id(usuarioId);
        return EmprestimoDto.converter(emprestimos);
    }

    @GetMapping("/usuario/nome/{usuarioNome}")
    public List<EmprestimoDto> buscarPorNomeUsuario(@PathVariable String usuarioNome) {
        List<Emprestimo> emprestimos = emprestimoRepository.findByUsuario_Nome(usuarioNome.toUpperCase());
        return EmprestimoDto.converter(emprestimos);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<EmprestimoDto> cadastrarEmprestimo(@RequestBody @Valid EmprestimoForm form, UriComponentsBuilder uriBuilder) {
        Emprestimo emprestimo = form.converter(usuarioRepository);
        emprestimoRepository.save(emprestimo);

        URI uri = uriBuilder.path("/emprestimos/{id}").buildAndExpand(emprestimo.getId()).toUri();
        return ResponseEntity.created(uri).body(new EmprestimoDto(emprestimo));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<EmprestimoDto> atualizarEmprestimo(@PathVariable Long id, @RequestBody @Valid AtualizacaoEmprestimoForm form) {
        Optional<Emprestimo> emprestimo = emprestimoRepository.findById(id);

        if (emprestimo.isPresent()){
            form.atualizar(emprestimo.get());
            return ResponseEntity.ok(new EmprestimoDto(emprestimo.get()));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> removerEmprestimo(@PathVariable Long id) {
        Optional<Emprestimo> emprestimo = emprestimoRepository.findById(id);

        if (emprestimo.isPresent()) {
            emprestimoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }
}
