package com.seatech.tp.ttlaigoc.action;

import com.seatech.framework.AppConstants;
import com.seatech.framework.common.jsp.PagingBean;
import com.seatech.framework.strustx.AppAction;
import com.seatech.framework.utils.StringUtil;
import com.seatech.tp.qlytp.action.QuanLyTPCPDelegate;
import com.seatech.tp.qlytp.vo.QuanLyTPCPVO;
import com.seatech.tp.ttindthau.action.QLyTTinDauThauDelegate;

import com.seatech.tp.ttindthau.form.ThongTinDauThauForm;
import com.seatech.tp.ttindthau.vo.ThongTinDauThauVO;
import com.seatech.tp.ttlaigoc.form.TTLaiGocForm;

import com.seatech.tp.ttlaigoc.vo.TTLaiGocVo;

import java.sql.Connection;

import java.util.ArrayList;
import java.util.Collection;
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

public class TTLaiGocVndAction extends AppAction {
    private static String SUCCESS = "success";
    private static String FAILURE = "failure";

    public ActionForward list(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {

        Connection conn = null;
        try {
            conn = getConnection();
            TTLaiGocForm f = (TTLaiGocForm)form;
            f.reset(mapping, request);
            return executeAction(mapping, form, request, response);
        } catch (Exception e) {
            throw e;
        } finally {
            close(conn);
        }
    }

    protected ActionForward executeAction(ActionMapping mapping,
                                          ActionForm form,
                                          HttpServletRequest request,
                                          HttpServletResponse response) throws Exception {
        Connection conn = null;
        try {
            conn = getConnection(request);
            QuanLyTPCPDelegate delegate = new QuanLyTPCPDelegate(conn);
            List listTPCP = new ArrayList();
            listTPCP = (List)delegate.getAllListTPCP();
            Iterator ito = listTPCP.iterator();
            QuanLyTPCPVO vo = null;
            List<String> arrDot_DT = new ArrayList<String>();
            while (ito.hasNext()) {
                vo = (QuanLyTPCPVO)ito.next();
                if (vo.getMa_tp() != null && !vo.getMa_tp().equals("")) {
                    arrDot_DT.add(vo.getMa_tp());
                }
            }
            request.setAttribute("lstAllDotDT", arrDot_DT);
        } catch (Exception e) {
            throw e;
        } finally {
            close(conn);
        }
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
            TTLaiGocVo vo = new TTLaiGocVo();
            BeanUtils.copyProperties(vo, f);
            int phantrang = (AppConstants.APP_NUMBER_ROW_ON_PAGE);
            // khai bao bien phan trang.
            String page = f.getPageNumber();
            if (page == null)
                page = "1";
            Integer currentPage = new Integer(page);
            Integer numberRowOnPage = phantrang;
            Integer totalCount[] = new Integer[1];
            TTLaiGocDelegate delegate = new TTLaiGocDelegate(conn);
            List listLenhTraNo = new ArrayList();
          
            listLenhTraNo =
                    (List)delegate.getListTTDTPaging(vo, currentPage, numberRowOnPage,
                                                     totalCount);
            
            QuanLyTPCPDelegate delegatetpcp = new QuanLyTPCPDelegate(conn);
            List listTPCP = new ArrayList();
            listTPCP = (List)delegatetpcp.getAllListTPCP();
            Iterator ito = listTPCP.iterator();
            QuanLyTPCPVO votp = null;
            List<String> arrDot_DT = new ArrayList<String>();
            while (ito.hasNext()) {
                votp = (QuanLyTPCPVO)ito.next();
                if (votp.getMa_tp() != null && !votp.getMa_tp().equals("")) {
                    arrDot_DT.add(votp.getMa_tp());
                }
            }
            PagingBean pagingBean = new PagingBean();
            pagingBean.setCurrentPage(currentPage);
            pagingBean.setNumberOfRow((totalCount[0] == null) ? 0 :
                                      totalCount[0].intValue());
            pagingBean.setRowOnPage(numberRowOnPage);
            request.setAttribute("lstAllDotDT", arrDot_DT);
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
