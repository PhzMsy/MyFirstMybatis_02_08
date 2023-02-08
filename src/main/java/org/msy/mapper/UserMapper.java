package org.msy.mapper;

import org.msy.bean.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Msy
 * @date 2023/2/7  16:45
 */
public interface UserMapper {

    List<User> queryAll();

    User queryById(String id);

    int insert(User user);

    int update(User user);

    int delete(String id);
}
