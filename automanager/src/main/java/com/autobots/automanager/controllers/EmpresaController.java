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

import com.autobots.automanager.dto.EmpresaDTO;
import com.autobots.automanager.entidades.Empresa;
import com.autobots.automanager.models.AdicionadorLinkEmpresa;
import com.autobots.automanager.repositorios.RepositorioEmpresa;
import com.autobots.automanager.services.EmpresaConverter;

@RestController
@RequestMapping("/empresa")
public class EmpresaController {
    @Autowired
    private RepositorioEmpresa repositorio;
    
    @Autowired
    private AdicionadorLinkEmpresa adicionadorLink;
    
    @Autowired
    private EmpresaConverter converter; //dto

    @GetMapping("/{id}")
    public ResponseEntity<Empresa> obterEmpresa(@PathVariable long id) {
        try {
            if (repositorio.existsById(id)) {
                Empresa empresa = repositorio.findById(id).get();
                adicionadorLink.adicionarLink(empresa);
                return ResponseEntity.ok(empresa);
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Empresa>> obterEmpresas() {
        try {
            List<Empresa> empresas = repositorio.findAll();
            if (empresas.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            adicionadorLink.adicionarLink(empresas);
            return ResponseEntity.ok(empresas);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/cadastro")
    public ResponseEntity<String> cadastrarEmpresa(@RequestBody EmpresaDTO dto) {
        try {
            Empresa empresa = converter.dtoParaEntidade(dto);
            repositorio.save(empresa);
            return ResponseEntity.status(HttpStatus.CREATED).body("Empresa criada com sucesso");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao criar empresa: " + e.getMessage());
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<String> atualizarEmpresa(@PathVariable long id, @RequestBody EmpresaDTO dto) {
        try {
            if (repositorio.existsById(id)) {
                Empresa empresa = repositorio.findById(id).get();
                converter.atualizarEntidade(empresa, dto);
                repositorio.save(empresa);
                return ResponseEntity.ok("Empresa atualizada com sucesso");
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao atualizar empresa: " + e.getMessage());
        }
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<String> excluirEmpresa(@PathVariable long id) {
        try {
            if (repositorio.existsById(id)) {
                repositorio.deleteById(id);
                return ResponseEntity.ok("Empresa excluída com sucesso");
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao excluir empresa");
        }
    }
}
