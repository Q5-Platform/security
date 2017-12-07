package com.codingapi.security.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lorne.core.framework.model.Msg;
import com.lorne.core.framework.model.Response;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by houcunlu on 2017/7/26.
 */
public class PostFilter extends ZuulFilter {



    private Logger logger = LoggerFactory.getLogger(PostFilter.class);

    @Override
    public String filterType() {
        return "post";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        Msg msg = new Msg();
        Response res = new Response();
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletResponse response =  ctx.getResponse();
        response.setHeader("Content-Type","application/json;charset=UTF-8");
        InputStream  dataInput  = ctx.getResponseDataStream();

        HttpServletRequest request = ctx.getRequest();
        String url = request.getRequestURI();

        logger.info("run-url->"+url);

        if("Page".equals(url.substring(url.length()-4))){
            return  null;
        }


        // 不需要任何 数据封装的地址
        if(response.getStatus() != 200){
            response.setStatus(200);
            // 异常
            if(dataInput != null){
                String data = null;
                String error  = "";
                try {
                    data = IOUtils.toString(dataInput,"UTF-8");
                } catch (IOException e) {
                    error = "服务器内部错误！";
                }

                JSONObject jb = JSON.parseObject(data);
                String exception =  jb.get("exception").toString();
                if("com.lorne.core.framework.exception.ServiceException".equals(exception)){
                    error = jb.getString("message");
                }else{
                    error = jb.getString("exception")+":"+jb.getString("message")+jb.getString("path") ;
                }

                res.setCode(40010);
                res.setMsg(error);
                msg.setState(1);
                msg.setRes(res);
                ctx.setResponseBody(msg.toJsonString());

            }else{
                msg.setRes(null);
                msg.setState(1);
            }
        }else{
            //正常
            if(dataInput != null){
                // json数据
                try {
                    String data =  IOUtils.toString(dataInput,"UTF-8");
                    res.setCode(40000);
                    res.setData(data);
                    msg.setState(1);
                    msg.setRes(res);
                    ctx.setResponseBody(msg.toJsonString());
                } catch (IOException e) {
                    res.setMsg("数据转换错误！");
                    res.setCode(40010);
                    res.setData(null);
                    msg.setState(1);
                    msg.setRes(res);
                    ctx.setResponseBody(msg.toJsonString());
                }
            }

        }
        return null;
    }

}


