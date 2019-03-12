package cn.wu.am.web.controller;

import cn.wu.am.entity.User;
import cn.wu.am.entity.td.UserTD;
import cn.wu.am.param.PageBean;
import cn.wu.am.param.QueryInfo;
import cn.wu.am.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserManage {

    @Autowired
    private UserService userService;

    @RequestMapping("/users")
    public String listUsers(@RequestParam(name = "currentPage", defaultValue = "1") int currentPage,
                            @RequestParam(name = "pageSize", defaultValue = "10") int pageSize, Model model) {
        QueryInfo queryInfo = new QueryInfo();
        queryInfo.setPagesize(pageSize);
        queryInfo.setCurrentpage(currentPage);
        PageBean<User> pageBean = userService.getAll(queryInfo);
        model.addAttribute("pageBean", pageBean);

        return "admin/list_users";
    }

    @GetMapping("/adduser")
    public String adduserUI() {
        return "admin/adduser";
    }

    @GetMapping("/check_user")
    public String check_userUI(int id, Model model) {
        User user = userService.findUserById(id);
        String errorMsg = "";
        if(user==null) {
            errorMsg = "该用户不存在";
            model.addAttribute("errorMsg", errorMsg);
            return "info";
        }
        UserTD userTD = new UserTD(user);
        model.addAttribute("userTD", userTD);
        return "admin/user_check";
    }

    @PostMapping("/check_user")
    public String check_user(int id, int status, Model model) {
        String errorMsg = "";
        User user = userService.findUserById(id);
        if(user==null) {
            errorMsg = "该用户不存在";
            model.addAttribute("errorMsg", errorMsg);
            return "info";
        }
        boolean flag = userService.updateStatus(id, status);
        if(!flag) {
            errorMsg = "出现未知错误";
            model.addAttribute("errorMsg", errorMsg);
            return "info";
        }
        return "redirect:/user_detail?id=" + id;
    }
}
