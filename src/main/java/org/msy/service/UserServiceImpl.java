package org.msy.service;

import com.alibaba.fastjson.JSON;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.msy.bean.User;
import org.msy.mapper.UserMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Msy
 * @date 2023/2/8  8:36
 */
public class UserServiceImpl implements UserService {

    @Override
    public void query(HttpServletRequest req, HttpServletResponse resp) {
        try {
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("MyBatis-config.xml"));
            SqlSession session = sessionFactory.openSession();
            UserMapper mapper = session.getMapper(UserMapper.class);
            List<User> list = mapper.queryAll();
            String s = JSON.toJSONString(list);

            resp.getWriter().print(s);
            session.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void queryById(HttpServletRequest req, HttpServletResponse resp) {
        String id = req.getParameter("id");
        try {
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("MyBatis-config.xml"));
            SqlSession session = sessionFactory.openSession();
            UserMapper mapper = session.getMapper(UserMapper.class);
            User user = mapper.queryById(id);
            String s = JSON.toJSONString(user);

            resp.getWriter().print(s);
            session.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void insert(HttpServletRequest req, HttpServletResponse resp) {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String sex = req.getParameter("sex");
        String birthday = req.getParameter("birthday");
        String address = req.getParameter("address");
        String hobby = req.getParameter("hobby");
        User user = new User(null, username, password, sex, birthday, address, hobby);
        try {
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("MyBatis-config.xml"));
            SqlSession session = sessionFactory.openSession();
            UserMapper mapper = session.getMapper(UserMapper.class);
            int i = mapper.insert(user);
            session.commit();
            resp.getWriter().print(i > 0);
            session.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("id"));
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String sex = req.getParameter("sex");
        String birthday = req.getParameter("birthday");
        String address = req.getParameter("address");
        String hobby = req.getParameter("hobby");
        User user = new User(id, username, password, sex, birthday, address, hobby);
        try {
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("MyBatis-config.xml"));
            SqlSession session = sessionFactory.openSession();
            UserMapper mapper = session.getMapper(UserMapper.class);
            int i = mapper.update(user);
            session.commit();

            resp.getWriter().print(i > 0);
            session.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(HttpServletRequest req, HttpServletResponse resp) {
        String id = req.getParameter("id");
        SqlSessionFactory sessionFactory = null;
        try {
            sessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("MyBatis-config.xml"));
            SqlSession session = sessionFactory.openSession();
            UserMapper mapper = session.getMapper(UserMapper.class);
            int i = mapper.delete(id);
            session.commit();
            resp.getWriter().print(i > 0);
            session.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
