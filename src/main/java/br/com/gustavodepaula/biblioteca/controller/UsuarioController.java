package br.com.gustavodepaula.biblioteca.controller;

import br.com.gustavodepaula.biblioteca.controller.form.UsuarioForm;
import br.com.gustavodepaula.biblioteca.dto.AutorDto;
import br.com.gustavodepaula.biblioteca.dto.EmprestimoDto;
import br.com.gustavodepaula.biblioteca.dto.UsuarioDto;
import br.com.gustavodepaula.biblioteca.model.Autor;
import br.com.gustavodepaula.biblioteca.model.Usuario;
import br.com.gustavodepaula.biblioteca.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public Page<UsuarioDto> listarUsuarios(@PageableDefault(sort = "id") Pageable pageable) {
        Page<Usuario> usuarios = usuarioRepository.findAll(pageable);
        return UsuarioDto.converter(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDto> buscarPorId(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);

        if (usuario.isPresent())
            return ResponseEntity.ok(new UsuarioDto(usuario.get()));

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<UsuarioDto> buscarPorNome(@PathVariable String nome) {
        Optional<Usuario> usuario = usuarioRepository.findByNome(nome.toUpperCase());

        if (usuario.isPresent())
            return ResponseEntity.ok(new UsuarioDto(usuario.get()));

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<UsuarioDto> cadastrarUsuario(@RequestBody @Valid UsuarioForm form, UriComponentsBuilder uriBuilder) {
        Usuario usuario = form.converter();
        usuarioRepository.save(usuario);

        URI uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(new UsuarioDto(usuario));
    }
}
