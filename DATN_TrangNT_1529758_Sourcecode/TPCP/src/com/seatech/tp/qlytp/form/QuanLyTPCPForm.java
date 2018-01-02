package com.seatech.tp.qlytp.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class QuanLyTPCPForm extends ActionForm {
    private String guid;

    private String ma_tp;

    private String phuong_thuc_ph;

    private String noi_cap;

    private String ky_han;

    private String trang_thai;

    private String nguoi_tao;

    private String ngay_tao;

    private String nguoi_sua_cuoi;

    private String ngay_sua_cuoi;

    private String pageNumber;

    private String ngay_tao_tu_ngay;

    private String ngay_tao_den_ngay;
    
    private String dac_biet;
    
    private String loai_tpcp;
    
    private String dvi_mua;
    

    public QuanLyTPCPForm() {
        this.guid = "";
        this.ma_tp = "";
        this.phuong_thuc_ph = "";
        this.noi_cap = "";
        this.ky_han = "";
        this.trang_thai = "";
        this.nguoi_tao = "";
        this.ngay_tao = "";
        this.nguoi_sua_cuoi = "";
        this.ngay_sua_cuoi = "";
        this.ngay_tao_tu_ngay = "";
        this.ngay_tao_den_ngay = "";
        this.dac_biet = "";
    }

    @Override
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        super.reset(mapping, request);
        this.guid = "";
        this.ma_tp = "";
        this.phuong_thuc_ph = "";
        this.noi_cap = "";
        this.ky_han = "";
        this.trang_thai = "";
        this.nguoi_tao = "";
        this.ngay_tao = "";
        this.nguoi_sua_cuoi = "";
        this.ngay_sua_cuoi = "";
        this.ngay_tao_tu_ngay = "";
        this.ngay_tao_den_ngay = "";
         this.dac_biet = "";
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public void setMa_tp(String ma_tp) {
        this.ma_tp = ma_tp;
    }

    public void setPhuong_thuc_ph(String phuong_thuc_ph) {
        this.phuong_thuc_ph = phuong_thuc_ph;
    }

    public void setNoi_cap(String noi_cap) {
        this.noi_cap = noi_cap;
    }

    public void setKy_han(String ky_han) {
        this.ky_han = ky_han;
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

    public String getGuid() {
        return guid;
    }

    public String getMa_tp() {
        return ma_tp;
    }

    public String getPhuong_thuc_ph() {
        return phuong_thuc_ph;
    }

    public String getNoi_cap() {
        return noi_cap;
    }

    public String getKy_han() {
        return ky_han;
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

    public String getPageNumber() {
        return pageNumber;
    }

    public void setNgay_tao_tu_ngay(String ngay_tao_tu_ngay) {
        this.ngay_tao_tu_ngay = ngay_tao_tu_ngay;
    }

    public String getNgay_tao_tu_ngay() {
        return ngay_tao_tu_ngay;
    }

    public void setNgay_tao_den_ngay(String ngay_tao_den_ngay) {
        this.ngay_tao_den_ngay = ngay_tao_den_ngay;
    }

    public String getNgay_tao_den_ngay() {
        return ngay_tao_den_ngay;
    }

    public void setPageNumber(String pageNumber) {
        this.pageNumber = pageNumber;
    }

    public void setDac_biet(String dac_biet) {
        this.dac_biet = dac_biet;
    }

    public String getDac_biet() {
        return dac_biet;
    }

   
    public void setDvi_mua(String dvi_mua) {
        this.dvi_mua = dvi_mua;
    }

    public String getDvi_mua() {
        return dvi_mua;
    }

    public void setLoai_tpcp(String loai_tpcp) {
        this.loai_tpcp = loai_tpcp;
    }

    public String getLoai_tpcp() {
        return loai_tpcp;
    }
}
