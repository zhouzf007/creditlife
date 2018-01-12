package com.entrobus.credit.common;

public interface Constants {
    /**
     * 数据库删除标记 0-未删除，1-已删除
     */
    interface DeleteFlag {
        int YES = 1;
        int NO = 0;
    }

    /**
     * 各种通用的状态
     * 0-正常（生效，可用），1-停用
     */
    interface Status{
        int NORMAL = 0;
        int DISABLE = 1;
    }

    /**
     * 调度任务分组
     */
    interface JobGroupName{
        String DEFAULT = "creditlife_default";//默认分组
    }

    /**
     * 操作状态
     */
    interface  OperationState{
        int SUCCESS = 0;//成功
        int FAIL = 1;//失败
        int ERROR = 2;//异常
    }

    /**
     * 静态数据常量类型
     */
    interface CodeType{
        String CODE_TYPE = "CODE_TYPE";
        String STATUS = "STATUS";
    }

    interface JobDataKey{
        String CUSTOM = "customParam";
    }

    interface SMS_TYPE{
        int VERIFICATION = 0;
        int INFORMATION = 1;
    }

    /**
     * 管理系统所属平台类型
     */
    interface PLATFORM{
        int CREDITLIFE = 0;//熵商后台
        int BANK = 1;//银行后台
    }

    interface ORGANIZATION_STATE{
        int NORMAL = 0;
        int FROZEN = 1;
    }

    interface ORDER_STATE{
        int AUIDT_PENGDING = 0;//待审核
        int LOAN_PENGDING = 1;//待放款
        int REJECTION = 2;//已驳回
        int PASS = 3;//使用中
        int OVERDUE = 4;//已逾期
        int FINISHED = 5;//已完成
    }

    interface REPAYMENT_ORDER_STATE{
        int PASS = 3;//使用中
        int OVERDUE = 4;//已逾期
        int FINISHED = 5;//已结清
    }
}
