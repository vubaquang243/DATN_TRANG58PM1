package com.seatech.tp.qlytinphieu.action;

import com.seatech.framework.AppConstants;
import com.seatech.framework.common.jsp.PagingBean;
import com.seatech.framework.exception.TPCPException;
import com.seatech.framework.strustx.AppAction;
import com.seatech.framework.utils.StringUtil;
import com.seatech.tp.dmkyhan.action.DMKyHanDelegate;
import com.seatech.tp.qlytinphieu.form.HDBanTinPhieuForm;
import com.seatech.tp.qlytinphieu.vo.HDBanTinPhieuVo;
import com.seatech.tp.qlytp.action.QuanLyTPCPDelegate;
import com.seatech.tp.qlytp.vo.QuanLyTPCPVO;
import com.seatech.tp.ttindthau.action.QLyTTinDauThauDelegate;
import com.seatech.tp.ttindthau.form.ThongTinDauThauForm;
import com.seatech.tp.ttindthau.vo.ThongTinDauThauVO;
import com.seatech.tp.user.UserHistoryVO;

import java.sql.Connection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class HDBanTinPhieuAction extends AppAction {
    private static String SUCCESS = "success";
    private static String FAILURE = "failure";

    protected ActionForward executeAction(ActionMapping mapping,
                                          ActionForm form,
                                          HttpServletRequest request,
                                          HttpServletResponse response) throws Exception {
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
        return mapping.findForward(SUCCESS);
    }

    public ActionForward list(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        try {
            HDBanTinPhieuForm f = (HDBanTinPhieuForm)form;
            f.reset(mapping, request);
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
            int stt=0;
            request.setAttribute("stt", stt);
            int phantrang = (AppConstants.APP_NUMBER_ROW_ON_PAGE);
            // khai bao bien phan trang.
            HDBanTinPhieuDelegate delegate = new HDBanTinPhieuDelegate(conn);
            getInt(request);
            String page = f.getPageNumber();
            if (page == null)
                page = "1";
            Integer currentPage = new Integer(page);
            Integer numberRowOnPage = phantrang;
            Integer totalCount[] = new Integer[1];
            HDBanTinPhieuVo vo = new HDBanTinPhieuVo();
            List allListHD = null;
            allListHD =(List)delegate.getLisHDTin_PhieuPaging(vo, currentPage,
                                                           numberRowOnPage,
                                                           totalCount);
            String ls= f.getLai_suat().trim();
            String kl= f.getKl_tp().trim();
            f.setLai_suat(ls.replace(",","."));
            f.setKl_tp(kl.replace(".", "")); // replace
            BeanUtils.copyProperties(vo, f);
            
            List listHDTinPhieu = null;
            listHDTinPhieu =
                    (List)delegate.getLisHDTin_PhieuPaging(vo, currentPage,
                                                           numberRowOnPage,
                                                           totalCount);
            // get tất cả list hợp đồng tín phiếu
            
            Iterator ito = listHDTinPhieu.iterator();
            HDBanTinPhieuVo vo2 = null;
            Collection resultTTDT = new ArrayList();
            while (ito.hasNext()) {
                vo2 = (HDBanTinPhieuVo)ito.next();
                vo2.setKl_tp(StringUtil.convertNumberToString(vo2.getKl_tp(),
                                                              "VND"));
                vo2.setLai_suat(StringUtil.convertNumberToString(vo2.getLai_suat(),
                                                              "VND"));
                
                resultTTDT.add(vo2);
            }

            PagingBean pagingBean = new PagingBean();
            pagingBean.setCurrentPage(currentPage);
            pagingBean.setNumberOfRow((totalCount[0] == null) ? 0 :
                                      totalCount[0].intValue());
            pagingBean.setRowOnPage(numberRowOnPage);

            //change

            DMKyHanDelegate khDelegate = new DMKyHanDelegate(conn);
            Map<String, Object> map1 = new HashMap<String, Object>();
            map1.put("loai_tpcp", "TIN_PHIEU");
            List listKyHan = null;
            listKyHan = (List)khDelegate.getDMKyHan(map1);
            request.setAttribute("allListHD",allListHD);
            request.setAttribute("listKyHan", listKyHan);
            request.setAttribute("PAGE_KEY", pagingBean);
            request.setAttribute("listHDTinPhieu", resultTTDT);

        } catch (Exception e) {
            throw e;

        } finally {
            close(conn);
        }
        return mapping.findForward(SUCCESS);
    }

    public ActionForward add(ActionMapping mapping, ActionForm form,
                             HttpServletRequest request,
                             HttpServletResponse response) throws Exception {
        Connection conn = null;
        try {
            conn = getConnection(request);
            HDBanTinPhieuForm f = (HDBanTinPhieuForm)form;
            QuanLyTPCPDelegate delegateTP = new QuanLyTPCPDelegate(conn);
            Map<String, Object> mapTP = new HashMap();
            if(!"".equals(f.getMa_tp())){
            mapTP.put("MA_TP", f.getMa_tp());
            QuanLyTPCPVO tpcpVo = delegateTP.getTTDTObject(mapTP);
            if(tpcpVo!=null){
              f.setKy_han(tpcpVo.getKy_han());
            }
            }
//          f.reset(mapping, request);
            
        } catch (Exception e) {
            throw e;
            }finally{
              close(conn);
            }
      return executeAction(mapping, form, request, response);
    }

    public ActionForward addExc(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
        //        if (!checkPermissionOnFunction(request, "SYS.QLY_NSD.THEMMOI")) {
        //            return mapping.findForward("errorQuyen");
        //        }
        Connection conn = null;
        String errMess = "";
        try {
            conn = getConnection(request);
            getInt(request);
            HDBanTinPhieuForm f = (HDBanTinPhieuForm)form;
            HDBanTinPhieuVo vo = new HDBanTinPhieuVo();
            QuanLyTPCPDelegate delegateTP = new QuanLyTPCPDelegate(conn);
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("MA_TP", f.getMa_tp().trim());
            map.put("KY_HAN", "");
            QuanLyTPCPVO voTP = delegateTP.getTTDTObject(map);

            if (voTP == null) {
                throw new TPCPException().createException("TPCP-0003",
                                                          f.getMa_tp());
            }
            HDBanTinPhieuDelegate hdBanTinPhieudelegate =
                new HDBanTinPhieuDelegate(conn);
            HashMap<String, Object> mapDT = new HashMap<String, Object>();
            
            // them dk
            mapDT.put("SO_HD", f.getSo_hd());
            HDBanTinPhieuVo hdBanTinPhieuVo =
                hdBanTinPhieudelegate.getHDTinPhieuObject(mapDT);
            if (hdBanTinPhieuVo != null) {
                throw new TPCPException().createException("TPCP-0011",f.getSo_hd());
            }
            // check ma_tp
            HashMap<String, Object> mapMa = new HashMap<String, Object>();
            mapMa.put("SO_HD", f.getSo_hd());
            HDBanTinPhieuVo hdBanTinPhieuVo1 =
                hdBanTinPhieudelegate.getHDTinPhieuObject(mapMa);
            if (hdBanTinPhieuVo1 != null) {
                throw new TPCPException().createException("TPCP-0021",f.getSo_hd());
            }
          
            HttpSession session = request.getSession();
            String nUserID =
                session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString();

            BeanUtils.copyProperties(vo, f);
            vo.setNguoi_tao(nUserID);
            if (vo.getTrang_thai().trim().equals("")) {
                vo.setTrang_thai("00");
            }
//            vo.setNgay_tao(;
            long idAdd = hdBanTinPhieudelegate.update(vo);
            //insert history
            UserHistoryVO userHisVO = new UserHistoryVO();
            userHisVO.setNguoi_tdoi(new Long(nUserID));
            userHisVO.setNoi_dung_thaydoi("Them moi ma HD tín phiếu" + idAdd);
            userHisVO.setNsd_id(idAdd);
            hdBanTinPhieudelegate.insertHistoryUser(userHisVO);
            f.reset(mapping, request);
            if(vo.getTrang_thai().equals("01")){
            errMess = "hdbantinphieu.add.sub";
            }else{
              errMess = "hdbantinphieu.add.succ";
            }
            saveMessage(request, new TPCPException(errMess));
        } catch (Exception e) {
            saveError(request, new TPCPException(errMess));
            throw e;
        } finally {
            close(conn);
        }
        return mapping.findForward(SUCCESS);
    }


    public ActionForward update(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
        Connection conn = null;
        try {
            conn = getConnection();
            HDBanTinPhieuForm hdForm = (HDBanTinPhieuForm)form;
            String guid = null;
            if (hdForm.getGuid() == null || hdForm.getGuid().trim() == "") {
                guid = request.getParameter("longid").trim();
            } else {
                guid = (String)hdForm.getGuid();
            }
            HDBanTinPhieuDelegate hdBanTinPhieudelegate =
                new HDBanTinPhieuDelegate(conn);
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("GUID", guid);
            HDBanTinPhieuVo hdBanTinPhieuVo =
                hdBanTinPhieudelegate.getHDTinPhieuObject(map);
            if (hdBanTinPhieuVo == null) {
                saveMessage(request,
                            new TPCPException("ttindthau.list.norecord"));
                return mapping.findForward(FAILURE);
            }
            //            voDThau.setKhoi_luong_tp(StringUtil.formatCurrency(voDThau.getKhoi_luong_tp()));
            //            voDThau.setMenh_gia(StringUtil.formatCurrency(voDThau.getMenh_gia()));
            //            request.setAttribute("ky_tra_lai",voDThau.getKy_tra_lai());
            //            request.setAttribute("ky_han",voDThau.getKy_han());
            HDBanTinPhieuForm f = (HDBanTinPhieuForm)form;

            BeanUtils.copyProperties(f, hdBanTinPhieuVo);
            f.setKl_tp(StringUtil.convertNumberToString(f.getKl_tp(), "VND"));
            f.setGia_ban(StringUtil.convertNumberToString(f.getGia_ban(),
                                                          "VND"));
            f.setLai_suat(StringUtil.convertNumberToString(f.getLai_suat(),
                                                          "VND"));
            request.setAttribute("ma_tp", hdBanTinPhieuVo.getMa_tp());
            request.setAttribute("ky_han", hdBanTinPhieuVo.getKy_han());
            
            QuanLyTPCPDelegate delegate = new QuanLyTPCPDelegate(conn);
            List listTPCP = new ArrayList();
  
            listTPCP = (List)delegate.getAllListTPCP_TIN_PHIEU();
         
            request.setAttribute("lstAllTPCP", listTPCP);
            //            List listTPCP = new ArrayList();
            //            QuanLyTPCPDelegate delegateTPCP = new QuanLyTPCPDelegate(conn);
            //            listTPCP = (List)delegateTPCP.getAllListTPCP();
            //            Iterator ito = listTPCP.iterator();
            //            QuanLyTPCPVO vo = null;
            //            List<String> arrMaTP = new ArrayList<String>();
            //            while (ito.hasNext()) {
            //                vo = (QuanLyTPCPVO)ito.next();
            //                if (vo.getMa_tp() != null && !vo.getMa_tp().equals("")) {
            //                    arrMaTP.add(vo.getMa_tp());
            //                }
            //            }
            //            request.setAttribute("lstAllTPCP", listTPCP);


            DMKyHanDelegate khDelegate = new DMKyHanDelegate(conn);
            Map<String, Object> mapKH = new HashMap();
            mapKH.put("loai_tpcp", "TIN_PHIEU");
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


    public ActionForward updateExc(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response) throws Exception {
        //        if (!checkPermissionOnFunction(request, "SYS.QLY_NSD.THEMMOI")) {
        //            return mapping.findForward("errorQuyen");
        //        }
        Connection conn = null;
        String errMess = "";
        try {
            conn = getConnection(request);
            HDBanTinPhieuForm f = (HDBanTinPhieuForm)form;
            HDBanTinPhieuVo vo = new HDBanTinPhieuVo();
            QuanLyTPCPDelegate delegateTP = new QuanLyTPCPDelegate(conn);
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("MA_TP", f.getMa_tp().trim());
            map.put("KY_HAN","");
            QuanLyTPCPVO voTP = delegateTP.getTTDTObject(map);
            if (voTP == null) {
                throw new TPCPException().createException("TPCP-0003",
                                                          f.getMa_tp());
            }
            
            
           // kiem tra da lap hop dong hay chua 
             HashMap<String, Object> map1 = new HashMap<String, Object>();
             map1.put("SO_HD",f.getSo_hd());
             HDBanTinPhieuVo voCheck = new HDBanTinPhieuVo();
             HDBanTinPhieuDelegate delegate= new HDBanTinPhieuDelegate(conn);
             voCheck = delegate.getHDTinPhieuObject(map1);
//             HashSet set= new HashSet();
//             set.add(voCheck.getMa_tp());
//             set.add(f.getMa_tp());
//             if(set.size()!=1){
//              HDBanTinPhieuVo voCheckTP =delegate.getHDTinPhieuObject(map) ;
//               if(voCheckTP!= null){
//                  throw new TPCPException().createException("TPCP-0021", f.getMa_tp());
//                }
//             }
             if(!f.getMa_tp().equals(voCheck.getMa_tp())){
                HDBanTinPhieuVo voCheckTP =delegate.getHDTinPhieuObject(map) ;
               if(voCheckTP!= null){
                 throw new TPCPException().createException("TPCP-0021", f.getMa_tp());
               }
             }

            
//            vo1 = delegate.getHDTinPhieuObject(map);
//            if(vo1!= null && vo2 == null){
//              throw new TPCPException().createException("TPCP-0021",
//                                                          f.getMa_tp());
//            }

            HttpSession session = request.getSession();
            String nUserID =
                session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString();
            BeanUtils.copyProperties(vo, f);
            //            vo.setNguoi_tao(nUserID);
            //            vo.setMenh_gia(f.getMenh_gia().replace(".", "").replace(",", ""));
            //            vo.setKhoi_luong_tp(f.getKhoi_luong_tp().replace(".",
            //                                                             "").replace(",",
            //                                                                         ""));
            HDBanTinPhieuDelegate hdBanTinPhieudelegate =
                new HDBanTinPhieuDelegate(conn);
            long idAdd = hdBanTinPhieudelegate.update(vo);
            //insert history
            UserHistoryVO userHisVO = new UserHistoryVO();
            userHisVO.setNguoi_tdoi(new Long(nUserID));
            userHisVO.setNoi_dung_thaydoi("Update HDTP" + idAdd);
            userHisVO.setNsd_id(idAdd);
            hdBanTinPhieudelegate.insertHistoryUser(userHisVO);
            f.reset(mapping, request);
            if (idAdd != 0 && vo.getTrang_thai().equals("00")) {
                errMess = "hdbantinphieu.update.succ";
            } else if(idAdd != 0 && vo.getTrang_thai().equals("01")){
                errMess = "hdbantinphieu.add.sub";
            }else{
                errMess = "hdbantinphieu.add.error";
            }
            saveMessage(request, new TPCPException(errMess));
        } catch (Exception e) {
            throw e;
        } finally {
            close(conn);
        }
        return mapping.findForward(SUCCESS);
    }

    public ActionForward delete(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
        //        if (!checkPermissionOnFunction(request, "SYS.QLY_NSD.XOA")) {
        //            return mapping.findForward("errorQuyen");
        //        }
        Connection conn = null;
        String errMess = "";
        try {
            conn = getConnection();
            HttpSession session = request.getSession();
            String nUserID =
                session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString();
            String guid = request.getParameter("longid").trim();
            //check xem TPCP tồn tại?
            HDBanTinPhieuDelegate hdBanTinPhieudelegate =
                new HDBanTinPhieuDelegate(conn);
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("GUID", guid);
            HDBanTinPhieuVo hdBanTinPhieuVo =
                hdBanTinPhieudelegate.getHDTinPhieuObject(map);
            if (hdBanTinPhieuVo == null) {
                saveMessage(request,
                            new TPCPException("hdbantinphieu.list.norecord"));
                return mapping.findForward(FAILURE);
            } else {
                HDBanTinPhieuForm f = (HDBanTinPhieuForm)form;
                long idAdd = hdBanTinPhieudelegate.deleteTTDT(hdBanTinPhieuVo);
                //insert history
                UserHistoryVO userHisVO = new UserHistoryVO();
                userHisVO.setNguoi_tdoi(new Long(nUserID));
                userHisVO.setNoi_dung_thaydoi("Xoa Hợp đồng bán tín phiếu  " +
                                              idAdd);
                userHisVO.setNsd_id(idAdd);
                hdBanTinPhieudelegate.insertHistoryUser(userHisVO);
                f.reset(mapping, request);
                if (idAdd != 0) {
                    errMess = "hdbantinphieu.delete.succ";
                } else
                    errMess = "hdbantinphieu.delete.error";
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

    public ActionForward view(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        Connection conn = null;
        try {
            conn = getConnection();
            String guid = request.getParameter("longid").trim();
            //check xem TPCP tồn tại?
            HDBanTinPhieuDelegate hdBanTinPhieudelegate =
                new HDBanTinPhieuDelegate(conn);
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("GUID", guid);
            HDBanTinPhieuVo hdBanTinPhieuVo =
                hdBanTinPhieudelegate.getHDTinPhieuObject(map);
            if (hdBanTinPhieuVo == null) {
                saveMessage(request,
                            new TPCPException("hdbantinphieu.list.norecord"));
                return mapping.findForward(FAILURE);
            }
            //            voDThau.setKhoi_luong_tp(StringUtil.formatCurrency(voDThau.getKhoi_luong_tp()));
            //            voDThau.setMenh_gia(StringUtil.formatCurrency(voDThau.getMenh_gia()));
            HDBanTinPhieuForm f = (HDBanTinPhieuForm)form;
            BeanUtils.copyProperties(f, hdBanTinPhieuVo);
            f.setKl_tp(StringUtil.convertNumberToString(f.getKl_tp(), "VND"));
            f.setGia_ban(StringUtil.convertNumberToString(f.getGia_ban(),
                                                          "VND"));
            f.setLai_suat(StringUtil.convertNumberToString(f.getLai_suat(),
                                                          "VND"));
            //          List listTPCP = new ArrayList();
            //          QuanLyTPCPDelegate delegateTPCP = new QuanLyTPCPDelegate(conn);
            //          listTPCP = (List)delegateTPCP.getAllListTPCP();
            //          request.setAttribute("lstAllTPCP", listTPCP);
            //
            DMKyHanDelegate khDelegate = new DMKyHanDelegate(conn);
            Map<String, Object> mapKH = new HashMap();
            mapKH.put("loai_tpcp", "TIN_PHIEU");
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
                                     HttpServletRequest request,
                                     HttpServletResponse response) throws Exception {
        //        if (!checkPermissionOnFunction(request, "SYS.QLY_NSD.THEMMOI")) {
        //            return mapping.findForward("errorQuyen");
        //        }
        Connection conn = null;
        String errMess = "";
        try {
            conn = getConnection(request);
            HDBanTinPhieuForm f = (HDBanTinPhieuForm)form;
            HDBanTinPhieuVo vo = new HDBanTinPhieuVo();
            QuanLyTPCPDelegate delegateTP = new QuanLyTPCPDelegate(conn);
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("MA_TP", f.getMa_tp());
            QuanLyTPCPVO voTP = delegateTP.getTTDTObject(map);
            //            if (voTP != null) {
            //                throw new TPCPException().createException("TPCP-0003",
            //                                                          f.getMa_tp());
            //            }
            HttpSession session = request.getSession();
            String nUserID =
                session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString();
            BeanUtils.copyProperties(vo, f);
      
            HDBanTinPhieuDelegate hdBanTinPhieudelegate =
                new HDBanTinPhieuDelegate(conn);
            long idAdd = hdBanTinPhieudelegate.update(vo);
            //insert history
            UserHistoryVO userHisVO = new UserHistoryVO();
            userHisVO.setNguoi_tdoi(new Long(nUserID));
            userHisVO.setNoi_dung_thaydoi("Trinh HD Tín Phiếu" + idAdd);
            userHisVO.setNsd_id(idAdd);
            hdBanTinPhieudelegate.insertHistoryUser(userHisVO);
            f.reset(mapping, request);
            if (idAdd != 0) {

                errMess = "hdbantinphieu.trinh.succ";
            } else
                errMess = "hdbantinphieu.trinh.error";
            saveMessage(request, new TPCPException(errMess));
        } catch (Exception e) {
            throw e;
        } finally {
            close(conn);
        }
        return mapping.findForward(SUCCESS);
    }


    public ActionForward pheDuyetAction(ActionMapping mapping, ActionForm form,
                                        HttpServletRequest request,
                                        HttpServletResponse response) throws Exception {
        //check quyen

        Connection conn = null;
        String errMess = "";
        try {
            conn = getConnection();
            //String guid = request.getParameter("longid").trim();
            HDBanTinPhieuForm f = (HDBanTinPhieuForm)form;
            HttpSession session = request.getSession();
            String nUserID =
                session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString();
            //check xem TPCP tồn tại?
            HDBanTinPhieuDelegate hdBanTinPhieudelegate =
                new HDBanTinPhieuDelegate(conn);
            HDBanTinPhieuVo hdBanTinPhieuVo = new HDBanTinPhieuVo();
            BeanUtils.copyProperties(hdBanTinPhieuVo, f);

            long idAdd = hdBanTinPhieudelegate.update(hdBanTinPhieuVo);
            //insert history
            UserHistoryVO userHisVO = new UserHistoryVO();
            userHisVO.setNguoi_tdoi(new Long(nUserID));
            userHisVO.setNoi_dung_thaydoi("Phe Duyet TTDT" + idAdd);
            userHisVO.setNsd_id(idAdd);
            hdBanTinPhieudelegate.insertHistoryUser(userHisVO);
            f.reset(mapping, request);
            if (idAdd != 0) {
                if (hdBanTinPhieuVo.getTrang_thai().equals("02")) {
                    errMess = "hdbantinphieu.pheduyet.succ";
                } else if (hdBanTinPhieuVo.getTrang_thai().equals("03")) {
                    errMess = "hdbantinphieu.tuchoi.succ";
                }
            } else
                errMess = "hdbantinphieu.pheduyet.error";
            saveMessage(request, new TPCPException(errMess));
        } catch (Exception e) {
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
