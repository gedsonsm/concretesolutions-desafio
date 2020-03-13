# Desafio Java Concrete Solutions

Aplicação API RESTful de criação de usuários e login.

## Cadastro

* EndPoint (POST):
http://{IP}:{PORTA}/user/create

* Exemplo de entrada:

```json
    {
        "name": "João da Silva",
        "email": "joao@silva.org",
        "password": "hunter2",
        "phones": [
            {
                "number": "987654321",
                "ddd": "21"
            }
        ]
    }
```

## Login

* EndPoint (POST):
http://{IP}:{PORTA}/user/login

* Exemplo de entrada:

```json
    {
        "email": "joao@silva.org",
        "password": "hunter2"
    }
```

## Perfil do Usuário

* EndPoint (POST):
http://{IP}:{PORTA}/user/profile/${user_id}

* Informações do Header:
token = ${user_token}



