package com.entrobus.credit.manager.common;

/**
 * 业务类常量
 */
public interface SysConstants {

    /** 系统管理员角色 */
    interface LOGIN_USER_ROLE{
        Long SUPER_ADMIN=1L; //超级管理员
    }

    /**
     * 登录用户信息
     */
    interface LOGIN_USER{
        String INFO="loginUser";
    }

    /**
     * 所属平台
     */
    interface PLATFORM{
        /**
         * 0：信用贷后台
         */
        Integer CREDITLIFE = 0;
        /**
         * 1：银行后台
         */
        Integer BANK = 1;
    }

    /**
     * 资源类型
     * 1：菜单   2：按钮
     */
    interface RESOURCE_TYPE{
        /**
         * 菜单
         */
        Integer MENU = 1;
        /**
         * 案例
         */
        Integer BUTTON = 2;
    }

    /**
     * 用户状态
     * 0：正常   1：禁用
     */
    interface USER_STATUS {
        int NORMAL = 0;//正常
        int FROZEN = 1;//禁用
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

    interface Error {

        String ERROR_CODE = "errorCode";

        String ERROR_MSG = "errorMsg";

        String ADD_SUCCESS = "添加成功";

        String ADD_FAILED = "添加失败";

        String SAVE_SUCCESS = "保存成功";

        String SAVE_FAILED = "保存失败";

        String DELETE_SUCCESS = "删除成功";

        String DELETE_FAILED = "删除失败";

        String SYS_ERROR_CODE = "-1";

        String SYS_ERROR = "系统异常";

        String DATA = "data";
    }

    /**
     * 操作日志状态
     */
    interface SYSLOG_STATE {
        int SUCCESS = 0;//操作成功
        int FAIL = 1;//操作失败
        int ERROR = 2;//异常
    }

    /**
     * 登录状态
     */
    interface lOGIN_STATE {
        String LOGIN = "0";//登录
        String LOGOUT = "1";//已退出登录
    }

    /**
     * 各种状态  0=（否，不） 1=（是，有）
     */
    interface DO_STATUS{
        int NO = 0;
        int YES = 1;
    }
}
