package com.codingapi.security.core;

/**
 * Created by houcunlu on 2017/11/9.
 */
public interface ContentController {



       String  WEB = "/security/{db_security_}";

       /**
        *重新加载Db链接
        */
       String DBREFRESH = "/dbRefresh";


       String ADMIN = WEB+"/admin";


       String RESOURCE = WEB+"/resource";


       String ROLE =  WEB+"/role";


       String ROLERESOURCE =  WEB+"/roleResource";


       String USERROLE =  WEB+"/userRole";


}
