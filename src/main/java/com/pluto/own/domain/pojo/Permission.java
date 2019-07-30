package com.pluto.own.domain.pojo;

/**
 * @author ：pluto
 * @date ：Created in 2019/7/30 10:23
 */
public class Permission {


    private Integer id;

    private String permissionname;

    private Role role;// 一个权限对应一个角色


    public Permission() {
    }

    public Permission(Integer id, String permissionname, Role role) {
        this.id = id;
        this.permissionname = permissionname;
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPermissionname() {
        return permissionname;
    }

    public void setPermissionname(String permissionname) {
        this.permissionname = permissionname;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
