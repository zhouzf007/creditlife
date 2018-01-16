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
        String ID_OBJ = "bs_statics_id:obj:";//根据id 获取缓存对象
        String TYPE_VALUE_ID = "bs_statics:type:value:id:";//根据codeType和codeValue 获取缓存id
        String TYPE_LIST = "bs_statics_type:list:";//根据codeType缓存list
    }

    interface Sms {
        String VERIFICATION_CODE = "verification_code:mobile:";
    }

    interface Translation {
        String ORDER_STATE = "order_state:state:";
        String REPAYMENT_STATE = "repayment_state:state:";
    }


}
