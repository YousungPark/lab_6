package ca.sait.lab6crud.servlets;

import ca.sait.lab6crud.models.Role;
import ca.sait.lab6crud.models.User;
import ca.sait.lab6crud.services.RoleService;
import ca.sait.lab6crud.services.UserService;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Level;
import java.util.logging.Logger;


public class UserServlet extends HttpServlet {

    

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
        
        RoleService rs = new RoleService();
        UserService us = new UserService();
        
        try {           
            List<User> users = us.getAll();
            List<Role> roles = rs.getAll();
            request.setAttribute("users", users);
            request.setAttribute("roles", roles);
            
            this.getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        String action = request.getParameter("action");
        request.setAttribute("message", "");
        
            if (action != null && action.equals("add")) {
                try {
                    RoleService rs = new RoleService();
                    UserService us = new UserService();

                    int roleId = 0;

                    String email = request.getParameter("email");
                    boolean active = request.getParameter("active") != null;
                    String firstName = request.getParameter("firstName");
                    String lastName = request.getParameter("lastName");
                    String password = request.getParameter("password");
                    String roleName = request.getParameter("role");

                    List<Role> roles;
                    roles = rs.getAll();

                    for (Role role : roles) {
                        if (role.getName().equals(roleName)) {
                            roleId = role.getId();
                        }
                    }
                    Role role = new Role(roleId, roleName);
                    us.insert(email, active, firstName, lastName, password, role);

                    request.setAttribute("message", "User added successfully");

                } catch (Exception ex) {
                    Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
                    request.setAttribute("message", "User added unsuccessfully checck the fields");
                }

            } else if (action != null && action.contains("edit?")) {
                try {
                    UserService us = new UserService();

                    String email = action.split("\\?", 2)[1];

                    User user = us.get(email);
                    request.setAttribute("user", user);
                } catch (Exception ex) {
                    Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
                }  
            } else if (action != null && action.equals("update")) {
                try {
                    RoleService rs = new RoleService();
                    UserService us = new UserService();

                    int roleId = 0;

                    String email = request.getParameter("email");
                    boolean active = request.getParameter("active") != null;
                    String firstName = request.getParameter("firstName");
                    String lastName = request.getParameter("lastName");
                    String password = request.getParameter("password");
                    String roleName = request.getParameter("role");

                    List<Role> roles;
                    roles = rs.getAll();

                    for (Role role : roles) {
                        if (role.getName().equals(roleName)) {
                            roleId = role.getId();
                        }
                    }

                    Role role = new Role(roleId, roleName);
                    us.update(email, active, firstName, lastName, password, role);
                    request.setAttribute("message", "User updated successfuly");

                } catch (Exception ex) {
                    Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
                    request.setAttribute("message", "Failed check your fields");
                }

            } else if (action != null && action.contains("delete?")) {
                try {
                    String email = action.split("\\?", 2)[1];

                    UserService us = new UserService();
                    us.delete(email);

                    request.setAttribute("message", "User deleted sucessfully");

                } catch (Exception ex) {
                    Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
                }  
            }
            try {
                UserService us = new UserService();
                RoleService rs = new RoleService();

                List<User> users = us.getAll();
                List<Role> roles = rs.getAll();

                request.setAttribute("users", users);
                request.setAttribute("roles", roles);
            } catch (Exception ex) {
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
        }
}   

