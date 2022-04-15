<!doctype html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <!-- Pour utiliser c:if -->
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
    <h1>Ajouter un joueur</h1>
    <p>Vous pouvez ajouter un joueur directement en remplissant les rubriques ci-dessous,<br>n'oubliez pas de cliquer sur le bouton "Ajouter" </p>
    <p class="lead"><c:if test="${ !empty erreur }"><p style="color:red;"><c:out value="${ erreur }" /></p></c:if>
  </div>
   <div style="width:40%; margin:auto;"> 

<form class="needs-validation "  novalidate method="post" action="ajouterJoueur">
  <div class="form-row">
    <div class="col-md-4 mb-3">
      <label for="validationCustom01">Nom du joueur</label>
      <input type="text" class="form-control" style="width:400px;" id="validationCustom01" name="txtNom" required>
	  <div class="valid-feedback">
        Tr�s bien!
      </div>
      <div class="invalid-feedback">
        Saisir le nom !
      </div>
    </div>
  </div> 
  <div class="form-row">
	<div class="col-md-4 mb-3">
	  <label for="validationCustom02">Pr�nom du joueur</label>
	  <input type="text" class="form-control" style="width:400px;" id="validationCustom02" name="txtPrenom" required>
	  <div class="valid-feedback">
       Tr�s bien!
     </div>
     <div class="invalid-feedback">
        Saisir le prenom !
      </div>
	</div>
   </div> 
  <div class="form-row">
    <div class="col-md-3 mb-3">
      <label for="validationCustom04">Sexe</label>
      <select class="custom-select" id="validationCustom04" name="opSexe" style="width:400px;" required>
        <option selected disabled value="" >S�lectioner...</option>
        <option value="F">Femme</option>
		<option value="H">Homme</option>
      </select>
	   <div class="valid-feedback">
       Tr�s bien!
     </div>
      <div class="invalid-feedback">
        Choisir le sexe !
      </div>
    </div>  
    </div>
  <div class="d-flex justify-content-center">
  	<button class="btn btn-primary center" type="submit">Ajouter</button>
  </div>
</form>

   </div>

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


