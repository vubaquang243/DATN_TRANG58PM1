package com.seatech.tp.tttmua.action;

import com.seatech.framework.AppConstants;
import com.seatech.framework.common.jsp.PagingBean;
import com.seatech.framework.strustx.AppAction;
import com.seatech.framework.utils.StringUtil;
import com.seatech.tp.qlytp.action.QuanLyTPCPDelegate;
import com.seatech.tp.qlytp.vo.QuanLyTPCPVO;
import com.seatech.tp.tttmua.form.QLyTToanTienMuaCTietForm;
import com.seatech.tp.tttmua.form.QLyTToanTienMuaForm;
import com.seatech.tp.tttmua.vo.QLyTToanTienMuaVO;
import com.seatech.tp.tttmua.vo.TTinDViSoHuuVO;
import com.seatech.tp.user.UserHistoryVO;

import java.math.BigDecimal;

import java.sql.Connection;

import java.util.ArrayList;
import java.util.Collection;

import java.util.HashMap;
import java.util.Iterator;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class QLyTToanTienMuaAction extends AppAction {
    private static String SUCCESS = "success";
    private static String FAILURE = "failure";

    protected ActionForward executeAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        Connection conn = null;
        try {
            conn = getConnection(request);
            QLyTToanTienMuaForm f = (QLyTToanTienMuaForm)form;
            QuanLyTPCPDelegate delegateTp = new QuanLyTPCPDelegate(conn);
            Collection lstDMTPCP = delegateTp.getAllListTPCPFromNgayPH(f.getNgay_ph());
            request.setAttribute("lstDMTPCP", lstDMTPCP);
        } catch (Exception e) {
            throw e;
        } finally {
            close(conn);
        }
        return mapping.findForward(SUCCESS);
    }

    public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            QLyTToanTienMuaForm f = (QLyTToanTienMuaForm)form;
            f.reset(mapping, request);
            f.setNgay_ph(StringUtil.getCurrentDate());
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
            Integer totalCount[] = new Integer[1];
            QuanLyTPCPDelegate delegateTp = new QuanLyTPCPDelegate(conn);
            Collection lstDMTPCP = delegateTp.getAllListTPCPFromNgayPH(f.getNgay_ph());
            request.setAttribute("lstDMTPCP", lstDMTPCP);
            //check thong tin Ma TPCP
            QuanLyTPCPVO tpVO = null;
            if (f.getMa_tpcp() != null && !"".equalsIgnoreCase(f.getMa_tpcp())) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("MA_TP", f.getMa_tpcp());
                tpVO = delegateTp.getTTDTObject(map);
                if (tpVO != null) {
                    f.setPhuong_thuc_ph(tpVO.getPhuong_thuc_ph());
                }
            }
            QLyTToanTienMuaDelegate delegate = new QLyTToanTienMuaDelegate(conn);
            QLyTToanTienMuaVO vo = new QLyTToanTienMuaVO();
            BeanUtils.copyProperties(vo, f);
            //get thong tin ve loai tien
            String loai_tien = delegate.getDonViTinhFromMa(vo);
            f.setLoai_tien(loai_tien);
            //get don vi can thanh toan
            Collection lstDviSoHuu = new ArrayList();
            lstDviSoHuu = delegate.getListDonViSoHuu(vo);
            request.setAttribute("lstDviSoHuu", lstDviSoHuu);
            if (f.getMa_nguoi_so_huu() != null && !f.getMa_nguoi_so_huu().equals("")) {
                //set list thong tin trung thau
                Collection lstTTinTThau = new ArrayList();
                lstTTinTThau = delegate.getTTinTrungThauDViSoHuu(vo);
                BigDecimal totalTien = new BigDecimal("0");
                BigDecimal totalTienBKe = new BigDecimal("0");
                if (!lstTTinTThau.isEmpty()) {
                    TTinDViSoHuuVO ttinVO = null;
                    Iterator ito1 = lstTTinTThau.iterator();
                    while (ito1.hasNext()) {
                        ttinVO = (TTinDViSoHuuVO)ito1.next();
                        totalTien = totalTien.add(new BigDecimal(ttinVO.getTien_tt_mua()));
                    }
                }
                request.setAttribute("totalTien", StringUtil.convertNumberToString(totalTien.toString(), "VND"));
                request.setAttribute("lstTTinTThau", lstTTinTThau);
                //set thong tin thanh toan
                Collection lstTTinTToan = new ArrayList();
                Collection lstTTinTToanBKe = new ArrayList();
                Collection lstTTinTToanChuaBKe = new ArrayList();
                lstTTinTToan = delegate.getTTinThanhToanDViSoHuu(vo);
                String guid_tttt = "";
                if (!lstTTinTToan.isEmpty()) {
                    Iterator ito = lstTTinTToan.iterator();
                    QLyTToanTienMuaVO ttVO = null;
                    while (ito.hasNext()) {
                        ttVO = (QLyTToanTienMuaVO)ito.next();
                        if ("01".equals(ttVO.getTrang_thai_tt())) {
                            totalTienBKe = totalTienBKe.add(new BigDecimal(ttVO.getSo_tien()));
                            ttVO.setSo_tien(StringUtil.convertNumberToString(ttVO.getSo_tien(), "VND"));
                            lstTTinTToanBKe.add(ttVO);
                        } else {
                            ttVO.setSo_tien(StringUtil.convertNumberToString(ttVO.getSo_tien(), "VND"));
                            lstTTinTToanChuaBKe.add(ttVO);
                        }
                    }
                }
                if (lstTTinTToanChuaBKe.isEmpty()) {
                    QLyTToanTienMuaCTietForm ctietForm = null;
                    if (lstTTinTToanBKe.isEmpty()) {
                        if (!lstTTinTThau.isEmpty()) {
                            TTinDViSoHuuVO ttinVO = null;
                            Iterator ito1 = lstTTinTThau.iterator();
                            while (ito1.hasNext()) {
                                ttinVO = (TTinDViSoHuuVO)ito1.next();
                                ctietForm = new QLyTToanTienMuaCTietForm();
                                ctietForm.setSo_tien((StringUtil.convertNumberToString(ttinVO.getTien_tt_mua(), "VND")));
                                lstTTinTToanChuaBKe.add(ctietForm);
                            }
                        }
                    } else {
                        ctietForm = new QLyTToanTienMuaCTietForm();
                        lstTTinTToanChuaBKe.add(ctietForm);
                    }
                }
                if (totalTienBKe.compareTo(totalTien) == 0) {
                    request.setAttribute("allowGhiTungBanGhi", false);
                } else
                    request.setAttribute("allowGhiTungBanGhi", true);
                request.setAttribute("lstTTinTToan", lstTTinTToanChuaBKe);
                request.setAttribute("lstTTinTToanBKe", lstTTinTToanBKe);
            }
            if (this.isTokenValid(request)) {
                resetToken(request);
            }
            this.saveToken(request);
        } catch (Exception e) {
            throw e;
        } finally {
            close(conn);
        }
        return mapping.findForward(SUCCESS);
    }

    public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Connection conn = null;
        try {

        } catch (Exception e) {
            throw e;
        } finally {
            close(conn);
        }
        return mapping.findForward(SUCCESS);
    }

    public ActionForward addExc(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Connection conn = null;
        try {
            HttpSession session = request.getSession();
            String nUserID = session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString();
            Collection lstTTinTToan = new ArrayList();
            conn = getConnection();
            QLyTToanTienMuaForm f = (QLyTToanTienMuaForm)form;
            QLyTToanTienMuaVO vo = new QLyTToanTienMuaVO();
            BeanUtils.copyProperties(vo, f);
            //get values from detail form
            String[] dvi_sohuu = request.getParameterValues("dvi_sohuu");
            String[] dvi_sohuu_hidden = request.getParameterValues("dvi_sohuu_hidden");
            String[] tong_tien_phai_tt = request.getParameterValues("tong_tien_phai_tt");
            String[] dot_phat_hanh = request.getParameterValues("dot_phat_hanh");
            String[] dvi_sohuu_bk = request.getParameterValues("dvi_sohuu_bk");
            QLyTToanTienMuaVO ctietVO = null;
            if (dvi_sohuu != null && dvi_sohuu.length > 0) {
                for (int i = 0; i < dvi_sohuu.length; i++) {
                    if (dvi_sohuu_hidden != null && dvi_sohuu_hidden.length > 0) {
                        int k = i;
                        for (int j = 0; j < dvi_sohuu_hidden.length; j++) {
                            if (dvi_sohuu[i].equals(dvi_sohuu_hidden[j])) {
                                k = j;
                                break;
                            }
                        }
                        ctietVO = new QLyTToanTienMuaVO();
                        ctietVO.setDot_ph(dot_phat_hanh[k]);
                        ctietVO.setNgay_ph(f.getNgay_ph());
                        ctietVO.setMa_tpcp(f.getMa_tpcp());
                        ctietVO.setMa_nguoi_so_huu(dvi_sohuu_hidden[k]);
                        ctietVO.setTrang_thai_tt("00");
                        ctietVO.setNop_cham("1");
                        ctietVO.setNguoi_tao(nUserID);
                        ctietVO.setLoai_tien(f.getLoai_tien());
                        ctietVO.setSo_tien(tong_tien_phai_tt[k]);
                        lstTTinTToan.add(ctietVO);
                    }
                }
            }
            String lstMaDvi = "";
            if (dvi_sohuu_bk != null && dvi_sohuu_bk.length > 0) {
                for (int i = 0; i < dvi_sohuu_bk.length; i++) {
                    lstMaDvi = lstMaDvi + ",'" + dvi_sohuu_bk[i] + "'";
                }
            }
            if (lstMaDvi != "") {
                lstMaDvi = lstMaDvi.substring(1);
            } //insert DB
            QLyTToanTienMuaDelegate delegate = new QLyTToanTienMuaDelegate(conn);
            delegate.update(lstTTinTToan, vo, lstMaDvi);
            //insert history
            UserHistoryVO userHisVO = new UserHistoryVO();
            userHisVO.setNguoi_tdoi(new Long(nUserID));
            userHisVO.setNoi_dung_thaydoi("Them moi thong tin thanh toan");
            delegate.insertHistoryUser(userHisVO);
            f.reset(mapping, request);

        } catch (Exception e) {
            throw e;
        } finally {
            close(conn);
        }
        return mapping.findForward(SUCCESS);
    }

    public ActionForward updateExc(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Connection conn = null;
        try {
            HttpSession session = request.getSession();
            String nUserID = session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString();
            Collection lstTTinTToan = new ArrayList();
            conn = getConnection();
            QLyTToanTienMuaForm f = (QLyTToanTienMuaForm)form;
            QLyTToanTienMuaVO vo = new QLyTToanTienMuaVO();
            BeanUtils.copyProperties(vo, f);
            //get values from detail form
            String[] so_gd = request.getParameterValues("so_gd");
            String[] so_tien = request.getParameterValues("so_tien");
            String[] ngay_tt = request.getParameterValues("ngay_tt");
            String[] nop_cham = request.getParameterValues("nop_cham");
            String[] trang_thai_tt = request.getParameterValues("trang_thai_tt");
            QLyTToanTienMuaVO ctietVo = null;
            if (so_gd != null && so_gd.length > 0) {
                for (int i = 0; i < so_gd.length; i++) {
                    ctietVo = new QLyTToanTienMuaVO();
                    ctietVo.setLoai_tien(f.getLoai_tien());
                    ctietVo.setDot_ph(f.getDot_ph());
                    ctietVo.setNgay_ph(f.getNgay_ph());
                    ctietVo.setMa_nguoi_so_huu(f.getMa_nguoi_so_huu());
                    ctietVo.setMa_tpcp(f.getMa_tpcp());
                    ctietVo.setSo_gd(so_gd[i]);
                    ctietVo.setSo_tien(so_tien[i]);
                    ctietVo.setNgay_tt(ngay_tt[i]);
                    ctietVo.setTrang_thai_tt(trang_thai_tt[i]);
                    ctietVo.setNop_cham(nop_cham[i]);
                    ctietVo.setNguoi_tao(nUserID);
                    lstTTinTToan.add(ctietVo);
                }
            }
            //get thong tin thanh toan
            TTinDViSoHuuVO voTToanDvi = null;
            Collection lstTToanDvi = new ArrayList();
            String[] id_dvi_sohuu = request.getParameterValues("id_dvi_sohuu");
            if (id_dvi_sohuu != null && id_dvi_sohuu.length > 0) {

                for (int i = 0; i < id_dvi_sohuu.length; i++) {
                    voTToanDvi = new TTinDViSoHuuVO();
                    voTToanDvi.setTrang_thai_tt("02");
                    voTToanDvi.setGuid(id_dvi_sohuu[i]);
                    lstTToanDvi.add(voTToanDvi);
                }
            }
            //insert DB
            QLyTToanTienMuaDelegate delegate = new QLyTToanTienMuaDelegate(conn);
            delegate.update(lstTTinTToan, lstTToanDvi, vo);
            //insert history
            UserHistoryVO userHisVO = new UserHistoryVO();
            userHisVO.setNguoi_tdoi(new Long(nUserID));
            userHisVO.setNoi_dung_thaydoi("Them moi thong tin thanh toan cho don vi " + f.getMa_nguoi_so_huu());
            delegate.insertHistoryUser(userHisVO);
            f.reset(mapping, request);
        } catch (Exception e) {
            throw e;
        } finally {
            close(conn);
        }
        return mapping.findForward(SUCCESS);
    }
}
