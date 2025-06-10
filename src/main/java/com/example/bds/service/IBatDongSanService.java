package com.example.bds.service;

import com.example.bds.dto.req.CommentReqDTO;
import com.example.bds.dto.req.CreatePostRequest;
import com.example.bds.model.BatDongSan;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface IBatDongSanService {
    Boolean createBatDongSan(CreatePostRequest request, Integer id);

    public Page<BatDongSan> getProductByOwn(String title, String propertyType, String status, Integer id, int page,
                                            int limit, String sort, String order);

    public Page<BatDongSan> getPostAll(String title, String propertyType, String listingType, String address,
                                       Integer minPrice, Integer maxPrice, Integer minSize, Integer maxSize,
                                       Integer bedroom, Integer bathroom, int page, int limit);

    public Optional<BatDongSan> findBaDongSanId(Integer id);
    public Boolean updateBatDongSan(CreatePostRequest req, Integer id);
    public Boolean deleteBatDongSan(Integer id);
    public Boolean createNewComment(Integer idTaiKhoan, CommentReqDTO req);
}
