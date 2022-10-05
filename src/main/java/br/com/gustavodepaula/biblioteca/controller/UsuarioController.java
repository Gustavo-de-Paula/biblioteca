package br.com.gustavodepaula.biblioteca.controller;

import br.com.gustavodepaula.biblioteca.dto.UsuarioDto;
import br.com.gustavodepaula.biblioteca.model.Usuario;
import br.com.gustavodepaula.biblioteca.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public List<UsuarioDto> listarUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return UsuarioDto.converter(usuarios);
    }

    @GetMapping("/{id}")
    public UsuarioDto listarPorId(@PathVariable Long id) {
        Usuario usuario = usuarioRepository.getReferenceById(id);
        return new UsuarioDto(usuario);
    }

    @GetMapping("/nome/{nome}")
    public List<UsuarioDto> listarPorNome(@PathVariable String nome) {
        if (nome == null) {
            return listarUsuarios();
        } else {
            List<Usuario> usuarios = usuarioRepository.findByNome(nome.toUpperCase());
            return UsuarioDto.converter(usuarios);
        }
    }
}
