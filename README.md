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
- LBC-04 : mvvm architecture
- LBC-05 : ui : recyclerview & performance

## Choix techniques

**Architecture MVVM**

L'architecture MVVM (Model View ViewModel) est une architecture récemment mise en avant par Google avec l'avènement du "LiveData".
- Le "LiveData" est un observeur qui est lié au cycle de vie des différents composants (activité, fragment, service) et notifie ses observeurs de tout changement sur l'élément de data qu'il observe.
- Le "DataModel" gère la données venant d'un appel à une API ou d'une base de donnée ou autre. Il est le seul point d'entrée de la donnée et gère la "DataBase Logic".
Cette couche récupère la donnée et l'expose au ViewModel via des observeurs.
- Le "ViewModel" est attaché au cycle de vie de son composant (fragment, activité) et est conservé après une rotation de l'écran. Ce qui évite par exemple de lancer 2 appels à votre API à cause d'une rotation d'écran.
Son travail est d'observer la donnée fournit par le DataModel et de l'exposer à la vue via des observeurs.
- La "View" (activité, fragment) observe les données exposées par le ViewModel et les affiche à l'écran
cf. https://android.jlelse.eu/android-architecture-pattern-components-mvvm-livedata-viewmodel-lifecycle-544e84e85177

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

**Room**

Librairie qui facilite l'utilisation d'SQLLite en offrant :
- check de l'implémentation à la compilation
- fonctionne bien avec notre architecture basée sur le "LiveData"
- moins de boilerplate code
- test et implémentation simple
cf. https://medium.com/mindorks/android-architecture-components-room-and-kotlin-f7b725c8d1d

**Kodein**

Librairie d' "injection de dépendence" pour Kotlin.
cf. https://proandroiddev.com/dependency-injection-with-kotlin-kodein-koin-3d783745e48d