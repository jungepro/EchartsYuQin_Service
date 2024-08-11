package com.hui.tupian.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.hui.tupian.entity.Yonghu;
import com.hui.tupian.util.Result;
import com.hui.tupian.util.SessionUser;
import com.hui.tupian.util.SessionUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


/**
 * 登录后端
 */
@Controller
@RequestMapping("gologin")
public class DoLoginController {


    @GetMapping("login")
    public String login() {
        return "login";
    }


    @GetMapping("logout")
    public String logout(HttpServletRequest request) {
        request.getSession().setAttribute(SessionUtil.SESSION_USER, null);
        return "redirect:index";
    }


    @PostMapping("toLogin")
    @ResponseBody
    public Result<?> toLogin(@RequestBody JSONObject object, HttpServletRequest request) {
        String username = object.getString("username");
        String password = object.getString("password");
        // check login
        LambdaQueryWrapper<Yonghu> query = new LambdaQueryWrapper<>();
        query.eq(Yonghu::getAccount, username);
        query.eq(Yonghu::getPwd, password);
        Yonghu one = new Yonghu();
        if (one != null) {
            SessionUser sessionUser = new SessionUser();
            BeanUtils.copyProperties(one, sessionUser);
            request.getSession().setAttribute(SessionUtil.SESSION_USER, sessionUser);
            return Result.OK();
        } else {
            return Result.error("");
        }
    }

    @GetMapping("register")
    public String register() {
        return "register";
    }

    @PostMapping("toRegister")
    @ResponseBody
    public Result<?> toRegister(@RequestBody JSONObject object) {
        Yonghu yonghu = JSONObject.toJavaObject(object, Yonghu.class);
        return Result.OK(object);
    }


    @GetMapping("doUpdateInfo")
    @ResponseBody
    public Result<?> doUpdateInfo(String name, String phone, HttpServletRequest request) {
        SessionUser sessionUser = SessionUtil.getSessionUser(request);
        Yonghu yonghu = new Yonghu();
        yonghu.setId(sessionUser.getId());
        yonghu.setName(name);
        yonghu.setPhone(phone);
        sessionUser.setName(name);
        sessionUser.setPhone(phone);
        request.getSession().setAttribute(SessionUtil.SESSION_USER, sessionUser);
        return Result.OK();
    }


}
