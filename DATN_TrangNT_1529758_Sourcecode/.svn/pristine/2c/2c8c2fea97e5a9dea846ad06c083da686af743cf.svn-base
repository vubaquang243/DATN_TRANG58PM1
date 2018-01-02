package com.seatech.framework.tbao;

import com.seatech.framework.utils.StringUtil;

import com.seatech.tp.thamso.ThamSoHThongDAO;

import java.sql.Connection;

import java.util.Date;

import java.util.Calendar;
import java.util.Collection;

public class ThongBaoTPCP {
    public ThongBaoTPCP() {
        super();
    }
    
    private Connection conn;

    
    
    public void sendThongBao(String emailAddr,
                               String HTMLContent,Collection tsoHeThong,String tbao,String tenTBao) throws Exception {
      
      
      TBaoGenerator tbaoGenerator =
          new TBaoGenerator();
      String emailHTMLContent =
          tbaoGenerator.getTBaoHTMLContent(tenTBao,tbao, HTMLContent);
      byte[] tbaoPDF = null;        
      
      try {
          (new ThongBaoSender()).sendEMailTBao(emailAddr,
                                               emailHTMLContent, tbaoPDF, tsoHeThong);
          saveTBao("1", tbaoGenerator.getSoThongBao(), null, emailHTMLContent, emailAddr);
      } catch (Exception ex) {
          ex.printStackTrace();
      }
      
  }

    public void saveTBao(String id_dau_thau,String soTBao,
                            byte[] tbaoPDFFile, String HTMLContent,
                            String emailAddr) throws Exception {
        String ngayGuiTimeStamp = StringUtil.convertDateToString(Calendar.getInstance(), "DDMMYYYYHH24MISS");
        TBaoDAO tbaoDAO = new TBaoDAO(conn);
        tbaoDAO.insertTBao(id_dau_thau, emailAddr, soTBao, ngayGuiTimeStamp, HTMLContent, tbaoPDFFile);
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }
}
