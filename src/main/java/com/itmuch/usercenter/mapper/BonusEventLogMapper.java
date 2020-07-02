package com.itmuch.usercenter.mapper;

import com.itmuch.usercenter.model.BonusEventLog;
import com.itmuch.usercenter.model.BonusEventLogExample;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BonusEventLogMapper {
    long countByExample(BonusEventLogExample example);

    int deleteByExample(BonusEventLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BonusEventLog record);

    int insertSelective(BonusEventLog record);

    List<BonusEventLog> selectByExample(BonusEventLogExample example);

    BonusEventLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BonusEventLog record, @Param("example") BonusEventLogExample example);

    int updateByExample(@Param("record") BonusEventLog record, @Param("example") BonusEventLogExample example);

    int updateByPrimaryKeySelective(BonusEventLog record);

    int updateByPrimaryKey(BonusEventLog record);
}