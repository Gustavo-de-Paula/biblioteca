package br.com.gustavodepaula.biblioteca.dto;

import br.com.gustavodepaula.biblioteca.model.Usuario;

import java.util.List;
import java.util.stream.Collectors;

public class UsuarioDto {
    private Long id;
    private String nome;

    public UsuarioDto(Usuario usuario) {
        this.id = usuario.getId();
        this.nome = usuario.getNome();
    }

    public static List<UsuarioDto> converter(List<Usuario> usuarios) {
        return usuarios.stream().map(UsuarioDto::new).collect(Collectors.toList());
    }
}
