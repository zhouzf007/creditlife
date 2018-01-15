package com.entrobus.credit.log.service;

import com.entrobus.credit.pojo.log.OperationLog;
import com.entrobus.credit.pojo.log.OperationLogExample;
import com.entrobus.credit.vo.log.OperationLogMsg;

import java.util.List;

public interface OperationLogService {
    /** 
	 * 根据条件统计总数。
	 * @mbggenerated 
	 */
    int countByExample(OperationLogExample example);

    /** 
	 * 根据主键查找。
	 * @mbggenerated 
	 */
    OperationLog selectByPrimaryKey(String id);

    /** 
	 * 根据条件查找。
	 * @mbggenerated 
	 */
    List<OperationLog> selectByExample(OperationLogExample example);

    /** 
	 * 根据主键删除。
	 * @mbggenerated 
	 */
    int deleteByPrimaryKey(String id);

    /** 
	 * 根据主键动态更新，只更新填充字段。
	 * @mbggenerated 
	 */
    int updateByPrimaryKeySelective(OperationLog record);

    /** 
	 * 根据主键更新，对主键外的所有字段更新(不包括BLOB类型字段)，不传的字段自动填充null。
	 * 如果要更新BLOB类型字段，使用updateByPrimaryKeyWithBLOBs方法
	 * @mbggenerated 
	 */
    int updateByPrimaryKey(OperationLog record);

    /** 
	 * 根据主键更新，对主键外的所有字段更新(包括BLOB类型字段)，不传的字段自动填充null。
	 * @mbggenerated 
	 */
    int updateByPrimaryKeyWithBLOBs(OperationLog record);

    /** 
	 * 根据条件删除。
	 * @mbggenerated 
	 */
    int deleteByExample(OperationLogExample example);

    /** 
	 * 根据条件更新，对所有 传入的 字段更新(包括主键和BLOB类型字段)，不传的字段不修改。
	 * @mbggenerated 
	 */
    int updateByExampleSelective(OperationLog record, OperationLogExample example);

    /** 
	 * 根据条件更新，对所有字段更新(BLOB类型字段除外)，不传的字段自动填充null(BLOB类型字段除外)。
	 * @mbggenerated 
	 */
    int updateByExample(OperationLog record, OperationLogExample example);

    /** 
	 * 根据条件更新，对所有字段更新(包括主键和BLOB类型字段)，不传的字段自动填充null。
	 * @mbggenerated 
	 */
    int updateByExampleWithBLOBs(OperationLog record, OperationLogExample example);

    /** 
	 * 插入所有字段，不传的字段填充null。
	 * @mbggenerated 
	 */
    int insert(OperationLog record);

    /** 
	 * 插入所有传入的字段，不传的字段不插入（数据库自动填充默认值）。
	 * @mbggenerated 
	 */
    int insertSelective(OperationLog record);

    int logMsg(OperationLogMsg msg);
}