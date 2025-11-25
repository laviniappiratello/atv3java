package com.autobots.automanager.models;

import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.autobots.automanager.controllers.MercadoriaController;
import com.autobots.automanager.entidades.Mercadoria;
import com.autobots.automanager.dto.MercadoriaDTO;

@Component
public class AdicionadorLinkMercadoria implements AdicionadorLink<Mercadoria> {
    
    @Override
    public void adicionarLink(List<Mercadoria> lista) {
        for (Mercadoria mercadoria : lista) {
            adicionarLink(mercadoria);
        }
    }
    
    @Override
    public void adicionarLink(Mercadoria mercadoria) {
        Link linkProprio = WebMvcLinkBuilder
            .linkTo(WebMvcLinkBuilder.methodOn(MercadoriaController.class).obterMercadoria(mercadoria.getId()))
            .withSelfRel();
        mercadoria.add(linkProprio);
        
        Link linkMercadorias = WebMvcLinkBuilder
            .linkTo(WebMvcLinkBuilder.methodOn(MercadoriaController.class).obterMercadorias())
            .withRel("mercadorias");
        mercadoria.add(linkMercadorias);
        
        Link linkAtualizar = WebMvcLinkBuilder
            .linkTo(WebMvcLinkBuilder.methodOn(MercadoriaController.class).atualizarMercadoria(mercadoria.getId(), new MercadoriaDTO()))
            .withRel("atualizar");
        mercadoria.add(linkAtualizar);
        
        Link linkExcluir = WebMvcLinkBuilder
            .linkTo(WebMvcLinkBuilder.methodOn(MercadoriaController.class).excluirMercadoria(mercadoria.getId()))
            .withRel("excluir");
        mercadoria.add(linkExcluir);
    }
}
