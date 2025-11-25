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

import com.autobots.automanager.dto.VeiculoDTO;
import com.autobots.automanager.entidades.Veiculo;
import com.autobots.automanager.models.AdicionadorLinkVeiculo;
import com.autobots.automanager.repositorios.RepositorioVeiculo;
import com.autobots.automanager.services.VeiculoConverter;

@RestController
@RequestMapping("/veiculo")
public class VeiculoController {
    @Autowired
    private RepositorioVeiculo repositorio;
    
    @Autowired
    private AdicionadorLinkVeiculo adicionadorLink;
    
    @Autowired
    private VeiculoConverter converter;

    @GetMapping("/{id}")
    public ResponseEntity<Veiculo> obterVeiculo(@PathVariable long id) {
        try {
            if (repositorio.existsById(id)) {
                Veiculo veiculo = repositorio.findById(id).get();
                adicionadorLink.adicionarLink(veiculo);
                return ResponseEntity.ok(veiculo);
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Veiculo>> obterVeiculos() {
        try {
            List<Veiculo> veiculos = repositorio.findAll();
            if (veiculos.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            adicionadorLink.adicionarLink(veiculos);
            return ResponseEntity.ok(veiculos);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/cadastro")
    public ResponseEntity<String> cadastrarVeiculo(@RequestBody VeiculoDTO dto) {
        try {
            Veiculo veiculo = converter.dtoParaEntidade(dto);
            repositorio.save(veiculo);
            return ResponseEntity.status(HttpStatus.CREATED).body("Veículo criado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao criar veículo: " + e.getMessage());
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<String> atualizarVeiculo(@PathVariable long id, @RequestBody VeiculoDTO dto) {
        try {
            if (repositorio.existsById(id)) {
                Veiculo veiculo = repositorio.findById(id).get();
                converter.atualizarEntidade(veiculo, dto);
                repositorio.save(veiculo);
                return ResponseEntity.ok("Veículo atualizado com sucesso");
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao atualizar veículo: " + e.getMessage());
        }
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<String> excluirVeiculo(@PathVariable long id) {
        try {
            if (repositorio.existsById(id)) {
                repositorio.deleteById(id);
                return ResponseEntity.ok("Veículo excluído com sucesso");
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao excluir veículo");
        }
    }
}
