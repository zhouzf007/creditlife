package com.entrobus.credit.log.service.impl;

import com.entrobus.credit.common.util.GUIDUtil;
import com.entrobus.credit.log.dao.OperationLogMapper;
import com.entrobus.credit.log.service.OperationLogService;
import com.entrobus.credit.pojo.log.OperationLog;
import com.entrobus.credit.pojo.log.OperationLogExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OperationLogServiceImpl implements OperationLogService {
    @Autowired
    private OperationLogMapper operationLogMapper;

    /** 
	 * 根据条件统计总数。
	 * @mbggenerated 
	 */
    @Override
    public int countByExample(OperationLogExample example) {
        int count = this.operationLogMapper.countByExample(example);
        return count;
    }

    /** 
	 * 根据主键查找。
	 * @mbggenerated 
	 */
    @Override
    public OperationLog selectByPrimaryKey(String id) {
        return this.operationLogMapper.selectByPrimaryKey(id);
    }

    /** 
	 * 根据条件查找。
	 * @mbggenerated 
	 */
    @Override
    public List<OperationLog> selectByExample(OperationLogExample example) {
        return this.operationLogMapper.selectByExample(example);
    }

    /** 
	 * 根据主键删除。
	 * @mbggenerated 
	 */
    @Override
    public int deleteByPrimaryKey(String id) {
        return this.operationLogMapper.deleteByPrimaryKey(id);
    }

    /** 
	 * 根据主键动态更新，只更新填充字段。
	 * @mbggenerated 
	 */
    @Override
    public int updateByPrimaryKeySelective(OperationLog record) {
		return this.operationLogMapper.updateByPrimaryKeySelective(record);
    }

    /** 
	 * 根据主键更新，对主键外的所有字段更新(不包括BLOB类型字段)，不传的字段自动填充null。
	 * 如果要更新BLOB类型字段，使用updateByPrimaryKeyWithBLOBs方法
	 * @mbggenerated 
	 */
    @Override
    public int updateByPrimaryKey(OperationLog record) {
		return this.operationLogMapper.updateByPrimaryKey(record);
    }

    /** 
	 * 根据主键更新，对主键外的所有字段更新(包括BLOB类型字段)，不传的字段自动填充null。
	 * @mbggenerated 
	 */
    @Override
    public int updateByPrimaryKeyWithBLOBs(OperationLog record) {
		return this.operationLogMapper.updateByPrimaryKeyWithBLOBs(record);
    }

    /** 
	 * 根据条件删除。
	 * @mbggenerated 
	 */
    @Override
    public int deleteByExample(OperationLogExample example) {
        return this.operationLogMapper.deleteByExample(example);
    }

    /** 
	 * 根据条件更新，对所有 传入的 字段更新(包括主键和BLOB类型字段)，不传的字段不修改。
	 * @mbggenerated 
	 */
    @Override
    public int updateByExampleSelective(OperationLog record, OperationLogExample example) {
		return this.operationLogMapper.updateByExampleSelective(record, example);
    }

    /** 
	 * 根据条件更新，对所有字段更新(BLOB类型字段除外)，不传的字段自动填充null(BLOB类型字段除外)。
	 * @mbggenerated 
	 */
    @Override
    public int updateByExample(OperationLog record, OperationLogExample example) {
		return this.operationLogMapper.updateByExample(record, example);
    }

    /** 
	 * 根据条件更新，对所有字段更新(包括主键和BLOB类型字段)，不传的字段自动填充null。
	 * @mbggenerated 
	 */
    @Override
    public int updateByExampleWithBLOBs(OperationLog record, OperationLogExample example) {
		return this.operationLogMapper.updateByExampleWithBLOBs(record, example);
    }

    /** 
	 * 插入所有字段，不传的字段填充null。
	 * @mbggenerated 
	 */
    @Override
    public int insert(OperationLog record) {
        this.defaultValue(record);
		return this.operationLogMapper.insert(record);
    }

    /** 
	 * 插入所有传入的字段，不传的字段不插入（数据库自动填充默认值）。
	 * @mbggenerated 
	 */
    @Override
    public int insertSelective(OperationLog record) {
        this.defaultValue(record);
		return this.operationLogMapper.insertSelective(record);
    }

    /** 
	 * 对pojo中没有赋值的字段进行赋予默认值，执行插入方法前运行。
	 * @mbggenerated 
	 */
    protected void defaultValue(OperationLog record) {
        // 填充字段默认值
        record.setId(GUIDUtil.genRandomGUID());
        record.setCreateTime(new Date());
    }
}