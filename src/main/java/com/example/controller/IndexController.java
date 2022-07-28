package com.example.controller;

import com.example.entity.User;
import com.example.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
@RequestMapping("/")
public class IndexController {
    @Resource
    private UserService userService;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String login_Get(){
        return "login";
    }
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login_Get_2(){
        return "login";
    }

    @RequestMapping(value = "/welcome",method = RequestMethod.GET)
    public String welcome(){
        return "welcome";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@ModelAttribute("username") String username, @ModelAttribute("password") String password,
                        HttpServletRequest request, HttpServletResponse response){
        String msg;
        if("".equals(username)||"".equals(password)){
            msg="账号或者密码不能为空";
        }
        else{
            User user = userService.getUserByUsername(username);
            if(user != null && user.getPassword().equals(password)){
                //将用户对象添加到Session中
                request.getSession().setAttribute("USER_SESSION",username);
                return "welcome";
            }
            msg="账号或者密码错误";
        }
        try {
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out = response.getWriter();
            //弹窗
            out.printf("<script>alert('%s');history.back();</script>",msg);
            //这句很关键
            out.flush();
            out.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
        return "login";
    }


}