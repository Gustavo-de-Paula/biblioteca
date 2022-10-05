package br.com.gustavodepaula.biblioteca.controller;

import br.com.gustavodepaula.biblioteca.controller.form.AutorForm;
import br.com.gustavodepaula.biblioteca.dto.AutorDto;
import br.com.gustavodepaula.biblioteca.model.Autor;
import br.com.gustavodepaula.biblioteca.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/autores")
public class AutorController {
    @Autowired
    private AutorRepository autorRepository;

    @GetMapping
    public List<AutorDto> listarAutores() {
        List<Autor> autores = autorRepository.findAll();
        return AutorDto.converter(autores);
    }

    @GetMapping("/{id}")
    public AutorDto buscarPorId(@PathVariable Long id) {
        Autor autor = autorRepository.getReferenceById(id);
        return new AutorDto(autor);
    }

    @GetMapping("/nome/{nome}")
    public AutorDto buscarPorNome(@PathVariable String nome) {
        Autor autor = autorRepository.findByNome(nome.toUpperCase());
        return new AutorDto(autor);
    }

    @PostMapping
    public ResponseEntity<AutorDto> cadastrarAutor(@RequestBody @Valid AutorForm form, UriComponentsBuilder uriBuilder) {
        Autor autor = form.converter();
        autorRepository.save(autor);

        URI uri = uriBuilder.path("/autores/{id}").buildAndExpand(autor.getId()).toUri();
        return ResponseEntity.created(uri).body(new AutorDto(autor));
    }
}
