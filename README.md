# Baozi Store - API REST

Java 17 + Spring Boot 3 + Spring Data JPA + H2 (ou MySQL).

## Executar
    mvn spring-boot:run
API em http://localhost:8080 | Console H2: http://localhost:8080/h2-console (JDBC URL: jdbc:h2:mem:baozidb)

## Endpoints (para /clientes, /produtos e /pedidos)
POST /recurso | GET /recurso | GET /recurso/{id} | PUT /recurso/{id} | DELETE /recurso/{id}
