# Automanager — Atividade 3 

> Microserviço Spring Boot (Java 17). H2 para dev; conector MySQL incluído.

Pré-requisitos: `Java 17`, `Git` (Maven opcional — `mvnw` incluído).

Executar (Windows cmd):
```
cd automanager
mvnw.cmd clean package
mvnw.cmd spring-boot:run
```

Alterar porta (ex.):
```
set SERVER_PORT=8081
mvnw.cmd spring-boot:run
```

Configuração: `src/main/resources/application.properties` (ajuste `spring.datasource.*` para MySQL).

Testes:
```
mvnw.cmd test
```

Nota: versão Spring Boot definida no `pom.xml`; `java.version=17`.
