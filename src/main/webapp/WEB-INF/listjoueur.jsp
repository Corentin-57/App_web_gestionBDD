<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>

<html lang="fr">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link rel="stylesheet" href="starter-template.css">
    <title>Liste des joueurs</title>
    <style>
    	.nomUtilisateur{
    		margin-left: 50px;
    		font-size: 20px;
    		font-weight: bold;
    		color: #177EF3;
    	}
    </style>
  </head>
  <body>
<%@ include file="menu.jsp" %>

<p class="nomUtilisateur">Bienvenue <c:out value="${connectedUser.login}"/>&nbsp;!</p> <!--  Afiche le login de l'utilisateur-->

<main role="main" class="container">

  <div class="starter-template">
    <h1>Liste des joueurs</h1>
    <p class="lead">Bienvenue sur la page qui liste les joueurs de tennis professionnels. Vous retrouvez leur nom, prénom et sexe. Vous avez la possibilité de modifier ou supprimer les joueurs à l'aide des boutons sur la ligne du joueur.<br>Vous pouvez également ajouter un nouveau joueur via le bouton "Ajouter un joueur".</p>
  </div>
  
    <form class="form-inline my-2 my-lg-0 justify-content-end" action="listjoueur" method="post">
      <input class="form-control mr-sm-2" type="text" name="txtsearch" placeholder="Nom / Prenom" aria-label="Search" required>
      <button class="btn btn-secondary my-2 my-sm-0" type="submit" name="action1" value="Rechercher" >Rechercher</button>
    </form>

</main><!-- /.container -->
<div class="container">

<div style="    padding: 1.5rem;    margin-right: 0;    margin-left: 0;    border-width: .2rem;">
<a class="btn btn-primary" href="/App_joueurs/ajouterJoueur" role="button">Ajouter un joueur</a>
</div>

<table class="table">
  <thead>
    <tr>
      <th scope="col" style="width:10%">#</th>
      <th scope="col" style="width:25%">Nom</th>
      <th scope="col" style="width:20%">Prenom</th>
      <th scope="col" style="width:20%">Sexe</th>
	  <th scope="col" style="width:20%"></th>
    </tr>
  </thead>
  <tbody>
  	<c:if test="${nboccurence == 0 }"> <--! Affiche le message si la liste est vide -->
  		<tr>
	  		<td colspan="5" style="text-align:center">
	  			Il n'y a pas de joueur
	  		</td>
  		</tr>
  	</c:if>
  	
    <c:forEach var="joueur" items="${ joueurs }">
  		<tr>
  			<td><c:out value="${ joueur.id }"/></td>
			<td><c:out value="${ joueur.nom }"/></td>
  			<td><c:out value="${ joueur.prenom }"/></td>
  			<td><c:out value="${ joueur.sexe }"/></td>
  			
  			<td>

<!-- 			    <button type="button" class="btn btn-outline-primary">Modifier</button>
 -->				 <a type="submit" class="btn btn-outline-primary" href="/App_joueurs/modifierJoueur?id=${ joueur.id }" role="button"  >Modifier</a>
 				<a type="button" class="btn btn-outline-warning" href="/App_joueurs/supprimerJoueur?id=${joueur.id }">Supprimer</a>
	 		</td>
  		</tr>
  	</c:forEach>
  </tbody>
</table>
</div>

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js0/di/u..md/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
  </body>
</html>


