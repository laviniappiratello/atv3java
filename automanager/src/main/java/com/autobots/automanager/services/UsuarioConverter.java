package com.autobots.automanager.services;

import java.util.Set;
import java.util.HashSet;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.autobots.automanager.dto.UsuarioDTO;
import com.autobots.automanager.dto.EmailDTO;
import com.autobots.automanager.dto.TelefoneDTO;
import com.autobots.automanager.dto.DocumentoDTO;
import com.autobots.automanager.dto.CredencialDTO;
import com.autobots.automanager.entidades.Usuario;
import com.autobots.automanager.entidades.Email;
import com.autobots.automanager.entidades.Telefone;
import com.autobots.automanager.entidades.Documento;
import com.autobots.automanager.entidades.Credencial;
import com.autobots.automanager.entidades.CredencialUsuarioSenha; //IMPORT DA SUBCLASSE
import com.autobots.automanager.entidades.Endereco;

@Service
public class UsuarioConverter {

    public Usuario dtoParaEntidade(UsuarioDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setNome(dto.nome);
        usuario.setNomeSocial(dto.nomeSocial);
        usuario.setPerfis(dto.perfis);

        if (dto.emails != null) {
            Set<Email> emails = new HashSet<>();
            for (EmailDTO emailDTO : dto.emails) {
                Email email = new Email();
                email.setEndereco(emailDTO.endereco);
                emails.add(email);
            }
            usuario.setEmails(emails);
        }

        if (dto.telefones != null) {
            Set<Telefone> telefones = new HashSet<>();
            for (TelefoneDTO telefoneDTO : dto.telefones) {
                Telefone telefone = new Telefone();
                telefone.setDdd(telefoneDTO.ddd);
                telefone.setNumero(telefoneDTO.numero);
                telefone.setTipo(telefoneDTO.tipo);
                telefones.add(telefone);
            }
            usuario.setTelefones(telefones);
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
            usuario.setEndereco(endereco);
        }

        if (dto.documentos != null) {
            Set<Documento> documentos = new HashSet<>();
            for (DocumentoDTO documentoDTO : dto.documentos) {
                Documento documento = new Documento();
                documento.setTipo(documentoDTO.tipo);
                documento.setNumero(documentoDTO.numero);
                documento.setDataEmissao(documentoDTO.dataEmissao);
                documentos.add(documento);
            }
            usuario.setDocumentos(documentos);
        }

        if (dto.credenciais != null) {
            Set<Credencial> credenciais = new HashSet<>();
            for (CredencialDTO credencialDTO : dto.credenciais) {
                CredencialUsuarioSenha credencial = new CredencialUsuarioSenha();
                credencial.setNomeUsuario(credencialDTO.nomeUsuario);
                credencial.setSenha(credencialDTO.senha);
                credencial.setInativo(credencialDTO.inativo);
                credencial.setCriacao(new Date());
                credencial.setUltimoAcesso(new Date());
                credenciais.add(credencial);
            }
            usuario.setCredenciais(credenciais);
        }

        return usuario;
    }

    public void atualizarEntidade(Usuario usuario, UsuarioDTO dto) {
        if (dto.nome != null) usuario.setNome(dto.nome);
        if (dto.nomeSocial != null) usuario.setNomeSocial(dto.nomeSocial);
        if (dto.perfis != null) usuario.setPerfis(dto.perfis);
    }
}
