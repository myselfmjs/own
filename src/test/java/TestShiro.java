import com.pluto.own.registration.MyRealm01;
import com.pluto.own.registration.MyRealm02;
import com.pluto.own.registration.MyRealmPermission01;
import com.pluto.own.registration.MyRealmPermission02;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.Authenticator;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.pam.AllSuccessfulStrategy;
import org.apache.shiro.authc.pam.FirstSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.authz.ModularRealmAuthorizer;
import org.apache.shiro.config.Ini;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

/**
 *  Shiro 测试用例
 * @author ：pluto
 * @date ：Created in 2019/7/30 10:34
 */
public class TestShiro {

    /**
     * 简单的演示 Shiro的登录登出情况
     * 1.创建一个SecurityManager工厂
     * 2.获取SecurityManger并绑定到SecurityUtils，全局设置,设置一次即可
     * 3.通过SecurityUtils得到Subject；会自动绑定当前线程,然后获取身份验证的token：UsernamePasswordToken
     * 4.调用Subject.login() 进行登录,会自动委托给 SecurityManger.login
     * 5.验证失败会捕获 AuthenticationException 或子类
     * isabledAccountException（禁用的帐号）、LockedAccountException（锁定的帐号）、
     * UnknownAccountException（错误的帐号）、ExcessiveAttemptsException（登录失败次数过多）、
     * IncorrectCredentialsException （错误的凭证）、ExpiredCredentialsException（过期的凭证）
     */
    @Test
    public void ShiroTest(){
        //1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager
        // IniSecurityManagerFactory 方法过期，可通过DefaultSecurityManager 方式获取
       /* Factory<org.apache.shiro.mgt.SecurityManager> factory =
        new IniSecurityManagerFactory("classpath:shiro.ini");*/
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        IniRealm iniRealm = new IniRealm("classpath:shiro.ini");
        defaultSecurityManager.setRealm(iniRealm);
        Ini ini= new Ini();
        ini.loadFromPath("classpath:shiro.ini");
        iniRealm.setIni(ini);

        //2、得到SecurityManager实例 并绑定给SecurityUtils
        //org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(defaultSecurityManager);

        //3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");

        try {
            //4、登录，即身份验证
            subject.login(token);
            System.out.println("身份验证成功！");
        } catch (AuthenticationException e) {
            //5、身份验证失败
            System.out.println("身份验证失败");
        }
        //断言用户已经登录
        Assert.assertEquals(true, subject.isAuthenticated());
        //6、退出
        subject.logout();
    }

    /**
     * 测试自定义Realm
    */
    @Test
    public void testCustomRealm(){

        //1、方式一： 配置shiro-realm.ini 获取SecurityManager 的方法已过时
       /* Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-realm.ini");
        org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);*/

        // 方式二：直接获取自定义的MyRealm 使用默认的 defaultSecurityManager
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(new MyRealm01());
        SecurityUtils.setSecurityManager(defaultSecurityManager);

        //得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");

        try {
            //登录，即身份验证
            subject.login(token);
        } catch (AuthenticationException e) {
            //身份验证失败
            e.printStackTrace();
        }
        Assert.assertEquals(true, subject.isAuthenticated()); //断言用户已经登录

        //退出
        subject.logout();
    }

    /**
     * 多个Realm
     * 配置  验证方式
     * FirstSuccessfulStrategy  只要有一个Realm验证成功即可，只返回第一个Realm身份验证成功的认证信息，其他的忽略；
     * AtLeastOneSuccessfulStrategy：只要有一个Realm验证成功即可，和FirstSuccessfulStrategy不同，返回所有Realm身份验证成功的认证信息；
     * AllSuccessfulStrategy：所有Realm验证成功才算成功，且返回所有Realm身份验证成功的认证信息，如果有一个失败就失败了。
     */
    @Test
    public void testCustomMultiRealm(){

        //1、方式一： 配置shiro-realm.ini 获取SecurityManager 的方法已过时
       /* Factory<org.apache.shiro.mgt.SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-multi-realm.ini");
        org.apache.shiro.mgt.SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);*/

        // 方式二：直接获取自定义的MyRealm 使用默认的 defaultSecurityManager
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        //多个Realm 验证
        Collection<Realm> realms = new ArrayList<>();
        realms.add(new MyRealm01());
        realms.add(new MyRealm02());
        // 设置验证方式
        ModularRealmAuthenticator modular = new ModularRealmAuthenticator();
        modular.setAuthenticationStrategy(new FirstSuccessfulStrategy());

        modular.setRealms(realms);
        defaultSecurityManager.setAuthenticator(modular);

        SecurityUtils.setSecurityManager(defaultSecurityManager);

        //得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");

        try {
            //登录，即身份验证
            subject.login(token);
        } catch (AuthenticationException e) {
            //身份验证失败
            e.printStackTrace();
        }
        Assert.assertEquals(true, subject.isAuthenticated()); //断言用户已经登录
        //退出
        subject.logout();
    }

    /**
     * Shiro 授权测试
     */
    @Test
    public void testPermissionRealm(){

        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        //多个Realm
        Collection<Realm> realms = new ArrayList<>();
        realms.add(new MyRealmPermission01());
        realms.add(new MyRealmPermission02());

        // 设置 多个授权，匹配其中一个则为True
        ModularRealmAuthorizer modularRealmAuthorizer = new ModularRealmAuthorizer();
        modularRealmAuthorizer.setRealms(realms);
        defaultSecurityManager.setAuthorizer(modularRealmAuthorizer);

        // 设置 多个Realm 认证  可自定认证规则
        ModularRealmAuthenticator modularRealmAuthenticator = new ModularRealmAuthenticator();
        modularRealmAuthenticator.setRealms(realms);
        modularRealmAuthenticator.setAuthenticationStrategy(new FirstSuccessfulStrategy());
        defaultSecurityManager.setAuthenticator(modularRealmAuthenticator);

        // 设置 SecurityManager
        SecurityUtils.setSecurityManager(defaultSecurityManager);

        //得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");

        try {
            //登录，即身份验证
            subject.login(token);
        } catch (AuthenticationException e) {
            //身份验证失败
            e.printStackTrace();
        }
        Assert.assertEquals(true, subject.isPermitted("user:add:select")); //断言用户是否有权限

        // 获取 当前主体的角色、权限(通过setSession 参数的方式)
        System.out.println(SecurityUtils.getSubject().getSession().getAttribute("role"));
        System.out.println(SecurityUtils.getSubject().getSession().getAttribute("permission"));
        //退出
        subject.logout();

    }
}
