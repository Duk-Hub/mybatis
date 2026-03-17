package com.orm.mybatis.dto;

import com.orm.mybatis.entity.MybatisData;

public record MybatisResponse(
        String title,
        String content
) {
    public static MybatisResponse of(MybatisData data) {
        return new MybatisResponse(data.getTitle(), data.getContent());
    }
}
