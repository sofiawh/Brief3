<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" %>
<%@ page import="com.model.Commande" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Articles</title>
</head>
<body>
<%@include file="header.jsp" %>
<h3>Saisie d'un articleCommande</h3>

 ClientID: ${commande.client.id}
 Client: ${commande.client.nom}
 Adresse: ${commande.client.adresse}
 Email: ${commande.client.email}
<br><br>
 NumeroCommande: ${commande.numeroCommande}
 CreatedAtDate: ${commande.createdAtDate}
 Status: ${commande.status}
<br><br>

<form action="SaveArticleCommande.php" method="post">
<label  >Quantite</label>
<input name="quantite" value="${articleCommande.quantite}" type="text" required="required">
<span></span>
<label  >Article</label>
<input name="articleId" type="text" value="${articleCommande.articleId}">
<span></span>
<button type="submit">Save</button>
</form>
<table>
<tr>
<th>Quantite</th><th>SousTotal</th><td>Article</td>
</tr>
<c:forEach items="${model.articleCommandes}" var="p">
<tr>
  <td>${p.quantite}</td>
  <td>${p.sousTotal}</td>
  <td>${p.article.designation}</td>
  <!--  td><a onclick="return confirm('Etes vous sure?')"href="Supprimer.php?id=${p.id}">Supprimer</a></td>
-->
  </tr>
</c:forEach>
<br>

</table>
Total: ${total}
</body>
</html>