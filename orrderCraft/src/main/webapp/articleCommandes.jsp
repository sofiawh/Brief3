<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Articles Commandes</title>
</head>
<body>
<%@include file="header.jsp" %>>
<form action="chercherArtCmd.php" method="get">
<label>Mot Cle</label>
<input type="text" name="motCle" value="${model.motCle}"/>
<button type="submit">chercher</button>
</form>

<table>
<tr>
<th>ID</th><th>Nom</th><td>Email</td><td>Adresse</td><td>Tel</td>
</tr>
<c:forEach items="${model.clients}" var="p">
<tr>
  <td>${p.id}</td>
  <td>${p.nom}</td>
  <td>${p.email}</td>
  <td>${p.adresse}</td>
  <td>${p.tel}</td>
  <td><a href="PasserCommande.php?id=${p.id}">Passer Commande</a></td>
  </tr>
</c:forEach>

</table>



</body>
</html>