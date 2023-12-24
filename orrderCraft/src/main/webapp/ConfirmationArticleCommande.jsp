<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>ArticleCommande</title>
</head>
<body>
<%@include file="header.jsp" %>
<h3>Confirmation</h3>

<div>
<label>Quantite:</label>
<label>${artCommande.quantite}</label>
</div>
<div>
<label>SousTotal</label>
<label>${artCommande.sousTotal}</label>
</div>
<div>
<label>Article:</label>
<label>${artCommande.article.designation}</label>
</div>
<div>
<label>Quatite:</label>
<label>${artCommande.quantite}</label>
</div>


</body>
</html>