# Teste Prático Fullstack Java

Desenvolver um sistema com um catálogo de veículos à venda.

## Descrição

Este projeto é parte de um teste prático Fullstack Java e consiste no desenvolvimento de um sistema com um catálogo de veículos à venda. O objetivo é permitir que os usuários visualizem, filtrem pelo preço(descendente ou ascendente) e gerenciem os veículos disponíveis. O desenvolvimento foi realizado de forma Fullstack, utilizando Java com Spring Boot para o backend e React para o frontend.

## Instalação

### Requisitos

- Java 17 +
- Maven
- Node 20 +
- AWS CLI
- MySQL 8 +


### Para a Instalação

#### Java

#### Instalar o Java:

- **Java (JDK 21):** Baixe e instale o Java Development Kit (JDK) a partir do site oficial da Oracle: [Oracle JDK](https://www.oracle.com/java/technologies/downloads/#java21)

#### Verifique a instalação:

```bash
    java --version

```

#### Instalar o Maven:

- **Maven:** Baixe e instale o Maven a partir do site oficial do Apache Maven: [Apache Maven](https://maven.apache.org/download.cgi?.)

#### Verifique a instalação:

```bash
    mvn --version

```

#### Node

#### Instalar o Node:

- **Node (v20.16.01):** Baixe e instale o Node.js a partir do site oficial: [Node.js](https://nodejs.org/pt)

#### Verifique a instalação:

```bash
    node --version
    npm --version
```

#### AWS CLI

#### Instalar o AWS CLI:

- **AWS CLI:** Siga as instruções no site oficial da AWS: [AWS CLI Installation](https://docs.aws.amazon.com/cli/latest/userguide/getting-started-install.html)

#### Configure o AWS CLI:
```bash
    aws configure

```

#### Verfique a instalação:

```bash
    aws --version

```

#### MySQL

#### Instalar o MySQL:

- Execute o instalador e selecione [MySQL](https://dev.mysql.com/downloads/installer/) e [MySQL Workbench](https://www.mysql.com/products/workbench/) para instalação.
- Siga as instruções do instalador para concluir a instalação.


## Instalação Obrigatória

Para garantir o funcionamento completo do projeto, algumas instalações são necessárias:

- **Java:** O Java é fundamental para rodar o backend desenvolvido com Spring Boot. Ele é a base do servidor que gerencia a lógica de negócios e a comunicação com o banco de dados.
- **Maven:** O Maven é uma ferramenta de gerenciamento e automação de build para projetos Java. Ele é utilizado para gerenciar as dependências do projeto, compilar o código, executar testes e criar o pacote final da aplicação.
- **Node.js:** O Node.js é necessário para rodar o frontend desenvolvido com React. Ele permite a execução do ambiente de desenvolvimento e das ferramentas associadas ao React.
- **AWS CLI:** O AWS CLI é necessário para configurar o acesso ao serviço de armazenamento S3 da AWS. Isso é essencial para o upload e gerenciamento das fotos dos veículos, garantindo que o serviço utilize as configurações padrão do seu ambiente local.
- **MySQL:** O MySQL é o banco de dados escolhido para a persistência dos dados. Ele armazena todas as informações sobre os veículos e usuários, permitindo consultas e operações eficientes.

Estas ferramentas e configurações são essenciais para o correto funcionamento e integração de todas as partes do sistema.

### Versões que utilizo:

- **Java:** 21.0.3 2024-04-16 LTS
- **Apache Maven**: Apache Maven 3.8.8 
- **Node.js**: v20.13.1
- **NPM**: 10.5.2
- **AWS CLI**: aws-cli/2.17.6 
- **MySQL**: 8.0.36 


## Configurações do Projeto

Para configurar e executar o projeto corretamente, siga os passos abaixo:

**1. Clone o Repositório:**

   Primeiro, clone o repositório do projeto para o seu ambiente local. Use o seguinte comando para clonar o repositório:

   ```bash
   git clone https://github.com/viniciuslemes10/Verzel-API.git
   ```

**2. Abra o Projeto na sua IDE:**

Após clonar o repositório, abra o projeto na sua IDE de preferência (por exemplo, IntelliJ IDEA, Eclipse, VSCode).

**3. Localize o Arquivo application.properties:**

Navegue até o arquivo de configuração application.properties. Ele normalmente está localizado no seguinte caminho:

```bash
  ${SEU_CAMINHO}\Verzel-API\verzel\src\main\resources
   ```

- Certifique-se de que o [MySQL](https://dev.mysql.com/downloads/installer/) esteja instalado.

Substitua ${SEU_CAMINHO} pelo caminho real do diretório onde o projeto foi clonado.

Dentro do arquivo application.properties, localize a configuração do MySQL e ajuste as propriedades conforme suas credenciais e configuração do banco de dados: 

```bash
    spring.datasource.url=jdbc:mysql://localhost:3306/catalogo_veiculos?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC
    spring.datasource.username=${SEU_USERNAME}
    spring.datasource.password=${SUA_PASSWORD}
````

### Configuração Backend (Java):

- Certifique-se de que o [Java JDK](https://www.oracle.com/java/technologies/downloads/#java21) e o [Maven](https://maven.apache.org/download.cgi?.) estão instalados.

 - Navegue até o diretório onde o arquivo `pom.xml` do backend está localizado. Esse arquivo está na raiz do diretório do backend. O caminho será algo como:

     ```bash
     ${SEU_CAMINHO}\Verzel-API\verzel
     ```

     Certifique-se de substituir `${SEU_CAMINHO}` pelo caminho real onde o projeto foi clonado.

   - Abra um terminal ou prompt de comando e navegue até esse diretório. Você pode usar o seguinte comando para mudar para o diretório correto:

     ```bash
     cd ${SEU_CAMINHO}\Verzel-API\verzel
     ```

   - Instale as dependências do projeto usando Maven com o comando:

     ```bash
     mvn install
     ```

    - Após a instalação das dependências, inicie o backend com o comando:

     ```bash
     mvn spring-boot:run -DskipTest
     ```

Isso iniciará o servidor backend e a aplicação Spring Boot estará pronta para uso.

As dependências que estou utilizando são:

- **Spring Boot Starter Data JPA**: Fornece suporte para a integração com o Spring Data JPA, facilitando a persistência de dados.
  - `org.springframework.boot:spring-boot-starter-data-jpa`

- **Spring Boot Starter Web**: Inclui as dependências básicas para desenvolver uma aplicação web com Spring Boot.
  - `org.springframework.boot:spring-boot-starter-web`

- **MySQL Connector/J**: Driver JDBC para conexão com o banco de dados MySQL.
  - `com.mysql:mysql-connector-j`

- **Spring Boot Starter Test**: Inclui as dependências necessárias para realizar testes unitários e de integração.
  - `org.springframework.boot:spring-boot-starter-test`

- **Flyway Core**: Ferramenta para migrações de banco de dados, garantindo que o esquema do banco de dados esteja atualizado.
  - `org.flywaydb:flyway-core`

- **Flyway MySQL**: Extensão do Flyway para suporte específico ao MySQL.
  - `org.flywaydb:flyway-mysql`

- **AWS Java SDK S3**: SDK da AWS para integração com o serviço de armazenamento S3.
  - `com.amazonaws:aws-java-sdk-s3`

- **AWS SDK for Java v2 S3**: Biblioteca da AWS SDK para interação com o serviço S3, versão 2.
  - `software.amazon.awssdk:s3`

- **Java JWT**: Biblioteca para criação e validação de JSON Web Tokens (JWT).
  - `com.auth0:java-jwt`

- **Spring Boot Starter Security**: Fornece funcionalidades de segurança para aplicações Spring Boot.
  - `org.springframework.boot:spring-boot-starter-security`

- **Spring Boot Starter Mail**: Inclui suporte para envio de e-mails.
  - `org.springframework.boot:spring-boot-starter-mail`

- **Springdoc OpenAPI Starter WebMVC UI**: Ferramenta para gerar a documentação OpenAPI (Swagger) para APIs Spring MVC.
  - `org.springdoc:springdoc-openapi-starter-webmvc-ui`

Esta lista fornece uma visão geral clara das dependências utilizadas no projeto, com uma breve descrição de cada uma.


### Configurações do Frontend (React):

- Certifique-se de que o [Node](https://nodejs.org/pt) esteja instalado.

- Navegue até o diretório e localize o arquivo `package.json` no frontend está localizado. Esse arquivo está na raiz do diretório do frontend. O caminho será algo como:

```bash
     ${SEU_CAMINHO}\Verzel-API
```

Certifique-se de substituir `${SEU_CAMINHO}` pelo caminho real onde o projeto foi clonado.

#### Instale as dependências:

Para garantir que todas as dependências do projeto sejam instaladas corretamente, execute o seguinte comando:

```bash
    npm install
```

Este comando irá baixar e instalar todos os pacotes necessários conforme especificado no arquivo package.json do projeto.

- Após a instalação das dependências, inicie o frontend com o comando:

```bash
    npm start
```

Este comando iniciará o servidor de desenvolvimento, permitindo que você visualize o aplicativo React em seu navegador.

As dependências que estou utilizando no frontend são:


- **@fortawesome/fontawesome-free:** Biblioteca de ícones FontAwesome para adicionar ícones vetoriais ao seu projeto. 

- **@testing-library/jest-dom:** Extensões para Jest que adicionam métodos adicionais para testar o DOM.

- **@testing-library/react:** Utilitário de teste para componentes React.

- **@testing-library/user-event:** Biblioteca para simular eventos do usuário durante testes. 

- **axios:** Biblioteca para realizar requisições HTTP. 

- **react:** Biblioteca principal para a construção de interfaces de usuário.  

- **react-dom:** Biblioteca para renderizar componentes React na DOM.  

- **react-icons:** Conjunto de ícones React de várias bibliotecas de ícones populares. 

- **react-router-dom:** Biblioteca para gerenciamento de rotas em aplicativos React. 

- **react-scripts:** Scripts padrão para configurar e executar aplicações React criadas com Create React App.  

- **web-vitals:** Biblioteca para medir e monitorar o desempenho da web. 

- **@babel/plugin-proposal-private-property-in-object:** Plugin Babel para suportar propriedades privadas em objetos.

Esta lista fornece uma visão geral clara das dependências utilizadas no projeto, com uma breve descrição de cada uma.

## ⚠️ Atenção: 

No final da explicação do backend e do frontend, irei disponibilizar um usuário ADMIN para que possam testar os endpoints `POST`, `PUT` e `DELETE` de veículos. 

Fique atento para que possa utilizar este usuário para testes completos das funcionalidades administrativas da API.


## Funcionamento do Backend (Java)

- A api estará disponível na porta `http://localhost:8080`

O backend do projeto é desenvolvido utilizando Java com o framework Spring Boot. Ele é responsável por gerenciar a lógica de negócios, a persistência de dados e a comunicação com o frontend.

### V.1 Endpoint: Criação de Usuário

O endpoint para criação de um novo usuário é responsável por adicionar usuários ao sistema. A seguir, detalho como este endpoint está implementado, indico começarem pela criação de usuário, para que possam testar a aplicação quando o frontend e backend estiverem rodando.

#### Endpoint: `POST /register/users`

Este endpoint permite a criação de novos usuários no sistema. Ele recebe os dados do usuário em formato JSON e, após a criação, define as permissões iniciais para o usuário. O usuário recém-criado é retornado na resposta com um código HTTP 201.


```http
{
  "nomeCompleto": "Vinicius Lemes",
  "email": "vinicius@gmail.com",
  "senha": "123456"
}
```

**Respostas**

- 201 Created: Retornado quando o usuário é criado com sucesso. A resposta inclui um objeto UsuarioDetalhamentoDTO com os detalhes do usuário criado.
- 400 Bad Request: Retornado quando a requisição está malformada ou contém dados inválidos.
- 404 Not Found: Retornado quando o recurso solicitado não pode ser encontrado.
- 500 Internal Server Error: Retornado quando ocorre um erro inesperado no servidor.

### V.2 Endpoint: Recuperação de Senha

Caso você tenha criado um usuário e tenha esquecido a senha, oferecemos dois endpoints para ajudar na recuperação da senha.

#### Endpoint 1: Enviar Código para Recuperação de Senha

Se você não consegue acessar sua conta porque esqueceu a senha, pode solicitar um código de recuperação. Para isso, você deve fornecer o e-mail cadastrado na plataforma. Se o e-mail não estiver registrado, uma exceção será retornada. Caso o e-mail esteja cadastrado, um código será enviado para esse endereço.

#### Endpoint `POST /register/users/enviar-codigo`

```http
{
  "email": "vinicius@gmail.com",
}
```

Descrição: 
- Informe seu e-mail para receber um código de recuperação de senha. Você usará este código para redefinir sua senha perdida.

**Respostas**
- 200 OK: Email enviado com sucesso para o endereço informado.
- 400 Bad Request: Solicitação mal formada, geralmente devido a dados inválidos.
- 404 Not Found: O e-mail informado não está registrado no sistema.
- 500 Internal Error: Erro interno do servidor.

### V.2.1 Endpoint 2: Redefinir Senha

Após receber o código de recuperação em seu e-mail, você pode usá-lo para redefinir sua senha. Para isso, você deve fornecer o e-mail cadastrado, o código enviado para esse e-mail e a nova senha desejada.

#### Endpoint `POST /register/users/redefinir-senha`

```http
{
  "email": "vinicius@gmail.com",
  "newPassword": "123",
  "code": "c0a44c23-9a2c-4998-bdaf-b819077fccf0"
}
```

Descrição
- Para redefinir sua senha, insira o e-mail cadastrado, o código enviado para esse e-mail e a nova senha desejada.


**Respostas**
- 200 OK: Senha redefinida com sucesso.
- 400 Bad Request: Solicitação mal formada, geralmente - devido a dados inválidos.
- 404 Not Found: E-mail ou código inválido.
- 500 Internal Error: Erro interno do servidor.

Esses dois endpoints facilitam o processo de recuperação de senha e garantem que você possa restaurar o acesso à sua conta de forma mais segura.

## Autenticação de Usuário

Após criar um usuário, você precisará autenticar-se para acessar recursos protegidos da aplicação. Por padrão, todos os usuários criados são atribuídos à permissão de usuário `COMMON`. Para testar os endpoints `POST`, `PUT` e `DELETE` de veículos, estarei disponibilizando um usuário `ADMIN` após as explicações.

### V.3 Endpoint: Autenticação de Usuário

Para se autenticar, você deve fornecer seu e-mail e senha cadastrados. O sistema retornará um token de autenticação, que inclui informações como dados do usuário.

#### Endpoint `POST /auth/login`


```http
{
  "email": "vinicius@gmail.com",
  "senha": "123"
}
```

Descrição
- Informe o e-mail e a senha cadastrados para autenticar o usuário. A resposta incluirá as credenciais do usuário, como `accessToken`, `refreshToken`, e informações do usuário.

**Respostas**

- 200 OK: Autenticação bem-sucedida. A resposta inclui o token de acesso e outras informações de autenticação.
- 400 Bad Request: Solicitação mal formada, geralmente devido a dados inválidos.
- 404 Not Found: E-mail ou senha não encontrados.
- 500 Internal Error: Erro interno do servidor.


Se você esquecer sua senha, utilize os endpoints de recuperação de senha descritos anteriormente em `V.2`. Se precisar de ajuda adicional, volte para a seção de recuperação de senha.

## Requisições Públicas

Após a autenticação, você pode acessar as requisições públicas para buscar informações sobre veículos. Existem dois endpoints principais disponíveis para consultar dados de veículos:

### V.4 Endpoint: Buscar Veículo por ID

Este endpoint permite recuperar detalhes de um veículo específico fornecendo seu ID. Se o veículo com o ID fornecido não for encontrado na base de dados, será retornada uma exceção indicando que o recurso não foi encontrado.

#### Endpoint `GET /api/veiculos/public/v1/{id}`
```http
{
    "id": 1,
    "nome": "Onix",
    "marca": "Chevrolet",
    "modelo": "1.0 TURBO FLEX PREMIER AUTOMÁTICO",
    "valor": 89990.00,
    "foto": "${URL_AWS_S3}"
}
```
Descrição
- Forneça o ID do veículo para obter as informações completas associadas a esse ID. Caso o veículo não seja encontrado, uma exceção Not Found será retornada.

**Respostas**

- 200 OK: Detalhes do veículo encontrado, retornando um objeto com informações completas do veículo.
- 400 Bad Request: Solicitação mal formada, geralmente devido a um ID inválido.
- 403 Forbidden: Acesso negado (embora este endpoint seja público, este código pode ser retornado se houver problemas de permissão).
- 404 Not Found: Veículo não encontrado com o ID fornecido.
- 500 Internal Error: Erro interno do servidor.

### V.4.1 Endpoint: Listar Todos os Veículos

Este endpoint permite listar todos os veículos disponíveis na base de dados.

#### Endpoint `GET /api/veiculos/public/v1`

```http
{
    "totalElements": 21,
    "totalPages": 3,
    "size": 10,
    "content": [
        {
            "id": 1,
            "nome": "Polo",
            "marca": "Volkswagem",
            "modelo": "1.4 250 TSI GTS AUTOMÁTICO",
            "valor": 1314589.00,
            "foto": "${URL_AWS_S3}"
        },
        {
            "id": 2,
            "nome": "Polo 2024",
            "marca": "Volkswagem",
            "modelo": "1.4 250 TSI GTS AUTOMÁTICO",
            "valor": 1314500.00,
            "foto": "${URL_AWS_S3}"
        }
        "first": true,
            "last": false,
            "numberOfElements": 10,
            "pageable": {
                "pageNumber": 0,
                "pageSize": 10,
                "sort": {
                    "empty": false,
                    "unsorted": false,
                    "sorted": true
                },
                "offset": 0,
                "unpaged": false,
                "paged": true
            },
            "empty": false
}
```

Descrição
- Recupera uma lista de todos os veículos registrados. Você pode paginar e ordenar os resultados especificando os parâmetros de paginação e direção de ordenação.

**Parâmetros de Query:**
- `page` (opcional, padrão: 0): Número da página para recuperação dos resultados. Por exemplo, 0 retorna a primeira página.
- `size` (opcional, padrão: 12): Número de veículos por página. Por padrão, são retornados 12 veículos por página.
- `direction` (opcional, padrão: desc): Direção da ordenação dos resultados. Pode ser asc para ordem crescente ou desc para ordem decrescente. Por padrão, os veículos são ordenados do maior para o menor valor.

**Respostas**
- 200 OK: Detalhes do veículo encontrado, retornando um objeto com informações completas do veículo.
- 400 Bad Request: Solicitação mal formada, geralmente devido a um ID inválido.
- 403 Forbidden: Acesso negado (embora este endpoint seja público, este código pode ser retornado se houver problemas de permissão).
- 404 Not Found: Veículo não encontrado com o ID fornecido.
- 500 Internal Error: Erro interno do servidor.


## Requisições Privadas: Somente para ADMIN

### V.5 Endpoint: Criar Veículo

Este endpoint permite a criação de um novo veículo e é acessível apenas para usuários com permissão de ADMIN. O processo de criação do veículo inclui o upload de uma foto para o Amazon S3.


#### Endpoint `POST /api/veiculos/admin/v1`

Descrição

Para criar um veículo, você deve enviar uma requisição multipart/form-data contendo os seguintes parâmetros:

- nome: Nome do veículo.
- marca: Marca do veículo.
- modelo: Modelo do veículo.
- valor: Valor do veículo.
- foto: Foto do veículo, enviada como um arquivo multipart.


O processo de criação do veículo segue estas etapas:

1. **Recepção dos Dados e Foto:** Os dados do veículo e a foto são recebidos como parte da requisição.

2. **Geração de UUID e Upload da Foto:**

- **Geração de Nome Único:** Um UUID é gerado para garantir que a foto tenha um nome único.

- **Conversão e Upload:** A foto é convertida para um arquivo e enviada para o Amazon S3.

- **Configuração do Bucket:** O upload é realizado no bucket configurado no AWS S3, juntamente no application.properties.

- **Retorno da URL:** Após o upload, a URL da foto no S3 é retornada.

3. **Criação do Veículo:** O veículo é criado com os dados fornecidos e a URL da foto, e salvo no banco de dados.

#### Explicação do AWS CLI

Para que o upload de fotos para o Amazon S3 funcione corretamente, você deve ter o AWS CLI instalado e configurado em sua máquina. A configuração padrão do AWS CLI é usada localmente, conforme especificado no arquivo AwsConfig.

**Nota:** O AWS CLI usa as configurações padrão locais para autenticação e autorização, que são gerenciadas pelo DefaultAWSCredentialsProviderChain e DefaultCredentialsProvider.

**Respostas**

- 201 Created: O veículo foi criado com sucesso. A resposta inclui os detalhes do veículo, incluindo a URL da foto no S3.
- 400 Bad Request: Solicitação mal formada, geralmente devido a parâmetros inválidos ou ausentes.
- 403 Forbidden: Acesso negado, indicando que o usuário não tem permissões suficientes.
- 404 Not Found: Recurso não encontrado.
- 500 Internal Error: Erro interno do servidor.


### V.5.1 Endpoint: Atualização do Veículo

Este endpoint permite a atualização de um veículo existente. É acessível somente para usuários com permissão de ADMIN. O processo de atualização inclui a manipulação de uma foto do veículo, que pode ser alterada ou mantida.

#### Endpoint `PUT /api/veiculos/admin/v1`

Descrição

Para atualizar um veículo, você deve enviar uma requisição multipart/form-data contendo os seguintes parâmetros:

- id: Campo Obrigatório
- nome: Não é obrigatório | Nome do veículo.
- marca: Não é obrigatório | Marca do veículo.
- modelo: Não é obrigatório | Modelo do veículo.
- valor: Não é obrigatório mas tem que ser o valor **0** | Valor do veículo.
- foto: Não é obrigatório mas tem que ser o valor **null** | Foto do veículo, enviada como um arquivo multipart.


**Processo de Atualização**
#### Busca do Veículo:

- O veículo é localizado no banco de dados pelo id fornecido.
- Se o veículo não for encontrado, uma resposta 404 Not Found é retornada.

#### Manipulação da Foto:

- Nome da Foto Atual: O nome da foto atualmente associada ao veículo é extraído.
- Deleção da Imagem Antiga: Se uma nova foto for fornecida, a foto antiga é deletada do bucket S3 usando o nome extraído.
- Upload da Nova Foto: A nova foto é enviada para o bucket S3, e a URL da nova foto é gerada e associada ao veículo.

#### Atualização do Veículo:

- Os campos do veículo são atualizados com os novos valores fornecidos.
- Se algum campo for null, ele não será atualizado, exceto para foto que tem que ser null e valor que tem que ser 0, caso não queira atualizar.

**Respostas**

- 200 OK: O veículo foi atualizado com sucesso. A resposta inclui os detalhes do veículo atualizado.

- 400 Bad Request: Solicitação mal formada, geralmente devido a parâmetros inválidos ou ausentes.

- 403 Forbidden: Acesso negado, indicando que o usuário não tem permissões suficientes.

- 404 Not Found: Veículo não encontrado.

- 500 Internal Error: Erro interno do servidor.


### V.5.2 Endpoint: Exclusão do Veículo

Este endpoint permite a exclusão de um veículo existente. É acessível somente para usuários com permissão de ADMIN. O processo inclui a remoção da foto associada ao veículo do bucket S3 antes de excluir o veículo da base de dados.

#### Endpoint `DELETE /api/veiculos/admin/v1/{id}`

Descrição

- Para deletar um veículo, você deve enviar uma requisição com o `id` no caminho da requisição.

**Processo de Exclusão**
#### Busca do Veículo:

- O veículo é localizado no banco de dados pelo id fornecido.

- Se o veículo não for encontrado, uma resposta 404 Not Found é retornada.

#### Deleção da Imagem:

- Nome da Foto: O nome do arquivo da foto associada ao veículo é extraído.
- Deleção no S3: O arquivo é removido do bucket S3 utilizando o nome do arquivo.

#### Exclusão do Veículo:
- Após a remoção da foto, o veículo é excluído do banco de dados.

**Respostas**
- 204 No Content: O veículo foi excluído com sucesso e a foto foi removida do bucket S3.

- 400 Bad Request: Solicitação mal formada, geralmente devido a parâmetros inválidos ou ausentes.

- 403 Forbidden: Acesso negado, indicando que o usuário não tem permissões suficientes.

- 404 Not Found: Veículo não encontrado.

- 500 Internal Error: Erro interno do servidor.


## Funcionamento do Frontend (React)

- A api estará disponível na porta `http://localhost:3000`

O frontend do projeto é desenvolvido utilizando React e estilizado com CSS responsivo. Ele desempenha um papel crucial na interface do usuário, sendo responsável por renderizar as telas, enviar e consumir dados, além de se comunicar com o backend para implementar a lógica da aplicação.

### Componentes e Funcionalidades

O primeiro componente mostrado será o de `Login`. ste componente gerencia a autenticação do usuário, permitindo que ele insira suas credenciais e receba feedback visual conforme interage com a aplicação.

### V.1 Endpoint: Login Autenticar Usuário

O arquivo Login.js é a primeira tela que o usuário verá ao acessar a aplicação. Nesta tela, o usuário deverá inserir suas credenciais de login (email e senha). Caso ainda não tenha uma conta, o usuário pode clicar em **Criar conta** para ser redirecionado para a página de cadastro localizada em `/criar-conta`.

Se as credenciais estiverem corretas, o usuário será autenticado e redirecionado para a página de veículos em `/veiculos`.

Se já tiver uma conta porém esqueceu a senha basta clicar em **Esqueceu senha?** que será redirecionado para `/forgot-password`;

### V.2 Endpoint: Cadastrar Usuário

#### Endpoint: Register.js `/criar-conta`

Este endpoint oferece uma tela de cadastro para novos usuários, contendo campos de entrada para nomeCompleto, email e senha. Ao preencher os campos e submeter o formulário, os dados são enviados para o backend e, em caso de sucesso, o usuário é redirecionado de volta para a tela de login em `/`.

### V.3 Endpoint: Esqueceu Senha

#### Endpoint: ForgotPassword.js `/forgot-password`

Este endpoint oferece uma tela onde o usuário pode solicitar a recuperação de sua senha. O usuário deve fornecer seu email cadastrado, e caso o email esteja correto, será enviado um código no seu email para redifinição de senha. Em caso de sucesso, o usuário será redirecionado para a página `/reset-password`. Se o email fornecido não estiver cadastrado ou houver um erro, uma mensagem de erro será exibida.

### V.3.1 Endpoint: Esqueceu Senha

#### Endpoint: ResetPassword.js `/reset-password`

O usuário deve fornecer seu email cadastrado, o código enviado ao seu email, e a nova senha desejada. Se o email ou o código estiverem inválidos, não será possível alterar a senha. Em caso de sucesso, o usuário será redirecionado para a página de login (`/`), onde poderá se autenticar com a nova senha.

### V.4 Endpoint: Listar Todos os Veículos

#### Endpoint: VeiculosList.js `/veiculos`

Após o login, o usuário será direcionado para esta tela, que exibe uma lista de todos os veículos disponíveis. Nesta página, você encontrará as seguintes funcionalidades:

- **Ver Detalhes:** Ao clicar no botão Ver detalhes, você será redirecionado para a página de detalhes do veículo específico em `/veiculos/:id`, onde poderá visualizar informações detalhadas sobre o veículo selecionado.

- **Editar:** Clicando em Editar, você será redirecionado para a página de edição do veículo em `/veiculos/editar/:id`, onde poderá alterar as informações do veículo.

- **Deletar:** Se o usuário for um administrador (ADMIN), ele poderá deletar um veículo ao clicar em Deletar. Caso contrário, uma mensagem de erro será exibida, indicando que a operação não é permitida.

#### Resumo

O componente VeiculosList exibe uma lista de veículos e oferece opções para visualizar detalhes, editar ou deletar cada veículo. A lista é carregada do backend. O componente também lida com erros e permissões, garantindo que apenas usuários autorizados possam realizar operações de exclusão.

### V.5 Endpoint: Veículo

#### Endpoint: Veiculo.js `/veiculos/:id`

Neste endpoint, você encontrará a página de detalhes de um veículo específico. A página exibe informações detalhadas sobre o veículo, incluindo foto, nome, marca, modelo e valor.

Aqui estão as funcionalidades disponíveis:

- **Editar:** Ao clicar no botão Editar, você será redirecionado para a página de edição do veículo em `/veiculos/editar/:id`. Nesta página, você poderá modificar as informações do veículo selecionado.

- **Voltar:** Ao clicar no botão Voltar, você será redirecionado para a tela de VeiculosList em `/veiculos`.

#### Resumo

O componente Veiculo exibe detalhes de um veículo específico e oferece opções para editar as informações do veículo ou voltar à página de login. Os detalhes são carregados do backend e exibidos ao usuário com um indicador de carregamento.

### V.6 Endpoint: Editar

#### Endpoint: Editar.js `/veiculos/editar/:id`

Este endpoint fornece uma tela para editar as informações de um veículo específico. A página exibe todos os detalhes do veículo selecionado, incluindo nome, marca, modelo e valor.

#### Explicação detalhada:

- Informações do Veículo: A tela de edição mostra os campos atuais do veículo que podem ser modificados. Isso inclui campos de texto para nome, marca, modelo e valor, além da foto que é um campo para upload de uma nova imagem do veículo.

#### Permissões de Usuário:

⚠️ **Atenção:** Se o usuário tiver a permissão de **COMMON**, ele poderá visualizar a tela de edição e modificar os dados nos inputs. No entanto, ao tentar salvar as alterações, o usuário será impedido de fazer isso, pois apenas usuários com permissão de **ADMIN** têm autorização para realizar alterações.

- **Campo de Foto:** O campo para upload de foto (Escolher Arquivo) estará sempre vazio ao carregar a tela. Se o usuário **ADMIN** desejar alterar a foto do veículo, é obrigatório selecionar uma nova imagem antes de salvar as alterações.

- **Voltar:** Caso o usuário queira voltar, ele será redirecionado para a tela de listagem de veículos em `/veiculos`.

### V.7 Endpoint: Header && Footer

#### Endpoint: Header.js && Footer.js

O Header.js é um componente de navegação que será exibido em todas as páginas da aplicação. Ele contém links para os seguintes endpoints:

Cadastrar: `/cadastrar`

- Descrição: Redireciona o usuário para a página de cadastro de um novo veículo.

Listar Todos os Veículos: `/veiculos`

- Descrição: Redireciona o usuário para a lista de todos os veículos disponíveis.

Login: `/`

- Descrição: Redireciona o usuário para a página de login.

Sair: `/`

- Descrição: Redireciona o usuário para a página de login.
- Ação Adicional: Ao clicar neste link, o token de autenticação é removido, e o usuário será desconectado, forçando um novo login para acessar áreas restritas.

Footer.js:

- Informações de Contato: O Footer.js exibe informações de contato, como e-mail e telefone. Estas informações ajudam os usuários a entrar em contato com a equipe de suporte ou administração.


### V.8 Endpoint: Cadastrae

#### Endpoint: Cadastrar.js

O Cadastrar.js é o componente responsável por permitir o cadastro de um novo veículo. Nesta página, o usuário pode fornecer todas as informações necessárias para cadastrar um veículo, incluindo nome, marca, modelo, valor e uma foto do veículo.

⚠️ Neste caso todos os campos são obrigatórios.

#### Permissões:

- ADMIN: Pode cadastrar um novo veículo. Após o envio bem-sucedido, será redirecionado para a lista de veículos (`/veiculos`).

- COMMON: Pode visualizar a tela e inserir dados nos campos, mas não será possível salvar as informações. Ao tentar salvar, será exibida uma mensagem de erro.


**Ação de Cadastro:** Ao submeter o formulário, os dados serão enviados ao backend para processamento e cadastro do veículo. Se o cadastro for bem-sucedido, o usuário será redirecionado para a página de listagem de veículos (`/veiculos`). Caso haja algum erro, será exibida uma mensagem de erro.

**Botão Voltar:** Permite que o usuário retorne à página de listagem de veículos (`/veiculos`).

Agora irei mostar o usuário ADMIN:

## ⚠️ Usuário ADMIN:

- Email: verzellemes@gmail.com
- Senha: admin123


### Agradecimento

Agradeço por ter realizado o teste, espero que a documentação tenha sido útil e clara. Se houver qualquer dúvida ou necessidade de mais informações, não hesite em entrar em contato.

Obrigado! 

### Contato
- Email: vinikjhgfds@gmail.com
- Telefone: (11) 91074-7135
