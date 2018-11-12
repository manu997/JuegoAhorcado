<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" session="true"%>
    <%
    String palabra_aleatoria = (String) request.getAttribute("palabra_aleatoria");
    String palabra_oculta = (String) request.getAttribute("palabra_oculta");
    String no_hay_letra = "";
    char letra = '\0';
    try {
    	letra = (char) request.getAttribute("letra");
        if(letra == '\0') {
        	letra = '\0';
        }
    } catch(NullPointerException e) {
    	no_hay_letra = "No has introducido ninguna letra.";
    }
    String mensaje_letra = (String) request.getAttribute("mensaje_letra");
    if(mensaje_letra == null) {
    	mensaje_letra = "";
    }
    int j;
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
			for(int i = 0; i < palabra_oculta.length(); i++) {
				%><td>
				<%
				
				%>
				</td><%
			}
			%>
		</tr>
	</table>
	<form action="AhorcadoServlet" method="post">
		Introduce una letra: <input type="text" name="letra" size="1" maxLength="1"/> 
		<input type="submit" value="Prueba letra"/><br>
		<a href="AhorcadoServlet?empezar">Cerrar ventana</a><br>
		<%=mensaje_letra %>
		<%=no_hay_letra %>
		<input type="hidden" name="palabra_aleatoria" value="<%=palabra_aleatoria %>"/>
		<input type="hidden" name="palabra_oculta" value="<%=palabra_oculta %>"/>
	</form>
</body>
</html>