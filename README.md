# testMessageBroker2

для запуска сервиса выполнить команнду в терминале:./mvnw spring-boot:run
сервис получает сообщения из очереди брокера сообщений RabbitMQ

сообщения получаются посредством GET запроса по адресу http://localhost:8081/message

полученный json записывается в виде xml в фаил src/main/resources/file.xml
