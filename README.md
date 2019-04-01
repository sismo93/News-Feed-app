# FeedBuzz : Projet de génie logiciel et gestion de projet (INFO-F-307)


Ce logiciel a pour but de regrouper des articles selon les préférences d'un utilisateur. Il permet l'ajout d'articles à partir de plusieurs journaux ([The Guardian](https://www.theguardian.com), [LeMonde](https://www.lemonde.fr/), [RTLbe](https://www.rtl.be)...) sur plusieurs catégories (actualités, politique, international, sport...). Ce logiciel permet aussi aux utilisateurs de créer un compte et de se déconnecter.

# Utilisation

**Dependency Management:** [Maven](https://maven.apache.org/)  
**Java version**: Java 8  
**Intellij Idea version**: 2018.2  

**Maven dependencies**:
 - SQLite JDBC (version 3.7.2)
 - TestFX Core (version 4.0.13-alpha)
 - TestFX Junit (version 4.0.13-alpha)
 - Junit Jupiter (version 5.3.1)
 - Junit Jupiter API (version 5.4.0)
 - Jsoup (version 1.7.0)
 - JFoenix (version 8.0.8)

**Libraries**:
 - [JavaFX 11](https://openjfx.io/)
 - [JFoenix](https://github.com/jfoenixadmin/JFoenix) (Material Design for JavaFX)
 - Java SQL (pour le management SQLite)
 - Java Util
 - Java Net
 - Java IO

# Installation
Pre-requisite software (if on Windows):

 - Java SDK 8u202, available here: [https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
 - GIT, available here: [https://git-scm.com/downloads](https://git-scm.com/downloads)
 - Maven, available here: [https://maven.apache.org/download.cgi](https://maven.apache.org/download.cgi)

N.B: Please follow [this article](https://www.mkyong.com/maven/how-to-install-maven-in-windows/) on how to add Maven into **Windows** command line.  
N.B #2: Please follow [this article](https://www.javahelps.com/2017/10/install-apache-maven-on-linux.html) on how to add Maven into **Linux** command line.
All standard Linux distributions come with Java & Git pre-installed (except "Light" ones).

## Instructions
Veuillez suivre les instructions pour cloner le projet:

 - `git clone https://gitlab.com/infof307-1819/groupe05.git`
 - `cd groupe05/`


## Compilation
Pour compiler l'application, il suffit de lancer la commande suivante:

 - `mvn compile`

## Démarrage 

Pour exécuter l'application, il suffit de lancer le fichier `g05-iteration-2.jar` disponible dans le dossier `dist` du dépôt Gitlab.

# Configuration :

## Serveur 
Aucune configuration est nécessaire pour le serveur. La base de donnée est créée automatiquement lors de l’exécution de l'application.

## Client

Aucune configuration est nécessaire pour le client.

# Tests

Pour exécuter les tests, il suffit de suivre les instructions ci-dessous:

 - `mvn clean test`

Si les tests ne sont pas détectés par Maven, ouvrez le projet avec Intellij IDEA et suivez les instructions ci-dessous:

 - `Au niveau de la structure du projet, double cliquez sur le dossier "test"`
 - `Faîtes un clique droit sur le package "be.ac.ulb.infof307.g05"`
 - `Cliquez ensuite sur "Run Tests in 'be.ac.ulb.infof307.g05'"`

Tous les tests seront exécutés.

# Misc

# Convention de nommage

## Développement
Projet développé par:  
Yamani Imad Eddine, Bounamar Mounir, Mustapha Cherrabi, Tanvirul Hoque, Wassim Kezai, Orestis Tranganidas, Wassil Choujaa, Vincent Auberic Tombou

## Screenshot
![Welcome view](https://i.imgur.com/pSrzWqT.png)

![Registration](https://i.imgur.com/ZFaEr96.png)

![Main menu](https://i.imgur.com/Tk7gWXV.png)

![Add articles](https://i.imgur.com/vhyY6gH.png)

![Feed view](https://i.imgur.com/KhVA9bG.png)

![Article preview](https://i.imgur.com/LcK3Tzr.png)

## License
[Apache License, Version 2.0](https://www.apache.org/licenses/LICENSE-2.0)



