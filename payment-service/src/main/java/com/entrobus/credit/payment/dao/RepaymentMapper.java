package com.entrobus.credit.payment.dao;

import com.entrobus.credit.pojo.payment.Repayment;
import com.entrobus.credit.pojo.payment.RepaymentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RepaymentMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table repayment
     *
     * @mbggenerated
     */
    int countByExample(RepaymentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table repayment
     *
     * @mbggenerated
     */
    int deleteByExample(RepaymentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table repayment
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table repayment
     *
     * @mbggenerated
     */
    int insert(Repayment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table repayment
     *
     * @mbggenerated
     */
    int insertSelective(Repayment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table repayment
     *
     * @mbggenerated
     */
    List<Repayment> selectByExample(RepaymentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table repayment
     *
     * @mbggenerated
     */
    Repayment selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table repayment
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") Repayment record, @Param("example") RepaymentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table repayment
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") Repayment record, @Param("example") RepaymentExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table repayment
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Repayment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table repayment
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Repayment record);

    int insertBatchSelective(List<Repayment> records);

    int updateBatchByPrimaryKeySelective(List<Repayment> records);
}