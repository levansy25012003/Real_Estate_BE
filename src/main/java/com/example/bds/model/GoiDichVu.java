package com.example.bds.model;

import jakarta.persistence.*;
import lombok.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "goidichvu")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GoiDichVu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "maGoiDichVu")
    private Integer id;

    @Column(name = "ten")
    private String ten;

    @Column(name = "hienThiNgay")
    private Boolean hienThiNgay = false;

    @Column(name = "hienThiNguoiBan")
    private Boolean hienThiNguoiBan = false;

    @Column(name = "hienThiNutGoiDien")
    private Boolean hienThiNutGoiDien = false;

    @Column(name = "hienThiMoTa")
    private Boolean hienThiMoTa = false;

    @Column(name = "kichThuocAnh")
    private String kichThuocAnh;

    @Column(name = "doUuTien")
    private Integer doUuTien;

    @Column(name = "diemYeuCau")
    private Integer diemYeuCau;

    @Column(name = "diemYeuCauDenCapDoTiep")
    private Integer diemYeuCauDenCapDoTiep;

    @Column(name = "gia")
    private Integer gia;

    @Column(name = "ngayHetHan")
    private Integer ngayHetHan;

    @Column(name = "urlHinhAnh")
    private String urlHinhAnh;

    @OneToMany(mappedBy = "maGiaHienTai")
    private List<TaiKhoan> taiKhoans;
}
