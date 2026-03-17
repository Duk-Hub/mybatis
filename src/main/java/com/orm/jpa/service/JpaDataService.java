package com.orm.jpa.service;

import com.orm.jpa.dto.JpaRequest;
import com.orm.jpa.dto.JpaResponse;
import com.orm.jpa.dto.JpaUpdate;
import com.orm.jpa.entity.JpaData;
import com.orm.jpa.repository.JpaDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class JpaDataService {

    private final JpaDataRepository jpaDataRepository;

    @Transactional
    public Long createJpaData(JpaRequest request){
        JpaData data = JpaData.create(request.title(), request.content());
        jpaDataRepository.save(data);
        return data.getId();
    }

    public List<JpaResponse> findAllJpaData(){
        return jpaDataRepository.findAllByIsDeletedFalse().stream().map(JpaResponse::of).toList();
    }

    public JpaResponse findJpaDataById(Long id){
        JpaData data = jpaDataRepository.findByIdAndIsDeletedFalse(id).orElseThrow(() -> new RuntimeException("Resource Not Found"));
        return JpaResponse.of(data);
    }

    @Transactional
    public void update(Long id, JpaUpdate request) {
        JpaData data = jpaDataRepository.findByIdAndIsDeletedFalse(id).orElseThrow(() -> new RuntimeException("Resource Not Found"));
        data.update(request.title(), request.content());
    }

    @Transactional
    public void delete(Long id) {
        JpaData data = jpaDataRepository.findByIdAndIsDeletedFalse(id).orElseThrow(() -> new RuntimeException("Resource Not Found"));
        data.delete();
    }
}
