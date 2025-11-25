package com.autobots.automanager.services;

import java.util.Set;
import java.util.HashSet;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autobots.automanager.dto.VendaDTO;
import com.autobots.automanager.entidades.Venda;
import com.autobots.automanager.entidades.Usuario;
import com.autobots.automanager.entidades.Veiculo;
import com.autobots.automanager.entidades.Mercadoria;
import com.autobots.automanager.entidades.Servico;
import com.autobots.automanager.repositorios.RepositorioUsuario;
import com.autobots.automanager.repositorios.RepositorioVeiculo;
import com.autobots.automanager.repositorios.RepositorioMercadoria;
import com.autobots.automanager.repositorios.RepositorioServico;

@Service
public class VendaConverter {

    @Autowired
    private RepositorioUsuario repositorioUsuario;

    @Autowired
    private RepositorioVeiculo repositorioVeiculo;

    @Autowired
    private RepositorioMercadoria repositorioMercadoria;

    @Autowired
    private RepositorioServico repositorioServico;

    public Venda dtoParaEntidade(VendaDTO dto) {
        Venda venda = new Venda();
        venda.setIdentificacao(dto.identificacao);
        venda.setCadastro(new Date());
        venda.setStatus(dto.status);

        if (dto.clienteId != null && repositorioUsuario.existsById(dto.clienteId)) {
            Usuario cliente = repositorioUsuario.findById(dto.clienteId).get();
            venda.setCliente(cliente);
        }

        if (dto.funcionarioId != null && repositorioUsuario.existsById(dto.funcionarioId)) {
            Usuario funcionario = repositorioUsuario.findById(dto.funcionarioId).get();
            venda.setFuncionario(funcionario);
        }

        if (dto.veiculoId != null && repositorioVeiculo.existsById(dto.veiculoId)) {
            Veiculo veiculo = repositorioVeiculo.findById(dto.veiculoId).get();
            venda.setVeiculo(veiculo);
        }

        if (dto.mercadoriasIds != null) {
            Set<Mercadoria> mercadorias = new HashSet<>();
            for (Long mercadoriaId : dto.mercadoriasIds) {
                if (repositorioMercadoria.existsById(mercadoriaId)) {
                    Mercadoria mercadoria = repositorioMercadoria.findById(mercadoriaId).get();
                    mercadorias.add(mercadoria);
                }
            }
            venda.setMercadorias(mercadorias);
        }

        if (dto.servicosIds != null) {
            Set<Servico> servicos = new HashSet<>();
            for (Long servicoId : dto.servicosIds) {
                if (repositorioServico.existsById(servicoId)) {
                    Servico servico = repositorioServico.findById(servicoId).get();
                    servicos.add(servico);
                }
            }
            venda.setServicos(servicos);
        }

        return venda;
    }

    public void atualizarEntidade(Venda venda, VendaDTO dto) {
        if (dto.identificacao != null) venda.setIdentificacao(dto.identificacao);
        if (dto.status != null) venda.setStatus(dto.status);
    }
}