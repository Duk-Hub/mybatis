package com.orm.mybatis.mapper;

import com.orm.mybatis.entity.MybatisData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface MybatisDataMapper {

    int insert(MybatisData data);
    Optional<MybatisData> findById(@Param("id") Long id);
    List<MybatisData> findAll();
    int update(MybatisData data);
    int softDelete(@Param("id") Long id);
}
