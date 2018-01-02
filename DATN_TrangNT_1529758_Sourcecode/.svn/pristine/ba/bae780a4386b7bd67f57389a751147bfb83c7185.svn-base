package com.seatech.tp.qlyLenhTraNo.action;

import com.seatech.framework.AppConstants;
import com.seatech.framework.common.jsp.PagingBean;
import com.seatech.framework.datamanager.ReportUtility;
import com.seatech.framework.exception.TPCPException;
import com.seatech.framework.strustx.AppAction;
import com.seatech.framework.utils.StringUtil;
import com.seatech.tp.dmdonvitt.action.DMDonViTTDelegate;
import com.seatech.tp.dmdonvitt.vo.DMDonViTTVo;
import com.seatech.tp.qlyLenhTraNo.form.LenhTraNoForm;
import com.seatech.tp.ttlaigoc.vo.TPLenhTriTraNoCTVo;
import com.seatech.tp.ttlaigoc.vo.TPLenhTriTraNoVo;
import com.seatech.tp.user.UserHistoryVO;

import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
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

public class QLyLenhTraNoUsdAction extends AppAction {
    private static String SUCCESS = "success";
    private static String FAILURE = "failure";

    public QLyLenhTraNoUsdAction() {
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
                    delegate.getListLenhTriTraNoUSDPaging(vo, currentPage, numberRowOnPage,
                                               totalCount);

            PagingBean pagingBean = new PagingBean();
            pagingBean.setCurrentPage(currentPage);
            pagingBean.setNumberOfRow((totalCount[0] == null) ? 0 :
                                      totalCount[0].intValue());
            pagingBean.setRowOnPage(numberRowOnPage);
            request.setAttribute("PAGE_KEY", pagingBean);
            request.setAttribute("listLenhTriTraNo", listLenhTriTraNo);
            Map<String, Object> map =new HashMap();
            DMDonViTTDelegate dMDonViTTDelegate =new DMDonViTTDelegate(conn);
            Collection listDonViTT=dMDonViTTDelegate.getListDmDonViTT(map);
            request.setAttribute("listDonViTT", listDonViTT);
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
            if(vo.getTrang_thai()!=null){
                if(vo.getTrang_thai().equals("00") || vo.getTrang_thai().equals("03")){
                    request.setAttribute("alowEdit", "false");
                }else{
                  request.setAttribute("alowEdit", "true");
                }
            }
            DMDonViTTDelegate dMDonViTTDelegate =new DMDonViTTDelegate(conn);
            Collection listDonViTT=dMDonViTTDelegate.getListDmDonViTTAll();
            request.setAttribute("listDonViTT", listDonViTT);
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
            QLyLenhTraNoDelegate delegate = new QLyLenhTraNoDelegate(conn);
            LenhTraNoForm f = (LenhTraNoForm)form;
            TPLenhTriTraNoVo vo = new TPLenhTriTraNoVo();
            BeanUtils.copyProperties(vo, f);            
            if(!"VND".equals(vo.getLoai_tien())){
                if (vo.getTy_gia() != null && !"".equals(vo.getTy_gia())) {
                    BigDecimal ty_gia = new BigDecimal(vo.getTy_gia().replace("\\.","").replace(",", "."));
                    BigDecimal so_tien_ngte = new BigDecimal(vo.getSo_tien_ngte().replace("\\.","").replace(",", "."));
                    String so_tien_vnd =so_tien_ngte.multiply(ty_gia).toString();
                    vo.setSo_tien_vnd(so_tien_vnd);
                }
            }
            Collection listCTLaiGoc=null;
            listCTLaiGoc=delegate.getLenhChiTraNoCTList2(f.getGuid());
            
            if(listCTLaiGoc!=null){
                Iterator ito = listCTLaiGoc.iterator();
                while(ito.hasNext()){
                    TPLenhTriTraNoCTVo ctChiTraNo = (TPLenhTriTraNoCTVo)ito.next();
                    if(ctChiTraNo!=null){
                      if (vo.getTy_gia() != null && !"".equals(vo.getTy_gia())) {
                        BigDecimal ty_gia = new BigDecimal(vo.getTy_gia().replace("\\.","").replace(",", "."));
                        BigDecimal so_tien_ngte = new BigDecimal(ctChiTraNo.getSo_tien_ngte());
                        ctChiTraNo.setSo_tien_vnd(so_tien_ngte.multiply(ty_gia).toString());
                      }
                    }
                }     
            }
            vo.setList_Lenh_tra_no_ct(listCTLaiGoc);
            
            long idAdd = delegate.update(vo);
            //insert history
            HttpSession session = request.getSession();
            String nUserID = session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString();
            UserHistoryVO userHisVO = new UserHistoryVO();
            userHisVO.setNguoi_tdoi(new Long(nUserID));
            userHisVO.setNoi_dung_thaydoi("Ghi Lệnh tri trả nợ ngoại tệ" + idAdd);
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
            QLyLenhTraNoDelegate delegate = new QLyLenhTraNoDelegate(conn);
            long idAdd = delegate.delete(vo);
            //insert history
            HttpSession session = request.getSession();
            String nUserID =
                session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString();
            UserHistoryVO userHisVO = new UserHistoryVO();
            userHisVO.setNguoi_tdoi(new Long(nUserID));
            userHisVO.setNoi_dung_thaydoi("Hủy Lệnh tri trả nợ ngoại tệ " + idAdd);
            userHisVO.setNsd_id(idAdd);
            delegate.insertHistoryUser(userHisVO);
            f.reset(mapping, request);
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
  public static final String fileName = "/LenhChiTraNoUsd";
  public ActionForward printAction(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response) throws Exception {
      String reportName = "/LenhChiTraNoUsd";
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
          sbSubHTML.append("<input type=\"hidden\" name=\"so_lenh\" value=\"" +
                         f.getNam_ns() +
                         "\" id=\"so_lenh\"></input>");
          sbSubHTML.append("<input type=\"hidden\" name=\"ngay_den_han_tt\" value=\"" +
                           f.getNgay_den_han_tt() +
                           "\" id=\"ngay_den_han_tt\"></input>");
          sbSubHTML.append("<input type=\"hidden\" name=\"can_cu\" value=\"" +
                           f.getCan_cu() +
                           "\" id=\"can_cu\"></input>");
          sbSubHTML.append("<input type=\"hidden\" name=\"dvi_nhan\" value=\"" +
                           f.getDvi_nhan() +
                           "\" id=\"dvi_nhan\"></input>");
          sbSubHTML.append("<input type=\"hidden\" name=\"so_tk_nhan\" value=\"" +
                           f.getSo_tk_nhan() +
                           "\" id=\"so_tk_nhan\"></input>");
          sbSubHTML.append("<input type=\"hidden\" name=\"tong_tien_chu\" value=\"" +
                         f.getTong_tien_chu() +
                         "\" id=\"tong_tien_chu\"></input>");
          sbSubHTML.append("<input type=\"hidden\" name=\"tong_tien_chu_usd\" value=\"" +
                       f.getTong_tien_chu_usd() +
                       "\" id=\"tong_tien_chu_usd\"></input>");
        sbSubHTML.append("<input type=\"hidden\" name=\"ty_gia\" value=\"" +
                     f.getTy_gia() +
                     "\" id=\"ty_gia\"></input>");
        sbSubHTML.append("<input type=\"hidden\" name=\"loai_tien\" value=\"" +
                     f.getLoai_tien() +
                     "\" id=\"loai_tien\"></input>");

          ResultSet rsNSD = null;
          rsNSD = delegate.getLenhChiTraNoCTList(vo.getGuid());
          if (rsNSD != null) {
              JasperPrint jasperPrint = null;
              Map<String, Object> parameterMap = new HashMap<String, Object>();
              Map<String, Object> map =new HashMap();
              map.put("MA", f.getDvi_nhan());              
              DMDonViTTDelegate dMDonViTTDelegate =new DMDonViTTDelegate(conn);
              ArrayList listDonViTT= (ArrayList)dMDonViTTDelegate.getListDmDonViTT(map);
              String dvi_nn = "";
              if(listDonViTT!=null && listDonViTT.size() > 0){
                dvi_nn = ((DMDonViTTVo)listDonViTT.get(0)).getTen();
              }
              parameterMap.put("pNamNS", f.getNam_ns());
              parameterMap.put("pSoLenh", f.getSo_lenh());
              parameterMap.put("pNgayTT", StringUtil.StringToCalendar(f.getNgay_den_han_tt(), "dd/MM/yyyy").get(Calendar.DATE)+"");
              parameterMap.put("pThangTT", StringUtil.StringToCalendar(f.getNgay_den_han_tt(), "dd/MM/yyyy").get(Calendar.MONTH) + 1 +"");
              parameterMap.put("pNamTT", StringUtil.StringToCalendar(f.getNgay_den_han_tt(), "dd/MM/yyyy").get(Calendar.YEAR)+"");
              parameterMap.put("pCanCu", f.getCan_cu()+"");
              parameterMap.put("pDonviNhanTien", dvi_nn);
              parameterMap.put("pTKSo", f.getSo_tk_nhan()+"");
              parameterMap.put("pNgayChuyen", f.getNgay_chuyen()+"");
              parameterMap.put("pNganHang", f.getNh_nhan()+"");
              parameterMap.put("pTongTienChu", f.getTong_tien_chu()+"");
              parameterMap.put("pTongTienChuUsd", f.getTong_tien_chu_usd()+"");
              parameterMap.put("pTyGia", f.getTy_gia()+"");
              parameterMap.put("pLoaiTien", f.getLoai_tien()+"");
              if(f.getLoai_tien()!=null && "VND".equals(f.getLoai_tien())){
                reportName = "/LenhChiTraNoVnd";
              }else reportName = "/LenhChiTraNoUSD2";
              reportStream = getServlet().getServletConfig().getServletContext().getResourceAsStream(REPORT_DIRECTORY +
                                                                                              reportName +
                                                                                              ".jasper");
              JRDataSource jrDS = new JRResultSetDataSource(rsNSD);
              jasperPrint = JasperFillManager.fillReport(reportStream, parameterMap,jrDS);

              ReportUtility rpUtilites = new ReportUtility();

              String strTypePrintAction =
                  request.getParameter(AppConstants.REQUEST_ACTION) == null ?
                  "" :
                  request.getParameter(AppConstants.REQUEST_ACTION).toString();
              String strActionName = "PrintLenhChiTraNoUsdAction.do";
              String strParameter = "";
              String strPathFont =
                  getServlet().getServletContext().getContextPath() +
                  REPORT_DIRECTORY + strFontTimeRoman;

              rpUtilites.exportReport(jasperPrint, strTypePrintAction,
                                      response, fileName, strPathFont,
                                      strActionName, sbSubHTML.toString(),
                                      strParameter);

          }

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
}
