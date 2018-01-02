package com.seatech.tp.dmtraichu.action;

import com.seatech.framework.AppConstants;
import com.seatech.framework.common.jsp.PagingBean;
import com.seatech.framework.exception.TPCPException;
import com.seatech.framework.strustx.AppAction;
import com.seatech.tp.dmtraichu.form.DMTraiChuForm;
import com.seatech.tp.dmtraichu.vo.DMTraiChuVO;
import com.seatech.tp.qlytp.form.QuanLyTPCPForm;
import com.seatech.tp.user.UserHistoryVO;

import java.sql.Connection;

import java.util.Collection;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class QLyDMTraiChuAction extends AppAction {
    private static String SUCCESS = "success";
    private static String FAILURE = "failure";

    public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Connection conn = null;
        try {
            DMTraiChuForm f = (DMTraiChuForm)form;
            f.reset(mapping, request);
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
            DMTraiChuForm f = (DMTraiChuForm)form;
            int phantrang = (AppConstants.APP_NUMBER_ROW_ON_PAGE);
            // khai bao bien phan trang.
            String page = f.getPageNumber();
            if (page == null)
                page = "1";
            Integer currentPage = new Integer(page);
            Integer numberRowOnPage = phantrang;
            Integer totalCount[] = new Integer[1];
            DMTraiChuVO vo = new DMTraiChuVO();
            BeanUtils.copyProperties(vo, f);
            DMTraiChuDelegate delegate = new DMTraiChuDelegate(conn);
            //get list KQDT
            Collection listDVSH = null;
            listDVSH = delegate.getDMTraiChuPaging(vo, currentPage, numberRowOnPage, totalCount);
            PagingBean pagingBean = new PagingBean();
            pagingBean.setCurrentPage(currentPage);
            pagingBean.setNumberOfRow((totalCount[0] == null) ? 0 : totalCount[0].intValue());
            pagingBean.setRowOnPage(numberRowOnPage);
            request.setAttribute("PAGE_KEY", pagingBean);
            request.setAttribute("listDVSH", listDVSH);

        } catch (Exception e) {
            throw e;
        } finally {
            close(conn);
        }
        return mapping.findForward(SUCCESS);
    }

    public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return mapping.findForward(SUCCESS);
    }

    public ActionForward addExc(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Connection conn = null;
        try {
            conn = getConnection(request);
            HttpSession session = request.getSession();
            String nUserID = session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString();
            DMTraiChuForm f = (DMTraiChuForm)form;
            DMTraiChuVO vo = new DMTraiChuVO();
            if (f.getBan_le().equals("on")) {
                f.setBan_le("1");
            } else
                f.setBan_le("0");
            BeanUtils.copyProperties(vo, f);
            vo.setNguoi_tao(nUserID);
            DMTraiChuDelegate delegate = new DMTraiChuDelegate(conn);
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("MA_CHU_SO_HUU", vo.getMa_chu_so_huu());
            DMTraiChuVO vo2 = delegate.getDMTraiChuVO(map);
            if (vo2 != null) {
                saveMessage(request, new TPCPException("qlydvsh.add.exist"));
                return mapping.findForward(FAILURE);
            }
            long idAdd = delegate.insertDVSH(vo);
            //insert history
            UserHistoryVO userHisVO = new UserHistoryVO();
            userHisVO.setNguoi_tdoi(new Long(nUserID));
            userHisVO.setNoi_dung_thaydoi("Them moi dvsh" + idAdd);
            userHisVO.setNsd_id(idAdd);
            delegate.insertHistoryUser(userHisVO);
            f.reset(mapping, request);
            saveMessage(request, new TPCPException("qlydvsh.add.succ"));
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            close(conn);
        }
        return mapping.findForward(SUCCESS);
    }

    public ActionForward updateExc(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Connection conn = null;
        try {
            conn = getConnection(request);
            HttpSession session = request.getSession();
            String nUserID = session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString();
            DMTraiChuForm f = (DMTraiChuForm)form;
            DMTraiChuVO vo = new DMTraiChuVO();
            if (f.getBan_le().equals("on")) {
                f.setBan_le("1");
            } else
                f.setBan_le("0");
            BeanUtils.copyProperties(vo, f);
            vo.setNguoi_tao(nUserID);
            DMTraiChuDelegate delegate = new DMTraiChuDelegate(conn);            
            long idAdd = delegate.insertDVSH(vo);
            //insert history
            UserHistoryVO userHisVO = new UserHistoryVO();
            userHisVO.setNguoi_tdoi(new Long(nUserID));
            userHisVO.setNoi_dung_thaydoi("Update dvsh" + idAdd);
            userHisVO.setNsd_id(idAdd);
            delegate.insertHistoryUser(userHisVO);
            f.reset(mapping, request);
            saveMessage(request, new TPCPException("qlydvsh.update.succ"));
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            close(conn);
        }
        return mapping.findForward(SUCCESS);
    }

    public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Connection conn = null;
        try {
            conn = getConnection();
            String guid = request.getParameter("longid").trim();
            //check xem DVSH tồn tại?
            DMTraiChuDelegate delegate = new DMTraiChuDelegate(conn);
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("GUID", guid);
            DMTraiChuVO vo = delegate.getDMTraiChuVO(map);
            if (vo == null) {
                saveMessage(request, new TPCPException("quanlytp.list.notfount"));
                return mapping.findForward(FAILURE);
            }
            DMTraiChuForm f = (DMTraiChuForm)form;
            BeanUtils.copyProperties(f, vo);
        } catch (Exception e) {
            throw e;
        } finally {
            close(conn);
        }
        return mapping.findForward(SUCCESS);
    }

    public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Connection conn = null;
        String errMess = "";
        try {
            conn = getConnection();
            HttpSession session = request.getSession();
            String nUserID = session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString();
            String guid = request.getParameter("longid").trim();
            //check xem TPCP tồn tại?
            DMTraiChuDelegate delegate = new DMTraiChuDelegate(conn);
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("GUID", guid);
            DMTraiChuVO vo = delegate.getDMTraiChuVO(map);
            if (vo == null) {
                saveMessage(request, new TPCPException("quanlytp.list.notfount"));
                return mapping.findForward(FAILURE);
            } else {
                DMTraiChuForm f = (DMTraiChuForm)form;
                long idAdd = delegate.delete(vo);
                //insert history
                UserHistoryVO userHisVO = new UserHistoryVO();
                userHisVO.setNguoi_tdoi(new Long(nUserID));
                userHisVO.setNoi_dung_thaydoi("Xoa ma TPCP " + idAdd);
                userHisVO.setNsd_id(idAdd);
                delegate.insertHistoryUser(userHisVO);
                f.reset(mapping, request);
                if (idAdd != 0) {
                    errMess = "qlydvsh.delete.succ";
                } else
                    errMess = "qlydvsh.delete.error";
                saveMessage(request, new TPCPException(errMess));
            }
        } catch (Exception e) {
            conn.rollback();
            throw e;
        } finally {
            close(conn);

        }
        return mapping.findForward(SUCCESS);
    }
}
