# spring-boot-booking

## Requisiti

- Docker
- Java 1.8
- Maven
- Aggiungere il seguente host tra quelli predefiniti di sistema(`/etc/hosts/`)
`127.0.0.1 spring-boot-booking_mysql_1`

## Avvio applicazione

- `docker-compose up -d`
- `mvn spring-boot:run`

## Regole di dominio

- non si può prenotare il campo in uno slot che è già prenotato
- la prenotazione deve essere di almeno un'ora e massimo tre ore
- il campo è prenotabile dalle 9 alle 23
- la decima prenotazione fatta dall'utente è gratuita
- la conferma deve essere fatta via email e via sms

# Tools

- Adminer: http://localhost:8081/
    - server: spring-boot-booking_mysql_1
    - user: dev
    - password: dev
