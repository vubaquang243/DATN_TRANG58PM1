package com.seatech.tp.kqphathanh.action;

import com.seatech.framework.AppConstants;
import com.seatech.framework.common.jsp.PagingBean;
import com.seatech.framework.exception.TPCPException;
import com.seatech.framework.strustx.AppAction;
import com.seatech.framework.utils.StringUtil;
import com.seatech.tp.kqphathanh.form.QLyKQPhatHanhForm;
import com.seatech.tp.kqphathanh.vo.QLyKQPhatHanhCTiet_SoHuuVO;
import com.seatech.tp.kqphathanh.vo.QLyKQPhatHanhCTiet_TpcpVO;
import com.seatech.tp.kqphathanh.vo.QLyKQPhatHanhVO;

import java.io.ByteArrayInputStream;

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

public class QLyKQPhatHanhPDAction extends AppAction {
    private static String SUCCESS = "success";
    private static String FAILURE = "failure";

    public ActionForward list(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        Connection conn = null;
        try {
            QLyKQPhatHanhForm f = (QLyKQPhatHanhForm)form;
            f.reset(mapping, request);
            f.setTrang_thai("01");
            return search(mapping, form, request, response);
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
            QLyKQPhatHanhForm f = (QLyKQPhatHanhForm)form;
            int phantrang = (AppConstants.APP_NUMBER_ROW_ON_PAGE);
            // khai bao bien phan trang.
            String page = f.getPageNumber();
            if (page == null)
                page = "1";
            Integer currentPage = new Integer(page);
            Integer numberRowOnPage = phantrang;
            Integer totalCount[] = new Integer[1];
            QLyKQPhatHanhVO vo = new QLyKQPhatHanhVO();
            BeanUtils.copyProperties(vo, f);
            QLyKQPhatHanhDelegate delegate = new QLyKQPhatHanhDelegate(conn);
            List lstKQphathanh = null;
            lstKQphathanh =
                    (List)delegate.getListKQPHPagingPD(vo, currentPage, numberRowOnPage,
                                                     totalCount);
            Iterator ito = lstKQphathanh.iterator();
            Collection resultKQPhathanh = new ArrayList();
            while (ito.hasNext()) {
                vo = (QLyKQPhatHanhVO)ito.next();
                if (vo.getTong_klph() != null) {
                    String khoiLuong = vo.getTong_klph();
                    vo.setTong_klph(StringUtil.convertNumberToString(khoiLuong,
                                                                     "VND"));
                }
              if (vo.getTong_kl_trung_thau() != null) {
                  String khoiLuong =vo.getTong_kl_trung_thau();
                  vo.setTong_kl_trung_thau(StringUtil.convertNumberToString(khoiLuong, "VND"));
              }
              if (vo.getLs_danh_nghia() != null) {
                  String ls_trungthau = vo.getLs_danh_nghia();
                  vo.setLs_danh_nghia(StringUtil.convertNumberToString(ls_trungthau,
                                                                       "VND"));
              }

              if (vo.getLs_trung_thau() != null) {
                  String ls_trungthau = vo.getLs_trung_thau();
                  vo.setLs_trung_thau(StringUtil.convertNumberToString(ls_trungthau,
                                                                       "VND"));
              }
                resultKQPhathanh.add(vo);
            }
            PagingBean pagingBean = new PagingBean();
            pagingBean.setCurrentPage(currentPage);
            pagingBean.setNumberOfRow((totalCount[0] == null) ? 0 :
                                      totalCount[0].intValue());
            pagingBean.setRowOnPage(numberRowOnPage);
            request.setAttribute("PAGE_KEY", pagingBean);
            request.setAttribute("lstKQphathanh", resultKQPhathanh);
        } catch (Exception e) {
            conn.rollback();
            throw e;
        } finally {
            close(conn);
        }
        return mapping.findForward(SUCCESS);
    }

}
