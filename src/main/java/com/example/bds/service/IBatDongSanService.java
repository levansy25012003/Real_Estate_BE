package com.example.bds.service;

import com.example.bds.dto.req.CreatePostRequest;
import com.example.bds.model.BatDongSan;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

public interface IBatDongSanService {
    Boolean createBatDongSan(CreatePostRequest request, Integer id);

    public Page<BatDongSan> getProductByOwn(String title, String propertyType, String status, int page, int limit, String sort, String order, Integer id);

}
