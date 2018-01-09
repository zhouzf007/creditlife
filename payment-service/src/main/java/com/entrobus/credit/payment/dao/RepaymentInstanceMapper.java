package com.entrobus.credit.payment.dao;

import com.entrobus.credit.pojo.payment.RepaymentInstance;
import com.entrobus.credit.pojo.payment.RepaymentInstanceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RepaymentInstanceMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table repayment_instance
     *
     * @mbggenerated
     */
    int countByExample(RepaymentInstanceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table repayment_instance
     *
     * @mbggenerated
     */
    int deleteByExample(RepaymentInstanceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table repayment_instance
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table repayment_instance
     *
     * @mbggenerated
     */
    int insert(RepaymentInstance record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table repayment_instance
     *
     * @mbggenerated
     */
    int insertSelective(RepaymentInstance record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table repayment_instance
     *
     * @mbggenerated
     */
    List<RepaymentInstance> selectByExample(RepaymentInstanceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table repayment_instance
     *
     * @mbggenerated
     */
    RepaymentInstance selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table repayment_instance
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") RepaymentInstance record, @Param("example") RepaymentInstanceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table repayment_instance
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") RepaymentInstance record, @Param("example") RepaymentInstanceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table repayment_instance
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(RepaymentInstance record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table repayment_instance
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(RepaymentInstance record);

    int insertBatchSelective(List<RepaymentInstance> records);

    int updateBatchByPrimaryKeySelective(List<RepaymentInstance> records);
}