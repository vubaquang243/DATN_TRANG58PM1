package com.seatech.tp.dmtraichu.vo;

import com.seatech.framework.datamanager.BaseVO;

public class DMTraiChuVO   {

    private String guid;

    private String ma_tv;

    private String ma_chu_so_huu;

    private String ten_dvi_so_huu;

    private String loai_hinh;

    private String co_cau;

    private String ban_le;

    private String trang_thai;

    private String nguoi_tao;

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getGuid() {
        return guid;
    }

    public void setMa_tv(String ma_tv) {
        this.ma_tv = ma_tv;
    }

    public String getMa_tv() {
        return ma_tv;
    }

    public void setMa_chu_so_huu(String ma_chu_so_huu) {
        this.ma_chu_so_huu = ma_chu_so_huu;
    }

    public String getMa_chu_so_huu() {
        return ma_chu_so_huu;
    }

    public void setTen_dvi_so_huu(String ten_dvi_so_huu) {
        this.ten_dvi_so_huu = ten_dvi_so_huu;
    }

    public String getTen_dvi_so_huu() {
        return ten_dvi_so_huu;
    }

    public void setLoai_hinh(String loai_hinh) {
        this.loai_hinh = loai_hinh;
    }

    public String getLoai_hinh() {
        return loai_hinh;
    }

    public void setCo_cau(String co_cau) {
        this.co_cau = co_cau;
    }

    public String getCo_cau() {
        return co_cau;
    }

    public void setTrang_thai(String trang_thai) {
        this.trang_thai = trang_thai;
    }

    public String getTrang_thai() {
        return trang_thai;
    }

    public void setBan_le(String ban_le) {
        this.ban_le = ban_le;
    }

    public String getBan_le() {
        return ban_le;
    }

    public void setNguoi_tao(String nguoi_tao) {
        this.nguoi_tao = nguoi_tao;
    }

    public String getNguoi_tao() {
        return nguoi_tao;
    }
}
