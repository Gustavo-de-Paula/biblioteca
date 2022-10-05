package br.com.gustavodepaula.biblioteca.controller;

import br.com.gustavodepaula.biblioteca.dto.LivroDto;
import br.com.gustavodepaula.biblioteca.model.Livro;
import br.com.gustavodepaula.biblioteca.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivroController {
    @Autowired
    private LivroRepository livroRepository;

    @GetMapping
    public List<LivroDto> listarLivros(String autor) {
        if (autor == null) {
            List<Livro> livros = livroRepository.findAll();
            return LivroDto.converter(livros);
        } else {
            List<Livro> livros = livroRepository.findByAutor_Nome(autor.toUpperCase());
            return LivroDto.converter(livros);
        }
    }
}
