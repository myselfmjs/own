package com.pluto.own.domain.pojo;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 *  数据库User
 * @author ：pluto
 * @date ：Created in 2019/7/30 10:21
 */
public class User {

    private Integer id;
    @NotEmpty(message = "用户名不能为空")
    private String username;
    @NotEmpty(message = "密码不能为空")
    private String password;

    private List<Role> roleList;// 一个用户具有多个角色

    public User() {
    }

    public User(Integer id, String username,String password, List<Role> roleList) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roleList = roleList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }
}
