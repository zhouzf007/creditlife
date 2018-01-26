package com.entrobus.credit.cache;

/**
 * Created by zhouzf on 2017/12/28.
 */
public interface Cachekey {

    interface User {
        String SID_PREFIX = "userInfo:sid:";//根据sid 获取 uid
        String UID_PREFIX = "userInfo:uid:";//根据uid 获取 userInfo
        String UID_SID_PREFIX = "userInfo:uid:sid:"; // 根据 uid 获取 sid
    }

    interface BsStatics {
        String ID_OBJ = "cl:static:id:obj:";//根据id 获取缓存对象
        String TYPE_VALUE_ID = "cl:static:type:value:id:";//根据codeType和codeValue 缓存id
        String TYPE_VALUE_NAME = "cl:static:type:value:name:";//根据codeType和codeValue 缓存codeName
        String TYPE_LIST = "cl:static:list:";//根据codeType缓存list
    }

    interface Sms {
        String VERIFICATION_CODE = "verification_code:mobile:";
    }

    /**
     * 如果需要跟静态数据同步
     * BsStatics.TYPE_VALUE_NAME 加上 静态数据的codeType值
     */
    interface Translation {
        String ORDER_STATE = BsStatics.TYPE_VALUE_NAME  + "ORDER_STATE";
        String REPAYMENT_STATE = BsStatics.TYPE_VALUE_NAME  + "REPAYMENT_STATE";
        String ROLE_NAME = BsStatics.TYPE_VALUE_NAME + "USER_ROLE";
        String LOG_OPERATION_STATE =  BsStatics.TYPE_VALUE_NAME + "OPERATION_STATE";
        String REPAYMENT_TYPE =  BsStatics.TYPE_VALUE_NAME + "REPAYMENT_TYPE";

    }

    interface Order {
        String apply_no = "order:apply_no:";
    }

    String BSBANK_TOKEN = "bank:bsapi:login:token";
}
