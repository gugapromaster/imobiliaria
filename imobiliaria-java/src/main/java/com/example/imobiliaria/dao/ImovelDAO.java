package com.example.imobiliaria.dao;

import com.example.imobiliaria.database.ConnectionFactory;
import com.example.imobiliaria.model.Imovel;

import java.sql.*;

public class ImovelDAO {

    public void cadastrar(Imovel imovel) {
        String sql = "INSERT INTO imoveis (descricao, endereco, tipo_imovel, valor_aluguel) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, imovel.getDescricao());
            stmt.setString(2, imovel.getEndereco());
            stmt.setString(3, imovel.getTipoImovel());
            stmt.setBigDecimal(4, imovel.getValorAluguel());

            stmt.executeUpdate();
            System.out.println("Imóvel cadastrado com sucesso!");

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar imóvel: " + e.getMessage(), e);
        }
    }

    public void atualizarDisponibilidade(int idImovel, boolean disponivel) {
        String sql = "UPDATE imoveis SET disponivel = ? WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setBoolean(1, disponivel);
            stmt.setInt(2, idImovel);

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar disponibilidade do imóvel: " + e.getMessage(), e);
        }
    }
}