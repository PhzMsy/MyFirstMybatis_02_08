package org.msy.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Msy
 * @date 2023/2/8  8:35
 */
public interface UserService {
    void query(HttpServletRequest req, HttpServletResponse resp);

    void queryById(HttpServletRequest req, HttpServletResponse resp);

    void insert(HttpServletRequest req, HttpServletResponse resp);

    void update(HttpServletRequest req, HttpServletResponse resp);

    void delete(HttpServletRequest req, HttpServletResponse resp);
}
