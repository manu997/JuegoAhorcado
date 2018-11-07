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
    String arrayPalabras[] = {"teléfono", "procesador", "teclado", "ratón", "monitor"};
    int pos_array = (int)(Math.random()*5);
    String palabra_aleatoria = arrayPalabras[pos_array];

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
	    HttpSession sesion = request.getSession(false);
	    
	    String arrayPalabras[] = {"teléfono", "procesador", "teclado", "ratón", "monitor"};
	    int pos_array = (int)(Math.random()*5);
	    String palabra_aleatoria = arrayPalabras[pos_array];
	    
	    String palabra_oculta = "";
	    
	    int letra_oculta;
	    
	    if(sesion != null ) {
	    	if(sesion.getAttribute("palabra_aleatoria") != null) {
	    		palabra_aleatoria = (String) sesion.getAttribute("palabra_aleatoria");
	    	}
	    	if(sesion.getAttribute("palabra_oculta") != null) {
	    		palabra_oculta = (String) sesion.getAttribute("palabra_oculta");
	    	} else {
	    		for(int i = 0; i < palabra_aleatoria.length(); i++) {
					letra_oculta = (int)(Math.random()*2);
					if(letra_oculta == 1) {
						palabra_oculta += "-";
					} else {
						palabra_oculta += palabra_aleatoria.charAt(i);
					}
				}
	    	}
		}
	    
		request.setAttribute("palabra_aleatoria", palabra_aleatoria);
		request.setAttribute("palabra_oculta", palabra_oculta);
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ahorcado.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}
