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

import com.autobots.automanager.dto.VendaDTO;
import com.autobots.automanager.entidades.Venda;
import com.autobots.automanager.models.AdicionadorLinkVenda;
import com.autobots.automanager.repositorios.RepositorioVenda;
import com.autobots.automanager.services.VendaConverter;

@RestController
@RequestMapping("/venda")
public class VendaController {
    @Autowired
    private RepositorioVenda repositorio;
    
    @Autowired
    private AdicionadorLinkVenda adicionadorLink;
    
    @Autowired
    private VendaConverter converter;

    @GetMapping("/{id}")
    public ResponseEntity<Venda> obterVenda(@PathVariable long id) {
        try {
            if (repositorio.existsById(id)) {
                Venda venda = repositorio.findById(id).get();
                adicionadorLink.adicionarLink(venda);
                return ResponseEntity.ok(venda);
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Venda>> obterVendas() {
        try {
            List<Venda> vendas = repositorio.findAll();
            if (vendas.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            adicionadorLink.adicionarLink(vendas);
            return ResponseEntity.ok(vendas);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/cadastro")
    public ResponseEntity<String> cadastrarVenda(@RequestBody VendaDTO dto) {
        try {
            Venda venda = converter.dtoParaEntidade(dto);
            repositorio.save(venda);
            return ResponseEntity.status(HttpStatus.CREATED).body("Venda criada com sucesso");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao criar venda: " + e.getMessage());
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<String> atualizarVenda(@PathVariable long id, @RequestBody VendaDTO dto) {
        try {
            if (repositorio.existsById(id)) {
                Venda venda = repositorio.findById(id).get();
                converter.atualizarEntidade(venda, dto);
                repositorio.save(venda);
                return ResponseEntity.ok("Venda atualizada com sucesso");
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao atualizar venda: " + e.getMessage());
        }
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<String> excluirVenda(@PathVariable long id) {
        try {
            if (repositorio.existsById(id)) {
                repositorio.deleteById(id);
                return ResponseEntity.ok("Venda excluída com sucesso");
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao excluir venda");
        }
    }
}
