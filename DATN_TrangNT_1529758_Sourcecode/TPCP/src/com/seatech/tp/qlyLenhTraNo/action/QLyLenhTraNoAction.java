package com.seatech.tp.qlyLenhTraNo.action;

import com.seatech.framework.AppConstants;
import com.seatech.framework.common.jsp.PagingBean;
import com.seatech.framework.datamanager.ReportUtility;
import com.seatech.framework.exception.TPCPException;
import com.seatech.framework.strustx.AppAction;
import com.seatech.framework.utils.StringUtil;
import com.seatech.tp.dmdonvitt.action.DMDonViTTDelegate;
import com.seatech.tp.kqphathanh.action.QLyKQPhatHanhDelegate;
import com.seatech.tp.kqphathanh.form.QLyKQPhatHanhForm;
import com.seatech.tp.kqphathanh.vo.QLyKQPhatHanhVO;

import com.seatech.tp.qlyLenhTraNo.form.LenhTraNoForm;

import com.seatech.tp.qlykehoach.action.QuanLyKeHoachDelegate;
import com.seatech.tp.qlykehoach.form.QuanLyKeHoachForm;
import com.seatech.tp.qlykehoach.vo.QuanLyKeHoachVo;
import com.seatech.tp.sotonghoptpcp.action.SoTongHopTpcpDelegate;
import com.seatech.tp.sotonghoptpcp.form.SoTongHopTpcpForm;
import com.seatech.tp.sotonghoptpcp.vo.SoTongHopTpcpVO;
import com.seatech.tp.ttlaigoc.vo.TPLenhTriTraNoCTVo;
import com.seatech.tp.ttlaigoc.vo.TPLenhTriTraNoVo;

import com.seatech.tp.user.UserHistoryVO;

import java.io.InputStream;

import java.sql.Connection;

import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class QLyLenhTraNoAction extends AppAction {
    private static String SUCCESS = "success";
    private static String FAILURE = "failure";

    public QLyLenhTraNoAction() {
        super();
    }

    public ActionForward list(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        Connection conn = null;
        try {
            LenhTraNoForm f = (LenhTraNoForm)form;
            f.reset(mapping, request);
            return search(mapping, form, request, response);
        } catch (Exception e) {
            conn.rollback();
            throw e;
        } finally {
            close(conn);
        }
    }

    public ActionForward search(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
        //check quyen

        Connection conn = null;
        try {
            conn = getConnection(request);
            LenhTraNoForm f = (LenhTraNoForm)form;
            TPLenhTriTraNoVo vo = new TPLenhTriTraNoVo();
            BeanUtils.copyProperties(vo, f);
            int phantrang = (AppConstants.APP_NUMBER_ROW_ON_PAGE);
            // khai bao bien phan trang.
            String page = f.getPageNumber();
            if (page == null)
                page = "1";
            Integer currentPage = new Integer(page);
            Integer numberRowOnPage = phantrang;
            Integer totalCount[] = new Integer[1];

            Collection listLenhTriTraNo = new ArrayList();
            QLyLenhTraNoDelegate delegate = new QLyLenhTraNoDelegate(conn);
            listLenhTriTraNo =
                    delegate.getListTTDTPaging(vo, currentPage, numberRowOnPage,
                                               totalCount);

            PagingBean pagingBean = new PagingBean();
            pagingBean.setCurrentPage(currentPage);
            pagingBean.setNumberOfRow((totalCount[0] == null) ? 0 :
                                      totalCount[0].intValue());
            pagingBean.setRowOnPage(numberRowOnPage);
            request.setAttribute("PAGE_KEY", pagingBean);
            request.setAttribute("listLenhTriTraNo", listLenhTriTraNo);
            //get list dvi_nhan
            getInit(conn, request);
        } catch (Exception e) {
            conn.rollback();
            throw e;
        } finally {
            close(conn);
        }
        return mapping.findForward(SUCCESS);
    }

    public ActionForward view(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        Connection conn = null;
        try {
            conn = getConnection();
            String idLenh = request.getParameter("longid").trim();
            LenhTraNoForm f = (LenhTraNoForm)form;
            //
            TPLenhTriTraNoVo vo = new TPLenhTriTraNoVo();
            QLyLenhTraNoDelegate delegate = new QLyLenhTraNoDelegate(conn);
            vo = delegate.getListLenhTriTraNoCT(idLenh);
            BeanUtils.copyProperties(f, vo);
            //get list dvi_nhan
            getInit(conn, request);
        } catch (Exception e) {
            throw e;
        } finally {
            close(conn);
        }
        return mapping.findForward(SUCCESS);
    }

    public ActionForward update(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
        Connection conn = null;
        String errMess = "";
        try {
            conn = getConnection();
            LenhTraNoForm f = (LenhTraNoForm)form;
            TPLenhTriTraNoVo vo = new TPLenhTriTraNoVo();
            BeanUtils.copyProperties(vo, f);
            
            //get list chi tiet
            String[] guid_ct=request.getParameterValues("guid_ct");
            String[] chuong=request.getParameterValues("chuong");
            String[] nganh_kt=request.getParameterValues("nganh_kt");
            String[] ctmt=request.getParameterValues("ctmt");
            String[] nguon=request.getParameterValues("nguon");
            String[] ndkt=request.getParameterValues("ndkt");
            
            Collection lenhCT=new ArrayList();
            if(guid_ct!=null && guid_ct.length >0){
                for(int i=0;i<guid_ct.length;i++){
                    TPLenhTriTraNoCTVo voCT=new TPLenhTriTraNoCTVo();
                    voCT.setGuid(guid_ct[i]);
                    voCT.setChuong(chuong[i]);
                    voCT.setNganh_kt(nganh_kt[i]);
                    voCT.setCtmt(ctmt[i]);
                    voCT.setNguon(nguon[i]);
                    voCT.setNdkt(ndkt[i]);
                    lenhCT.add(voCT);
                }
            }
            vo.setList_Lenh_tra_no_ct(lenhCT);
            QLyLenhTraNoDelegate delegate = new QLyLenhTraNoDelegate(conn);
            long idAdd = delegate.update(vo);
            //insert history
            HttpSession session = request.getSession();
            String nUserID =session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString();
            UserHistoryVO userHisVO = new UserHistoryVO();
            userHisVO.setNguoi_tdoi(new Long(nUserID));
            userHisVO.setNoi_dung_thaydoi("Ghi Lệnh tri trả nợ " + idAdd);
            userHisVO.setNsd_id(idAdd);
            delegate.insertHistoryUser(userHisVO);
            f.reset(mapping, request);
            if (idAdd != 0) {
                errMess = "thanhtoanlaigoc.update.succ";
            } else
                errMess = "thanhtoanlaigoc.update.error";
            saveMessage(request, new TPCPException(errMess));
        } catch (Exception e) {
            throw e;
        } finally {
            close(conn);
        }
        return mapping.findForward(SUCCESS);
    }
    
  public ActionForward delete(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
      Connection conn = null;
      String errMess = "";
      try {
          conn = getConnection();
          QLyLenhTraNoDelegate delegate = new QLyLenhTraNoDelegate(conn);
          LenhTraNoForm f = (LenhTraNoForm)form;
          TPLenhTriTraNoVo vo = new TPLenhTriTraNoVo();
          if("".equals(f.getGuid())){
            String guid = (String)request.getParameter("longid");
            String loaitt = (String)request.getParameter("loaitt");
            String solenh = (String)request.getParameter("solenh");
            vo.setGuid(guid);
            vo.setLoai_thanh_toan(loaitt);
            vo.setSo_lenh(solenh);
          }else{
            BeanUtils.copyProperties(vo, f);
          }
          long idAdd = delegate.delete(vo);
          //insert history
          HttpSession session = request.getSession();
          String nUserID =session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString();
          UserHistoryVO userHisVO = new UserHistoryVO();
          userHisVO.setNguoi_tdoi(new Long(nUserID));
          userHisVO.setNoi_dung_thaydoi("Hủy Lệnh tri trả nợ " + idAdd);
          userHisVO.setNsd_id(idAdd);
          delegate.insertHistoryUser(userHisVO);
          f.reset(mapping, request);
          //get list dvi_nhan
          getInit(conn, request);
          if (idAdd != 0) {
              errMess = "thanhtoanlaigoc.delete.succ";
          } else
              errMess = "thanhtoanlaigoc.delete.error";
          saveMessage(request, new TPCPException(errMess));
      } catch (Exception e) {
          throw e;
      } finally {
          close(conn);
      }
      return mapping.findForward(SUCCESS);
  }
  
  public static final String REPORT_DIRECTORY = "/report";
  public static final String strFontTimeRoman = "/font/times.ttf";
  public static final String fileName = "/LenhChiTraNoVnd";
  public ActionForward printAction(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response) throws Exception {
      String reportName = "/LenhChiTraNoVnd";
      Connection conn = null;
      InputStream reportStream = null;
      StringBuffer sbSubHTML = new StringBuffer();
      
      try {
          conn = getConnection();
          LenhTraNoForm f = (LenhTraNoForm)form;
          TPLenhTriTraNoVo vo = new TPLenhTriTraNoVo();
          QLyLenhTraNoDelegate delegate = new QLyLenhTraNoDelegate(conn);
          BeanUtils.copyProperties(vo, f);

          sbSubHTML.append("<input type=\"hidden\" name=\"guid\" value=\"" +
                           vo.getGuid() +
                           "\" id=\"guid\"></input>");
          sbSubHTML.append("<input type=\"hidden\" name=\"nam_ns\" value=\"" +
                           f.getNam_ns() +
                           "\" id=\"nam_ns\"></input>");
          sbSubHTML.append("<input type=\"hidden\" name=\"ngay_den_han_tt\" value=\"" +
                           f.getNgay_den_han_tt() +
                           "\" id=\"ngay_den_han_tt\"></input>");
          sbSubHTML.append("<input type=\"hidden\" name=\"can_cu\" value=\"" +
                           f.getCan_cu() +
                           "\" id=\"can_cu\"></input>");
          sbSubHTML.append("<input type=\"hidden\" name=\"dvi_nhan\" value=\"" +
                           f.getDvi_nhan() +
                           "\" id=\"dvi_nhan\"></input>");
        sbSubHTML.append("<input type=\"hidden\" name=\"ten_dvi_nhan\" value=\"" +
                         f.getTen_dvi_nhan() +
                         "\" id=\"ten_dvi_nhan\"></input>");
          sbSubHTML.append("<input type=\"hidden\" name=\"so_tk_nhan\" value=\"" +
                           f.getSo_tk_nhan() +
                           "\" id=\"so_tk_nhan\"></input>");
          sbSubHTML.append("<input type=\"hidden\" name=\"tong_tien_chu\" value=\"" +
                         f.getTong_tien_chu() +
                         "\" id=\"tong_tien_chu\"></input>");

          ResultSet rsNSD = null;
          rsNSD = delegate.getLenhChiTraNoCTList(vo.getGuid());
          if (rsNSD != null) {
              JasperPrint jasperPrint = null;
              Map<String, Object> parameterMap = new HashMap<String, Object>();
              parameterMap.put("pNamNS", f.getNam_ns());
              String[] ngay_den_han_tt=f.getNgay_den_han_tt().split("/");
              if(ngay_den_han_tt==null||ngay_den_han_tt.length<3){
                ngay_den_han_tt=new String[3];
                ngay_den_han_tt[0]="";
                ngay_den_han_tt[1]="";
                ngay_den_han_tt[2]="";
              }
              parameterMap.put("pNgayTT",ngay_den_han_tt[0]+ "");
              parameterMap.put("pThangTT",ngay_den_han_tt[1]+"");
              parameterMap.put("pNamTT",ngay_den_han_tt[2]+"");
              parameterMap.put("pCanCu", f.getCan_cu()+"");
              parameterMap.put("pDonviNhanTien", f.getTen_dvi_nhan()+"");
              parameterMap.put("pTKSo", f.getSo_tk_nhan()+"");
              parameterMap.put("pNgayChuyen", f.getNgay_chuyen()+"");
              parameterMap.put("pNganHang", f.getNh_nhan()+"");
              parameterMap.put("pTongTienChu", f.getTong_tien_chu()+"");
              parameterMap.put("nam_ns", f.getNam_ns()+"");
              parameterMap.put("so_lenh", f.getSo_lenh()+"");
              reportStream =
                      getServlet().getServletConfig().getServletContext().getResourceAsStream(REPORT_DIRECTORY +
                                                                                              reportName +
                                                                                              ".jasper");
              JRDataSource jrDS = new JRResultSetDataSource(rsNSD);
              jasperPrint = JasperFillManager.fillReport(reportStream, parameterMap,jrDS);

              ReportUtility rpUtilites = new ReportUtility();

              String strTypePrintAction =
                  request.getParameter(AppConstants.REQUEST_ACTION) == null ?
                  "" :
                  request.getParameter(AppConstants.REQUEST_ACTION).toString();
              String strActionName = "PrintLenhChiTraNoAction.do";
              String strParameter = "";
              String strPathFont =
                  getServlet().getServletContext().getContextPath() +
                  REPORT_DIRECTORY + strFontTimeRoman;

              rpUtilites.exportReport(jasperPrint, strTypePrintAction,
                                      response, fileName, strPathFont,
                                      strActionName, sbSubHTML.toString(),
                                      strParameter);
          }
        //get list dvi_nhan
        getInit(conn, request);
      } catch (Exception e) {
          throw e;
      } finally {
          try {
              reportStream.close();
          } catch (Exception e) {
              e.printStackTrace();
          }
          close(conn);
      }
      
      return mapping.findForward(SUCCESS);
  }
  public void getInit(Connection conn,HttpServletRequest request) throws Exception {
    //get list dvi_nhan
    Map<String, Object> map =new HashMap();
    DMDonViTTDelegate dMDonViTTDelegate =new DMDonViTTDelegate(conn);
    Collection listDonViTT=dMDonViTTDelegate.getListDmDonViTT(map);
    request.setAttribute("listDonViTT", listDonViTT);
  }
}
