package com.pluto.own.registration.shiro;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

/**
 * 自定义Realm 实现
 * @author ：pluto
 * @date ：Created in 2019/7/30 11:26
 */
public class MyRealm01 implements Realm {
    @Override
    public String getName() {
        return "myRealm01";
    }

    @Override
    public boolean supports(AuthenticationToken authenticationToken) {
        //仅支持UsernamePasswordToken类型的Token
        return authenticationToken instanceof UsernamePasswordToken;
    }

    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        String username = (String)token.getPrincipal();  //得到用户名
        String password = new String((char[])token.getCredentials()); //得到密码
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
