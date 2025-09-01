# Empresa-API

Este é um projeto de API RESTful desenvolvido com **Spring Boot**, que simula um sistema de gerenciamento para uma empresa, com entidades como Funcionários, Produtos, Clientes, Fornecedores e Departamentos.

## Tecnologias Utilizadas

* **Java 17**
* **Spring Boot 3.2.0**
* **Spring Data JPA**
* **Spring-boot-starter-test**
* **H2 Database** (Banco de dados em memória para desenvolvimento)
* **Maven** (Gerenciador de dependências)
* **Lombok** (Para simplificar a criação de modelos)

## Como Rodar o Projeto

1.  **Pré-requisitos:** Certifique-se de ter o **JDK 17** instalado e configurado em sua máquina.
2.  **Clone o repositório:**
3.  **Execute a aplicação:**
    Use o Maven Wrapper para iniciar a aplicação diretamente do terminal.
    ```bash
    ./mvnw spring-boot:run
    ```
    A aplicação será iniciada na porta `8081`.

## Endpoints da API

A API expõe endpoints REST para as seguintes entidades, com operações de CRUD (Create, Read, Update, Delete):

* **Funcionários:** `http://localhost:8081/api/funcionarios`
* **Produtos:** `http://localhost:8081/api/produtos`
* **Clientes:** `http://localhost:8081/api/clientes`
* **Fornecedores:** `http://localhost:8081/api/fornecedores`
* **Departamentos:** `http://localhost:8081/api/departamentos`

## Banco de Dados H2

Para acessar o console web do banco de dados H2, utilize a seguinte URL após iniciar a aplicação:

* **URL:** `http://localhost:8081/h2-console`
* **JDBC URL:** `jdbc:h2:mem:minhaempresa`
* **Usuário:** `sa`
* **Senha:** `password`

## Testes

Os testes de integração da API podem ser executados com o seguinte comando Maven:
```bash
./mvnw test
