package com.entrobus.credit.log.service;

import com.entrobus.credit.pojo.log.OperationLogTableColumn;
import com.entrobus.credit.pojo.log.OperationLogTableColumnExample;

import java.util.List;

public interface OperationLogTableColumnService {
    /** 
	 * 根据条件统计总数。
	 * @mbggenerated 
	 */
    int countByExample(OperationLogTableColumnExample example);

    /** 
	 * 根据主键查找。
	 * @mbggenerated 
	 */
    OperationLogTableColumn selectByPrimaryKey(String id);

    /** 
	 * 根据条件查找。
	 * @mbggenerated 
	 */
    List<OperationLogTableColumn> selectByExample(OperationLogTableColumnExample example);

    /** 
	 * 根据主键删除。
	 * @mbggenerated 
	 */
    int deleteByPrimaryKey(String id);

    /** 
	 * 根据主键动态更新，只更新填充字段。
	 * @mbggenerated 
	 */
    int updateByPrimaryKeySelective(OperationLogTableColumn record);

    /** 
	 * 根据主键更新，对主键外的所有字段更新(不包括BLOB类型字段)，不传的字段自动填充null。
	 * 如果要更新BLOB类型字段，使用updateByPrimaryKeyWithBLOBs方法
	 * @mbggenerated 
	 */
    int updateByPrimaryKey(OperationLogTableColumn record);

    /** 
	 * 根据条件删除。
	 * @mbggenerated 
	 */
    int deleteByExample(OperationLogTableColumnExample example);

    /** 
	 * 根据条件更新，对所有 传入的 字段更新(包括主键和BLOB类型字段)，不传的字段不修改。
	 * @mbggenerated 
	 */
    int updateByExampleSelective(OperationLogTableColumn record, OperationLogTableColumnExample example);

    /** 
	 * 根据条件更新，对所有字段更新(BLOB类型字段除外)，不传的字段自动填充null(BLOB类型字段除外)。
	 * @mbggenerated 
	 */
    int updateByExample(OperationLogTableColumn record, OperationLogTableColumnExample example);

    /** 
	 * 插入所有字段，不传的字段填充null。
	 * @mbggenerated 
	 */
    int insert(OperationLogTableColumn record);

    /** 
	 * 插入所有传入的字段，不传的字段不插入（数据库自动填充默认值）。
	 * @mbggenerated 
	 */
    int insertSelective(OperationLogTableColumn record);
}