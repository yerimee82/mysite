package com.poscodx.mysite.controller;

import com.poscodx.mysite.service.UserService;
import com.poscodx.mysite.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/join", method = RequestMethod.GET)
    public String join(@ModelAttribute UserVo vo) {
        return "user/join";
    }

    @RequestMapping(value = "/join", method = RequestMethod.POST)
    public String join(@ModelAttribute @Valid UserVo vo, BindingResult result, Model model) {
        if(result.hasErrors()) {
//            model.addAttribute("userVo", vo);
//            List<ObjectError> errors = result.getAllErrors();
//            for (ObjectError error : errors) {
//                System.out.println(error);
//            }
            Map<String, Object> map = result.getModel();
            model.addAllAttributes(map);
            return "user/join";
        }
        userService.join(vo);
        return "redirect:/user/joinsuccess";
    }

    @RequestMapping(value="/joinsuccess", method=RequestMethod.GET)
    public String joinsuccess() {
        return "user/joinsuccess";
    }

    @RequestMapping(value = "/login")
    public String login() {
        return "user/login";
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String update(Authentication authentication, Model model) {
        UserVo authUser = (UserVo)authentication.getPrincipal();
        UserVo vo = userService.getUser(authUser.getNo());
        model.addAttribute("userVo", vo);

        return "user/update";
    }

    @RequestMapping(value="/update", method=RequestMethod.POST)
    public String update(Authentication authentication, UserVo vo) {
        UserVo authUser = (UserVo)authentication.getPrincipal();
        vo.setNo(authUser.getNo());
        userService.update(vo);

        authUser.setName(vo.getName());
        return "redirect:/user/updatesuccess";
    }

    @RequestMapping(value="/updatesuccess", method=RequestMethod.GET)
    public String updatesuccess() {
        return "user/updatesuccess";
    }

}
