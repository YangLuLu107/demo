package com.example.mapper;

import com.example.pojo.IndexCode;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface IndexCodeMapper {

    List<IndexCode> getList();

    List<String> getBigclassNameList();
    List<String> getMidclassNameList();
    List<String> getSubclassNameList();

    int insert(IndexCode record);

    int insertSelective(IndexCode record);

    int delete();
}