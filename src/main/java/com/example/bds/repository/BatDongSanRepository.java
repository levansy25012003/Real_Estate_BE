package com.example.bds.repository;

import com.example.bds.model.BatDongSan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BatDongSanRepository extends JpaRepository<BatDongSan,Integer>, JpaSpecificationExecutor<BatDongSan> {
    @Override
    Optional<BatDongSan> findById(Integer integer);
}
