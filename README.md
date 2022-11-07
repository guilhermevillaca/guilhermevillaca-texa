# guilhermevillaca-texa
Teste vaga backend

Passos para reproduzir <br>
fazer o clone do repositório <br>
navegar pelo terminal até a pasta <br> 

Caso seja linux ou macos executar o comando:

```powershell
./mvnw spring-boot:run
```
ou 
Caso seja windows executar o comando:
```powershell
mvnw spring-boot:run
```

Caso dê algum erro verificar a versão do java, fiz o desenvolvimento com Java 17. <br> 


O banco embarcado usado foi o h2, <br> 
para conferir se está ok acessar http://localhost:8080/h2-console/ e usar a JDBC url jdbc:h2:mem:guilhermevillaca <br> 

Foi criado FilmeController com os serviços REST <br> 
GET para retornar todos os registros <br> 
http://localhost:8080/filme <br> 

GET passando id, para retornar um filme específico <br> 
http://localhost:8080/filme/1 <br> 

POST passando um json conforme exemplo abaixo <br> 
http://localhost:8080/filme
```json
{
    "year": "2022",
    "title": "Poderoso Chefão 4",
    "studios": "villaca",
    "producers": "Luís Villaca e Helô Villaca",
    "winner": "yes"
}
```

PUT para editar passando id do filme a ser alterado <br> 
http://localhost:8080/filme/207 <br> 
```json
{
    "year": "2022",
    "title": "Poderoso Chefão 4",
    "studios": "villaca",
    "producers": "Luís Villaca e Helô Villaca",
    "winner": "yes"
}
```

DELETE para remover passando id <br> 
http://localhost:8080/filme/207
