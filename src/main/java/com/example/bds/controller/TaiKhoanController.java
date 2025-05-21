package com.example.bds.controller;

import com.example.bds.dto.rep.ApiResponse;
import com.example.bds.dto.rep.TaiKhoanResponse;
import com.example.bds.dto.req.SendOtpRequest;
import com.example.bds.dto.req.VerifyOtpRequest;
import com.example.bds.exception.DataNotFoundException;
import com.example.bds.model.TaiKhoan;
import com.example.bds.repository.TaiKhoanRepository;
import com.example.bds.service.impl.TaiKhoanService;
import com.twilio.rest.verify.v2.service.Verification;
import com.twilio.rest.verify.v2.service.VerificationCheck;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class TaiKhoanController {

    @Value("${twilio.serviceSid}")
    private String serviceSid;
    private final TaiKhoanService taiKhoanService;
    private final TaiKhoanRepository taiKhoanRepository;

    @GetMapping("/me")
    public ResponseEntity<?> getMe() {
        try {
            TaiKhoanResponse dto = taiKhoanService.findByCurrentLogin();
            return ResponseEntity.ok(Map.of(
                    "success", true,
                    "user", dto,
                    "msg", "OK"
            ));
        } catch (DataNotFoundException e) {
            return ResponseEntity.ok(Map.of(
                    "success", false,
                    "user", null,
                    "msg", e.getMessage()
            ));
        }
    }

    @PostMapping("/send-otp")
    public ResponseEntity<?> sendOtp(@RequestBody SendOtpRequest request) throws DataNotFoundException {
        String fullphone = "+84" + request.getPhone();

        Optional<TaiKhoan> taiKhoanExisting = taiKhoanService.finByPhoneNumber(request.getPhone());
        if (taiKhoanExisting.isPresent()) {
            return ResponseEntity.ok().body(new ApiResponse(false, "Số điện thoại đã được đăng ký"));
        }

       try {
           Verification.creator(serviceSid, fullphone, "sms").create();
           return ResponseEntity.ok(new ApiResponse(true, "Mã OTP đã gửi thành công."));
       } catch (Exception e) {
           return ResponseEntity.ok(new ApiResponse(false, "Mã OTP đã gửi không thành công."));
       }
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<?> verifyOtp(@RequestBody VerifyOtpRequest request) {
       String fullPhone = "+84" + request.getPhone();

       try {
           VerificationCheck verificationCheck = VerificationCheck.creator(serviceSid, request.getCode())
                   .setTo(fullPhone)
                   .create();
           Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
           TaiKhoan currentUser = (TaiKhoan) authentication.getPrincipal();
            if ("approved".equals(verificationCheck.getStatus())) {
                // Update số điện thoại cho người dùng
                int updated = taiKhoanService.updatePhoneAndVerified(currentUser.getId(), request.getPhone());
                boolean success = updated > 0;
                return ResponseEntity.ok(new ApiResponse(success, success ? "Xác minh điện thoại thành công." : "Xác minh điện thoại không thành công."));
            } else {
                return ResponseEntity.ok(new ApiResponse(false, "Xác minh điện thoại không thành công."));
            }
       } catch (Exception e) {
           return ResponseEntity.ok(new ApiResponse(false, "Mã OTP không hợp lệ mm."));
       }

   }

    @PutMapping("/upgrade-owner")
    public ResponseEntity<?> updateRoleOwner() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        TaiKhoan currentUser = (TaiKhoan) authentication.getPrincipal();

        if (!currentUser.getDienThoaiXacThuc() || !currentUser.getEmailXacThuc() || currentUser.getSoDu() == 0) {
            return ResponseEntity.ok(new ApiResponse(false, "Bạn chưa đủ điều kiện nâng cấp thành chủ bất động sản."));
        }

        currentUser.setVaiTro(TaiKhoan.VaiTro.CHUBATDONGSAN);
        taiKhoanRepository.save(currentUser);

        return ResponseEntity.ok(new ApiResponse(true, "Nâng cấp thành công, bạn hãy đăng nhập lại để cập nhật nhé"));
    }
}
