
package servlets;

import Outils.CustomException;
import beanPresentation.PanierPresentation;
import entites.Produit;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sessionBeans.BeanMagasinLocal;
import sessionBeans.GestionPanierLocal;

/**
 *
 * @author pham
 */
@WebServlet(name = "Controleur", urlPatterns = {"/Controleur"})
public class Controleur extends HttpServlet {
        
    @EJB
    private BeanMagasinLocal beanMagasin; // action c'est la version pour debutant

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
       request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        String prefixe = "/WEB-INF/jsp/";

        String url = "home.jsp";  // à faire
        String section = request.getParameter("section");
        String action = request.getParameter("action");
        
        if(session.getAttribute("panierPresentation")== null){   
            session.setAttribute("panierPresentation", new PanierPresentation());
        }
        PanierPresentation p = (PanierPresentation) session.getAttribute("panierPresentation");
        GestionPanierLocal gp= p.getGestionPanier();
        
        if("afficherCatalogue".equalsIgnoreCase(section)){
            url="home.jsp";
        }
        
        if("afficherPanier".equalsIgnoreCase(section)){
            session.setAttribute("panier", gp.getListe());
            session.setAttribute("total", gp.getTotalHT());
            url="panier.jsp";
        }
        
        if("panier".equalsIgnoreCase(section)){
            if("add".equalsIgnoreCase(action)){
                String ref = request.getParameter("ref");
                gp.add(ref);
                //session.setAttribute("panier", gestionPanier.getListe());
                url="home.jsp";
            }
        }
        
        if("catalogue".equalsIgnoreCase(section)){
            if("voir".equalsIgnoreCase(action)){
                List<Produit> lp = beanMagasin.selectAllproduit();
                request.setAttribute("produits", lp);
                url = "catalogue.jsp";
            }
        }

        if ("produit".equalsIgnoreCase(section)) {
            
            if("creerDonnees".equalsIgnoreCase(action)){
                beanMagasin.creerJeuxDonnees();
                request.setAttribute("msg", "le jeu d'essai a été crée !");
                url = "home.jsp";
            }
            
            
            if ("versFormulaire".equalsIgnoreCase(action)) {
                url = "formulaire-produit.jsp";
            }

            if ("creer".equalsIgnoreCase(action)) {
                String ref = request.getParameter("ref");
                String descp = request.getParameter("label");

                // attention aux exceptions
                String sprix = request.getParameter("prix");
                String sqte = request.getParameter("qte");
                
                try {
                    Produit p01 = beanMagasin.creerProduit(ref, descp, sprix, sqte);
                    request.setAttribute("msg", p01+" créé!");

                    url = "home.jsp";

                } catch (CustomException ex) {
                    HashMap<String, String> mp = ex.getErreurs();
                    request.setAttribute("msgERR", ex.getMessage());
                    for (String clef : mp.keySet()) {
                        request.setAttribute(clef, mp.get(clef));
                    }
                    url = "formulaire-produit.jsp";
                } 

            }
        }

        url = response.encodeURL(prefixe + url);
        getServletContext().getRequestDispatcher(url).include(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    

}
