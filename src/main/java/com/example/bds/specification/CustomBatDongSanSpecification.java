package com.example.bds.specification;


import com.example.bds.model.BatDongSan;
import com.example.bds.model.TaiKhoan;
import jakarta.persistence.criteria.*;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

@SuppressWarnings("serial")
@RequiredArgsConstructor
public class CustomBatDongSanSpecification implements Specification<BatDongSan> {

    @NonNull
    private String field;
    @NonNull
    private Object value;


    @Override
    public Predicate toPredicate(Root<BatDongSan> root,
                                 CriteriaQuery<?> query,
                                 CriteriaBuilder criteriaBuilder) {
        if (field.equalsIgnoreCase("title")) {
            return criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("tieuDe")),
                    "%" + value.toString().toLowerCase() + "%"
            );
        }

        if (field.equalsIgnoreCase("propertyType")) {
            return criteriaBuilder.equal(root.get("loaiBatDongSan"), value.toString());
        }

        if (field.equalsIgnoreCase("status")) {
            return criteriaBuilder.equal(root.get("trangThai"), value.toString());
        }
        if (field.equalsIgnoreCase("id")) {
            Join<BatDongSan, TaiKhoan> taiKhoanJoin = root.join("taiKhoan");
            return criteriaBuilder.equal(taiKhoanJoin.get("id"), value);
        }

        if (field.equalsIgnoreCase("listingType")) {
            return criteriaBuilder.equal(root.get("loaiDanhSach"), value.toString());
        }

        if (field.equalsIgnoreCase("address")) {
            return criteriaBuilder.equal(root.get("diaChi"), value.toString());
        }

        if (field.equalsIgnoreCase("bedroom")) {
            return criteriaBuilder.equal(root.get("soPhongNgu"), Integer.parseInt(value.toString()));
        }
        if (field.equalsIgnoreCase("bathroom")) {
            return criteriaBuilder.equal(root.get("soPhongTam"), Integer.parseInt(value.toString()));
        }
        if (field.equalsIgnoreCase("minPrice")) {
            return criteriaBuilder.greaterThanOrEqualTo(root.get("gia"), Integer.parseInt(value.toString()));
        }

        if (field.equalsIgnoreCase("maxPrice")) {
            return criteriaBuilder.lessThanOrEqualTo(root.get("gia"), Integer.parseInt(value.toString()));
        }

        if (field.equalsIgnoreCase("minSize")) {
            return criteriaBuilder.greaterThanOrEqualTo(root.get("dienTich"), Integer.parseInt(value.toString()));
        }

        if (field.equalsIgnoreCase("maxSize")) {
            return criteriaBuilder.lessThanOrEqualTo(root.get("dienTich"), Integer.parseInt(value.toString()));
        }
        if (field.equalsIgnoreCase("isPublic")) {
            return criteriaBuilder.equal(root.get("congKhai"), value);
        }

        return null;
    }
}
