/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Minh Hoàng
 */
public class MainController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String LOGIN_ACTION = "Login";
    private static final String LOGIN = "LoginController";
    private static final String LOGOUT_ACTION = "Logout";
    private static final String LOGOUT = "LogoutController";
    private static final String CREATE_ACCOUNT_ACTION = "createAccount";
    private static final String CREATEACCOUNT = "CreateAccountController";

    private static final String SEARCH_ACTION = "Search";
    private static final String SEARCH = "SearchController";
    private static final String UPDATE_ACTION = "Update";
    private static final String UPDATE = "UpdateController";
    private static final String DELETE_ACTION = "Delete";
    private static final String DELETE = "DeleteController";
    private static final String SENDMAIL_ACTION = "SendMail";
    private static final String SENDMAIL = "SendMailController";
    private static final String ADD_SERVICE_ACTION = "AddService";
    private static final String ADD_SERVICE = "AddServiceController";
    private static final String SEARCH_SERVICE_ACTION = "SearchService";
    private static final String SEARCH_SERVICE = "SearchServiceController";
    private static final String UPDATE_SERVICE_ACTION = "UpdateService";
    private static final String UPDATE_SERVICE = "UpdateServiceController";
    private static final String REMOVE_SERVICE_ACTION = "RemoveService";
    private static final String REMOVE_SERVICE = "RemoveServiceController";
    private static final String ADD_DETAIL_SERVICE_ACTION = "AddDetailService";
    private static final String ADD_DETAIL_SERVICE = "AddDetailServiceController";

    private static final String VIEW_TROUBLE = "ViewTrouble";
    private static final String VIEW_TROUBLE_CONTROLLER = "ViewTroubleController";
    private static final String UPDATE_TROUBLE = "UpdateTrouble";
    private static final String UPDATE_TROUBLE_CONTROLLER = "UpdateTroubleController";
    private static final String SEND_TROUBLE = "SendTrouble";
    private static final String SEND_TROUBLE_CONTROLLER = "SendTroubleController";
    private static final String CREATE_TROUBLE = "CreateTrouble";
    private static final String CREATE_TROUBLE_CONTROLLER = "CreateTroubleController";
    private static final String VIEW_RESIDENT = "ViewResident";
    private static final String VIEW_RESIDENT_CONTROLLER = "ViewResidentController";
    private static final String ADD_RESIDENT = "AddResident";
    private static final String ADD_RESIDENT_CONTROLLER = "AddResidentController";
    private static final String BEFORE_CREATE_TROUBLE = "BeforeCreateTrouble";
    private static final String BEFORE_CREATE_TROUBLE_CONTROLLER = "BeforeCreateTroubleController";
    private static final String BEFORE_DELETE_RESIDENT = "BeforeDeleteResident";
    private static final String BEFORE_DELETE_RESIDENT_CONTROLLER = "BeforeDeleteResidentController";
    private static final String DELETE_RESIDENT = "DeleteResident";
    private static final String DELETE_RESIDENT_CONTROLLER = "DeleteResidentController";
    private static final String BEFORE_RESIDENT_REQUEST = "befResidentRequest";
    private static final String BEFORE_RESIDENT_REQUEST_CONTROLLER = "befResidentRequestController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String action = request.getParameter("action");
            if (LOGIN_ACTION.equals(action)) {
                url = LOGIN;

            } else if (LOGOUT_ACTION.equals(action)) {
                url = LOGOUT;
            } else if (CREATE_ACCOUNT_ACTION.equals(action)) {
                url = CREATEACCOUNT;
            } else if (SEARCH_ACTION.equals(action)) {
                url = SEARCH;
            } else if (UPDATE_ACTION.equals(action)) {
                url = UPDATE;
            } else if (DELETE_ACTION.equals(action)) {
                url = DELETE;
            } else if (SENDMAIL_ACTION.equals(action)) {
                url = SENDMAIL;
            } else if (ADD_SERVICE_ACTION.equals(action)) {
                url = ADD_SERVICE;
            } else if (SEARCH_SERVICE_ACTION.equals(action)) {
                url = SEARCH_SERVICE;
            } else if (UPDATE_SERVICE_ACTION.equals(action)) {
                url = UPDATE_SERVICE;
            } else if (REMOVE_SERVICE_ACTION.equals(action)) {
                url = REMOVE_SERVICE;
            } else if (ADD_DETAIL_SERVICE_ACTION.equals(action)) {
                url = ADD_DETAIL_SERVICE;
            } else if (CREATE_ACCOUNT_ACTION.equals(action)) {
                url = CREATEACCOUNT;
            } else if (VIEW_TROUBLE.equals(action)) {
                url = VIEW_TROUBLE_CONTROLLER;
            } else if (UPDATE_TROUBLE.equals(action)) {
                url = UPDATE_TROUBLE_CONTROLLER;
            } else if (CREATE_TROUBLE.equals(action)) {
                url = CREATE_TROUBLE_CONTROLLER;
            } else if (SEND_TROUBLE.equals(action)) {
                url = SEND_TROUBLE_CONTROLLER;
            } else if (VIEW_RESIDENT.equals(action)) {
                url = VIEW_RESIDENT_CONTROLLER;
            } else if (ADD_RESIDENT.equals(action)) {
                url = ADD_RESIDENT_CONTROLLER;
            } else if (BEFORE_CREATE_TROUBLE.equals(action)) {
                url = BEFORE_CREATE_TROUBLE_CONTROLLER;
            } else if (BEFORE_DELETE_RESIDENT.equals(action)) {
                url = BEFORE_DELETE_RESIDENT_CONTROLLER;
            } else if (DELETE_RESIDENT.equals(action)) {
                url = DELETE_RESIDENT_CONTROLLER;
            } else if (BEFORE_RESIDENT_REQUEST.equals(action)) {
                url = BEFORE_RESIDENT_REQUEST_CONTROLLER;
            } else {

                HttpSession session = request.getSession();
                session.setAttribute("ERROR_MESSAGE", "Function is not avaible!");
            }
        } catch (Exception e) {
            log("Error at MainController:" + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
