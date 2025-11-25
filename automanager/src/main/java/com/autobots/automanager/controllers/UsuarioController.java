package com.autobots.automanager.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.autobots.automanager.dto.UsuarioDTO;
import com.autobots.automanager.entidades.Usuario;
import com.autobots.automanager.models.AdicionadorLinkUsuario;
import com.autobots.automanager.repositorios.RepositorioUsuario;
import com.autobots.automanager.services.UsuarioConverter;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private RepositorioUsuario repositorio;
    
    @Autowired
    private AdicionadorLinkUsuario adicionadorLink;
    
    @Autowired
    private UsuarioConverter converter; //aqui usa o dto

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obterUsuario(@PathVariable long id) {
        try {
            if (repositorio.existsById(id)) {
                Usuario usuario = repositorio.findById(id).get();
                adicionadorLink.adicionarLink(usuario);
                return ResponseEntity.ok(usuario);
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> obterUsuarios() {
        try {
            List<Usuario> usuarios = repositorio.findAll();
            if (usuarios.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            adicionadorLink.adicionarLink(usuarios);
            return ResponseEntity.ok(usuarios);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/cadastro")
    public ResponseEntity<String> cadastrarUsuario(@RequestBody UsuarioDTO dto) {
        try {
            Usuario usuario = converter.dtoParaEntidade(dto);
            repositorio.save(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body("Usuário criado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao criar usuário: " + e.getMessage());
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<String> atualizarUsuario(@PathVariable long id, @RequestBody UsuarioDTO dto) {
        try {
            if (repositorio.existsById(id)) {
                Usuario usuario = repositorio.findById(id).get();
                converter.atualizarEntidade(usuario, dto);
                repositorio.save(usuario);
                return ResponseEntity.ok("Usuário atualizado com sucesso");
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao atualizar usuário: " + e.getMessage());
        }
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<String> excluirUsuario(@PathVariable long id) {
        try {
            if (repositorio.existsById(id)) {
                repositorio.deleteById(id);
                return ResponseEntity.ok("Usuário excluído com sucesso");
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao excluir usuário");
        }
    }
}
