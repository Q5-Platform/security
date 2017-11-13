package com.codingapi.security.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.codingapi.model.TokenUser;
import com.codingapi.model.VerificationResult;
import com.codingapi.model.Config;
import com.codingapi.security.service.CheckService;
import com.codingapi.utils.RedisHelp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.ServletRequestUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by houcunlu on 2017/8/15.
 */
@Service
public class CheckServiceImpl implements CheckService {


    @Autowired
    private Environment env;


    @Autowired
    private RedisHelp rh;


    @Override
    public VerificationResult execute(HttpServletRequest request, String url) {

        VerificationResult verificationResult = new VerificationResult();

        //noToken 接口

        List<String> noTokenUrls = Config.noToken;
        for(int i = 0; i < noTokenUrls.size() ; i++ ){
            if(!noTokenUrls.get(i).equals("") && url.equals(noTokenUrls.get(i))){
                return verificationResult;
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
            if(url.equals(Config.contextPath+sr.get("url"))){
                return verificationResult;
            }
        }

        verificationResult.setState(VerificationResult.STATE_ERROR);
        verificationResult.setMsg("你没有权限获取该资源！");
        return verificationResult;
    }





}
