package com.example.imobiliaria.model;

import java.math.BigDecimal;

public class Imovel {
    private int id;
    private String descricao;
    private String endereco;
    private String tipoImovel;
    private BigDecimal valorAluguel;
    private boolean disponivel;

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }
    public String getTipoImovel() { return tipoImovel; }
    public void setTipoImovel(String tipoImovel) { this.tipoImovel = tipoImovel; }
    public BigDecimal getValorAluguel() { return valorAluguel; }
    public void setValorAluguel(BigDecimal valorAluguel) { this.valorAluguel = valorAluguel; }
    public boolean isDisponivel() { return disponivel; }
    public void setDisponivel(boolean disponivel) { this.disponivel = disponivel; }

    @Override
    public String toString() {
        String status = disponivel ? "Disponível" : "Indisponível";
        return "Imovel [ID=" + id + ", Descrição=" + descricao + ", Endereço=" + endereco + 
               ", Tipo=" + tipoImovel + ", Aluguel=R$" + valorAluguel + ", Status=" + status + "]";
    }
}