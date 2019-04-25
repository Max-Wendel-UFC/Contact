# Contact
- Simples CRUD de Contatos

## Instalando e rodando a aplicação
#### Configurando o Banco de dados
- Crie um banco _PostgreSQL_ com o nome `db_contact`.
- Ou se preferir outro nome, altere o nome final da `url`
```yaml
#...
datasource:
    #...
    url: jdbc:postgresql://localhost:5432/${nome_do_bd}
#...
```
- Verifique se o `username` e o `password` estão de acordo com suas configuraçções do _PostgreSQL_:
```yaml
#...
  datasource:
    username: postgres
    password: postgres
#...
```
#### Instalando as dependências
- Para instalar as dependências, execute o seguinte comando no terminal:
```sh
$ mvn clean install
```

#### Rodando a aplicação
- Para executar a aplicação execute o seguinte comando no terminal:
```sh
$ mvn spring-boot:run
```
## Testando a aplicação
#### Testando com o _curl_, via terminal
- Salvando um contato:

```sh
$ curl -X POST "http://localhost:8090/contact/save" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"name\": \"loki\", \"phones\": [ { \"number\": \"88997979797\", \"operatorId\": \"TIM\" }, { \"number\": \"88997978888\", \"operatorId\": \"VIVO\" } ], \"mails\": [ { \"address\": \"loki.loki@valhalla.com\" } ]}"
```

- Buscando um contato

```sh
$ curl -X GET "http://localhost:8090/contact/find?name=loki" -H "accept: */*"
```

- Editando um Contato

```sh
$ curl -X PUT "http://localhost:8090/contact/update?name=loki" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"name\": \"loki\", \"phones\": [ { \"number\": \"88997979797\", \"operatorId\": \"TIM\" }, { \"number\": \"88997978888\", \"operatorId\": \"VIVO\" } ], \"mails\": [ { \"address\": \"loki.loki@valhalla.com\" }, { \"address\": \"loki.12@niflheim.com\" } ]}"
```
- Apagando um Contato

```sh
$ curl -X DELETE "http://localhost:8090/contact/delete?name=loki" -H "accept: */*"
```

## Documentação
- Para acessar a documentação gerada pelo _Swagger_, acesse `http://localhost:8090/v2/api-docs` quando a aplicação estiver rodando.
- Para uma apresentação mais bonita, acesse `http://localhost:8090/swagger-ui.html`.
