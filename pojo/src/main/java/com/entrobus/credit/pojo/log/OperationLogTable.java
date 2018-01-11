package com.entrobus.credit.pojo.log;

import java.io.Serializable;

public class OperationLogTable implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column operation_log_table.id
     *
     * @mbggenerated
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column operation_log_table.log_id
     *
     * @mbggenerated
     */
    private String logId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column operation_log_table.table_name
     *
     * @mbggenerated
     */
    private String tableName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column operation_log_table.database_name
     *
     * @mbggenerated
     */
    private String databaseName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table operation_log_table
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column operation_log_table.id
     *
     * @return the value of operation_log_table.id
     *
     * @mbggenerated
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column operation_log_table.id
     *
     * @param id the value for operation_log_table.id
     *
     * @mbggenerated
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column operation_log_table.log_id
     *
     * @return the value of operation_log_table.log_id
     *
     * @mbggenerated
     */
    public String getLogId() {
        return logId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column operation_log_table.log_id
     *
     * @param logId the value for operation_log_table.log_id
     *
     * @mbggenerated
     */
    public void setLogId(String logId) {
        this.logId = logId == null ? null : logId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column operation_log_table.table_name
     *
     * @return the value of operation_log_table.table_name
     *
     * @mbggenerated
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column operation_log_table.table_name
     *
     * @param tableName the value for operation_log_table.table_name
     *
     * @mbggenerated
     */
    public void setTableName(String tableName) {
        this.tableName = tableName == null ? null : tableName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column operation_log_table.database_name
     *
     * @return the value of operation_log_table.database_name
     *
     * @mbggenerated
     */
    public String getDatabaseName() {
        return databaseName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column operation_log_table.database_name
     *
     * @param databaseName the value for operation_log_table.database_name
     *
     * @mbggenerated
     */
    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName == null ? null : databaseName.trim();
    }
}