package com.entrobus.credit.log.service.impl;

import com.entrobus.credit.log.dao.OperationLogTableColumnMapper;
import com.entrobus.credit.log.service.OperationLogTableColumnService;
import com.entrobus.credit.pojo.log.OperationLogTableColumn;
import com.entrobus.credit.pojo.log.OperationLogTableColumnExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperationLogTableColumnServiceImpl implements OperationLogTableColumnService {
    @Autowired
    private OperationLogTableColumnMapper operationLogTableColumnMapper;

    /** 
	 * 根据条件统计总数。
	 * @mbggenerated 
	 */
    @Override
    public int countByExample(OperationLogTableColumnExample example) {
        int count = this.operationLogTableColumnMapper.countByExample(example);
        return count;
    }

    /** 
	 * 根据主键查找。
	 * @mbggenerated 
	 */
    @Override
    public OperationLogTableColumn selectByPrimaryKey(String id) {
        return this.operationLogTableColumnMapper.selectByPrimaryKey(id);
    }

    /** 
	 * 根据条件查找。
	 * @mbggenerated 
	 */
    @Override
    public List<OperationLogTableColumn> selectByExample(OperationLogTableColumnExample example) {
        return this.operationLogTableColumnMapper.selectByExample(example);
    }

    /** 
	 * 根据主键删除。
	 * @mbggenerated 
	 */
    @Override
    public int deleteByPrimaryKey(String id) {
        return this.operationLogTableColumnMapper.deleteByPrimaryKey(id);
    }

    /** 
	 * 根据主键动态更新，只更新填充字段。
	 * @mbggenerated 
	 */
    @Override
    public int updateByPrimaryKeySelective(OperationLogTableColumn record) {
        this.updateValue(record);
		return this.operationLogTableColumnMapper.updateByPrimaryKeySelective(record);
    }

    /** 
	 * 根据主键更新，对主键外的所有字段更新(不包括BLOB类型字段)，不传的字段自动填充null。
	 * 如果要更新BLOB类型字段，使用updateByPrimaryKeyWithBLOBs方法
	 * @mbggenerated 
	 */
    @Override
    public int updateByPrimaryKey(OperationLogTableColumn record) {
        this.updateValue(record);
		return this.operationLogTableColumnMapper.updateByPrimaryKey(record);
    }

    /** 
	 * 根据条件删除。
	 * @mbggenerated 
	 */
    @Override
    public int deleteByExample(OperationLogTableColumnExample example) {
        return this.operationLogTableColumnMapper.deleteByExample(example);
    }

    /** 
	 * 根据条件更新，对所有 传入的 字段更新(包括主键和BLOB类型字段)，不传的字段不修改。
	 * @mbggenerated 
	 */
    @Override
    public int updateByExampleSelective(OperationLogTableColumn record, OperationLogTableColumnExample example) {
        this.updateValue(record);
		return this.operationLogTableColumnMapper.updateByExampleSelective(record, example);
    }

    /** 
	 * 根据条件更新，对所有字段更新(BLOB类型字段除外)，不传的字段自动填充null(BLOB类型字段除外)。
	 * @mbggenerated 
	 */
    @Override
    public int updateByExample(OperationLogTableColumn record, OperationLogTableColumnExample example) {
        this.updateValue(record);
		return this.operationLogTableColumnMapper.updateByExample(record, example);
    }

    /** 
	 * 插入所有字段，不传的字段填充null。
	 * @mbggenerated 
	 */
    @Override
    public int insert(OperationLogTableColumn record) {
        this.defaultValue(record);
		return this.operationLogTableColumnMapper.insert(record);
    }

    /** 
	 * 插入所有传入的字段，不传的字段不插入（数据库自动填充默认值）。
	 * @mbggenerated 
	 */
    @Override
    public int insertSelective(OperationLogTableColumn record) {
        this.defaultValue(record);
		return this.operationLogTableColumnMapper.insertSelective(record);
    }

    /** 
	 * 对pojo中没有赋值的字段进行赋予默认值，执行插入方法前运行。
	 * @mbggenerated 
	 */
    protected boolean defaultValue(OperationLogTableColumn record) {
        // 填充字段默认值
		return false;
    }
}