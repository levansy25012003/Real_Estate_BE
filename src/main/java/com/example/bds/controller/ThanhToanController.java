package com.example.bds.controller;

import com.example.bds.dto.req.CreatePostRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/payment")
public class ThanhToanController {

    @PostMapping("/deposit")
    public ResponseEntity<?> createBatDongSan() {

        String a = "";
        return null;
    }
}
