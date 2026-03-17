package com.orm.mybatis.controller;

import com.orm.mybatis.dto.MybatisRequest;
import com.orm.mybatis.dto.MybatisResponse;
import com.orm.mybatis.dto.MybatisUpdate;
import com.orm.mybatis.entity.MybatisData;
import com.orm.mybatis.service.MybatisDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mybatis")
public class MybatisDataController {

    private final MybatisDataService mybatisDataService;

    @PostMapping
    public ResponseEntity<Long> create(@RequestBody MybatisRequest request) {
        Long id = mybatisDataService.createMybatisData(request);
        URI uri = URI.create("/api/mybatis/" + id);
        return ResponseEntity.created(uri).body(id);
    }

    @GetMapping
    public ResponseEntity<List<MybatisResponse>> findAll() {
        return ResponseEntity.ok().body(mybatisDataService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MybatisResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(mybatisDataService.findById(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody MybatisUpdate request) {
        mybatisDataService.updateMybatisData(id, request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        mybatisDataService.deleteMybatisData(id);
        return ResponseEntity.noContent().build();
    }
}
