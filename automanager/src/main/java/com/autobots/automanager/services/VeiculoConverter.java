package com.autobots.automanager.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autobots.automanager.dto.VeiculoDTO;
import com.autobots.automanager.entidades.Veiculo;
import com.autobots.automanager.entidades.Usuario;
import com.autobots.automanager.repositorios.RepositorioUsuario;

@Service
public class VeiculoConverter {

    @Autowired
    private RepositorioUsuario repositorioUsuario;

    public Veiculo dtoParaEntidade(VeiculoDTO dto) {
        Veiculo veiculo = new Veiculo();
        veiculo.setTipo(dto.tipo);
        veiculo.setModelo(dto.modelo);
        veiculo.setPlaca(dto.placa);

        // Associar proprietário se fornecido
        if (dto.proprietarioId != null && repositorioUsuario.existsById(dto.proprietarioId)) {
            Usuario proprietario = repositorioUsuario.findById(dto.proprietarioId).get();
            veiculo.setProprietario(proprietario);
        }

        return veiculo;
    }

    public void atualizarEntidade(Veiculo veiculo, VeiculoDTO dto) {
        if (dto.tipo != null) veiculo.setTipo(dto.tipo);
        if (dto.modelo != null) veiculo.setModelo(dto.modelo);
        if (dto.placa != null) veiculo.setPlaca(dto.placa);
    }
}