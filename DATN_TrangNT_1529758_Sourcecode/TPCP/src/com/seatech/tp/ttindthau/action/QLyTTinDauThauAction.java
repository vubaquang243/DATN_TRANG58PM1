package com.seatech.tp.ttindthau.action;

import com.seatech.framework.AppConstants;
import com.seatech.framework.common.jsp.PagingBean;
import com.seatech.framework.datamanager.ReportUtility;
import com.seatech.framework.exception.TPCPException;
import com.seatech.framework.strustx.AppAction;
import com.seatech.framework.utils.StringUtil;
import com.seatech.tp.dmkyhan.action.DMKyHanDelegate;
import com.seatech.tp.dmkyhan.vo.DMKyHanVO;
import com.seatech.tp.kqphathanh.action.QLyKQPhatHanhDelegate;
import com.seatech.tp.kqphathanh.vo.QLyKQPhatHanhVO;
import com.seatech.tp.qlytp.action.QuanLyTPCPDelegate;
import com.seatech.tp.qlytp.form.QuanLyTPCPForm;

import com.seatech.tp.qlytp.vo.QuanLyTPCPVO;

import com.seatech.tp.sotonghoptpcp.action.SoTongHopTpcpDelegate;
import com.seatech.tp.sotonghoptpcp.form.SoTongHopTpcpForm;
import com.seatech.tp.sotonghoptpcp.vo.SoTongHopTpcpVO;
import com.seatech.tp.ttindthau.form.ThongTinDauThauForm;
import com.seatech.tp.ttindthau.vo.ThongTinDauThauVO;
import com.seatech.tp.user.UserHistoryVO;

import java.io.InputStream;

import java.sql.Array;
import java.sql.Connection;

import java.sql.ResultSet;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class QLyTTinDauThauAction extends AppAction {
    private static String SUCCESS = "success";
    private static String FAILURE = "failure";

    protected ActionForward executeAction(ActionMapping mapping, ActionForm form, 
                                          HttpServletRequest request, HttpServletResponse response) throws Exception {
        Connection conn = null;
        try {
            conn = getConnection(request);
            QuanLyTPCPDelegate delegate = new QuanLyTPCPDelegate(conn);
            List listTPCP = new ArrayList();
            listTPCP = (List)delegate.getAllListTPCP_DT();
            request.setAttribute("lstAllTPCP", listTPCP);

            DMKyHanDelegate khDelegate = new DMKyHanDelegate(conn);
            Map<String, Object> map = new HashMap();
            map.put("loai_tpcp", "TRAI_PHIEU");
            List listKyHan = null;
            listKyHan = (List)khDelegate.getDMKyHan(map);
            request.setAttribute("listKyHan", listKyHan);
            
        } catch (Exception e) {
            throw e;
        } finally {
            close(conn);
        }
        return mapping.findForward(SUCCESS);
    }

    public ActionForward list(ActionMapping mapping, ActionForm form, 
                              HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            ThongTinDauThauForm f = (ThongTinDauThauForm)form;
            f.reset(mapping, request);
            return search(mapping, form, request, response);
        } catch (Exception e) {
            throw e;
        }
    }

    public ActionForward search(ActionMapping mapping, ActionForm form, 
                                HttpServletRequest request, HttpServletResponse response) throws Exception {
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
            listTTDT = (List)delegate.getListTTDTPaging(vo, currentPage, numberRowOnPage, totalCount);
            Iterator ito = listTTDT.iterator();
            ThongTinDauThauVO vo2 = null;
            Collection resultTTDT = new ArrayList();
            while (ito.hasNext()) {
                vo2 = (ThongTinDauThauVO)ito.next();
                String khoiLuong = vo2.getKhoi_luong_tp();
                vo2.setKhoi_luong_tp(StringUtil.convertNumberToString(khoiLuong, "VND"));
                if(vo2.getKhoi_luong_them()!=null){
                  vo2.setKhoi_luong_them(StringUtil.convertNumberToString(vo2.getKhoi_luong_them(), "VND"));
                }
              SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
              Date datetime = new Date();
              String strTime= sdf.format(datetime);
              if("05".equals(vo2.getTrang_thai())&& strTime.equals(vo2.getNgay_to_chuc_ph())){
                  vo2.setIsAddmore("true");
              }
                resultTTDT.add(vo2);
            }

            PagingBean pagingBean = new PagingBean();
            pagingBean.setCurrentPage(currentPage);
            pagingBean.setNumberOfRow((totalCount[0] == null) ? 0 : totalCount[0].intValue());
            pagingBean.setRowOnPage(numberRowOnPage);
            request.setAttribute("PAGE_KEY", pagingBean);
            request.setAttribute("lstTTDT", resultTTDT);
            //Lay list ma tpcp va ky han
            getInit(request);
        } catch (Exception e) {
            throw e;

        } finally {
            close(conn);
        }
        return mapping.findForward(SUCCESS);
    }

    public ActionForward add(ActionMapping mapping, ActionForm form,
                             HttpServletRequest request, HttpServletResponse response) throws Exception {
        Connection conn = null;
        try {
            conn = getConnection(request);
            ThongTinDauThauForm f = (ThongTinDauThauForm)form;
            request.setAttribute("ky_tra_lai", "0");
            QuanLyTPCPDelegate delegate = new QuanLyTPCPDelegate(conn);
            List listTPCP = new ArrayList();
            listTPCP = (List)delegate.getAllListTPCP_DT();
            request.setAttribute("lstAllTPCP", listTPCP);

            DMKyHanDelegate khDelegate = new DMKyHanDelegate(conn);
            Map<String, Object> map = new HashMap();
            map.put("loai_tpcp", "TRAI_PHIEU");
            List listKyHan = null;
            listKyHan = (List)khDelegate.getDMKyHan(map);
            request.setAttribute("listKyHan", listKyHan);
            
            QuanLyTPCPDelegate delegateTP = new QuanLyTPCPDelegate(conn);
            Map<String, Object> mapTP = new HashMap();
            if(!"".equals(f.getMa_tpcp())){
            mapTP.put("MA_TP", f.getMa_tpcp());
            }else{
              mapTP.put("MA_TP"," ");
            }
            QuanLyTPCPVO tpcpVo = delegateTP.getTTDTObject(mapTP);
            if(tpcpVo!=null){
              f.setKy_han(tpcpVo.getKy_han());
            }
        } catch (Exception e) {
            throw e;
        } finally {
            close(conn);
        }
        return mapping.findForward(SUCCESS);
    }

    public ActionForward update(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response) throws Exception {
        Connection conn = null;
        try {
            conn = getConnection();
            ThongTinDauThauForm f = (ThongTinDauThauForm)form;
            String guid = "";
            if (request.getParameter("longid") != null) {
                guid = request.getParameter("longid");
            } else {
                guid = f.getGuid();
            }
            //Lay list ma tpcp va ky han
            getInit(request);
            //check xem TPCP tồn tại?
            QLyTTinDauThauDelegate delegate = new QLyTTinDauThauDelegate(conn);
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("GUID", guid);
            ThongTinDauThauVO voDThau = delegate.getTTDTObject2(map);
            if (voDThau == null) {
                saveMessage(request, new TPCPException("ttindthau.list.norecord"));
                return mapping.findForward(FAILURE);
            }
            voDThau.setKhoi_luong_tp(StringUtil.convertNumberToString(voDThau.getKhoi_luong_tp(), "VND"));
            voDThau.setMenh_gia(StringUtil.convertNumberToString(voDThau.getMenh_gia(), "VND"));
            if (voDThau.getLs_danh_nghia() != null) {
                voDThau.setLs_danh_nghia(StringUtil.convertNumberToString(voDThau.getLs_danh_nghia(), "VND"));
            }
          if(voDThau.getKhoi_luong_them()!=null){
            voDThau.setKhoi_luong_them(StringUtil.convertNumberToString(voDThau.getKhoi_luong_them(), "VND"));
          }
            BeanUtils.copyProperties(f, voDThau);
            List listTPCP = new ArrayList();
            QuanLyTPCPDelegate delegateTPCP = new QuanLyTPCPDelegate(conn);
            listTPCP = (List)delegateTPCP.getAllListTPCP_DT();
            request.setAttribute("lstAllTPCP", listTPCP);
            Map<String,Object> map_matpcp = new HashMap<String,Object>();
            map_matpcp.put("MA_TPCP", voDThau.getMa_tpcp());
            ThongTinDauThauVO voDThau_KL = delegate.getTong_kl_da_goi_thau(map_matpcp);
            // dùng cột KHOI_LUONG_TP lưu tổng kl
            String tong_kl_da_goi_thau=StringUtil.convertNumberToString(voDThau_KL.getKhoi_luong_tp(), "VND");
            request.setAttribute("kl_goi_thau_lan_dau", tong_kl_da_goi_thau);

            DMKyHanDelegate khDelegate = new DMKyHanDelegate(conn);
            Map<String, Object> mapKH = new HashMap();
            mapKH.put("loai_tpcp", "TRAI_PHIEU");
            List listKyHan = null;
            listKyHan = (List)khDelegate.getDMKyHan(mapKH);
            request.setAttribute("listKyHan", listKyHan);
        } catch (Exception e) {

            saveError(request, new TPCPException("TPCP-0001"));
            throw e;

        } finally {
            close(conn);
        }
        return mapping.findForward(SUCCESS);

    }

    public ActionForward addExc(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request, HttpServletResponse response) throws Exception {
        //        if (!checkPermissionOnFunction(request, "SYS.QLY_NSD.THEMMOI")) {
        //            return mapping.findForward("errorQuyen");
        //        }
        Connection conn = null;
        String errMess = "";
        try {
            conn = getConnection(request);

            QuanLyTPCPDelegate delegateTP = new QuanLyTPCPDelegate(conn);
            //Lay list ma tpcp va ky han
            //getInit(request);

            ThongTinDauThauForm f = (ThongTinDauThauForm)form;
            ThongTinDauThauVO vo = new ThongTinDauThauVO();
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("MA_TP", f.getMa_tpcp());
            QuanLyTPCPVO voTP = delegateTP.getTTDTObject(map);

            if (voTP == null) {
                throw new TPCPException().createException("TPCP-0003", f.getMa_tpcp());
            }


            QLyTTinDauThauDelegate delegateDT = new QLyTTinDauThauDelegate(conn);
            HashMap<String, Object> mapDT = new HashMap<String, Object>();
            mapDT.put("DOT_DAU_THAU", f.getDot_dau_thau());
            ThongTinDauThauVO voDThau = delegateDT.getTTDTObject(mapDT);
            if (voDThau != null) {
                throw new TPCPException().createException("TPCP-0010", f.getDot_dau_thau());
            }

            // check so_tbao_goi_thau

            HashMap<String, Object> mapSTB = new HashMap<String, Object>();
            if (!"".equals(f.getSo_tbao_goi_thau())) {
                mapSTB.put("SO_TBAO_GOI_THAU", f.getSo_tbao_goi_thau());
                ThongTinDauThauVO voDThau1 = delegateDT.getTTDTObject(mapSTB);
                if (voDThau1 != null) {
                    throw new TPCPException().createException("TPCP-0010", f.getSo_tbao_goi_thau());
                }
            }

            // check dot bo sung
            if (f.getDot_bo_sung() != null && !"".equalsIgnoreCase(f.getDot_bo_sung())) {
                HashMap<String, Object> mapS = new HashMap<String, Object>();
                mapS.put("DOT_DT", f.getDot_bo_sung());
                mapS.put("TRANG_THAI","02");
                QLyKQPhatHanhDelegate delegatekq = new QLyKQPhatHanhDelegate(conn);
                QLyKQPhatHanhVO vokq = delegatekq.getQLyKQPhatHanhObject(mapS);
                if (vokq == null) {
                    throw new TPCPException().createException("TPCP-0031", f.getDot_bo_sung());
                }
            }
            // check ma đã lập chưa
            HashMap<String, Object> mapDT1 = new HashMap<String, Object>();
            mapDT1 = new HashMap<String, Object>();
            mapDT1.put("MA_TPCP", f.getMa_tpcp());
            voDThau = delegateDT.getTTDTObject(mapDT1);
            if (voDThau != null) {
                if (f.getDot_bo_sung() == null || f.getDot_bo_sung().equals("")) {
                    throw new TPCPException().createException("TPCP-0012", f.getMa_tpcp());
                }
            }
            HttpSession session = request.getSession();
            String nUserID = session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString();
            String ky_tra_lai = f.getKy_tra_lai();

            if ("0".equals(ky_tra_lai)) {
                f.setNgay_tt_lai(f.getNgay_dao_han());
            } else if ("1".equals(ky_tra_lai)) {
                String ngay_ph = f.getNgay_ph();
                Date date = StringUtil.StringToDate(ngay_ph, "dd/MM/yyyy");
                date.setYear(date.getYear() + 1);
                ngay_ph = StringUtil.DateToString(date, "dd/MM/yyyy");
                f.setNgay_tt_lai(ngay_ph);
            } else if ("2".equals(ky_tra_lai)) {
                String ngay_ph = f.getNgay_ph();
                Date date = StringUtil.StringToDate(ngay_ph, "dd/MM/yyyy");
                date.setYear(date.getMonth() + 6);
                ngay_ph = StringUtil.DateToString(date, "dd/MM/yyyy");
                f.setNgay_tt_lai(ngay_ph);
            }
            BeanUtils.copyProperties(vo, f);
            vo.setNguoi_tao(nUserID);
//            vo.setTrang_thai("00");
            if("".equals(vo.getTrang_thai()) || vo.getTrang_thai() == null){
              vo.setTrang_thai("00");
            }
            QLyTTinDauThauDelegate delegate = new QLyTTinDauThauDelegate(conn);
            long idAdd = delegate.update(vo);
            //insert history
            UserHistoryVO userHisVO = new UserHistoryVO();
            userHisVO.setNguoi_tdoi(new Long(nUserID));
            userHisVO.setNoi_dung_thaydoi("Them moi ma TTDT" + idAdd);
            userHisVO.setNsd_id(idAdd);
            delegate.insertHistoryUser(userHisVO);
            f.reset(mapping, request);
            if ("01".equals(vo.getTrang_thai())) {
                errMess = "ttindthau.trinhsave.succ";
            } else {
                errMess = "ttindthau.add.succ";
            }
            saveMessage(request, new TPCPException(errMess));
        } catch (Exception e) {
            saveError(request, new TPCPException("TPCP-0001"));
            throw e;
        } finally {
            close(conn);
        }
        return mapping.findForward(SUCCESS);
    }

    public ActionForward updateExc(ActionMapping mapping, ActionForm form, 
                                   HttpServletRequest request, HttpServletResponse response) throws Exception {
        //        if (!checkPermissionOnFunction(request, "SYS.QLY_NSD.THEMMOI")) {
        //            return mapping.findForward("errorQuyen");
        //        }
        Connection conn = null;
        String errMess = "";
        try {
            conn = getConnection(request);
            ThongTinDauThauForm f = (ThongTinDauThauForm)form;
            ThongTinDauThauVO vo = new ThongTinDauThauVO();
            QuanLyTPCPDelegate delegateTP = new QuanLyTPCPDelegate(conn);
            //Lay list ma tpcp va ky han
            getInit(request);


            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("MA_TP", f.getMa_tpcp());
            QuanLyTPCPVO voTP = delegateTP.getTTDTObject(map);
            if (voTP == null) {
                throw new TPCPException().createException("TPCP-0003", f.getMa_tpcp());
            }

            QLyTTinDauThauDelegate delegateDT = new QLyTTinDauThauDelegate(conn);
            HashMap<String, Object> mapDT = new HashMap<String, Object>();

            mapDT.put("MA_TPCP", f.getMa_tpcp());
            Collection ttdts = delegateDT.getListTTDT(mapDT);
            if (ttdts != null) {
                if (ttdts.size() > 1) {
                    if (f.getDot_bo_sung() == null || f.getDot_bo_sung().equals("")) {
                        throw new TPCPException().createException("TPCP-0012", f.getMa_tpcp());
                    }
                }
            }
            HashMap<String, Object> mapObject = new HashMap<String, Object>();
            mapObject.put("DOT_DAU_THAU", f.getDot_dau_thau());
            ThongTinDauThauVO voDThauObject = delegateDT.getTTDTObject(mapObject);
            HashMap<String, Object> mapSTB = new HashMap<String, Object>();

            if (voDThauObject.getSo_tbao_goi_thau() == null && !"".equals(f.getSo_tbao_goi_thau().trim())) {

                mapSTB.put("SO_TBAO_GOI_THAU", f.getSo_tbao_goi_thau().trim());
                ThongTinDauThauVO voDThau1 = delegateDT.getTTDTObject(mapSTB);
                if (voDThau1 != null) {
                    throw new TPCPException().createException("TPCP-0010", f.getSo_tbao_goi_thau());
                }
            } else if (voDThauObject.getSo_tbao_goi_thau() != null && !"".equals(f.getSo_tbao_goi_thau().trim())) {
                if (!f.getSo_tbao_goi_thau().equals(voDThauObject.getSo_tbao_goi_thau())) {
                    mapSTB.put("SO_TBAO_GOI_THAU", f.getSo_tbao_goi_thau().trim());
                    ThongTinDauThauVO voDThau1 = delegateDT.getTTDTObject(mapSTB);
                    if (voDThau1 != null) {
                        throw new TPCPException().createException("TPCP-0010", f.getSo_tbao_goi_thau());
                    }
                }
            }

            HttpSession session = request.getSession();
            String nUserID = session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString();
            BeanUtils.copyProperties(vo, f);
            if ("".equals(vo.getSo_tbao_goi_thau())) {
                vo.setSo_tbao_goi_thau(" ");
            }
//            if(!"".equals(vo.getLs_danh_nghia())){
//            vo.setLs_danh_nghia(StringUtil.convertNumberToString(vo.getLs_danh_nghia(), "VND"));
//            }
            vo.setNguoi_tao(nUserID);
            QLyTTinDauThauDelegate delegate = new QLyTTinDauThauDelegate(conn);
            long idAdd = delegate.update(vo);
            //insert history
            UserHistoryVO userHisVO = new UserHistoryVO();
            userHisVO.setNguoi_tdoi(new Long(nUserID));
            userHisVO.setNoi_dung_thaydoi("Update TTDT" + idAdd);
            userHisVO.setNsd_id(idAdd);
            delegate.insertHistoryUser(userHisVO);
            f.reset(mapping, request);
            if (idAdd != 0 && "00".equals(vo.getTrang_thai())) {
                errMess = "ttindthau.update.succ";
            } else if (idAdd != 0 && "01".equals(vo.getTrang_thai())) {
                errMess = "ttindthau.trinhsave.succ";
            } else
                errMess = "ttindthau.update.error";
            saveMessage(request, new TPCPException(errMess));
        } catch (Exception e) {
            saveError(request, new TPCPException("TPCP-0001"));
            throw e;
        } finally {
            close(conn);
        }
        return mapping.findForward(SUCCESS);
    }

    public ActionForward delete(ActionMapping mapping, ActionForm form, 
                                HttpServletRequest request, HttpServletResponse response) throws Exception {
        //        if (!checkPermissionOnFunction(request, "SYS.QLY_NSD.XOA")) {
        //            return mapping.findForward("errorQuyen");
        //        }
        Connection conn = null;
        String errMess = "";
        try {
            conn = getConnection();
            HttpSession session = request.getSession();
            String nUserID = session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString();
            String guid = request.getParameter("longid").trim();
            //Lay list ma tpcp va ky han
            getInit(request);
            //check xem TPCP tồn tại?
            QLyTTinDauThauDelegate delegate = new QLyTTinDauThauDelegate(conn);
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("GUID", guid);
            ThongTinDauThauVO voDThau = delegate.getTTDTObject(map);
            if (voDThau == null) {
                saveMessage(request, new TPCPException("ttindthau.list.norecord"));
                return mapping.findForward(FAILURE);
            } else {
                ThongTinDauThauForm f = (ThongTinDauThauForm)form;
                long idAdd = delegate.deleteTTDT(voDThau);
                //insert history
                UserHistoryVO userHisVO = new UserHistoryVO();
                userHisVO.setNguoi_tdoi(new Long(nUserID));
                userHisVO.setNoi_dung_thaydoi("Xoa ma TTDT " + idAdd);
                userHisVO.setNsd_id(idAdd);
                delegate.insertHistoryUser(userHisVO);
                f.reset(mapping, request);
                if (idAdd != 0) {
                    errMess = "ttindthau.delete.succ";
                } else
                    errMess = "ttindthau.delete.error";
                saveMessage(request, new TPCPException(errMess));
            }
        } catch (Exception e) {
            conn.rollback();
            saveError(request, new TPCPException("TPCP-0001"));
            throw e;

        } finally {
            close(conn);

        }
        return mapping.findForward(SUCCESS);
    }


    public ActionForward view(ActionMapping mapping, ActionForm form, 
                              HttpServletRequest request, HttpServletResponse response) throws Exception {
        Connection conn = null;
        try {
            conn = getConnection();
            String guid = request.getParameter("longid").trim();
            //check xem TPCP tồn tại?
            QLyTTinDauThauDelegate delegate = new QLyTTinDauThauDelegate(conn);
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("GUID", guid);
            ThongTinDauThauVO voDThau = delegate.getTTDTObject2(map);
            if (voDThau == null) {
                saveMessage(request, new TPCPException("ttindthau.list.norecord"));
                return mapping.findForward(FAILURE);
            }
            if (voDThau.getDot_bo_sung() != null) {
                HashMap<String, Object> map1 = new HashMap<String, Object>();
                map1.put("DOT_DAU_THAU", voDThau.getDot_bo_sung());
                ThongTinDauThauVO voDThau1 = delegate.getTTDTObject2(map1);
                request.setAttribute("voDThau1", voDThau1);               
                // Get tổng khối lượng đã gọi thầu - 
                Map<String,Object> map_matpcp = new HashMap<String,Object>();
                map_matpcp.put("MA_TPCP", voDThau.getMa_tpcp());
                ThongTinDauThauVO voDThau_KL = delegate.getTong_kl_da_goi_thau(map_matpcp);
                // dùng cột KHOI_LUONG_TP lưu tổng kl
                voDThau_KL.setKhoi_luong_tp(StringUtil.convertNumberToString(voDThau_KL.getKhoi_luong_tp(), "VND"));
                request.setAttribute("kl_goi_thau_lan_dau", voDThau_KL.getKhoi_luong_tp());
            }
          
            voDThau.setKhoi_luong_tp(StringUtil.convertNumberToString(voDThau.getKhoi_luong_tp(), "VND"));
            voDThau.setMenh_gia(StringUtil.convertNumberToString(voDThau.getMenh_gia(), "VND"));
            if(voDThau.getLs_danh_nghia()!= null){
            voDThau.setLs_danh_nghia(StringUtil.convertNumberToString(voDThau.getLs_danh_nghia(), "VND"));
            }
            if(voDThau.getKhoi_luong_them()!=null){
              voDThau.setKhoi_luong_them(StringUtil.convertNumberToString(voDThau.getKhoi_luong_them(), "VND"));
            }
            ThongTinDauThauForm f = (ThongTinDauThauForm)form;
            BeanUtils.copyProperties(f, voDThau);

            List listTPCP = new ArrayList();
            QuanLyTPCPDelegate delegateTPCP = new QuanLyTPCPDelegate(conn);
            listTPCP = (List)delegateTPCP.getAllListTPCP_DT();
            request.setAttribute("lstAllTPCP", listTPCP);
            //
            DMKyHanDelegate khDelegate = new DMKyHanDelegate(conn);
            Map<String, Object> mapKH = new HashMap();
            mapKH.put("loai_tpcp", "TRAI_PHIEU");
            List listKyHan = null;
            listKyHan = (List)khDelegate.getDMKyHan(mapKH);
            request.setAttribute("listKyHan", listKyHan);
        } catch (Exception e) {
            throw e;
        } finally {
            close(conn);
        }
        return mapping.findForward(SUCCESS);

    }


    public ActionForward trinhAction(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response) throws Exception {
        //        if (!checkPermissionOnFunction(request, "SYS.QLY_NSD.THEMMOI")) {
        //            return mapping.findForward("errorQuyen");
        //        }
        Connection conn = null;
        String errMess = "";
        try {
            conn = getConnection(request);
            String guid = request.getParameter("longid").trim();
            //check xem TPCP tồn tại?
            QLyTTinDauThauDelegate delegate = new QLyTTinDauThauDelegate(conn);
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("GUID", guid);
            ThongTinDauThauVO voDThau = delegate.getTTDTObject2(map);
            if (voDThau == null) {
                saveMessage(request, new TPCPException("ttindthau.list.norecord"));
                return mapping.findForward(FAILURE);
            }

            HttpSession session = request.getSession();
            String nUserID = session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString();
            voDThau.setTrang_thai("01");
            if( voDThau.getLs_danh_nghia()!=null){
              voDThau.setLs_danh_nghia(StringUtil.convertNumberToString(voDThau.getLs_danh_nghia(), "VND"));
            }
            long idAdd = delegate.update(voDThau);
            //insert history
            UserHistoryVO userHisVO = new UserHistoryVO();
            userHisVO.setNguoi_tdoi(new Long(nUserID));
            userHisVO.setNoi_dung_thaydoi("Trinh TTDT" + idAdd);
            userHisVO.setNsd_id(idAdd);
            delegate.insertHistoryUser(userHisVO);
            if (idAdd != 0) {
                errMess = "ttindthau.trinh.succ";
            } else
                errMess = "ttindthau.trinh.error";
            saveMessage(request, new TPCPException(errMess));
        } catch (Exception e) {
            throw e;
        } finally {
            close(conn);
        }
        return mapping.findForward(SUCCESS);
    }


    public ActionForward pheDuyetAction(ActionMapping mapping, ActionForm form,
                                        HttpServletRequest request, HttpServletResponse response) throws Exception {
        //check quyen

        Connection conn = null;
        String errMess = "";
        try {
            conn = getConnection(request);
            ThongTinDauThauForm f = (ThongTinDauThauForm)form;

            HttpSession session = request.getSession();
            String nUserID = session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString();
            //check xem TPCP tồn tại?
            QLyTTinDauThauDelegate delegate = new QLyTTinDauThauDelegate(conn);
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("GUID", f.getGuid());
            ThongTinDauThauVO voDThau = delegate.getTTDTObject2(map);
            if (voDThau == null) {
                saveMessage(request, new TPCPException("ttindthau.list.norecord"));
                return mapping.findForward(FAILURE);
            }

            voDThau.setTrang_thai(f.getTrang_thai());
            if( voDThau.getLs_danh_nghia()!=null){
                voDThau.setLs_danh_nghia(StringUtil.convertNumberToString(voDThau.getLs_danh_nghia(), "VND"));
              }
            voDThau.setLy_do_tu_choi(f.getLy_do_tu_choi());
            voDThau.setNguoi_phe_duyet(nUserID);
            Date date = new Date();
            voDThau.setNgay_phe_duyet(StringUtil.DateToString(date, "dd/MM/yyyy"));

            long idAdd = delegate.update(voDThau);
            //insert history
            UserHistoryVO userHisVO = new UserHistoryVO();
            userHisVO.setNguoi_tdoi(new Long(nUserID));
            userHisVO.setNoi_dung_thaydoi("Phe Duyet TTDT" + idAdd);
            userHisVO.setNsd_id(idAdd);
            delegate.insertHistoryUser(userHisVO);


            if (idAdd != 0) {
                if ("02".equals(f.getTrang_thai())) {
                    errMess = "ttindthau.pheduyet.succ";
                } else if ("03".equals(f.getTrang_thai())) {
                    errMess = "ttindthau.tuchoi.succ";
                }
            } else {
                if ("02".equals(f.getTrang_thai())) {
                    errMess = "ttindthau.pheduyet.error";
                } else if ("03".equals(f.getTrang_thai())) {
                    errMess = "ttindthau.tuchoi.error";
                }
            }
            f.reset(mapping, request);
            saveMessage(request, new TPCPException(errMess));
        } catch (Exception e) {
            throw e;
        } finally {
            close(conn);
        }
        return mapping.findForward(SUCCESS);
    }

    public void getInit(HttpServletRequest request) throws Exception {
        Connection conn = null;
        try {
            conn = getConnection(request);
            QuanLyTPCPDelegate delegate = new QuanLyTPCPDelegate(conn);
            List listTPCP = new ArrayList();
            listTPCP = (List)delegate.getAllListTPCP_DT();
            request.setAttribute("lstAllTPCP", listTPCP);

            DMKyHanDelegate khDelegate = new DMKyHanDelegate(conn);
            Map<String, Object> map = new HashMap();
            map.put("loai_tpcp", "TRAI_PHIEU");
            List listKyHan = null;
            listKyHan = (List)khDelegate.getDMKyHan(map);
            request.setAttribute("listKyHan", listKyHan);
        } catch (Exception e) {
            throw e;
        } finally {
            close(conn);
        }
    }
    public static final String REPORT_DIRECTORY = "/report";
    public static final String strFontTimeRoman = "/font/times.ttf";
    //    public static final String fileName = "/ThongTinDauThau";

    public ActionForward printAction(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response) throws Exception {

        Connection conn = null;
        InputStream reportStream = null;
        StringBuffer sbSubHTML = new StringBuffer();
        try {
            conn = getConnection();
            ThongTinDauThauForm f = (ThongTinDauThauForm)form;
            QLyTTinDauThauDelegate delegate = new QLyTTinDauThauDelegate(conn);
            DMKyHanDelegate delegateKH = new DMKyHanDelegate(conn);
           
            
            Map<String, Object> map = new HashMap();
            map.put("GUID", f.getKy_han());
            DMKyHanVO vokh = (DMKyHanVO)delegateKH.getDMKyHanObject(map);
            ThongTinDauThauVO vo = new ThongTinDauThauVO();
            BeanUtils.copyProperties(vo, f);
            String fileName;
            String reportName;
            Map<String, Object> mapBS = new HashMap();
            QLyKQPhatHanhVO qlVO = null;
            QLyKQPhatHanhDelegate delegateQL = new QLyKQPhatHanhDelegate(conn);
            // đọc số tiền đợt bổ sung 
             String ls="";
            ls= f.getLs_danh_nghia().replace(",",".");
            f.setLs_danh_nghia(StringUtil.convertNumberToString(ls, "VND"));
            if ("".equals(f.getDot_bo_sung())) {
                if( f.getKhoi_luong_them()==null|| "".equals(f.getKhoi_luong_them())){                
                  fileName = "/ThongTinDauThau";
                  reportName = "/ThongTinDauThau";
                }else{
               
                  fileName = "/ThongTinDauThauAddThem";
                  reportName = "/ThongTinDauThauAddThem";
                }
            } else {
              if( f.getKhoi_luong_them()==null||"".equals(f.getKhoi_luong_them())){
                
                fileName = "/ThongTinDauThauDotBS";
                reportName = "/ThongTinDauThauDotBS";
              }else{
                
       
                fileName = "/ThongTinDauThauAddThemBS";
                reportName = "/ThongTinDauThauAddThemBS";
              }
                
                mapBS.put("DOT_DT", f.getDot_bo_sung());
                qlVO = delegateQL.getQLyKQPhatHanhObject(mapBS);

            }
            String hinh_thuc_dt = null;
            String nam = f.getNgay_ph().substring(6,10);
            
            if ("01".equals(f.getPthuc_xacdinh_kqdt())) {
                hinh_thuc_dt = "Đấu thầu đơn giá";
            }
            if ("02".equals(f.getPthuc_xacdinh_kqdt())) {
                hinh_thuc_dt = "Đấu thầu đa giá";
            }
            String so_lan_tt = null;
            String kieu_tra_lai = null;
            String[] str = null;
            if ("0".equals(f.getKy_tra_lai())) {
                so_lan_tt = "0";
                kieu_tra_lai = "";
            }
            if ("1".equals(f.getKy_tra_lai())) {
                so_lan_tt = "1";
                str = f.getNgay_tt_lai_1().split("/");
                kieu_tra_lai = "Tiền lãi trái phiếu được thanh toán định kỳ hàng năm vào ngày " + str[0] + "/" + str[1] +" từ năm "+str[2]+ " đến năm  đáo hạn.";

            }
            if ("2".equals(f.getKy_tra_lai())) {
                so_lan_tt = "2";
                str = f.getNgay_tt_lai_1().split("/");
                String[] str1 = f.getNgay_tt_lai_2().split("/");
                kieu_tra_lai =
                        "Tiền lãi trái phiếu được thanh toán định kỳ hàng năm vào ngày " + str[0] + "/" + str[1] + " và ngày " + str1[0] + "/" + str1[1] +" từ năm "+str1[2]+ " đến năm đáo hạn.";
            }
            String dv;
            if("VND".equals(f.getLoai_tien())){
              dv ="đồng";
            }else{
              dv="đô";
            }
            sbSubHTML.append("<input type=\"hidden\" name=\"ky_tra_lai\" value=\"" + f.getKy_tra_lai() + "\" id=\"ky_tra_lai\"></input>");
            sbSubHTML.append("<input type=\"hidden\" name=\"dot_dau_thau\" value=\"" + f.getDot_dau_thau() + "\" id=\"dot_dau_thau\"></input>");
            sbSubHTML.append("<input type=\"hidden\" name=\"dot_bo_sung\" value=\"" + f.getDot_bo_sung() + "\" id=\"dot_bo_sung\"></input>");
            sbSubHTML.append("<input type=\"hidden\" name=\"ma_tpcp\" value=\"" + f.getMa_tpcp() + "\" id=\"ma_tpcp\"></input>");
            sbSubHTML.append("<input type=\"hidden\" name=\"khoi_luong_tp\" value=\"" + f.getKhoi_luong_tp() + "\" id=\"khoi_luong_tp\"></input>");
            sbSubHTML.append("<input type=\"hidden\" name=\"loai_tien\" value=\"" + f.getLoai_tien() + "\" id=\"loai_tien\"></input>");
            sbSubHTML.append("<input type=\"hidden\" name=\"ky_han\" value=\"" + f.getKy_han() + "\" id=\"ky_han\"></input>");
            sbSubHTML.append("<input type=\"hidden\" name=\"ngay_to_chuc_ph\" value=\"" + f.getNgay_to_chuc_ph() + "\" id=\"ngay_to_chuc_ph\"></input>");
            sbSubHTML.append("<input type=\"hidden\" name=\"ngay_ph\" value=\"" + f.getNgay_ph() + "\" id=\"ngay_ph\"></input>");
            sbSubHTML.append("<input type=\"hidden\" name=\"ngay_tt_tien_mua\" value=\"" + f.getNgay_tt_tien_mua() + "\" id=\"ngay_tt_tien_mua\"></input>");
            sbSubHTML.append("<input type=\"hidden\" name=\"ngay_tt_lai\" value=\"" + f.getNgay_tt_lai() + "\" id=\"ngay_tt_lai\"></input>");
            sbSubHTML.append("<input type=\"hidden\" name=\"ngay_dao_han\" value=\"" + f.getNgay_dao_han() + "\" id=\"ngay_dao_han\"></input>");
            sbSubHTML.append("<input type=\"hidden\" name=\"phuong_thuc_thanh_toan\" value=\"" + f.getPhuong_thuc_thanh_toan() + "\" id=\"phuong_thuc_thanh_toan\"></input>");
            sbSubHTML.append("<input type=\"hidden\" name=\"hinh_thuc_dt\" value=\"" + f.getHinh_thuc_dt() + "\" id=\"hinh_thuc_dt\"></input>");
            sbSubHTML.append("<input type=\"hidden\" name=\"so_tk_nhan\" value=\"" + f.getSo_tk_nhan() + "\" id=\"so_tk_nhan\"></input>");
            sbSubHTML.append("<input type=\"hidden\" name=\"khoi_luong_tp_chu\" value=\"" + f.getKhoi_luong_tp_chu() + "\" id=\"khoi_luong_tp_chu\"></input>");
            sbSubHTML.append("<input type=\"hidden\" name=\"menh_gia_chu\" value=\"" + f.getMenh_gia_chu() + "\" id=\"menh_gia_chu\"></input>");
            sbSubHTML.append("<input type=\"hidden\" name=\"ls_danh_nghia\" value=\"" + f.getLs_danh_nghia() + "\" id=\"ls_danh_nghia\"></input>");
            sbSubHTML.append("<input type=\"hidden\" name=\"ky_tra_lai\" value=\"" + f.getKy_tra_lai() + "\" id=\"ky_tra_lai\"></input>");
            ResultSet rsNSD = null;
            //rsNSD = delegate.getSoTongHopTpcpList(vo);
            if (rsNSD == null) {
                JasperPrint jasperPrint = null;
                Map<String, Object> parameterMap = new HashMap<String, Object>();
                
                if (qlVO != null) {
                    parameterMap.put("pTong_klph", StringUtil.convertNumberToString(qlVO.getTong_klph(), "VND"));
                    String tong_kl_chu = qlVO.getTong_klph_chu().replace("đồng chẵn","");
                    if("VND".equals(qlVO.getLoai_tien())){
                      tong_kl_chu=tong_kl_chu+" đồng";
                    }else{
                      tong_kl_chu=tong_kl_chu+" đô";
                    }
                    parameterMap.put("pTong_klph_chu", tong_kl_chu.toLowerCase());
                }
                parameterMap.put("pNam",nam);
                parameterMap.put("pDonvitien",dv);
                parameterMap.put("pKhoi_luong_them", f.getKhoi_luong_them());
                parameterMap.put("pKhoi_luong_them_chu", f.getKhoi_luong_them_chu());
                parameterMap.put("pDot_dau_thau", f.getDot_dau_thau());
                parameterMap.put("pDot_bo_sung", f.getDot_bo_sung());
                parameterMap.put("pMa_tpcp", f.getMa_tpcp());
                parameterMap.put("pKhoi_luong_tp", f.getKhoi_luong_tp());
                parameterMap.put("pLoai_tien", f.getLoai_tien());
                parameterMap.put("pKy_han", vokh.getMo_ta());
                parameterMap.put("pNgay_to_chuc_ph", f.getNgay_to_chuc_ph());
                parameterMap.put("pNgay_ph", f.getNgay_ph());
                parameterMap.put("pNgay_tt_tien_mua", f.getNgay_tt_tien_mua());
                parameterMap.put("pNgay_tt_lai", f.getNgay_tt_lai());
                parameterMap.put("pNgay_dao_han", f.getNgay_dao_han());
                parameterMap.put("pPhuong_thuc_thanh_toan", f.getPhuong_thuc_thanh_toan());
                parameterMap.put("pSo_tk_nhan", f.getSo_tk_nhan());
                parameterMap.put("pMenh_gia_chu", f.getMenh_gia_chu());
                parameterMap.put("pKhoi_luong_tp_chu", f.getKhoi_luong_tp_chu());
                parameterMap.put("pMenh_gia", f.getMenh_gia());
                parameterMap.put("pLs_danh_nghia",f.getLs_danh_nghia());
                parameterMap.put("pHinh_thuc_dt", hinh_thuc_dt);
                parameterMap.put("pSo_lan_tt", so_lan_tt);
                parameterMap.put("pNgay_tt_lai_lan_1", f.getNgay_tt_lai_1());
                parameterMap.put("pNgay_tt_lai_lan_2", f.getNgay_tt_lai_2());
                parameterMap.put("pKieu_tra_lai", kieu_tra_lai);
                reportStream = getServlet().getServletConfig().getServletContext().getResourceAsStream(REPORT_DIRECTORY + reportName + ".jasper");
                JRDataSource jrDS = new JRResultSetDataSource(rsNSD);
                jasperPrint = JasperFillManager.fillReport(reportStream, parameterMap, conn);

                ReportUtility rpUtilites = new ReportUtility();

                String strTypePrintAction = request.getParameter(AppConstants.REQUEST_ACTION) == null ? "" : request.getParameter(AppConstants.REQUEST_ACTION).toString();
                String strActionName = "PrintThongTinDauThauAction.do";
                String strParameter = "";
                String strPathFont = getServlet().getServletContext().getContextPath() + REPORT_DIRECTORY + strFontTimeRoman;

                rpUtilites.exportReport(jasperPrint, strTypePrintAction, response, fileName, strPathFont, strActionName, sbSubHTML.toString(), strParameter);

            }

        } catch (Exception e) {
            throw e;
        } finally {
            try {
                reportStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            close(conn);
        }
        return mapping.findForward(SUCCESS);
    }


}
