package com.seatech.tp.banletraiphieutw.action;

import com.seatech.framework.AppConstants;
import com.seatech.framework.common.jsp.PagingBean;
import com.seatech.framework.strustx.AppAction;
import com.seatech.framework.utils.StringUtil;
import com.seatech.tp.banletraiphieutw.form.BanLeTraiPhieuTwForm;
import com.seatech.tp.banletraiphieutw.vo.BanLeTraiPhieuTwVO;

import com.seatech.tp.qlytp.action.QuanLyTPCPDelegate;

import java.sql.Connection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class BanLeTraiPhieuTwPDAction extends AppAction {
    private static String SUCCESS = "success";
    //private static String FAILURE = "failure";


    public ActionForward list(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        Connection conn = null;
        try {
            BanLeTraiPhieuTwForm f = (BanLeTraiPhieuTwForm)form;
            f.reset(mapping, request);
            if(f.getTrang_thai()== null || f.getTrang_thai().equals("")){
            f.setTrang_thai("01");
            }
           
        } catch (Exception e) {
            conn.rollback();
            throw e;
        } finally {
            close(conn);
        }
      return search(mapping, form, request, response);
    }

    public ActionForward search(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
        //check quyen

        Connection conn = null;
        try {
            conn = getConnection(request);
            BanLeTraiPhieuTwForm f = (BanLeTraiPhieuTwForm)form;
            int phantrang = (AppConstants.APP_NUMBER_ROW_ON_PAGE);
            // khai bao bien phan trang.
            String page = f.getPageNumber();
            if (page == null)
                page = "1";
            QuanLyTPCPDelegate delegatetpcp = new QuanLyTPCPDelegate(conn);
            List listTPCP = new ArrayList();
            listTPCP = (List)delegatetpcp.getAllListTPCP_Ban_Le();
            request.setAttribute("listTPCP", listTPCP);
            Integer currentPage = new Integer(page);
            Integer numberRowOnPage = phantrang;
            Integer totalCount[] = new Integer[1];
            BanLeTraiPhieuTwVO vo = new BanLeTraiPhieuTwVO();
            BeanUtils.copyProperties(vo, f);
            BanLeTraiPhieuTwDelegate delegate =
                new BanLeTraiPhieuTwDelegate(conn);
            List lstBanLeTraiPhieuTw = null;
            lstBanLeTraiPhieuTw =
                    (List)delegate.getListBanLeTraiPhieuTwPagingPD(vo,
                                                                 currentPage,
                                                                 numberRowOnPage,
                                                                 totalCount);
            Iterator ito = lstBanLeTraiPhieuTw.iterator();
            Collection resultBanLe = new ArrayList();
            while (ito.hasNext()) {
                vo = (BanLeTraiPhieuTwVO)ito.next();
                if (vo.getKhoi_luong() != null) {
                    String khoiLuong = vo.getKhoi_luong();
                    vo.setKhoi_luong(StringUtil.convertNumberToString(khoiLuong,
                                                                      "VND"));
                }
                resultBanLe.add(vo);
            }
            PagingBean pagingBean = new PagingBean();
            pagingBean.setCurrentPage(currentPage);
            pagingBean.setNumberOfRow((totalCount[0] == null) ? 0 :
                                      totalCount[0].intValue());
            pagingBean.setRowOnPage(numberRowOnPage);
            request.setAttribute("PAGE_KEY", pagingBean);
            request.setAttribute("lstBanLeTraiPhieuTw", resultBanLe);
            
        } catch (Exception e) {
            conn.rollback();
            throw e;
        } finally {
            close(conn);
        }
        return mapping.findForward(SUCCESS);
    }
}
