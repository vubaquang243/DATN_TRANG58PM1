package com.seatech.tp.qlytinphieu.action;

import com.seatech.framework.AppConstants;
import com.seatech.framework.common.jsp.PagingBean;
import com.seatech.framework.strustx.AppAction;
import com.seatech.framework.utils.StringUtil;
import com.seatech.tp.dmkyhan.action.DMKyHanDelegate;
import com.seatech.tp.qlytinphieu.form.HDBanTinPhieuForm;
import com.seatech.tp.qlytinphieu.vo.HDBanTinPhieuVo;

import com.seatech.tp.qlytp.action.QuanLyTPCPDelegate;

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

public class HDBanTinPhieuPheDuyetAction extends AppAction {
    private static String SUCCESS = "success";
    private static String FAILURE = "failure";

    public HDBanTinPhieuPheDuyetAction() {
        super();
    }

    public ActionForward list(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        try {
            HDBanTinPhieuForm f = (HDBanTinPhieuForm)form;
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
            HDBanTinPhieuForm f = (HDBanTinPhieuForm)form;
            int phantrang = (AppConstants.APP_NUMBER_ROW_ON_PAGE);
            // khai bao bien phan trang.
            String page = f.getPageNumber();
            if (page == null)
                page = "1";
            Integer currentPage = new Integer(page);
            Integer numberRowOnPage = phantrang;
            Integer totalCount[] = new Integer[1];
            HDBanTinPhieuVo vo = new HDBanTinPhieuVo();
            HDBanTinPhieuDelegate delegate = new HDBanTinPhieuDelegate(conn);
            List allListHD = null;
            allListHD =(List)delegate.getLisHDTin_PhieuPDPaging(vo, currentPage,
                                                         numberRowOnPage,
                                                         totalCount);
           

            QuanLyTPCPDelegate delegatetp = new QuanLyTPCPDelegate(conn);
            List listTPCP = new ArrayList();
            DMKyHanDelegate khDelegate = new DMKyHanDelegate(conn);
            Map<String, Object> map1 = new HashMap<String, Object>();
            map1.put("loai_tpcp", "TIN_PHIEU");
            List listKyHan = null;
            listKyHan = (List)khDelegate.getDMKyHan(map1);
            request.setAttribute("listKyHan", listKyHan);
              listTPCP = (List)delegatetp.getLstTPCP_TIN_PHIEU();
  
            request.setAttribute("lstAllTPCP", listTPCP);
            
            List listHDTinPhieu = null;
            f.setLai_suat(f.getLai_suat().replace(",","."));
            f.setKl_tp(f.getKl_tp().replace(".", "")); // replace
            BeanUtils.copyProperties(vo, f);
            listHDTinPhieu =
                    (List)delegate.getLisHDTin_PhieuPDPaging(vo, currentPage,
                                                             numberRowOnPage,
                                                             totalCount);
            Iterator ito = listHDTinPhieu.iterator();
            HDBanTinPhieuVo vo2 = null;
            Collection resultTTDT = new ArrayList();
            while (ito.hasNext()) {
                vo2 = (HDBanTinPhieuVo)ito.next();
                vo2.setKl_tp(StringUtil.convertNumberToString(vo2.getKl_tp(),
                                                              "VND"));
                vo2.setLai_suat(vo2.getLai_suat().replace(".", ","));
                resultTTDT.add(vo2);
            }

            PagingBean pagingBean = new PagingBean();
            pagingBean.setCurrentPage(currentPage);
            pagingBean.setNumberOfRow((totalCount[0] == null) ? 0 :
                                      totalCount[0].intValue());
            pagingBean.setRowOnPage(numberRowOnPage);
            request.setAttribute("allListHD",allListHD );
            request.setAttribute("PAGE_KEY", pagingBean);
            request.setAttribute("listHDTinPhieu", resultTTDT);

        } catch (Exception e) {
            throw e;

        } finally {
            close(conn);
        }
        return mapping.findForward(SUCCESS);
    }
}

