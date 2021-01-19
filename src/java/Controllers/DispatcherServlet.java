/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Nguyen Khoi
 */
public class DispatcherServlet extends HttpServlet {

    private final String LOGIN_CONTROLLER = "LoginServlet";
    private final String SEARCH_CONTROLLER = "SearchServlet";
    private final String HOME_PAGE = "home.jsp";
    private final String ADDTOCART_CONTROLLER = "AddtocartServlet";
    private final String CREATEITEM_PAGE = "ListCategoryServlet";
    private final String UPDATECART = "UpdateCartServlet";
    private final String UPDATE_CONTROLLER = "UpdateServlet";
    private final String CREATEITEM_CONTROLLER = "CreateServlet";
    private final String DELETE_CONTROLLER = "DeleteServlet";
    private final String ADDMOREITEM = "home.jsp";
    private final String CHECKOUT_CONTROLLER = "CheckoutServlet";
    private final String DELETEITEM_CONTROLLER = "DeleteItemServlet";
    private final String CREATECATEGORY_PAGE = "createcategory.jsp";
    private final String CREATECATEGORY_CONTROLLER = "CreatecategoryServlet";
    private final String PAYMENT_PAGE = "payment.jsp";
    private final String SEARCH_ADMIN_CONTROLLER = "SearchAdminServlet";
    private final String LIST_PAGE = "ListServlet";
    private final String HISTORY_CONTROLLER = "HistoryServlet";
    private final String HISTORY_DETAIL_PAGE = "HistoryDetailServlet";
    private final String HOME_CONTROLLER = "HomeServlet";

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
        String url = HOME_PAGE;
        String button = request.getParameter("btAction");
        try {
            if (button.equals("Login")) {
                url = LOGIN_CONTROLLER;
            } else if (button.equals("Search")) {
                url = SEARCH_CONTROLLER;
            } else if (button.equals("Add Item")) {
                url = ADDTOCART_CONTROLLER;
            } else if (button.equals("Create Item")) {
                url = CREATEITEM_PAGE;
            } else if (button.equals("Update Item")) {
                url = UPDATE_CONTROLLER;
            } else if (button.equals("Delete Item")) {
                url = DELETE_CONTROLLER;
            } else if (button.equals("Save")) {
                url = CREATEITEM_CONTROLLER;
            } else if (button.equals("Remove Selected")) {
                url = DELETEITEM_CONTROLLER;
            } else if (button.equals("Update Quantity")) {
                url = UPDATECART;
            } else if (button.equals("Add more item")) {
                url = ADDMOREITEM;
            } else if (button.equals("Create Category")) {
                url = CREATECATEGORY_PAGE;
            } else if (button.equals("Create")) {
                url = CREATECATEGORY_CONTROLLER;
            } else if (button.equals("Pay Now")) {
                url = CHECKOUT_CONTROLLER;
            } else if (button.equals("Checkout")) {
                url = PAYMENT_PAGE;
            } else if (button.equals("Find")) {
                url = SEARCH_ADMIN_CONTROLLER;
            } else if (button.equals("Get All")) {
                url = LIST_PAGE;
            } else if (button.equals("ShowOrder")){
                url = HISTORY_DETAIL_PAGE;
            } else if (button.equals("History")){
                url = HISTORY_CONTROLLER;
            }else if (button.equals("All Item")){
                url = HOME_CONTROLLER;
            }

        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
