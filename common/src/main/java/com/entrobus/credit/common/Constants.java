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
}
