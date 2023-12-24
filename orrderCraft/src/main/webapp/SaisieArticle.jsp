<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Articles</title>
</head>
<body>
<%@include file="header.jsp" %>
<h3>Saisie d'un article</h3>
<form action="SaveArticle.php" method="post">
<label  >Designation</label>
<input name="designation" value="${article.designation}" type="text" required="required">
<span></span><br><br>
<label  >Prix</label>
<input name="prix" type="text" value="${article.prix}">
<span></span>
<br/><br/>
<label  >Quantite</label>
<input name="quantite" type="text" value="${article.quantite}">
<span></span>
<br/><br/>
<button type="submit">Save</button>
</form>

</body>
</html>