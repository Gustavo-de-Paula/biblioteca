package br.com.gustavodepaula.biblioteca.controller;

import br.com.gustavodepaula.biblioteca.controller.form.AtualizaLivroForm;
import br.com.gustavodepaula.biblioteca.controller.form.LivroForm;
import br.com.gustavodepaula.biblioteca.dto.LivroDto;
import br.com.gustavodepaula.biblioteca.model.Livro;
import br.com.gustavodepaula.biblioteca.repository.AutorRepository;
import br.com.gustavodepaula.biblioteca.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/livros")
public class LivroController {
    @Autowired
    private LivroRepository livroRepository;
    @Autowired
    private AutorRepository autorRepository;

    @GetMapping
    public Page<LivroDto> listarLivros(@PageableDefault(sort = "id") Pageable pageable) {
        Page<Livro> livros = livroRepository.findAll(pageable);
        return LivroDto.converter(livros);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LivroDto> buscarPorId(@PathVariable Long id) {
        Optional<Livro> livro = livroRepository.findById(id);

        if (livro.isPresent())
            return ResponseEntity.ok(new LivroDto(livro.get()));

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/nome/{nome}")
    public Page<LivroDto> buscarPorNome(@PathVariable String nome, @PageableDefault(sort = "id") Pageable pageable) {
        Page<Livro> livros = livroRepository.findByNome(nome.toUpperCase(), pageable);
        return LivroDto.converter(livros);
    }

    @GetMapping("/autor/{autor}")
    public Page<LivroDto> listarPorAutor(@PathVariable String autor, @PageableDefault(sort = "id") Pageable pageable) {
        Page<Livro> livros = livroRepository.findByAutor_Nome(autor.toUpperCase(), pageable);
        return LivroDto.converter(livros);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<LivroDto> cadastrarLivro(@RequestBody @Valid LivroForm form, UriComponentsBuilder uriBuilder) {
        Livro livro = form.converter(autorRepository);
        livroRepository.save(livro);

        URI uri = uriBuilder.path("/livros/{id}").buildAndExpand(livro.getId()).toUri();
        return ResponseEntity.created(uri).body(new LivroDto(livro));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<LivroDto> atualizarLivro(@PathVariable Long id, @RequestBody @Valid AtualizaLivroForm form) {
        Optional<Livro> livro = livroRepository.findById(id);

        if (livro.isPresent()){
            form.atualizar(livro.get());
            return ResponseEntity.ok(new LivroDto(livro.get()));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> removerLivro(@PathVariable Long id) {
        Optional<Livro> livro = livroRepository.findById(id);

        if (livro.isPresent()) {
            livroRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }
}
