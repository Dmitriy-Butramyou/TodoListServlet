package controller.web.logging;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/addUser")
public class CreateUser  extends HttpServlet {

    private UserDao userDao = new UserDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("error", "");
        req.getRequestDispatcher("view/addUser.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        req.setAttribute("error", "Wrong data");

        List<User> userList = userDao.getAll();
        Boolean uniqueName = true;
        Boolean addUser = false;

        String userName = req.getParameter("userName");
        String password = req.getParameter("password");

        if(userName != null && !userName.trim().isEmpty() && password != null && !password.trim().isEmpty()) {
            for (User user : userList) {
                if(user.getName().equals(userName)) {
                    uniqueName = false;
                    req.setAttribute("error", "A user with the same name exists.");

                }
            }
            if(uniqueName) {
                User newUser = new User.Builder()
                        .name(userName)
                        .password(password)
                        .build();
                userDao.save(newUser);
                addUser = true;
            }

        }

        if(addUser) {
            String path = req.getContextPath() + "/";
            resp.sendRedirect(path);
        } else {
            req.getRequestDispatcher("view/addUser.jsp")
                    .forward(req, resp);
        }
    }
}
