package com.seatech.tp.ttindthau.action;

import com.google.gson.Gson;

import com.seatech.framework.AppConstants;
import com.seatech.framework.exception.TPCPException;
import com.seatech.framework.strustx.AppAction;
import com.seatech.framework.utils.StringUtil;
import com.seatech.tp.banletraiphieutw.action.BanLeTraiPhieuTwDelegate;
import com.seatech.tp.banletraiphieutw.vo.BanLeTraiPhieuTwVO;
import com.seatech.tp.dmkyhan.action.DMKyHanDelegate;
import com.seatech.tp.dmkyhan.vo.DMKyHanVO;
import com.seatech.tp.kqphathanh.action.QLyKQPhatHanhDelegate;
import com.seatech.tp.kqphathanh.vo.QLyKQPhatHanhCTiet_TpcpVO;
import com.seatech.tp.kqphathanh.vo.QLyKQPhatHanhVO;
import com.seatech.tp.qlytp.action.QuanLyTPCPDelegate;
import com.seatech.tp.qlytp.vo.QuanLyTPCPVO;
import com.seatech.tp.ttindthau.form.ThongTinDauThauForm;
import com.seatech.tp.ttindthau.vo.ThongTinDauThauVO;
import com.seatech.tp.user.UserHistoryVO;

import java.io.PrintWriter;

import java.sql.Connection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import java.util.Iterator;
import java.util.List;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class GetAjaxKyHanAction extends AppAction {
    private static String SUCCESS = "success";
    private static String FAILURE = "failure";
    public GetAjaxKyHanAction() {
        super();
    }
  public ActionForward executeAction(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
      Connection conn = null;
      String errMess = "";
      try {
          conn = getConnection();
          String ma_tp = request.getParameter("longid").trim();
     
          QuanLyTPCPDelegate delegate = new QuanLyTPCPDelegate(conn);
          Map<String, Object> map =new HashMap();
          map.put("MA_TP",ma_tp);
          QuanLyTPCPVO tpcpVo = delegate.getTTDTObject(map);
          response.setContentType("text/text;charset=utf-8");
          response.setHeader("cache-control", "no-cache");
          PrintWriter out = response.getWriter();
          Gson gson = new Gson();
          out.println(gson.toJson(tpcpVo));
          out.flush();
          //request.setAttribute("dmKyHan", tpcpVo.getKy_han());
//          System.out.println("================================="+guid);
      } catch (Exception e) {
          conn.rollback();
          throw e;
      } finally {
          close(conn);

      }
      return null;
  }
  public ActionForward search(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
      //        if (!checkPermissionOnFunction(request, "SYS.QLY_NSD.XOA")) {
      //            return mapping.findForward("errorQuyen");
      //        }
      Connection conn = null;
      String errMess = "";
      try {
          conn = getConnection();
          String dot_dau_thau = request.getParameter("dot_dau_thau").trim();
          if("".equals(dot_dau_thau)){
            dot_dau_thau=" ";
          }
          QLyTTinDauThauDelegate delegate = new QLyTTinDauThauDelegate(conn);
          ThongTinDauThauVO voDThau = null;
          // check dot_phat_hanh
          HashMap<String, Object> map1 = new HashMap<String, Object>();
          map1.put("DOT_DT", dot_dau_thau);  
          QLyKQPhatHanhDelegate dele= new QLyKQPhatHanhDelegate(conn);
          QLyKQPhatHanhVO voph=dele.getQLyKQPhatHanhObject(map1) ;
          if(voph != null){
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("dot_dau_thau", dot_dau_thau);   
            voDThau = delegate.getTTDTObject2(map); 
            // get tong khoi luong da phat hanh
            Map<String,Object> map_matpcp = new HashMap<String,Object>();
            map_matpcp.put("MA_TPCP", voDThau.getMa_tpcp());
            ThongTinDauThauVO voDThau_KL = delegate.getTong_kl_da_goi_thau(map_matpcp);
            // dùng cột KHOI_LUONG_TP lưu tổng kl
            voDThau.setKhoi_luong_tp(StringUtil.convertNumberToString(voDThau_KL.getKhoi_luong_tp(), "VND"));
            
            request.setAttribute("kl_goi_thau_lan_dau", voDThau_KL.getKhoi_luong_tp());
          }
       
          QLyKQPhatHanhCTiet_TpcpVO keHoachPhatHanhVo =
              new QLyKQPhatHanhCTiet_TpcpVO();
          QLyKQPhatHanhDelegate keHoachPhatHanhDelegate =
              new QLyKQPhatHanhDelegate(conn);
  
          keHoachPhatHanhVo =
                  keHoachPhatHanhDelegate.getKQPH_CTIET_TPCP(dot_dau_thau);
          if (keHoachPhatHanhVo != null) {
              voDThau.setLs_danh_nghia(StringUtil.convertNumberToString(keHoachPhatHanhVo.getLs_danh_nghia(), "VND"));
              voDThau.setMenh_gia(StringUtil.convertNumberToString(voDThau.getMenh_gia(), "VND"));
          }
    
          response.setContentType("text/text;charset=utf-8");
          response.setHeader("cache-control", "no-cache");
          PrintWriter out = response.getWriter();
          Gson gson = new Gson();
          out.println(gson.toJson(voDThau));
          out.flush();
          //request.setAttribute("dmKyHan", tpcpVo.getKy_han());
  //          System.out.println("================================="+guid);
      } catch (Exception e) {
          conn.rollback();
          throw e;
      } finally {
          close(conn);

      }
      return null;
  }
  
  public ActionForward viewUpload(ActionMapping mapping,
                                  ActionForm form, 
                                  HttpServletRequest request, 
                                  HttpServletResponse response)throws Exception{
    Connection conn=null;
    try{
      conn = getConnection();
      String ma_tp =(String) request.getParameter("longid");
      BanLeTraiPhieuTwDelegate delegate = new BanLeTraiPhieuTwDelegate(conn);
      Map<String,Object> map = new HashMap<String,Object>();
      map.put("MA_TPCP", ma_tp);
      ArrayList array = new ArrayList();
      array = (ArrayList)delegate.getAllBanLe(map);
    
      BanLeTraiPhieuTwVO vo= new   BanLeTraiPhieuTwVO();
        if(array.size()!=0){
         vo = (BanLeTraiPhieuTwVO)array.get(0);
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
