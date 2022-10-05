package br.com.gustavodepaula.biblioteca.controller;

import br.com.gustavodepaula.biblioteca.controller.form.LivroForm;
import br.com.gustavodepaula.biblioteca.dto.LivroDto;
import br.com.gustavodepaula.biblioteca.model.Livro;
import br.com.gustavodepaula.biblioteca.repository.AutorRepository;
import br.com.gustavodepaula.biblioteca.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

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
    public LivroDto listarPorId(@PathVariable Long id) {
        Livro livro = livroRepository.getReferenceById(id);
        return new LivroDto(livro);
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
}
