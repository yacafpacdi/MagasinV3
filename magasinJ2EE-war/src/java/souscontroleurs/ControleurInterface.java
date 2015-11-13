package souscontroleurs;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ControleurInterface {
    public String execute(HttpServletRequest request, 
            HttpServletResponse response,
            HttpServlet servlet); 
}