package com.autobots.automanager.dto;

import java.util.List;
import java.util.Set;

import com.autobots.automanager.enumeracoes.PerfilUsuario;

public class UsuarioDTO {
    public String nome;
    public String nomeSocial;
    public Set<PerfilUsuario> perfis; // MUDANÇA: List → Set
    public List<EmailDTO> emails;
    public List<TelefoneDTO> telefones;
    public EnderecoDTO endereco;
    public List<DocumentoDTO> documentos;
    public List<CredencialDTO> credenciais;
}
