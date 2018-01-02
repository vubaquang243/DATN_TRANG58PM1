package com.seatech.tp.qlyLenhTraNo.action;

import com.google.gson.Gson;

import com.seatech.framework.strustx.AppAction;
import com.seatech.tp.dmdonvitt.action.DMDonViTTDelegate;
import com.seatech.tp.dmdonvitt.vo.DMDonViTTVo;
import com.seatech.tp.qlytp.action.QuanLyTPCPDelegate;
import com.seatech.tp.qlytp.vo.QuanLyTPCPVO;
import com.seatech.tp.ttindthau.action.GetAjaxKyHanAction;

import java.io.PrintWriter;

import java.sql.Connection;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class GetAjaxDonViNhanAction extends AppAction {
    private static String SUCCESS = "success";
    private static String FAILURE = "failure";
    public GetAjaxDonViNhanAction() {
        super();
    }
  public ActionForward view(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
      Connection conn = null;
      String errMess = "";
      try {
          conn = getConnection();
          String ma = request.getParameter("longid").trim();
          String stk = request.getParameter("so_tk_nhan");
          Map<String, Object> map =new HashMap();
          map.put("MA", ma);
          if(stk!=null){
            map.put("SO_TK", stk.trim());
          }
          DMDonViTTDelegate dMDonViTTDelegate =new DMDonViTTDelegate(conn);
          Collection listDonViTT=dMDonViTTDelegate.getListDmDonViTT(map);
          Iterator ito = listDonViTT.iterator();
//          DMDonViTTVo vo=new DMDonViTTVo();
//          while(ito.hasNext()){
//            vo = (DMDonViTTVo)ito.next();
//            break;
//          }
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
