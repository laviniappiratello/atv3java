package com.autobots.automanager.models;

import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.autobots.automanager.controllers.VeiculoController;
import com.autobots.automanager.entidades.Veiculo;
import com.autobots.automanager.dto.VeiculoDTO;

@Component
public class AdicionadorLinkVeiculo implements AdicionadorLink<Veiculo> {
    
    @Override
    public void adicionarLink(List<Veiculo> lista) {
        for (Veiculo veiculo : lista) {
            adicionarLink(veiculo);
        }
    }
    
    @Override
    public void adicionarLink(Veiculo veiculo) {
        Link linkProprio = WebMvcLinkBuilder
            .linkTo(WebMvcLinkBuilder.methodOn(VeiculoController.class).obterVeiculo(veiculo.getId()))
            .withSelfRel();
        veiculo.add(linkProprio);
        
        Link linkVeiculos = WebMvcLinkBuilder
            .linkTo(WebMvcLinkBuilder.methodOn(VeiculoController.class).obterVeiculos())
            .withRel("veiculos");
        veiculo.add(linkVeiculos);
        
        Link linkAtualizar = WebMvcLinkBuilder
            .linkTo(WebMvcLinkBuilder.methodOn(VeiculoController.class).atualizarVeiculo(veiculo.getId(), new VeiculoDTO()))
            .withRel("atualizar");
        veiculo.add(linkAtualizar);
        
        Link linkExcluir = WebMvcLinkBuilder
            .linkTo(WebMvcLinkBuilder.methodOn(VeiculoController.class).excluirVeiculo(veiculo.getId()))
            .withRel("excluir");
        veiculo.add(linkExcluir);
    }
}