# Запуск авто-тестов

### ПО, необходимое для запуска авто-тестов:

1. Для Windows ниже 10 Pro: Docker Toolbox (Oracle VM VirtualBox и Docker Quickstart Terminal);
2. Для Linux, Mac OS и Windows 10 Pro: Docker.

#### Важно:

Код тестов БД написан под Docker Toolbox, для успешного запуска тестов на Linux, Mac OS и Windows 10 Pro **IP-адрес 192.168.99.100** необходимо заменить на **localhost**.

---

### Шаги для запуска авто-тестов (Windows 10):

1. Запустить Oracle VM VirtualBox, в которой в фоновом режиме запустить машину "default".
2. Запустить Docker Quickstart Terminal.

В IDE необходимо совершить следующие шаги:

1. Находясь в корневой папке проекта ввести в консоль команду **docker-compose up** для запуска образов БД и симулятор банковских сервисов.
2. Запустить тестируемое приложение c БД MySQL командой **java -Dspring.datasource.url=jdbc:mysql://192.168.99.100:3306/app -jar aqa-shop.jar**.
3. Запустить тесты командой **gradlew test -Ddb.url=jdbc:mysql://192.168.99.100:3306/app**.
4. Сгенерировать отчет Allure командой **gradlew allureReport**.
5. Запустить тестируемое приложение командой с БД PostgreSQL **java -Dspring.datasource.url=jdbc:postgresql://192.168.99.100:5432/app -jar aqa-shop.jar**.
6. Запустить тесты командой **gradlew test -Ddb.url=jdbc:postgresql://192.168.99.100:5432/app**.
7. Сгенерировать отчеты Allure командой **gradlew allureReport**.
