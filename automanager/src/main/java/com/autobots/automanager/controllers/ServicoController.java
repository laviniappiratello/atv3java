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

import com.autobots.automanager.dto.ServicoDTO;
import com.autobots.automanager.entidades.Servico;
import com.autobots.automanager.models.AdicionadorLinkServico;
import com.autobots.automanager.repositorios.RepositorioServico;
import com.autobots.automanager.services.ServicoConverter;

@RestController
@RequestMapping("/servico")
public class ServicoController {
    @Autowired
    private RepositorioServico repositorio;
    
    @Autowired
    private AdicionadorLinkServico adicionadorLink;
    
    @Autowired
    private ServicoConverter converter;

    @GetMapping("/{id}")
    public ResponseEntity<Servico> obterServico(@PathVariable long id) {
        try {
            if (repositorio.existsById(id)) {
                Servico servico = repositorio.findById(id).get();
                adicionadorLink.adicionarLink(servico);
                return ResponseEntity.ok(servico);
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Servico>> obterServicos() {
        try {
            List<Servico> servicos = repositorio.findAll();
            if (servicos.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            adicionadorLink.adicionarLink(servicos);
            return ResponseEntity.ok(servicos);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/cadastro")
    public ResponseEntity<String> cadastrarServico(@RequestBody ServicoDTO dto) {
        try {
            Servico servico = converter.dtoParaEntidade(dto);
            repositorio.save(servico);
            return ResponseEntity.status(HttpStatus.CREATED).body("Serviço criado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao criar serviço: " + e.getMessage());
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<String> atualizarServico(@PathVariable long id, @RequestBody ServicoDTO dto) {
        try {
            if (repositorio.existsById(id)) {
                Servico servico = repositorio.findById(id).get();
                converter.atualizarEntidade(servico, dto);
                repositorio.save(servico);
                return ResponseEntity.ok("Serviço atualizado com sucesso");
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao atualizar serviço: " + e.getMessage());
        }
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<String> excluirServico(@PathVariable long id) {
        try {
            if (repositorio.existsById(id)) {
                repositorio.deleteById(id);
                return ResponseEntity.ok("Serviço excluído com sucesso");
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao excluir serviço");
        }
    }
}
