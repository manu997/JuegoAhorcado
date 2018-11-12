import java.io.IOException;
import java.io.PrintWriter;

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
    
    String arrayPalabras[] = {"telefono"/*, "procesador", "teclado", "ratón", "monitor"*/};
    //int pos_array = (int)(Math.random()*5);
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
	    HttpSession sesion = request.getSession(false);
	    
	    String palabra_oculta = "";
	    char letra = '\0';
	    String palabra_aleatoria = arrayPalabras[0];
	    
	    for(int i = 0; i < palabra_aleatoria.length(); i++) {
	    	palabra_oculta += "-";
	    }
	    if(sesion != null ) {
	    	if (request.getParameter("empezar") != null) {  // se ha recibido el parámetro empezar
				sesion.invalidate();  // se inactiva la sesión
			} else {
		    	if(sesion.getAttribute("palabra_aleatoria") != null) {
		    		palabra_aleatoria = (String) sesion.getAttribute("palabra_aleatoria");
		    	}
		    	if(sesion.getAttribute("palabra_oculta") != null) {
		    		palabra_oculta = (String) sesion.getAttribute("palabra_oculta");
		    	}
			}
		}
	    request.setAttribute("letra", letra);
	    request.setAttribute("palabra_oculta", palabra_oculta);
		request.setAttribute("palabra_aleatoria", palabra_aleatoria);
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ahorcado.jsp");
		dispatcher.include(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		String letra_cadena = request.getParameter("letra");
		String palabra_aleatoria = request.getParameter("palabra_aleatoria");
		String palabra_oculta = request.getParameter("palabra_oculta");
		char letra = '\0';
		boolean devuelve_letra = false; //Es false cuando la letra no coincide con ninguna de la palabra.
		
		if(letra_cadena != "") {
			letra = letra_cadena.charAt(0);
			for(int i = 0; i < palabra_aleatoria.length(); i++) {
		    	if(letra == palabra_aleatoria.charAt(i)) {
		    		devuelve_letra = true;
		    		request.setAttribute("mensaje_letra", "<font color='green'>Letra correcta!!</font>");
		    	}
		    }
			if(devuelve_letra == false) {
				request.setAttribute("mensaje_letra", "<font color='red'>Letra incorrecta.</font>");
			}
		}
		request.setAttribute("letra", letra);
		request.setAttribute("palabra_oculta", palabra_oculta);
		request.setAttribute("palabra_aleatoria", palabra_aleatoria);
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ahorcado.jsp");
		dispatcher.include(request, response);
	}
}
