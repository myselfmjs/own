package com.pluto.own.controller;

import com.pluto.own.common.AjaxResult;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.pluto.own.common.AjaxResult.error;
import static com.pluto.own.common.AjaxResult.success;

/**
 * @author ：pluto
 * @date ：Created in 2019/12/4 09:47
 * 登录
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    private String prefix= "html/login";

    //登录页面
    @RequestMapping("/index")
    public String index(){
        return prefix + "/login";
    }


    //登录验证
    @PostMapping("/login")
    @ResponseBody
    //@CacheEvict(value="OnlineUser")
    public AjaxResult ajaxLogin(String username, String password,Boolean rememberMe) {
        UsernamePasswordToken token = new UsernamePasswordToken(username, password,rememberMe);
        Subject subject = SecurityUtils.getSubject();
        try
        {
            subject.login(token);
            return success();
        }
        catch (AuthenticationException e)
        {
            String msg = "用户或密码错误";
            if (StringUtils.isNotEmpty(e.getMessage()))
            {
                msg = e.getMessage();
            }
            return error(msg);
        }
    }

    @RequestMapping("/success")
    public String succss(){
        return prefix + "/success";
    }




    // 退出
    @RequestMapping("/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();//取出当前验证主体
        if (subject != null) {
            subject.logout();//不为空，执行一次logout的操作，将session全部清空
        }
        return prefix +"/login";
    }

    // 跳转页面1
    @RequestMapping("/one")
    @RequiresPermissions("user:info")
    public String one(){
        return prefix +"/one";
    }

    @RequestMapping("/unauth")
    public String unauth(){
        return prefix + "/unauth";
    }
}
