<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Liste des villes</title>
</head>
<body>


	<h2>Liste des villes :</h2><br>
	<a href="?commande=listeVilles&listCmd=moins&maxlist=${maxlist}&minlist=${minlist}">- 50 </a>
	<a href="?commande=accueil">Retour</a>
	<a href="?commande=listeVilles&listCmd=plus&maxlist=${maxlist}&minlist=${minlist}">+ 50</a>
	
	<table>
		<tr>
			<td>Code commune</td>
			<td>Nom commune</td>
			<td>Code Postal</td>
			<td>Libelle acheminement</td>
			<td></td>
		</tr>
		<c:forEach var="i" begin="${sessionScope.minlist}" end="${sessionScope.maxlist}" step="1">
			<c:if test="${ville[i].codeCommune!=null && ville[i].codeCommune!=''}" var="variable">			    	
				<tr>
					<td><c:out value="${ ville[i].codeCommune}" /> </td>
					<td><c:out value="${ ville[i].nomCommune}" /></td>
					<td><c:out value="${ ville[i].codePostal}" /></td>
					<td><c:out value="${ ville[i].libelleAcheminement}" /></td>
					<td><a href="?commande=focusVille&codeCommune=${ville[i].codeCommune}">détail</a></td>
				</tr>
			</c:if>
		</c:forEach>
	</table>

</body>
</html>