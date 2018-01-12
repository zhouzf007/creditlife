package com.entrobus.credit.payment.dao;

import com.entrobus.credit.pojo.payment.RepaymentPlan;
import com.entrobus.credit.pojo.payment.RepaymentPlanExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RepaymentPlanMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table repayment_plan
     *
     * @mbggenerated
     */
    int countByExample(RepaymentPlanExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table repayment_plan
     *
     * @mbggenerated
     */
    int deleteByExample(RepaymentPlanExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table repayment_plan
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table repayment_plan
     *
     * @mbggenerated
     */
    int insert(RepaymentPlan record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table repayment_plan
     *
     * @mbggenerated
     */
    int insertSelective(RepaymentPlan record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table repayment_plan
     *
     * @mbggenerated
     */
    List<RepaymentPlan> selectByExample(RepaymentPlanExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table repayment_plan
     *
     * @mbggenerated
     */
    RepaymentPlan selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table repayment_plan
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") RepaymentPlan record, @Param("example") RepaymentPlanExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table repayment_plan
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") RepaymentPlan record, @Param("example") RepaymentPlanExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table repayment_plan
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(RepaymentPlan record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table repayment_plan
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(RepaymentPlan record);

    int insertBatchSelective(List<RepaymentPlan> records);

    int updateBatchByPrimaryKeySelective(List<RepaymentPlan> records);
}