package com.seatech.tp.ttindthau.action;

import com.seatech.framework.AppConstants;
import com.seatech.framework.common.jsp.PagingBean;
import com.seatech.framework.strustx.AppAction;
import com.seatech.framework.utils.StringUtil;
import com.seatech.tp.qlytp.action.QuanLyTPCPDelegate;
import com.seatech.tp.ttindthau.form.ThongTinDauThauForm;
import com.seatech.tp.ttindthau.vo.ThongTinDauThauVO;

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

public class QLyPheDuyetTTinDauThauAction extends AppAction {
    private static String SUCCESS = "success";
    private static String FAILURE = "failure";

    public ActionForward list(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        try {
            ThongTinDauThauForm f = (ThongTinDauThauForm)form;
            f.reset(mapping, request);
            if (f.getTrang_thai() == null || f.getTrang_thai().equals("")) {
                f.setTrang_thai("01");
            }
            return search(mapping, form, request, response);
        } catch (Exception e) {
            throw e;
        }
    }

    public ActionForward search(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
        //check quyen

        Connection conn = null;
        try {

            conn = getConnection(request);
            ThongTinDauThauForm f = (ThongTinDauThauForm)form;
            
            int phantrang = (AppConstants.APP_NUMBER_ROW_ON_PAGE);
            // khai bao bien phan trang.
            String page = f.getPageNumber();
            if (page == null)
                page = "1";
            Integer currentPage = new Integer(page);
            Integer numberRowOnPage = phantrang;
            Integer totalCount[] = new Integer[1];
            ThongTinDauThauVO vo = new ThongTinDauThauVO();
            BeanUtils.copyProperties(vo, f);


            QLyTTinDauThauDelegate delegate = new QLyTTinDauThauDelegate(conn);
            List listTTDT = null;
            listTTDT =
                    (List)delegate.getListTTDT_PDPaging(vo, currentPage, numberRowOnPage,
                                                     totalCount);
            Iterator ito = listTTDT.iterator();
            ThongTinDauThauVO vo2 = null;
            Collection resultTTDT = new ArrayList();
            while (ito.hasNext()) {
                vo2 = (ThongTinDauThauVO)ito.next();
                String khoiLuong = vo2.getKhoi_luong_tp();
                vo2.setKhoi_luong_tp(StringUtil.convertNumberToString(khoiLuong,
                                                                      "VND"));
                resultTTDT.add(vo2);
            }
            PagingBean pagingBean = new PagingBean();
            pagingBean.setCurrentPage(currentPage);
            pagingBean.setNumberOfRow((totalCount[0] == null) ? 0 :
                                      totalCount[0].intValue());
            pagingBean.setRowOnPage(numberRowOnPage);
            request.setAttribute("PAGE_KEY", pagingBean);
            request.setAttribute("lstTTDT", resultTTDT);
            QuanLyTPCPDelegate delegateTP = new QuanLyTPCPDelegate(conn);
            List listTPCP = new ArrayList();
            listTPCP = (List)delegateTP.getAllListTPCP_DT();
            request.setAttribute("lstAllTPCP", listTPCP);
        } catch (Exception e) {
            throw e;

        } finally {
            close(conn);
        }
        return mapping.findForward(SUCCESS);
    }

    public QLyPheDuyetTTinDauThauAction() {
        super();
    }
    
}
