package com.codingapi.security.interceptor;

import com.codingapi.security.annotation.DefaultResponse;
import com.codingapi.security.model.Msg;
import com.codingapi.security.model.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * Created by houcunlu on 2017/8/18.
 */
@ControllerAdvice
public class DataResponseBodyAdvice implements ResponseBodyAdvice {


    private Logger logger = LoggerFactory.getLogger(DataResponseBodyAdvice.class);

    @Override
    public boolean supports(MethodParameter returnType, Class aClass) {
        Class controller =  returnType.getMethod().getDeclaringClass();
        DefaultResponse noControllerAdvice = (DefaultResponse) controller.getAnnotation(DefaultResponse.class);
        String className = controller.getName();
        logger.info("supports - className >" +className +", NoControllerAdvice - > "+noControllerAdvice);
        if(noControllerAdvice!=null){
            return false;
        }
        return true;
    }


    /**
     * 统一处理  正常返回数据
     * @return
     */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {

        logger.info("beforeBodyWrite - >" +body );

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



        Response res = new Response();
        res.setCode(40000);
        res.setMsg("");
        res.setData(body);
        Msg msg = new Msg(1, res);
        return msg;

    }




}
