/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entity.Groupuser;
import entity.Person;
import entity.Users;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.GroupuserFacade;
import session.PersonFacade;
import session.UsersFacade;

/**
 *
 * @author melnikov
 */
@WebServlet(name = "SecurityController",loadOnStartup = 1, urlPatterns = {
    "/showLogin",
    "/login",
    "/logout",
    "/showRegistration",
    "/registration",
    "/showAdmin",
    "/editRole",
    
})
public class SecurityController extends HttpServlet {

    @EJB UsersFacade usersFacade;
    @EJB PersonFacade personFacade;
    @EJB GroupuserFacade groupuserFacade;

    @Override
    public void init() throws ServletException {
        List<Groupuser> listGroupusers = groupuserFacade.findAll();
        if(listGroupusers.size() == 0){
            Person p = new Person(
                    "Juri", 
                    "Melnikov", 
                    "juri.melnikov@ivkhk.ee"
            );
            personFacade.create(p);
            Users u = new Users("admin", "admin", p);
            usersFacade.create(u);
            Groupuser g = new Groupuser("ADMINISTRATOR");
            g.setUsersLogin(u);
            groupuserFacade.create(g);
        }
    }

    
    
    
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
        String path = request.getServletPath();
        if(path != null)
            switch (path){
                case "/showLogin":
                    request.getRequestDispatcher("/login.jsp")
                            .forward(request, response);
                    break;
                case "/login":
                    String username = request.getParameter("username");
                    String password = request.getParameter("password");
                    Users regUser = usersFacade.findUserByLogin(username);
                    if(regUser == null){
                        request.setAttribute("info", "Неправильный логин или пароль");
                        request.getRequestDispatcher("/login.jsp")
                                .forward(request, response);
                    }
                    if(!password.equals(regUser.getPassword())){
                        request.setAttribute("info", "Неправильный логин или пароль");
                        request.getRequestDispatcher("/login.jsp")
                                .forward(request, response);
                    }
                    HttpSession session = request.getSession(true);
                    session.setAttribute("regUser", regUser);
                    request.setAttribute("info", "Вы вошли");
                    request.getRequestDispatcher("/index.jsp")
                                .forward(request, response);
                    break;
                case "/logout":
                    session = request.getSession(false);
                    if(session != null){
                        session.invalidate();
                        request.setAttribute("info", "Вы вышли");
                    }
                    request.getRequestDispatcher("/index.jsp")
                            .forward(request, response);
                    break;
                case "/showRegistration":
                    request.getRequestDispatcher("/showRegistration.jsp")
                            .forward(request, response);
                    break;
                case "/registration":
                    String name = request.getParameter("name");
                    String surname = request.getParameter("surname");
                    String email = request.getParameter("email");
                    String login = request.getParameter("login");
                    String password1 = request.getParameter("password1");
                    String password2 = request.getParameter("password2");
                    if(!password1.equals(password2)){
                        request.setAttribute("info", "Неодинаковые пороли");
                        request.getRequestDispatcher("/showRegistration.jsp")
                            .forward(request, response);
                        break;
                    }
                    Person person = new Person(name, surname, email);
                    personFacade.create(person);
                    Users user = new Users(login, password1, person);
                    usersFacade.create(user);
                    Groupuser gu = new Groupuser();
                    gu.setName("USER");
                    gu.setUsersLogin(user);
                    groupuserFacade.create(gu);
                    request.setAttribute("info", "Вы зарегистрированы!");
                    request.getRequestDispatcher("/index.jsp")
                            .forward(request, response);
                    break;
                case "/showAdmin":
                    List<Groupuser> listGroupuser = groupuserFacade.findAll();
                    request.setAttribute("listGroupuser", listGroupuser);
                    request.getRequestDispatcher("/WEB-INF/private/admin.jsp")
                            .forward(request, response);
                    break;
                case "/editRole":
                    login = request.getParameter("login");
                    String makeAdmin = request.getParameter("makeAdmin");
                    String rmAdmin = request.getParameter("rmAdmin");
                    if(makeAdmin != null){
                        user = usersFacade.find(login);
                        gu = groupuserFacade.findByUser(user);
                        groupuserFacade.remove(gu);
                        gu.setName("ADMINSTRATOR");
                        gu.setName(user.getLogin());
                        groupuserFacade.edit(gu);
                    }
                    if(rmAdmin != null){
                        user = usersFacade.find(login);
                        gu = groupuserFacade.findByUser(user);
                        groupuserFacade.remove(gu);
                        gu.setName("USER");
                        gu.setName(user.getLogin());
                        groupuserFacade.edit(gu);
                    }
                    listGroupuser = groupuserFacade.findAll();
                    request.setAttribute("listGroupuser", listGroupuser);
                    request.getRequestDispatcher("/WEB-INF/private/admin.jsp")
                            .forward(request, response);
                    break;
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
