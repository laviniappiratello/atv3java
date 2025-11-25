package com.autobots.automanager.services;

import java.util.Set;
import java.util.HashSet;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.autobots.automanager.dto.EmpresaDTO;
import com.autobots.automanager.dto.TelefoneDTO;
import com.autobots.automanager.entidades.Empresa;
import com.autobots.automanager.entidades.Telefone;
import com.autobots.automanager.entidades.Endereco;

@Service
public class EmpresaConverter {

    public Empresa dtoParaEntidade(EmpresaDTO dto) {
        Empresa empresa = new Empresa();
        empresa.setRazaoSocial(dto.razaoSocial);
        empresa.setNomeFantasia(dto.nomeFantasia);
        empresa.setCadastro(new Date());

        if (dto.telefones != null) {
            Set<Telefone> telefones = new HashSet<>();
            for (TelefoneDTO telefoneDTO : dto.telefones) {
                Telefone telefone = new Telefone();
                telefone.setDdd(telefoneDTO.ddd);
                telefone.setNumero(telefoneDTO.numero);
                telefone.setTipo(telefoneDTO.tipo);
                telefones.add(telefone);
            }
            empresa.setTelefones(telefones);
        }

        if (dto.endereco != null) {
            Endereco endereco = new Endereco();
            endereco.setEstado(dto.endereco.estado);
            endereco.setCidade(dto.endereco.cidade);
            endereco.setBairro(dto.endereco.bairro);
            endereco.setRua(dto.endereco.rua);
            endereco.setNumero(dto.endereco.numero);
            endereco.setCodigoPostal(dto.endereco.codigoPostal);
            endereco.setInformacoesAdicionais(dto.endereco.informacoesAdicionais);
            endereco.setTipo(dto.endereco.tipo);
            empresa.setEndereco(endereco);
        }

        return empresa;
    }

    public void atualizarEntidade(Empresa empresa, EmpresaDTO dto) {
        if (dto.razaoSocial != null) empresa.setRazaoSocial(dto.razaoSocial);
        if (dto.nomeFantasia != null) empresa.setNomeFantasia(dto.nomeFantasia);
    }
}