package com.seatech.tp.banletraiphieutw.action;

import com.seatech.framework.AppConstants;
import com.seatech.framework.common.jsp.PagingBean;
import com.seatech.framework.exception.TPCPException;
import com.seatech.framework.strustx.AppAction;
import com.seatech.framework.utils.StringUtil;
import com.seatech.tp.banletraiphieutw.form.BanLeTraiPhieuTwChiTietForm;
import com.seatech.tp.banletraiphieutw.form.BanLeTraiPhieuTwForm;
import com.seatech.tp.banletraiphieutw.vo.BanLeTraiPhieuTwChiTietVO;
import com.seatech.tp.banletraiphieutw.vo.BanLeTraiPhieuTwVO;
import com.seatech.tp.dmkyhan.action.DMKyHanDelegate;
import com.seatech.tp.dmtraichu.action.DMTraiChuDelegate;
import com.seatech.tp.dmtraichu.vo.DMTraiChuVO;
import com.seatech.tp.qlytp.action.QuanLyTPCPDelegate;
import com.seatech.tp.qlytp.vo.QuanLyTPCPVO;
import com.seatech.tp.ttindthau.action.QLyTTinDauThauDelegate;
import com.seatech.tp.ttindthau.vo.ThongTinDauThauVO;
import com.seatech.tp.user.UserHistoryVO;

import java.math.BigDecimal;

import java.sql.Connection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.time.FastDateFormat;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class BanLeTraiPhieuTwAction extends AppAction {
    private static String SUCCESS = "success";
    private static String FAILURE = "failure";


    public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Connection conn = null;
        try {
            BanLeTraiPhieuTwForm f = (BanLeTraiPhieuTwForm)form;
            f.reset(mapping, request);

        } catch (Exception e) {
            conn.rollback();
            throw e;
        } finally {
            close(conn);
        }
        return search(mapping, form, request, response);
    }

    public ActionForward search(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
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
            Integer currentPage = new Integer(page);
            Integer numberRowOnPage = phantrang;
            Integer totalCount[] = new Integer[1];
            BanLeTraiPhieuTwVO vo = new BanLeTraiPhieuTwVO();
            BeanUtils.copyProperties(vo, f);
            // get list ma_tpcp
            QuanLyTPCPDelegate delegatetpcp = new QuanLyTPCPDelegate(conn);
            List listTPCP = new ArrayList();
            listTPCP = (List)delegatetpcp.getAllListTPCP_Ban_Le();
            request.setAttribute("listTPCP", listTPCP);

            BanLeTraiPhieuTwDelegate delegate = new BanLeTraiPhieuTwDelegate(conn);
            List lstBanLeTraiPhieuTw = null;
            lstBanLeTraiPhieuTw = (List)delegate.getListBanLeTraiPhieuTwPaging(vo, currentPage, numberRowOnPage, totalCount);

            Iterator ito = lstBanLeTraiPhieuTw.iterator();
            Collection resultBanLe = new ArrayList();
            while (ito.hasNext()) {
                vo = (BanLeTraiPhieuTwVO)ito.next();
                if (vo.getKhoi_luong() != null) {
                    String khoiLuong = vo.getKhoi_luong();
                    vo.setKhoi_luong(StringUtil.convertNumberToString(khoiLuong, "VND"));
                }
                resultBanLe.add(vo);
            }
            PagingBean pagingBean = new PagingBean();
            pagingBean.setCurrentPage(currentPage);
            pagingBean.setNumberOfRow((totalCount[0] == null) ? 0 : totalCount[0].intValue());
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

    public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Connection conn = null;
        try {
            conn = getConnection(request);
            BanLeTraiPhieuTwForm f = (BanLeTraiPhieuTwForm)form;
            QuanLyTPCPDelegate delegate = new QuanLyTPCPDelegate(conn);
            List listTPCP = new ArrayList();
            listTPCP = (List)delegate.getAllListTPCP_Ban_Le();
            request.setAttribute("listTPCP", listTPCP);

            //get list ky han
            DMKyHanDelegate dmKyHanDelegate = new DMKyHanDelegate(conn);
            HashMap<String, Object> map = new HashMap<String, Object>();
            List lstKyHan = (List)dmKyHanDelegate.getDMKyHan(map);
            request.setAttribute("lstKyHan", lstKyHan);
            //            String kh = "1";
            //            request.setAttribute("kh", kh);

            // set ky han khi addExc Failure
            QuanLyTPCPDelegate delegateTP = new QuanLyTPCPDelegate(conn);
            Map<String, Object> mapTP = new HashMap();
            if (!"".equals(f.getMa_tpcp())) {
                mapTP.put("MA_TP", f.getMa_tpcp());
                QuanLyTPCPVO tpcpVo = delegateTP.getTTDTObject(mapTP);
                if (tpcpVo != null) {
                    f.setKy_han(tpcpVo.getKy_han());
                }
            }

            //get list don vi so huuu
            DMTraiChuDelegate dmTraiChuDelegate = new DMTraiChuDelegate(conn);
            HashMap<String, Object> map_DVSH = new HashMap<String, Object>();
            map_DVSH.put("TRANG_THAI", "00");
            List lstDVSH = (List)dmTraiChuDelegate.getDMTraiChu(map_DVSH);
            request.setAttribute("lstDVSH", lstDVSH);
            if (f.getNgay_dao_han() == null || "".equals(f.getNgay_dao_han())) {
                //end get list don vi so huuu
                BanLeTraiPhieuTwChiTietForm formCTiet = new BanLeTraiPhieuTwChiTietForm();
                formCTiet.setStt("1");
                Collection lst = new ArrayList();
                lst.add(formCTiet);
                f.setLstKQBanLe_CTiet(lst);
            } else {
                BanLeTraiPhieuTwVO voBanLeTraiPhieuTwVO = new BanLeTraiPhieuTwVO();

                f.setStt("1");

                Collection listBanLe = new ArrayList();
                //Add ban le chi tiet
                String[] sMa_dvi_so_huu = request.getParameterValues("ma_dvi_so_huu");
                String[] sSoluongtraiphieu = request.getParameterValues("sl_dky_mua");
                String[] sKl_dky_mua = request.getParameterValues("kl_dky_mua");
                String[] sSotienthanhtoan = request.getParameterValues("so_tien_tt");

                if (sMa_dvi_so_huu.length > 0) {
                    for (int i = 0; i < sMa_dvi_so_huu.length; i++) {
                        BanLeTraiPhieuTwChiTietVO voChiTiet = new BanLeTraiPhieuTwChiTietVO();
                        String ma_dvi_so_huu = "";
                        if (!sMa_dvi_so_huu[i].toString().trim().equals("")) {
                            ma_dvi_so_huu = sMa_dvi_so_huu[i].toString().trim();
                        }

                        String soluongtraiphieu = "";
                        if (!sSoluongtraiphieu[i].toString().trim().equals("")) {
                            soluongtraiphieu = sSoluongtraiphieu[i].toString().trim();

                        }
                        String sotienthanhtoan = "";
                        if (!sSotienthanhtoan[i].toString().trim().equals("")) {
                            sotienthanhtoan = sSotienthanhtoan[i].toString().trim();
                        }
                        String kl_dky_mua = "";
                        if (!sKl_dky_mua[i].toString().trim().equals("")) {
                            kl_dky_mua = sKl_dky_mua[i].toString().trim();
                        }
                        voChiTiet.setMa_dvi_so_huu(ma_dvi_so_huu);
                        voChiTiet.setDvi_so_huu(getTenDonViSoHuuByGuid(ma_dvi_so_huu, conn));
                        voChiTiet.setSl_dky_mua(soluongtraiphieu);
                        voChiTiet.setKl_dky_mua(kl_dky_mua);
                        voChiTiet.setSo_tien_tt(sotienthanhtoan);
                        listBanLe.add(voChiTiet);
                    }
                    voBanLeTraiPhieuTwVO.setLstKQBanLe_CTiet(listBanLe);
                }
                f.setLstKQBanLe_CTiet(voBanLeTraiPhieuTwVO.getLstKQBanLe_CTiet());
            }
        } catch (Exception e) {
            conn.rollback();
            throw e;
        } finally {
            conn.close();
        }
        return mapping.findForward(SUCCESS);
    }

    public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Connection conn = null;
        try {
            conn = getConnection();
            BanLeTraiPhieuTwForm f = (BanLeTraiPhieuTwForm)form;
            String guid = "";
            if ("".equals(f.getDot_ph())) {
                guid = request.getParameter("longid").trim();
            } else {
                guid = f.getGuid();
            }
            //check xem TPCP tồn tại?
            BanLeTraiPhieuTwDelegate delegate = new BanLeTraiPhieuTwDelegate(conn);
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("GUID", guid);
            BanLeTraiPhieuTwVO voBanLeTraiPhieuTw = delegate.getBanLeTraiPhieuTwObject(map);
            if (voBanLeTraiPhieuTw == null) {
                saveMessage(request, new TPCPException("BanLeTraiPhieuTw.list.norecord"));
                return mapping.findForward(FAILURE);
            }
            String kh = voBanLeTraiPhieuTw.getKy_tt_lai();
            request.setAttribute("kh", kh);
            if (voBanLeTraiPhieuTw.getLai_suat() != null) {

                voBanLeTraiPhieuTw.setLai_suat(StringUtil.convertNumberToString(voBanLeTraiPhieuTw.getLai_suat(), "VND"));
            }
            if (voBanLeTraiPhieuTw.getKhoi_luong() != null) {
                String khoiluong = voBanLeTraiPhieuTw.getKhoi_luong();
                voBanLeTraiPhieuTw.setKhoi_luong(StringUtil.convertNumberToString(khoiluong, "VND"));
            }
            if (voBanLeTraiPhieuTw.getSo_luong() != null) {
                String soluong = voBanLeTraiPhieuTw.getSo_luong();
                voBanLeTraiPhieuTw.setSo_luong(StringUtil.convertNumberToString(soluong, "VND"));
            }
            if (voBanLeTraiPhieuTw.getMenh_gia() != null) {
                String menhgia = voBanLeTraiPhieuTw.getMenh_gia();
                voBanLeTraiPhieuTw.setMenh_gia(StringUtil.convertNumberToString(menhgia, "VND"));
            }
            if (voBanLeTraiPhieuTw.getTong_so_tt() != null) {
                String tong_so_tt = voBanLeTraiPhieuTw.getTong_so_tt();
                voBanLeTraiPhieuTw.setTong_so_tt(StringUtil.convertNumberToString(tong_so_tt, "VND"));
            }

            BeanUtils.copyProperties(f, voBanLeTraiPhieuTw);
            //get list TPCP
            QuanLyTPCPDelegate delegateTPCP = new QuanLyTPCPDelegate(conn);
            List listTPCP = new ArrayList();
            listTPCP = (List)delegateTPCP.getAllListTPCP_Ban_Le();
            request.setAttribute("listTPCP", listTPCP);
            //Ky Han
            DMKyHanDelegate dmKyHanDelegate = new DMKyHanDelegate(conn);
            //get list ky han
            HashMap<String, Object> map_kyhan = new HashMap<String, Object>();
            //            map_kyhan.put("LOAI_TPCP", "TRAI_PHIEU");
            List lstKyHan = (List)dmKyHanDelegate.getDMKyHan(map_kyhan);
            request.setAttribute("lstKyHan", lstKyHan);
            //
            //get list don vi so huuu
            DMTraiChuDelegate dmTraiChuDelegate = new DMTraiChuDelegate(conn);
            HashMap<String, Object> map_DVSH = new HashMap<String, Object>();
            map_DVSH.put("TRANG_THAI", "00");
            List lstDVSH = (List)dmTraiChuDelegate.getDMTraiChu(map_DVSH);
            request.setAttribute("lstDVSH", lstDVSH);
            //end get list don vi so huuu
            //Chi tiet
            BanLeTraiPhieuTwChiTietDelegate delegateChiTiet = new BanLeTraiPhieuTwChiTietDelegate(conn);
            HashMap<String, Object> map_chitiet = new HashMap<String, Object>();
            map_chitiet.put("KQPH_BAN_LE_ID", guid);
            Collection lstCTietVO = null;
            lstCTietVO = delegateChiTiet.getListBanLeTraiPhieuChiTietTw(map_chitiet, 0);
            Collection lstCTietForm = new ArrayList();
            Iterator ito2 = lstCTietVO.iterator();
            BanLeTraiPhieuTwChiTietVO ctietVO = null;
            BanLeTraiPhieuTwChiTietForm ctietForm = null;
            int dem = 0;
            while (ito2.hasNext()) {
                ctietForm = new BanLeTraiPhieuTwChiTietForm();
                dem++;
                ctietVO = (BanLeTraiPhieuTwChiTietVO)ito2.next();
                ctietForm.setStt(dem + "");
                if (ctietVO.getSl_dky_mua() != null) {
                    String Sl_dky_mua = ctietVO.getSl_dky_mua();
                    ctietVO.setSl_dky_mua(StringUtil.convertNumberToString(Sl_dky_mua, "VND"));
                }
                if (ctietVO.getKl_dky_mua() != null) {
                    String Kl_dky_mua = ctietVO.getKl_dky_mua();
                    ctietVO.setKl_dky_mua(StringUtil.convertNumberToString(Kl_dky_mua, "VND"));
                }
                if (ctietVO.getSo_tien_tt() != null) {
                    String So_tien_tt = ctietVO.getSo_tien_tt();
                    ctietVO.setSo_tien_tt(StringUtil.convertNumberToString(So_tien_tt, "VND"));
                }
                BeanUtils.copyProperties(ctietForm, ctietVO);
                lstCTietForm.add(ctietForm);
            }
            f.setLstKQBanLe_CTiet(lstCTietForm);
            //End Chitiet
        } catch (Exception e) {
            conn.rollback();
            throw e;
        } finally {
            close(conn);
        }
        return mapping.findForward(SUCCESS);

    }

    public String getTenDonViSoHuuByGuid(String Ma, Connection conn) throws Exception {
        String ten = "";
        DMTraiChuDelegate dmTraiChuDelegate = new DMTraiChuDelegate(conn);
        HashMap<String, Object> map_DVSH = new HashMap<String, Object>();
        map_DVSH.put("MA_CHU_SO_HUU", Ma);
        DMTraiChuVO voDMTraiChu = dmTraiChuDelegate.getDMTraiChuVO(map_DVSH);
        if (voDMTraiChu.getTen_dvi_so_huu() != null) {
            ten = voDMTraiChu.getTen_dvi_so_huu().toString().trim();
        }
        return ten;
    }

    public void XuLyPhanBanLeChiTiet(HttpServletRequest request, Connection conn, BanLeTraiPhieuTwVO voBanLeTraiPhieuTwVO) throws Exception {
        {
            Collection listBanLe = new ArrayList();
            //Add ban le chi tiet
            String[] sMa_dvi_so_huu = request.getParameterValues("ma_dvi_so_huu");

            String loai_tien = request.getParameter("loai_tien");
            String[] sSoluongtraiphieu = request.getParameterValues("sl_dky_mua");
            String[] sKl_dky_mua = request.getParameterValues("kl_dky_mua");
            String[] sSotienthanhtoan = request.getParameterValues("so_tien_tt");

            if (sMa_dvi_so_huu.length > 0) {
                for (int i = 0; i < sMa_dvi_so_huu.length; i++) {
                    BanLeTraiPhieuTwChiTietVO voChiTiet = new BanLeTraiPhieuTwChiTietVO();
                    String ma_dvi_so_huu = "";
                    if (!sMa_dvi_so_huu[i].toString().trim().equals("")) {
                        ma_dvi_so_huu = sMa_dvi_so_huu[i].toString().trim();
                    }

                    String soluongtraiphieu = "";
                    if (!sSoluongtraiphieu[i].toString().trim().equals("")) {
                        soluongtraiphieu = sSoluongtraiphieu[i].toString().trim();

                    }
                    String sotienthanhtoan = "";
                    if (!sSotienthanhtoan[i].toString().trim().equals("")) {
                        sotienthanhtoan = sSotienthanhtoan[i].toString().trim();
                    }
                    String kl_dky_mua = "";
                    if (!sKl_dky_mua[i].toString().trim().equals("")) {
                        kl_dky_mua = sKl_dky_mua[i].toString().trim();
                    }
                    voChiTiet.setMa_dvi_so_huu(ma_dvi_so_huu);
                    voChiTiet.setDvi_so_huu(getTenDonViSoHuuByGuid(ma_dvi_so_huu, conn));

                    voChiTiet.setLoai_tien(loai_tien);
                    voChiTiet.setSl_dky_mua(soluongtraiphieu);
                    voChiTiet.setKl_dky_mua(kl_dky_mua);
                    voChiTiet.setSo_tien_tt(sotienthanhtoan);
                    listBanLe.add(voChiTiet);
                }
                voBanLeTraiPhieuTwVO.setLstKQBanLe_CTiet(listBanLe);
            }
        }
        //End add ban le chi tiet
    }

    public ActionForward addExc(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Connection conn = null;
        String errMess = "";
        try {
            conn = getConnection(request);
            getInt(request);
            BanLeTraiPhieuTwForm f = (BanLeTraiPhieuTwForm)form;
            BanLeTraiPhieuTwDelegate delegate = new BanLeTraiPhieuTwDelegate(conn);
            HashMap<String, Object> map_BL = new HashMap<String, Object>();
            map_BL.put("DOT_PH", f.getDot_ph());
            BanLeTraiPhieuTwVO voBanLe = delegate.getBanLeTraiPhieuTwObjectHienThi(map_BL);
            if (voBanLe != null) {
                throw new TPCPException().createException("TPCP-0013", f.getDot_ph());
            }            
            // check ma_tpcp
            if ("".equals(f.getDot_bo_sung())) {
                Map<String, Object> maptpcp = new HashMap();
                maptpcp.put("MA_TPCP", f.getMa_tpcp());
                List ma_tpcpbl = (List)delegate.getAllBanLe(maptpcp);
                if (ma_tpcpbl.size() >= 1) {
                    throw new TPCPException().createException("TPCP-0020", f.getMa_tpcp());
                }
            }

            //check dot bo sung
            if (f.getDot_bo_sung() != null && !f.getDot_bo_sung().equals("")) {
                Map<String, Object> map2 = new HashMap();
                map2.put("DOT_PH", f.getDot_bo_sung());
                map2.put("TRANG_THAI", "02");
                map2.put("MA_TPCP", f.getMa_tpcp());
                BanLeTraiPhieuTwVO voBanLe2 = delegate.getBanLeTraiPhieuTwObject(map2);
                if (voBanLe2 == null) {
                    throw new TPCPException().createException("TPCP-0031", f.getDot_bo_sung());
                }
            }

            BanLeTraiPhieuTwVO vo = new BanLeTraiPhieuTwVO();
            HttpSession session = request.getSession();
            String nUserID = session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString();
            BeanUtils.copyProperties(vo, f);
            vo.setNguoi_tao(nUserID);
            if ("".equals(vo.getTrang_thai())) {
                vo.setTrang_thai("00");
            }
            //Add ban le chi tiet
            XuLyPhanBanLeChiTiet(request, conn, vo);
            //End add ban le chi tiet

            long idAdd = delegate.update(vo);
            //insert history
            UserHistoryVO userHisVO = new UserHistoryVO();
            userHisVO.setNguoi_tdoi(new Long(nUserID));
            userHisVO.setNoi_dung_thaydoi("Them moi ma ban le trai phieu tw" + idAdd);
            userHisVO.setNsd_id(idAdd);
            delegate.insertHistoryUser(userHisVO);
            f.reset(mapping, request);
            errMess = "BanLeTraiPhieuTw.add.succ";
            saveMessage(request, new TPCPException(errMess));
        } catch (Exception e) {
            conn.rollback();
            throw e;
        } finally {
            close(conn);
        }
        return mapping.findForward(SUCCESS);
    }


    public ActionForward updateExc(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Connection conn = null;
        String errMess = "";
        try {
            conn = getConnection(request);
            BanLeTraiPhieuTwForm f = (BanLeTraiPhieuTwForm)form;
            BanLeTraiPhieuTwVO vo = new BanLeTraiPhieuTwVO();
            BanLeTraiPhieuTwDelegate delegate = new BanLeTraiPhieuTwDelegate(conn);
            HttpSession session = request.getSession();
            String nUserID = session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString();
            
            //check dot bo sung
            if (f.getDot_bo_sung() != null && !f.getDot_bo_sung().equals("")) {
                Map<String, Object> map2 = new HashMap();
                map2.put("DOT_PH", f.getDot_bo_sung());
                map2.put("TRANG_THAI", "02");
                map2.put("MA_TPCP", f.getMa_tpcp());
                BanLeTraiPhieuTwVO voBanLe = delegate.getBanLeTraiPhieuTwObject(map2);
                if (voBanLe == null) {
                    throw new TPCPException().createException("TPCP-0031", f.getDot_bo_sung());
                }
            }else{
              //check neu cap nhap ma TPCP
              if (f.getMa_tpcp_old() != null && !f.getMa_tpcp_old().equals(f.getMa_tpcp())) {
                  Map<String, Object> map = new HashMap();
                  map.put("MA_TPCP", f.getMa_tpcp());
                  BanLeTraiPhieuTwVO voCheckTien = new BanLeTraiPhieuTwVO();
                  voCheckTien = delegate.getBanLeTraiPhieuTwObject(map);
                  if (voCheckTien != null) {
                      throw new TPCPException().createException("TPCP-0020", f.getMa_tpcp());
                  }
              }
            }
            BeanUtils.copyProperties(vo, f);
            vo.setNgay_sua_cuoi(getDate());
            vo.setNguoi_sua_cuoi(nUserID);
            //Add ban le chi tiet
            XuLyPhanBanLeChiTiet(request, conn, vo);
            //End add ban le chi tiet

            long idAdd = delegate.update(vo);
            UserHistoryVO userHisVO = new UserHistoryVO();
            userHisVO.setNguoi_tdoi(new Long(nUserID));
            userHisVO.setNoi_dung_thaydoi("Update ban le trai phieu tw" + idAdd);
            userHisVO.setNsd_id(idAdd);
            delegate.insertHistoryUser(userHisVO);
            f.reset(mapping, request);
            if ("00".equals(vo.getTrang_thai())) {
                errMess = "BanLeTraiPhieuTw.update.succ";
            } else {
                errMess = "BanLeTraiPhieuTw.updatesub.succ";
            }
            saveMessage(request, new TPCPException(errMess));
        } catch (Exception e) {
            conn.rollback();
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
            BanLeTraiPhieuTwDelegate delegate = new BanLeTraiPhieuTwDelegate(conn);
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("GUID", guid);
            BanLeTraiPhieuTwVO voBanLe = delegate.getBanLeTraiPhieuTwObject(map);
            if (voBanLe == null) {
                saveMessage(request, new TPCPException("BanLeTraiPhieuTw.list.norecord"));
                return mapping.findForward(FAILURE);
            } else {
                BanLeTraiPhieuTwForm f = (BanLeTraiPhieuTwForm)form;
                long idAdd = delegate.deleteBanLeTraiPhieuTw(voBanLe);
                //insert history
                UserHistoryVO userHisVO = new UserHistoryVO();
                userHisVO.setNguoi_tdoi(new Long(nUserID));
                userHisVO.setNoi_dung_thaydoi("Xoa ma banletraiphieutw " + idAdd);
                userHisVO.setNsd_id(idAdd);
                delegate.insertHistoryUser(userHisVO);
                f.reset(mapping, request);
                if (idAdd != 0) {
                    errMess = "BanLeTraiPhieuTw.delete.succ";
                } else {
                    errMess = "BanLeTraiPhieuTw.delete.error";
                }
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

    public ActionForward trinhAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Connection conn = null;
        String errMess = "";
        try {

            conn = getConnection(request);
            BanLeTraiPhieuTwForm f = (BanLeTraiPhieuTwForm)form;
            BanLeTraiPhieuTwVO vo = new BanLeTraiPhieuTwVO();
            HttpSession session = request.getSession();
            String nUserID = session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString();
            BeanUtils.copyProperties(vo, f);
            vo.setTrang_thai("01");
            BanLeTraiPhieuTwDelegate delegate = new BanLeTraiPhieuTwDelegate(conn);
            long idAdd = delegate.update(vo);
            //insert history
            UserHistoryVO userHisVO = new UserHistoryVO();
            userHisVO.setNguoi_tdoi(new Long(nUserID));
            userHisVO.setNoi_dung_thaydoi("Trinh ket qua ban le trai phieu tw" + idAdd);
            userHisVO.setNsd_id(idAdd);
            delegate.insertHistoryUser(userHisVO);
            f.reset(mapping, request);
            if (idAdd != 0) {
                errMess = "BanLeTraiPhieuTw.trinhkq.succ";
            } else
                errMess = "BanLeTraiPhieuTw.trinhkq.error";
            saveMessage(request, new TPCPException(errMess));
        } catch (Exception e) {
            conn.rollback();
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
            //check xem TPCP tồn tại?
            BanLeTraiPhieuTwDelegate delegate = new BanLeTraiPhieuTwDelegate(conn);
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("GUID", guid);
            BanLeTraiPhieuTwVO voBanLeTraiPhieuTw = delegate.getBanLeTraiPhieuTwObject(map);
            String kh = voBanLeTraiPhieuTw.getKy_tt_lai();
            request.setAttribute("kh", kh);
            if (voBanLeTraiPhieuTw == null) {
                saveMessage(request, new TPCPException("BanLeTraiPhieuTw.list.norecord"));
                return mapping.findForward(FAILURE);
            }
            if (voBanLeTraiPhieuTw.getLai_suat() != null) {
                //     String laisuat = voBanLeTraiPhieuTw.getLai_suat().replace(".", ",");
                voBanLeTraiPhieuTw.setLai_suat(StringUtil.convertNumberToString(voBanLeTraiPhieuTw.getLai_suat(), "VND"));
            }
            if (voBanLeTraiPhieuTw.getKhoi_luong() != null) {
                String khoiluong = voBanLeTraiPhieuTw.getKhoi_luong();
                voBanLeTraiPhieuTw.setKhoi_luong(StringUtil.convertNumberToString(khoiluong, "VND"));
            }
            if (voBanLeTraiPhieuTw.getSo_luong() != null) {
                String soluong = voBanLeTraiPhieuTw.getSo_luong();
                voBanLeTraiPhieuTw.setSo_luong(StringUtil.convertNumberToString(soluong, "VND"));
            }
            if (voBanLeTraiPhieuTw.getMenh_gia() != null) {
                String menhgia = voBanLeTraiPhieuTw.getMenh_gia();
                voBanLeTraiPhieuTw.setMenh_gia(StringUtil.convertNumberToString(menhgia, "VND"));
            }
            if (voBanLeTraiPhieuTw.getTong_so_tt() != null) {
                String tong_so_tt = voBanLeTraiPhieuTw.getTong_so_tt();
                voBanLeTraiPhieuTw.setTong_so_tt(StringUtil.convertNumberToString(tong_so_tt, "VND"));
            }
            BanLeTraiPhieuTwForm f = (BanLeTraiPhieuTwForm)form;
            BeanUtils.copyProperties(f, voBanLeTraiPhieuTw);
            //get list TPCP
            QuanLyTPCPDelegate delegateTPCP = new QuanLyTPCPDelegate(conn);
            List listTPCP = new ArrayList();
            listTPCP = (List)delegateTPCP.getAllListTPCP();
            request.setAttribute("listTPCP", listTPCP);
            //Ky Han
            DMKyHanDelegate dmKyHanDelegate = new DMKyHanDelegate(conn);
            //get list ky han
            HashMap<String, Object> map_kyhan = new HashMap<String, Object>();
            //            map_kyhan.put("LOAI_TPCP", "TRAI_PHIEU");
            //            List lstKyHan = (List)dmKyHanDelegate.getDMKyHan(map_kyhan);
            //            request.setAttribute("lstKyHan", lstKyHan);
            List lstKyHan = (List)dmKyHanDelegate.getDMKyHan(map_kyhan);
            request.setAttribute("lstKyHan", lstKyHan);
            //
            BeanUtils.copyProperties(f, voBanLeTraiPhieuTw);
            //
            //Chi tiet
            BanLeTraiPhieuTwChiTietDelegate delegateChiTiet = new BanLeTraiPhieuTwChiTietDelegate(conn);
            HashMap<String, Object> map_chitiet = new HashMap<String, Object>();
            map_chitiet.put("KQPH_BAN_LE_ID", guid);
            Collection lstCTietVO = null; // goi DAO lay dl tu bang chi tiet theo GUID
            //
            lstCTietVO = delegateChiTiet.getListBanLeTraiPhieuChiTietTw(map_chitiet, 1);
            Collection lstCTietForm = new ArrayList();
            Iterator ito2 = lstCTietVO.iterator();
            BanLeTraiPhieuTwChiTietVO ctietVO = null;
            BanLeTraiPhieuTwChiTietForm ctietForm = null;
            int dem = 0;
            while (ito2.hasNext()) {
                ctietForm = new BanLeTraiPhieuTwChiTietForm();
                dem++;
                ctietVO = (BanLeTraiPhieuTwChiTietVO)ito2.next();
                ctietForm.setStt(dem + "");
                if (ctietVO.getSl_dky_mua() != null) {
                    String Sl_dky_mua = ctietVO.getSl_dky_mua();
                    ctietVO.setSl_dky_mua(StringUtil.convertNumberToString(Sl_dky_mua, "VND"));
                }
                if (ctietVO.getKl_dky_mua() != null) {
                    String Kl_dky_mua = ctietVO.getKl_dky_mua();
                    ctietVO.setKl_dky_mua(StringUtil.convertNumberToString(Kl_dky_mua, "VND"));
                }
                if (ctietVO.getSo_tien_tt() != null) {
                    String So_tien_tt = ctietVO.getSo_tien_tt();
                    ctietVO.setSo_tien_tt(StringUtil.convertNumberToString(So_tien_tt, "VND"));
                }
                BeanUtils.copyProperties(ctietForm, ctietVO);
                lstCTietForm.add(ctietForm);
            }
            f.setLstKQBanLe_CTiet(lstCTietForm);
            //End Chitiet
        } catch (Exception e) {
            conn.rollback();
            throw e;
        } finally {
            close(conn);
        }
        return mapping.findForward(SUCCESS);
    }

    public String getDate() {
        String date = FastDateFormat.getInstance("dd/MM/yyyy").format(System.currentTimeMillis());
        return date;
    }


    public ActionForward pheDuyetAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Connection conn = null;
        String errMess = "";
        try {

            conn = getConnection(request);
            BanLeTraiPhieuTwForm f = (BanLeTraiPhieuTwForm)form;
            BanLeTraiPhieuTwVO vo = new BanLeTraiPhieuTwVO();
            HttpSession session = request.getSession();
            String nUserID = session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString();
            BeanUtils.copyProperties(vo, f);
            vo.setTrang_thai("02");
            vo.setNgay_duyet(getDate());
            vo.setNguoi_duyet(nUserID);
            BanLeTraiPhieuTwDelegate delegate = new BanLeTraiPhieuTwDelegate(conn);
            long idAdd = delegate.update(vo);
            //insert history
            UserHistoryVO userHisVO = new UserHistoryVO();
            userHisVO.setNguoi_tdoi(new Long(nUserID));
            userHisVO.setNoi_dung_thaydoi("Phe duyet ban le trai phieu tw" + idAdd);
            userHisVO.setNsd_id(idAdd);
            delegate.insertHistoryUser(userHisVO);
            f.reset(mapping, request);
            if (idAdd != 0) {
                errMess = "BanLeTraiPhieuTw.pheduyet.succ";
            } else
                errMess = "BanLeTraiPhieuTw.pheduyet.error";
            saveMessage(request, new TPCPException(errMess));
        } catch (Exception e) {
            conn.rollback();
            throw e;
        } finally {
            close(conn);
        }
        return mapping.findForward(SUCCESS);
    }

    public ActionForward tuchoiAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Connection conn = null;
        String errMess = "";
        try {
            conn = getConnection(request);
            BanLeTraiPhieuTwForm f = (BanLeTraiPhieuTwForm)form;
            BanLeTraiPhieuTwVO vo = new BanLeTraiPhieuTwVO();
            HttpSession session = request.getSession();
            String nUserID = session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString();
            BeanUtils.copyProperties(vo, f);
            vo.setTrang_thai("03");
            vo.setNgay_duyet(getDate());
            vo.setNguoi_duyet(nUserID);
            BanLeTraiPhieuTwDelegate delegate = new BanLeTraiPhieuTwDelegate(conn);
            long idAdd = delegate.update(vo);
            //insert history
            UserHistoryVO userHisVO = new UserHistoryVO();
            userHisVO.setNguoi_tdoi(new Long(nUserID));
            userHisVO.setNoi_dung_thaydoi("Tu choi ban le trai phieu tw" + idAdd);
            userHisVO.setNsd_id(idAdd);
            delegate.insertHistoryUser(userHisVO);
            f.reset(mapping, request);
            if (idAdd != 0) {
                errMess = "BanLeTraiPhieuTw.tuchoi.succ";
            } else
                errMess = "BanLeTraiPhieuTw.tuchoi.error";
            saveMessage(request, new TPCPException(errMess));
        } catch (Exception e) {
            conn.rollback();
            throw e;
        } finally {
            close(conn);
        }
        return mapping.findForward(SUCCESS);
    }

    public void getInt(HttpServletRequest request) throws Exception {

        Connection conn = null;
        try {
            conn = getConnection(request);
            QuanLyTPCPDelegate delegate = new QuanLyTPCPDelegate(conn);
            List listTPCP = new ArrayList();

            listTPCP = (List)delegate.getLstTPCP_TIN_PHIEU();

            request.setAttribute("lstAllTPCP", listTPCP);
            //
            //QuanLyTPCPDelegate qlyTPCPDelegate = new QuanLyTPCPDelegate(conn);
            // Map<String ,Object> mapTPCP = new HashMap();
            //mapTPCP.put("",)

            DMKyHanDelegate khDelegate = new DMKyHanDelegate(conn);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("loai_tpcp", "TIN_PHIEU");

            List listKyHan = null;
            listKyHan = (List)khDelegate.getDMKyHan(map);
            request.setAttribute("listKyHan", listKyHan);
        } catch (Exception e) {
            throw e;
        } finally {
            close(conn);
        }


    }
}
