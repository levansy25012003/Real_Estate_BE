package com.example.bds.controller;

import com.example.bds.dto.rep.ApiResponse;
import com.example.bds.dto.rep.BatDongSanDTO;
import com.example.bds.dto.rep.Pagination;
import com.example.bds.dto.rep.ProductResponseDTO;
import com.example.bds.dto.req.CreatePostRequest;
import com.example.bds.model.BatDongSan;
import com.example.bds.model.TaiKhoan;
import com.example.bds.service.IBatDongSanService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class BatDongSanController {

    private final IBatDongSanService batDongSanService;

    @PostMapping("/new")
    public ResponseEntity<?> createBatDongSan(@RequestBody CreatePostRequest request) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        TaiKhoan currentUser = (TaiKhoan) authentication.getPrincipal();

        boolean success = batDongSanService.createBatDongSan(request, currentUser.getId());
        return ResponseEntity.ok(new ApiResponse(success, success ? "Tạo tin đăng thành công." : "Tạo tin đăng không thành công."));
    }

    @GetMapping("/user")
    public ResponseEntity<?> getBatDongSanByOwn(
            @RequestParam(defaultValue = "5") int limit,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "createdAt") String sort,
            @RequestParam(defaultValue = "DESC") String order,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String propertyType,
            @RequestParam(required = false) String status
            ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        TaiKhoan currentUser = (TaiKhoan) authentication.getPrincipal();

        Page<BatDongSan> resultPage = batDongSanService.getProductByOwn(title, propertyType, status, page, limit, sort, order, currentUser.getId());
        Page<BatDongSanDTO> pageDto = resultPage.map(BatDongSanDTO::fromEntity);
        ProductResponseDTO<BatDongSanDTO> response = ProductResponseDTO.<BatDongSanDTO>builder()
                .success(true)
                .properties(pageDto.getContent())
                .pagination(Pagination.builder()
                        .limit(limit)
                        .page(page)
                        .count(resultPage.getTotalElements())
                        .totalPages(resultPage.getTotalPages())
                        .build())
                .build();

        return ResponseEntity.ok(response);
    }
}
