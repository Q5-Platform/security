//package com.codingapi.security.interceptor;
//
//import com.codingapi.security.annotation.DefaultResponse;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//import org.springframework.web.method.HandlerMethod;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
///**
// * Created by houcunlu on 2017/8/16.
// */
//@Component
//public class ExceptionInterceptor implements  HandlerInterceptor {
//
//    private Logger logger = LoggerFactory.getLogger(ExceptionInterceptor.class);
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception {
//
//        HandlerMethod handlerMethod = (HandlerMethod) handler;
//
//        if(handlerMethod!=null) {
//            if(handlerMethod.getMethod()!=null){
//
//                logger.info("handlerMethod.getBeanType()->" + handlerMethod.getBeanType());
//                logger.info("handlerMethod.getDeclaringClass()->" + handlerMethod.getMethod().getDeclaringClass());
//
//                Class clazz =  handlerMethod.getMethod().getDeclaringClass();
//
//                DefaultResponse defaultResponse =  (DefaultResponse) clazz.getAnnotation(DefaultResponse.class);
//                if(defaultResponse!=null){
//                    request.setAttribute("isDefaultResponse","1");
//                }
//            }
//
//        }
//
//        return true;
//    }
//
//
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//
//    }
//
//
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//
//    }
//
//
//}
