<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%
    String palabra_aleatoria = (String) request.getAttribute("palabra_aleatoria");
    String palabra_oculta = (String) request.getAttribute("palabra_oculta");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Juego del ahorcado</title>
<style type="text/css">
	body {
		font-family: Verdana;
		line-height: 2;
	}
	table, tr, td {
		border: 1px black solid;
		border-collapse: collapse;
	}
	td {
		width: 50px;
		heigth: 50px;
		text-align: center;
	}
</style>
</head>
<body>
	<table>
		<tr>
			<%
			for(int i = 0; i < palabra_aleatoria.length(); i++) {
			%><td><%=palabra_oculta.charAt(i) %></td><%
			}
			%>
		</tr>
	</table>
	<form action="AhorcadoServlet">
		Introduce una letra: <input type="text" name="letra" size="1" maxLength="1"/> 
		<input type="submit" value="Prueba letra"/>
		<input type="hidden" name="palabra" value="<%=palabra_aleatoria %>"/>
		<input type="hidden" name="palabra" value="<%=palabra_oculta %>"/>
	</form>
</body>
</html>