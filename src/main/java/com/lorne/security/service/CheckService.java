package com.lorne.security.service;



import com.lorne.model.VerificationResult;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by houcunlu on 2017/8/15.
 */
public interface CheckService {


    VerificationResult execute(HttpServletRequest request, String url);


}
