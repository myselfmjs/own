package com.pluto.own.registration.shiro.realm;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * Shiro 授权
 * 自定义 继承 AuthorizingRealm
 * @author ：pluto
 * @date ：Created in 2019/8/1 10:57
 */
public class MyRealmPermission01 extends AuthorizingRealm {

    /**
     * 授权
     * 只有当用到授权的时候才执行该方法
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRole("role01");
        info.addStringPermission("user:add:select");
        // 将权限信息set 到Session参数中，方便在其它地方调用获取当前主体的权限信息
        SecurityUtils.getSubject().getSession().setAttribute("role","admin");
        SecurityUtils.getSubject().getSession().setAttribute("permission","user:add:select");
        return info;
    }

    /**
     * 认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

       // String username = (String)token.getPrincipal();  //得到用户名
       // String password = new String((char[])token.getCredentials()); //得到密码
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        String username = upToken.getUsername();
        String password = new String(upToken.getPassword());

        if(!"zhang".equals(username)) {
            System.out.println("错误的账号");
            throw new UnknownAccountException(); //如果用户名错误
        }
        if(!"123".equals(password)) {
            System.out.println("错误的密码");
            throw new IncorrectCredentialsException(); //如果密码错误
        }
        System.out.println("身份验证成功");
        //如果身份认证验证成功，返回一个AuthenticationInfo实现；
        return new SimpleAuthenticationInfo(username, password, getName());

    }
}
