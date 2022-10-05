package br.com.gustavodepaula.biblioteca.controller;

import br.com.gustavodepaula.biblioteca.controller.form.EmprestimoForm;
import br.com.gustavodepaula.biblioteca.dto.EmprestimoDto;
import br.com.gustavodepaula.biblioteca.model.Emprestimo;
import br.com.gustavodepaula.biblioteca.repository.EmprestimoRepository;
import br.com.gustavodepaula.biblioteca.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

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
    public EmprestimoDto buscarPorId(@PathVariable Long id) {
        Emprestimo emprestimo = emprestimoRepository.getReferenceById(id);
        return new EmprestimoDto(emprestimo);
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
    public ResponseEntity<EmprestimoDto> cadastrarEmprestimo(@RequestBody @Valid EmprestimoForm form, UriComponentsBuilder uriBuilder) {
        Emprestimo emprestimo = form.converter(usuarioRepository);
        emprestimoRepository.save(emprestimo);

        URI uri = uriBuilder.path("/emprestimos/{id}").buildAndExpand(emprestimo.getId()).toUri();
        return ResponseEntity.created(uri).body(new EmprestimoDto(emprestimo));
    }
}
