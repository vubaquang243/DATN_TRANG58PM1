package com.seatech.tp.kqduthau.action;

import com.seatech.framework.AppConstants;
import com.seatech.framework.common.jsp.PagingBean;
import com.seatech.framework.strustx.AppAction;
import com.seatech.framework.utils.StringUtil;
import com.seatech.tp.kqduthau.form.QLyKQDuThauForm;
import com.seatech.tp.kqduthau.vo.QLyKQDuThauCTietVO;
import com.seatech.tp.kqduthau.vo.QLyKQDuThauVO;
import com.seatech.tp.qlytp.action.QuanLyTPCPDelegate;

import java.sql.Connection;

import java.util.ArrayList;


import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.commons.beanutils.BeanUtils;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class QLyKQDuThauPDAction extends AppAction {
    private static String SUCCESS = "success";
    private static String FAILURE = "failure";

    protected ActionForward executeAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Connection conn = null;
        try {
            conn = getConnection(request);
        } catch (Exception e) {
            throw e;
        } finally {
            close(conn);
        }
        return mapping.findForward(SUCCESS);
    }

    public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Connection conn = null;
        try {
            QLyKQDuThauForm f = (QLyKQDuThauForm)form;
            f.reset(mapping, request);
            f.setTrang_thai("01");
            return search(mapping, form, request, response);
        } catch (Exception e) {
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
            QLyKQDuThauForm f = (QLyKQDuThauForm)form;
            int phantrang = (AppConstants.APP_NUMBER_ROW_ON_PAGE);
            // khai bao bien phan trang.
            String page = f.getPageNumber();
            if (page == null)
                page = "1";
            Integer currentPage = new Integer(page);
            Integer numberRowOnPage = phantrang;
            Integer totalCount[] = new Integer[1];
            QLyKQDuThauVO vo = new QLyKQDuThauVO();
            BeanUtils.copyProperties(vo, f);
            QLyKQDuThauDelegate delegate = new QLyKQDuThauDelegate(conn);
            //get list KQDT
            List listKQDT = null;
            listKQDT = (List)delegate.getListKQDTPaging_PD(vo, currentPage, numberRowOnPage, totalCount);
            Iterator ito = listKQDT.iterator();
            Collection resultKQDT = new ArrayList();
            while (ito.hasNext()) {
                vo = (QLyKQDuThauVO)ito.next();
                if (vo.getKl_goi_thau() != null) {
                    String khoiLuong = vo.getKl_goi_thau();
                    vo.setKl_goi_thau(StringUtil.convertNumberToString(khoiLuong, "VND"));
                }
                resultKQDT.add(vo);
            }
            PagingBean pagingBean = new PagingBean();
            pagingBean.setCurrentPage(currentPage);
            pagingBean.setNumberOfRow((totalCount[0] == null) ? 0 : totalCount[0].intValue());
            pagingBean.setRowOnPage(numberRowOnPage);
            request.setAttribute("PAGE_KEY", pagingBean);
            request.setAttribute("listKQDT", resultKQDT);

        } catch (Exception e) {
            throw e;

        } finally {
            close(conn);
        }
        return mapping.findForward(SUCCESS);
    }

}

