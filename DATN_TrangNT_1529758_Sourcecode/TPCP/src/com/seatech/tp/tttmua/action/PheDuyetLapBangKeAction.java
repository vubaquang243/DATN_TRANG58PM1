package com.seatech.tp.tttmua.action;

import com.seatech.framework.AppConstants;
import com.seatech.framework.common.jsp.PagingBean;
import com.seatech.framework.exception.TPCPException;
import com.seatech.framework.strustx.AppAction;
import com.seatech.framework.utils.StringUtil;
import com.seatech.tp.dmkyhan.action.DMKyHanDelegate;
import com.seatech.tp.qlytp.action.QuanLyTPCPDelegate;
import com.seatech.tp.ttindthau.action.QLyTTinDauThauDelegate;
import com.seatech.tp.ttindthau.vo.ThongTinDauThauVO;
import com.seatech.tp.tttmua.form.QLyLapBangKeForm;
import com.seatech.tp.tttmua.form.QLyTToanTienMuaCTietForm;
import com.seatech.tp.tttmua.form.QLyTToanTienMuaForm;
import com.seatech.tp.tttmua.vo.QLyLapBangKeCTietVO;
import com.seatech.tp.tttmua.vo.QLyLapBangKeVO;
import com.seatech.tp.tttmua.vo.QLyTToanTienMuaVO;

import com.seatech.tp.user.UserHistoryVO;

import java.sql.Connection;

import java.util.ArrayList;
import java.util.Collection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class PheDuyetLapBangKeAction extends AppAction {
    private static String SUCCESS = "success";
    private static String FAILURE = "failure";

    public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            QLyLapBangKeForm f = (QLyLapBangKeForm)form;
            f.reset(mapping, request);
            f.setTrang_thai("01");
            return search(mapping, form, request, response);
        } catch (Exception e) {
            throw e;
        }
    }

    public ActionForward search(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Connection conn = null;
        try {
            conn = getConnection(request);
            QLyLapBangKeForm f = (QLyLapBangKeForm)form;
            DMKyHanDelegate khDelegate = new DMKyHanDelegate(conn);
            //          Map<String, Object> map = new HashMap();
            //          map.put("loai_tpcp", "TRAI_PHIEU");
            List listKyHan = null;
            listKyHan = (List)khDelegate.getDMKyHan(null);
            request.setAttribute("listKyHan", listKyHan);
            QuanLyTPCPDelegate delegateTP = new QuanLyTPCPDelegate(conn);
            List listTPCP = new ArrayList();
            listTPCP = (List)delegateTP.getAllListTPCP();
            request.setAttribute("lstAllTPCP", listTPCP);
            int phantrang = (AppConstants.APP_NUMBER_ROW_ON_PAGE);
            String page = f.getPageNumber();
            if (page == null || page.equalsIgnoreCase(""))
                page = "1";
            Integer currentPage = new Integer(page);
            Integer numberRowOnPage = phantrang;
            Integer totalCount[] = new Integer[1];
            QLyLapBangKeDelegate delegate = new QLyLapBangKeDelegate(conn);
            Collection lstBKeXNhan = new ArrayList();
            QLyLapBangKeVO vo = new QLyLapBangKeVO();
            BeanUtils.copyProperties(vo, f);
            lstBKeXNhan = delegate.getListBangKeXacNhan(vo, currentPage, numberRowOnPage, totalCount);
            request.setAttribute("lstBKeXNhan", lstBKeXNhan);

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

    public ActionForward pheDuyetAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Connection conn = null;
        try {
            String errMess = "";
            conn = getConnection();
            HttpSession session = request.getSession();
            String nUserID = session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString();
            QLyLapBangKeForm f = (QLyLapBangKeForm)form;
            QLyLapBangKeVO vo = new QLyLapBangKeVO();
            vo.setLy_do_tu_choi("");
            vo.setGuid(f.getGuid());
            vo.setTrang_thai("02");
            vo.setNguoi_duyet(nUserID);
            vo.setNgay_duyet(StringUtil.getCurrentDate());
            QLyLapBangKeDelegate delegate = new QLyLapBangKeDelegate(conn);
            long idAdd = delegate.update(vo, "");
            //insert history
            UserHistoryVO userHisVO = new UserHistoryVO();
            userHisVO.setNguoi_tdoi(new Long(nUserID));
            userHisVO.setNoi_dung_thaydoi("Phe duyệt bang ke " + idAdd);
            userHisVO.setNsd_id(idAdd);
            delegate.insertHistoryUser(userHisVO);
            f.reset(mapping, request);
            errMess = "tpbkechuyentien.pduyet.succ";
            saveMessage(request, new TPCPException(errMess));
        } catch (Exception e) {
            throw e;
        } finally {
            close(conn);
        }
        return mapping.findForward(SUCCESS);
    }

    public ActionForward tuchoiAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Connection conn = null;
        try {
            String errMess = "";
            conn = getConnection();
            HttpSession session = request.getSession();
            String nUserID = session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString();
            QLyLapBangKeForm f = (QLyLapBangKeForm)form;
            QLyLapBangKeVO vo = new QLyLapBangKeVO();
            vo.setGuid(f.getGuid());
            vo.setLy_do_tu_choi(f.getLy_do_tu_choi());
            vo.setTrang_thai("03");
            vo.setNguoi_duyet(nUserID);
            vo.setNgay_duyet(StringUtil.getCurrentDate());
            QLyLapBangKeDelegate delegate = new QLyLapBangKeDelegate(conn);
            long idAdd = delegate.update(vo, "");
            //insert history
            UserHistoryVO userHisVO = new UserHistoryVO();
            userHisVO.setNguoi_tdoi(new Long(nUserID));
            userHisVO.setNoi_dung_thaydoi("Từ chối phê duyệt bảng kê " + idAdd);
            userHisVO.setNsd_id(idAdd);
            delegate.insertHistoryUser(userHisVO);
            f.reset(mapping, request);
            errMess = "tpbkechuyentien.tchoi.succ";
            saveMessage(request, new TPCPException(errMess));
        } catch (Exception e) {
            throw e;
        } finally {
            close(conn);
        }
        return mapping.findForward(SUCCESS);
    }

    public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Connection conn = null;
        try {
            conn = getConnection();
            String guid = request.getParameter("longid").trim();
            QLyLapBangKeDelegate delegate = new QLyLapBangKeDelegate(conn);
            QLyLapBangKeForm f = (QLyLapBangKeForm)form;
            QLyLapBangKeVO vo = null;
            vo = delegate.getTTinBangKeObject(guid);
            if (vo == null) {
                saveMessage(request, new TPCPException("tpbkechuyentien.norecord"));
                return mapping.findForward(FAILURE);
            }
            vo.setKl_trung_thau(StringUtil.convertNumberToString(vo.getKl_trung_thau(), "VND"));
            vo.setLs_danh_nghia(StringUtil.convertNumberToString(vo.getLs_danh_nghia(), "VND"));
            vo.setLs_trung_thau(StringUtil.convertNumberToString(vo.getLs_trung_thau(), "VND"));
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("MA_TPCP", f.getMa_tpcp());
            map.put("DOT_PH", f.getDot_ph());
            QLyLapBangKeDelegate delegateBKe = new QLyLapBangKeDelegate(conn);
            QLyLapBangKeVO voDThau = delegateBKe.getTTinDThauFromKQPH(map, vo.getPhuong_thuc_ph());
            if (voDThau != null) {
                vo.setKl_trung_thau(StringUtil.convertNumberToString(voDThau.getKl_trung_thau(), "VND"));
            }
            BeanUtils.copyProperties(f, vo);
            f.setPtph_tpcp(vo.getPhuong_thuc_ph());
            request.setAttribute("lstDViSoHuuBKe", vo.getLstCTietBKeCTien());
            DMKyHanDelegate khDelegate = new DMKyHanDelegate(conn);
            //          Map<String, Object> map = new HashMap();
            //          map.put("loai_tpcp", "TRAI_PHIEU");
            List listKyHan = null;
            listKyHan = (List)khDelegate.getDMKyHan(null);
            request.setAttribute("listKyHan", listKyHan);
            QuanLyTPCPDelegate delegateTP = new QuanLyTPCPDelegate(conn);
            List listTPCP = new ArrayList();
            listTPCP = (List)delegateTP.getAllListTPCP();
            request.setAttribute("lstAllTPCP", listTPCP);

        } catch (Exception e) {
            throw e;
        } finally {
            close(conn);
        }
        return mapping.findForward(SUCCESS);
    }
}
