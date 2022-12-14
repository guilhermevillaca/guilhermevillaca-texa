# guilhermevillaca-texa
Teste vaga backend

Para esse projeto foi utilizado o framework springboot, pensando em facilitar o desenvolvimento (ter servidor e banco embarcado) e a análise por parte da TEXA.

Foi utilizado o java 17 e IDE Netbeans.

Passos para reproduzir <br>
fazer o clone do repositório <br>
```powershell
git clone https://github.com/guilhermevillaca/guilhermevillaca-texa.git
```
navegar pelo terminal até a pasta <br> 
```powershell
cd guilhermevillaca-texa
```

Caso seja linux ou macos executar o comando:
```powershell
./mvnw spring-boot:run
```
ou 
Caso seja windows executar o comando:
```powershell
mvnw spring-boot:run
```

O banco embarcado usado foi o h2, <br> 
para conferir se está ok acessar http://localhost:8080/h2-console/ e usar a JDBC url jdbc:h2:mem:guilhermevillaca <br> 

Na classe principal GuilhermevillacaApplication tem o método main e um trecho de código que faz a leitura do CSV e o processo de persistir no banco, esse código é executado ao construir o projeto. <br>

Foi criado um modelo que está no package <b>br.com.guilhermevillaca.model</b> chamado <b>Filme</b>, <br>
com os atributos <i>id</i>, <i>year</i>, <i>title</i>, <i>studios</i>, <i>producers</i>, <i>winner</i>. <br>

Foi criado um repositório no package <b>br.com.guilhermevillaca.repository</b> chamado <b>FilmeRepository</b> que extende <b>CrudRepository</b>,
para automatizar as etapas de salvar, editar, deletar e consultar.

Foi criado <b>FilmeController</b> com os serviços REST <br> 

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

PUT para editar passando id do filme a ser alterado, conforme exemplo abaixo <br> 
http://localhost:8080/filme/207 <br> 
```json
{
    "year": "2024",
    "title": "Poderoso Chefão 5",
    "studios": "villaca",
    "producers": "Luís Villaca e Helô Villaca",
    "winner": "yes"
}
```

DELETE para remover passando id <br> 
http://localhost:8080/filme/207

Para conferir o o produtor com maior intervalo entre dois prêmios consecutivos, e o que obteve dois prêmios mais rápido <br>
http://localhost:8080/filme/maiorIntervalo

Os testes estão na classe MovieControllerTest. 
Testes realizados direto pelo netbeans.
Foram testados GET, POST, PUT e DELETE e a persistência dos dados pelo CSV.
