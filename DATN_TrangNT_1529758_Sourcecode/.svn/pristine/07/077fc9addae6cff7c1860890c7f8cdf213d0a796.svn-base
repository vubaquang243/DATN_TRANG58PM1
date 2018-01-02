package com.seatech.tp.thamso.action;


import com.seatech.framework.AppConstants;
import com.seatech.framework.common.jsp.PagingBean;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.datamanager.ReportUtility;
import com.seatech.framework.strustx.AppAction;
import com.seatech.tp.thamso.ThamSoHThongDAO;
import com.seatech.tp.thamso.ThamSoHThongVO;
import com.seatech.tp.thamso.form.ThamSoHThongForm;

import java.io.InputStream;

import java.sql.Connection;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

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


public class ThamSoHThongAction extends AppAction {
    private static String SUCCESS = "success";
    //    private static String FAILURE = "failure";
    //    private String forward = AppConstants.SUCCESS;
    //    private static String STRING_EMPTY = "";

    public ActionForward list(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
//        if (!checkPermissionOnFunction(request, "SYS.TS_HT.QLY_TS")) {
//            return mapping.findForward("errorQuyen");
//        }
        Connection conn = null;
        //        Collection coll = null;
        ThamSoHThongForm f = null;
        try {
            conn = getConnection(request);
            f = (ThamSoHThongForm)form;
            HttpSession session = request.getSession();
            saveVisitLog(conn, session, "SYS.TS_HT.QLY_TS", "");
            // là ng dùng dang su dung   f.getG_nsd_id()
            //Khai bao bien find.
            String strid = null;
            String strmota = null;
            String strma = null;
            if (f != null) {
                strid = f.getId();
                strmota = f.getMo_ta();
                strma = f.getTen_ts();
            }
            // khai bao bien phan trang.
            //          String page = f.getPageNumber();
            String strRoleList = "";
            if (session.getAttribute(AppConstants.APP_ROLE_LIST_SESSION) !=
                null) {
                strRoleList =
                        session.getAttribute(AppConstants.APP_ROLE_LIST_SESSION).toString();
            }
            String strWhere = " and cho_phep_sua = 'Y' ";
            String[] rollList = strRoleList.split("\\|");
            if (rollList.length > 0) {
                strWhere += " and ( ";
                for (int i = 0; i < rollList.length; i++) {
                    if (i == 0) {
                        strWhere += "dvi_sua like '%" + rollList[i] + "%' ";
                    } else {
                        strWhere += "or dvi_sua like '%" + rollList[i] + "%' ";
                    }
                }
                strWhere += ") ";
            } else {
                strWhere += " and dvi_sua = '" + strRoleList + "' ";
            }

            Vector v_param = null;
            if (strid != null && !strid.equals("")) {
                strWhere = "and a.id=? ";
                v_param = new Vector();
                v_param.add(new Parameter(strid, true));
            }
            if (strmota != null && !strmota.equals("")) {

                strWhere =
                        "and lower(a.mo_ta) like lower('%" + strmota.trim() +
                        "%') ";
            }
            if (strma != null && !strma.equals("")) {
                strWhere +=
                        " and lower(a.ten_ts) like lower('%" + strma.trim() +
                        "%') ";
            }
            ThamSoHThongDAO tshtdao = new ThamSoHThongDAO(conn);
            List lstTS = null;
            lstTS = (List)tshtdao.getThamSoList(strWhere, v_param);
            if (lstTS.isEmpty()) {
                request.setAttribute("status",
                                     "QuanLyTSHT.listQLTSHT.failure.null.ketqua");
            }
            request.setAttribute("listTS", lstTS);
        } catch (Exception e) {
            throw e;
        } finally {
            close(conn);
        }
        return mapping.findForward(SUCCESS);
    }

    public static final String fileName = "/DSachTSoHT";

    public ActionForward printAction(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response) throws Exception {
//        if (!checkPermissionOnFunction(request, "SYS.TS_HT.QLY_TS")) {
//            return mapping.findForward("errorQuyen");
//        }
        Connection conn = null;
        //        Collection coll = null;
        ThamSoHThongForm f = null;
        StringBuffer sbSubHTML = new StringBuffer();
        InputStream reportStream = null;
        try {
            conn = getConnection(request);
            f = (ThamSoHThongForm)form;
            HttpSession session = request.getSession();
            saveVisitLog(conn, session, "SYS.TS_HT.QLY_TS", "");
            // là ng dùng dang su dung   f.getG_nsd_id()
            //Khai bao bien find.
            String strid = null;
            String strmota = null;
            String strma = null;
            if (f != null) {
                strid = f.getId();
                strmota = f.getMo_ta();
                strma = f.getTen_ts();
                if (strid != null) {
                    sbSubHTML.append("<input type=\"hidden\" name=\"id\" value=\"" +
                                     strid + "\" id=\"id\"></input>");
                }
                if (strmota != null) {
                    sbSubHTML.append("<input type=\"hidden\" name=\"mo_ta\" value=\"" +
                                     strmota + "\" id=\"mo_ta\"></input>");
                }
                if (strma != null) {
                    sbSubHTML.append("<input type=\"hidden\" name=\"ten_ts\" value=\"" +
                                     strma + "\" id=\"ten_ts\"></input>");
                }
            }

            String strWhere = null;
            Vector v_param = null;
            if (strid != null && !strid.equals("")) {
                strWhere = "and a.id=? ";
                v_param = new Vector();
                v_param.add(new Parameter(strid, true));
            }
            if (strmota != null && !strmota.equals("")) {

                strWhere =
                        "and lower(a.mo_ta) like lower('%" + strmota.trim() +
                        "%') ";
            } else {
                strWhere = "";
            }
            if (strma != null && !strma.equals("")) {
                strWhere +=
                        " and lower(a.ten_ts) like lower('%" + strma.trim() +
                        "%') ";
            }
            ThamSoHThongDAO tshtdao = new ThamSoHThongDAO(conn);
            ResultSet lstTS = null;
            lstTS = tshtdao.getThamSoResultSet(strWhere, v_param);
            if (lstTS == null) {
                request.setAttribute("status",
                                     "QuanLyTSHT.listQLTSHT.failure.null.ketqua");
            } else {
                JasperPrint jasperPrint = null;
                HashMap parameterMap = null;

                reportStream =
                        getServlet().getServletConfig().getServletContext().getResourceAsStream(AppConstants.REPORT_DIRECTORY +
                                                                                                fileName +
                                                                                                ".jasper");
                JRDataSource jrDS = new JRResultSetDataSource(lstTS);
                jasperPrint =
                        JasperFillManager.fillReport(reportStream, parameterMap,
                                                     jrDS);
                String strTypePrintAction =
                    request.getParameter(AppConstants.REQUEST_ACTION) == null ?
                    "" :
                    request.getParameter(AppConstants.REQUEST_ACTION).toString();
                String strActionName = "ThamSoHTPrintAction.do";
                String strParameter = "";
                String strPathFont =
                    getServlet().getServletContext().getContextPath() +
                    AppConstants.REPORT_DIRECTORY +
                    AppConstants.FONT_FOR_REPORT;
                ReportUtility rpUtilites = new ReportUtility();
                rpUtilites.exportReport(jasperPrint, strTypePrintAction,
                                        response, fileName, strPathFont,
                                        strActionName, sbSubHTML.toString(),
                                        strParameter);
            }

            request.setAttribute("listTS", new ArrayList());
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

    public ActionForward update(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
//        if (!checkPermissionOnFunction(request, "SYS.TS_HT.QLY_TS.SUA")) {
//            return mapping.findForward("errorQuyen");
//        }
        ThamSoHThongForm f = (ThamSoHThongForm)form;
        f.setGiatri_ts_moi(request.getParameter("strgiatri").trim());
        f.setGiatri_ts(request.getParameter("strgiatri").trim());
        f.setCho_phep_sua(request.getParameter("strchophepsua").trim());
        f.setId(request.getParameter("longid").trim());
        f.setTen_ts(request.getParameter("strtents").trim());
        f.setMo_ta(request.getParameter("strmota").trim());

        return mapping.findForward("success");
    }
    //action deer thucj hien update

    public ActionForward updateExc(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response) throws Exception {

//        if (!checkPermissionOnFunction(request, "SYS.TS_HT.QLY_TS.SUA")) {
//            return mapping.findForward("errorQuyen");
//        }
        Connection conn = null;
        try {
            HttpSession session = request.getSession();

            //        String mathamso = null;
            Long nUserID =
                new Long(session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString());
            Long lKB =
                new Long(session.getAttribute(AppConstants.APP_KB_ID_SESSION).toString());

            conn = getConnection(request);

            saveVisitLog(conn, session, "SYS.TS_HT.QLY_TS.SUA",
                         "Cap nhat tham so he thong");
            ThamSoHThongForm f = (ThamSoHThongForm)form;
            ThamSoHThongVO vo = new ThamSoHThongVO();
            f.setId(f.getId().trim());
            //vo.setTen(f.getTen());
            // vo.setId(Long.valueOf(f.getId().trim()));
            ThamSoHThongDAO tsDAO = new ThamSoHThongDAO(conn);
            BeanUtils.copyProperties(vo, f);
            vo.setKb_id(lKB);
            //          vo.setGia_tri_ts_moi(f.getGiatri_ts_moi());
            vo.setNsd_id(nUserID);
            request.setAttribute("nsdID", f.getTen_ts());
            if (f.getCho_phep_sua().equalsIgnoreCase("Y") ||
                f.getCho_phep_sua().equalsIgnoreCase("y")) {
                int i = tsDAO.update(vo);
                if (i > 0) {
                    Long j = tsDAO.insert(vo);
                    if (j.longValue() == 0) {
                        request.setAttribute("status",
                                             "QuanLyTSHT.listQLTSHT.failure.sua");

                    }

                    request.setAttribute("status",
                                         "QuanLyTSHT.listQLTSHT.success.sua");
                    f.setMo_ta("");
                    f.setTen_ts("");

                } else {
                    request.setAttribute("status",
                                         "QuanLyTSHT.listQLTSHT.failure.sua");
                }
            } else {
                request.setAttribute("status",
                                     "QuanLyTSHT.listQLTSHT.failure.sua.kchophep");
            }
            conn.commit();
        } catch (Exception e) {
            throw e;
        } finally {
            close(conn);
        }
        ThamSoHThongForm fff = new ThamSoHThongForm();
        return list(mapping, fff, request, response);
        //       return mapping.findForward("success");

    }

    public ActionForward view(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {

//        if (!checkPermissionOnFunction(request, "SYS.TS_HT.LS_TS")) {
//            return mapping.findForward("errorQuyen");
//        }
        Connection conn = null;
        //      Collection coll = null;


        try {
            conn = getConnection(request);
            int phantrang = AppConstants.APP_NUMBER_ROW_ON_PAGE;
            ThamSoHThongForm f = (ThamSoHThongForm)form;
            // là ng dùng dang su dung   f.getG_nsd_id()
            //Khai bao bien find.
            String page = f.getPageNumber();
            if (page == null)
                page = "1";
            Integer currentPage = new Integer(page);
            Integer numberRowOnPage = phantrang;
            Integer totalCount[] = new Integer[1];
            String strid = null;
            String strmota = null;
            String strma = null;
            String tu_ngay = null;
            String den_ngay = null;
            if (f != null) {
                strmota = f.getMo_ta();
                strma = f.getTen_ts();
                tu_ngay = f.getTu_ngay();
                den_ngay = f.getDen_ngay();
            }
            String strWhere = null;
            Vector v_param = null;
            if (strid != null && !strid.equals("")) {
                strWhere = "and a.id=? ";
                v_param = new Vector();
                v_param.add(new Parameter(strid, true));
            }
            if (strmota != null && !strmota.equals("")) {

                strWhere =
                        "and lower(a.mo_ta) like lower('%" + strmota + "%') ";
            }
            if ((den_ngay == null || "".equals(den_ngay)) &&
                (tu_ngay != null && !"".equals(tu_ngay))) {
                strWhere =
                        " and ((a.ngay_cap_nhat <=  to_date(sysdate) and a.ngay_cap_nhat >=  to_date('" +
                        tu_ngay + "','DD-MM-YYYY'))) ";
            } else if (den_ngay != null && !"".equals(den_ngay) &&
                       (tu_ngay == null || "".equals(tu_ngay))) {
                strWhere =
                        " and (a.ngay_cap_nhat <=  to_date('" + den_ngay + "','DD-MM-YYYY')) ";
            } else if (tu_ngay != null && !"".equals(tu_ngay) &&
                       den_ngay != null && !"".equals(den_ngay)) {
                strWhere =
                        " and ((a.ngay_cap_nhat >=  to_date('" + tu_ngay + "','DD-MM-YYYY') and a.ngay_cap_nhat <=  to_date('" +
                        den_ngay + "','DD-MM-YYYY'))) ";
            } else {
                strWhere = "";
            }
            if (strma != null && !strma.equals("")) {
                strWhere +=
                        " and lower(a.ten_ts) like lower('%" + strma.trim() +
                        "%') ";
            }

            ThamSoHThongDAO tshtdao = new ThamSoHThongDAO(conn);
            List lstTS = null;
            lstTS =
                    (List)tshtdao.getThamSoLSuList(strWhere, v_param, currentPage,
                                                   numberRowOnPage,
                                                   totalCount);
            request.setAttribute("listTS", lstTS);
            PagingBean pagingBean = new PagingBean();
            pagingBean.setCurrentPage(currentPage);
            pagingBean.setNumberOfRow(totalCount[0].intValue());
            pagingBean.setRowOnPage(numberRowOnPage);
            request.setAttribute("PAGE_KEY", pagingBean);
        } catch (Exception e) {
            throw e;
        } finally {
            close(conn);
        }
        return mapping.findForward(SUCCESS);
    }

    public ActionForward executeAction(ActionMapping mapping, ActionForm form,
                                       HttpServletRequest request,
                                       HttpServletResponse response) throws Exception {

//        if (!checkPermissionOnFunction(request, "SYS.TS_HT.LS_TS")) {
//            return mapping.findForward("errorQuyen");
//        }
        Connection conn = null;
        //      Collection coll = null;

        StringBuffer sbSubHTML = new StringBuffer();
        InputStream reportStream = null;
        try {
            conn = getConnection(request);

            ThamSoHThongForm f = (ThamSoHThongForm)form;

            String strid = null;
            String strmota = null;
            String strma = null;
            if (f != null) {
                strmota = f.getMo_ta();
                strma = f.getTen_ts();
                if (strmota != null) {
                    sbSubHTML.append("<input type=\"hidden\" name=\"mo_ta\" value=\"" +
                                     strmota + "\" id=\"mo_ta\"></input>");
                }
                if (strma != null) {
                    sbSubHTML.append("<input type=\"hidden\" name=\"ten_ts\" value=\"" +
                                     strma + "\" id=\"ten_ts\"></input>");
                }
            }
            String strWhere = null;
            Vector v_param = null;
            if (strid != null && !strid.equals("")) {
                strWhere = "and a.id=? ";
                v_param = new Vector();
                v_param.add(new Parameter(strid, true));
            }
            if (strmota != null && !strmota.equals("")) {

                strWhere =
                        "and lower(a.mo_ta) like lower('%" + strmota + "%') ";
            } else {
                strWhere = "";
            }
            if (strma != null && !strma.equals("")) {
                strWhere +=
                        " and lower(a.ten_ts) like lower('%" + strma.trim() +
                        "%') ";
            }
            ThamSoHThongDAO tshtdao = new ThamSoHThongDAO(conn);
            ResultSet lstTS = null;
            lstTS = tshtdao.getThamSoLSuResultSet(strWhere, v_param);

            //print
            JasperPrint jasperPrint = null;
            HashMap parameterMap = null;
            reportStream =
                    getServlet().getServletConfig().getServletContext().getResourceAsStream(AppConstants.REPORT_DIRECTORY +
                                                                                            "/LSuTSoHT" +
                                                                                            ".jasper");
            JRDataSource jrDS = new JRResultSetDataSource(lstTS);
            jasperPrint =
                    JasperFillManager.fillReport(reportStream, parameterMap,
                                                 jrDS);

            String strTypePrintAction =
                request.getParameter(AppConstants.REQUEST_ACTION) == null ?
                "" :
                request.getParameter(AppConstants.REQUEST_ACTION).toString();
            String strActionName = "ThamSoHTLSuPrintAction.do";
            String strParameter = "";
            String strPathFont =
                getServlet().getServletContext().getContextPath() +
                AppConstants.REPORT_DIRECTORY + AppConstants.FONT_FOR_REPORT;
            ReportUtility rpUtilites = new ReportUtility();
            rpUtilites.exportReport(jasperPrint, strTypePrintAction, response,
                                    fileName, strPathFont, strActionName,
                                    sbSubHTML.toString(), strParameter);
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
