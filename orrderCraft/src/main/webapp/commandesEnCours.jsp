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

<table>
<tr>
<th>ID</th><th>NumeroCommande</th><td>Status</td><td>CreatedAtDate</td><td>Client</td><td>ArticlesCommandes</td></tr>
<c:forEach items="${model.commandes}" var="p">
<tr>
  <td>${p.id}</td>
  <td>${p.numeroCommande}</td>
  <td>
    ${p.status ? 'Complete' : 'En cours'}
  </td>
  <td>${p.createdAtDate}</td>
  <td>${p.client.nom}</td> 
  <td><a href="EditCmd.php?id=${p.id}">Marquer comme COMPLETE</a></td>
  </tr>
</c:forEach>

</table>
</body>
</html>