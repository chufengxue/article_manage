package cn.wu.am.config;


import cn.wu.am.entity.User;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Component
@Aspect
public class AuthAspect {


    @Around("execution(* cn.wu.am.web.controller.UserManage.*(..))")
    public Object authAdmin(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("authAdmin");
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        User user = (User) request.getSession().getAttribute("login_user");
        if(user==null || !"admin".equals(user.getUserType())) {
            // 没有权限，直接返回首页
            return "redirect:/index";
        }
        return joinPoint.proceed();
    }

}
