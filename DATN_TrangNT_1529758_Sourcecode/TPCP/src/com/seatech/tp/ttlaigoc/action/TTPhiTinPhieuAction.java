package com.seatech.tp.ttlaigoc.action;

import com.seatech.framework.AppConstants;
import com.seatech.framework.common.jsp.PagingBean;
import com.seatech.framework.strustx.AppAction;
import com.seatech.tp.ttlaigoc.form.TTLaiGocForm;

import java.sql.Connection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class TTPhiTinPhieuAction extends AppAction {
    private static String SUCCESS = "success";
    private static String FAILURE = "failure";
    public ActionForward list(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
        //check quyen
        return mapping.findForward(SUCCESS);
    }
    public ActionForward search(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
        //check quyen
        Connection conn = null;
        try {
            conn = getConnection(request);
            TTLaiGocForm f = (TTLaiGocForm)form;
            int phantrang = (AppConstants.APP_NUMBER_ROW_ON_PAGE);
            // khai bao bien phan trang.
            String page = f.getPageNumber();
            if (page == null)
                page = "1";
            Integer currentPage = new Integer(page);
            Integer numberRowOnPage = phantrang;
            Integer totalCount[] = new Integer[1];
            
            //Map<String, Object> map
            Map<String, Object> map=new HashMap();
            map.put("tu_ngay_tcph", f.getNgay_to_chuc_ph_tu_ngay());
            map.put("den_ngay_tcph", f.getNgay_to_chuc_ph_den_ngay());
            map.put("tu_ngay_ph", f.getNgay_ph_tu_ngay());
            map.put("den_ngay_ph", f.getNgay_ph_den_ngay());
            TTPhiTinPhieuDelegate delegate = new TTPhiTinPhieuDelegate(conn);
            List listLenhTraNo = new ArrayList();
            listLenhTraNo = (List)delegate.getListTTPhiTinPhieu(map, currentPage, numberRowOnPage,
                                                     totalCount);
            PagingBean pagingBean = new PagingBean();
            pagingBean.setCurrentPage(currentPage);
            pagingBean.setNumberOfRow((totalCount[0] == null) ? 0 :
                                      totalCount[0].intValue());
            pagingBean.setRowOnPage(numberRowOnPage);
            request.setAttribute("PAGE_KEY", pagingBean);
            request.setAttribute("listLenhTraNo", listLenhTraNo);
        } catch (Exception e) {
            throw e;
        } finally {
            close(conn);
        }
        return mapping.findForward(SUCCESS);
    }
}
