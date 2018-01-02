package com.seatech.tp.bcthanhtoantpcp.action;

import com.seatech.framework.AppConstants;
import com.seatech.framework.common.jsp.PagingBean;
import com.seatech.framework.datamanager.ReportUtility;
import com.seatech.framework.strustx.AppAction;

import com.seatech.tp.bcthanhtoantpcp.form.BCThanhToanTpcpForm;

import com.seatech.tp.bcthanhtoantpcp.vo.BCThanhToanTpcpVo;

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
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import net.sf.jasperreports.engine.JasperReport;

import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class BCThanhToanTpcpAction extends AppAction {

    private static String SUCCESS = "success";
    //private static String FAILURE = "failure";


    public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Connection conn = null;
        try {
            BCThanhToanTpcpForm f = (BCThanhToanTpcpForm)form;
            f.reset(mapping, request);
            request.setAttribute("lstThanhToanTpcp", new ArrayList());
            return mapping.findForward(SUCCESS);
        } catch (Exception e) {
            conn.rollback();
            throw e;
        } finally {
            close(conn);
        }
    }

    public ActionForward search(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        //check quyen

        Connection conn = null;
        try {
            conn = getConnection(request);
            BCThanhToanTpcpForm f = (BCThanhToanTpcpForm)form;
            int phantrang = (AppConstants.APP_NUMBER_ROW_ON_PAGE);
            // khai bao bien phan trang.
            String page = f.getPageNumber();
            if (page == null)
                page = "1";
            Integer currentPage = new Integer(page);
            Integer numberRowOnPage = phantrang;
            Integer totalCount[] = new Integer[1];
            BCThanhToanTpcpVo vo = new BCThanhToanTpcpVo();
            BeanUtils.copyProperties(vo, f);
            ;
            BCThanhToanTpcpDelegate delegate = new BCThanhToanTpcpDelegate(conn);
            List lstThanhToanTpcp = null;
            lstThanhToanTpcp = (List)delegate.getBCThanhToanTpcpPaging(vo, currentPage, numberRowOnPage, totalCount);
            PagingBean pagingBean = new PagingBean();
            pagingBean.setCurrentPage(currentPage);
            pagingBean.setNumberOfRow((totalCount[0] == null) ? 0 : totalCount[0].intValue());
            pagingBean.setRowOnPage(numberRowOnPage);
            request.setAttribute("PAGE_KEY", pagingBean);
            request.setAttribute("lstThanhToanTpcp", lstThanhToanTpcp);
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
    public static final String fileName = "/BCThanhToanTpcp";

    public ActionForward printAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String reportName = "/BCHuyDongVonKyHan";
        Connection conn = null;
        InputStream reportStream = null;
        InputStream subReportStream = null;
        StringBuffer sbSubHTML = new StringBuffer();
        try {
            conn = getConnection();
            BCThanhToanTpcpForm f = (BCThanhToanTpcpForm)form;
            sbSubHTML.append("<input type=\"hidden\" name=\"ngay_dau_thau_tu_ngay\" value=\"" + f.getNgay_dau_thau_tu_ngay() + "\" id=\"ngay_dau_thau_tu_ngay\"></input>");
            sbSubHTML.append("<input type=\"hidden\" name=\"ngay_dau_thau_den_ngay\" value=\"" + f.getNgay_dau_thau_den_ngay() + "\" id=\"ngay_dau_thau_den_ngay\"></input>");
            ResultSet rsBC = null;
            //rsBC = delegate.getBCThanhToanTpcpList(vo);
            //          if (rsBC != null) {
            JasperPrint jasperPrint = null;
            Map<String, Object> parameterMap = new HashMap<String, Object>();
            parameterMap.put("pTuNgay", f.getNgay_dau_thau_tu_ngay());
            parameterMap.put("pDenNgay", f.getNgay_dau_thau_den_ngay());
            reportStream = getServlet().getServletConfig().getServletContext().getResourceAsStream(REPORT_DIRECTORY + reportName + ".jasper");
            subReportStream = getServlet().getServletConfig().getServletContext().getResourceAsStream(REPORT_DIRECTORY + "/BCThanhToanTpcp_subreport.jasper");               
            
            //JasperReport subReport = (JasperReport)JRLoader.loadObject(subReportStream);
            //parameterMap.put("subReport", subReport);
            ReportUtility rpUtilites = new ReportUtility();
            jasperPrint = JasperFillManager.fillReport(reportStream, parameterMap, conn);
            String strTypePrintAction = request.getParameter(AppConstants.REQUEST_ACTION) == null ? "" : request.getParameter(AppConstants.REQUEST_ACTION).toString();
            String strActionName = "PrintBCThanhToanTPCPAction.do";
            String strParameter = "";
            String strPathFont = getServlet().getServletContext().getContextPath() + REPORT_DIRECTORY + strFontTimeRoman;

            rpUtilites.exportReport(jasperPrint, strTypePrintAction, response, fileName, strPathFont, strActionName, sbSubHTML.toString(), strParameter);

            //          }

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
