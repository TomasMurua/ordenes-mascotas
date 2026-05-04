# Ordenes Mascotas - Microservicio

Microservicio Spring Boot para gestionar productos y ordenes de compra para mascotas. Conecta a Oracle, expone respuestas con HATEOAS y se ejecuta containerizado.

## Stack

- Java 21 + Spring Boot 3.4.4
- Spring Data JPA + Oracle JDBC (`ojdbc11`)
- Spring HATEOAS
- JUnit 5 + Mockito
- Maven, Docker, Docker Compose

## Ejecutar con Docker (recomendado)

```bash
docker compose up -d
```

Levanta Oracle + el microservicio. Disponible en `http://localhost:8082`.

## Ejecutar local sin Docker

```bash
docker compose up -d oracle
mvn spring-boot:run
```

## Pruebas

```bash
mvn test
```

- `ProductoServiceTest`: 4 pruebas de servicio con Mockito
- `ProductoControllerHateoasTest`: 2 pruebas verificando enlaces HATEOAS

## Endpoints

| Metodo | Ruta | Descripcion |
|--------|------|-------------|
| GET | /productos | Listar productos (con `_links`) |
| GET | /productos/{id} | Obtener producto |
| POST | /productos | Crear producto |
| PUT | /productos/{id} | Actualizar |
| DELETE | /productos/{id} | Eliminar |
| GET | /ordenes | Listar ordenes |
| GET | /ordenes/{id} | Obtener orden |
| GET | /ordenes/estado/{estado} | Filtrar por estado |
| POST | /ordenes | Crear orden |
| PUT | /ordenes/{id} | Actualizar |
| DELETE | /ordenes/{id} | Eliminar |

## Arquitectura

```
src/main/java/com/duoc/ordenesmascotas/
  controller/   -> Controladores REST + enlaces HATEOAS
  service/      -> Logica de negocio + mapeo entity->DTO
  repository/   -> Interfaces JpaRepository (@Repository)
  model/        -> Entidades JPA
  dto/          -> RequestDto (entrada) y ResponseDto (salida con links)
  exception/    -> Manejo global de errores
  config/       -> Carga inicial de datos
```
