package com.entrobus.credit.vo.log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OperationLogMsg implements Serializable {
    /**
     * 关联id,如orderId
     */
    private String relId;

    /**
     * 操作说明：自定义,如 提交申请（创建订单）、审核 等
     */
    private String desc;
    /**
     * 操作时间
     */
    private Date time;
    /**
     * 操作人id
     */
    private String operatorId;
    /**
     * 备注（1024）：自定义，如：超时、定时操作等
     */
    private String remark;
    /**
     * 操作状态：0-成功，1-失败，2-异常
     */
    private Integer operationState;

    /**
     * 请求id,保留字段
     */
    private String requestId;
    /**
     * 应用名称
     */
    private String applicationName;
    /**
     * 操作人类型：0：信用贷后台管理员，1：资金方后台管理员，2-用户
     */
    private Integer operatorType;
    /**
     * 请求参数
     */
    private Object operationData;
    /**
     * 扩展字段
     */
    private Object extData;
    /**
     * 相关被操作表信息
     */
    public List<Table> tables;

    /**
     * 添加新的被操作表
     * @return
     */
    public Table newTables(String name,String database){
        if (tables == null ) {
            tables = new ArrayList<>();
        }
        Table table = new Table(name,database);
        tables.add(table);
        return table;
    }

    /**
     * 添加新的被操作表
     * @return
     */
    public Table newTables(String name){
        return newTables(name,null);
    }
    public List<Table> getTables() {
        return tables;
    }

    public void setTables(List<Table> tables) {
        this.tables = tables;
    }

    public String getRelId() {
        return relId;
    }

    public void setRelId(String relId) {
        this.relId = relId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getOperationState() {
        return operationState;
    }

    public void setOperationState(Integer operationState) {
        this.operationState = operationState;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public Integer getOperatorType() {
        return operatorType;
    }

    public void setOperatorType(Integer operatorType) {
        this.operatorType = operatorType;
    }

    public Object getOperationData() {
        return operationData;
    }

    public void setOperationData(Object operationData) {
        this.operationData = operationData;
    }

    public Object getExtData() {
        return extData;
    }

    public void setExtData(Object extData) {
        this.extData = extData;
    }

    public static class Table {
        /**
         * 表名
         */
        private String name;
        /**
         * 库名
         */
        private String database;
        /**
         * 字段
         */
        private List<Colume> columes;

        public Table() {
        }

        public Table(String name, String database) {
            this.name = name;
            this.database = database;
        }

        /**
         * 添加相关字段
         * @param name 字段名
         * @param newValue 新值
         * @param oldValue 旧值
         * @return
         */
        public Table putColume(String name, String newValue, String oldValue){
            if (columes == null) {
                columes = new ArrayList<>();
            }
            columes.add(new Colume(name,newValue,oldValue));
            return this;
        }
        public List<Colume> getColumes() {
            return columes;
        }

        public void setColumes(List<Colume> columes) {
            this.columes = columes;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDatabase() {
            return database;
        }

        public void setDatabase(String database) {
            this.database = database;
        }
    }

    public static class Colume{
        /**
         * 字段名
         */
        private String name;
        /**
         * 旧值
         *
         */
        private String oldValue;
        /**
         * 新值
         */
        private String newValue;

        public Colume() {
        }

        public Colume(String name, String newValue, String oldValue) {
            this.name = name;
            this.oldValue = oldValue;
            this.newValue = newValue;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getOldValue() {
            return oldValue;
        }

        public void setOldValue(String oldValue) {
            this.oldValue = oldValue;
        }

        public String getNewValue() {
            return newValue;
        }

        public void setNewValue(String newValue) {
            this.newValue = newValue;
        }
    }
}
