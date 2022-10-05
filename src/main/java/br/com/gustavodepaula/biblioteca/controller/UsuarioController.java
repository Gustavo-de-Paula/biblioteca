package br.com.gustavodepaula.biblioteca.controller;

import br.com.gustavodepaula.biblioteca.controller.form.UsuarioForm;
import br.com.gustavodepaula.biblioteca.dto.UsuarioDto;
import br.com.gustavodepaula.biblioteca.model.Usuario;
import br.com.gustavodepaula.biblioteca.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
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
    public UsuarioDto buscarPorId(@PathVariable Long id) {
        Usuario usuario = usuarioRepository.getReferenceById(id);
        return new UsuarioDto(usuario);
    }

    @GetMapping("/nome/{nome}")
    public List<UsuarioDto> buscarPorNome(@PathVariable String nome) {
        List<Usuario> usuarios = usuarioRepository.findByNome(nome.toUpperCase());
        return UsuarioDto.converter(usuarios);
    }

    @PostMapping
    public ResponseEntity<UsuarioDto> cadastrarUsuario(@RequestBody @Valid UsuarioForm form, UriComponentsBuilder uriBuilder) {
        Usuario usuario = form.converter();
        usuarioRepository.save(usuario);

        URI uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(new UsuarioDto(usuario));
    }
}
