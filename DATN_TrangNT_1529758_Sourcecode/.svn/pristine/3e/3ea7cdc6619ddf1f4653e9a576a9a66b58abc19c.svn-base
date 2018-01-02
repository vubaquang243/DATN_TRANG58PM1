package com.seatech.tp.qlykqphtinphieu.action;

import com.google.gson.Gson;

import com.seatech.framework.exception.TPCPException;
import com.seatech.framework.strustx.AppAction;
import com.seatech.framework.utils.StringUtil;
import com.seatech.tp.banletraiphieutw.action.BanLeTraiPhieuTwDelegate;
import com.seatech.tp.banletraiphieutw.form.BanLeTraiPhieuTwForm;
import com.seatech.tp.banletraiphieutw.vo.BanLeTraiPhieuTwVO;
import com.seatech.tp.qlykqphtinphieu.form.KQPHTinPhieuForm;
import com.seatech.tp.qlykqphtinphieu.vo.KQPHTinPhieuVo;
import com.seatech.tp.qlytp.action.QuanLyTPCPDelegate;
import com.seatech.tp.qlytp.vo.QuanLyTPCPVO;

import java.io.PrintWriter;

import java.sql.Connection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class getAjaxSoTBAction extends AppAction{
    public getAjaxSoTBAction() {
        super();
    }
  public ActionForward executeAction(ActionMapping mapping,
                                  ActionForm form, 
                                  HttpServletRequest request, 
                                  HttpServletResponse response)throws Exception{
    Connection conn=null;
    try{
      conn = getConnection();
      String so_tb_de_nghi_ph =(String) request.getParameter("so_tb_de_nghi_ph");

      KQPHTinPhieuForm f = (KQPHTinPhieuForm)form;
      KQPHTinPhieuVo vo = new KQPHTinPhieuVo();  
      KQPHTinPhieuDelegate delegate1 = new KQPHTinPhieuDelegate(conn);
    
      Map<String, Object> mapStb = new HashMap();
      mapStb.put("so_tb_de_nghi_ph", so_tb_de_nghi_ph);
      vo = delegate1.getQLyKQPhatHanhTinPhieuObject(mapStb);
      response.setContentType("text/text;charset=utf-8");
      response.setHeader("cache-control", "no-cache");
      PrintWriter out = response.getWriter();
      Gson gson = new Gson();
      out.println(gson.toJson(vo));
      out.flush();
    }catch(Exception ex){
        conn.rollback();
      throw ex;
      }finally{
        close(conn);
      }
                   
    return null;
  }
}
