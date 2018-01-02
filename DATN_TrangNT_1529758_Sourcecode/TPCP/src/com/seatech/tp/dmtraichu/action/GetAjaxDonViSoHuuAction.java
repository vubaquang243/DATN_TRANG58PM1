package com.seatech.tp.dmtraichu.action;

import com.google.gson.Gson;

import com.seatech.framework.strustx.AppAction;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class GetAjaxDonViSoHuuAction extends AppAction {
    private static String SUCCESS = "success";
    private static String FAILURE = "failure";
    public GetAjaxDonViSoHuuAction() {
        super();
    }
  public ActionForward executeAction(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
      Connection conn = null;
      String errMess = "";
      try {
          conn = getConnection();        
          String ten_dvi_so_huu = request.getParameter("ten_dvi_so_huu");
          Map<String, Object> map =new HashMap();
          if(ten_dvi_so_huu!=null){
            map.put("TEN_DVI_SO_HUU", ten_dvi_so_huu.trim());
          }
          DMTraiChuDelegate dmtraiChuDelegate =new DMTraiChuDelegate(conn);
          Collection listDonViTT=dmtraiChuDelegate.getDMTraiChu(map);         
          response.setContentType("text/text;charset=utf-8");
          response.setHeader("cache-control", "no-cache");
          PrintWriter out = response.getWriter();
          Gson gson = new Gson();
          out.println(gson.toJson(listDonViTT));
          out.flush();
      } catch (Exception e) {
          conn.rollback();
          throw e;
      } finally {
          close(conn);

      }
      return null;
  }
}
