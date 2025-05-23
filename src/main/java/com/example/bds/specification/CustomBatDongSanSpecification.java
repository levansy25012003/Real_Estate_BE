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


        return null;
    }
}
