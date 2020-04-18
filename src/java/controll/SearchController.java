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
public class SearchController extends HttpServlet {

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
            if (imagePath == null) {
                request.setAttribute("ErrPath", "Don't have Data picture");
            }
            // get top1 
            Digital top1 = digitalDAO.getTop1();
            // check null
            if (top1 == null) {
                request.setAttribute("RightErrNew", "Don't have data");
            } else {
                //check path
                if (imagePath != null) {
                    top1.setImage(imagePath + top1.getImage());
                }
                request.setAttribute("one", top1);
            }

            // get top5
            List<Digital> list = digitalDAO.getTop5();
            // check null
            if (list == null) {
                request.setAttribute("RightErr", "Don't have data");
            } else {
                request.setAttribute("top5", list);
            }
            // check search input
            String txtSearch = request.getParameter("txtSearch");

            if (txtSearch == null || txtSearch.trim().length() == 0) {
                request.setAttribute("RightSearchErr", "Please enter somethings");
            } else {
                // get pageIndex
                String pageIndex = request.getParameter("index");
                // check null
                if (pageIndex == null) {
                    pageIndex = "1";
                }
                //check input string
                if (pageIndex.matches("[0-9]+") == false) {
                    request.setAttribute("SearchErr", "Don't have index string");
                    request.setAttribute("LastTxtSearch", txtSearch);

                } else {
                    int index = Integer.parseInt(pageIndex);
                    int total = digitalDAO.count(txtSearch);
                    //check number of record
                    if (total == 0) {
                        request.setAttribute("SearchErr", "Don't have Data");
                        request.setAttribute("LastTxtSearch", txtSearch);
                    } else {
                        int pageSize = 2;
                        int maxPage = total / pageSize;
                        if (total % pageSize != 0) {
                            maxPage++;
                        }
                        //get number colum of page
                        int rowFrom = (index - 1) * pageSize + 1;
                        int rowTo = index * pageSize;
                        //end get

                        // get list search 
                        List<Digital> listSearch = digitalDAO.search(txtSearch, rowFrom, rowTo);
                        //check null
                        if (listSearch == null || listSearch.size() == 0) {
                            request.setAttribute("SearchErr", "Don't have Data");
                            request.setAttribute("LastTxtSearch", txtSearch);
                        } else {
                            if (imagePath != null) {
                                // set image
                                for (int i = 0; i < listSearch.size(); i++) {
                                    listSearch.get(i).setImage(imagePath + listSearch.get(i).getImage());
                                }
                            }
                            // set all
                            request.setAttribute("list", listSearch);
                            request.setAttribute("txt", txtSearch);
                            request.setAttribute("maxPage", maxPage);
                            request.setAttribute("index", index);
                        }
                    }
                }

            }
            request.getRequestDispatcher("SearchResultPage.jsp").forward(request, response);
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
