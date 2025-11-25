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

import com.autobots.automanager.dto.MercadoriaDTO;
import com.autobots.automanager.entidades.Mercadoria;
import com.autobots.automanager.models.AdicionadorLinkMercadoria;
import com.autobots.automanager.repositorios.RepositorioMercadoria;
import com.autobots.automanager.services.MercadoriaConverter;

@RestController
@RequestMapping("/mercadoria")
public class MercadoriaController {
    @Autowired
    private RepositorioMercadoria repositorio;
    
    @Autowired
    private AdicionadorLinkMercadoria adicionadorLink;
    
    @Autowired
    private MercadoriaConverter converter;

    @GetMapping("/{id}")
    public ResponseEntity<Mercadoria> obterMercadoria(@PathVariable long id) {
        try {
            if (repositorio.existsById(id)) {
                Mercadoria mercadoria = repositorio.findById(id).get();
                adicionadorLink.adicionarLink(mercadoria);
                return ResponseEntity.ok(mercadoria);
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Mercadoria>> obterMercadorias() {
        try {
            List<Mercadoria> mercadorias = repositorio.findAll();
            if (mercadorias.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            adicionadorLink.adicionarLink(mercadorias);
            return ResponseEntity.ok(mercadorias);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/cadastro")
    public ResponseEntity<String> cadastrarMercadoria(@RequestBody MercadoriaDTO dto) {
        try {
            Mercadoria mercadoria = converter.dtoParaEntidade(dto);
            repositorio.save(mercadoria);
            return ResponseEntity.status(HttpStatus.CREATED).body("Mercadoria criada com sucesso");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao criar mercadoria: " + e.getMessage());
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<String> atualizarMercadoria(@PathVariable long id, @RequestBody MercadoriaDTO dto) {
        try {
            if (repositorio.existsById(id)) {
                Mercadoria mercadoria = repositorio.findById(id).get();
                converter.atualizarEntidade(mercadoria, dto);
                repositorio.save(mercadoria);
                return ResponseEntity.ok("Mercadoria atualizada com sucesso");
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao atualizar mercadoria: " + e.getMessage());
        }
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<String> excluirMercadoria(@PathVariable long id) {
        try {
            if (repositorio.existsById(id)) {
                repositorio.deleteById(id);
                return ResponseEntity.ok("Mercadoria excluída com sucesso");
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao excluir mercadoria");
        }
    }
}
