# Desafio Reskilling Java

Apresentação e entrega do Desafio Reskilling Java.

## Conteúdo

- [Pré-requisitos](#pré-requisitos)
- [Execução Aplicação](#execução)
- [Execução Sonarqube](#sonarqube)
- [Links](#links)
- [Tarefa Principal](#tarefa-principal)
- [Tarefa Bônus](#tarefa-bônus)

## Pré-requisitos

Estas são as instalações e configurações necessárias para executar o projeto.

Para executar este projeto é necessário instalar:

- Apache Maven 3.8.5
- Java 18

1. Após a instalação é necessário configurar as variaveis de ambiente:

   - JAVA_HOME - Apontando para o local de instalação do Java
   - MAVEN_HOME - Apontando para o local de instalação do Maven
   - PATH - Adicionar "JAVA_HOME\bin" e "MAVEN_HOME\bin"

2. Após intatalações e configurações verifique se o ambiente está pronto:

   - Execute o seguinte comando no terminal 

         java -version

- Resultado esperado

   - java version "18" 2022-02-08
   - Java (TM) SE Runtime Enviroment (build 18+36-2087)
   - Java HotSpot(TM) 64-Bit Server VM (build 18+36-2087), mixed mode, sharing)

3. Execute o seguinte comando no terminal:

      mvn -version

- Resultado esperado

   - Apache Maven 3.8.5 (3599d3414f046de2324203b78ddcf9b5e4388aa0)
   - Maven home: ...\apache-maven-3.8.5
   - Java version: 18

## Execução

1. Clonar repositório git utilizando o comando:

         git clone https://github.com/gmkalandar/tarefaSolutis.git

2. Vá ate a pasta do projeto

         cd tarefa

3. Install all dependencies

         mvn spring-boot:run
         
Após a execução a inicialização conseguirá acessar:

   - Raiz da API
   - Documentação no Swagger (http://localhost:8080/swagger-ui.html)
   - Cliente h2 (http://localhost:8080/h2-console)

## SonarCloud

   - https://sonarcloud.io/project/overview?id=gmkalandar_tarefaSolutis


## Links

###### GitHub - https://github.com/elayneargollo/votacao_solutis
###### Swagger - http://localhost:8080/swagger-ui.html#/
###### Coleção Postman - https://github.com/gmkalandar/tarefaSolutis/blob/main/TarefaSolutis.postman_collection.json

## Tarefa Principal

Em uma cooperativa, cada associado possui um voto e as decisões são tomadas através de assembleias, por votação. A partir disso, você precisa criar o backend para gerenciar essas sessões de votação. A solução deve atender os seguintes requisitos através de uma API REST: 

- RF1. Cadastrar pauta.

- RF2. Abrir uma sessão de votação para uma pauta.
  
      - Cada pauta deve comportar apenas uma sessão de votação
      - A sessão de votação deve ficar aberta por um tempo determinado na chamada de abertura ou 1 minuto por padrão
      
- RF3. Receber votos dos associados em pautas abertas

      - Os votos são apenas 'Sim'/'Não'
      - Cada associado é identificado por um id único e pode votar apenas uma vez por pauta
      - Registre a data/hora do voto
      
- RF4. Contabilizar os votos e dar o resultado da votação na pauta

      - Exibir vencedor por maioria simples
      - Exibir quantidade de votos para cada um dos grupos 'Sim'/'Não'
      - Exibir percentual para cada um dos grupos 'Sim'/'Não
      
- Para fins de exercício

       - A segurança pode ser abstraída e qualquer chamada para a API pode ser considerada autorizada.
       - Para fins de exercício, você pode cadastrar previamente um conjunto de associados.
       - Implemente testes unitários com ao menos 50% de cobertura de código.
       - Utilize Swagger para documentar sua API.
       - Disponibilize uma coleção do Postman com todos endpoints.
       - Se utilizar um banco de dados diferente do H2, um dockerfile deve ser disponibilizado para o banco de dados.

## Tarefa Bônus

- [x] Tarefa Bônus 1 - Integração com sistemas externos (GET: https://cpf-api-almfelipe.herokuapp.com/cpf/12345678901)
- Tarefa Bônus 2 - Contabilização automática (A rotina de contabilização deve ser executada a cada minuto)
- Tarefa Bônus 3 - Mensageria e filas (Kafka, RebbitMQ ou qualquer outra)
- Tarefa Bônus 4 - Hospede sua API na nuven (Free Tier AWS, Heroku ou outro)
[1/2]- Tarefa Bônus 5 - Análise de qualidade do código (Sonarqube) [por problemas técnicos usados paleativos: Sonalint (cliente)  e SonarCloud (Servidor)]
- [x]  Tarefa Bônus 6 - Versionamento da API

