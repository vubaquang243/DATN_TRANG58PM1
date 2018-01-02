package com.seatech.tp.ttlaigoc.vo;

import java.util.ArrayList;
import java.util.Collection;

public class TTLaiGocVo {
    private String guid;

    private String dot_ph;

    private String ngay_dt;

    private String ngay_ph;

    private String ngay_tt;

    private String ma_tpcp;

    private String ky_han;

    private String kl_trung_thau;

    private String ls_trung_thau;

    private String ls_danh_nghia;

    private String tong_so_tien_tt;

    private String loai_tien;

    private String trang_thai;

    private String nguoi_tao;

    private String ngay_tao;

    private String nguoi_sua_cuoi;

    private String ngay_sua_cuoi;

    private String so_bke;

    private String hinh_thuc_dt;

    private String menh_gia;

    private String ky_tra_lai;

    private String ngay_tt_lai_1;

    private String ngay_tt_lai_2;

    private String ngay_tt_tien_mua;

    private String lan_tra_lai;

    private String phuong_thuc_ph;

    private String ngay_thanh_toan_tu_ngay;

    private String ngay_thanh_toan_den_ngay;

    private String ty_gia;

    private String pageNumber;

    private Collection list_Lenh_tra_no;

    public TTLaiGocVo() {
        this.guid = "";
        this.dot_ph = "";
        this.ngay_dt = "";
        this.ngay_ph = "";
        this.ngay_tt = "";
        this.ma_tpcp = "";
        this.ky_han = "";
        this.kl_trung_thau = "";
        this.ls_trung_thau = "";
        this.ls_danh_nghia = "";
        this.tong_so_tien_tt = "";
        this.loai_tien = "";
        this.trang_thai = "";
        this.nguoi_tao = "";
        this.ngay_tao = "";
        this.nguoi_sua_cuoi = "";
        this.ngay_sua_cuoi = "";
        this.so_bke = "";
        this.hinh_thuc_dt = "";
        this.menh_gia = "";
        this.ky_tra_lai = "";
        this.ngay_tt_lai_1 = "";
        this.ngay_tt_lai_2 = "";
        this.ngay_tt_tien_mua = "";
        this.lan_tra_lai = "";
        this.phuong_thuc_ph = "";
        this.so_bke = "";
        this.ngay_thanh_toan_tu_ngay = "";
        this.ngay_thanh_toan_den_ngay = "";
        this.ty_gia = "";
        this.list_Lenh_tra_no = new ArrayList();
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public void setDot_ph(String dot_ph) {
        this.dot_ph = dot_ph;
    }

    public void setNgay_dt(String ngay_dt) {
        this.ngay_dt = ngay_dt;
    }

    public void setNgay_ph(String ngay_ph) {
        this.ngay_ph = ngay_ph;
    }

    public void setNgay_tt(String ngay_tt) {
        this.ngay_tt = ngay_tt;
    }

    public void setMa_tpcp(String ma_tpcp) {
        this.ma_tpcp = ma_tpcp;
    }

    public void setKy_han(String ky_han) {
        this.ky_han = ky_han;
    }

    public void setKl_trung_thau(String kl_trung_thau) {
        this.kl_trung_thau = kl_trung_thau;
    }

    public void setLs_trung_thau(String ls_trung_thau) {
        this.ls_trung_thau = ls_trung_thau;
    }

    public void setLs_danh_nghia(String ls_danh_nghia) {
        this.ls_danh_nghia = ls_danh_nghia;
    }

    public void setTong_so_tien_tt(String tong_so_tien_tt) {
        this.tong_so_tien_tt = tong_so_tien_tt;
    }

    public void setLoai_tien(String loai_tien) {
        this.loai_tien = loai_tien;
    }

    public void setTrang_thai(String trang_thai) {
        this.trang_thai = trang_thai;
    }

    public void setNguoi_tao(String nguoi_tao) {
        this.nguoi_tao = nguoi_tao;
    }

    public void setNgay_tao(String ngay_tao) {
        this.ngay_tao = ngay_tao;
    }

    public void setNguoi_sua_cuoi(String nguoi_sua_cuoi) {
        this.nguoi_sua_cuoi = nguoi_sua_cuoi;
    }

    public void setNgay_sua_cuoi(String ngay_sua_cuoi) {
        this.ngay_sua_cuoi = ngay_sua_cuoi;
    }

    public void setSo_bke(String so_bke) {
        this.so_bke = so_bke;
    }

    public String getGuid() {
        return guid;
    }

    public String getDot_ph() {
        return dot_ph;
    }

    public String getNgay_dt() {
        return ngay_dt;
    }

    public String getNgay_ph() {
        return ngay_ph;
    }

    public String getNgay_tt() {
        return ngay_tt;
    }

    public String getMa_tpcp() {
        return ma_tpcp;
    }

    public String getKy_han() {
        return ky_han;
    }

    public String getKl_trung_thau() {
        return kl_trung_thau;
    }

    public String getLs_trung_thau() {
        return ls_trung_thau;
    }

    public String getLs_danh_nghia() {
        return ls_danh_nghia;
    }

    public String getTong_so_tien_tt() {
        return tong_so_tien_tt;
    }

    public String getLoai_tien() {
        return loai_tien;
    }

    public String getTrang_thai() {
        return trang_thai;
    }

    public String getNguoi_tao() {
        return nguoi_tao;
    }

    public String getNgay_tao() {
        return ngay_tao;
    }

    public String getNguoi_sua_cuoi() {
        return nguoi_sua_cuoi;
    }

    public String getNgay_sua_cuoi() {
        return ngay_sua_cuoi;
    }

    public String getSo_bke() {
        return so_bke;
    }

    public String getPageNumber() {
        return pageNumber;
    }

    public void setNgay_thanh_toan_tu_ngay(String ngay_thanh_toan_tu_ngay) {
        this.ngay_thanh_toan_tu_ngay = ngay_thanh_toan_tu_ngay;
    }

    public String getNgay_thanh_toan_tu_ngay() {
        return ngay_thanh_toan_tu_ngay;
    }

    public void setNgay_thanh_toan_den_ngay(String ngay_thanh_toan_den_ngay) {
        this.ngay_thanh_toan_den_ngay = ngay_thanh_toan_den_ngay;
    }

    public String getNgay_thanh_toan_den_ngay() {
        return ngay_thanh_toan_den_ngay;
    }

    public void setTy_gia(String ty_gia) {
        this.ty_gia = ty_gia;
    }

    public String getTy_gia() {
        return ty_gia;
    }

    public void setHinh_thuc_dt(String hinh_thuc_dt) {
        this.hinh_thuc_dt = hinh_thuc_dt;
    }

    public String getHinh_thuc_dt() {
        return hinh_thuc_dt;
    }

    public void setMenh_gia(String menh_gia) {
        this.menh_gia = menh_gia;
    }

    public String getMenh_gia() {
        return menh_gia;
    }

    public void setKy_tra_lai(String ky_tra_lai) {
        this.ky_tra_lai = ky_tra_lai;
    }

    public String getKy_tra_lai() {
        return ky_tra_lai;
    }

    public void setNgay_tt_lai_1(String ngay_tt_lai_1) {
        this.ngay_tt_lai_1 = ngay_tt_lai_1;
    }

    public String getNgay_tt_lai_1() {
        return ngay_tt_lai_1;
    }

    public void setNgay_tt_lai_2(String ngay_tt_lai_2) {
        this.ngay_tt_lai_2 = ngay_tt_lai_2;
    }

    public String getNgay_tt_lai_2() {
        return ngay_tt_lai_2;
    }

    public void setNgay_tt_tien_mua(String ngay_tt_tien_mua) {
        this.ngay_tt_tien_mua = ngay_tt_tien_mua;
    }

    public String getNgay_tt_tien_mua() {
        return ngay_tt_tien_mua;
    }

    public void setLan_tra_lai(String lan_tra_lai) {
        this.lan_tra_lai = lan_tra_lai;
    }

    public String getLan_tra_lai() {
        return lan_tra_lai;
    }

    public void setPhuong_thuc_ph(String phuong_thuc_ph) {
        this.phuong_thuc_ph = phuong_thuc_ph;
    }

    public String getPhuong_thuc_ph() {
        return phuong_thuc_ph;
    }

    public void setList_Lenh_tra_no_chi_tiet(Collection list_Lenh_tra_no_chi_tiet) {
        this.list_Lenh_tra_no = list_Lenh_tra_no_chi_tiet;
    }

    public Collection getList_Lenh_tra_no_chi_tiet() {
        return list_Lenh_tra_no;
    }
}
