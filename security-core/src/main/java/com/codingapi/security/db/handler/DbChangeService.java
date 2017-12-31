package com.codingapi.security.db.handler;

import com.lorne.core.framework.exception.ServiceException;

/**
 * create by lorne on 2017/12/7
 */
public interface DbChangeService {

    void changeDb(String url)throws ServiceException;
}
