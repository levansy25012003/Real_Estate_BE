package com.example.bds.model;

import jakarta.persistence.*;
import lombok.*;
import java.sql.Timestamp;

@Entity
@Table(name = "BATDONGSAN")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BatDongSan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "maBatDongSan", nullable = false, unique = true)
    private Integer id;

    @Column(name = "tieuDe")
    private String tieuDe;

    @Column(name = "diaChi")
    private String diaChi;

    @Column(name = "xa")
    private String xa;

    @Column(name = "huyen")
    private String huyen;

    @Column(name = "tinh")
    private String tinh;

    @Column(name = "gia")
    private Integer gia = 0;

    @Column(name = "dongGia")
    private Integer dongGia = 0;

    @Column(name = "dienTich")
    private Integer dienTich = 0;

    @Column(name = "soTang")
    private Integer soTang = 0;

    @Column(name = "soPhongTam")
    private Integer soPhongTam = 0;

    @Column(name = "soPhongNgu")
    private Integer soPhongNgu = 0;

    @Column(name = "moTa", columnDefinition = "TEXT")
    private String moTa;

    @Enumerated(EnumType.STRING)
    @Column(name = "loaiBatDongSan", columnDefinition = "ENUM('Nhà', 'Đất', 'Chung cư')")
    private LoaiBatDongSan loaiBatDongSan;

    @Enumerated(EnumType.STRING)
    @Column(name = "loaiDanhSach", columnDefinition = "ENUM('Bán', 'Cho thuê')")
    private LoaiDanhSach loaiDanhSach;

    @Column(name = "diemDanhGiaTB")
    private Float diemDanhGiaTB;

    @Column(name = "luotXem")
    private Integer luotXem = 0;

    @Enumerated(EnumType.STRING)
    @Column(name = "huongBanCong", columnDefinition = "ENUM('Đông', 'Tây', 'Nam', 'Bắc')")
    private Huong huongBanCong;

    @Enumerated(EnumType.STRING)
    @Column(name = "huong", columnDefinition = "ENUM('Đông', 'Tây', 'Nam', 'Bắc')")
    private Huong huong;

    @Enumerated(EnumType.STRING)
    @Column(name = "trangThai", columnDefinition = "ENUM('Đang mở', 'Đang cập nhật', 'Đã bàn giao')")
    private TrangThai trangThai;

    @Column(name = "noiThat")
    private Boolean noiThat = false;

    @Column(name = "congKhai")
    private Boolean congKhai = false;

    @Column(name = "xacThuc")
    private Boolean xacThuc = false;

    @Column(name = "ngayHetHan")
    private Timestamp ngayHetHan;

    @Column(name = "thoiGianHetHieuLucDayTin")
    private Timestamp thoiGianHetHieuLucDayTin;

    @Column(name = "hinhAnh", columnDefinition = "TEXT")
    private String hinhAnh;

    @Column(name = "the", columnDefinition = "TEXT")
    private String the;

    @Column(name = "createdAt", updatable = false, insertable = false)
    private Timestamp createdAt;

    @Column(name = "updatedAt", insertable = false)
    private Timestamp updatedAt;

    @ManyToOne
    @JoinColumn(name = "maGoiDichVu")
    private GoiDichVu goiDichVu;

    @ManyToOne
    @JoinColumn(name = "maTaiKhoan")
    private TaiKhoan taiKhoan;



    public enum LoaiBatDongSan {
        Nhà, Đất, Chung_cư
    }

    public enum LoaiDanhSach {
        Bán, Cho_thuê
    }

    public enum Huong {
        Đông, Tây, Nam, Bắc
    }

    public enum TrangThai {
        Đang_mở, Đang_cập_nhật, Đã_bàn_giao
    }

}
