<%--
  Created by IntelliJ IDEA.
  User: tplocal
  Date: 18/01/2022
  Time: 12:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="utilisateur" type="modele.Utilisateur" scope="session" />
<html>
<head>
    <title>menu</title>
</head>
<body>
<h1>Bienvenue ${uitlisateur.login}</h1>
<ul>
    <li><a href="/home/parisouverts">Afficher les matchs à parier</a></li>
    <li><a href="/home/mesparis">Mes paris</a></li>
    <li><a href="/home">Deconnexion</a></li>
</ul>
</body>
</html>
