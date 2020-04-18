/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controll;

import entity.Digital;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DBContext;
import model.DigitalDAO;

/**
 *
 * @author Darkness_King
 */
public class HomeController extends HttpServlet {

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
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");

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
        try {

            DigitalDAO digitalDAO = new DigitalDAO();
            DBContext bContext = new DBContext();
            String imagePath = bContext.getImagePath();
//            String imagePath = null;
            // check path
            if (imagePath == null) {
                request.setAttribute("ErrPath", "Don't have data Picture");
            }
            // set Top5 in right page
            List<Digital> list = digitalDAO.getTop5();
            if (list == null) {
                request.setAttribute("RightErr", "Don't have data");
            }
            request.setAttribute("top5", list);
            //check get top1
            Digital top1 = digitalDAO.getTop1();
            if (top1 == null) {
                request.setAttribute("HomeErr", "Don't have data");
            } else {
                if (imagePath != null) {
                    top1.setImage(imagePath + top1.getImage());
                }
                request.setAttribute("top1", top1);
                request.setAttribute("one", top1);
            }
            request.getRequestDispatcher("HomePage.jsp").forward(request, response);
        } catch (Exception ex) {
            request.getRequestDispatcher("ErroPage.jsp").forward(request, response);
        }
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
