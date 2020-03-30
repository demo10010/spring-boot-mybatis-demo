package com.haizhi.mybatises.interceptor;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EsSqlInterceptor extends HandlerInterceptorAdapter {

    @Override
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
            throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
            throws Exception {
        // TODO Auto-generated method stub

    }

    //之前拦截
    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object obj) throws Exception {
//        //你请求的目标必须是方法
//        if(obj instanceof HandlerMethod) {
//            HandlerMethod hm = (HandlerMethod) obj;
//            Object o =  hm.getMethodAnnotation(anno.class);
//            if(o==null) {//没有这个注解
//                return true;
//
//            }else {//有注解
//
//                if(UserContext.getCurrent() == null) {//没有登录
//
//                    resp.sendRedirect("/paike/login.jsp");
//                    return false;
//                }else { //登录了
//                    return true;
//                }
//
//            }
//
//        }


        return true;
    }

}
