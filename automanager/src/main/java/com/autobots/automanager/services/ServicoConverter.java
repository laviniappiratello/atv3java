package com.autobots.automanager.services;

import org.springframework.stereotype.Service;

import com.autobots.automanager.dto.ServicoDTO;
import com.autobots.automanager.entidades.Servico;

@Service
public class ServicoConverter {

    public Servico dtoParaEntidade(ServicoDTO dto) {
        Servico servico = new Servico();
        servico.setNome(dto.nome);
        servico.setValor(dto.valor);
        servico.setDescricao(dto.descricao);
        return servico;
    }

    public void atualizarEntidade(Servico servico, ServicoDTO dto) {
        if (dto.nome != null) servico.setNome(dto.nome);
        if (dto.valor > 0) servico.setValor(dto.valor);
        if (dto.descricao != null) servico.setDescricao(dto.descricao);
    }
}