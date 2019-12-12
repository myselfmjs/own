package com.pluto.own.registration.shiro.realm;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.RolePermissionResolver;
import org.apache.shiro.authz.permission.WildcardPermission;

import java.util.Arrays;
import java.util.Collection;

/**
 * 自定义自己的RolePermission
 * RolePermission 根据角色字符串来解析得到权限集合
 * @author ：pluto
 * @date ：Created in 2019/8/1 13:43
 */
public class MyRolePermissionResolver implements RolePermissionResolver {
    /**
     * 如果 用户拥有role01,那么就返回一个menu:*:* 权限；
     * 该权限添加到用户的权限列表里
     * @param roleString
     * @return
     */
    @Override
    public Collection<Permission> resolvePermissionsInRole(String roleString) {
        if ("role001".equals(roleString)){
            return Arrays.asList((Permission)new WildcardPermission("menu:*:*"));
        }
        return null;
    }
}
