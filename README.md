# Baozi Store – API REST

Trabalho de **Desenvolvimento Web Back-End** (Uninter)
Aluno: **Abimael de Menezes Pedro – RU 5278234**

API REST simples para a Baozi Store (loja de pão chinês) com cadastro de **clientes**, **produtos** e **pedidos**.

## Tecnologias
- Java 17
- Spring Boot 3.3 (Spring Web / MVC)
- Spring Data JPA
- Banco relacional H2 (padrão) – com opção de MySQL
- JSON em todos os endpoints
- Testes com Postman

## Estrutura (MVC)
```
src/main/java/com/baozi/store
├── BaoziStoreApplication.java
├── model        -> Cliente, Produto, Pedido (entidades JPA)
├── repository   -> ClienteRepository, ProdutoRepository, PedidoRepository
└── controller   -> ClienteController, ProdutoController, PedidoController
```

## Como executar
Pré-requisito: JDK 17 ou superior.

**Pelo IntelliJ / Eclipse / VS Code:** abra a pasta do projeto (é um projeto Maven) e execute a classe `BaoziStoreApplication`.

**Pelo terminal (com Maven instalado):**
```
mvn spring-boot:run
```

A API sobe em `http://localhost:8080`.
Console do banco H2: `http://localhost:8080/h2-console` (JDBC URL `jdbc:h2:mem:baozidb`, usuário `sa`, senha em branco). O banco é em memória: os dados são apagados ao reiniciar a aplicação.

Para usar MySQL, descomente a dependência `mysql-connector-j` no `pom.xml` e siga os comentários em `src/main/resources/application.properties`.

## Endpoints

| Método | Rota | Descrição |
|---|---|---|
| POST | /clientes | Cria cliente |
| GET | /clientes | Lista clientes |
| GET | /clientes/{id} | Busca cliente por ID |
| PUT | /clientes/{id} | Atualiza cliente (opcional) |
| DELETE | /clientes/{id} | Apaga cliente |
| POST | /produtos | Cria produto |
| GET | /produtos | Lista produtos |
| GET | /produtos/{id} | Busca produto por ID |
| PUT | /produtos/{id} | Atualiza produto (opcional) |
| DELETE | /produtos/{id} | Apaga produto |
| POST | /pedidos | Cria pedido |
| GET | /pedidos | Lista pedidos |
| GET | /pedidos/{id} | Busca pedido por ID |
| PUT | /pedidos/{id} | Atualiza pedido (opcional) |
| DELETE | /pedidos/{id} | Apaga pedido |

### Exemplos de JSON
Cliente:
```json
{ "nome": "Abimael5278234", "clienteDesde": "2026-11-25" }
```
Produto:
```json
{ "nome": "Baozi de Porco", "preco": 6.50, "estoque": true }
```
Pedido:
```json
{ "clienteId": 1, "produtoId": 1, "quantidade": 12 }
```

## Postman
Importe o arquivo `postman/BaoziStore.postman_collection.json` no Postman (File → Import). Todas as requisições já estão prontas.
