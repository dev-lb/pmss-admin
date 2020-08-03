package jg.sso.pmss.admin.controller;


import com.alibaba.fastjson.JSONObject;
import jg.sso.pmss.admin.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "/login")
    public void login(String username, String password, String redirectUrl, HttpServletResponse response) throws IOException {
        boolean suc = loginService.login(username, password);
        if (suc) {
            //设置token
            Cookie cookie = new Cookie("token", UUID.randomUUID().toString());
            cookie.setPath("/");
            response.addCookie(cookie);
            //redis保存token

            //重定向
            response.sendRedirect(redirectUrl);
        } else {
            response.getWriter().write(new JSONObject().toJSONString());
        }
    }

}
