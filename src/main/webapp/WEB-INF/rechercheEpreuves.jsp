<!doctype html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="fr">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link rel="stylesheet" href="starter-template.css">
    <title>Gestion BDD</title>
  </head>
  <body>
    <%@ include file="menu.jsp" %>

<main role="main" class="container">

  <div class="starter-template">
    <h1>Rechercher les joueurs par type d'épreuve et année</h1>
    <p>Vous pouvez rechercher les joueurs qui ont participé aux épreuves par anéee et par type.</p>
    
    <p class="lead"><c:if test="${ !empty erreur }"><p style="color:red;"><c:out value="${ erreur }" /></p></c:if>
  </div>
  
   <div style="width:40%; margin:auto;"> 
<form class="needs-validation "  novalidate method="post" action="rechercheEpreuves">
  <div class="form-row">
    <div class="col-md-3 mb-3">
      <label for="validationCustom04">Type</label>
      <select class="custom-select" id="validationCustom04" name="type" style="width:400px;" required>
        <option selected disabled value="">Sélectioner...</option>
        <option value="H">Homme</option>
		<option value="F">Femme</option>
      </select>
      <div class="invalid-feedback">
        Choisir un sexe!
      </div>
    </div>  
    </div>
    
    <div class="form-row">
	<div class="col-md-4 mb-3">
      <label for="validationCustom05">Année</label>
      <input class="form-control mr-sm-2" type="text" name="année" aria-label="Search" id="validationCustom05" required>
     <div class="invalid-feedback">
        Saisir une année !
      </div>
	</div>
   </div>
    
      <div class="d-flex justify-content-center">
      	<button class="btn btn-primary center" style="margin-bottom: 30px" type="submit">Rechercher</button>
      </div>
      
</form>

   </div>
   
   <table class="table">
  <thead>
    <tr>
      <th scope="col" style="width:25%">Nom</th>
      <th scope="col" style="width:20%">Prenom</th>
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
			<td><c:out value="${ joueur.nom }"/></td>
  			<td><c:out value="${ joueur.prenom }"/></td>
  		</tr>
  	</c:forEach>
  </tbody>
</table>

</main><!-- /.container -->

<script>
// Example starter JavaScript for disabling form submissions if there are invalid fields
(function() {
  'use strict';
  window.addEventListener('load', function() {
    // Fetch all the forms we want to apply custom Bootstrap validation styles to
    var forms = document.getElementsByClassName('needs-validation');
    // Loop over them and prevent submission
    var validation = Array.prototype.filter.call(forms, function(form) {
      form.addEventListener('submit', function(event) {
        if (form.checkValidity() === false) {
          event.preventDefault();
          event.stopPropagation();
        }
        form.classList.add('was-validated');
      }, false);
    });
  }, false);
})();
</script>


    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
  </body>
</html>




</body>
</html>




