package com.example.bds.controller;

import com.example.bds.dto.req.CreatePostRequest;
import com.example.bds.service.IBatDongSanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class BatDongSanController {

    private final IBatDongSanService batDongSanService;

    @PostMapping("/new")
    public ResponseEntity<?> createBatDongSan(@RequestBody CreatePostRequest request) {

        return ResponseEntity.ok("Bat Dong San created successfully!");
    }
}
