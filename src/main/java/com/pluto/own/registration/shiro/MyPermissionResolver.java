package com.pluto.own.registration.shiro;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.PermissionResolver;
import org.apache.shiro.authz.permission.WildcardPermission;

/**
 * 用于解析权限字符到Permission实例
 * @author ：pluto
 * @date ：Created in 2019/8/1 14:05
 */
public class MyPermissionResolver implements PermissionResolver {


    @Override
    public Permission resolvePermission(String s) {
        WildcardPermission wildcardPermission = new WildcardPermission(s);
        return wildcardPermission;
    }
}
