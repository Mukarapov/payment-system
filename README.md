# Payment System

REST API для обработки платежей с конвертацией валют, расчетом комиссии и хранением операций в PostgreSQL.

## Технологии

- Java 17
- Spring Boot 3
- Spring Data JPA
- PostgreSQL 16
- Flyway
- Spring Validation
- Swagger / OpenAPI
- JUnit 5
- Mockito
- MockMvc
- Docker
- Gradle

---

## Возможности

- Создание платежа
- Конвертация валют в RUB
- Расчет комиссии по тарифной политике
- Хранение платежей и комиссий в PostgreSQL
- Обработка доменных событий
- Глобальная обработка ошибок
- Валидация входящих запросов
- Документация API через Swagger
- Unit и Integration тесты

---

### Основные компоненты

- PaymentController
- PaymentServiceImpl
- PaymentStoreServiceImpl
- DefaultFeePolicyService
- StubExchangeRateService
- PaymentCreatedListener
- GlobalExceptionHandler

---

## База данных

Используются миграции Flyway.

### Таблицы

- users
- payments
- fees

### Миграции

```text
V1__init_schema.sql
V2__test_users.sql
```

## Запуск PostgreSQL

### Docker

```bash
docker compose up -d
```

или

```bash
docker run --name payments-postgres \
-e POSTGRES_DB=payments \
-e POSTGRES_USER=postgres \
-e POSTGRES_PASSWORD=postgres \
-p 5433:5432 \
-d postgres:16
```

---

## Конфигурация

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5433/payments
    username: postgres
    password: postgres
```

---

## Запуск приложения

```bash
./gradlew bootRun
```

через IntelliJ IDEA:

```text
PaymentSystemApplication
```

---

## Swagger

После запуска:

```text
http://localhost:8080/swagger-ui.html
```



## Тестирование

### Unit Tests

Проверяется бизнес-логика:

- создание платежа
- расчет комиссии
- запрет перевода самому себе

### Integration Tests

Используются:

- SpringBootTest
- MockMvc
- PostgreSQL
- Flyway

Проверяется полный сценарий работы API.

---

## Обработанные сценарии ошибок

### Пользователь не найден

```json
{
  "message": "User not found: 999",
  "timestamp": "2026-06-09T10:15:30"
}
```

### Перевод самому себе

```json
{
  "message": "Cannot transfer money to yourself",
  "timestamp": "2026-06-09T10:15:30"
}
```

---

## Автор

Mukarapov
