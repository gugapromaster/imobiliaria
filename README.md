# 🏡 Sistema de Gestão para Imobiliária (CLI em Java)

Um sistema de **console (CLI)** em Java para gerenciar clientes, imóveis e contratos de aluguel de uma pequena imobiliária familiar.  
A interação é feita inteiramente via terminal, garantindo **simplicidade** e **eficiência** nas tarefas do dia a dia.

---

## ✨ Funcionalidades Principais

### 🏠 Gestão de Imóveis
- Cadastro de novos imóveis com suas características (descrição, endereço, tipo, valor).
- O **status de disponibilidade** é gerenciado automaticamente pelo sistema.

### 👤 Gestão de Clientes
- Cadastro de clientes com informações de contato.

### ✍️ Gestão de Contratos
- Criação de contratos de aluguel, associando um cliente a um **imóvel disponível**.
- Ao criar um contrato, o imóvel associado torna-se **indisponível**.

### 📊 Relatórios Gerenciais
- Listagem de **todos os imóveis disponíveis** para aluguel.
- Listagem de **todos os contratos ativos** no momento.
- **Ranking** de clientes com o maior número de contratos históricos.
- **Alerta** de contratos **expirando nos próximos 30 dias**, permitindo uma gestão proativa.

---

## 🛠️ Tecnologias Utilizadas

| Tecnologia | Propósito |
|-----------:|:---------|
| Java 11 | Linguagem principal do projeto |
| JDBC | Conexão e comunicação com o banco de dados |
| Maven | Gerenciamento de dependências (conector MySQL) |
| MySQL 8.0 | SGDB para persistência dos dados |

> **Observação:** ajuste as versões conforme o seu ambiente, se necessário.

---

## 🏛️ Arquitetura e Modelagem

O projeto foi estruturado utilizando o padrão de camadas **DAO (Data Access Object)** para separar as regras de negócio da lógica de acesso a dados.

- **Camada `model`**: classes de domínio (Cliente, Imovel, Contrato, etc.).  
- **Camada `dao`**: acesso a dados e operações CRUD via JDBC.  
- **Camada `database`**: `ConnectionFactory` e utilitários de conexão.  
- **Camada `report`**: geração de relatórios no console.  
- **Camada `view`/CLI**: menus e interação com o usuário no terminal.  

### Diagrama de Classe (UML)
Coloque a imagem abaixo em `diagrams/diagrama_de_classe.jpg`:

![Diagrama de Classe](diagrams/diagrama_de_classe.jpg)

### Modelo Entidade-Relacionamento (MER)
Coloque a imagem abaixo em `diagrams/modelo_entidade_relacionamento.jpg`:

![Modelo Entidade-Relacionamento](diagrams/modelo_entidade_relacionamento.jpg)

---

## 🚀 Começando

### ✅ Pré-requisitos
- **Java (JDK) 11** ou superior  
- **Maven 3.6** ou superior  
- **MySQL 8.0**

> Caso ainda não tenha, instale o JDK, Maven e MySQL conforme seu sistema operacional.

---

## ⚙️ Instalação e Execução

### 1) Clone o repositório
```bash
git clone https://github.com/gugapromaster/NOME-DO-SEU-REPOSITORIO.git
cd NOME-DO-SEU-REPOSITORIO
```

### 2) Configure o Banco de Dados
- Acesse seu cliente MySQL preferido (Workbench, DBeaver, etc.).
- Crie o banco executando o comando que está **no início** do arquivo `script.sql`:
```sql
CREATE DATABASE IF NOT EXISTS imobiliaria_db;
```
- Em seguida, execute **todo** o conteúdo do arquivo `script.sql` para criar as tabelas e **popular** o banco com dados de exemplo.

### 3) Configure a conexão JDBC
Edite o arquivo `src/main/java/com/example/imobiliaria/database/ConnectionFactory.java` e **altere** as credenciais:

```java
private static final String USER = "seu_usuario_aqui";
private static final String PASSWORD = "sua_senha_aqui";
```

> Se necessário, ajuste também **URL**, **porta** e **nome do banco** conforme seu ambiente.

### 4) Compile o projeto com Maven
```bash
mvn compile
```

### 5) Execute a aplicação (Main)
```bash
mvn exec:java -Dexec.mainClass="com.example.imobiliaria.Main"
```

---

## 💻 Como Usar

Ao executar o programa, um **menu interativo** será exibido no terminal. Digite o número da opção desejada e pressione **Enter**.

```
--- Sistema de Gestão Imobiliária ---
1. Cadastrar Imóvel
2. Cadastrar Cliente
3. Cadastrar Contrato de Aluguel
--- Relatórios ---
4. Listar Imóveis Disponíveis
5. Listar Contratos Ativos
6. Listar Clientes com Mais Contratos
7. Listar Contratos Expirando em 30 dias
0. Sair

Escolha uma opção:
```

---

## 📤 Exportar para Planilhas (opcional)

Caso o projeto inclua utilitários de **exportação**, você pode:
- Exportar relatórios (imóveis disponíveis, contratos ativos, ranking de clientes, contratos a vencer) em **CSV**.
- Salvar os arquivos na pasta `exports/` e abrir no **Excel**, **LibreOffice** ou **Google Planilhas**.

> Se o módulo de exportação ainda **não** existir, crie um método de relatório que grave o resultado em CSV usando `PrintWriter`/`BufferedWriter`, separado por vírgulas (`;` se preferir formato BR).

---

## 📁 Estrutura sugerida do projeto

```
src/
 └── main/
     └── java/com/example/imobiliaria/
         ├── Main.java
         ├── database/
         │   └── ConnectionFactory.java
         ├── dao/
         │   ├── ClienteDAO.java
         │   ├── ImovelDAO.java
         │   └── ContratoDAO.java
         ├── model/
         │   ├── Cliente.java
         │   ├── Imovel.java
         │   └── Contrato.java
         └── report/
             └── Relatorios.java
resources/
 └── script.sql
diagrams/
 ├── diagrama_de_classe.jpg
 └── modelo_entidade_relacionamento.jpg
```

---

## ✒️ Autor

Feito por **[Gustavo do Rosário Nunes]**  
- LinkedIn: https://www.linkedin.com/in/SEU-USUARIO  
- GitHub: [https://github.com/gugapromaster](https://github.com/gugapromaster)

---

## 📄 Licença

Este projeto está sob a licença **MIT**. Consulte o arquivo `LICENSE` para mais detalhes.

> Dica: se ainda não existir, crie um arquivo `LICENSE` na raiz do projeto e cole o texto da licença MIT.

---

## 🧩 Notas Finais

- **Substitua os placeholders**: `NOME-DO-SEU-REPOSITORIO`, seu **nome completo** e, se quiser, o link do **LinkedIn**.  
- **Garanta** que as imagens dos diagramas (`diagrama_de_classe.jpg` e `modelo_entidade_relacionamento.jpg`) estejam na pasta `diagrams/`.  
- Ajuste nomes de pacotes se necessário (ex.: `com.example.imobiliaria`).  
