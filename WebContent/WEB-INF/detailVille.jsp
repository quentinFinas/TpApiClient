<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Détail d'une ville </title>
</head>
<body>
	<a href="?commande=listeVilles">Liste des Villes :</a>
	
	<h2>Détail de  : ${ villeDetail.nomCommune }</h2>
	
	<form method="post" action="?commande=updateVille">
		<label for="codeCommune">Code commune : </label>
	    <input type="text" name="codeCommune" id="codeCommune" value="${ villeDetail.codeCommune }" required> <br>
	    
	    <label for="nomCommune">Nom commune : </label>
	    <input type="text" name="nomCommune" id="nomCommune" value="${ villeDetail.nomCommune }"  ><br>
	    
	    <label for="codePostal">Code postal : </label>
	    <input type="text" name="codePostal" id="codePostal" value="${ villeDetail.codePostal }" ><br>
	    
	    <label for="libelleAcheminement">Libelle acheminement : </label>
	    <input type="text" name="libelleAcheminement" id="libelleAcheminement" value="${ villeDetail.libelleAcheminement }" ><br>
	    
	    <label for="ligne">Ligne : </label>
	    <input type="text" name="ligne" id="ligne" value="${ villeDetail.ligne }" ><br>
	    
	    <label for="latitude">Latitude : </label>
	    <input type="text" name="latitude" id="latitude" value="${ villeDetail.latitude }" ><br>
	    
	    <label for="longitude">Longitude : </label>
	    <input type="text" name="longitude" id="longitude" value="${ villeDetail.longitude }" ><br>
	    
	    <p>Météo : ${temp} degrés</p>
	    
	   <input type="submit" value="Mettre à jour"/>
	</form>

</body>
</html>