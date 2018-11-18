<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" session="true"%>
    <%
    String palabra_aleatoria = (String) request.getAttribute("palabra_aleatoria");
    String palabra_oculta = (String) request.getAttribute("palabra_oculta");
    String mensaje_victoria = (String) request.getAttribute("mensaje_victoria");
    String mensaje_derrota = (String) request.getAttribute("mensaje_derrota");
    String letras_probadas = (String) request.getAttribute("letras_probadas");
    String letra_repetida = (String) request.getAttribute("letra_repetida");
    String intentos = (String) request.getAttribute("intentos");
    String errores = (String) request.getAttribute("errores");
    String reinicar = (String) request.getAttribute("reiniciar");
    if(reinicar == null) {
    	reinicar = "";
    }
    if(mensaje_victoria == null) {
    	mensaje_victoria = "";
    }
    if(mensaje_derrota == null) {
    	mensaje_derrota = "";
    }
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
    if(letra_repetida == null) {
    	letra_repetida = "";
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
	div {
		float: left;
	}
	#imagen {
		padding-left: 2%;
	}
</style>
</head>
<body>
	<div>
		<table>
			<tr>
				<%
				for(int i = 0; i < palabra_oculta.length(); i++) {
					%><td>
					<%=palabra_oculta.charAt(i)%>
					</td><%
				}
				%>
			</tr>
		</table>
		<form action="AhorcadoServlet" method="post">
			<%
			if(!palabra_oculta.equals(palabra_aleatoria)) {
				if(errores.equals("6")) {
				%>
					<%=mensaje_derrota%>
					<br>
					<%=reinicar %>
				<%
				} else {
					%>
					Introduce una letra: <input type="text" name="letra" size="1" maxLength="1"/> 
					<input type="submit" value="Prueba letra"/><br>
					<%=mensaje_letra %>
					<%=letra_repetida %>
					<font color='blue'><%=no_hay_letra %></font><br>
					Ultima letra usada: <%=letra %><br>
					Intentos restantes: <%=intentos %><br>
					Errores: <%=errores %><br>
					Letras probadas: <%=letras_probadas %><br>
					<a href="AhorcadoServlet?empezar">Cerrar ventana</a><br>
				<%
				}
			} else {
				%>
				<%=mensaje_victoria%>
				<br>
				<%=reinicar %>
			<%
			}
			%>
			<input type="hidden" name="palabra_oculta" value="<%=palabra_oculta %>"/>
			<input type="hidden" name="palabra_aleatoria" value="<%=palabra_aleatoria %>"/>
			<input type="hidden" name="intentos" value="<%=intentos %>"/>
			<input type="hidden" name="errores" value="<%=errores %>"/>
			<input type="hidden" name="letras_probadas" value="<%=letras_probadas %>"/>
		</form>
	</div>
	<div id="imagen">
		<%
		if(palabra_oculta.equals(palabra_aleatoria)) {
			%>
			<img height="260px" src="img/ahorcado_victoria.png">
			<%
		}else {
			switch(errores) {
			case "0":
				%>
				<img height="260px" src="img/ahorcado_vacio.png">
				<%
			break;
			case "1":
				%>
				<img height="260px" src="img/ahorcado_cabeza.png">
				<%
			break;
			case "2":
				%>
				<img height="260px" src="img/ahorcado_cuerpo.png">
				<%
			break;
			case "3":
				%>
				<img height="260px" src="img/ahorcado_brazo_izquierdo.png">
				<%
			break;
			case "4":
				%>
				<img height="260px" src="img/ahorcado_brazo_derecho.png">
				<%
			break;
			case "5":
				%>
				<img height="260px" src="img/ahorcado_pierna_izquierda.png">
				<%
			break;
			case "6":
				%>
				<img height="260px" src="img/ahorcado_derrota.png">
				<%
			break;
			}
		}
		%>
	</div>
</body>
</html>