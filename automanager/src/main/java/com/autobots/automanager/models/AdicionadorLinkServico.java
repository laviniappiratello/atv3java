package com.autobots.automanager.models;

import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.autobots.automanager.controllers.ServicoController;
import com.autobots.automanager.entidades.Servico;
import com.autobots.automanager.dto.ServicoDTO;

@Component
public class AdicionadorLinkServico implements AdicionadorLink<Servico> {
    
    @Override
    public void adicionarLink(List<Servico> lista) {
        for (Servico servico : lista) {
            adicionarLink(servico);
        }
    }
    
    @Override
    public void adicionarLink(Servico servico) {
        Link linkProprio = WebMvcLinkBuilder
            .linkTo(WebMvcLinkBuilder.methodOn(ServicoController.class).obterServico(servico.getId()))
            .withSelfRel();
        servico.add(linkProprio);
        
        Link linkServicos = WebMvcLinkBuilder
            .linkTo(WebMvcLinkBuilder.methodOn(ServicoController.class).obterServicos())
            .withRel("servicos");
        servico.add(linkServicos);
        
        Link linkAtualizar = WebMvcLinkBuilder
            .linkTo(WebMvcLinkBuilder.methodOn(ServicoController.class).atualizarServico(servico.getId(), new ServicoDTO()))
            .withRel("atualizar");
        servico.add(linkAtualizar);
        
        Link linkExcluir = WebMvcLinkBuilder
            .linkTo(WebMvcLinkBuilder.methodOn(ServicoController.class).excluirServico(servico.getId()))
            .withRel("excluir");
        servico.add(linkExcluir);
    }
}
