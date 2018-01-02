package com.seatech.tp.banletraiphieutw.action;

import com.google.gson.Gson;

import com.seatech.framework.strustx.AppAction;
import com.seatech.framework.utils.StringUtil;
import com.seatech.tp.banletraiphieutw.form.BanLeTraiPhieuTwForm;
import com.seatech.tp.banletraiphieutw.vo.BanLeTraiPhieuTwVO;

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

public class getAjaxBanLeAction extends AppAction{
    public getAjaxBanLeAction() {
        super();
    }
  public ActionForward executeAction(ActionMapping mapping,
                                  ActionForm form, 
                                  HttpServletRequest request, 
                                  HttpServletResponse response)throws Exception{
    Connection conn=null;
    try{
      conn = getConnection();
      String ma_tp =(String) request.getParameter("longid");

      BanLeTraiPhieuTwForm f= (BanLeTraiPhieuTwForm) form;
      
      BanLeTraiPhieuTwDelegate delegate = new BanLeTraiPhieuTwDelegate(conn);
 //     QuanLyTPCPDelegate delegateTP = new QuanLyTPCPDelegate(conn);
      Map<String,Object> map = new HashMap<String,Object>();
      map.put("DOT_PH", ma_tp);
      BanLeTraiPhieuTwVO vo= new   BanLeTraiPhieuTwVO();
        if("".equals(ma_tp)){
          vo = null;
        }else {
      ArrayList array = new ArrayList();
      array = (ArrayList)delegate.getAllBanLe(map);
    
      
      BeanUtils.copyProperties(vo, f);
      if(array.size()==1){
        vo = (BanLeTraiPhieuTwVO)array.get(0);
        vo.setLai_suat(StringUtil.convertNumberToString(vo.getLai_suat(), "VND"));
        vo.setMenh_gia(StringUtil.convertNumberToString(vo.getMenh_gia(),
                                                          "VND"));
        vo.setAllowEdit("true");
      }else if(array.size()>1){
        vo = (BanLeTraiPhieuTwVO)array.get(0);
        vo.setLai_suat(StringUtil.convertNumberToString(vo.getLai_suat(), "VND"));
        vo.setMenh_gia(StringUtil.convertNumberToString(vo.getMenh_gia(),
                                                          "VND"));
        vo.setAllowEdit("false");
      }
      else{
        QuanLyTPCPDelegate delegateTP = new QuanLyTPCPDelegate(conn);
        Map<String, Object> mapTP = new HashMap();
        mapTP.put("MA_TP", ma_tp);
        QuanLyTPCPVO tpcpVo = delegateTP.getTTDTObject(mapTP);
        vo.setKy_han(tpcpVo.getKy_han());
        vo.setKy_tt_lai("");
          vo=null;
      }
        }
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
