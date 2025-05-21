package com.example.bds.service;

import com.example.bds.dto.req.CreatePostRequest;
import org.springframework.stereotype.Service;

public interface IBatDongSanService {
    Boolean createBatDongSan(CreatePostRequest request, Integer id);
}
