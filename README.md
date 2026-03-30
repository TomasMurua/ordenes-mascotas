# Ordenes Mascotas - Microservicio

Microservicio para gestionar ordenes de compra de productos para mascotas.

## Tecnologias

- Java 21
- Spring Boot 3.4.4
- Maven

## Como ejecutar

```bash
mvn spring-boot:run
```

La aplicacion corre en `http://localhost:8080`

## Endpoints

| Metodo | Ruta | Descripcion |
|--------|------|-------------|
| GET | /productos | Listar todos los productos |
| GET | /productos/{id} | Obtener producto por ID |
| GET | /ordenes | Listar todas las ordenes |
| GET | /ordenes/{id} | Obtener orden por ID |
| GET | /ordenes/estado/{estado} | Filtrar ordenes por estado |

## Estructura

```
src/main/java/com/duoc/ordenesmascotas/
  controller/    -> Controladores REST
  model/         -> Clases de modelo (Producto, Orden)
  service/       -> Logica de negocio y datos en memoria
```
