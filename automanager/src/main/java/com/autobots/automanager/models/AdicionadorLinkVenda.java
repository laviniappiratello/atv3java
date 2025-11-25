package com.autobots.automanager.models;

import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.autobots.automanager.controllers.VendaController;
import com.autobots.automanager.entidades.Venda;
import com.autobots.automanager.dto.VendaDTO;

@Component
public class AdicionadorLinkVenda implements AdicionadorLink<Venda> {
    
    @Override
    public void adicionarLink(List<Venda> lista) {
        for (Venda venda : lista) {
            adicionarLink(venda);
        }
    }
    
    @Override
    public void adicionarLink(Venda venda) {
        Link linkProprio = WebMvcLinkBuilder
            .linkTo(WebMvcLinkBuilder.methodOn(VendaController.class).obterVenda(venda.getId()))
            .withSelfRel();
        venda.add(linkProprio);
        
        Link linkVendas = WebMvcLinkBuilder
            .linkTo(WebMvcLinkBuilder.methodOn(VendaController.class).obterVendas())
            .withRel("vendas");
        venda.add(linkVendas);
        
        Link linkAtualizar = WebMvcLinkBuilder
            .linkTo(WebMvcLinkBuilder.methodOn(VendaController.class).atualizarVenda(venda.getId(), new VendaDTO()))
            .withRel("atualizar");
        venda.add(linkAtualizar);
        
        Link linkExcluir = WebMvcLinkBuilder
            .linkTo(WebMvcLinkBuilder.methodOn(VendaController.class).excluirVenda(venda.getId()))
            .withRel("excluir");
        venda.add(linkExcluir);
    }
}
