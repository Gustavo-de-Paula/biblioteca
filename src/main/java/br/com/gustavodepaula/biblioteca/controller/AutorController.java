package br.com.gustavodepaula.biblioteca.controller;

import br.com.gustavodepaula.biblioteca.dto.AutorDto;
import br.com.gustavodepaula.biblioteca.dto.UsuarioDto;
import br.com.gustavodepaula.biblioteca.model.Autor;
import br.com.gustavodepaula.biblioteca.model.Usuario;
import br.com.gustavodepaula.biblioteca.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
