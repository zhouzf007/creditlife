package com.entrobus.credit.log.service;

import com.entrobus.credit.pojo.log.OperationLogTable;
import com.entrobus.credit.pojo.log.OperationLogTableExample;

import java.util.List;

public interface OperationLogTableService {
    /** 
	 * 根据条件统计总数。
	 * @mbggenerated 
	 */
    int countByExample(OperationLogTableExample example);

    /** 
	 * 根据主键查找。
	 * @mbggenerated 
	 */
    OperationLogTable selectByPrimaryKey(String id);

    /** 
	 * 根据条件查找。
	 * @mbggenerated 
	 */
    List<OperationLogTable> selectByExample(OperationLogTableExample example);

    /** 
	 * 根据主键删除。
	 * @mbggenerated 
	 */
    int deleteByPrimaryKey(String id);

    /** 
	 * 根据主键动态更新，只更新填充字段。
	 * @mbggenerated 
	 */
    int updateByPrimaryKeySelective(OperationLogTable record);

    /** 
	 * 根据主键更新，对主键外的所有字段更新(不包括BLOB类型字段)，不传的字段自动填充null。
	 * 如果要更新BLOB类型字段，使用updateByPrimaryKeyWithBLOBs方法
	 * @mbggenerated 
	 */
    int updateByPrimaryKey(OperationLogTable record);

    /** 
	 * 根据条件删除。
	 * @mbggenerated 
	 */
    int deleteByExample(OperationLogTableExample example);

    /** 
	 * 根据条件更新，对所有 传入的 字段更新(包括主键和BLOB类型字段)，不传的字段不修改。
	 * @mbggenerated 
	 */
    int updateByExampleSelective(OperationLogTable record, OperationLogTableExample example);

    /** 
	 * 根据条件更新，对所有字段更新(BLOB类型字段除外)，不传的字段自动填充null(BLOB类型字段除外)。
	 * @mbggenerated 
	 */
    int updateByExample(OperationLogTable record, OperationLogTableExample example);

    /** 
	 * 插入所有字段，不传的字段填充null。
	 * @mbggenerated 
	 */
    int insert(OperationLogTable record);

    /** 
	 * 插入所有传入的字段，不传的字段不插入（数据库自动填充默认值）。
	 * @mbggenerated 
	 */
    int insertSelective(OperationLogTable record);
}