package com.seatech.tp.dmkyhan.vo;

import com.seatech.framework.datamanager.BaseVO;

public class DMKyHanVO extends BaseVO {

    private String guid;

    private String loai_ky_han;

    private String ky_han;

    private String loai_tpcp;

    private String trang_thai;

    private String mo_ta;

    private String pageNumber;

    private String name_ky_han;
    private String klph;
    private String id_ky_han;

    public DMKyHanVO() {
        this.guid = "";
        this.loai_ky_han = "";
        this.ky_han = "";
        this.loai_tpcp = "";
        this.trang_thai = "";
        this.mo_ta = "";
        this.klph = "";
        this.id_ky_han = "";
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public void setLoai_ky_han(String loai_ky_han) {
        this.loai_ky_han = loai_ky_han;
    }

    public void setKy_han(String ky_han) {
        this.ky_han = ky_han;
    }

    public void setLoai_tpcp(String loai_tpcp) {
        this.loai_tpcp = loai_tpcp;
    }

    public void setTrang_thai(String trang_thai) {
        this.trang_thai = trang_thai;
    }

    public void setMo_ta(String mo_ta) {
        this.mo_ta = mo_ta;
    }

    public String getGuid() {
        return guid;
    }

    public String getLoai_ky_han() {
        return loai_ky_han;
    }

    public String getKy_han() {
        return ky_han;
    }

    public String getLoai_tpcp() {
        return loai_tpcp;
    }

    public String getTrang_thai() {
        return trang_thai;
    }

    public String getMo_ta() {
        return mo_ta;
    }

    public String getPageNumber() {
        return pageNumber;
    }

    public void setName_ky_han(String name_ky_han) {
        this.name_ky_han = name_ky_han;
    }

    public String getName_ky_han() {
        return name_ky_han;
    }

    public void setSo_tien(String klph) {
        this.klph = klph;
    }

    public String getSo_tien() {
        return klph;
    }

    public void setId_ky_han(String id_ky_han) {
        this.id_ky_han = id_ky_han;
    }

    public String getId_ky_han() {
        return id_ky_han;
    }
}
