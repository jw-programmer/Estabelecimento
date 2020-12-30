# Crud Rest de Estabelecimentos e funcionários

# Sobre

Esta API é um MPV de um crud de estabelecimentos e funcionários feita em Spring Boot com java.

# Configuracao

A aplicação tem dois perfis de teste, dev e test. Dev se conecta com um banco de dados postgresql e test se conecta com um banco h2 em memória, configurado para ser populado com dados automáticamente.

Para se conectar com o postgresql, prencha as informações em **application-dev.properties**:

```proprerties
spring.jpa.hibernate.ddl-auto=<preencha com 'create' para primeira execução para criar as tabelas. Depois preencha com 'none' quando não quiser mais mudanças>
spring.datasource.url=<url do banco de dados postgresql, exemplo: jdbc:postgresql://localhost:5432/estabelecimentos>
spring.datasource.username=<usuario do banco exemplo: postgres>
spring.datasource.password=<senha do banco, exemplo: admim>
```

## Endpoints

A aplicação possui 2 endpoints REST

- estabelecimentos/{id}
- profissionais/{id}

Cada um dos seus verbos http realiza a operação crud desejada, conforme as praticas REST.

O formato esperado de json para estabelecimento é:

```json
{
  "id": 4,
  "nome": "Lojas Americanas",
  "endereco": "Centro",
  "telefone": "33333",
  "profissionais": []
}
```

O formato esperado de json para profissional é:

```json
{
  "nome": "Queiroz",
  "endereco": "Rua 15, mondubim",
  "telefoneCelular": "85985698",
  "funcao": "Zelador",
  "telefoneResidencial": "565565151"
}
```

Para adicionar novos profissionais aos estabelecimentos, basta colocar seus objetos na lista "profissionais" do estabelecimento e pedir para ser atualizado (Put)

Os profissinais possuem também o seguinte endpoint:

* profissionais/search?nome=...

Com esta url, você pode pesquisar por profissionais pelo nome.
