package com.codingapi.security.core.handler;

import com.codingapi.filter.core.interceptor.handler.FilterDataResponseHandler;
import com.codingapi.filter.zuul.handler.self.SelfZuulFilterDataResponseHandler;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;

/**
 * create by lorne on 2017/12/31
 */
public class SecurityFilterDataResponseHandler implements FilterDataResponseHandler{

    SelfZuulFilterDataResponseHandler selfZuulFilterDataResponseHandler = new SelfZuulFilterDataResponseHandler();

    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {

        String swaggerClass = returnType.getMethod().getDeclaringClass().getName();

        if( swaggerClass.equals("springfox.documentation.swagger.web.ApiResourceController") ||
            swaggerClass.equals("springfox.documentation.swagger2.web.Swagger2Controller") ){
            return  body;
        }
        /**
         * 文档
         */
        if(returnType.getMethod().getName().equals("swaggerResources")){
            return  body;
        }

        /**
         *  分页数据  接口地址必须以 Page 结尾   /admin/findUserPage
         */
        String url =  ((ServletServerHttpRequest) request).getServletRequest().getRequestURI();
        if("Page".equals(url.substring(url.length()-4))){
            return  body;
        }


        return selfZuulFilterDataResponseHandler.beforeBodyWrite(body, returnType, selectedContentType, selectedConverterType, request, response);
    }
}
