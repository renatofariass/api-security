# **API Security**

Este é um projeto de exemplo em Java Spring que utiliza o Spring Security para controle de autenticação e JWT para autenticação de usuários. O projeto também utiliza o Flyway Migrations para gerenciar as migrações de banco de dados e o PostgreSQL como banco de dados.

## **Pré-requisitos**

Certifique-se de ter o seguinte instalado em sua máquina antes de iniciar:

- Java JDK 17
- PostgreSQL (se quiser uma interface para gerenciar os seus banco de dados instale o Dbeaver ou PG Admin)
- Git
- Intellij, Eclipse ou VSCode
- Rest Client (Postman ou Imnsonia)

## **Clonando o Projeto**

Para clonar este projeto, execute o seguinte comando no seu terminal:

```bash
git clone https://github.com/renatofariass/api-security.git
```

Com o projeto clonado, abra ele na IDE.

## **Configurando o Banco de Dados**

1. Crie um banco de dados PostgreSQL com o nome que você preferir (mas lembre-se de atualizar as configurações no arquivo **`application-dev.yml`**).
    
    ```yaml
    spring:  
        datasource:
            url: jdbc:postgresql://localhost:5432/nome-do-seu-banco # altere aqui o nome do banco
            username: ${USERNAME}    
            password: ${PASSWORD}
    ```
    
2. Certifique-se de que o PostgreSQL esteja em execução.
3. As configurações do banco de dados estão no arquivo **`src/main/resources/application-dev.yml`**. Verifique se as informações de conexão estão corretas para o seu ambiente.

## Observações:

1. Antes de iniciar o projeto, você precisa configurar as variáveis de ambiente que estão no **`src/main/resources/application-dev.yml`**. Coloque em **`${USERNAME}`** seu username configurado no postgres, em **`${PASSWORD}`** sua senha configurada no postgres e em **`${SECRET-KEY}`** uma chave qualquer, ex: 123.

## **Autenticação**

A autenticação na aplicação é controlada pelo Spring Security e utiliza tokens JWT. Existem duas roles de autenticação:

- **`USER`**: Permite acesso padrão para usuários autenticados.
- **`ADMIN`**: Permite acesso padrão e também criar produtos.

## **Endpoints**

Aqui estão os endpoints disponíveis na aplicação:

- **`GET /product`**: Recupera uma lista de todos os produtos (acesso permitido para todos os usuários autenticados).
- **`POST /product`**: Registra um novo produto (requer acesso de ADMIN).
- **`POST /auth/login`**: Realiza o login na aplicação.
- **`POST /auth/register`**: Registra um novo usuário na aplicação com a role “USER” por default.

## **Exemplo de Autenticação**

Para acessar os endpoints protegidos, você precisa primeiro obter um token JWT. Faça uma solicitação POST para **`/auth/login`** com as credenciais de um usuário registrado para receber um token. Em seguida, no postman ou imsonia, inclua o token no cabeçalho das solicitações para os endpoints protegidos, exemplo de como fazer no postman:

1. Vá até a aba Authorization.
2. Selecione o type “Bearer token”.
3. Coloque o token recebido pelo endpoint  **`/auth/login`** no token.

## **Iniciando a Aplicação**

Para iniciar a aplicação, siga estas etapas:

1. Abra o projeto na IDE da sua preferência
2. Faça o download das dependências do Maven
3. Aperte no botão de “play” na sua IDE.

A url base da aplicação será: **`http://localhost:8080`**.

Assim que você iniciar o projeto e as migrations do flyway já estiver no postgres, você precisa criar um usuário ADMIN manualmente no banco de dados, pois não é possível criar um usuário com permissões de ADMIN pelo endpoint **`/auth/register`.**

- Use um UUID generator para criar um id: [**UUID Generator**](https://www.uuidgenerator.net/)
- Use um Bcrypt generator para encriptar sua senha: [**Bcrypt Generator**](https://bcrypt.online/)
- No campo de “role” para definir um usuário como ADMIN use **`“0”`**, pois como é um Enum ele reconhecerá um ADMIN como **`“0”`** e USER como **`”1”`**
- Ex de um usuário ADMIN:
    
    ```sql
    insert into users (id, login, password, role)
    values ("bfbc76be-4f78-11ee-be56-0242ac120002", "email@admin.com", 
    "$2y$10$yJSM25Zp3FO7lrJ1gS4f2O4W3/MCW6G6gFsIIe/zTtQjFhOo6TSO6", "0");
    ```
    

Aproveite o uso deste projeto de exemplo em Java Spring!
