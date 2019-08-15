package controller.web;

import dao.impl.TaskDaoImpl;
import model.User;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/index")
public class IndexServlet extends HttpServlet {

    private TaskDaoImpl taskDao = new TaskDaoImpl();

    public void init(ServletConfig servletConfig) {
        try {
            super.init(servletConfig);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }


        @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            HttpSession session = req.getSession();
            User sessionUser = (User) session.getAttribute("user");

                req.setAttribute("name", sessionUser.getName());
            req.getRequestDispatcher("view/index.jsp")
                .forward(req, resp);
    }
}
