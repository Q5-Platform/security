package com.lorne.security;

/**
 * Created by houcunlu on 2017/11/9.
 */
public interface ContentController {


       /**
        *重新加载Db链接
        */
       String DBREFRESH = "/dbRefresh";




       String  WEB = "/{db_security_}";


       String ADMIN = WEB+"/admin";


       String RESOURCE = WEB+"/resource";


       String ROLE =  WEB+"/role";


       String ROLERESOURCE =  WEB+"/roleResource";


       String USERROLE =  WEB+"/userRole";


}
