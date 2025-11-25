package com.autobots.automanager.models;

import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.autobots.automanager.controllers.UsuarioController;
import com.autobots.automanager.entidades.Usuario;
import com.autobots.automanager.dto.UsuarioDTO;

@Component
public class AdicionadorLinkUsuario implements AdicionadorLink<Usuario> {
    
    @Override
    public void adicionarLink(List<Usuario> lista) {
        for (Usuario usuario : lista) {
            adicionarLink(usuario);
        }
    }
    
    @Override
    public void adicionarLink(Usuario usuario) {
        //link para si mesmo 
        Link linkProprio = WebMvcLinkBuilder
            .linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class).obterUsuario(usuario.getId()))
            .withSelfRel();
        usuario.add(linkProprio);
        
        //link para a lista de usuários 
        Link linkUsuarios = WebMvcLinkBuilder
            .linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class).obterUsuarios())
            .withRel("usuarios");
        usuario.add(linkUsuarios);
        
        //link para atualizar - usando DTO
        Link linkAtualizar = WebMvcLinkBuilder
            .linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class).atualizarUsuario(usuario.getId(), new UsuarioDTO()))
            .withRel("atualizar");
        usuario.add(linkAtualizar);
        
        //link para excluir 
        Link linkExcluir = WebMvcLinkBuilder
            .linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class).excluirUsuario(usuario.getId()))
            .withRel("excluir");
        usuario.add(linkExcluir);
    }
}


