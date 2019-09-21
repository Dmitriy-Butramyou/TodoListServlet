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
        req.getRequestDispatcher("view/addUser.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        List<User> userList = userDao.getAll();
        boolean uniqueName = true;
        boolean addUser = false;

        String userName = req.getParameter("userName");
        String password = req.getParameter("password");
        String retypePassword = req.getParameter("retypePassword");

        boolean checkPassword = (password != null &&
                !password.trim().isEmpty() &&
                retypePassword != null &&
                !retypePassword.trim().isEmpty() &&
                password.equals(retypePassword));

        if(checkPassword && userName != null && !userName.trim().isEmpty()) {
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
            req.setAttribute("error", "Wrong data");
            req.getRequestDispatcher("view/addUser.jsp")
                    .forward(req, resp);
        }
    }
}
