package com.example.imobiliaria;

import com.example.imobiliaria.dao.ClienteDAO;
import com.example.imobiliaria.dao.ContratoDAO;
import com.example.imobiliaria.dao.ImovelDAO;
import com.example.imobiliaria.model.Cliente;
import com.example.imobiliaria.model.Contrato;
import com.example.imobiliaria.model.Imovel;
import com.example.imobiliaria.report.Relatorios;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) {
        int opcao;
        do {
            exibirMenu();
            opcao = Integer.parseInt(scanner.nextLine());
            processarOpcao(opcao);
        } while (opcao != 0);

        System.out.println("Sistema finalizado.");
        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("\n--- Sistema de Gestão Imobiliária ---");
        System.out.println("1. Cadastrar Imóvel");
        System.out.println("2. Cadastrar Cliente");
        System.out.println("3. Cadastrar Contrato de Aluguel");
        System.out.println("--- Relatórios ---");
        System.out.println("4. Listar Imóveis Disponíveis");
        System.out.println("5. Listar Contratos Ativos");
        System.out.println("6. Listar Clientes com Mais Contratos");
        System.out.println("7. Listar Contratos Expirando em 30 dias");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void processarOpcao(int opcao) {
        switch (opcao) {
            case 1:
                cadastrarImovel();
                break;
            case 2:
                cadastrarCliente();
                break;
            case 3:
                cadastrarContrato();
                break;
            case 4:
                listarImoveisDisponiveis();
                break;
            case 5:
                listarContratosAtivos();
                break;
            case 6:
                listarClientesComMaisContratos();
                break;
            case 7:
                listarContratosExpirando();
                break;
            case 0:
                break;
            default:
                System.out.println("Opção inválida!");
        }
    }

    private static void cadastrarImovel() {
        System.out.println("\n--- Cadastro de Imóvel ---");
        Imovel imovel = new Imovel();
        
        System.out.print("Descrição: ");
        imovel.setDescricao(scanner.nextLine());
        
        System.out.print("Endereço: ");
        imovel.setEndereco(scanner.nextLine());
        
        System.out.print("Tipo (Casa, Apartamento, etc): ");
        imovel.setTipoImovel(scanner.nextLine());

        System.out.print("Valor do Aluguel (ex: 1500.00): ");
        imovel.setValorAluguel(new BigDecimal(scanner.nextLine()));

        new ImovelDAO().cadastrar(imovel);
    }

    private static void cadastrarCliente() {
        System.out.println("\n--- Cadastro de Cliente ---");
        Cliente cliente = new Cliente();

        System.out.print("Nome completo: ");
        cliente.setNome(scanner.nextLine());

        System.out.print("CPF (xxx.xxx.xxx-xx): ");
        cliente.setCpf(scanner.nextLine());

        System.out.print("Telefone: ");
        cliente.setTelefone(scanner.nextLine());

        System.out.print("Email: ");
        cliente.setEmail(scanner.nextLine());

        new ClienteDAO().cadastrar(cliente);
    }

    private static void cadastrarContrato() {
        System.out.println("\n--- Cadastro de Contrato de Aluguel ---");
        Contrato contrato = new Contrato();
        
        System.out.print("ID do Cliente: ");
        contrato.setIdCliente(Integer.parseInt(scanner.nextLine()));
        
        System.out.print("ID do Imóvel (deve estar disponível): ");
        contrato.setIdImovel(Integer.parseInt(scanner.nextLine()));
        
        System.out.print("Data de Início (dd/MM/yyyy): ");
        contrato.setDataInicio(LocalDate.parse(scanner.nextLine(), dateFormatter));
        
        System.out.print("Data de Fim (dd/MM/yyyy): ");
        contrato.setDataFim(LocalDate.parse(scanner.nextLine(), dateFormatter));
        
        System.out.print("Valor do Aluguel no Contrato (ex: 1500.00): ");
        contrato.setValorAluguelContrato(new BigDecimal(scanner.nextLine()));
        
        new ContratoDAO().cadastrar(contrato);
    }

    private static void listarImoveisDisponiveis() {
        System.out.println("\n--- Relatório: Imóveis Disponíveis ---");
        List<Imovel> imoveis = new Relatorios().listarImoveisDisponiveis();
        if (imoveis.isEmpty()) {
            System.out.println("Nenhum imóvel disponível no momento.");
        } else {
            imoveis.forEach(System.out::println);
        }
        System.out.println("---------------------------------------");
    }

    private static void listarContratosAtivos() {
        System.out.println("\n--- Relatório: Contratos Ativos ---");
        List<Contrato> contratos = new Relatorios().listarContratosAtivos();
        if (contratos.isEmpty()) {
            System.out.println("Nenhum contrato ativo no momento.");
        } else {
            contratos.forEach(System.out::println);
        }
        System.out.println("-------------------------------------");
    }
    
    private static void listarClientesComMaisContratos() {
        new Relatorios().listarClientesComMaisContratos();
    }

    private static void listarContratosExpirando() {
        System.out.println("\n--- Relatório: Contratos Expirando nos Próximos 30 Dias ---");
        List<Contrato> contratos = new Relatorios().listarContratosExpirando();
        if (contratos.isEmpty()) {
            System.out.println("Nenhum contrato expira nos próximos 30 dias.");
        } else {
            contratos.forEach(System.out::println);
        }
        System.out.println("---------------------------------------------------------");
    }
}