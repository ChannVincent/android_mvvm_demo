# android_mvvm_demo

## Sujet 

Vous devez réaliser une application native Android affichant la liste des albums suivant : https://img8.leboncoin.fr/technical-test.json

## Prérequis 

- [ok] Le projet est à réaliser sur la plateforme Android (API minimum 15)
- Vous devez implémenter un système de persistance des données afin que les données puissent être disponibles offline, même après redémarrage de l'application.
- Vous êtes libre d'utiliser le langage et les librairies que vous voulez, mais vous devez expliquer vos choix
- Votre code doit être versionné sur un dépôt Git librement consultable

## Nous observerons particulièrement

- Les choix d'architecture 
- Les choix de patterns
- La gestion des changements de configuration
- Les performances de l'application

# Action plan 

## Roadmap (english commits & roadmap)

- LBC-01 : init project
- LBC-02 : setup navigation
- LBC-03 : data & http call

## Choix techniques

**Navigation**

Standard de navigation entre les différentes vues de l'application.
Tout les workflow de navigation sont contenu dans le fichier "mobile_navigation.xml".
cf. https://developer.android.com/topic/libraries/architecture/navigation/navigation-implementing

**Retrofit**

Librairie crée par Square afin de gérer des appels à des webservices de manière simple et structurée.
Cette librairie est aujourd'hui un standard du développement Android.

**Coroutines**

Elles offrent de meilleures performances que des Threads ou autres AsyncTasks et une utilisation des resources CPU inférieures. Elles sont utilisées pour des traitements d’arrière-plan (appels réseaux, traitements lourds, etc.).

**AndroidX**

Nouveau set de librairies support crées pour remplacer les anciennes librairies supports v4, v7, v13, ...
Elles sont devenus le nouveau standard et recevrons toutes les nouvelles mise à jour de librairies support Android.
