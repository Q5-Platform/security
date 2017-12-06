package com.codingapi.security.service;



import com.codingapi.security.model.VerificationResult;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by houcunlu on 2017/8/15.
 */
public interface SCheckService {


    VerificationResult execute(HttpServletRequest request, String url);


}
