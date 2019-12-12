package com.pluto.own.registration.shiro.realm;

import com.pluto.own.configuration.ShiroConfiguration;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 自定义Realm 实现
 * @author ：pluto
 * @date ：Created in 2019/7/30 11:26
 */
public class MyRealm01 extends AuthorizingRealm {

    private static final Logger logger = LoggerFactory.getLogger(ShiroConfiguration.class);

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
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        String username = (String)authenticationToken.getPrincipal();  //得到用户名
        String password = new String((char[])authenticationToken.getCredentials()); //得到密码
        if(!"admin".equals(username)) {
            System.out.println("错误的账号");
            throw new UnknownAccountException(); //如果用户名错误
        }
        if(!"admin".equals(password)) {
            System.out.println("错误的密码");
            throw new IncorrectCredentialsException(); //如果密码错误
        }
        System.out.println("doGetAuthenticationInfo-----身份验证成功");
        //如果身份认证验证成功，返回一个AuthenticationInfo实现；
        return new SimpleAuthenticationInfo(username, password, getName());
    }

    /**
     * 两种方式触发：
     *  1.Controller 配置了 @RequiresPermissions
     *  2.页面使用了  shiro:hasPermission
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("Authorization----------------------：配置权限");
        SimpleAuthorizationInfo Info = new SimpleAuthorizationInfo();
        Info.addStringPermission("user:info");
        return Info;
    }
}
