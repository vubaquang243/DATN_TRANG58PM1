package com.seatech.tp.kqphathanh.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class QLyKQPhatHanhCTiet_TpcpForm extends ActionForm{
  private String guid;

  private String tp_kq_ph_id;

  private String ma_tpcp;

  private String ky_han;

  private String ngay_ph;

  private String ngay_dao_han;

  private String ls_trung_thau;

  private String ls_danh_nghia;

  private String klph;

  private String klph_them;

  private String tong_klph;

  private String pageNumber;
  
  private String stt;
  

  public QLyKQPhatHanhCTiet_TpcpForm(){
     this.guid="";
     this.tp_kq_ph_id="";
     this.ma_tpcp="";
     this.ky_han="";
     this.ngay_ph="";
     this.ngay_dao_han="";
     this.ls_trung_thau="";
     this.ls_danh_nghia="";
     this.klph="";
     this.klph_them="";
     this.tong_klph="";
     this.stt="";
  }
  
  @Override
  public void reset(ActionMapping mapping, HttpServletRequest request) {
     super.reset(mapping, request);
     this.guid="";
     this.tp_kq_ph_id="";
     this.ma_tpcp="";
     this.ky_han="";
     this.ngay_ph="";
     this.ngay_dao_han="";
     this.ls_trung_thau="";
     this.ls_danh_nghia="";
     this.klph="";
     this.klph_them="";
     this.tong_klph="";
     this.stt="";
  }
  public void setGuid(String guid) { 
     this.guid = guid;
  }

  public void setTp_kq_ph_id(String tp_kq_ph_id) { 
     this.tp_kq_ph_id = tp_kq_ph_id;
  }

  public void setMa_tpcp(String ma_tpcp) { 
     this.ma_tpcp = ma_tpcp;
  }

  public void setKy_han(String ky_han) { 
     this.ky_han = ky_han;
  }

  public void setNgay_ph(String ngay_ph) { 
     this.ngay_ph = ngay_ph;
  }

  public void setNgay_dao_han(String ngay_dao_han) { 
     this.ngay_dao_han = ngay_dao_han;
  }

  public void setLs_trung_thau(String ls_trung_thau) { 
     this.ls_trung_thau = ls_trung_thau;
  }

  public void setLs_danh_nghia(String ls_danh_nghia) { 
     this.ls_danh_nghia = ls_danh_nghia;
  }

  public void setKlph(String klph) { 
     this.klph = klph;
  }

  public void setKlph_them(String klph_them) { 
     this.klph_them = klph_them;
  }

  public void setTong_klph(String tong_klph) { 
     this.tong_klph = tong_klph;
  }

  public String getGuid() { 
     return guid;
  }

  public String getTp_kq_ph_id() { 
     return tp_kq_ph_id;
  }

  public String getMa_tpcp() { 
     return ma_tpcp;
  }

  public String getKy_han() { 
     return ky_han;
  }

  public String getNgay_ph() { 
     return ngay_ph;
  }

  public String getNgay_dao_han() { 
     return ngay_dao_han;
  }

  public String getLs_trung_thau() { 
     return ls_trung_thau;
  }

  public String getLs_danh_nghia() { 
     return ls_danh_nghia;
  }

  public String getKlph() { 
     return klph;
  }

  public String getKlph_them() { 
     return klph_them;
  }

  public String getTong_klph() { 
     return tong_klph;
  }

  public String getPageNumber() { 
     return pageNumber;
  }

    public void setStt(String stt) {
        this.stt = stt;
    }

    public String getStt() {
        return stt;
    }
}
