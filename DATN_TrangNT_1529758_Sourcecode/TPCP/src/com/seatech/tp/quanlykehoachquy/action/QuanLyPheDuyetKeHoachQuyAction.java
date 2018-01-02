package com.seatech.tp.quanlykehoachquy.action;

import com.seatech.framework.AppConstants;
import com.seatech.framework.common.jsp.PagingBean;
import com.seatech.framework.exception.TPCPException;
import com.seatech.framework.strustx.AppAction;
import com.seatech.framework.utils.StringUtil;
import com.seatech.tp.quanlykehoachquy.form.QuanLyKeHoachQuyForm;
import com.seatech.tp.quanlykehoachquy.vo.QuanLyKeHoachQuyVO;
import com.seatech.tp.user.UserHistoryVO;

import java.math.BigDecimal;

import java.sql.Connection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class QuanLyPheDuyetKeHoachQuyAction extends AppAction {
    private static String SUCCESS = "success";
    private static String FAILURE = "failure";

    public QuanLyPheDuyetKeHoachQuyAction() {
        super();
    }
    public ActionForward list(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request, HttpServletResponse response) throws Exception{
        
        try{
            
            QuanLyKeHoachQuyForm f= (QuanLyKeHoachQuyForm)form;
            f.reset(mapping, request);
            f.setTrang_thai("01");
        }catch(Exception ex){
          throw ex;
        }
        return search(mapping,form,request,response);                            
    }
    public ActionForward search(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
        //check quyen

        Connection conn = null;
        try {

            conn = getConnection(request);
            QuanLyKeHoachQuyForm f = (QuanLyKeHoachQuyForm)form;
            int phantrang = (AppConstants.APP_NUMBER_ROW_ON_PAGE);
            // khai bao bien phan trang.
            String page = f.getPageNumber();
            if (page == null)
                page = "1";
            Integer currentPage = new Integer(page);
            Integer numberRowOnPage = phantrang;
            Integer totalCount[] = new Integer[1];
            QuanLyKeHoachQuyVO vo = new QuanLyKeHoachQuyVO();
            BeanUtils.copyProperties(vo, f);
            QuanLyKeHoachQuyDelegate delegate =
                new QuanLyKeHoachQuyDelegate(conn);
            List listKHQuy = null;
            listKHQuy =
                    (List)delegate.getListPD(vo, currentPage, numberRowOnPage,
                                             totalCount);
            Iterator ito = listKHQuy.iterator();
            Collection resultKH = new ArrayList();
            while (ito.hasNext()) {
                vo = (QuanLyKeHoachQuyVO)ito.next();
                if (vo.getTong_klph() != null) {
                    BigDecimal bTongKLTP;
                    bTongKLTP = new BigDecimal(vo.getTong_klph());
                    BigDecimal giatri;
                    if("VND".equals(vo.getLoai_tien())){
                     giatri = new BigDecimal(1000000000);
                    }else{
                      giatri = new BigDecimal(1000000);
                    }
                    
                    bTongKLTP = bTongKLTP.divide(giatri);
                    String khoiLuong = String.valueOf(bTongKLTP);
                    vo.setTong_klph(StringUtil.convertNumberToString(khoiLuong,
                                                                     "VND"));
                }
                resultKH.add(vo);
            }
            PagingBean pagingBean = new PagingBean();
            pagingBean.setCurrentPage(currentPage);
            pagingBean.setNumberOfRow((totalCount[0] == null) ? 0 :
                                      totalCount[0].intValue());
            pagingBean.setRowOnPage(numberRowOnPage);
            request.setAttribute("PAGE_KEY", pagingBean);
            request.setAttribute("lstKHQuy", resultKH);
        } catch (Exception e) {
            throw e;
        } finally {
            close(conn);
        }
        return mapping.findForward(SUCCESS);
    }

    public ActionForward update(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
        //        if (!checkPermissionOnFunction(request, "SYS.QLY_NSD.SUA")) {
        //            return mapping.findForward("errorQuyen");
        //        }
        Connection conn = null;
        try {
            conn = getConnection();
            String guid = request.getParameter("longid").trim();
            //check xem TPCP tồn tại?
            QuanLyKeHoachQuyDelegate delegate =
                new QuanLyKeHoachQuyDelegate(conn);
            QuanLyKeHoachQuyVO vo = delegate.getTpKeHoachObject(guid);
            if (vo == null) {
                saveMessage(request,
                            new TPCPException("qlykehoach.list.notfount"));
                return mapping.findForward(FAILURE);
            }
            QuanLyKeHoachQuyForm f = (QuanLyKeHoachQuyForm)form;
            BeanUtils.copyProperties(f, vo);
        } catch (Exception e) {
            throw e;
        } finally {
            close(conn);
        }
        return mapping.findForward(SUCCESS);
    }

    public ActionForward updateExc(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response) throws Exception {

        //        if (!checkPermissionOnFunction(request, "SYS.QLY_NSD.SUA")) {
        //            return mapping.findForward("errorQuyen");
        //        }
        Connection conn = null;
        try {
            conn = getConnection(request);
            HttpSession session = request.getSession();
            String nUserID =
                session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString();
            QuanLyKeHoachQuyForm f = (QuanLyKeHoachQuyForm)form;
            QuanLyKeHoachQuyVO vo = new QuanLyKeHoachQuyVO();
            BeanUtils.copyProperties(vo, f);
            QuanLyKeHoachQuyDelegate delegate =
                new QuanLyKeHoachQuyDelegate(conn);
            long idAdd = delegate.update(vo);
            //insert history
            UserHistoryVO userHisVO = new UserHistoryVO();
            userHisVO.setNguoi_tdoi(new Long(nUserID));
            userHisVO.setNoi_dung_thaydoi("Cập nhật ma TPKeHoach" + idAdd);
            userHisVO.setNsd_id(idAdd);
            delegate.insertHistoryUser(userHisVO);
            f.reset(mapping, request);
            saveMessage(request, new TPCPException("qlykehoach.update.succ"));
        } catch (Exception e) {
            throw e;
        } finally {
            close(conn);
        }
        return mapping.findForward(SUCCESS);

    }
}
