import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/AhorcadoServlet")
public class AhorcadoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AhorcadoServlet() {
        super();
    }
    
    String arrayPalabras[] = {"telefono", "procesador", "teclado", "raton", "monitor"};// Array de palabras
    int pos_array = (int)(Math.random()*5);
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
	    HttpSession sesion = request.getSession(false);
	    
	    String palabra_oculta = "";
	    char letra = '\0';
	    String palabra_aleatoria = arrayPalabras[pos_array];
	    String intentos = "6";
	    String errores = "0";
	    String letras_probadas = "";
	    
	    //"palabra_oculta" es un String del mismo tamaño que "palabra_aleatoria" pero formado con "-"
	    for(int i = 0; i < palabra_aleatoria.length(); i++) {
	    	palabra_oculta += "-";
	    }
	    
	    //Cuando se recarga la pagina se comprueba si hay una sesion, si la hay, se recogen los atributos para mantener sus valores y que no se reinicien
	    if(sesion != null ) {
	    	if(request.getParameter("reiniciar") != null) {
				pos_array = (int)(Math.random()*5);
				palabra_aleatoria = arrayPalabras[pos_array];
				//Se vuelve a formar "palabra_oculta" ya que la longitud de la nueva "palabra_aleatoria" puede ser diferente a la primera
				palabra_oculta = "";
				for(int i = 0; i < palabra_aleatoria.length(); i++) {
			    	palabra_oculta += "-";
			    }
				intentos = "6";
			    errores = "0";
	    	}
	    	if (request.getParameter("empezar") != null) {  // se ha recibido el parámetro empezar
				sesion.invalidate();  // se inactiva la sesión
			} else {
				if(sesion.getAttribute("palabra_aleatoria") != null) {
		    		palabra_aleatoria = (String) sesion.getAttribute("palabra_aleatoria");
		    	}
		    	if(sesion.getAttribute("palabra_oculta") != null) {
		    		palabra_oculta = (String) sesion.getAttribute("palabra_oculta");
		    	}
		    	if(sesion.getAttribute("intentos") != null) {
		    		intentos = (String) sesion.getAttribute("intentos");
		    	}
		    	if(sesion.getAttribute("errores") != null) {
		    		errores = (String) sesion.getAttribute("errores");
		    	}
		    	if(sesion.getAttribute("letras_probadas") != null) {
		    		errores = (String) sesion.getAttribute("letras_probadas");
		    	}
			}
		}
	    //Despues de las comprobaciones de la sesion se pasan los atributos al JSP
	    request.setAttribute("letra", letra);
	    request.setAttribute("palabra_oculta", palabra_oculta);
		request.setAttribute("palabra_aleatoria", palabra_aleatoria);
		request.setAttribute("intentos", intentos);
		request.setAttribute("errores", errores);
		request.setAttribute("letras_probadas", letras_probadas);
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ahorcado.jsp");
		dispatcher.include(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		String palabra_aleatoria = request.getParameter("palabra_aleatoria");
		String letra_cadena = request.getParameter("letra");
		String palabra_oculta = request.getParameter("palabra_oculta");
		String cadena_intentos = request.getParameter("intentos");
		String cadena_errores = request.getParameter("errores");
		String letras_probadas = request.getParameter("letras_probadas");
		char letra = '\0';
		boolean devuelve_letra = false; //Es false cuando el valor de "letra" no coincide con ninguno de los caracteres de "palabra_aleatoria"
		
		if(letra_cadena != "") {//Si se ha introducido algun caracter
			letra = letra_cadena.charAt(0);
			request.setAttribute("letra", letra);
			for(int i = 0; i < palabra_aleatoria.length(); i++) {
		    	if(letra == palabra_aleatoria.charAt(i)) {
	    			letras_probadas += letra;
	    			char[] tempCharArray = palabra_oculta.toCharArray();//El String "palabra_oculta" se descompone en un array de char
		    		tempCharArray[i] = letra; // "i" es la posicion que se va a sustituir por el valor de "letra"
		    		palabra_oculta = String.valueOf(tempCharArray);//Se vuelve a juntar el array de char para formar un String
		    		devuelve_letra = true;
		    		if(palabra_oculta.equals(palabra_aleatoria)) {
			    		request.setAttribute("mensaje_victoria", "<font color='green'><b>Has ganado!!</b></font>");
			    		request.setAttribute("reiniciar", "<a href='AhorcadoServlet?reiniciar'>Volver a jugar</a>");
		    		} else {
			    		request.setAttribute("mensaje_letra", "<font color='green'>Letra correcta!!</font>");
		    		}
		    	}
		    }
			if(devuelve_letra == false) {
				request.setAttribute("mensaje_letra", "<font color='red'>Letra incorrecta.</font>");
				//Tratamos el numero de intentos
				int intentos = Integer.parseInt(cadena_intentos);
				intentos--;
				cadena_intentos = Integer.toString(intentos);
				//Tratamos el numero de errores
				int errores = Integer.parseInt(cadena_errores);
				errores++;
				cadena_errores = Integer.toString(errores);
				//Pasamos los atributos modificados de errores e intentos al JSP
				if(cadena_intentos.equals("0")) {
					request.setAttribute("mensaje_derrota", "<font color='red'><b>Has perdido.</b></font>");
					request.setAttribute("reiniciar", "<a href='AhorcadoServlet?reiniciar'>Volver a jugar</a>");
				}
			}
		}
		request.setAttribute("letras_probadas", letras_probadas);
		request.setAttribute("errores", cadena_errores);
		request.setAttribute("intentos", cadena_intentos);
		request.setAttribute("palabra_oculta", palabra_oculta);
		request.setAttribute("palabra_aleatoria", palabra_aleatoria);
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ahorcado.jsp");
		dispatcher.include(request, response);
	}
}