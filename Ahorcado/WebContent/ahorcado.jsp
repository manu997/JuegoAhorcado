<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" session="true"%>
    <%
    String palabra_aleatoria = (String) request.getAttribute("palabra_aleatoria");
    String palabra_oculta = (String) request.getAttribute("palabra_oculta");
    char letra = (char) request.getAttribute("letra");
    if(letra == '\0') {
    	letra = '\0';
    }
    String letra_correcta = (String) request.getAttribute("letra_correcta");
    if(letra_correcta == null) {
    	letra_correcta = "";
    }
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
				if(letra == palabra_aleatoria.charAt(i)) {
					%><td><%=letra %></td><%
				} else {
					%><td>-</td><%
				}
			}
			%>
		</tr>
	</table>
	<form action="AhorcadoServlet" method="post">
		Introduce una letra: <input type="text" name="letra" size="1" maxLength="1"/> 
		<input type="submit" value="Prueba letra"/><br>
		<a href="AhorcadoServlet?empezar">Cerrar ventana</a><br>
		<%=letra_correcta %>
		<input type="hidden" name="palabra_aleatoria" value="<%=palabra_aleatoria %>"/>
		<input type="hidden" name="palabra_oculta" value="<%=palabra_oculta %>"/>
	</form>
</body>
</html>