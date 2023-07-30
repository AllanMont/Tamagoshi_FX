Tamagoshi

Tamagoshi est une application console qui permet de jouer au célèbre jeu des Tamagoshis. Le but du jeu est de s'occuper d'un Tamagoshi en veillant à ses besoins, tels que la nourriture et les loisirs, tout en veillant à son bien-être général.

Le projet est écrit en Java et utilise le système de construction Gradle pour gérer les dépendances et construire l'application.
Prérequis

Pour exécuter Tamagoshi, vous devez avoir Java 8 (ou une version ultérieure) installé sur votre ordinateur.
Installation et lancement

    Clonez le dépôt à l'aide de la commande suivante : git clone https://gitlabinfo.iutmontp.univ-montp2.fr/montagnea/javaFX_Tama
    Accédez au répertoire du projet : cd JavaFXTama
    Exécutez le projet à l'aide de la commande suivante : ./gradlew run

Fonctionnalités

L'application Tamagoshi comprend plusieurs classes pour simuler le jeu Tamagoshi. Voici une brève présentation des principales classes :

    Tamagoshi : représente un Tamagoshi avec un nom, une quantité d'énergie et de fun, et un âge. Un Tamagoshi peut manger, jouer, vieillir et consommer de l'énergie et du fun.
    TamaAccueil : permet d'accueillir le joueur et de l'inviter à créer un nouveau Tamagoshi ou à charger un Tamagoshi existant.
    TamaCreation : permet de créer un nouveau Tamagoshi en demandant à l'utilisateur d'entrer son nom.
    TamaGame : représente le coeur du jeu Tamagoshi, où l'utilisateur peut interagir avec son Tamagoshi en lui faisant manger, jouer, vieillir, etc.
    ScoreManager : permet de gérer les scores des joueurs en gardant une trace du score le plus élevé.

Dans le jeu Tamagoshi, chaque tour est limité en nourriture et jouets, ce qui signifie que le joueur doit être stratégique dans la gestion des ressources pour maintenir le bien-être de son Tamagoshi.
