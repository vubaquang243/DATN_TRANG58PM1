package com.seatech.tp.tttmua.action;

import com.seatech.framework.AppConstants;
import com.seatech.framework.common.jsp.PagingBean;
import com.seatech.framework.exception.TPCPException;
import com.seatech.framework.strustx.AppAction;
import com.seatech.framework.utils.StringUtil;
import com.seatech.tp.dmkyhan.action.DMKyHanDelegate;
import com.seatech.tp.qlytp.action.QuanLyTPCPDelegate;
import com.seatech.tp.qlytp.vo.QuanLyTPCPVO;
import com.seatech.tp.ttindthau.action.QLyTTinDauThauDelegate;
import com.seatech.tp.ttindthau.vo.ThongTinDauThauVO;
import com.seatech.tp.tttmua.form.QLyLapBangKeForm;
import com.seatech.tp.tttmua.vo.QLyLapBangKeCTietVO;
import com.seatech.tp.tttmua.vo.QLyLapBangKeVO;
import com.seatech.tp.tttmua.vo.QLyTToanTienMuaVO;

import com.seatech.tp.user.UserHistoryVO;

import java.math.BigDecimal;

import java.sql.Connection;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

import java.util.Date;
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

public class LapBangKeAction extends AppAction {
    private static String SUCCESS = "success";
    private static String FAILURE = "failure";

    protected ActionForward executeAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        Connection conn = null;
        try {
            conn = getConnection(request);
            QLyLapBangKeForm f = (QLyLapBangKeForm)form;
            QuanLyTPCPDelegate delegateTp = new QuanLyTPCPDelegate(conn);
            Collection lstDMTPCP = delegateTp.getAllListTPCPFromDotPH(f.getDot_ph());
            request.setAttribute("lstDMTPCP", lstDMTPCP);
            request.setAttribute("lstDviSoHuu", new ArrayList());
            PagingBean pagingBean = new PagingBean();
            pagingBean.setCurrentPage(1);
            request.setAttribute("PAGE_KEY", pagingBean);
        } catch (Exception e) {
            throw e;
        } finally {
            close(conn);
        }
        return mapping.findForward(SUCCESS);
    }

    public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            QLyLapBangKeForm f = (QLyLapBangKeForm)form;
            f.reset(mapping, request);
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

            QuanLyTPCPDelegate delegateTp = new QuanLyTPCPDelegate(conn);
            Collection lstDMTPCP = delegateTp.getAllListTPCPFromDotPH(f.getDot_ph());
            request.setAttribute("lstDMTPCP", lstDMTPCP);
            //get thong tin ve ma trai phieu
            QuanLyTPCPVO tpVO = null;
            if (f.getMa_tpcp() != null && !"".equalsIgnoreCase(f.getMa_tpcp())) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("MA_TP", f.getMa_tpcp());
                tpVO = delegateTp.getTTDTObject(map);
                if (tpVO != null) {
                    f.setPtph_tpcp(tpVO.getPhuong_thuc_ph());
                }
            }
            QLyTToanTienMuaDelegate delegate = new QLyTToanTienMuaDelegate(conn);
            Collection lstDviSoHuu = new ArrayList();
            QLyTToanTienMuaVO vo = new QLyTToanTienMuaVO();
            vo.setMa_tpcp(f.getMa_tpcp());
            vo.setPtph_tpcp(f.getPtph_tpcp());
            vo.setDot_ph(f.getDot_ph());
            vo.setPhuong_thuc_ph(f.getPtph_tpcp());
            lstDviSoHuu = delegate.getListDonViLapBKe(vo);
            request.setAttribute("lstDviSoHuu", lstDviSoHuu);
            //get thong tin loai tien
            String loai_tien = delegate.getDonViTinhFromMa(vo);
            f.setLoai_tien(loai_tien);
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
            conn = getConnection();
            //get thong tin đợt đấu thầu
            QLyLapBangKeForm f = (QLyLapBangKeForm)form;
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("MA_TPCP", f.getMa_tpcp());
            map.put("DOT_PH", f.getDot_ph());
            QLyLapBangKeDelegate delegate = new QLyLapBangKeDelegate(conn);
            QLyLapBangKeVO vo = new QLyLapBangKeVO();
            vo = delegate.getTTinDThauFromKQPH(map, f.getPtph_tpcp());
            if (vo == null) {
                saveMessage(request, new TPCPException("tpbkechuyentien.norecord2"));
                return mapping.findForward(FAILURE);
            }
            vo.setKl_trung_thau(StringUtil.convertNumberToString(vo.getKl_trung_thau(), "VND"));
            vo.setLs_danh_nghia(StringUtil.convertNumberToString(vo.getLs_danh_nghia(), "VND"));
            vo.setLs_trung_thau(StringUtil.convertNumberToString(vo.getLs_trung_thau(), "VND"));
            vo.setPtph_tpcp(f.getPtph_tpcp());
            BeanUtils.copyProperties(f, vo);
            //get list don vi so huu can lap bang ke
            String[] ma_dvi = request.getParameterValues("ma_nguoi_so_huu");
            String lsvDViSoHuu = "";
            if (ma_dvi != null && ma_dvi.length > 0) {
                for (int i = 0; i < ma_dvi.length; i++) {
                    lsvDViSoHuu = lsvDViSoHuu + ",'" + ma_dvi[i] + "'";
                }
            }
            if (lsvDViSoHuu != "") {
                lsvDViSoHuu = lsvDViSoHuu.substring(1);
            }
            QLyTToanTienMuaDelegate delegateTMua = new QLyTToanTienMuaDelegate(conn);
            Collection lstDViSoHuu = delegateTMua.getListDonViLapBKe(f.getPtph_tpcp(), f.getMa_tpcp(), f.getDot_ph(), lsvDViSoHuu);
            request.setAttribute("lstDViSoHuuBKe", lstDViSoHuu);
            f.setListIdSoHuu(lsvDViSoHuu);
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

    public ActionForward addExc(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Connection conn = null;
        try {
            String errMess = "";
            conn = getConnection();
            HttpSession session = request.getSession();
            String nUserID = session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString();
            QLyLapBangKeForm f = (QLyLapBangKeForm)form;
            QLyLapBangKeVO vo = new QLyLapBangKeVO();
            BeanUtils.copyProperties(vo, f);
            //update lai thông tin đấu thầu
            vo.setKl_trung_thau(f.getTong_kl_trung_thau());
            //check dot phat hanh co dot bo sung ko
            QLyTTinDauThauDelegate delegateDT = new QLyTTinDauThauDelegate(conn);
            ThongTinDauThauVO voDThauLanDau = delegateDT.getTTDTDotDauFromDotBS(f.getMa_tpcp(),f.getDot_ph(), f.getPtph_tpcp());
            if (voDThauLanDau != null && (vo.getGuid()==null || vo.getGuid().equalsIgnoreCase(""))) {
                vo.setNgay_tt_tien_mua(voDThauLanDau.getNgay_tt_tien_mua());
                //neu lan dau co bang ke, da thanh toan lai thi phai update ngay
                //thanh toan lai cho dot phat hanh bo sung theo so lan thanh toan lai cua lan dau
                QLyLapBangKeDelegate delegate = new QLyLapBangKeDelegate(conn);
                QLyLapBangKeVO bkeVO = delegate.getTTinBangKeObject(voDThauLanDau.getMa_tpcp(), voDThauLanDau.getDot_dau_thau(), voDThauLanDau.getNgay_ph());
                if (bkeVO != null) {
                    if (Long.parseLong(bkeVO.getLan_tra_lai()) > 0) {
                        vo.setNgay_tt_lai_1(bkeVO.getNgay_tt_lai_1());
                        vo.setNgay_tt_lai_2(bkeVO.getNgay_tt_lai_2());
                        vo.setLan_tra_lai(bkeVO.getLan_tra_lai());
                    } else {
                        if(voDThauLanDau.getNgay_tt_lai_1()!=null && voDThauLanDau.getNgay_tt_lai_1()!=""){
                          Calendar ngay_ph_bo_sung = StringUtil.convertStringToDate(vo.getNgay_ph(), "DD/MM/YYYY");
                          Calendar ngay_tt_lai1 = StringUtil.convertStringToDate(voDThauLanDau.getNgay_tt_lai_1(), "DD/MM/YYYY");
                          if(ngay_ph_bo_sung.getTimeInMillis() < ngay_tt_lai1.getTimeInMillis()){                            
                            vo.setNgay_tt_lai_1(voDThauLanDau.getNgay_tt_lai_1());                            
                          }else{
                            ngay_tt_lai1.set(Calendar.YEAR, ngay_tt_lai1.get(Calendar.YEAR) + 1);
                            vo.setNgay_tt_lai_1(StringUtil.convertCalendarToString(ngay_tt_lai1, "DD/MM/YYYY"));
                            vo.setLan_tra_lai("1");
                          }
                        }                        
                    }                    
                }
            }
            QLyLapBangKeCTietVO ctietVO = null;
            vo.setPhuong_thuc_ph(f.getPtph_tpcp());
            //vo.setTrang_thai("00");
            //get list chi tiet
            Collection lstCTiet = new ArrayList();
            String[] ma_trai_chu = request.getParameterValues("ma_trai_chu");
            String[] ten_trai_chu = request.getParameterValues("ten_trai_chu");
            String[] kl_trung_thau = request.getParameterValues("kl_trung_thau_ctiet");
            String[] so_tien_phai_tt = request.getParameterValues("so_tien_phai_tt");
            String[] so_tien_da_tt = request.getParameterValues("so_tien_da_tt");
            String[] ngay_chuyen_tien = request.getParameterValues("ngay_chuyen_tien");
            String[] ghi_chu = request.getParameterValues("ghi_chu");
            String lstMaDvi = "";
            BigDecimal tong_kl_trung_thau = new BigDecimal("0");            
            if (ma_trai_chu != null && ma_trai_chu.length > 0) {
                for (int i = 0; i < ma_trai_chu.length; i++) {
                    ctietVO = new QLyLapBangKeCTietVO();
                    ctietVO.setMa_trai_chu(ma_trai_chu[i]);
                    ctietVO.setTen_trai_chu(ten_trai_chu[i]);
                    ctietVO.setKl_trung_thau(kl_trung_thau[i]);
                    ctietVO.setSo_tien_phai_tt(so_tien_phai_tt[i]);
                    ctietVO.setSo_tien_da_tt(so_tien_da_tt[i]);
                    ctietVO.setNgay_chuyen_tien(ngay_chuyen_tien[i]);
                    ctietVO.setGhi_chu(ghi_chu[i]);
                    tong_kl_trung_thau = tong_kl_trung_thau.add(new BigDecimal(kl_trung_thau[i].replaceAll("\\.","")));
                    lstCTiet.add(ctietVO);
                    lstMaDvi = lstMaDvi + ",'" + ma_trai_chu[i] + "'";
                }
            }
            if (lstMaDvi != "") {
                lstMaDvi = lstMaDvi.substring(1);
            }
            vo.setKl_trung_thau(String.valueOf(tong_kl_trung_thau));
            vo.setLstCTietBKeCTien(lstCTiet);
            vo.setNguoi_tao(nUserID);
            QLyLapBangKeDelegate delegate = new QLyLapBangKeDelegate(conn);
            long idAdd = delegate.update(vo, lstMaDvi);
            //update trang thai lap bang ke vao bang so huu
            //insert history
            UserHistoryVO userHisVO = new UserHistoryVO();
            userHisVO.setNguoi_tdoi(new Long(nUserID));
            userHisVO.setNoi_dung_thaydoi("Lap bang ke " + idAdd);
            userHisVO.setNsd_id(idAdd);
            delegate.insertHistoryUser(userHisVO);
            f.reset(mapping, request);
            errMess = "tpbkechuyentien.add.succ";
            if ("01".equals(vo.getTrang_thai())) {
                errMess = "tpbkechuyentien.trinh.succ";
            }
            saveMessage(request, new TPCPException(errMess));

        } catch (Exception e) {
            throw e;
        } finally {
            close(conn);
        }
        return mapping.findForward(SUCCESS);
    }
}
