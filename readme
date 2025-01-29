# XML-RPC Chat Application

![Java](https://img.shields.io/badge/Java-8%2B-orange)
![XML-RPC](https://img.shields.io/badge/XML--RPC-2.0-blue)
![License](https://img.shields.io/badge/License-MIT-green)

Une application de chat simple basée sur XML-RPC permettant la communication en temps réel entre plusieurs clients via un serveur centralisé.

## Prérequis

- Java 8 ou supérieur
- Apache Ant pour la compilation

## Structure du Projet

| Répertoire | Description |
|------------|-------------|
| `src/`     | Code source de l'application |
| `bin/`     | Classes compilées |
| `lib/`     | Bibliothèques externes |
| `archive/` | Fichiers JAR générés |
| `doc/`     | Documentation JavaDoc |

## Dépendances

Les bibliothèques suivantes sont requises et doivent être présentes dans le répertoire `lib/` :

- `xmlrpc-2.0.jar` - Implémentation XML-RPC
- `commons-codec-1.16.0.jar` - Apache Commons Codec

## Installation et Compilation

1. Clonez le dépôt
2. Assurez-vous que les dépendances sont présentes dans le dossier `lib/`
3. Compilez le projet avec :
   ```bash
   ant all
   ```

## Commandes Disponibles

### Compilation et Construction

- `ant all` - Nettoie, compile et crée tous les fichiers JAR
- `ant clean` - Supprime tous les fichiers générés
- `ant compile` - Compile le code source
- `ant jar-all` - Crée les fichiers JAR (interfaces, client et serveur)
- `ant doc` - Génère la documentation JavaDoc

### Exécution

- `ant server` - Lance le serveur de chat
- `ant client` - Lance un client de chat

## Utilisation

1. Démarrez d'abord le serveur :
   ```bash
   ant server
   ```

2. Dans un nouveau terminal, lancez un client :
   ```bash
   ant client
   ```

3. Répétez l'étape 2 pour chaque client supplémentaire

## Documentation

Pour générer la documentation JavaDoc, exécutez :
```bash
ant doc
```
La documentation sera disponible dans le répertoire `doc/`.