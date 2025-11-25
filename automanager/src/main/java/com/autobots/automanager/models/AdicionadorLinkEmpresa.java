package com.autobots.automanager.models;

import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.autobots.automanager.controllers.EmpresaController;
import com.autobots.automanager.entidades.Empresa;
import com.autobots.automanager.dto.EmpresaDTO;

@Component
public class AdicionadorLinkEmpresa implements AdicionadorLink<Empresa> {
    
    @Override
    public void adicionarLink(List<Empresa> lista) {
        for (Empresa empresa : lista) {
            adicionarLink(empresa);
        }
    }
    
    @Override
    public void adicionarLink(Empresa empresa) {
        //self link
        Link linkProprio = WebMvcLinkBuilder
            .linkTo(WebMvcLinkBuilder.methodOn(EmpresaController.class).obterEmpresa(empresa.getId()))
            .withSelfRel();
        empresa.add(linkProprio);
        
        Link linkEmpresas = WebMvcLinkBuilder
            .linkTo(WebMvcLinkBuilder.methodOn(EmpresaController.class).obterEmpresas())
            .withRel("empresas");
        empresa.add(linkEmpresas);
        
        Link linkAtualizar = WebMvcLinkBuilder
            .linkTo(WebMvcLinkBuilder.methodOn(EmpresaController.class).atualizarEmpresa(empresa.getId(), new EmpresaDTO()))
            .withRel("atualizar");
        empresa.add(linkAtualizar);
        
        Link linkExcluir = WebMvcLinkBuilder
            .linkTo(WebMvcLinkBuilder.methodOn(EmpresaController.class).excluirEmpresa(empresa.getId()))
            .withRel("excluir");
        empresa.add(linkExcluir);
    }
}
