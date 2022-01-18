import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Accueil extends HttpServlet {
    public static final String CLE_ACCUEIL = "accueil";
    public static final String CLE_MENU = "menu";
    private HttpServletRequest request;
    private HttpServletResponse response;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String[] chemin = request.getRequestURI().split("/");
        String cle = chemin[chemin.length - 1];
        String jsp;

        switch (cle) {
            case CLE_ACCUEIL:
                jsp = "/WEB-INF/jsp/accueil.jsp";
                break;
            case CLE_MENU:
                jsp = "/WEB-INF/jsp/menu.jsp";
                break;
            default:
                jsp = "/WEB-INF/jsp/accueil.jsp";
                break;
        }
        request.getServletContext()
                .getRequestDispatcher(jsp)
                .forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse reponse) throws ServletException, IOException{}
}
