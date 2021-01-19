/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DAO.ItemDAO;
import DAO.UpdateRecordDAO;
import DTO.UpdateRecordDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 *
 * @author Nguyen Khoi
 */
public class UpdateServlet extends HttpServlet {

    private final String ADMIN_PAGE = "ListServlet";

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
        String url = ADMIN_PAGE;
        try {
            Date date = new Date();
            SimpleDateFormat formater = new SimpleDateFormat("yyyy/MM/dd");
            String createDate = formater.format(date);
            String updateName = request.getParameter("txtusername");
            String id = request.getParameter("txtId");
            String name = request.getParameter("txtname");
            String description = request.getParameter("txtdescription");
            String price = request.getParameter("txtprice");
            int status = Integer.parseInt(request.getParameter("txtstatus"));
            String image = request.getParameter("txtimage");
            String image1 = request.getParameter("txtimage1");
            if (image.isEmpty()) {
                image = image1;
            }
            String category = request.getParameter("txtcategory");
            String quantity = request.getParameter("txtquantity");
            if (name.isEmpty() || description.isEmpty() || category.isEmpty()) {
                request.setAttribute("err", "Not empty!!!");
                url = ADMIN_PAGE;
            } else if (!price.matches("\\d++.\\d") || price.isEmpty() || !quantity.matches("\\d++") || quantity.isEmpty()) {
                request.setAttribute("err", "Price and quantity must be number!!!");
                url = ADMIN_PAGE;
            } else {
                float price1 = Float.parseFloat(price);
                int quantity1 = Integer.parseInt(quantity);
                ItemDAO dao = new ItemDAO();
                int result = dao.updateItem(id, name, image, description, price1, category, quantity1,status);
                UpdateRecordDAO.createUpdate(new UpdateRecordDTO(updateName, createDate, "Update"));
                if (result > 0) {
                    url = ADMIN_PAGE;
                }
            }
        } catch (SQLException | NamingException ex) {
            Logger.getLogger(UpdateServlet.class).error("Error at UpdateServlet " + ex.toString());
        } finally {

            request.getRequestDispatcher(url).forward(request, response);
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
