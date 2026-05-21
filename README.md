# Cadastro de Palestras

API REST para gerenciamento de palestras, eventos, palestrantes e usuários. Desenvolvida com Java e Spring Boot, seguindo arquitetura em camadas e boas práticas de desenvolvimento back-end.

---

## Tecnologias

- Java 21
- Spring Boot 4.0.6
- Spring Data JPA / Hibernate
- Lombok
- Jakarta Validation
- Maven
- H2 Database (banco em memória)

---

## Banco de Dados

O projeto utiliza **H2 em memória** — um banco de dados relacional leve que não requer instalação de nenhum serviço externo. Ele sobe automaticamente junto com a aplicação e é zerado a cada reinicialização.

Nenhuma configuração adicional é necessária para rodar o projeto localmente.

---

## Arquitetura

O projeto segue o padrão de camadas **Controller → Service → Repository**, com separação clara de responsabilidades:

```
controller/   → Endpoints REST (recebem e respondem requisições HTTP)
service/      → Regras de negócio e orquestração
repository/   → Acesso ao banco de dados via Spring Data JPA
model/        → Entidades JPA mapeadas para tabelas
```

---

## Modelagem de Dados

| Entidade     | Descrição                                              |
|--------------|--------------------------------------------------------|
| `Usuario`    | Usuário base do sistema                                |
| `Organizador`| Estende `Usuario`; responsável por eventos ativos      |
| `Palestrante`| Especialista vinculado a uma ou mais palestras         |
| `Evento`     | Contém local, data, hora e duração; agrupa palestras   |
| `Palestra`   | Entidade central; relaciona evento e palestrantes      |

**Relacionamentos:**
- `Organizador` herda de `Usuario` via `SINGLE_TABLE` (herança JPA)
- `Palestra` → `Evento`: `@ManyToOne`
- `Palestra` ↔ `Palestrante`: `@ManyToMany` bidirecional

---

## Endpoints

Base path: `/cadastropalestras`

| Método   | Rota                        | Descrição                        |
|----------|-----------------------------|----------------------------------|
| `GET`    | `/palestras`                | Lista todas as palestras         |
| `POST`   | `/palestras`                | Cadastra uma nova palestra       |
| `PUT`    | `/palestras/{id}`           | Atualiza uma palestra            |
| `DELETE` | `/palestras/{id}`           | Remove uma palestra              |
| `GET`    | `/palestrantes`             | Lista todos os palestrantes      |
| `POST`   | `/palestrantes`             | Cadastra um novo palestrante     |
| `PUT`    | `/palestrantes/{id}`        | Atualiza um palestrante          |
| `DELETE` | `/palestrantes/{id}`        | Remove um palestrante            |
| `GET`    | `/eventos`                  | Lista todos os eventos           |
| `POST`   | `/eventos`                  | Cadastra um novo evento          |
| `PUT`    | `/eventos/{id}`             | Atualiza um evento               |
| `DELETE` | `/eventos/{id}`             | Remove um evento                 |
| `GET`    | `/usuarios`                 | Lista todos os usuários          |
| `POST`   | `/usuarios`                 | Cadastra um novo usuário         |
| `PUT`    | `/usuarios/{id}`            | Atualiza um usuário              |
| `DELETE` | `/usuarios/{id}`            | Remove um usuário                |
| `GET`    | `/organizadores`            | Lista todos os organizadores     |
| `POST`   | `/organizadores`            | Cadastra um novo organizador     |
| `PUT`    | `/organizadores/{id}`       | Atualiza um organizador          |
| `DELETE` | `/organizadores/{id}`       | Remove um organizador            |

---

## Destaques de Implementação

- **Resolução de entidades relacionadas no cadastro de palestras:** ao criar uma `Palestra`, o service valida e carrega os objetos completos de `Evento` e `Palestrante` do banco antes de persistir, evitando dados inconsistentes.
- **Deleção segura de palestras:** a remoção limpa manualmente as associações bidirecionais (`@ManyToMany` e `@OneToMany`) antes de deletar, prevenindo erros de integridade referencial.
- **Injeção de dependência via construtor** em todos os componentes, seguindo boas práticas do Spring.

---

## Como Executar

```bash
# Clone o repositório
git clone https://github.com/lucazcode/CadastroDePalestras.git

# Acesse o diretório
cd CadastroDePalestras/CadastroDePalestras

# Execute com Maven
./mvnw spring-boot:run
```

A API estará disponível em `http://localhost:8080/cadastropalestras`.

---

## Exemplo de Requisição

**Cadastrar uma palestra:**

```http
POST /cadastropalestras/palestras
Content-Type: application/json

{
  "nome": "Introdução ao Spring Boot",
  "tema": "Back-end",
  "local": "Auditório A",
  "data": "2025-09-10",
  "hora": "14:00",
  "duracao": 90,
  "evento": { "id": 1 },
  "palestrantes": [{ "id": 2 }, { "id": 3 }]
}
```

---

## Autor

Desenvolvido por **Lucas** — [github.com/lucazcode](https://github.com/lucazcode)
