# TPaga Account Service - Api Docs
Este documento describe como interactuar con la Api Rest de TPaga Account Service.

## Environments
| Name        | Host                        |
| ----------  | --------------------------- |
| local       | localhost                   |
| development | backend-tpaga.herokuapp.com |
| production  | 18.204.251.209:9091         |

## Headers
| Name         | Host             |
| ------------ | ---------------- |
| Content-Type | application/json |
| Accept       | application/json |

## Methods

## Create Account Transaction
#### ```POST /api/account/```
Crea una transacción con el fin de sumar un valor a la cuenta sea positivo o negativo.

##### Transaction Request
| Name              | Type       | Obligatorio |
| ----------------- | ---------- | :---------: |
| transactionDetail | String     |     si      |
| transactionValue  | double     |     si      |

##### Transaction Response
| Name              | Type        |
| ----------------- | ---------   |
| transaction       | Transaction |

##### Transaction
| Name              | Type   |
| ----------------- | ------ |
| description       | String |
| value             | double |
| previousBalance   | double |
| currentBalance    | double |

##### Ejemplo
###### Request Example
```
   {
   	"transactionDetail" : "Translado desde cuenta TPaga",
   	"transactionValue" : 50.50
   }
```

###### Response Example
```
   {
       "transaction": {
           "description": "Translado desde cuenta TPaga",
           "value": 50.5,
           "previousBalance": 0,
           "currentBalance": 50.5
       }
   }
```

###### Curl Example
```
curl --request POST --url 'http://backend-tpaga.herokuapp.com/api/account' --header 'Content-Type: application/json' --data '{ "transactionDetail" : "Translado desde cuenta TPaga", "transactionValue" : 50.50 }'
```

## Get Account Info
#### ```GET /api/account/```
Obtiene la información de la cuenta y el historial de transacciones.

###### Curl Example
```
curl --request GET --url 'http://backend-tpaga.herokuapp.com/api/account'
```

###### Response Example
```
   {
       "balance": 50.5,
       "transactions": [
           {
               "description": "Translado desde cuenta TPaga",
               "value": 50.5,
               "previousBalance": 0,
               "currentBalance": 50.5
           }
       ]
   }
```

## Reset Account
#### ```PUT /api/account/```
Reinicia el historial y balance de la cuenta.

###### Curl Example
```
curl --request PUT --url 'http://backend-tpaga.herokuapp.com/api/account'
```

###### Response Example
```
Account successfully reset
```

## Response Status
Todas las peticiones exitosas retornan un Código HTTP 200

## Errores
Existen diferentes tipos de errores, sin embargo estos están controlados por un interceptor y devuelven un objeto error en cada petición.

###### Error Example
```
    {
        "status": "BAD_REQUEST",
        "message": "Validation failed",
        "errors": [
            "transactionValue: valor numérico fuera de los límites (se esperaba <10 dígitos>.<2 dígitos)"
        ]
    }
```