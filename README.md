# guilhermevillaca-texa
Teste vaga backend

Passos para reproduzir
fazer o clone do repositório
navegar pelo terminal até a pasta 

Caso seja linux ou macos executar o comando:
./mvnw spring-boot:run

ou 
Caso seja windows executar o comando:
mvnw spring-boot:run

Caso dê algum erro verificar a versão do java, fiz o desenvolvimento com Java 17.


O banco embarcado usado foi o h2, 
para conferir se está ok acessar http://localhost:8080/h2-console/ e usar a JDBC url jdbc:h2:mem:guilhermevillaca

foi criado FilmeController com os serviços REST
GET para retornar todos os registros
http://localhost:8080/filme

GET passando id, para retornar um filme específico
http://localhost:8080/filme/1

POST passando um json conforme exemplo abaixo
http://localhost:8080/filme
{
    "year": "2022",
    "title": "Poderoso Chefão 4",
    "studios": "villaca",
    "producers": "Luís Villaca e Helô Villaca",
    "winner": "yes"
}

PUT para editar passando id do filme a ser alterado
http://localhost:8080/filme/207
{
    "year": "2022",
    "title": "Poderoso Chefão 4",
    "studios": "villaca",
    "producers": "Luís Villaca e Helô Villaca",
    "winner": "yes"
}

DELETE para remover passando id
http://localhost:8080/filme/207
