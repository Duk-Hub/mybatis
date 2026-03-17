package com.orm.jpa.controller;

import com.orm.jpa.dto.JpaRequest;
import com.orm.jpa.dto.JpaResponse;
import com.orm.jpa.dto.JpaUpdate;
import com.orm.jpa.service.JpaDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/jpa")
public class JpaDataController {

    private final JpaDataService jpaDataService;

    @PostMapping
    public ResponseEntity<Long> create(@RequestBody JpaRequest request) {
        Long id = jpaDataService.createJpaData(request);
        URI uri = URI.create("/api/jpa/" + id);
        return ResponseEntity.created(uri).body(id);
    }

    @GetMapping
    public ResponseEntity<List<JpaResponse>> findAll() {
        List<JpaResponse> data = jpaDataService.findAllJpaData();
        return ResponseEntity.ok().body(data);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JpaResponse> findById(@PathVariable Long id) {
        JpaResponse data = jpaDataService.findJpaDataById(id);
        return ResponseEntity.ok().body(data);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody JpaUpdate request) {
        jpaDataService.update(id,request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        jpaDataService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
