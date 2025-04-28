package com.seusistema.usuarioapi.repository;

import com.seusistema.usuarioapi.model.Usuario;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UsuarioRepository {
    private List<Usuario> usuarios = new ArrayList<>();

    public List<Usuario> listarTodos() {
        return usuarios;
    }

    public Usuario buscarPorId(int id) {
        return usuarios.stream().filter(u -> u.getId() == id).findFirst().orElse(null);
    }

    public Usuario salvar(Usuario usuario) {
        usuario.setId(usuarios.size() + 1);
        usuarios.add(usuario);
        return usuario;
    }

    public Usuario atualizar(int id, Usuario usuarioAtualizado) {
        Usuario usuario = buscarPorId(id);
        if (usuario != null) {
            usuario.setNome(usuarioAtualizado.getNome());
            usuario.setEmail(usuarioAtualizado.getEmail());
            return usuario;
        }
        return null;
    }

    public boolean deletar(int id) {
        Usuario usuario = buscarPorId(id);
        if (usuario != null) {
            usuarios.remove(usuario);
            return true;
        }
        return false;
    }
}
