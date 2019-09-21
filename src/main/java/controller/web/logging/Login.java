package controller.web.logging;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/")
public class Login extends HttpServlet {

    private UserDao userDao = new UserDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("view/login.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> userList = userDao.getAll();


        HttpSession session = req.getSession();
        User userSession;

        boolean login = false;
        req.setCharacterEncoding("UTF-8");
        String userName = req.getParameter("userName");
        String password = req.getParameter("password");

        resp.setCharacterEncoding("UTF-8");

        if(userName != null && !userName.trim().isEmpty() && password != null && !password.trim().isEmpty()) {

            for (User user : userList) {
                if (user.getName().equals(userName)) {
                    if (user.getPassword().equals(password)) {
                        userSession = user;
                        session.setAttribute("user", userSession);
                        login = true;
                    }
                }
            }
        }
        if(login) {
            String path = req.getContextPath() + "/allTask";
            resp.sendRedirect(path);
        } else {
            req.setAttribute("error", "Wrong login or password");
            req.getRequestDispatcher("view/login.jsp")
                    .forward(req, resp);
        }
    }
}
