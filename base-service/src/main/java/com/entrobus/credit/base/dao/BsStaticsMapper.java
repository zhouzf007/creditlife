package com.entrobus.credit.base.dao;

import com.entrobus.credit.pojo.base.BsStatics;
import com.entrobus.credit.pojo.base.BsStaticsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BsStaticsMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bs_statics
     *
     * @mbggenerated
     */
    int countByExample(BsStaticsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bs_statics
     *
     * @mbggenerated
     */
    int deleteByExample(BsStaticsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bs_statics
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bs_statics
     *
     * @mbggenerated
     */
    int insert(BsStatics record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bs_statics
     *
     * @mbggenerated
     */
    int insertSelective(BsStatics record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bs_statics
     *
     * @mbggenerated
     */
    List<BsStatics> selectByExample(BsStaticsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bs_statics
     *
     * @mbggenerated
     */
    BsStatics selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bs_statics
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") BsStatics record, @Param("example") BsStaticsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bs_statics
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") BsStatics record, @Param("example") BsStaticsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bs_statics
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(BsStatics record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table bs_statics
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(BsStatics record);

    int insertBatchSelective(List<BsStatics> records);

    int updateBatchByPrimaryKeySelective(List<BsStatics> records);
}