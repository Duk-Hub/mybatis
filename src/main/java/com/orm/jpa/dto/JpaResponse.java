package com.orm.jpa.dto;

import com.orm.jpa.entity.JpaData;

public record JpaResponse(
        String title,
        String content
) {
    public static JpaResponse of(JpaData jpaData) {
        return new JpaResponse(jpaData.getTitle(), jpaData.getContent());
    }
}
