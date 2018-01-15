package com.entrobus.credit.payment.dao;

import com.entrobus.credit.pojo.payment.RepaymentPlanInstance;
import com.entrobus.credit.pojo.payment.RepaymentPlanInstanceExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RepaymentPlanInstanceMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table repayment_plan_instance
     *
     * @mbggenerated
     */
    int countByExample(RepaymentPlanInstanceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table repayment_plan_instance
     *
     * @mbggenerated
     */
    int deleteByExample(RepaymentPlanInstanceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table repayment_plan_instance
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table repayment_plan_instance
     *
     * @mbggenerated
     */
    int insert(RepaymentPlanInstance record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table repayment_plan_instance
     *
     * @mbggenerated
     */
    int insertSelective(RepaymentPlanInstance record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table repayment_plan_instance
     *
     * @mbggenerated
     */
    List<RepaymentPlanInstance> selectByExample(RepaymentPlanInstanceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table repayment_plan_instance
     *
     * @mbggenerated
     */
    RepaymentPlanInstance selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table repayment_plan_instance
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") RepaymentPlanInstance record, @Param("example") RepaymentPlanInstanceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table repayment_plan_instance
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") RepaymentPlanInstance record, @Param("example") RepaymentPlanInstanceExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table repayment_plan_instance
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(RepaymentPlanInstance record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table repayment_plan_instance
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(RepaymentPlanInstance record);

    int insertBatchSelective(List<RepaymentPlanInstance> records);

    int updateBatchByPrimaryKeySelective(List<RepaymentPlanInstance> records);
}