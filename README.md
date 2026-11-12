
# API REST - Gestão de Estoque

## Sobre o projeto

Este projeto consiste em uma API REST para gerenciamento de produtos de um estoque. A aplicação permite cadastrar, consultar, atualizar e excluir produtos.

## Tecnologias utilizadas

- Java 25
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate
- Banco de dados H2
- Maven

## Estrutura do projeto

- **model:** representa a entidade Produto.
- **repository:** responsável pelo acesso ao banco de dados.
- **service:** contém as regras de negócio.
- **controller:** disponibiliza os endpoints da API.

## Entidade Produto

A entidade possui os seguintes campos:

| Campo | Tipo | Descrição |
|---|---|---|
| id | Long | Identificador do produto |
| codigo | String | Código único do produto |
| nome | String | Nome do produto |
| preco | BigDecimal | Preço do produto |
| quantidadeEstoque | Integer | Quantidade disponível |
| categoria | String | Categoria do produto |
| dataCriacao | LocalDateTime | Data de criação |

## Como executar o projeto

1. Abra o terminal na pasta principal do projeto.
2. Execute o comando:

```bash
.\mvnw.cmd spring-boot:run
```

3. Aguarde a aplicação iniciar.
4. A API estará disponível em:

```text
http://localhost:8080
```

## Endpoints disponíveis

| Método | Endpoint | Descrição |
|---|---|---|
| GET | /api/produtos | Lista todos os produtos |
| GET | /api/produtos/{id} | Busca produto por ID |
| POST | /api/produtos | Cadastra um produto |
| PUT | /api/produtos/{id} | Atualiza um produto |
| DELETE | /api/produtos/{id} | Exclui um produto |

## Exemplo de cadastro

**POST** `/api/produtos`

```json
{
  "codigo": "PROD005",
  "nome": "Teclado Mecânico",
  "preco": 250.00,
  "quantidadeEstoque": 15,
  "categoria": "Periféricos"
}
```

## Validações e respostas HTTP

- **200 OK:** consulta ou atualização realizada.
- **201 Created:** produto cadastrado.
- **204 No Content:** produto excluído.
- **400 Bad Request:** dados inválidos.
- **404 Not Found:** produto não encontrado.
- **409 Conflict:** código de produto já cadastrado.

## Banco de dados

O projeto utiliza o banco H2 em memória.

Para acessar o console:

```text
http://localhost:8080/h2-console
```

Configurações:

- JDBC URL: `jdbc:h2:mem:estoque`
- User Name: `sa`
- Password: deixar em branco