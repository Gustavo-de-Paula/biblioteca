package br.com.gustavodepaula.biblioteca.controller;

import br.com.gustavodepaula.biblioteca.controller.form.AtualizaEmprestimoForm;
import br.com.gustavodepaula.biblioteca.controller.form.AutorForm;
import br.com.gustavodepaula.biblioteca.dto.AutorDto;
import br.com.gustavodepaula.biblioteca.dto.EmprestimoDto;
import br.com.gustavodepaula.biblioteca.model.Autor;
import br.com.gustavodepaula.biblioteca.model.Emprestimo;
import br.com.gustavodepaula.biblioteca.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
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
@RequestMapping("/autores")
public class AutorController {
    @Autowired
    private AutorRepository autorRepository;

    @GetMapping
    public Page<AutorDto> listarAutores(@PageableDefault(sort = "id")Pageable pageable) {
        Page<Autor> autores = autorRepository.findAll(pageable);
        return AutorDto.converter(autores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AutorDto> buscarPorId(@PathVariable Long id) {
        Optional<Autor> autor = autorRepository.findById(id);

        if (autor.isPresent())
            return ResponseEntity.ok(new AutorDto(autor.get()));

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<AutorDto> buscarPorNome(@PathVariable String nome) {
        Optional<Autor> autor = autorRepository.findByNome(nome.toUpperCase());

        if (autor.isPresent())
            return ResponseEntity.ok(new AutorDto(autor.get()));

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @Transactional
    public ResponseEntity<AutorDto> cadastrarAutor(@RequestBody @Valid AutorForm form, UriComponentsBuilder uriBuilder) {
        Autor autor = form.converter();
        autorRepository.save(autor);

        URI uri = uriBuilder.path("/autores/{id}").buildAndExpand(autor.getId()).toUri();
        return ResponseEntity.created(uri).body(new AutorDto(autor));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<AutorDto> atualizarAutor(@PathVariable Long id, @RequestBody @Valid AutorForm form) {
        Optional<Autor> autor = autorRepository.findById(id);

        if (autor.isPresent()){
            form.atualizar(autor.get());
            return ResponseEntity.ok(new AutorDto(autor.get()));
        }

        return ResponseEntity.notFound().build();
    }
}
