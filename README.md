# Literalura

Literalura é uma aplicação Java para consulta, registro e visualização de livros e autores, integrando com a API Gutendex. O projeto permite buscar livros por título, idioma e autor, além de registrar e listar livros salvos em um banco de dados local.

## Funcionalidades
- Buscar livros pela API Gutendex
- Registrar livros e autores no banco de dados
- Listar livros e autores registrados
- Filtrar livros por idioma

## Tecnologias Utilizadas
- Java 17+
- Spring Boot
- JPA/Hibernate
- API Gutendex

## Como Executar

1. Clone o repositório:
   ```bash
   git clone <url-do-repositorio>
   ```
2. Acesse a pasta do projeto:
   ```bash
   cd literalura
   ```
3. Execute o projeto com Maven:
   ```bash
   ./mvnw spring-boot:run
   ```
   Ou, se preferir, gere o JAR:
   ```bash
   ./mvnw package
   java -jar target/literalura-0.0.1-SNAPSHOT.jar
   ```

## Executando com Docker

O Docker Compose é executado automaticamente ao rodar o projeto com Maven (`./mvnw spring-boot:run`), graças a uma dependência configurada no projeto. Portanto, não é necessário rodar manualmente o comando `docker compose up --build` — as imagens Docker serão construídas e os containers iniciados automaticamente.

Ao finalizar a aplicação, o comando `docker compose down` também é executado automaticamente, removendo os containers criados.

A aplicação será inicializada conforme definido no arquivo `compose.yaml`.

## Configuração

- As configurações do banco de dados e outras variáveis estão em `src/main/resources/application.properties`.

## Configuração do Ambiente (.env)

Antes de executar a aplicação, é necessário configurar as variáveis de ambiente:

1. Copie o arquivo `.env.example` para `.env` na raiz do projeto.
2. Preencha a variável `POSTGRES_PASSWORD` com a senha desejada para o banco de dados PostgreSQL.
   - Exemplo: `POSTGRES_PASSWORD=minha_senha_secreta`
   - Não utilize aspas, apenas a senha.
3. O arquivo `.env` não deve ser adicionado ao controle de versão (git) por questões de segurança.

## Estrutura do Projeto
- `src/main/java/com/one/literalura/` - Código-fonte principal
- `src/main/resources/` - Arquivos de configuração e templates

## Créditos
Desenvolvido por Matheus.

---
Sinta-se à vontade para contribuir ou sugerir melhorias!
