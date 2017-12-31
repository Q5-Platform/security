//package com.codingapi.security.interceptor;
//
//import com.alibaba.fastjson.JSON;
//import com.codingapi.security.model.Msg;
//import com.codingapi.security.model.Response;
//import com.lorne.core.framework.exception.ServiceException;
//import org.apache.commons.lang.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
///**
// * Created by houcunlu on 2017/8/17.
// */
//@ControllerAdvice
//public class GlobalExceptionHandler {
//
//    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
//
//
//    @ExceptionHandler(value = Exception.class)
//    public String  exceptionHandler( HttpServletResponse response, HttpServletRequest req,Object handler, Exception e) throws Exception {
//
//        logger.info("exceptionHandler->"+e+",handler->"+handler);
//
//        Object isDefaultResponse =  req.getAttribute("isDefaultResponse");
//        if(isDefaultResponse!=null){
//            throw e;
//        }
//
//        Response res = new Response();
//        res.setCode(40010);
//        if ( e instanceof ServiceException) {
//            res.setMsg(e.getMessage());
//        }else{
//            e.printStackTrace();
//            res.setMsg(e.getClass().getName());
//        }
//        Msg _msg = new Msg();
//        _msg.setRes(res);
//        String json = JSON.toJSONString(_msg).toString();
//
//        response.setContentType("text/html;charset=utf-8");
//        response.setHeader("Content-type",
//                "application/json;charset=utf-8");
//        response.getWriter().print(json);
//        response.getWriter().close();
//        return json;
//    }
//
//
//
//
//}
