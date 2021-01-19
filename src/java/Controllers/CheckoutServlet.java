/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import DAO.ItemDAO;
import DAO.OrderDAO;
import DAO.OrderDetailDAO;
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
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

/**
 *
 * @author Nguyen Khoi
 */
public class CheckoutServlet extends HttpServlet {

    private final String HOME_PAGE = "home.jsp";
    private final String PAYMENT_PAGE = "payment.jsp";
    private final String VIEWCART_PAGE = "viewcart.jsp";

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
        String url = PAYMENT_PAGE;
        try {
            Date date = new Date();
            SimpleDateFormat formater = new SimpleDateFormat("yyyy/MM/dd");
            String createDate = formater.format(date);
            HttpSession session = request.getSession(false);
            String cusname = request.getParameter("txtCusName");
            float totalprice = Float.parseFloat(request.getParameter("txtTotalPrice"));
            OrderDAO dao = new OrderDAO();
            int result = 0;
            int result1 = 0;
            String[] listId = request.getParameterValues("txtid");
            String[] listFoodname = request.getParameterValues("txtFoodname");
            String[] listQuantity = request.getParameterValues("txtQuantity");
            String[] listPrice = request.getParameterValues("txtPrice");
            result = dao.createItemOrder(cusname, createDate, totalprice);
            for (int i = 0; i < listFoodname.length; i++) {
                int orderid = OrderDAO.getOrderid(cusname);
                String id = listId[i];
                String foodname = listFoodname[i];
                int quantity = Integer.parseInt(listQuantity[i]);
                float price = Float.parseFloat(listPrice[i]);
                int quantityKho = ItemDAO.getQuantity(foodname);
                if (quantityKho >= quantity) {
                    ItemDAO.updateQuantity(foodname, ItemDAO.getQuantity(foodname) - quantity);   
                    result1 = OrderDetailDAO.createItemOrderDetail(orderid, id, foodname, price, quantity);
                    if (result > 0 && result1 > 0) {
                        request.setAttribute("THANK", "Thank You and see you again!!!");
                        url = HOME_PAGE;
                        session.removeAttribute("CUSTCART");
                    }
                } else {
                    request.setAttribute("OVER", "The product is out of stock. You must be buy less than "+(quantityKho+1));
                    url = VIEWCART_PAGE;
                }
            }
        } catch (SQLException | NamingException ex) {
            Logger.getLogger(CheckoutServlet.class).error("Error at CheckoutServlet " + ex.toString());
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
