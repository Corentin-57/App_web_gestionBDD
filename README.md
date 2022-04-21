# App_web_gestionBDD
Création d'une application web pour gérer une BDD (CRUD) à l'aide de JEE et MySQL

J'ai intégré une page de connexion qui vérifie les identifiants de l'utilisateur et bloque l'accès en affichant un message si les identifiants sont incorrects.

Pour certains j'ai appliqué des restrictions sur le nomnbre de caractères saisies (gestion d'exceptions).

Pour tous les champs de saisie obligatoire j'ai ajouté une condition afin d'afficher un message pour que l'utilisateur complète tous les champs.

J'ai ajouté la possiblité de faire une recherche en sélectionnant que les hommes ou les femmes pour l'onglet joueur.

Sujet :

L’interface doit comporter des onglets (Joueur, Tournoi, épreuve, et match), chaque onglet est lié à l’affichage d’une table de la base de données tennis (utilisation d’un jTable)

Onglet Joueur: 
L’objectif de l’onglet joueur est d’offrir les fonctionnalités suivantes : a- Fonction Ajouter : cette fonction permet l’ajout d’un nouveau joueur. b- Fonction Editer : cette fonction permet la modification d’un joueur. c- Fonction Supprimer : cette fonction permet la suppression d’un joueur L’onglet doit contenir un champ recherche, le client doit pouvoir avoir la possibilité de taper une chaine de caractère dans le champ recherche et voir s’afficher les joueurs qui ont cette chaine contenue dans le nom ou le prénom. L’onglet doit offrir aussi la possibilité d’afficher que les joueurs hommes ou femmes. 

Onglet Tournois L’objectif de l’onglet tournois est d’offrir les fonctionnalités suivantes : a- Fonction Ajouter : cette fonction permet l’ajout d’un nouveau tournoi. b- Fonction Editer : cette fonction permet la modification d’un tournoi. c- Fonction Supprimer : cette fonction permet la suppression d’un tournoi. L’onglet doit contenir un champ recherche, le client doit pouvoir avoir la possibilité de taper une chaine de caractère dans le champ recherche et voir s’afficher les tournois qui ont cette chaine contenue dans le nom ou le code. 

Onglet Matchs L’objectif de l’onglet Match est d’offrir les fonctionnalités suivantes : Avoir la possibilité d’afficher le nom, prénom et sexe des vainqueurs ou des finalistes ainsi qu’un champ de recherche sur le nom ou prénom. 

Onglet Epreuves L’objectif de l’onglet Epreuve est d’offrir les fonctionnalités suivantes : Avoir la possibilité d’afficher le nom et prénom des joueurs qui ont participé à l’année X et au type d’épreuve Y.
