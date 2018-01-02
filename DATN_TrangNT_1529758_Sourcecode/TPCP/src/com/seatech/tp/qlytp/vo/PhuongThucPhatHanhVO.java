package com.seatech.tp.qlytp.vo;

import com.seatech.framework.datamanager.BaseVO;

public class PhuongThucPhatHanhVO extends BaseVO {
    private String guid;

    private String ma_phuong_thuc;

    private String ten_phuong_thuc;

    private String trang_thai;

    private String ngay_tao;

    private String nguoi_tao;

    private String pageNumber;

    public PhuongThucPhatHanhVO() {
        this.guid = "";
        this.ma_phuong_thuc = "";
        this.ten_phuong_thuc = "";
        this.trang_thai = "";
        this.ngay_tao = "";
        this.nguoi_tao = "";
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public void setMa_phuong_thuc(String ma_phuong_thuc) {
        this.ma_phuong_thuc = ma_phuong_thuc;
    }

    public void setTen_phuong_thuc(String ten_phuong_thuc) {
        this.ten_phuong_thuc = ten_phuong_thuc;
    }

    public void setTrang_thai(String trang_thai) {
        this.trang_thai = trang_thai;
    }

    public void setNgay_tao(String ngay_tao) {
        this.ngay_tao = ngay_tao;
    }

    public void setNguoi_tao(String nguoi_tao) {
        this.nguoi_tao = nguoi_tao;
    }

    public String getGuid() {
        return guid;
    }

    public String getMa_phuong_thuc() {
        return ma_phuong_thuc;
    }

    public String getTen_phuong_thuc() {
        return ten_phuong_thuc;
    }

    public String getTrang_thai() {
        return trang_thai;
    }

    public String getNgay_tao() {
        return ngay_tao;
    }

    public String getNguoi_tao() {
        return nguoi_tao;
    }

    public String getPageNumber() {
        return pageNumber;
    }

}
