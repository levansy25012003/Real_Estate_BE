package com.example.bds.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "donhang")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DonHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "maDonHang")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "maGoiDichVu", nullable = false)
    private GoiDichVu goiDichVu;

    @ManyToOne
    @JoinColumn(name = "maTaiKhoan", nullable = false)
    private TaiKhoan taiKhoan;

    @Column(name = "tongSo", nullable = false)
    private Integer tongSo;

    @Column(name = "maHoaDon")
    private String maHoaDon;

    @Enumerated(EnumType.STRING)
    @Column(name = "trangThai")
    private TrangThai trangThai;

    public enum TrangThai {
        Thanh_cong, Dang_cho, That_bai
    }

}
