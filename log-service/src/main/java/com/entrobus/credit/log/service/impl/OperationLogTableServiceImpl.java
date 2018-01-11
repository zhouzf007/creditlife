package com.entrobus.credit.log.service.impl;

import com.entrobus.credit.common.util.GUIDUtil;
import com.entrobus.credit.log.dao.OperationLogTableMapper;
import com.entrobus.credit.log.service.OperationLogTableService;
import com.entrobus.credit.pojo.log.OperationLogTable;
import com.entrobus.credit.pojo.log.OperationLogTableExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperationLogTableServiceImpl implements OperationLogTableService {
    @Autowired
    private OperationLogTableMapper operationLogTableMapper;

    /** 
	 * 根据条件统计总数。
	 * @mbggenerated 
	 */
    @Override
    public int countByExample(OperationLogTableExample example) {
        int count = this.operationLogTableMapper.countByExample(example);
        return count;
    }

    /** 
	 * 根据主键查找。
	 * @mbggenerated 
	 */
    @Override
    public OperationLogTable selectByPrimaryKey(String id) {
        return this.operationLogTableMapper.selectByPrimaryKey(id);
    }

    /** 
	 * 根据条件查找。
	 * @mbggenerated 
	 */
    @Override
    public List<OperationLogTable> selectByExample(OperationLogTableExample example) {
        return this.operationLogTableMapper.selectByExample(example);
    }

    /** 
	 * 根据主键删除。
	 * @mbggenerated 
	 */
    @Override
    public int deleteByPrimaryKey(String id) {
        return this.operationLogTableMapper.deleteByPrimaryKey(id);
    }

    /** 
	 * 根据主键动态更新，只更新填充字段。
	 * @mbggenerated 
	 */
    @Override
    public int updateByPrimaryKeySelective(OperationLogTable record) {
		return this.operationLogTableMapper.updateByPrimaryKeySelective(record);
    }

    /** 
	 * 根据主键更新，对主键外的所有字段更新(不包括BLOB类型字段)，不传的字段自动填充null。
	 * 如果要更新BLOB类型字段，使用updateByPrimaryKeyWithBLOBs方法
	 * @mbggenerated 
	 */
    @Override
    public int updateByPrimaryKey(OperationLogTable record) {
		return this.operationLogTableMapper.updateByPrimaryKey(record);
    }

    /** 
	 * 根据条件删除。
	 * @mbggenerated 
	 */
    @Override
    public int deleteByExample(OperationLogTableExample example) {
        return this.operationLogTableMapper.deleteByExample(example);
    }

    /** 
	 * 根据条件更新，对所有 传入的 字段更新(包括主键和BLOB类型字段)，不传的字段不修改。
	 * @mbggenerated 
	 */
    @Override
    public int updateByExampleSelective(OperationLogTable record, OperationLogTableExample example) {
		return this.operationLogTableMapper.updateByExampleSelective(record, example);
    }

    /** 
	 * 根据条件更新，对所有字段更新(BLOB类型字段除外)，不传的字段自动填充null(BLOB类型字段除外)。
	 * @mbggenerated 
	 */
    @Override
    public int updateByExample(OperationLogTable record, OperationLogTableExample example) {
		return this.operationLogTableMapper.updateByExample(record, example);
    }

    /** 
	 * 插入所有字段，不传的字段填充null。
	 * @mbggenerated 
	 */
    @Override
    public int insert(OperationLogTable record) {
        this.defaultValue(record);
		return this.operationLogTableMapper.insert(record);
    }

    /** 
	 * 插入所有传入的字段，不传的字段不插入（数据库自动填充默认值）。
	 * @mbggenerated 
	 */
    @Override
    public int insertSelective(OperationLogTable record) {
        this.defaultValue(record);
		return this.operationLogTableMapper.insertSelective(record);
    }

    /** 
	 * 对pojo中没有赋值的字段进行赋予默认值，执行插入方法前运行。
	 * @mbggenerated 
	 */
    protected void defaultValue(OperationLogTable record) {
        // 填充字段默认值
        if (record.getId() == null){
            record.setId(GUIDUtil.genRandomGUID());
        }
    }


}