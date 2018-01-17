package com.entrobus.credit.common;

public interface Constants {
    /**
     * 数据库删除标记 0-未删除，1-已删除
     */
    interface DELETE_FLAG {
        int YES = 1;
        int NO = 0;
    }


    interface ACCOUNT_STATUS {
        int NORMAL = 0;//正常
        int FROZEN = 1;//冻结
        int WAIT = 2;//待验证
    }

    /**
     * 各种通用的状态
     * 0-正常（生效，可用），1-停用
     */
    interface STATUS {
        int NORMAL = 0;
        int DISABLE = 1;
    }

    /**
     * 调度任务分组
     */
    interface JOB_GROUP_NAME {
        String DEFAULT = "creditlife_default";//默认分组
    }

    /**
     * 操作状态
     */
    interface OPERATION_STATE {
        int SUCCESS = 0;//成功
        int FAIL = 1;//失败
        int ERROR = 2;//异常
    }


    /**
     * 静态数据常量类型
     */
    interface CODE_TYPE {
        String CODE_TYPE = "CODE_TYPE";
        String STATUS = "STATUS";
    }

    interface JOB_DATA_KEY {
        String CUSTOM = "customParam";
    }

    interface SMS_TYPE {
        int VERIFICATION = 0;
        int INFORMATION = 1;
    }

    interface VERIFICATION_TYPE {
        int REGISTER = 1;//用户注册
        int RESET_PASSWORD = 2;//忘记密码
        int BINDING_CARD = 3;//绑定银行卡
    }

    /**
     * 平台类型
     */
    interface PLATFORM {
        int CREDITLIFE = 0;//熵商后台
        int BANK = 1;//资金方后台
        int USER = 2;//用户（贷款方）
    }
    /**
     * 客户端类型
     */
    interface CLIENT {
        int IOS = 1;// 苹果
        int ANDROID = 0;// 安卓
        int WECHAT = 2;// 微信公众号
        int MINA = 3;// 微信小程序
        int H5 = 4;//h5
    }

    interface ORGANIZATION_STATE {
        int NORMAL = 0;
        int FROZEN = 1;
    }

    interface ORDER_STATE {
        int NOT_LOAN = -1;//未借款
        int AUIDT_PENGDING = 0;//待审核
        int LOAN_PENGDING = 1;//待放款
        int REJECTION = 2;//已驳回
        int PASS = 3;//使用中
        int OVERDUE = 4;//已逾期
        int FINISHED = 5;//已完成
    }

    interface REPAYMENT_ORDER_STATE {
        int PASS = 3;//使用中
        int OVERDUE = 4;//已逾期
        int FINISHED = 5;//已结清
    }

    /**
     * 还款方式类型
     */
    interface REPAYMENT_TYPE{
        int INTEREST_CAPITAL = 0;//先息后本
        int MONTH_EQUAL = 1;//每月等额
    }

    /**
     * 用户状态
     * 0：正常   1：禁用
     */
    interface USER_STATUS {
        int NORMAL = 0;//正常
        int FROZEN = 1;//冻结
    }
    /**
     * 数据库性别字段
     */
    interface GENDER {
        /**
         * 男
         */
        int MALE = 1;
        /**
         * 女
         */
        int FEMALE = 2;

        /**
         * 保密
         */
        int SECRECY = 0;
    }
    /**
     * 登录状态
     */
    interface lOGIN_STATE {
        String LOGIN = "0";//登录
        String LOGOUT = "1";//已退出登录
    }

}
