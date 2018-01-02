package com.seatech.tp.bcsolieuhuydongvon.action;

import com.seatech.framework.AppConstants;
import com.seatech.framework.common.jsp.PagingBean;
import com.seatech.framework.datamanager.ReportUtility;
import com.seatech.framework.strustx.AppAction;
import com.seatech.tp.bcsolieuhuydongvon.form.BCSLHuyDongVonForm;
import com.seatech.tp.bcsolieuhuydongvon.vo.BCSLHuyDongVonVo;
import com.seatech.tp.sotonghoptpcp.action.SoTongHopTpcpDelegate;
import com.seatech.tp.sotonghoptpcp.form.SoTongHopTpcpForm;
import com.seatech.tp.sotonghoptpcp.vo.SoTongHopTpcpVO;

import java.io.InputStream;

import java.sql.Connection;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class BCSLHuyDongVonAction extends AppAction {
    private static String SUCCESS = "success";
    //private static String FAILURE = "failure";


    public ActionForward list(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        Connection conn = null;
        try {
            BCSLHuyDongVonForm f = (BCSLHuyDongVonForm)form;
            f.reset(mapping, request);
            request.setAttribute("lstBCSLHuyDongVon", new ArrayList());
            return mapping.findForward(SUCCESS);
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
            BCSLHuyDongVonForm f = (BCSLHuyDongVonForm)form;
            int phantrang = (AppConstants.APP_NUMBER_ROW_ON_PAGE);
            // khai bao bien phan trang.
            String page = f.getPageNumber();
            if (page == null)
                page = "1";
            Integer currentPage = new Integer(page);
            Integer numberRowOnPage = phantrang;
            Integer totalCount[] = new Integer[1];
            BCSLHuyDongVonVo vo = new BCSLHuyDongVonVo();
            BeanUtils.copyProperties(vo, f);
            //vo.setPhuong_thuc_tong_hop(f.getPhuong_thuc_tong_hop());
            vo.setNgay_phat_hanh_tu_ngay(f.getNgay_phat_hanh_tu_ngay());
            vo.setNgay_phat_hanh_den_ngay(f.getNgay_phat_hanh_den_ngay());
            BCSLHuyDongVonDelegate delegate = new BCSLHuyDongVonDelegate(conn);
            List lstBCSLHuyDongVon = null;
            lstBCSLHuyDongVon =
                    (List)delegate.getBCSLHuyDongVonPaging(vo, currentPage,
                                                           numberRowOnPage,
                                                           totalCount);
            PagingBean pagingBean = new PagingBean();
            pagingBean.setCurrentPage(currentPage);
            pagingBean.setNumberOfRow((totalCount[0] == null) ? 0 :
                                      totalCount[0].intValue());
            pagingBean.setRowOnPage(numberRowOnPage);
            request.setAttribute("PAGE_KEY", pagingBean);
            request.setAttribute("lstBCSLHuyDongVon", lstBCSLHuyDongVon);
        } catch (Exception e) {
            conn.rollback();
            throw e;
        } finally {
            close(conn);
        }
        return mapping.findForward(SUCCESS);
    }

    public static final String REPORT_DIRECTORY = "/report";
    public static final String strFontTimeRoman = "/font/times.ttf";
    public static final String fileName = "/BCSLHuyDongVon";

    public ActionForward printAction(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response) throws Exception {
        String reportName = "/BCSLHuyDongVon";
        Connection conn = null;
        InputStream reportStream = null;
        InputStream subReportStream = null;
        StringBuffer sbSubHTML = new StringBuffer();
        try {
            conn = getConnection();
            BCSLHuyDongVonForm f = (BCSLHuyDongVonForm)form;
            BCSLHuyDongVonDelegate delegate = new BCSLHuyDongVonDelegate(conn);
            BCSLHuyDongVonVo vo = new BCSLHuyDongVonVo();
            BeanUtils.copyProperties(vo, f);
            sbSubHTML.append("<input type=\"hidden\" name=\"ngay_phat_hanh_tu_ngay\" value=\"" +
                             f.getNgay_phat_hanh_tu_ngay() +
                             "\" id=\"ngay_phat_hanh_tu_ngay\"></input>");
            sbSubHTML.append("<input type=\"hidden\" name=\"ngay_phat_hanh_den_ngay\" value=\"" +
                             f.getNgay_phat_hanh_den_ngay() +
                             "\" id=\"ngay_phat_hanh_den_ngay\"></input>");

           // ResultSet rsBC = null;
            //rsBC = delegate.getBCSLHuyDongVonList(vo);
           // if (rsBC != null) {
                JasperPrint jasperPrint = null;
                Map<String, Object> parameterMap =
                    new HashMap<String, Object>();
                parameterMap.put("pTuNgay", f.getNgay_phat_hanh_tu_ngay());
                parameterMap.put("pDenNgay", f.getNgay_phat_hanh_den_ngay());
                reportStream =
                        getServlet().getServletConfig().getServletContext().getResourceAsStream(REPORT_DIRECTORY +
                                                                                                reportName +
                                                                                                ".jasper");
            subReportStream = getServlet().getServletConfig().getServletContext().getResourceAsStream(REPORT_DIRECTORY + "/BCSLHuyDongVon_subreport.jasper");               
            //jasperPrint = JasperFillManager.fillReport(reportStream, parameterMap, conn);
            //JasperReport subReport = (JasperReport)JRLoader.loadObject(subReportStream);
            //parameterMap.put("subReport", subReport);
                //JRDataSource jrDS = new JRResultSetDataSource(rsBC);
                jasperPrint =
                        JasperFillManager.fillReport(reportStream, parameterMap,
                                                     conn);

                ReportUtility rpUtilites = new ReportUtility();

                String strTypePrintAction =
                    request.getParameter(AppConstants.REQUEST_ACTION) == null ?
                    "" :
                    request.getParameter(AppConstants.REQUEST_ACTION).toString();
                String strActionName = "PrintBCSLHuyDongVonAction.do";
                String strParameter = "";
                String strPathFont =
                    getServlet().getServletContext().getContextPath() +
                    REPORT_DIRECTORY + strFontTimeRoman;

                rpUtilites.exportReport(jasperPrint, strTypePrintAction,
                                        response, fileName, strPathFont,
                                        strActionName, sbSubHTML.toString(),
                                        strParameter);

           // }

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
