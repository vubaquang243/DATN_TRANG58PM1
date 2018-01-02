package com.seatech.tp.tttmua.action;

import com.seatech.framework.AppConstants;
import com.seatech.framework.common.jsp.PagingBean;
import com.seatech.framework.datamanager.ReportUtility;
import com.seatech.framework.exception.TPCPException;
import com.seatech.framework.strustx.AppAction;
import com.seatech.framework.utils.StringUtil;
import com.seatech.tp.dmkyhan.action.DMKyHanDelegate;
import com.seatech.tp.dmkyhan.vo.DMKyHanVO;
import com.seatech.tp.qlyLenhTraNo.action.QLyLenhTraNoDelegate;
import com.seatech.tp.qlyLenhTraNo.form.LenhTraNoForm;
import com.seatech.tp.qlytp.action.QuanLyTPCPDelegate;
import com.seatech.tp.ttindthau.action.QLyTTinDauThauDelegate;
import com.seatech.tp.ttindthau.vo.ThongTinDauThauVO;
import com.seatech.tp.ttlaigoc.vo.TPLenhTriTraNoVo;
import com.seatech.tp.tttmua.form.QLyLapBangKeForm;
import com.seatech.tp.tttmua.form.QLyTToanTienMuaCTietForm;
import com.seatech.tp.tttmua.form.QLyTToanTienMuaForm;
import com.seatech.tp.tttmua.vo.QLyLapBangKeCTietVO;
import com.seatech.tp.tttmua.vo.QLyLapBangKeVO;
import com.seatech.tp.tttmua.vo.QLyTToanTienMuaVO;

import com.seatech.tp.user.UserHistoryVO;

import java.io.InputStream;

import java.math.BigDecimal;

import java.sql.Connection;

import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.Collection;

import java.util.HashMap;
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

public class QLyLapBangKeAction extends AppAction {
    private static String SUCCESS = "success";
    private static String FAILURE = "failure";

    public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            QLyLapBangKeForm f = (QLyLapBangKeForm)form;
            f.reset(mapping, request);
            return search(mapping, form, request, response);
        } catch (Exception e) {
            throw e;
        }
    }

    public ActionForward search(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Connection conn = null;
        try {
            conn = getConnection(request);
            QLyLapBangKeForm f = (QLyLapBangKeForm)form;
            DMKyHanDelegate khDelegate = new DMKyHanDelegate(conn);
            //          Map<String, Object> map = new HashMap();
            //          map.put("loai_tpcp", "TRAI_PHIEU");
            List listKyHan = null;
            listKyHan = (List)khDelegate.getDMKyHan(null);
            request.setAttribute("listKyHan", listKyHan);
            QuanLyTPCPDelegate delegateTP = new QuanLyTPCPDelegate(conn);
            List listTPCP = new ArrayList();
            listTPCP = (List)delegateTP.getAllListTPCP();
            request.setAttribute("lstAllTPCP", listTPCP);
            int phantrang = (AppConstants.APP_NUMBER_ROW_ON_PAGE);
            String page = f.getPageNumber();
            if (page == null || page.equalsIgnoreCase(""))
                page = "1";
            Integer currentPage = new Integer(page);
            Integer numberRowOnPage = phantrang;
            Integer totalCount[] = new Integer[1];
            QLyLapBangKeDelegate delegate = new QLyLapBangKeDelegate(conn);
            Collection lstBKeXNhan = new ArrayList();
            QLyLapBangKeVO vo = new QLyLapBangKeVO();
            BeanUtils.copyProperties(vo, f);
            lstBKeXNhan = delegate.getListBangKeXacNhan(vo, currentPage, numberRowOnPage, totalCount);
            request.setAttribute("lstBKeXNhan", lstBKeXNhan);

            PagingBean pagingBean = new PagingBean();
            pagingBean.setCurrentPage(currentPage);
            pagingBean.setNumberOfRow((totalCount[0] == null) ? 0 : totalCount[0].intValue());
            pagingBean.setRowOnPage(numberRowOnPage);
            request.setAttribute("PAGE_KEY", pagingBean);

        } catch (Exception e) {
            throw e;
        } finally {
            close(conn);
        }
        return mapping.findForward(SUCCESS);
    }

    public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Connection conn = null;
        try {
            conn = getConnection();
            String guid = request.getParameter("longid").trim();
            QLyLapBangKeDelegate delegate = new QLyLapBangKeDelegate(conn);
            QLyLapBangKeForm f = (QLyLapBangKeForm)form;
            QLyLapBangKeVO vo = null;
            vo = delegate.getTTinBangKeObject(guid);
            if (vo == null) {
                saveMessage(request, new TPCPException("tpbkechuyentien.norecord"));
                return mapping.findForward(FAILURE);
            }
            vo.setKl_trung_thau(StringUtil.convertNumberToString(vo.getKl_trung_thau(), "VND"));
            vo.setLs_danh_nghia(StringUtil.convertNumberToString(vo.getLs_danh_nghia(), "VND"));
            vo.setLs_trung_thau(StringUtil.convertNumberToString(vo.getLs_trung_thau(), "VND"));
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("MA_TPCP", vo.getMa_tpcp());
            map.put("DOT_PH", vo.getDot_ph());
            QLyLapBangKeDelegate delegateBKe = new QLyLapBangKeDelegate(conn);
            QLyLapBangKeVO voDThau = delegateBKe.getTTinDThauFromKQPH(map, vo.getPhuong_thuc_ph());
            if (voDThau != null) {
                vo.setKl_trung_thau(StringUtil.convertNumberToString(voDThau.getKl_trung_thau(), "VND"));
            }
            BeanUtils.copyProperties(f, vo);
            f.setPtph_tpcp(vo.getPhuong_thuc_ph());
            request.setAttribute("lstDViSoHuuBKe", vo.getLstCTietBKeCTien());
            DMKyHanDelegate khDelegate = new DMKyHanDelegate(conn);
            //          Map<String, Object> map = new HashMap();
            //          map.put("loai_tpcp", "TRAI_PHIEU");
            List listKyHan = null;
            listKyHan = (List)khDelegate.getDMKyHan(null);
            request.setAttribute("listKyHan", listKyHan);
            QuanLyTPCPDelegate delegateTP = new QuanLyTPCPDelegate(conn);
            List listTPCP = new ArrayList();
            listTPCP = (List)delegateTP.getAllListTPCP();
            request.setAttribute("lstAllTPCP", listTPCP);

        } catch (Exception e) {
            throw e;
        } finally {
            close(conn);
        }
        return mapping.findForward(SUCCESS);
    }

    public ActionForward updateExc(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Connection conn = null;
        try {
            String errMess = "";
            conn = getConnection();
            HttpSession session = request.getSession();
            String nUserID = session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString();
            QLyLapBangKeForm f = (QLyLapBangKeForm)form;
            QLyLapBangKeVO vo = new QLyLapBangKeVO();
            QLyLapBangKeCTietVO ctietVO = null;
            BeanUtils.copyProperties(vo, f);
            //update lai thông tin đấu thầu
            vo.setKl_trung_thau(f.getTong_kl_trung_thau());
            //update lai thông tin đấu thầu
            vo.setKl_trung_thau(f.getTong_kl_trung_thau());
            vo.setNgay_sua_cuoi(StringUtil.getCurrentDate());
            vo.setNguoi_sua_cuoi(nUserID);
            //vo.setTrang_thai("00");
            //get list chi tiet
            Collection lstCTiet = new ArrayList();
            String[] ma_trai_chu = request.getParameterValues("ma_trai_chu");
            String[] ten_trai_chu = request.getParameterValues("ten_trai_chu");
            String[] kl_trung_thau = request.getParameterValues("kl_trung_thau_ctiet");
            String[] so_tien_phai_tt = request.getParameterValues("so_tien_phai_tt");
            String[] so_tien_da_tt = request.getParameterValues("so_tien_da_tt");
            String[] ngay_chuyen_tien = request.getParameterValues("ngay_chuyen_tien");
            String[] ghi_chu = request.getParameterValues("ghi_chu");
            BigDecimal tong_kl_trung_thau = new BigDecimal("0");    
            if (ma_trai_chu != null && ma_trai_chu.length > 0) {
                for (int i = 0; i < ma_trai_chu.length; i++) {
                    ctietVO = new QLyLapBangKeCTietVO();
                    ctietVO.setMa_trai_chu(ma_trai_chu[i]);
                    ctietVO.setTen_trai_chu(ten_trai_chu[i]);
                    ctietVO.setKl_trung_thau(kl_trung_thau[i]);
                    tong_kl_trung_thau = tong_kl_trung_thau.add(new BigDecimal(kl_trung_thau[i].replaceAll("\\.","")));
                    ctietVO.setSo_tien_phai_tt(so_tien_phai_tt[i]);
                    ctietVO.setSo_tien_da_tt(so_tien_da_tt[i]);
                    ctietVO.setNgay_chuyen_tien(ngay_chuyen_tien[i]);
                    ctietVO.setGhi_chu(ghi_chu[i]);
                    lstCTiet.add(ctietVO);
                }
            }
            vo.setKl_trung_thau(String.valueOf(tong_kl_trung_thau));
            vo.setLstCTietBKeCTien(lstCTiet);
            QLyLapBangKeDelegate delegate = new QLyLapBangKeDelegate(conn);
            long idAdd = delegate.update(vo, "");
            //insert history
            UserHistoryVO userHisVO = new UserHistoryVO();
            userHisVO.setNguoi_tdoi(new Long(nUserID));
            userHisVO.setNoi_dung_thaydoi("Update bang ke " + idAdd);
            userHisVO.setNsd_id(idAdd);
            delegate.insertHistoryUser(userHisVO);
            f.reset(mapping, request);
            errMess = "tpbkechuyentien.update.succ";
            saveMessage(request, new TPCPException(errMess));

        } catch (Exception e) {
            throw e;
        } finally {
            close(conn);
        }
        return mapping.findForward(SUCCESS);

    }

    public ActionForward trinhAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Connection conn = null;
        String errMess = "";
        try {
            conn = getConnection();
            //String guid = request.getParameter("longid").trim();
            QLyLapBangKeDelegate delegate = new QLyLapBangKeDelegate(conn);
            HttpSession session = request.getSession();
            String nUserID = session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString();
            QLyLapBangKeForm f = (QLyLapBangKeForm)form;
            QLyLapBangKeVO vo = new QLyLapBangKeVO();;
            if (delegate.getTTinBangKeObject(f.getGuid()) == null) {
                saveMessage(request, new TPCPException("tpbkechuyentien.norecord"));
                return mapping.findForward(FAILURE);
            }          
            QLyLapBangKeCTietVO ctietVO = null;
            BeanUtils.copyProperties(vo, f);
            vo.setNgay_sua_cuoi(StringUtil.getCurrentDate());
            vo.setNguoi_sua_cuoi(nUserID);
            vo.setTrang_thai("00");
            //get list chi tiet
            Collection lstCTiet = new ArrayList();
            String[] ma_trai_chu = request.getParameterValues("ma_trai_chu");
            String[] ten_trai_chu = request.getParameterValues("ten_trai_chu");
            String[] kl_trung_thau = request.getParameterValues("kl_trung_thau");
            String[] so_tien_phai_tt = request.getParameterValues("so_tien_phai_tt");
            String[] ngay_chuyen_tien = request.getParameterValues("ngay_chuyen_tien");
            String[] ghi_chu = request.getParameterValues("ghi_chu");
            if (ma_trai_chu != null && ma_trai_chu.length > 0) {
                for (int i = 0; i < ma_trai_chu.length; i++) {
                    ctietVO = new QLyLapBangKeCTietVO();
                    ctietVO.setMa_trai_chu(ma_trai_chu[i]);
                    ctietVO.setTen_trai_chu(ten_trai_chu[i]);
                    ctietVO.setKl_trung_thau(kl_trung_thau[i]);
                    ctietVO.setSo_tien_phai_tt(so_tien_phai_tt[i]);
                    ctietVO.setNgay_chuyen_tien(ngay_chuyen_tien[i]);
                    ctietVO.setGhi_chu(ghi_chu[i]);
                    lstCTiet.add(ctietVO);
                }
            }
            vo.setLstCTietBKeCTien(lstCTiet);
            vo.setGuid(f.getGuid());
            vo.setTrang_thai("01");
            long idAdd = delegate.update(vo, "");
            //insert history
            UserHistoryVO userHisVO = new UserHistoryVO();
            userHisVO.setNguoi_tdoi(new Long(nUserID));
            userHisVO.setNoi_dung_thaydoi("Trình phê duyệt bang ke " + idAdd);
            userHisVO.setNsd_id(idAdd);
            delegate.insertHistoryUser(userHisVO);
            f.reset(mapping, request);
            errMess = "tpbkechuyentien.trinh.succ";
            saveMessage(request, new TPCPException(errMess));
        } catch (Exception e) {
            throw e;
        } finally {
            close(conn);
        }
        return mapping.findForward(SUCCESS);
    }

    public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Connection conn = null;
        try {
            conn = getConnection();
            String guid = request.getParameter("longid").trim();
            QLyLapBangKeDelegate delegate = new QLyLapBangKeDelegate(conn);
            QLyLapBangKeForm f = (QLyLapBangKeForm)form;
            QLyLapBangKeVO vo = null;
            vo = delegate.getTTinBangKeObject(guid);
            if (vo == null) {
                saveMessage(request, new TPCPException("tpbkechuyentien.norecord"));
                return mapping.findForward(FAILURE);
            }            
            vo.setKl_trung_thau(StringUtil.convertNumberToString(vo.getKl_trung_thau(), "VND"));
            vo.setLs_danh_nghia(StringUtil.convertNumberToString(vo.getLs_danh_nghia(), "VND"));
            vo.setLs_trung_thau(StringUtil.convertNumberToString(vo.getLs_trung_thau(), "VND"));  
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("MA_TPCP", vo.getMa_tpcp());
            map.put("DOT_PH", vo.getDot_ph());
            QLyLapBangKeDelegate delegateBKe = new QLyLapBangKeDelegate(conn);
            QLyLapBangKeVO voDThau = delegateBKe.getTTinDThauFromKQPH(map, vo.getPhuong_thuc_ph());            
            if(voDThau!=null){
              vo.setKl_trung_thau(StringUtil.convertNumberToString(voDThau.getKl_trung_thau(), "VND"));
            }
            BeanUtils.copyProperties(f, vo);
            f.setPtph_tpcp(vo.getPhuong_thuc_ph());
            request.setAttribute("lstDViSoHuuBKe", vo.getLstCTietBKeCTien());
            DMKyHanDelegate khDelegate = new DMKyHanDelegate(conn);
            //          Map<String, Object> map = new HashMap();
            //          map.put("loai_tpcp", "TRAI_PHIEU");
            List listKyHan = null;
            listKyHan = (List)khDelegate.getDMKyHan(null);
            request.setAttribute("listKyHan", listKyHan);
            QuanLyTPCPDelegate delegateTP = new QuanLyTPCPDelegate(conn);
            List listTPCP = new ArrayList();
            listTPCP = (List)delegateTP.getAllListTPCP();
            request.setAttribute("lstAllTPCP", listTPCP);

        } catch (Exception e) {
            throw e;
        } finally {
            close(conn);
        }
        return mapping.findForward(SUCCESS);
    }

    public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Connection conn = null;
        String errMess = "";
        try {
            conn = getConnection();
            QLyLapBangKeForm f = (QLyLapBangKeForm)form;
            HttpSession session = request.getSession();
            String nUserID = session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString();
            String guid = request.getParameter("longid").trim();
            QLyLapBangKeDelegate delegate = new QLyLapBangKeDelegate(conn);
            QLyLapBangKeVO vo = null;
            vo = delegate.getTTinBangKeObject(guid);
            if (vo == null) {
                saveMessage(request, new TPCPException("tpbkechuyentien.norecord"));
                return mapping.findForward(FAILURE);
            }
            long idAdd = delegate.delete(vo);
            //insert history
            UserHistoryVO userHisVO = new UserHistoryVO();
            userHisVO.setNguoi_tdoi(new Long(nUserID));
            userHisVO.setNoi_dung_thaydoi("Xoa ma TPCP " + idAdd);
            userHisVO.setNsd_id(idAdd);
            delegate.insertHistoryUser(userHisVO);
            f.reset(mapping, request);
            if (idAdd != 0) {
                errMess = "tpbkechuyentien.delete.succ";
            } else
                errMess = "tpbkechuyentien.delete.error";
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
  public ActionForward printAction(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response) throws Exception {
      String reportName = "/BangKeBanLe";
      String fileName = "/BangKeBanLe";
      Connection conn = null;
      InputStream reportStream = null;
      StringBuffer sbSubHTML = new StringBuffer();
      try {
          
          conn = getConnection();
          QLyLapBangKeForm f = (QLyLapBangKeForm)form;
          f.setNgay_tt_tien_mua(f.getNgay_tt());
          QLyLapBangKeVO vo = new QLyLapBangKeVO();
          QLyLapBangKeDelegate delegate = new QLyLapBangKeDelegate(conn);
          BeanUtils.copyProperties(vo, f);
          if(f.getPhuong_thuc_ph().equals("TD")){
            fileName="/BangKeDauThau";
            reportName = "/BangKeDauThau";
          }else if(f.getPhuong_thuc_ph().equals("TL")){            
            fileName="/BangKeBanLe";
            reportName = "/BangKeBanLe";
          }else if(f.getPhuong_thuc_ph().equals("TPKB")){
              fileName="/BangKeTinPhieu";
              reportName = "/BangKeTinPhieu";
            }else if(f.getPhuong_thuc_ph().equals("PHTT")){
              fileName="/BangKeTinPhieuHD";
              reportName = "/BangKeTinPhieuHD";
            }
          sbSubHTML.append("<input type=\"hidden\" name=\"phuong_thuc_ph\" value=\"" +
                           vo.getPhuong_thuc_ph() +
                           "\" id=\"phuong_thuc_ph\"></input>");
        sbSubHTML.append("<input type=\"hidden\" name=\"loai_tien\" value=\"" +
                         vo.getLoai_tien() +
                         "\" id=\"loai_tien\"></input>");
          sbSubHTML.append("<input type=\"hidden\" name=\"guid\" value=\"" +
                           vo.getGuid() +
                           "\" id=\"guid\"></input>");
          sbSubHTML.append("<input type=\"hidden\" name=\"dot_ph\" value=\"" +
                           f.getDot_ph() +
                           "\" id=\"dot_ph\"></input>");
          sbSubHTML.append("<input type=\"hidden\" name=\"ma_tpcp\" value=\"" +
                           f.getMa_tpcp() +
                           "\" id=\"ma_tpcp\"></input>");
          sbSubHTML.append("<input type=\"hidden\" name=\"noi_cap\" value=\"" +
                           f.getNoi_cap() +
                           "\" id=\"noi_cap\"></input>");
          sbSubHTML.append("<input type=\"hidden\" name=\"ky_han\" value=\"" +
                           f.getKy_han() +
                           "\" id=\"ky_han\"></input>");
          sbSubHTML.append("<input type=\"hidden\" name=\"ngay_dt\" value=\"" +
                           f.getNgay_dt() +
                           "\" id=\"ngay_dt\"></input>");
          sbSubHTML.append("<input type=\"hidden\" name=\"kl_trung_thau\" value=\"" +
                         f.getKl_trung_thau() +
                         "\" id=\"kl_trung_thau\"></input>");
          sbSubHTML.append("<input type=\"hidden\" name=\"ngay_ph\" value=\"" +
                       f.getNgay_ph() +
                       "\" id=\"ngay_ph\"></input>");
        sbSubHTML.append("<input type=\"hidden\" name=\"ls_trung_thau\" value=\"" +
                     f.getLs_trung_thau() +
                     "\" id=\"ls_trung_thau\"></input>");
        sbSubHTML.append("<input type=\"hidden\" name=\"ngay_tt_tien_mua\" value=\"" +
                     f.getNgay_tt_tien_mua() +
                     "\" id=\"ngay_tt_tien_mua\"></input>");
        sbSubHTML.append("<input type=\"hidden\" name=\"ls_danh_nghia\" value=\"" +
                     f.getLs_danh_nghia() +
                     "\" id=\"ls_danh_nghia\"></input>");
        sbSubHTML.append("<input type=\"hidden\" name=\"ly_do_tu_choi\" value=\"" +
                     f.getLy_do_tu_choi() +
                     "\" id=\"ly_do_tu_choi\"></input>");

          ResultSet rsNSD = null;
          rsNSD = delegate.getDVSoHuuObject(vo.getGuid());
          if (rsNSD != null) {
              JasperPrint jasperPrint = null;
              Map<String, Object> parameterMap = new HashMap<String, Object>();
              parameterMap.put("guid", f.getGuid()+"");
              parameterMap.put("dot_ph", f.getDot_ph()+"");
              parameterMap.put("loai_tien", f.getLoai_tien()+"");
              parameterMap.put("ma_tpcp", f.getMa_tpcp()+"");
              parameterMap.put("noi_cap", f.getNoi_cap()+"");
              DMKyHanDelegate dmKyHanDelegate = new DMKyHanDelegate(conn);
              HashMap<String, Object> map = new HashMap<String, Object>();
              map.put("GUID", f.getKy_han());
              DMKyHanVO voDMKyHan = dmKyHanDelegate.getDMKyHanObject(map);
              parameterMap.put("ky_han",voDMKyHan!=null?voDMKyHan.getMo_ta():"");
              parameterMap.put("ngay_dt", f.getNgay_dt()+"");
              parameterMap.put("kl_trung_thau", f.getKl_trung_thau());
              parameterMap.put("ngay_ph", f.getNgay_ph()+"");
              parameterMap.put("ls_trung_thau", f.getLs_trung_thau()+"");
              parameterMap.put("ls_danh_nghia", f.getLs_danh_nghia()+"");
              parameterMap.put("ngay_tt_tien_mua", f.getNgay_tt_tien_mua()+"");
              parameterMap.put("ly_do_tu_choi", f.getLy_do_tu_choi()+"");
              parameterMap.put("IS_IGNORE_PAGINATION", true);
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
              String strActionName = "PrintBangKeAction.do";
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
