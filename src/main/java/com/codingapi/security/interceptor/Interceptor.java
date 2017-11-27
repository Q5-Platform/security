package com.codingapi.security.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.codingapi.security.model.TokenUser;
import com.codingapi.security.redis.RedisHelper;
import com.codingapi.security.utils.SecurityConfig;
import com.codingapi.security.utils.SecurityConfigUtils;
import com.lorne.core.framework.Code;
import com.lorne.core.framework.model.Msg;
import com.lorne.core.framework.model.Response;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by houcunlu on 2017/8/16.
 */
@Component
public class Interceptor implements  HandlerInterceptor {


    @Autowired
    private RedisHelper rh;

    @Value("${server.contextPath}")
    private String path;



    private void outJson(HttpServletResponse response, String msg, int code) throws Exception {
        Response res = new Response();
        res.setCode(code);
        res.setMsg(msg);
        Msg _msg = new Msg(1, res);
        String json = JSONObject.toJSONString(_msg);
        response.setContentType("text/html;charset=utf-8");
        response.setHeader("Content-type", "application/json;charset=utf-8");
        response.getWriter().print(json);
        response.getWriter().close();
    }



    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception {
        String  url = request.getRequestURI();

        String dbName = SecurityConfigUtils.getInstance().getDbName(url);
        if(StringUtils.isNotEmpty(dbName)){
            DbNameLocal dbNameLocal = new DbNameLocal();
            dbNameLocal.setKey(dbName);
            DbNameLocal.setCurrent(dbNameLocal);
        }

        SecurityConfig securityConfig =  SecurityConfigUtils.getInstance().getSecurityConfig(dbName);

        String token = ServletRequestUtils.getStringParameter(request, "token", "");
        // urls 接口
        List<String> noTokenUrls = securityConfig.getAlowUrls();
        for(String action:noTokenUrls){
            if(StringUtils.isNotEmpty(action)){
                String sUrl = url.substring(url.length() - action.length()  , url.length());
                if(action.equals(sUrl)){
                    return  true;
                }
            }
        }


        // token 验证
        if(StringUtils.isEmpty(token)){
            outJson(response, "token为空！", Code.code_error);
            return  false;
        }

        String userInfo = rh.getToken(token);
        if(StringUtils.isEmpty(userInfo)){
            outJson(response, "token已失效!", Code.code_error);
            return  false;
        }

        TokenUser tokenUser = JSONObject.parseObject(userInfo , TokenUser.class );
        List<Map<String,Object>> list =  tokenUser.getResource();
        if(list == null){
            outJson(response, "资源获取失败！", Code.code_error);
            return  false;
        }

        String currentUrl = getCurrentUrl(url);

        for(Map<String,Object> sr : list){
           String cUrl =  sr.get("url").toString();
            if(currentUrl.equals(cUrl)){
                return  true;
            }

        }

        outJson(response, "你没有权限获取该资源！", Code.code_error);
        return false;// 只有返回true才会继续向下执行，返回false取消当前请求
    }


    private String getCurrentUrl(String url){
        url = url.replace(path,"");

        url =  url.substring(url.indexOf("_/")+1,url.length());

        return url;
    }



    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {


    }


    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {


    }





}
