package com.esieve.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.esieve.common.bean.OperationResult;
import com.esieve.user.bean.User;
import com.esieve.user.service.UserService;
import com.esieve.util.MD5Util;
import com.google.common.base.Preconditions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by 77239 on 2017/4/3/0003.
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    @Reference
    private UserService userService;

    @RequestMapping(method = {RequestMethod.GET})
    public String showLoginView(HttpSession session) {
        User user = (User) session.getAttribute("curUser");
        if (user != null) {
            return "redirect:/manage";
        } else {
            return "login/login";
        }
    }

    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public String checkUser(HttpServletRequest request, RedirectAttributes attributes) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        HttpSession session = request.getSession();
        User user = new User(request.getParameter("username"), MD5Util.encoderPassword(request.getParameter("password")));
        OperationResult<User> result = userService.checkUser(user);

        Preconditions.checkNotNull(result);
        if (result.isSuccess()) {
            session.setAttribute("curUser", result.getData());
            return "redirect:/manage";
        } else {
            attributes.addFlashAttribute("info", result.getInfo());
            return "redirect:/login";
        }
    }

}
