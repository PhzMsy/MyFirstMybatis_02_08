package org.msy.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Msy
 * @date 2023/2/7  16:44
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer id;
    private String username;
    private String password;
    private String sex;
    private String birthday;
    private String address;
    private String hobby;
}
