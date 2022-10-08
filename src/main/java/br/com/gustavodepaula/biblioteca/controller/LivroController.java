package br.com.gustavodepaula.biblioteca.controller;

import br.com.gustavodepaula.biblioteca.controller.form.AtualizaLivroForm;
import br.com.gustavodepaula.biblioteca.controller.form.LivroForm;
import br.com.gustavodepaula.biblioteca.dto.LivroDto;
import br.com.gustavodepaula.biblioteca.model.Livro;
import br.com.gustavodepaula.biblioteca.repository.AutorRepository;
import br.com.gustavodepaula.biblioteca.repository.LivroRepository;
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
@RequestMapping("/livros")
public class LivroController {
    @Autowired
    private LivroRepository livroRepository;
    @Autowired
    private AutorRepository autorRepository;

    @GetMapping
    public List<LivroDto> listarLivros() {
        List<Livro> livros = livroRepository.findAll();
        return LivroDto.converter(livros);
    }

    @GetMapping("/{id}")
    public LivroDto buscarPorId(@PathVariable Long id) {
        Livro livro = livroRepository.getReferenceById(id);
        return new LivroDto(livro);
    }

    @GetMapping("/nome/{nome}")
    public List<LivroDto> buscarPorNome(@PathVariable String nome) {
        List<Livro> livros = livroRepository.findByNome(nome.toUpperCase());
        return LivroDto.converter(livros);
    }

    @GetMapping("/autor/{autor}")
    public List<LivroDto> listarPorAutor(@PathVariable String autor) {
        if (autor == null) {
            return listarLivros();
        } else {
            List<Livro> livros = livroRepository.findByAutor_Nome(autor.toUpperCase());
            return LivroDto.converter(livros);
        }
    }

    @PostMapping
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
