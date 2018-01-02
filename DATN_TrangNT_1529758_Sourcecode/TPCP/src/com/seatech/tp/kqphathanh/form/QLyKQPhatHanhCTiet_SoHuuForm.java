package com.seatech.tp.kqphathanh.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class QLyKQPhatHanhCTiet_SoHuuForm extends ActionForm{
  private String guid;

  private String tp_kq_ph_id;

  private String stt;

  private String ma_tpcp;

  private String thanh_vien_dt;

  private String so_tk_tt;

  private String kl_trung_thau;

  private String ls_trung_thau;

  private String tien_tt_mua;

  private String trang_thai_tt;

  private String ma_nguoi_so_huu;

  private String ten_nguoi_so_huu;

  private String pageNumber;
  
  private String thanh_vien_dau_thau;
  
  
  
  public QLyKQPhatHanhCTiet_SoHuuForm(){
     this.guid="";
     this.tp_kq_ph_id="";
     this.stt="";
     this.ma_tpcp="";
     this.thanh_vien_dt="";
     this.so_tk_tt="";
     this.kl_trung_thau="";
     this.ls_trung_thau="";
     this.tien_tt_mua="";
     this.trang_thai_tt="";
     this.ma_nguoi_so_huu="";
     this.ten_nguoi_so_huu="";
     this.thanh_vien_dau_thau="";
  }
  @Override
  public void reset(ActionMapping mapping, HttpServletRequest request) {
      super.reset(mapping, request);
    this.guid="";
    this.tp_kq_ph_id="";
    this.stt="";
    this.ma_tpcp="";
    this.thanh_vien_dt="";
    this.so_tk_tt="";
    this.kl_trung_thau="";
    this.ls_trung_thau="";
    this.tien_tt_mua="";
    this.trang_thai_tt="";
    this.ma_nguoi_so_huu="";
    this.ten_nguoi_so_huu="";
    this.thanh_vien_dau_thau="";
  }

  public void setGuid(String guid) { 
     this.guid = guid;
  }

  public void setTp_kq_ph_id(String tp_kq_ph_id) { 
     this.tp_kq_ph_id = tp_kq_ph_id;
  }

  public void setStt(String stt) { 
     this.stt = stt;
  }

  public void setMa_tpcp(String ma_tpcp) { 
     this.ma_tpcp = ma_tpcp;
  }

  public void setThanh_vien_dt(String thanh_vien_dt) { 
     this.thanh_vien_dt = thanh_vien_dt;
  }

  public void setSo_tk_tt(String so_tk_tt) { 
     this.so_tk_tt = so_tk_tt;
  }

  public void setKl_trung_thau(String kl_trung_thau) { 
     this.kl_trung_thau = kl_trung_thau;
  }

  public void setLs_trung_thau(String ls_trung_thau) { 
     this.ls_trung_thau = ls_trung_thau;
  }

  public void setTien_tt_mua(String tien_tt_mua) { 
     this.tien_tt_mua = tien_tt_mua;
  }

  public void setTrang_thai_tt(String trang_thai_tt) { 
     this.trang_thai_tt = trang_thai_tt;
  }

  public void setMa_nguoi_so_huu(String ma_nguoi_so_huu) { 
     this.ma_nguoi_so_huu = ma_nguoi_so_huu;
  }

  public void setTen_nguoi_so_huu(String ten_nguoi_so_huu) { 
     this.ten_nguoi_so_huu = ten_nguoi_so_huu;
  }

  public String getGuid() { 
     return guid;
  }

  public String getTp_kq_ph_id() { 
     return tp_kq_ph_id;
  }

  public String getStt() { 
     return stt;
  }

  public String getMa_tpcp() { 
     return ma_tpcp;
  }

  public String getThanh_vien_dt() { 
     return thanh_vien_dt;
  }

  public String getSo_tk_tt() { 
     return so_tk_tt;
  }

  public String getKl_trung_thau() { 
     return kl_trung_thau;
  }

  public String getLs_trung_thau() { 
     return ls_trung_thau;
  }

  public String getTien_tt_mua() { 
     return tien_tt_mua;
  }

  public String getTrang_thai_tt() { 
     return trang_thai_tt;
  }

  public String getMa_nguoi_so_huu() { 
     return ma_nguoi_so_huu;
  }

  public String getTen_nguoi_so_huu() { 
     return ten_nguoi_so_huu;
  }

  public String getPageNumber() { 
     return pageNumber;
  }

    public void setThanh_vien_dau_thau(String thanh_vien_dau_thau) {
        this.thanh_vien_dau_thau = thanh_vien_dau_thau;
    }

    public String getThanh_vien_dau_thau() {
        return thanh_vien_dau_thau;
    }
}
