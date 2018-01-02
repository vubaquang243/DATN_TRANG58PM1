package com.seatech.tp.tiente.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class TienTeForm extends ActionForm {
  private String id;

  private String ma;

  private String ten;

  private String ten_ta;

  private String ten_qg;

  private String ty_gia;

  private String ma_dang_so;

  private String ghi_chu;

  private String tinh_trang;

  private String don_vi_nguyen;

  private String don_vi_thap_phan;

  private String pageNumber;

  public TienTeForm(){
     this.id="";
     this.ma="";
     this.ten="";
     this.ten_ta="";
     this.ten_qg="";
     this.ty_gia="";
     this.ma_dang_so="";
     this.ghi_chu="";
     this.tinh_trang="";
     this.don_vi_nguyen="";
     this.don_vi_thap_phan="";
  }
  
  @Override
  public void reset(ActionMapping mapping, HttpServletRequest request) {
      super.reset(mapping, request);
    this.id="";
    this.ma="";
    this.ten="";
    this.ten_ta="";
    this.ten_qg="";
    this.ty_gia="";
    this.ma_dang_so="";
    this.ghi_chu="";
    this.tinh_trang="";
    this.don_vi_nguyen="";
    this.don_vi_thap_phan="";
      
  }
  public void setId(String id) { 
     this.id = id;
  }

  public void setMa(String ma) { 
     this.ma = ma;
  }

  public void setTen(String ten) { 
     this.ten = ten;
  }

  public void setTen_ta(String ten_ta) { 
     this.ten_ta = ten_ta;
  }

  public void setTen_qg(String ten_qg) { 
     this.ten_qg = ten_qg;
  }

  public void setTy_gia(String ty_gia) { 
     this.ty_gia = ty_gia;
  }

  public void setMa_dang_so(String ma_dang_so) { 
     this.ma_dang_so = ma_dang_so;
  }

  public void setGhi_chu(String ghi_chu) { 
     this.ghi_chu = ghi_chu;
  }

  public void setTinh_trang(String tinh_trang) { 
     this.tinh_trang = tinh_trang;
  }

  public void setDon_vi_nguyen(String don_vi_nguyen) { 
     this.don_vi_nguyen = don_vi_nguyen;
  }

  public void setDon_vi_thap_phan(String don_vi_thap_phan) { 
     this.don_vi_thap_phan = don_vi_thap_phan;
  }

  public String getId() { 
     return id;
  }

  public String getMa() { 
     return ma;
  }

  public String getTen() { 
     return ten;
  }

  public String getTen_ta() { 
     return ten_ta;
  }

  public String getTen_qg() { 
     return ten_qg;
  }

  public String getTy_gia() { 
     return ty_gia;
  }

  public String getMa_dang_so() { 
     return ma_dang_so;
  }

  public String getGhi_chu() { 
     return ghi_chu;
  }

  public String getTinh_trang() { 
     return tinh_trang;
  }

  public String getDon_vi_nguyen() { 
     return don_vi_nguyen;
  }

  public String getDon_vi_thap_phan() { 
     return don_vi_thap_phan;
  }

  public String getPageNumber() { 
     return pageNumber;
  }

}
