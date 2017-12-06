package com.codingapi.security.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.codingapi.security.model.TokenUser;
import com.codingapi.security.model.VerificationResult;
import com.codingapi.security.redis.RedisHelper;
import com.codingapi.security.service.SCheckService;
import com.codingapi.security.utils.SecurityConfig;
import com.codingapi.security.utils.SecurityConfigUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.ServletRequestUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by houcunlu on 2017/8/15.
 */
@Service
public class SCheckServiceImpl implements SCheckService {


    @Value("${server.contextPath}")
    private String contextPath;

    @Autowired
    private RedisHelper rh;


    @Override
    public VerificationResult execute(HttpServletRequest request, String url) {

        VerificationResult verificationResult = new VerificationResult();

        String dbName = SecurityConfigUtils.getInstance().getDbName(url);

        //urls 接口

        SecurityConfig securityConfig =  SecurityConfigUtils.getInstance().getSecurityConfig(dbName);

        List<String> noTokenUrls = securityConfig.getAlowUrls();
        for(String action:noTokenUrls){
            if(StringUtils.isNotEmpty(action)) {
                if (url.equals(action)) {
                    return verificationResult;
                }
            }
        }

        String token = ServletRequestUtils.getStringParameter(request, "token", "");
        if(StringUtils.isEmpty(token)){
            verificationResult.setState(VerificationResult.STATE_ERROR);
            verificationResult.setMsg("token no exist!");
            return verificationResult;
        }

        // 获取Token
        String  checkToken  = rh.getToken(token);
        if(StringUtils.isEmpty(checkToken)){
            verificationResult.setState(VerificationResult.STATE_ERROR);
            verificationResult.setMsg("token 不存在！");
            return verificationResult;
        }


        TokenUser tokenUser = JSONObject.parseObject(checkToken , TokenUser.class );
        List<Map<String,Object>> list =  tokenUser.getResource();
        if(list == null){
            verificationResult.setState(VerificationResult.STATE_ERROR);
            verificationResult.setMsg("获取资源失败！");
            return verificationResult;
        }


        for(Map<String,Object> sr : list){
            if(url.equals(contextPath+sr.get("url"))){
                return verificationResult;
            }
        }

        verificationResult.setState(VerificationResult.STATE_ERROR);
        verificationResult.setMsg("你没有权限获取该资源！");
        return verificationResult;
    }





}
