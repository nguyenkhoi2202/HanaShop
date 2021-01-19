/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DAO.CartObject;
import DAO.ItemDAO;
import DTO.CartDTO;
import DTO.ItemDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

/**
 *
 * @author Nguyen Khoi
 */
public class AddtocartServlet extends HttpServlet {

    private final String SHOW_SHOPPING = "home.jsp";

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
        PrintWriter out = response.getWriter();
        String urlRewriting = SHOW_SHOPPING;
        try {
            HttpSession session = request.getSession();
            CartObject cart = (CartObject) session.getAttribute("CUSTCART");
            if (cart == null) {
                cart = new CartObject();
            }
            String cusName = request.getParameter("txtcusName");
            String id = request.getParameter("txtid");
            String name = request.getParameter("txtname");
            float price = Float.parseFloat(request.getParameter("txtprice"));
            String searchValue = request.getParameter("lastSearchValue");
            String description = request.getParameter("txtdescription");
            CartDTO item = new CartDTO(cusName, id, name, description, 1, price);
            int quantityDB = ItemDAO.getQuantity(name);
            if (quantityDB == 0) {

            }
            if (quantityDB > 0) {
                cart.add(item);
                cart.getItem().get(name).getQuantity();
                session.setAttribute("CUSTCART", cart);
                urlRewriting = "SearchServlet?txtkey=" + searchValue
                        + "&btAction=Search";
            } else {
                ItemDTO dto = new ItemDTO();
                dto.setStatus(0);
                request.setAttribute("PRODUCT", "Product empty");
                urlRewriting = "SearchServlet?txtkey=" + searchValue
                        + "&btAction=Search";
            }
        } catch (SQLException | NamingException ex) {
            Logger.getLogger(AddtocartServlet.class).error("Error at AddtocartServlet " + ex.toString());
        } finally {
            response.sendRedirect(urlRewriting);
            out.close();
        }
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
