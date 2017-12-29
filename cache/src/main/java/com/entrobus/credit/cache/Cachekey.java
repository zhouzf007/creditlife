package com.entrobus.credit.cache;

/**
 * Created by zhouzf on 2017/12/28.
 */
public interface Cachekey {

    interface User {
        String SID_PREFIX = "userInfo:uid:";
        String UID_PREFIX = "userInfo:uid:";
        String UID_SID_PREFIX = "userInfo:uid:sid:";
    }

}
