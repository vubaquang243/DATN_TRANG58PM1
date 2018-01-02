package com.seatech.framework.tbao;

import java.util.Calendar;

public class TBaoGenerator {
    private final String cssContent = "h1 {\n" +
        "    font-family: Times New Roman;\n" +
        "    font-size: 12pt;\n" +
        "    font-weight: bold;\n" +
        "    color: #3a9bcc;\n" +
        "    margin-top: 0px;\n" +
        "    margin-bottom: 0px;\n" +
        "}\n" +
        "table {\n" +
        "    border: none;\n" +
        "    font-family: Times New Roman;\n" +
        "    font-size: 10pt;\n" +
        "    page-break-inside:auto;\n" +
        "}\n" +
        "table.grid {\n" +
        "    border: solid;\n" +
        "    border-width: 1px;\n" +
        "    border-collapse: collapse; \n" +
        "    border-color: gray;\n" +
        "    font-family: Times New Roman;\n" +
        "    font-size: 10pt;\n" +
        "}\n" +
        "thead {\n" +
        "    background-color: #f0f0f0;\n" +
        "    font-weight: bold;\n" +
        "}\n" +
        "tr { page-break-inside:avoid; page-break-after:auto }\n" +
        "tr.header {\n" +
        "    background-color: #f0f0f0;\n" +
        "    font-weight: bold;\n" +
        "}\n" +
        "tr.tkhaiheader {\n" +
        "    background-color: #f7f7f7;\n" +
        "    font-weight: bold;\n" +
        "}\n" +
        "a:link {\n" +
        "    color: #000000;\n" +
        "    text-decoration: none;\n" +
        "}\n" +
        "a:visited {\n" +
        "    color: #000000;\n" +
        "    text-decoration: none;\n" +
        "}\n" +
        "a:hover {\n" +
        "    color:#0000FF;\n" +
        "    text-decoration: underline;\n" +
        "}\n" +
        "a:active {\n" +
        "    color: #000000;\n" +
        "    text-decoration: none;\n" +
        "}\n" +
        "input {\n" +
        "    font-family: Times New Roman;\n" +
        "    font-size: 10pt;\n" +
        "}\n" +
        "input.button {\n" +
        "    font-family: Arial;\n" +
        "    font-size: 10pt;\n" +
        "    padding: 1px;\n" +
        "}\n" +
        "input.date {\n" +
        "    text-align: center; \n" +
        "    font-family: Times New Roman; \n" +
        "    font-size: 10pt\n" +
        "}\n" +
        "input.number {\n" +
        "    text-align: right; \n" +
        "    font-family: Times New Roman; \n" +
        "    font-size:10pt\n" +
        "}\n" +
        "input.numberflat {\n" +
        "    text-align: right;\n" +
        "    font-family: Times New Roman; \n" +
        "    font-size:10pt;\n" +
        "    border: none;\n" +
        "}\n" +
        "input.textflat {\n" +
        "    font-family: Times New Roman; \n" +
        "    font-size:10pt;\n" +
        "    border: none;\n" +
        "}\n" +
        "input.dateflat {\n" +
        "    text-align: center;\n" +
        "    font-family: Times New Roman; \n" +
        "    font-size:10pt;\n" +
        "    border: none;\n" +
        "}\n" +
        "select {\n" +
        "    font-family: Times New Roman;\n" +
        "    font-size: 10pt;\n" +
        "}\n" +
        "option {\n" +
        "    font-family: Times New Roman;\n" +
        "    font-size: 10pt;\n" +
        "}\n" +
        "textarea {\n" +
        "    font-family: Times New Roman;\n" +
        "    font-size: 10pt;\n" +
        "}\n" +
        "font.qhieutkhai {\n" +
        "    font-family: Times New Roman;\n" +
        "    font-size: 12pt;\n" +
        "}\n" +
        "font.tentkhai {\n" +
        "    font-family: Times New Roman;\n" +
        "    font-size: 12pt;\n" +
        "    font-weight: bold;\n" +
        "}";
    private String soTBao;

    public TBaoGenerator() {
    }

    
    
  private String getTBaoHeaderHTML(String tenTBao,String thongBao) throws Exception {
      Calendar d = Calendar.getInstance();
      String dd = Integer.toString(d.get(Calendar.DATE));
      String mm = Integer.toString(d.get(Calendar.MONTH) + 1);
      String yyyy = Integer.toString(d.get(Calendar.YEAR));
      String hh = Integer.toString(d.get(Calendar.HOUR_OF_DAY));
      String mi = Integer.toString(d.get(Calendar.MINUTE));
      String ss = Integer.toString(d.get(Calendar.SECOND));
      String ms = Integer.toString(d.get(Calendar.MILLISECOND));
      if (dd.length() == 1) {
          dd = "0" + dd;
      }
      if (mm.length() == 1) {
          mm = "0" + mm;
      }
      if (hh.length() == 1) {
          hh = "0" + hh;
      }
      if (mi.length() == 1) {
          mi = "0" + mi;
      }
      if (ss.length() == 1) {
          ss = "0" + ss;
      }
      ms = (ms.length() == 1 ? "00" + ms : ms.length() == 2 ? "0" + ms : ms);
      soTBao = mm + dd + hh + mi + ss + ms + "/" + yyyy + "/TB-KBNN";

      return "<table border='0' cellpadding='5' cellspacing='0' style='font-family: Times New Roman; font-size:12pt'>\n" +
          " <tr>\n" +
          "  <td>\n" +
          "   <table width='100%' border='0' cellpadding='0' cellspacing='0' style='font-family: Times New Roman; font-size:12pt'>\n" +
          "    <tr>\n" +
          "     <td align='left'>\n" +
          "      <table border='0' cellpadding='0' cellspacing='0' style='font-size:12pt'>\n" +
        
          "      </table>\n" +
          "     </td>\n" +
          "     <td align='center'>\n" +
          "      <table border='0' cellpadding='0' cellspacing='0' style='font-family: Times New Roman; font-size:12pt'>\n" +
      
          "      </table>\n" +
          "     </td>\n" +
          "    </tr>\n" +
          "   </table>\n" +
          "   <br>\n" +
          "   <br>\n" +
          "   <br>\n" +
          "  </td>\n" +
          " </tr>\n" +
          " <tr>\n" +
          "  <td align='center'><b>"+thongBao+"<BR>" + tenTBao +
          "</b></td>\n" +
          " </tr>\n<BR>" +
          " <tr>\n" +
          "  <td>\n";
  }
    private String getTBaoFooter() {
        return "  </td>\n" +
            " </tr>\n" +
            "</table>\n";
    }
    public String getSoThongBao(){
      return soTBao;
    }
      
  public String getTBaoHTMLContent(String tenTBao,String thongBao, 
                                   String tbaoHTMLContent) throws Exception {
      return getTBaoHeaderHTML(tenTBao,thongBao) + tbaoHTMLContent +
          getTBaoFooter();
  }  
}
