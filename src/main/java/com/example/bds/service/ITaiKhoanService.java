package com.example.bds.service;

import com.example.bds.exception.DataNotFoundException;
import com.example.bds.model.TaiKhoan;

import java.util.Optional;

public interface ITaiKhoanService {
    String login (String email, String password) throws Exception;
    TaiKhoan findById(Integer mataikhoan) throws DataNotFoundException;

    Optional<TaiKhoan> finByPhoneNumber(String phoneNumber) throws DataNotFoundException;

    int updatePhoneAndVerified(Integer id, String phone);
}
