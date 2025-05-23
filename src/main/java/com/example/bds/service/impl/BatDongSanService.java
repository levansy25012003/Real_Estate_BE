package com.example.bds.service.impl;

import com.example.bds.dto.req.CreatePostRequest;
import com.example.bds.model.BatDongSan;
import com.example.bds.repository.BatDongSanRepository;
import com.example.bds.repository.GoiDichVuRepository;
import com.example.bds.repository.TaiKhoanRepository;
import com.example.bds.service.IBatDongSanService;
import com.example.bds.specification.BatDongSanSpecification;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.Timestamp;

@Service
@RequiredArgsConstructor
public class BatDongSanService implements IBatDongSanService {
    private final BatDongSanRepository batDongSanRepository;
    private final GoiDichVuRepository goiDichVuRepository;
    private final TaiKhoanRepository taiKhoanRepository;

    @Override
    public Boolean createBatDongSan(CreatePostRequest req, Integer id) {
      try {
          BatDongSan bds = new BatDongSan();

          bds.setTieuDe(req.getTitle());
          bds.setMoTa(req.getDescription());
          bds.setDiaChi(req.getAddress());
          bds.setTinh(req.getProvince());
          bds.setHuyen(req.getDistrict());
          bds.setXa(req.getWard());
          bds.setGia(req.getPrice());
          bds.setDongGia(req.getPriceUnit());
          bds.setDienTich(req.getSize());
          bds.setSoTang(req.getFloor());
          bds.setSoPhongNgu(req.getBedroom());
          bds.setSoPhongTam(req.getBathroom());
          bds.setNoiThat(req.getIsFurniture());
          bds.setNgayHetHan(new Timestamp(req.getExpiredDate().getTime()));

          bds.setLoaiBatDongSan(req.getPropertyType());
          //bds.setLoaiDanhSach(BatDongSan.LoaiDanhSach.valueOf(req.getListingType()));

          if (req.getDirection() != null) {
              bds.setHuong(BatDongSan.Huong.valueOf(req.getDirection()));
          }
          if (req.getBalonyDirection() != null) {
              bds.setHuongBanCong(BatDongSan.Huong.valueOf(req.getBalonyDirection()));
          }

          if (req.getIdPricing() != null && req.getIdPricing() != 1) {
              bds.setCongKhai(true);
          } else {
              bds.setCongKhai(false);
          }

          if (req.getTags() != null) {
              ObjectMapper mapper = new ObjectMapper();
              String jsonTags = mapper.writeValueAsString(req.getTags());
              bds.setThe(jsonTags);
          }
          if (req.getMedia() != null) {
              ObjectMapper mapper = new ObjectMapper();
              String jsonMedia = mapper.writeValueAsString(req.getMedia());
              bds.setHinhAnh(jsonMedia);
          }
          bds.setXacThuc(false);
          bds.setLuotXem(0);
          // Thiết lập liên kết tài khoản và gói dịch vụ
          taiKhoanRepository.findById(id).ifPresent(bds::setTaiKhoan);
          if (req.getIdPricing() != null) {
              goiDichVuRepository.findById(req.getIdPricing())
                      .ifPresentOrElse(
                              bds::setGoiDichVu,
                              () -> goiDichVuRepository.findById(1)
                                      .ifPresent(bds::setGoiDichVu)
                      );
          } else {
              goiDichVuRepository.findById(1)
                      .ifPresent(bds::setGoiDichVu);
          }
          batDongSanRepository.save(bds);
          return true;
      } catch (Exception e) {
          e.printStackTrace();
          return false;
      }

    }

    @Override
    public Page<BatDongSan> getProductByOwn(String title, String propertyType, String status, int page, int limit, String sort, String order, Integer id) {
        Sort.Direction direction = order.equalsIgnoreCase("DESC") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(Math.max(page - 1, 0), limit, Sort.by(direction, sort));
        Specification<BatDongSan> where = BatDongSanSpecification.buildWhere(title, propertyType, status, id);
        Page<BatDongSan> batDongSans = batDongSanRepository.findAll(where, pageable);

        return batDongSans;
    }
}
