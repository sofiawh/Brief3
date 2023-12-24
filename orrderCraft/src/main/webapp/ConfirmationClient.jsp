<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Clients</title>
</head>
<body>
<%@include file="header.jsp" %>
<h3>Confirmation</h3>
<div>
<label>ID:</label>
<label>${client.id}</label>
</div>
<div>
<label>Nom:</label>
<label>${client.nom}</label>
</div>
<div>
<label>Email:</label>
<label>${client.email}</label>
</div>
<div>
<label>Adresse:</label>
<label>${client.adresse}</label>
</div>
<div>
<label>Tel:</label>
<label>${client.tel}</label>
</div>


</body>
</html>