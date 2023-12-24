<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Commandes</title>
</head>
<body>
<%@include file="header.jsp" %>
<h1>Saisie d'une Commande</h1>
<h5> ClientID: ${client.id}</h5>
<h5> Client: ${client.nom}</h5>
<h5> Adresse: ${client.adresse}</h5>
<h5> Email: ${client.email}</h5>
<form action="SaveCommande.php" method="post">
<label  >Numero</label>
<input name="Numero" value="${commande.numeroCommande}" type="text" required="required">
<span></span><br><br>
<label  >Date</label>
<input name="date" type="text" value="${commande.createdAtDate}">
<span></span>
<br/><br/>
<label  >Status</label>
  <label for="options">Status</label>
    <select name="Status" id="Status">
        <option value="false" ${commande.status eq 'false' ? 'selected' : ''}>En cours</option>
        <option value="true" ${commande.status eq 'true' ? 'selected' : ''}>Complete</option>
    </select>
<!--  label  >Status</label>
<input name="Status" type="text" value="${commande.status}"-->
<span></span>
<br/><br/>

<button type="submit">Save</button>
</form>


</body>
</html>