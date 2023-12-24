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
<h3>Confirmation</h3>
<div>
<label>ID:</label>
<label>${commande.id}</label>
</div>
<div>
<label>Numero:</label>
<label>${commande.numeroCommande}</label>
</div>
<div>
<label>status:</label>
<label>${commande.status}</label>
</div>
<div>
<label>createdAtDate:</label>
<label>${commande.createdAtDate}</label>
</div>
<div>
<label>Client:</label>
<label>${client.nom}</label>
</div>


</body>
</html>