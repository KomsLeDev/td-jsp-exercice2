import facade.FacadeParis;
import facade.FacadeParisStaticImpl;
import facade.exceptions.InformationsSaisiesIncoherentesException;
import facade.exceptions.UtilisateurDejaConnecteException;
import modele.Match;
import modele.Utilisateur;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.stream.FactoryConfigurationError;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.IllegalCharsetNameException;
import java.util.Collection;

public class Accueil extends HttpServlet {
    public static final String CLE_ACCUEIL = "accueil";
    public static final String CLE_MENU = "menu";
    public static final String CLE_MESPARIS = "mesparis";
    public static final String CLE_PARISOUVERTS = "parisouverts";
    public static final String CLE_PARISVALIDER= "parisvalider";
    public static final String CLE_PARISANNULER = "parisannuler";
    public static final String CLE_DECONNEXION = "deconnexion";
    private HttpServletRequest request;
    private HttpServletResponse response;
    private FacadeParisStaticImpl facade;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String[] chemin = request.getRequestURI().split("/");
        String cle = chemin[chemin.length - 1];
        String jsp;
        this.facade = (FacadeParisStaticImpl) request.getServletContext().getAttribute("facade");

        switch (cle) {
            case CLE_ACCUEIL:
                jsp = "/WEB-INF/jsp/accueil.jsp";
                break;
            case CLE_MENU:
                jsp = "/WEB-INF/jsp/menu.jsp";
                break;
            case CLE_MESPARIS:
                jsp = "/WEB-INF/jsp/mesparis.jsp";
                break;
            case CLE_PARISOUVERTS:
                jsp = "/WEB-INF/jsp/parisouverts.jsp";
                break;
            case CLE_PARISVALIDER:
                Collection<Match> matchsPasCommences = facade.getMatchsPasCommences();
                request.setAttribute("parisouverts", matchsPasCommences);
                jsp = "/WEB-INF/jsp/parisvalider.jsp";
                break;
            case CLE_PARISANNULER:
                jsp = "/WEB-INF/jsp/parisannuler.jsp";
                break;
            case CLE_DECONNEXION:
                HttpSession session = request.getSession();
                Utilisateur utilisateur = (Utilisateur) session.getAttribute("login");
                facade.deconnexion(utilisateur.getLogin());
                session.setAttribute("utilisateur",null);
                jsp = "WEB-INF/jsp/accueil.jsp";
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
    protected void doPost(HttpServletRequest request, HttpServletResponse reponse) throws ServletException, IOException{
        this.facade = (FacadeParisStaticImpl) request.getServletContext().getAttribute("facade");
        String login = request.getParameter("login");
        String mdp = request.getParameter("mdp");
        String jsp = "/WEB-INF/jsp/accueil.jsp";

        try{
            Utilisateur utilisateur = facade.connexion(login, mdp);
            jsp = "/WEB-INF/jsp/menu.jsp";
        }catch (UtilisateurDejaConnecteException e){
            e.printStackTrace();
        }catch (IllegalCharsetNameException e){
            e.printStackTrace();
        } catch (InformationsSaisiesIncoherentesException e) {
            e.printStackTrace();
        }

        request.getServletContext()
                .getRequestDispatcher(jsp)
                .forward(request,response);

        System.out.println(login);
        System.out.println(mdp);
    }
}
