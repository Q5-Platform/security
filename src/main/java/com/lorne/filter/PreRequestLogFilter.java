package com.lorne.filter;

import com.lorne.model.VerificationResult;
import com.lorne.security.service.CheckService;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by lorne on 2017/7/8.
 */
public class PreRequestLogFilter extends ZuulFilter {

    private final static String ERROR_MSG_FORMAT = "{\"state\":0,\"res\":null,\"msg\":\"%s\"}";

    @Autowired
    private CheckService checkService;


    private static final Logger logger = LoggerFactory.getLogger(PreRequestLogFilter.class);

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        String fullUrl = request.getRequestURL().toString();
        String url = request.getRequestURI();

        logger.info(String.format("send %s request to %s", request.getMethod(),fullUrl));


        logger.info(String.format("send %s request to %s", request.getMethod(),fullUrl));

        VerificationResult result =  checkService.execute(request,url);
        switch (result.getState()){
            case VerificationResult.STATE_OK:{
                ctx.setSendZuulResponse(true);
                ctx.setResponseStatusCode(200);
                return request;
            }
            case VerificationResult.STATE_ERROR:{
                ctx.setSendZuulResponse(false);
                ctx.setResponseStatusCode(200);
                ctx.setResponseBody(String.format(ERROR_MSG_FORMAT,result.getMsg()));
                return request;
            }
        }

        return result;
    }


}
