package org.msy.servlet;

import org.msy.service.UserService;
import org.msy.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @author Msy
 * @date 2023/2/7  16:46
 */
@WebServlet("/users")
public class UserServlet extends HttpServlet {
    UserService userService = new UserServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        String m = req.getParameter("m");
        if ("query".equals(m)){
            userService.query(req,resp);
        }else if ("queryById".equals(m)){
            userService.queryById(req,resp);
        }else if ("insert".equals(m)){
            userService.insert(req,resp);
        }else if ("update".equals(m)){
            userService.update(req,resp);
        }else if ("delete".equals(m)){
            userService.delete(req,resp);
        }


    }

}
