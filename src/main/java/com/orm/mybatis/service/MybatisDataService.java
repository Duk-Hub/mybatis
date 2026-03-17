package com.orm.mybatis.service;

import com.orm.mybatis.dto.MybatisRequest;
import com.orm.mybatis.dto.MybatisResponse;
import com.orm.mybatis.dto.MybatisUpdate;
import com.orm.mybatis.entity.MybatisData;
import com.orm.mybatis.mapper.MybatisDataMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MybatisDataService {

    private final MybatisDataMapper mybatisDataMapper;

    @Transactional
    public Long createMybatisData(MybatisRequest request) {
        MybatisData mybatisData = MybatisData.create(request.title(), request.content());
        int result = mybatisDataMapper.insert(mybatisData);
        if (result != 1){
            throw new RuntimeException("Insert Error");
        }
        return mybatisData.getId();
    }

    public MybatisResponse findById(Long id) {
        MybatisData data = mybatisDataMapper.findById(id).orElseThrow(
                () -> new RuntimeException("Resource Not Found"));
        return MybatisResponse.of(data);
    }

    public List<MybatisResponse> findAll() {
        return mybatisDataMapper.findAll()
                .stream().map(MybatisResponse::of).toList();
    }

    @Transactional
    public void updateMybatisData(Long id, MybatisUpdate request) {
        MybatisData data = mybatisDataMapper.findById(id).orElseThrow(
                () -> new RuntimeException("Resource Not Found"));
        data.update(request.title(), request.content());
        int result = mybatisDataMapper.update(data);
        if (result != 1) {
            throw new RuntimeException("Update Error");
        }
    }

    @Transactional
    public void deleteMybatisData(Long id) {
        MybatisData data = mybatisDataMapper.findById(id).orElseThrow(
                () -> new RuntimeException("Resource Not Found"));
        data.delete();
        int result = mybatisDataMapper.softDelete(id);
        if (result != 1) {
            throw new RuntimeException("Delete Error");
        }
    }
}
