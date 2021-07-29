# API Rest com Spring Boot

- java version 16
- maven project
- spring boot 2.5.3

### Features
- [x] Paginação e ordenação de recursos
- [x] Melhorando desempenho com Spring Cache 
- [x] Proteção com Spring Security
- [x] Autenticação via JWT


#### Autenticação
```
curl --request POST \
  --url http://localhost:8080/auth \
  --header 'Content-Type: application/json' \
  --data '{
	"username":"aluno",
	"password":"123"
}'
```

Para as requisições de Cadastrar, Alterar e Excluir, insira no cabeçalho de cada requisição um parametro "Authorization" com o valor "Bearer + (token)"


#### Receber a lista com todos os topicos cadastrados
```
curl --request GET \
  --url 'http://localhost:8080/topicos?page=0&size=10&sort=id%2Cdesc'
```

#### Receber um topico específico e detalhado
```
curl --request GET \
  --url http://localhost:8080/topicos/1
```

#### Cadastrar um novo tópico
```
curl --request POST \
  --url http://localhost:8080/topicos \
  --header 'Authorization: Bearer TOKEN' \
  --header 'Content-Type: application/json' \
  --data '{
	"titulo":"Insomnia",
	"mensagem":"Testando método POST",
	"nomeCurso":"Spring Boot"
}'
```

#### Atualizar um tópico existente
```
curl --request PUT \
  --url http://localhost:8080/topicos/1 \
  --header 'Authorization: Bearer TOKEN' \
  --header 'Content-Type: application/json' \
  --data '{
	"mensagem":"Testando método PUT",
	"titulo":"Atualizando Tópico"
}'
```

#### Excluir um tópico existente
```
curl --request DELETE \
  --url http://localhost:8080/topicos/2 \
  --header 'Authorization: Bearer TOKEN' \
```