package com.example.imobiliaria.report;

import com.example.imobiliaria.database.ConnectionFactory;
import com.example.imobiliaria.model.Contrato;
import com.example.imobiliaria.model.Imovel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Relatorios {

    public List<Imovel> listarImoveisDisponiveis() {
        List<Imovel> imoveis = new ArrayList<>();
        String sql = "SELECT * FROM imoveis WHERE disponivel = TRUE";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Imovel imovel = new Imovel();
                imovel.setId(rs.getInt("id"));
                imovel.setDescricao(rs.getString("descricao"));
                imovel.setEndereco(rs.getString("endereco"));
                imovel.setTipoImovel(rs.getString("tipo_imovel"));
                imovel.setValorAluguel(rs.getBigDecimal("valor_aluguel"));
                imovel.setDisponivel(rs.getBoolean("disponivel"));
                imoveis.add(imovel);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar imóveis disponíveis: " + e.getMessage(), e);
        }
        return imoveis;
    }

    public List<Contrato> listarContratosAtivos() {
        List<Contrato> contratos = new ArrayList<>();
        String sql = "SELECT con.*, cli.nome as nome_cliente, imo.descricao as descricao_imovel " +
                     "FROM contratos con " +
                "JOIN clientes cli ON con.cliente_id = cli.id "  +
                     "JOIN imoveis imo ON con.id_imovel = imo.id " +
                     "WHERE con.ativo = TRUE AND CURDATE() BETWEEN con.data_inicio AND con.data_fim";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Contrato contrato = new Contrato();
                contrato.setId(rs.getInt("id"));
                contrato.setDataInicio(rs.getDate("data_inicio").toLocalDate());
                contrato.setDataFim(rs.getDate("data_fim").toLocalDate());
                contrato.setValorAluguelContrato(rs.getBigDecimal("valor_aluguel_contrato"));
                contrato.setAtivo(rs.getBoolean("ativo"));
                contrato.setNomeCliente(rs.getString("nome_cliente"));
                contrato.setDescricaoImovel(rs.getString("descricao_imovel"));
                contratos.add(contrato);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar contratos ativos: " + e.getMessage(), e);
        }
        return contratos;
    }
    
    public void listarClientesComMaisContratos() {
        String sql = "SELECT c.nome, COUNT(co.id) as total_contratos " +
                     "FROM clientes c " +
                     "JOIN contratos co ON c.id = co.id_cliente " +
                     "GROUP BY c.id, c.nome " +
                     "ORDER BY total_contratos DESC";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            System.out.println("\n--- Clientes com Mais Contratos ---");
            while (rs.next()) {
                String nomeCliente = rs.getString("nome");
                int total = rs.getInt("total_contratos");
                System.out.printf("Cliente: %s | Total de Contratos: %d\n", nomeCliente, total);
            }
            System.out.println("------------------------------------");

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao gerar relatório de clientes: " + e.getMessage(), e);
        }
    }
    
    public List<Contrato> listarContratosExpirando() {
        List<Contrato> contratos = new ArrayList<>();
        String sql = "SELECT con.*, cli.nome as nome_cliente, imo.descricao as descricao_imovel " +
                     "FROM contratos con " +
                     "JOIN clientes cli ON con.id_cliente = cli.id " +
                     "JOIN imoveis imo ON con.id_imovel = imo.id " +
                     "WHERE con.ativo = TRUE AND con.data_fim BETWEEN CURDATE() AND DATE_ADD(CURDATE(), INTERVAL 30 DAY)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Contrato contrato = new Contrato();
                contrato.setId(rs.getInt("id"));
                contrato.setDataInicio(rs.getDate("data_inicio").toLocalDate());
                contrato.setDataFim(rs.getDate("data_fim").toLocalDate());
                contrato.setValorAluguelContrato(rs.getBigDecimal("valor_aluguel_contrato"));
                contrato.setAtivo(rs.getBoolean("ativo"));
                contrato.setNomeCliente(rs.getString("nome_cliente"));
                contrato.setDescricaoImovel(rs.getString("descricao_imovel"));
                contratos.add(contrato);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar contratos expirando: " + e.getMessage(), e);
        }
        return contratos;
    }
}