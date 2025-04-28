package com.seusistema.usuarioapi.controller;

import com.seusistema.usuarioapi.model.Usuario;
import com.seusistema.usuarioapi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    @GetMapping("/")
    public String home() {
        return "<h1>API CRUD de Usuários - Java Spring Boot</h1><p>Use /users para interagir com a API</p>";
    }

    @PostMapping
    public Usuario criarUsuario(@RequestBody Usuario usuario) {
        return repository.salvar(usuario);
    }

    @GetMapping
    public List<Usuario> listarUsuarios() {
        return repository.listarTodos();
    }

    @GetMapping("/{id}")
    public Usuario buscarUsuario(@PathVariable int id) {
        return repository.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Usuario atualizarUsuario(@PathVariable int id, @RequestBody Usuario usuario) {
        return repository.atualizar(id, usuario);
    }

    @DeleteMapping("/{id}")
    public String deletarUsuario(@PathVariable int id) {
        boolean deletado = repository.deletar(id);
        if (deletado) {
            return "Usuário deletado com sucesso.";
        } else {
            return "Usuário não encontrado.";
        }
    }
}
