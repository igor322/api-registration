# API REST Estudantes

## Índice

<ol>
  <li><a href="#Sobre">Sobre o projeto</a></li>
  <li><a href="#Tecnologias">Tecnologias utilizadas</a></li>
  <li><a href="#Endpoint">Endpoints</a></li>
    <ol>
      <li><a href="#Base">URL Base</a></li>
      <li><a href="#Todos">Retornar todos os estudantes</a></li>
      <li><a href="#buscaID">Retornar o estudante procurando pelo id</a></li>
      <li><a href="#buscaNOME">Retornar os estudantes procurando pelo nome</a></li>
      <li><a href="#buscaMATRICULA"> Retornar o estudante procurando pela matricula</a></li>
      <li><a href="#Adicionar"> Adicionando um novo estudante</a></li>
      <li><a href="#Atualizar">Atualizando dados de um estudante</a></li>
      <li><a href="#Deletar">Deletando um estudante</a></li>
    </ol>
  <li><a href="#final">Considerações finais</a></li>
</ol> 

  
### <a name="Sobre">1. Sobre o projeto</a> 

&nbsp;&nbsp;&nbsp;&nbsp;O objetivo deste projeto é criar uma API REST
para o cadastro de estudantes com intuito de consultar, criar novos registros,
realizar atualizações e exclusões nestes (CRUD) utilizando um banco de dados.

### <a name="Tecnologias">2.Tecnologias utilizadas</a> 
&nbsp;&nbsp;&nbsp;&nbsp;Para o presente projeto foi utilizado a linguagem Java na
sua versão 11, o framework Spring Boot 2.4.3 e a IDE IntelliJ IDEA. Para a persistência dos dados
foi utilizado o banco de dados não relacional PostgreSQL. Foi utilizado o Heroku como
ferramenta para deploy do projeto, que junto do postman foi utilizado para testes.


### <a name="Endpoint">3. Endpoints</a>

#### <a name="Base">I. URL Base</a>
A URL Base é a porta de entrada de toda a API, caso seja feito algum request e retorne 
um erro **404 NOT FOUND** verificar a URL Base primeiro.
```Https
https://api-registration.herokuapp.com/api
```


#### <a name="Todos">II. Retornar todos os estudantes</a>

Utilizando de uma requisição do tipo **GET** para : 
```Https
https://api-registration.herokuapp.com/api
```

Retornará um json contendo todos os usuarios cadastrados no banco de dados.

#### <a name="buscaID">III. Retornar o estudante procurando pelo id</a>

Utilizando de uma requisição do tipo **GET** para :
```Https
https://api-registration.herokuapp.com/api/id/{numero}
```

Onde {numero} deve ser substituido pelo id do estudante que deseja buscar
no banco de dados.

#### <a name="buscaNOME">IV. Retornar os estudantes procurando pelo nome</a>

Utilizando de uma requisição do tipo **GET** para :
```Https
https://api-registration.herokuapp.com/api/nome/{Nome}
```

Onde {nome} deve ser substituido pelo nome que deseja buscar no banco de dados.

#### <a name="buscaMATRICULA">V. Retornar o estudante procurando pela matricula</a>

Utilizando de uma requisição do tipo **GET** para :
```Https
https://api-registration.herokuapp.com/api/matricula/{número}
```

Onde {número} deve ser substituido pelo valor da matricula que deseja buscar no
banco de dados.

#### <a name="Adicionar">VI. Adicionando um novo estudante</a>

Utilizando de uma requisição do tipo **POST** para :
```Https
https://api-registration.herokuapp.com/api
```
Passando-se dados validos para o cadastro de um novo estudante no **body**
da requisição, por exemplo:

```JSON
{
   "nome": "Igor",
   "sobrenome": "Araujo",
   "matricula": 10000,
  "telefone": ["(37)99870-6012","(37)3222-7102"]
}
```

#### <a name="Atualizar">VII. Atualizando dados de um estudante</a>

Utilizando de uma requisição do tipo **POST** para :
```Https
https://api-registration.herokuapp.com/api/{id}
```
Onde {id} deve ser substituido pelo id do estudante que deseja atualizar os dados. Deve
 passar dados validos, no body da requisição, para serem atualizados no banco de dados.
Por exemplo :
```JSON
{
   "nome": "Igor",
   "sobrenome": "Dias",
   "matricula": 10000,
  "telefone": ["(37)99870-6012"]
}
```

#### <a name="Deletar">VIII. Deletando um estudante</a>

Utilizando de uma requisição do tipo **DELETE** para :
```Https
https://api-registration.herokuapp.com/api/{id}
```
Onde {id} deve ser substituido pelo id do estudante que deseja excluir do banco de dados.

### <a name="final">4 . Considerações finais</a>

&nbsp;&nbsp;&nbsp;&nbsp;API criada e documentada por Igor Araujo, como parte de um teste.

