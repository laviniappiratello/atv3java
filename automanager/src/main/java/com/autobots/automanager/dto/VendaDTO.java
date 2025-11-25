package com.autobots.automanager.dto;

import java.util.Date;
import java.util.List;

import com.autobots.automanager.enumeracoes.StatusVenda;

public class VendaDTO {
    public String identificacao;
    public Date cadastro;
    public StatusVenda status;
    public Long clienteId;
    public Long funcionarioId;
    public Long veiculoId;
    public List<Long> mercadoriasIds;
    public List<Long> servicosIds;
}
