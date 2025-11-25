package com.autobots.automanager.services;

import java.util.Date;
import org.springframework.stereotype.Service;

import com.autobots.automanager.dto.MercadoriaDTO;
import com.autobots.automanager.entidades.Mercadoria;

@Service
public class MercadoriaConverter {

    public Mercadoria dtoParaEntidade(MercadoriaDTO dto) {
        Mercadoria mercadoria = new Mercadoria();
        mercadoria.setCadastro(new Date());
        mercadoria.setNome(dto.nome);
        mercadoria.setQuantidade(dto.quantidade);
        mercadoria.setValor(dto.valor);
        mercadoria.setDescricao(dto.descricao);
        mercadoria.setFabricao(dto.fabricao);
        mercadoria.setValidade(dto.validade);
        return mercadoria;
    }

    public void atualizarEntidade(Mercadoria mercadoria, MercadoriaDTO dto) {
        if (dto.nome != null) mercadoria.setNome(dto.nome);
        if (dto.quantidade > 0) mercadoria.setQuantidade(dto.quantidade);
        if (dto.valor > 0) mercadoria.setValor(dto.valor);
        if (dto.descricao != null) mercadoria.setDescricao(dto.descricao);
        if (dto.fabricao != null) mercadoria.setFabricao(dto.fabricao);
        if (dto.validade != null) mercadoria.setValidade(dto.validade);
    }
}