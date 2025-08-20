package com.example.imobiliaria.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Contrato {
    private int id;
    private int idCliente;
    private int idImovel;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private BigDecimal valorAluguelContrato;
    private boolean ativo;
    
    // Opcional: Para relatórios mais completos
    private String nomeCliente;
    private String descricaoImovel;

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getIdCliente() { return idCliente; }
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }
    public int getIdImovel() { return idImovel; }
    public void setIdImovel(int idImovel) { this.idImovel = idImovel; }
    public LocalDate getDataInicio() { return dataInicio; }
    public void setDataInicio(LocalDate dataInicio) { this.dataInicio = dataInicio; }
    public LocalDate getDataFim() { return dataFim; }
    public void setDataFim(LocalDate dataFim) { this.dataFim = dataFim; }
    public BigDecimal getValorAluguelContrato() { return valorAluguelContrato; }
    public void setValorAluguelContrato(BigDecimal valorAluguelContrato) { this.valorAluguelContrato = valorAluguelContrato; }
    public boolean isAtivo() { return ativo; }
    public void setAtivo(boolean ativo) { this.ativo = ativo; }
    public String getNomeCliente() { return nomeCliente; }
    public void setNomeCliente(String nomeCliente) { this.nomeCliente = nomeCliente; }
    public String getDescricaoImovel() { return descricaoImovel; }
    public void setDescricaoImovel(String descricaoImovel) { this.descricaoImovel = descricaoImovel; }

    @Override
    public String toString() {
        return String.format("Contrato [ID=%d, Cliente=%s, Imóvel=%s, Início=%s, Fim=%s, Valor=R$%.2f, Ativo=%b]",
                id, nomeCliente, descricaoImovel, dataInicio, dataFim, valorAluguelContrato, ativo);
    }
}