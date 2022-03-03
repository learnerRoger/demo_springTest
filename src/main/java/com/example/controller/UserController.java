package com.example.controller;

import com.example.domain.ResponseResult;
import com.example.domain.Role;
import com.example.domain.User;
import com.example.service.RoleService;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import com.example.utils.ResponseUtils2;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;


    @RequestMapping("/list")
    public ModelAndView list() throws Exception{
        List<User> userList = userService.list();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userList",userList);
        modelAndView.setViewName("user-list");
        return modelAndView;
    }

//    @RequestMapping("/listTest")
//    public ModelAndView listTest() throws Exception{
//        List<User> userList = (List<User>) ResponseUtils2.success(userService.list());
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.addObject("userLists",userList);
//        modelAndView.setViewName("user-list-test");
//        System.out.println(ResponseUtils2.success(userList).getData());
//        return modelAndView;
//    }

    @RequestMapping("/showListInJsonFormat")
    @ResponseBody
    public ResponseResult<Object> show() throws Exception{
        List<User> userList = userService.list();
        return ResponseUtils2.success(userList);
    }

    @RequestMapping("/showPart")
    @ResponseBody
    public Object showPart() throws Exception{
        System.out.println(show().getData());
        return show().getData();
    }

    @RequestMapping("/saveUI")
    public ModelAndView saveUI(){
        ModelAndView modelAndView = new ModelAndView();
        List<Role> roleList = roleService.list();
        modelAndView.addObject("roleList",roleList);
        modelAndView.setViewName("user-add");
        return modelAndView;
    }

    @RequestMapping("/save")
    public String save(User user,Long[] roleIds){
        userService.save(user,roleIds);
        return "redirect:/user/list";
    }

    @RequestMapping("/del/{userId}")
    public String del(@PathVariable("userId") Long userId){
        userService.del(userId);
        return "redirect:/user/list";
    }



    @RequestMapping("/login")
    public String login(String username, String  password, HttpSession httpSession){
        User user = userService.login(username,password);
        if (user != null){
            httpSession.setAttribute("user",user);
            System.out.println(httpSession.getAttribute("user"));
            return "redirect:/pages/main.jsp";
        }
        return "redirect:/login.jsp";
    }
}
