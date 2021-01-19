/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DAO.CategoryDAO;
import DAO.ItemDAO;
import DTO.CategoryDTO;
import DTO.ItemDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
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
public class ListServlet extends HttpServlet {
    private final String ADMIN_PAGE = "admin.jsp";
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
        try  {
            int pageIndex = 1;
            final int PAGE_SIZE = 5;
            String raw_page = request.getParameter("pageIndex");
            if(raw_page!=null){
                pageIndex = Integer.parseInt(raw_page);
            }
            
            ItemDAO dao = new ItemDAO();
            CategoryDAO dao1 = new CategoryDAO();
            List<ItemDTO> list = dao.getAllPaggingAdmin(pageIndex, PAGE_SIZE);
            int totalPage = dao.countPage(PAGE_SIZE);
            List<CategoryDTO> listItem = dao1.getAllCategory();
            List<ItemDTO> liststatus = dao.getAllItem();
            request.setAttribute("totalPage", totalPage);
            request.setAttribute("pageIndex", pageIndex);
            request.setAttribute("CATEGORY", listItem);
            request.setAttribute("LISTSTATUS", liststatus);
            request.setAttribute("LISTITEM", list);
            url = ADMIN_PAGE;
        } catch (SQLException | NamingException ex) {
            Logger.getLogger(ListServlet.class).error("Error at ListServlet " + ex.toString());
        }finally{
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
