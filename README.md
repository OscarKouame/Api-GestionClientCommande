# 📦 GestCom - API de gestion de clients et de commandes

## 📝 Description du projet

**GestCom** est un ensemble d’API REST développées avec **Spring Boot** pour gérer les clients et leurs commandes.  
Le projet fournit des endpoints permettant de **créer**, **consulter** et **supprimer** des clients ainsi que leurs commandes associées.

---

## 🗂️ Arborescence technique du projet

```bash
src/
├── main/
│   ├── java/com/testorangeci/gestcom/
│   │   ├── controllers/         # Contrôleurs REST
│   │   ├── services/            # Interfaces et implémentations de la logique métier
│   │   ├── dto/                 # Objets de transfert de données (Data Transfer Objects)
│   │   ├── mappers/         # Convertisseurs DTO <-> Entités
│   │   ├── entities/            # Entités JPA représentant les tables de la base
│   │   ├── repositories/     # Interfaces Spring Data JPA pour la persistance
│   │   └── exceptions/       # Gestion centralisée des erreurs
│   └── resources/
│       ├── application.properties  # Configuration de la base de données et de l'application
│       └── static/                 # Fichiers statiques 
└── test/                           # Tests unitaires et d’intégration


🛠️ Configuration de la base de données et de l application
Fichier : src/main/resources/application.properties

# Nom de l’application
spring.application.name=GestCom

# Configuration PostgreSQL
spring.datasource.url=jdbc:postgresql://localhost:5432/DBGestCom
spring.datasource.username=postgres
spring.datasource.password=postgres
spring.datasource.driver-class-name=org.postgresql.Driver

# JPA / Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect


# Définition du chemin de base (context path) pour toutes les routes REST
# Toutes les URLs exposées par les contrôleurs commenceront par /api
server.servlet.context-path=/api

🚀 Lancement du projet

Dans un terminal, place-toi à la racine du projet et exécute la commande suivante :

bash
mvn spring-boot:run
Le projet démarre sur :
👉 http://localhost:8081

🔄 Exemple de requêtes Postman
Une collection Postman est disponible dans le dossier docs/ :
📁 docs/GestCom.postman_collection.json

🔹 1. Créer un client
POST http://localhost:8081/api/clients

payload
{
  "email": "shulmers@hotmail.fr",
  "firstName": "Toto",
  "lastName": "Tata"
}

🔹 2. Lister tous les clients
GET http://localhost:8081/api/clients

🔹 3. Récupérer un client par ID
GET http://localhost:8081/api/clients/1

🔹 4. Supprimer un client
DELETE http://localhost:8081/api/clients/1

🔹 5. Créer une commande pour un client
POST http://localhost:8081/api/clients/1/orders

payload
{
  "amount": 2000,
  "description": "Commande Imprimante"
}

🔹 6. Lister les commandes d un client
GET http://localhost:8081/api/clients/1/orders

🔹 7. Détail d'une commande
GET http://localhost:8081/api/orders/1

🔹 8. Supprimer une commande
DELETE http://localhost:8081/api/orders/1

✅ Prérequis
Java 17+

Maven 3.8+

PostgreSQL (avec une base nommée DBGestCom créée)

Postman (pour tester les API)

🙌 Auteur
Oscar Kouamé — Développeur Back-End Spring Boot

