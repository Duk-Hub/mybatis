package com.orm.jpa.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SoftDelete;
import org.springframework.util.StringUtils;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "jpa_data")
public class JpaData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    private boolean isDeleted;

    private JpaData(String title, String content) {
        this.title = title;
        this.content = content;
        this.isDeleted = false;
    }

    public static JpaData create(String title, String content) {
        return new JpaData(title, content);
    }

    public void update(String title, String content) {
        this.title = StringUtils.hasText(title) ? title : this.title;
        this.content = StringUtils.hasText(content) ? content : this.content;
    }

    public void delete(){
        this.isDeleted = true;
    }
}
