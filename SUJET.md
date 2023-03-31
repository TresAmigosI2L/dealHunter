# Projet: Architecture multi tier avec JEE

À la fin du TP, vous serez, si mon job est un succès, capable d'implémenter un 3-Tier à l'aide de Java EE.
Nous utiliserons Spring comme framework web. Au choix postgresql, SQLite ou bien mysql comme base de données.

Seul ou par groupe de deux.

### Rendu

Le minimum:

- [X] Le schéma de votre infrastructure n-tier
- [X] Une base de données avec au moins deux tables (On ne compte pas les tables de jointures). Avec une ou des relations.
- [X] Le modèle de la base lié grâce à Hibernate et Spring Data.
- [X] Le projet devra compiler pour java 11+ à l'aide de Maven.
- [X] Une authentification en Basic avec Spring Sécurité. => on a poussé vers une OAUTH2 avec Bearer
- [X] Au moins deux endpoints correspondant à des besoins utilisateurs.
- [X] Une API utilisant JSON et Spring REST.
- [X] La partie front qui appelle l'API.


BONUS:

- [X] Une suite de tests Spring.
- [ ] Une web socket.
- [ ] Une CI avec Jenkins ou Git Action qui compile le code et lance les tests.
- [ ] Mise en place d'un cache guava.
- [ ] La CD est déployée et hostée.

### Quelques rappels

- Effectuer un schéma de votre organisation !
- Diviser correctement les différentes couches de votre projet.
- Pensez à utiliser l'API des `Collection`s et des `Stream`s de Java pour rendre vos transformations facilement
  lisibles !
- Utiliser une [convention de rangement](../cours/spring.md#mettre-un-peu-dordre-dans-tout-ça) dans votre projet Spring.

### Idée de sujets

- Celui du cours, un journal.
- **Un shop (amazon, dealabs).**
- Un réseau social.
- Un blog.