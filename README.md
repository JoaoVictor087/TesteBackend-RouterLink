# Teste Backend Java - RouterLink

## Introdução

Essa API teve como finalidade demonstrar conhecimento técnico para o teste de estágio da RouterLink.
Foi feito uma API Rest com métodos CRUD que se baseiam em cadastro de pessoas e endereços relacionados.
A ideia foi seguir padrões profissionais da indústria para melhor garantir a melhor estrutura possível.
Mas ainda assim, foi determinado algumas regras negócios fora do ambito do pdf. Como por exemplo,
é possível adicionar endereços a um usuário tanto diretamente, numa única requisição POST:
`POST` `/pessoas`

```json
{
  "nome": "João Silva",
  "cpf": "12345678900",
  "email": "joao.silva@email.com",
  "dataNascimento": "1990-05-15",
  "telefones": [
    {
      "numeroTelefone": "11988887777"
    },
    {
      "numeroTelefone": "11333344442"
    }
  ],
  "enderecos": [
    {
      "logradouro": "Avenida Paulista",
      "numero": 1000,
      "complemento": "Apto 12",
      "bairro": "Bela Vista",
      "cidade": "São Paulo",
      "uf": "SP",
      "cep": "01310-100",
      "principal": true
    },
    {
      "logradouro": "Rua das Flores",
      "numero": 50,
      "complemento": "Casa",
      "bairro": "Centro",
      "cidade": "Campinas",
      "uf": "SP",
      "cep": "13010-000",
      "principal": false
    }
  ]
}
```

Ou em diferente separadamente:

`POST` `/pessoas`

```json
{
  "nome": "João Silva",
  "cpf": "00000000000",
  "email": "joao.silva@email.com",
  "dataNascimento": "1990-05-15"
}
```

`POST` `/pessoas/{id}/enderecos`

```json
{
  "logradouro": "Rua Exemplo",
  "numero": 123,
  "complemento": "Apto 45",
  "bairro": "Centro",
  "cidade": "São Paulo",
  "cep": "01001-000",
  "uf": "SP",
  "principal": true
}
```

Além disso, foi utilizado conceitos básicos para formato das informações,Por exemplo, CPF é apenas digitos 12345678910
(sem caracteres especiais),
ao invés de fazer um método _parser_ para transformar ´123456789-10´ em ´12345678910´. Foi utilizado esse conceito com o
intuito de priorizar racíocinio lógico e estruturação do programa ao invés de fazer um método totalmente viável para
produção.

***Não foi utilizado Inteligência Artificial para gerar código nesse projeto.***

## Stacks

```
SpringBoot : 4.0.4
Dependências: H2 Database, Spring Data JPA , Spring Web e Swagger.
Java: 21
```

## Como executar o programa

```
git clone https://github.com/JoaoVictor087/TesteBackend-RouterLink.git
cd TesteBackend-RouterLink
./gradlew bootRun
localhost:8080/swagger-ui/index.html
```

## Endpoints

Documentação completa da API disponível em:
http://localhost:8080/swagger-ui/index.html

### Pessoas

`POST /pessoas`
*Descrição*: Criar uma nova pessoa. Somente os campos CPF e Nome são obrigatórios.

**Request Body:**

```json
{
  "nome": "João Victor",
  "cpf": "55566677522",
  "email": "email@email.com"
}
```

**Response:** `201 Created`

```json
{
  "email": "joao.silva@email.com",
  "nome": "João Silva",
  "id": 5,
  "dataNascimento": "1990-05-15",
  "telefones": [],
  "enderecos": []
}
```

---

`PUT /pessoas/{id_pessoa}`

*Descrição*: Atualizar Pessoa. Uma vez que esteja criado, qualquer campo é opcional nesse método.

**Request Body:**

```json

{
  "nome": "João Silva",
  "cpf": "12345678900",
  "email": "joao.silva@email.com",
  "dataNascimento": "1995-05-15",
  "telefones": [
    {
      "numeroTelefone": "988889777"
    }
  ],
  "enderecos": [
    {
      "logradouro": "Rua das Flores",
      "numero": "123",
      "bairro": "Centro",
      "cidade": "São Paulo",
      "uf": "SP",
      "cep": "01001000"
    }
  ]
}
```

***Response:*** `200 OK`

```json

{
  "email": "joao.silva@email.com",
  "nome": "João Silva",
  "id": 5,
  "dataNascimento": "1990-05-15",
  "telefones": [],
  "enderecos": []
}
```

---

`GET /pessoas`
Descrição: Retorna todas as pessoas no banco, de forma páginada.

***Request Body:***

```json

```

***Response:*** `200 OK`

```json
{
  "content": [
    {
      "email": "joao.silva@email.com",
      "nome": "João Silva",
      "id": 4,
      "dataNascimento": "1990-05-15",
      "telefones": [],
      "enderecos": []
    },
    {
      "email": "joao.silva@email.com",
      "nome": "João Silva",
      "id": 5,
      "dataNascimento": "1990-05-15",
      "telefones": [],
      "enderecos": []
    }
  ],
  "empty": false,
  "first": true,
  "last": true,
  "number": 0,
  "numberOfElements": 2,
  "pageable": {
    "offset": 0,
    "pageNumber": 0,
    "pageSize": 20,
    "paged": true,
    "sort": {
      "empty": true,
      "sorted": false,
      "unsorted": true
    },
    "unpaged": false
  },
  "size": 20,
  "sort": {
    "empty": true,
    "sorted": false,
    "unsorted": true
  },
  "totalElements": 2,
  "totalPages": 1
}

```

---

`GET /pessoas/{id_pessoa}`
Descrição: Retorna uma pessoa no banco, especificamente.

***Request Body:***
```json

```

***Response:*** `200 OK`

```json
{
  "email": "joao.silva@email.com",
  "nome": "João Silva",
  "id": 5,
  "dataNascimento": "1990-05-15",
  "telefones": [],
  "enderecos": []
}
```
---

`DELETE /pessoas/{id_pessoa}`
Descrição: Deleta uma pessoa especifica.

***Resquest Body:***
```json

```

***Response:*** `204 No Content`
```

```
___


### Enderecos

`POST /pessoas/{id_pessoa}/enderecos`
Descrição: Adiciona um endereco a uma pessoa.

***Request Body***:
```json
{
  "logradouro": "Avenida Paulista",
  "numero": 1500,
  "complemento": "Apto 42",
  "bairro": "Bela Vista",
  "cidade": "São Paulo",
  "cep": "01310200",
  "uf": "SP",
  "principal": true
}
```

***Response:*** `201 Created`
```json
{
    "complemento": "Apto 45",
    "bairro": "Centro",
    "cidade": "São Paulo",
    "uf": "SP",
    "cep": "01001-000",
    "principal": true,
    "numero": 123,
    "id": 7,
    "logradouro": "Rua Exemplo"
}
```
---
`PUT /pessoas/{id_pessoa}/enderecos/{id_endereco}`
Descrição: Atualiza um endereço de uma pessoa. É possível atualizar todos os campos ou apenas um.

***Request Body***:
```json
{
  "logradouro": "Avenida Paulista",
  "numero": 1500,
  "complemento": "Apto 42",
  "bairro": "Bela Vista",
  "cidade": "São Paulo",
  "cep": "01310200",
  "uf": "SP",
  "principal": true
}
```

***Response:*** `200 OK`
```json
{
    "complemento": "Apto 45",
    "bairro": "Centro",
    "cidade": "São Paulo",
    "uf": "SP",
    "cep": "01001-000",
    "principal": true,
    "numero": 123,
    "id": 7,
    "logradouro": "Rua Exemplo"
}
```
---
`DELETE /pessoas/{id_pessoa}/enderecos/{id_enredeco}`
Descrição: Deleta apenas um endereço de uma pessoa.

***Request Body***:
```json
{
  
}
```

***Response:*** `204 No Content`
```json
{
    
}
```


