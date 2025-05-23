package com.example.servlets;

import com.example.models.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/DeleteAccountServlet")
public class DeleteAccountServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("user");

        String password = request.getParameter("password");

        try {
            // Verify password
            if (!currentUser.getPassword().equals(password)) {
                request.setAttribute("error", "Incorrect password");
                request.getRequestDispatcher("account.jsp").forward(request, response);
                return;
            }

            List<User> users = FileHandler.readUsers();
            users.removeIf(user -> user.equals(currentUser));

            FileHandler.updateUsers(users);
            session.invalidate(); // Logout user
            response.sendRedirect("emplogin.jsp");

        } catch (IOException e) {
            e.printStackTrace();
            request.setAttribute("error", "Account deletion failed");
            request.getRequestDispatcher("account.jsp").forward(request, response);
        }
    }
}