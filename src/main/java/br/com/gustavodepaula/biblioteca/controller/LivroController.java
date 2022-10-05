package br.com.gustavodepaula.biblioteca.controller;

import br.com.gustavodepaula.biblioteca.dto.LivroDto;
import br.com.gustavodepaula.biblioteca.model.Livro;
import br.com.gustavodepaula.biblioteca.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LivroController {
    @Autowired
    private LivroRepository livroRepository;

    @RequestMapping("/livros")
    public List<LivroDto> listarLivros(){
        List<Livro> livros = livroRepository.findAll();
        return LivroDto.converter(livros);
    }
}
