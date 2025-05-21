package com.example.bds.service.impl;

import com.example.bds.component.JwtTokenUtil;
import com.example.bds.dto.req.LoginGoogleDTO;
import com.example.bds.exception.DataNotFoundException;
import com.example.bds.model.TaiKhoan;
import com.example.bds.repository.TaiKhoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final TaiKhoanRepository taiKhoanRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;

    public String loginWithGoogle(LoginGoogleDTO req) throws Exception {

        Optional<TaiKhoan> taiKhoanOpt = taiKhoanRepository.findByEmail(req.getEmail());
        TaiKhoan taiKhoan;

        if (taiKhoanOpt.isPresent()) {
            // Đã có tài khoản trong DB
            taiKhoan = taiKhoanOpt.get();
        } else {
            // Không có tài khoản, kiểm tra password để tạo tài khoản mới
            if (req.getPassword() == null) {
                throw new DataNotFoundException("Tài khoản hoặc mật khẩu không đúng");
            }

            // Tạo mới tài khoản
            taiKhoan = new TaiKhoan();
            taiKhoan.setEmail(req.getEmail());
            taiKhoan.setHoVaTen(req.getFullname());
            taiKhoan.setAnhDaiDien(req.getAvatar());
            taiKhoan.setEmailXacThuc(true);

            taiKhoan.setMatKhau(passwordEncoder.encode(req.getPassword()));

            taiKhoan = taiKhoanRepository.save(taiKhoan);
        }

        String token = jwtTokenUtil.generateToken(taiKhoan);
        return token;
    }
}
