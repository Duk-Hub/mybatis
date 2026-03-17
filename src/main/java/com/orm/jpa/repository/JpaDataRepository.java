package com.orm.jpa.repository;

import com.orm.jpa.entity.JpaData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JpaDataRepository extends JpaRepository<JpaData, Long> {
    Optional<JpaData> findByIdAndIsDeletedFalse(Long id);
    List<JpaData> findAllByIsDeletedFalse();
}
