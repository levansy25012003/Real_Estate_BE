package com.example.bds.repository;

import com.example.bds.model.BatDongSan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BatDongSanRepository extends JpaRepository<BatDongSan,Integer> {
}
