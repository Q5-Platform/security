package com.codingapi.security.service;



import com.codingapi.model.VerificationResult;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by houcunlu on 2017/8/15.
 */
public interface CheckService {


    VerificationResult execute(HttpServletRequest request, String url);


}
