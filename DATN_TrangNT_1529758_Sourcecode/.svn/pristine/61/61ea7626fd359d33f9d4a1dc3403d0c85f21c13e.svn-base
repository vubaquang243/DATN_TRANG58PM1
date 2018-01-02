package com.seatech.tp.nhomnsd.action;


import com.seatech.framework.AppConstants;
import com.seatech.framework.common.jsp.PagingBean;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.datamanager.ReportUtility;
import com.seatech.framework.exception.TPCPException;
import com.seatech.framework.strustx.AppAction;
import com.seatech.tp.chucnang.PhanQuyenDAO;
import com.seatech.tp.nhomnsd.NhomNSDDAO;
import com.seatech.tp.nhomnsd.NhomNSDVO;
import com.seatech.tp.nhomnsd.form.QLyNhomNSDForm;
import com.seatech.tp.thamchieu.MaThamChieuDAO;
import com.seatech.tp.user.UserDAO;
import com.seatech.tp.user.form.QuanLyNSDForm;

import java.io.InputStream;

import java.sql.Connection;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class QLyNhomNSDAction extends AppAction {
    private static String SUCCESS = "success";
    //    private static String FAILURE = "failure";
    //    private String forward = AppConstants.SUCCESS;
    //    private static String STRING_EMPTY = "";

    public ActionForward list(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        //      if (!checkPermissionOnFunction(request, "SYS.QLY_NSD")) {
        //          return mapping.findForward("errorQuyen");
        //      }
        Connection conn = null;
        try {
            conn = getConnection();
            //            QLyNhomNSDForm f = (QLyNhomNSDForm)form;
            //            HttpSession session = request.getSession();
            //            int phantrang = AppConstants.APP_NUMBER_ROW_ON_PAGE;
            String strWhere = null;
            Vector v_param = null;
            NhomNSDDAO dao = new NhomNSDDAO(conn);
            List listNSD = null;
            listNSD = (List)dao.getNhomNSDList(strWhere, v_param);
            request.setAttribute("lstNhomNSD", listNSD);

        } catch (Exception e) {
            throw e;
        } finally {

            close(conn);
        }
        return mapping.findForward(SUCCESS);
    }
    public static final String REPORT_DIRECTORY = "/report";
    public static final String strFontTimeRoman = "/font/times.ttf";
    public static final String fileName = "/DSNhomNSD";

    public ActionForward printAction(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response) throws Exception {
        //      if (!checkPermissionOnFunction(request, "SYS.QLY_NSD")) {
        //          return mapping.findForward("errorQuyen");
        //      }
        String reportName = "/DSNhomNSD";
        Connection conn = null;
        InputStream reportStream = null;
        try {
            conn = getConnection();

            String strWhere = null;
            Vector v_param = null;
            NhomNSDDAO dao = new NhomNSDDAO(conn);
            List listNSD = null;
            listNSD = (List)dao.getNhomNSDList(strWhere, v_param);
            ResultSet rsNSD = null;
            rsNSD = dao.getNhomNSDList(strWhere);
            request.setAttribute("lstNhomNSD", listNSD);
            if (!listNSD.isEmpty()) {
                JasperPrint jasperPrint = null;
                HashMap parameterMap = null;

                reportStream =
                        getServlet().getServletConfig().getServletContext().getResourceAsStream(REPORT_DIRECTORY +
                                                                                                reportName +
                                                                                                ".jasper");
                JRDataSource jrDS = new JRResultSetDataSource(rsNSD);
                jasperPrint =
                        JasperFillManager.fillReport(reportStream, parameterMap,
                                                     jrDS);

                ReportUtility rpUtilites = new ReportUtility();

                String strTypePrintAction =
                    request.getParameter(AppConstants.REQUEST_ACTION) == null ?
                    "" :
                    request.getParameter(AppConstants.REQUEST_ACTION).toString();
                String strActionName = "QuanLyNhomNSDPrintAction.do";
                String strParameter = "";
                String strPathFont =
                    getServlet().getServletContext().getContextPath() +
                    REPORT_DIRECTORY + strFontTimeRoman;

                rpUtilites.exportReport(jasperPrint, strTypePrintAction,
                                        response, fileName, strPathFont,
                                        strActionName, "", strParameter);

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

    public ActionForward add(ActionMapping mapping, ActionForm form,
                             HttpServletRequest request,
                             HttpServletResponse response) throws Exception {
        //
        Connection conn = null;
        String strWhere = null;
        Vector params = null;
        try {
            //            QLyNhomNSDForm f = (QLyNhomNSDForm)form;
            Collection listNhomNSD = new ArrayList();
            conn = getConnection();
            MaThamChieuDAO dao = new MaThamChieuDAO(conn);
            NhomNSDDAO daoN = new NhomNSDDAO(conn);
            listNhomNSD = daoN.getNhomNSDListM("", params);
            request.setAttribute("listNNSD", listNhomNSD);
        } catch (Exception e) {
            throw e;
        } finally {
            close(conn);
        }

        return mapping.findForward("success");
    }

    public ActionForward addExc(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
        //      if (!checkPermissionOnFunction(request, "SYS.QLY_NSD.THEMMOI")) {
        //          return mapping.findForward("errorQuyen");
        //      }

        Connection conn = null;
        Collection reval = null;

        try {
            //                      if (isTokenValid(request)) {
            //            HttpSession session = request.getSession();
            //            Long nUserID =
            //                new Long(session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString());
            conn = getConnection();
            QLyNhomNSDForm f = (QLyNhomNSDForm)form;
            NhomNSDVO vo = new NhomNSDVO();
            BeanUtils.copyProperties(vo, f);
            NhomNSDDAO dao = new NhomNSDDAO(conn);
            //Kiem tra ma KTV da ton tai trong db chua
            String strWhere = " LOWER(ten_nhom) = ? ";
            Vector vParam = new Vector();
            vParam.add(new Parameter(vo.getTen_nhom().toLowerCase(), true));
            reval = dao.getNhomNSDList(strWhere, vParam);
            if (!reval.isEmpty()) {
                f.setTrangthai("tontai");
                f.getId();
                f.getLoai_nhom();
                f.getTen_nhom();
                return mapping.findForward("failure");
            } else {
                Long a = dao.insert(vo);

                if (a.longValue() == 0) {
                    request.setAttribute("status",
                                         "qlynhomnsd.listphannhomnsd.failure.them");
                } else {
                    //ghi save
                    request.setAttribute("status",
                                         "qlynhomnsd.listphannhomnsd.success.them");
                    request.setAttribute("nsdID", a.longValue());
                    saveVisitLog(conn, request.getSession(),
                                 "SYS.QLY_NSD.THEMMOI", "");
                    conn.commit();
                    f = new QLyNhomNSDForm();
                    return list(mapping, f, request, response);


                }
            }
            //                      }
            //                      resetToken(request);
            //                      saveToken(request);

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
        //      if (!checkPermissionOnFunction(request, "SYS.QLY_NSD.SUA")) {
        //          return mapping.findForward("errorQuyen");
        //      }
        Connection conn = null;
        Vector params = null;
        try {
            conn = getConnection();
            QLyNhomNSDForm f = (QLyNhomNSDForm)form;
            f.setId(request.getParameter("longid").trim());
            f.setLoai_nhom(request.getParameter("loainhom").trim());
            f.setTen_nhom(request.getParameter("tennhom").trim());
            String strWhere = null;
            Vector vParam = null;
            Collection listNhomNSD = new ArrayList();

            NhomNSDDAO daoN = new NhomNSDDAO(conn);
            listNhomNSD = daoN.getNhomNSDListM("", params);
            request.setAttribute("listNNSD", listNhomNSD);
        } catch (Exception e) {
            throw e;
        } finally {
            close(conn);
        }

        return mapping.findForward("success");
    }

    public ActionForward updateExc(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response) throws Exception {

        //      if (!checkPermissionOnFunction(request, "SYS.QLY_NSD.SUA")) {
        //          return mapping.findForward("errorQuyen");
        //      }
        Connection conn = null;
        String nhomid = null;
        try {
            //            HttpSession session = request.getSession();
            //            Long nUserID =
            //                new Long(session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString());
            //            if (isTokenValid(request)) {

            conn = getConnection();
            QLyNhomNSDForm f = (QLyNhomNSDForm)form;
            nhomid = f.getId();

            NhomNSDVO vo = new NhomNSDVO();
            BeanUtils.copyProperties(vo, f);
            vo.setId(new Long(f.getId()));
            NhomNSDDAO dao = new NhomNSDDAO(conn);
            int i = dao.update(vo);
            if (i > 0) {
                request.setAttribute("status",
                                     "qlynhomnsd.listphannhomnsd.success.sua");
                request.setAttribute("nsdID", nhomid);
                saveVisitLog(conn, request.getSession(), "SYS.QLY_NSD.SUA",
                             "");

            } else {
                request.setAttribute("status",
                                     "qlynhomnsd.listphannhomnsd.failure.sua");
                request.setAttribute("nsdID", nhomid);

            }
            //

            conn.commit();
            QLyNhomNSDForm fff = new QLyNhomNSDForm();
            return list(mapping, fff, request, response);
            //            }
            //            resetToken(request);
            //            saveToken(request);

        } catch (Exception e) {
            throw e;
        } finally {
            close(conn);
        }


    }

    public ActionForward delete(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
//        if (!checkPermissionOnFunction(request, "SYS.QLY_NSD.XOA")) {
//            return mapping.findForward("errorQuyen");
//        }
        Connection conn = null;
        String nsdID = null;
        try {
            //            if (!isTokenValid(request)) {
            //            HttpSession session = request.getSession();
            //            Long nUserID =
            //                new Long(session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString());
            conn = getConnection();
            QLyNhomNSDForm f = (QLyNhomNSDForm)form;
            f.setId(request.getParameter("longid").trim());
            nsdID = f.getId();
            String strWhere = "id = ? ";
            Vector vParam = new Vector();
            vParam.add(new Parameter(f.getId(), true));
            Collection listUser = null;
            UserDAO daoUser = new UserDAO(conn);
            listUser =  daoUser.getUserList(strWhere,vParam);
            if(listUser.size() > 0){
              throw new TPCPException().createException("TPCP-0033");
            }
            NhomNSDVO vo = new NhomNSDVO();
            NhomNSDDAO dao = new NhomNSDDAO(conn);            
            vo = dao.getNhomNSD(strWhere, vParam);
            if (vo == null) {
                request.setAttribute("status",
                                     "qlynhomnsd.listphannhomnsd.failure.xoa.daxoa");
                request.setAttribute("nsdID", nsdID);
            } else {
                long lID = new Long(f.getId());
                String strwhereNsd_nhom = " and nhom_id=" + lID;
                PhanQuyenDAO dao2 = new PhanQuyenDAO(conn);
                int a1 = dao.deleteNsd_nhom(strwhereNsd_nhom, vParam);
                int a2 = dao2.deleteNhom_id(lID);
                if ((a1 != 0 && a2 > 0) || (a1 == 0 && a2 == 0)) {
                    int a = dao.delete(lID);
                    if (a > 0) {
                        request.setAttribute("status",
                                             "qlynhomnsd.listphannhomnsd.success.xoa");
                        request.setAttribute("nsdID", nsdID);
                    } else {
                        request.setAttribute("status",
                                             "qlynhomnsd.listphannhomnsd.failure.xoa");
                        request.setAttribute("nsdID", nsdID);
                    }
                } else {
                    request.setAttribute("status",
                                         "qlynhomnsd.listphannhomnsd.failure.xoa");
                    request.setAttribute("nsdID", nsdID);
                }
            }

            conn.commit();
            saveVisitLog(conn, request.getSession(), "SYS.QLY_NSD.XOA", "");
            QLyNhomNSDForm fff = new QLyNhomNSDForm();
            return list(mapping, fff, request, response);


            //            }
            //
            //            resetToken(request);
            //            saveToken(request);
        } catch (Exception e) {
            conn.rollback();
            throw e;
        } finally {
            close(conn);
        }

    }

    public ActionForward view(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {

        Connection conn = null;
        String strWhere = "";
        try {
            conn = getConnection();
            QuanLyNSDForm f = (QuanLyNSDForm)form;
            String strid = null;
            String strten = null;
            String strmaNsd = null;
            String strmaKB = null;
            String strtrangThai = null;
            if (f != null) {
                strid = f.getId();
                strten = f.getTen_nsd();
                strmaNsd = f.getMa_nsd();
                strmaKB = f.getKb_id();
                strtrangThai = f.getTrang_thai();
            }

            //            HttpSession session = request.getSession();
            Vector v_param = null;
            int phantrang = AppConstants.APP_NUMBER_ROW_ON_PAGE;
            if (null == f.getId_nhom() || "".equals(f.getId_nhom())) {
                f.setId_nhom(request.getParameter("longid").trim());
                f.setLoai_nhom(request.getParameter("loainhom").trim());
                f.setTen_nhom(request.getParameter("tennhom").trim());
            }
            if (strten != null && !strten.equals("")) {
                strWhere =
                        "and lower(a.ten_nsd) like lower('%" + strten + "%') ";
            } else {
                strWhere = "";
            }
            if (strmaNsd != null && !strmaNsd.equals("")) {
                strWhere +=
                        " and lower(a.ma_nsd) like lower('%" + strmaNsd + "%') ";
            }

            if (strmaKB != null && !strmaKB.equals("")) {
                strWhere += " and a.kb_id =" + strmaKB;
            }
            if (strtrangThai != null && !strtrangThai.equals("")) {
                strWhere +=
                        " and lower(a.trang_thai) like lower('%" + strtrangThai +
                        "%') ";
            }

            strWhere +=
                    " and c.id = a.kb_id and a.id = b.nsd_id and b.nhom_id = ? ";
            v_param = new Vector();
            v_param.add(new Parameter(f.getId_nhom(), true));
            UserDAO dao = new UserDAO(conn);
            String page = f.getPageNumber();
            if (page == null)
                page = "1";
            Integer currentPage = new Integer(page);
            Integer numberRowOnPage = phantrang;
            Integer totalCount[] = new Integer[1];
            List listNSD = null;
            listNSD =
                    (List)dao.getUserList_ptrang(strWhere, v_param, currentPage,
                                                 numberRowOnPage, totalCount);
            PagingBean pagingBean = new PagingBean();
            pagingBean.setCurrentPage(currentPage);
            pagingBean.setNumberOfRow(totalCount[0].intValue());
            pagingBean.setRowOnPage(numberRowOnPage);
            request.setAttribute("PAGE_KEY", pagingBean);
            request.setAttribute("lstNhomNSD", listNSD);

            resetToken(request);
            saveToken(request);
        } catch (Exception e) {
            throw e;
        } finally {

            close(conn);
        }
        return mapping.findForward(SUCCESS);
    }
}


