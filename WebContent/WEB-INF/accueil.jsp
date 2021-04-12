<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Accueil</title>
</head>
<body>
	<a href="?commande=listeVilles">Liste des Villes :</a> 
	
	
	<h2>Calcul de distance :</h2>

	<form method="post" action="?commande=calcD">

		<label for="ville1">Ville 1:</label> <select name="ville1" id="ville1">
			<option value="choose">--Merci de choisir une ville--</option>
			<c:forEach items="${ ville }" var="ville" varStatus="status">
				<option value="${ ville.codeCommune }">${ ville.codeCommune }: ${ ville.nomCommune }</option>
			</c:forEach>
		</select> <label for="ville2">Ville 2:</label> <select name="ville2" id="ville2">
			<option value="choose">--Merci de choisir une ville--</option>
			<c:forEach items="${ ville }" var="ville" varStatus="status">
				<option value="${ ville.codeCommune }">${ ville.codeCommune }: ${ ville.nomCommune }</option>
			</c:forEach>
		</select>
		<input type="submit" value="Calculer la distance"/>

	</form>

	

	<c:if test="${ distance > 0 }" var="variable">
		<c:out value="La distance entre ${ville1} et ${ville2} est de ${ distance} km" />
	</c:if>
	<c:if test="${ erreur != '' }" var="variable">
		<c:out value="${ erreur}" />
	</c:if>
	<br>
	
	<h2>Ajout d'une ville :</h2>
	
	<form method="post" action="?commande=addVille">
		<label for="codeCommune">Code commune : </label>
	    <input type="text" name="codeCommune" id="codeCommune" required> <br>
	    
	    <label for="nomCommune">Nom commune : </label>
	    <input type="text" name="nomCommune" id="nomCommune" required><br>
	    
	    <label for="codePostal">Code postal : </label>
	    <input type="text" name="codePostal" id="codePostal" required><br>
	    
	    <label for="libelleAcheminement">Libelle acheminement : </label>
	    <input type="text" name="libelleAcheminement" id="libelleAcheminement" required><br>
	    
	    <label for="ligne">Ligne : </label>
	    <input type="text" name="ligne" id="ligne" required><br>
	    
	    <label for="latitude">Latitude : </label>
	    <input type="text" name="latitude" id="latitude" required><br>
	    
	    <label for="longitude">Longitude : </label>
	    <input type="text" name="longitude" id="longitude" required><br>
	    
	   <input type="submit" value="Ajoutez"/>
	</form>
	
	<h2>Suppresion d'une ville :</h2>
	
	<form method="post" action="?commande=deleteVille">
		<label for="codeCommune">Ville 1:</label> <select name="codeCommune" id="codeCommune">
			<option value="">--Merci de choisir une ville--</option>
			<c:forEach items="${ ville }" var="ville" varStatus="status">
				<option value="${ ville.codeCommune }">${ ville.codeCommune }: ${ ville.nomCommune }</option>
			</c:forEach>
		</select>
		<input type="submit" value="Supprimer"/>
	</form>
	
	

</body>
</html>