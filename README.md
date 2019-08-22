# spring-boot-booking

## Requisiti

- Docker(servirà in futuro)
- Java 1.8
- Maven

## Avvio applicazione

- `docker-compose up -d`(servirà in futuro)
- `mvn spring-boot:run`

## Regole di dominio

- non si può prenotare il campo in uno slot che è già prenotato
- la prenotazione deve essere di almeno un'ora e massimo tre ore
- il campo è prenotabile dalle 9 alle 23
- la decima prenotazione fatta dall'utente è gratuita
- la conferma deve essere fatta via email e via sms