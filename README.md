# Sujet

[SUJET.md](./SUJET.md)

# Setup

[💻 DEV.md](./DEV.md)

# DealHunter test

## Functional tests

### Requirements

- Database Running
- API Running
- NewMan installed (https://learning.postman.com/docs/running-collections/using-newman-cli/installing-running-newman/)

### Run

```bash
newman run src/test/functional/DealHunter_FunctionalTests.json
```

## Front 

https://github.com/TresAmigosI2L/dh-front

http://localhost:5173/login

![img.png](docs/front_screen.png)

## Demo account
```
{
    "username":"axel.lebas@decathlon.com",
    "password":"xmn"
}
```
## Unit tests

> See DealControllerTest

## Functionnal tests

> See DealHunter_FunctionnalTests.json

# Schema architecture N-Tiers

![alt text](docs/schema_archi_n_tiers.jpg)

Lien vers l'architecture : [Dealhunter.drawio](https://app.diagrams.net/#G1JV7frwOrxJv80vo7KtEiK9-571BISpfK)

# Schema DB
![img.png](docs/diagram_db.png)