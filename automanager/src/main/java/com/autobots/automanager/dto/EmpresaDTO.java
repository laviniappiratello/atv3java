package com.autobots.automanager.dto;

import java.util.Date;
import java.util.List;

public class EmpresaDTO {
    public String razaoSocial;
    public String nomeFantasia;
    public Date cadastro;
    public List<TelefoneDTO> telefones;
    public EnderecoDTO endereco;
    public List<Long> usuariosIds;
    public List<Long> mercadoriasIds;
    public List<Long> servicosIds;
    public List<Long> vendasIds;
}
