package cn.wu.am.web.controller;

import cn.wu.am.dao.ArticleMapper;
import cn.wu.am.entity.User;
import cn.wu.am.entity.td.UserTD;
import cn.wu.am.mconst.UserConst;
import cn.wu.am.param.LoginParam;
import cn.wu.am.param.RegisterParam;
import cn.wu.am.service.ArticleService;
import cn.wu.am.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private ArticleService articleService;

    @GetMapping("/login")
    public String loginUI() {
        return "user/login";
    }

    @PostMapping("/login")
    public String login(@Valid LoginParam loginParam , BindingResult result, ModelMap model, HttpServletRequest request) {
        User loginedUser = (User) request.getSession().getAttribute(UserConst.LOGIN_USER);
        if(loginedUser!=null) {
            return "redirect:/index";
        }
        String errorMsg = "";
        if (result.hasErrors()) {
            List<ObjectError> list = result.getAllErrors();
            for (ObjectError error : list) {
                errorMsg = errorMsg + error.getDefaultMessage() + "; ";
            }
            model.addAttribute("errorMsg", errorMsg);
            return "user/login";
        }
        User user = userService.login(loginParam.getUsername(), loginParam.getPassword());
        if (user == null) {
            model.addAttribute("errorMsg", "用户名与密码不匹配!");
            return "user/login";
        }
        request.getSession().setAttribute(UserConst.LOGIN_USER, user);
        request.getSession().setAttribute(UserConst.LOGIN_TYPE, user.getUserType());
        return "redirect:/";
    }

    // 退出登录
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().removeAttribute(UserConst.LOGIN_USER);
        request.getSession().removeAttribute(UserConst.LOGIN_TYPE);
        return "redirect:/";
    }

    @GetMapping("/register")
    public String registerUI() {
        return "user/register";
    }

    @PostMapping("/register")
    public String register(@Valid RegisterParam registerParam, BindingResult result, ModelMap model, HttpServletRequest request) {
        String errorMsg = "";
        if (result.hasErrors()) {
            List<ObjectError> list = result.getAllErrors();
            for (ObjectError error : list) {
                errorMsg = errorMsg + error.getDefaultMessage() + "; ";
            }
            model.addAttribute("errorMsg", errorMsg);
            return "user/register";
        }

        String password = registerParam.getPassword();
        if(!password.equals(registerParam.getPassword2())) {
            errorMsg = "密码与确认密码不一致！";
            model.addAttribute("errorMsg", errorMsg);
            return "user/register";
        }

        User user = userService.findUserByUsername(registerParam.getUsername());
        if (user != null) {
            errorMsg = "该用户名已存在！";
            model.addAttribute("errorMsg", errorMsg);
            return "user/register";
        }

        user = new User();
        user.setUsername(registerParam.getUsername());
        user.setPassword(password);
        user.setUserStatus(UserConst.USER_STATUS_CHECKING);
        user.setUserType(UserConst.USER_TYPE_COMMON);
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        boolean flag = userService.register(user);
        if(!flag) {
            errorMsg = "由于未知原因，注册失败！";
            model.addAttribute("errorMsg", errorMsg);
            return "user/register";
        }

        request.getSession().setAttribute(UserConst.LOGIN_USER, user);
        request.getSession().setAttribute(UserConst.LOGIN_TYPE, user.getUserType());
        return "redirect:/";
    }

    @RequestMapping("/user_detail")
    public String getUserDetailById(int id, Model model, HttpServletRequest request) {
        User loginedUser = (User) request.getSession().getAttribute("login_user");
        User findUser = userService.findUserById(id);
        String errorMsg = "";

        if(findUser==null) {
            errorMsg = "该用户不存在";
            model.addAttribute("errorMsg", errorMsg);
            return "info";
        }

        if(loginedUser==null || !loginedUser.getUserType().equals("admin")) {
            if(loginedUser.getId() != findUser.getId().intValue()) {
                errorMsg = "你没有相应的权限";
                model.addAttribute("errorMsg", errorMsg);
                return "info";
            }
        }

        int articleCount = articleService.getUserArticleCount(findUser.getId());

        UserTD userTD = new UserTD(findUser);
        userTD.setArticleCount(articleCount);
        model.addAttribute("userTD", userTD);
        return "admin/user_detail";
    }

    @GetMapping("updatepassword")
    public String updatePasswordUI(Model model, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(UserConst.LOGIN_USER);
        String errorMsg = "";
        if(user==null) {
            errorMsg = "你还没有登录";
            model.addAttribute("errorMsg", errorMsg);
            return "info";
        }
        model.addAttribute("id", user.getId());
        return "user/updatepassword";
    }

    @PostMapping("/updatepassword")
    public String updatePassword(int id, String oldPassword, String newPassword, Model model) {
        model.addAttribute("id", id);
        System.out.println("id="+id +" old=" + oldPassword +" new="+ newPassword);
        User user = userService.findUser(id, oldPassword);
        if(user == null) {
            model.addAttribute("errorMsg", "原密码错误");
            return "user/updatepassword";
        }
        if(oldPassword==null || oldPassword.length()<6) {
            model.addAttribute("errorMsg", "密码长度不能少于6位");
            return "user/updatepassword";
        }
        boolean flag = userService.updatePassword(id, newPassword);
        if(!flag) {
            model.addAttribute("errorMsg", "发生未知错误，密码修改失败");
        } else {
            model.addAttribute("errorMsg", "修改成功");
        }
        return "info";
    }
}
