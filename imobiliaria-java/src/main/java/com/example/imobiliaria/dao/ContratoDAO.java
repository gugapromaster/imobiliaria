package com.example.imobiliaria.dao;

import com.example.imobiliaria.database.ConnectionFactory;
import com.example.imobiliaria.model.Contrato;

import java.sql.*;

public class ContratoDAO {

    public void cadastrar(Contrato contrato) {
        String sql = "INSERT INTO contratos (id_cliente, id_imovel, data_inicio, data_fim, valor_aluguel_contrato) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, contrato.getIdCliente());
            stmt.setInt(2, contrato.getIdImovel());
            stmt.setDate(3, Date.valueOf(contrato.getDataInicio()));
            stmt.setDate(4, Date.valueOf(contrato.getDataFim()));
            stmt.setBigDecimal(5, contrato.getValorAluguelContrato());

            stmt.executeUpdate();
            
            // Após criar o contrato, atualiza o status do imóvel para indisponível
            new ImovelDAO().atualizarDisponibilidade(contrato.getIdImovel(), false);
            
            System.out.println("Contrato de aluguel cadastrado com sucesso!");

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar contrato: " + e.getMessage(), e);
        }
    }
}