package org.msy.servlet;

import com.alibaba.fastjson.JSON;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.msy.bean.User;
import org.msy.mapper.UserMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Msy
 * @date 2023/2/7  16:46
 */
@WebServlet("/users")
public class UserServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        String m = req.getParameter("m");
        if ("query".equals(m)){
            query(req,resp);
        }else if ("queryById".equals(m)){
            queryById(req,resp);
        }else if ("insert".equals(m)){
            insert(req,resp);
        }else if ("update".equals(m)){
            update(req,resp);
        }else if ("delete".equals(m)){
            delete(req,resp);
        }
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("MyBatis-config.xml"));
        SqlSession session = sessionFactory.openSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        int i = mapper.delete(id);
        session.commit();
        resp.getWriter().print(i>0);
        session.close();
    }

    private void update(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String sex = req.getParameter("sex");
        String birthday = req.getParameter("birthday");
        String address = req.getParameter("address");
        String hobby = req.getParameter("hobby");
        User user = new User(id, username, password, sex, birthday, address, hobby);
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("MyBatis-config.xml"));
        SqlSession session = sessionFactory.openSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        int i = mapper.update(user);
        session.commit();
        resp.getWriter().print(i>0);
        session.close();
    }

    private void insert(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String sex = req.getParameter("sex");
        String birthday = req.getParameter("birthday");
        String address = req.getParameter("address");
        String hobby = req.getParameter("hobby");
        User user = new User(null, username, password, sex, birthday, address, hobby);

        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("MyBatis-config.xml"));
        SqlSession session = sessionFactory.openSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        int i = mapper.insert(user);
        session.commit();
        resp.getWriter().print(i>0);
        session.close();
    }

    private void queryById(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("MyBatis-config.xml"));
        SqlSession session = sessionFactory.openSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        User user = mapper.queryById(id);
        String s = JSON.toJSONString(user);
        resp.getWriter().print(s);
        session.close();
    }

    private void query(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("MyBatis-config.xml"));
        SqlSession session = sessionFactory.openSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        List<User> list = mapper.queryAll();
        String s = JSON.toJSONString(list);
        resp.getWriter().print(s);
        session.close();
    }
}
