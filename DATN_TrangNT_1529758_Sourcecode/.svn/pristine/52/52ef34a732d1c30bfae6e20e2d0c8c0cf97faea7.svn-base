package com.seatech.tp.ttlaigoc.action;

import com.seatech.framework.AppConstants;
import com.seatech.framework.common.jsp.PagingBean;
import com.seatech.framework.strustx.AppAction;
import com.seatech.tp.qlytp.action.QuanLyTPCPDelegate;
import com.seatech.tp.qlytp.vo.QuanLyTPCPVO;
import com.seatech.tp.ttlaigoc.form.TTLaiGocForm;
import com.seatech.tp.ttlaigoc.vo.TTLaiGocVo;

import java.sql.Connection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class TTPhiHNXAction extends AppAction {
    private static String SUCCESS = "success";
    private static String FAILURE = "failure";
    public TTPhiHNXAction() {
        super();
    }
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
            map.put("ngay_ph_tu_ngay", f.getNgay_ph_tu_ngay());
            map.put("ngay_ph_den_ngay", f.getNgay_ph_den_ngay());
            map.put("ngay_to_chuc_ph_tu_ngay", f.getNgay_to_chuc_ph_tu_ngay());
            map.put("ngay_to_chuc_ph_den_ngay", f.getNgay_to_chuc_ph_den_ngay());
            TTPhiHNXDelegate delegate = new TTPhiHNXDelegate(conn);
            List listLenhTraNo = new ArrayList();
          
            listLenhTraNo = (List)delegate.getListTTPhiHNX(map, currentPage, numberRowOnPage,
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
