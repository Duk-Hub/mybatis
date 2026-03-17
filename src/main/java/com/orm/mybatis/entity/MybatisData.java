package com.orm.mybatis.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.StringUtils;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MybatisData {

    private Long id;

    private String title;

    private String content;

    private boolean isDeleted;

    private MybatisData(String title, String content) {
        this.title = title;
        this.content = content;
        this.isDeleted = false;
    }

    public static MybatisData create(String title, String content) {
        return new MybatisData(title, content);
    }

    public void update(String title, String content) {
        this.title = StringUtils.hasText(title) ? title : this.title;
        this.content = StringUtils.hasText(content) ? content : this.content;
    }

    public void delete() {
        this.isDeleted = true;
    }
}
