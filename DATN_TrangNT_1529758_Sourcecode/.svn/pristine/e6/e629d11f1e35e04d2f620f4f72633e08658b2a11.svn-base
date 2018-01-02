package com.seatech.tp.tttmua.action;

import com.seatech.framework.AppConstants;
import com.seatech.framework.common.jsp.PagingBean;
import com.seatech.framework.strustx.AppAction;
import com.seatech.framework.utils.StringUtil;
import com.seatech.tp.tttmua.form.QLyTToanTienMuaCTietForm;
import com.seatech.tp.tttmua.form.QLyTToanTienMuaForm;
import com.seatech.tp.tttmua.vo.QLyTToanTienMuaVO;
import com.seatech.tp.tttmua.vo.TTinDViSoHuuVO;
import com.seatech.tp.user.UserHistoryVO;

import java.math.BigDecimal;

import java.sql.Connection;

import java.util.ArrayList;
import java.util.Collection;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class TraCuuTToanTienMuaAction extends AppAction {
    private static String SUCCESS = "success";
    private static String FAILURE = "failure";

    public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            QLyTToanTienMuaForm f = (QLyTToanTienMuaForm)form;
            f.reset(mapping, request);
            request.setAttribute("lstDviSoHuu", new ArrayList());
            return search(mapping, form, request, response);
        } catch (Exception e) {
            throw e;
        }
    }

    public ActionForward search(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Connection conn = null;
        try {
            conn = getConnection(request);
            QLyTToanTienMuaForm f = (QLyTToanTienMuaForm)form;
            //set list dvi so huu
            int phantrang = (AppConstants.APP_NUMBER_ROW_ON_PAGE);
            // khai bao bien phan trang.
            String page = f.getPageNumber();
            if (page == null || page.equalsIgnoreCase(""))
                page = "1";
            Integer currentPage = new Integer(page);
            Integer numberRowOnPage = phantrang;
            Integer totalCount[] = new Integer[1];
            QLyTToanTienMuaDelegate delegate = new QLyTToanTienMuaDelegate(conn);
            Collection lstDviSoHuu = new ArrayList();
            QLyTToanTienMuaVO vo = new QLyTToanTienMuaVO();
            BeanUtils.copyProperties(vo, f);
            lstDviSoHuu = delegate.traCuuTTinTToan(vo, currentPage, numberRowOnPage, totalCount);
            request.setAttribute("lstDviSoHuu", lstDviSoHuu);

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
}
