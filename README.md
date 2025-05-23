Микросервис для управления пользователями и их подписками на цифровые сервисы (Netflix, YouTube Premium и др.), реализованный на Spring Boot 3 и Java 17.

---

## 📋 Основные функции (по ТЗ)

- **Управление пользователями**:
    - Создание, обновление, удаление пользователей.
- **Управление подписками**:
    - Добавление/удаление подписок.
    - Получение списка подписок пользователя.
    - ТОП-3 популярных сервисов.
- **Интеграция с PostgreSQL**:
    - Таблицы: `users`, `subscriptions`, `platforms`.
- **Логирование**:
    - SLF4J с детализацией операций (создание, обновление, удаление).
- **Docker**:
    - Готовые конфигурации для развертывания приложения и БД.

---

## 🛠 Дополнительные улучшения
0. **Расширенная бизнес-логика**: 
   - Валидация данных (уникальность email и имени).
   - реализована логика класса `Platform` (цифровые сервисы) с соответствующим  контроллером. 
   - Использование DTO и мапперов для передачи данных.
1. **Swagger UI**:
    - Автоматическая документация API доступна по адресу: `http://localhost:8080/swagger-ui.html`.
   - Подробное описание эндпоинтов и параметров.
2. **Контроллер платформ**:
    - Создание, удаление, получение информации о цифровых сервисах (например, Netflix).
    - Автоматическая инициализация тестовых данных (YouTube Premium, Netflix) при старте.
3. **Статистика**:
    - Просмотр количества подписчиков для каждой платформы.
4. **Глобальная обработка ошибок**:
    - Кастомные сообщения для `404 Not Found`, `409 Conflict`, `403 Forbidden`.

---

## 🚀 Запуск проекта

### Требования:
- Docker и Docker Compose.
- Java 17.

### Инструкция:
1. Склонируйте репозиторий:
   ```bash
   git clone https://github.com/TeacherGOD/UserSubscriptionsTest.git
   ```
2. Перейдите в директорию проекта:
    ```bash
    cd UserSubscriptionsTest
    ```
3. Запустите контейнеры:
    ```bash
    docker-compose up --build
    ```
   - Приложение будет доступно на http://localhost:8080.

   - БД PostgreSQL: порт `5432`, имя БД: `user_subscriptions_db`, пользователь: `postgres`, пароль: `1`.

## 📡 Тестирование API
### Лучше всего тестировать в Swagger, но вот пример пары запросов:
### Создание пользователя:
```bash
curl -X POST http://localhost:8080/users \
-H "Content-Type: application/json" \
-d '{"name": "Ivan", "email": "ivan@example.com", "password": "secret"}'
```
### Добавление подписки:
```bash
curl -X POST http://localhost:8080/users/1/subscriptions \
-H "Content-Type: application/json" \
-d '{"platformId": 1}'
```
### Получение ТОП-3 подписок:
```bash
curl http://localhost:8080/subscriptions/top
```

## Документация:
   Откройте Swagger UI: http://localhost:8080/swagger-ui.html.
   
## Контакты:
Для связи можете использовать почту: `growder1@gmail.com`  
или Tg: `https://t.me/LeoTaurum`