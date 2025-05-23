package com.example.bds.specification;

import com.example.bds.model.BatDongSan;
import org.springframework.data.jpa.domain.Specification;

@SuppressWarnings("deprecation")
public class BatDongSanSpecification {

    public static Specification<BatDongSan> buildWhere(String title, String propertyType, String status, Integer id) {
        Specification<BatDongSan> where = null;
        if (title != null) {
            CustomBatDongSanSpecification titleSpec = new CustomBatDongSanSpecification("title", title);
            where = Specification.where(titleSpec);
        }

        if (propertyType != null) {
            CustomBatDongSanSpecification propertyTypeSpec = new CustomBatDongSanSpecification("propertyType", propertyType);
            where = Specification.where(propertyTypeSpec);
        }

        if (status != null) {
            CustomBatDongSanSpecification statusSpec = new CustomBatDongSanSpecification("status", status);
            where = Specification.where(statusSpec);
        }
        if (id != null) {
            CustomBatDongSanSpecification idSpec = new CustomBatDongSanSpecification("id", id);
            where = Specification.where(idSpec);
        }
        return where;
    }
}
