package com.seatech.tp.sotonghoptpcp.action;

import com.seatech.framework.AppConstants;
import com.seatech.framework.common.jsp.PagingBean;
import com.seatech.framework.datamanager.ReportUtility;
import com.seatech.framework.strustx.AppAction;

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

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class SoTongHopTpcpAction extends AppAction {
    private static String SUCCESS = "success";
    //private static String FAILURE = "failure";


    public ActionForward list(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        Connection conn = null;
        try {
            SoTongHopTpcpForm f = (SoTongHopTpcpForm)form;
            f.reset(mapping, request);
            request.setAttribute("lstSoTongHopTpcp", new ArrayList());
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
            SoTongHopTpcpForm f = (SoTongHopTpcpForm)form;
            int phantrang = (AppConstants.APP_NUMBER_ROW_ON_PAGE);
            // khai bao bien phan trang.
            String page = f.getPageNumber();
            if (page == null)
                page = "1";
            Integer currentPage = new Integer(page);
            Integer numberRowOnPage = phantrang;
            Integer totalCount[] = new Integer[1];
            SoTongHopTpcpVO vo = new SoTongHopTpcpVO();
            BeanUtils.copyProperties(vo, f);
            vo.setPhuong_thuc_tong_hop(f.getPhuong_thuc_tong_hop());
            vo.setNgay_phat_hanh_tu_ngay(f.getNgay_phat_hanh_tu_ngay());
            vo.setNgay_phat_hanh_den_ngay(f.getNgay_phat_hanh_den_ngay());
            SoTongHopTpcpDelegate delegate = new SoTongHopTpcpDelegate(conn);
            List lstSoTongHopTpcp = null;
            lstSoTongHopTpcp =
                    (List)delegate.getListSoTongHopTpcpPaging(vo, currentPage,
                                                              numberRowOnPage,
                                                              totalCount);
            PagingBean pagingBean = new PagingBean();
            pagingBean.setCurrentPage(currentPage);
            pagingBean.setNumberOfRow((totalCount[0] == null) ? 0 :
                                      totalCount[0].intValue());
            pagingBean.setRowOnPage(numberRowOnPage);
            request.setAttribute("PAGE_KEY", pagingBean);
            request.setAttribute("lstSoTongHopTpcp", lstSoTongHopTpcp);
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
    public static final String fileName = "/SoTongHopTpcp";

    public ActionForward printAction(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response) throws Exception {
        String reportName = "/SoTongHopTpcp";
        Connection conn = null;
        InputStream reportStream = null;
        StringBuffer sbSubHTML = new StringBuffer();
        try {
            conn = getConnection();
            SoTongHopTpcpForm f = (SoTongHopTpcpForm)form;
            SoTongHopTpcpDelegate delegate = new SoTongHopTpcpDelegate(conn);
            SoTongHopTpcpVO vo = new SoTongHopTpcpVO();
            BeanUtils.copyProperties(vo, f);

            sbSubHTML.append("<input type=\"hidden\" name=\"phuong_thuc_tong_hop\" value=\"" +
                             f.getPhuong_thuc_tong_hop() +
                             "\" id=\"phuong_thuc_tong_hop\"></input>");
            sbSubHTML.append("<input type=\"hidden\" name=\"ngay_phat_hanh_tu_ngay\" value=\"" +
                             f.getNgay_phat_hanh_tu_ngay() +
                             "\" id=\"ngay_phat_hanh_tu_ngay\"></input>");
            sbSubHTML.append("<input type=\"hidden\" name=\"ngay_phat_hanh_den_ngay\" value=\"" +
                             f.getNgay_phat_hanh_den_ngay() +
                             "\" id=\"ngay_phat_hanh_den_ngay\"></input>");

            ResultSet rsNSD = null;
            //rsNSD = delegate.getSoTongHopTpcpList(vo);
            if (rsNSD == null) {
                JasperPrint jasperPrint = null;
                Map<String, Object> parameterMap =
                    new HashMap<String, Object>();
                parameterMap.put("pTuNgay", f.getNgay_phat_hanh_tu_ngay());
                parameterMap.put("pDenNgay", f.getNgay_phat_hanh_den_ngay());
                parameterMap.put("pPhuongThuc", f.getPhuong_thuc_tong_hop());
                parameterMap.put("IS_IGNORE_PAGINATION", true);
                reportStream =
                        getServlet().getServletConfig().getServletContext().getResourceAsStream(REPORT_DIRECTORY +
                                                                                                reportName +
                                                                                                ".jasper");
                JRDataSource jrDS = new JRResultSetDataSource(rsNSD);
                jasperPrint =
                        JasperFillManager.fillReport(reportStream, parameterMap,conn);

                ReportUtility rpUtilites = new ReportUtility();

                String strTypePrintAction =
                    request.getParameter(AppConstants.REQUEST_ACTION) == null ?
                    "" :
                    request.getParameter(AppConstants.REQUEST_ACTION).toString();
                String strActionName = "PrintSoTongHopTpcpAction.do";
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
