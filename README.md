# TPaga Account Service
BackEnd Developer Test - TPaga is a rest service application builds with Spring Boot in a microservice oriented architecture.

[![Deploy](https://www.herokucdn.com/deploy/button.svg)](https://heroku.com/deploy?template=https://github.com/pipecode/tpaga-account-service)

## Problem
### Un API de cuenta básico
hacer un API que permita manipular una sola cuenta con 3 endpoints
1. listar transacciones indicando saldo actual
2. Sumar un valor a la cuenta, bien sea positivo o negativo
3. inicializar el saldo en 0
Usted hará la especificación de estos endpoints y los métodos y parámetros de entrada así
como la indicación de los parámetros de salida y manejo de errores.

### Problema de Double Spending
Asegurar que a pesar de que haya transacciones que puedan ser concurrentes, estas son
manejadas de alguna forma para que la cuenta pueda procesar todos los movimientos y las
transacciones resulten en un estado consistente. Por favor haga explícito en el documento
las técnicas empleadas para lograrlo.

##### Solución Propuesta
Se implemento la interfaz Lock y ReetrantLock del api ```java.util.concurrent.locks```.
 
Se implemento la interfaz de bloqueo (Lock), que actúa como una cerradura de un proceso a ejecutar haciendo que los hilos se bloqueen hasta que el proceso la cerradura/bloqueo sea abierta/liberada.

>Nota: Una instancia bloqueada siempre debe desbloquearse para evitar la condición de interbloqueo.

##### Ejemplo
```

Lock lock = new ReentrantLock();

public void perform() {
        lock.lock();
        try {
            // TODO Logic
        } finally {
            lock.unlock();
        }
    }
```

#### Descripción de Métodos

##### Void lock()
Adquiere el bloqueo (lock) si esta disponible, de lo contrario los hilos se bloquean hasta que el bloqueo (lock) sea liberado.

##### Void unlock()
Libera el bloqueo (lock)

## Run the application
To build and run the application, execute:

```
./gradlew build && java -jar build/libs/{artifact}-{version}.jar
```

## Unit Tests
To run the application tests, execute:

```
./gradlew test
```

## Documentation
#### Postman
[TPaga Account Service - Postman Docs](https://documenter.getpostman.com/view/5261799/RWaPt6Ug)
#### Local
[TPaga Account Service - Local Docs](https://github.com/pipecode/tpaga-account-service/blob/master/APIDOC.md)

## Autor
[Luis Felipe Castro Ospina](https://www.linkedin.com/in/felipecode/)