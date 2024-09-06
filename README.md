## Send Email API
### Exemplo de objeto de envio
```json
{
	"to":"exemplo@email.com",
	"subject":"Assunto",
	"message":"Olá, este é um e-mail de teste"
}
```
### Observações
É interessante observar que a origem do e-mail bem como a senha serão definidos no `application.properties` nas variáveis `email.address` e `email.password`. Ademais, recomendo ativar a autenticação de dois fatores e utilizar uma senha de aplicativo.