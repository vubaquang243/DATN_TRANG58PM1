
package com.seatech.tp.qlykqphtinphieu.action;

import com.seatech.framework.AppConstants;
import com.seatech.framework.common.jsp.PagingBean;
import com.seatech.framework.exception.TPCPException;
import com.seatech.framework.strustx.AppAction;
import com.seatech.framework.utils.StringUtil;
import com.seatech.tp.banletraiphieutw.form.BanLeTraiPhieuTwForm;
import com.seatech.tp.dmkyhan.action.DMKyHanDelegate;
import com.seatech.tp.dmkyhan.vo.DMKyHanVO;
import com.seatech.tp.dmtraichu.action.DMTraiChuDelegate;
import com.seatech.tp.dmtraichu.vo.DMTraiChuVO;
import com.seatech.tp.kqphathanh.action.QLyKQPhatHanhDelegate;
import com.seatech.tp.kqphathanh.form.QLyKQPhatHanhCTiet_SoHuuForm;
import com.seatech.tp.kqphathanh.form.QLyKQPhatHanhCTiet_TpcpForm;
import com.seatech.tp.kqphathanh.form.QLyKQPhatHanhForm;
import com.seatech.tp.kqphathanh.vo.QLyKQPhatHanhCTiet_SoHuuVO;
import com.seatech.tp.kqphathanh.vo.QLyKQPhatHanhCTiet_TpcpVO;
import com.seatech.tp.kqphathanh.vo.QLyKQPhatHanhVO;
import com.seatech.tp.qlykqphtinphieu.dao.KQPHTinPhieuDao;
import com.seatech.tp.qlykqphtinphieu.form.KQPHTinPhieuForm;

import com.seatech.tp.qlykqphtinphieu.vo.KQPHTinPhieuCTSoHuuVo;
import com.seatech.tp.qlykqphtinphieu.vo.KQPHTinPhieuCTTPCPVo;
import com.seatech.tp.qlykqphtinphieu.vo.KQPHTinPhieuVo;
import com.seatech.tp.qlytp.action.QuanLyTPCPDelegate;
import com.seatech.tp.qlytp.vo.QuanLyTPCPVO;
import com.seatech.tp.ttindthau.action.QLyTTinDauThauDelegate;
import com.seatech.tp.ttindthau.vo.ThongTinDauThauVO;

import com.seatech.tp.user.UserHistoryVO;

import java.io.ByteArrayInputStream;

import java.math.BigDecimal;

import java.sql.Connection;

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

import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

public class KQPHTinPhieuAction extends AppAction {
    private static String SUCCESS = "success";
    private static String FAILURE = "failure";

    public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Connection conn = null;
        try {
            KQPHTinPhieuForm f = (KQPHTinPhieuForm)form;
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
            KQPHTinPhieuForm f = (KQPHTinPhieuForm)form;
            int phantrang = (AppConstants.APP_NUMBER_ROW_ON_PAGE);
            // khai bao bien phan trang.
            String page = f.getPageNumber();
            if (page == null)
                page = "1";
            Integer currentPage = new Integer(page);
            Integer numberRowOnPage = phantrang;
            Integer totalCount[] = new Integer[1];
            KQPHTinPhieuDelegate delegate = new KQPHTinPhieuDelegate(conn);
            KQPHTinPhieuVo vo = new KQPHTinPhieuVo();

            BeanUtils.copyProperties(vo, f);

            List lstKQphathanh = null;
            lstKQphathanh = (List)delegate.getListKQPHPaging(vo, currentPage, numberRowOnPage, totalCount);
            Iterator ito = lstKQphathanh.iterator();
            Collection resultKQPhathanh = new ArrayList();
            List traiphieuVo = null;

            while (ito.hasNext()) {
                vo = (KQPHTinPhieuVo)ito.next();
                if (vo.getTong_klph() != null) {
                    vo.setTong_klph(StringUtil.convertNumberToString(vo.getTong_klph(), "VND"));
                }
                if (vo.getTong_kl_trung_thau() != null) {
                    vo.setTong_kl_trung_thau(StringUtil.convertNumberToString(vo.getTong_kl_trung_thau(), "VND"));
                }
                if (vo.getLs_binh_quan() != null) {
                    vo.setLs_binh_quan(StringUtil.convertNumberToString(vo.getLs_binh_quan(), "VND"));
                }
                Map<String, Object> mapTP = new HashMap();
                mapTP.put("TP_KQPH_TIN_PHIEU_ID", vo.getGuid());
                traiphieuVo = (List)delegate.getListQLyKQPhatHanh_CTIET_TPCP(mapTP);
                vo.setLstCTPH_TPCP(traiphieuVo);
                resultKQPhathanh.add(vo);
            }
            PagingBean pagingBean = new PagingBean();
            pagingBean.setCurrentPage(currentPage);
            pagingBean.setNumberOfRow((totalCount[0] == null) ? 0 : totalCount[0].intValue());
            pagingBean.setRowOnPage(numberRowOnPage);

            // get list TPCP

            QuanLyTPCPDelegate deleg = new QuanLyTPCPDelegate(conn);
            List listTPCP = new ArrayList();
            listTPCP = (List)deleg.getAllListTPCP_TIN_PHIEU();

            request.setAttribute("listTinPhieu", listTPCP);
            request.setAttribute("PAGE_KEY", pagingBean);
            request.setAttribute("lstKQphathanh", resultKQPhathanh);
            //            f.reset(mapping, request);
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
            conn = getConnection();
            KQPHTinPhieuForm f = (KQPHTinPhieuForm)form;
            f.reset(mapping, request);

        } catch (Exception e) {
            throw e;
        } finally {
            close(conn);
        }
        return executeAction(mapping, form, request, response);
    }

    protected ActionForward executeAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Connection conn = null;
        try {
            conn = getConnection(request);
            QLyTTinDauThauDelegate delegate = new QLyTTinDauThauDelegate(conn);
            List listDot_DT = new ArrayList();
            listDot_DT = (List)delegate.getTTDTObject_DotDT();
            Iterator ito = listDot_DT.iterator();
            ThongTinDauThauVO vo = null;
            List<String> arrDot_DT = new ArrayList<String>();
            while (ito.hasNext()) {
                vo = (ThongTinDauThauVO)ito.next();
                if (vo.getDot_dau_thau() != null && !vo.getDot_dau_thau().equals("")) {
                    arrDot_DT.add(vo.getDot_dau_thau());
                }
            }
            request.setAttribute("lstAllDotDT", arrDot_DT);
        } catch (Exception e) {
            throw e;
        } finally {
            close(conn);
        }
        return mapping.findForward(SUCCESS);
    }

    public ActionForward viewUploadAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Connection conn = null;
        try {
            conn = getConnection();
            KQPHTinPhieuForm f = (KQPHTinPhieuForm)form;
            KQPHTinPhieuVo voTinPhieu = new KQPHTinPhieuVo();
            Map<String, Object> mapp = new HashMap<String, Object>();
            mapp.put("DOT_PH", f.getDot_ph());
            KQPHTinPhieuDelegate delegate = new KQPHTinPhieuDelegate(conn);
            voTinPhieu = (KQPHTinPhieuVo)delegate.getQLyKQPhatHanhTinPhieuObject(mapp);
            if (voTinPhieu != null) {
                throw new TPCPException().createException("TPCP-0006", f.getDot_ph());
            }
            FormFile file = f.getFileUpload();
            if (file.getFileSize() == 0) {
                throw new TPCPException().createException("TPCP-0004");
            }
            HttpSession session = request.getSession();
            String nUserID = session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString();
            //only allow excel to upload
            String endType = file.getFileName().substring(file.getFileName().lastIndexOf(".") + 1, file.getFileName().length());
            //file size cant larger than 10kb
            if (file.getFileSize() > 102400) { //10kb
                throw new TPCPException().createException("TPCP-0005");
            }
            // get du lieu tu file
            KQPHTinPhieuVo vo = getDataFromFile(file.getFileData(), endType);
            // check xem mã trái phiếu có trùng nhau không
            if (vo == null) {
                throw new TPCPException().createException("TPCP-0019");
            }
            // check ma_tpcp và ngày phát hành


            // kiểm tra maTPCP có phải tin phiếu k không
            Map<String, Object> mapMa_TPCP = new HashMap<String, Object>();
            mapMa_TPCP.put("MA_TP", vo.getMa_tpcp().trim());
            mapMa_TPCP.put("PHUONG_THUC_PH", "TPKB");
            mapMa_TPCP.put("TRANG_THAI", "03");
            QuanLyTPCPDelegate delegatetp = new QuanLyTPCPDelegate(conn);
            QuanLyTPCPVO voQL = new QuanLyTPCPVO();
            voQL = (QuanLyTPCPVO)delegatetp.getTPCPObjectTin_Phieu(mapMa_TPCP);
            if (voQL == null) {
                throw new TPCPException().createException("TPCP-0017", vo.getMa_tpcp());
            }
           
            // kiểm tra xem Mã TP và Loại tiền có trùng nhau không .

            Map<String, Object> mapVo = new HashMap<String, Object>();
            mapVo.put("MA_TPCP", vo.getMa_tpcp());
            KQPHTinPhieuVo voCheck = delegate.getQLyKQPhatHanhTinPhieuObject(mapVo);
            if (voCheck != null) {
                mapVo.put("LOAI_TIEN", vo.getLoai_tien());
                KQPHTinPhieuVo vo1 = delegate.getQLyKQPhatHanhTinPhieuObject(mapVo);
                if (vo1 == null) {
                    throw new TPCPException().createException("TPCP-0022");
                }
            }
            Map<String, Object> mapMa_TPCP_ngay_ph = new HashMap<String, Object>();


            SimpleDateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat formatter2 = new SimpleDateFormat("dd/MMM/yyyy");
            Date date = formatter1.parse(vo.getNgay_ph());
            String strDate = formatter2.format(date);
            mapMa_TPCP_ngay_ph.put("MA_TPCP", vo.getMa_tpcp());
            mapMa_TPCP_ngay_ph.put("ngay_ph", strDate);
            KQPHTinPhieuVo voCheckMa_tpcp = delegate.getQLyKQPhatHanhTinPhieuObject(mapMa_TPCP_ngay_ph);
            if (voCheckMa_tpcp != null) {
                throw new TPCPException().createException("TPCP-0034", vo.getNgay_ph());
            }
            // check ngay đáo hạn
            Map<String,Object> map_ngay_dao_han= new HashMap();
            map_ngay_dao_han.put("MA_TPCP", vo.getMa_tpcp());
            map_ngay_dao_han.put("trang_thai","02");
            KQPHTinPhieuVo kq_ngay_dao_han = delegate.getQLyKQPhatHanhTinPhieuObject(map_ngay_dao_han);
            if(kq_ngay_dao_han!=null){
                if(!kq_ngay_dao_han.getNgay_dao_han().equals(vo.getNgay_dao_han())){
                  throw new TPCPException().createException("TPCP-0041", vo.getNgay_dao_han());
                }
            }
            Collection list = new ArrayList();
            Collection list_tp = vo.getLstCTPH_TPCP();
            Iterator itr = list_tp.iterator();
            KQPHTinPhieuCTTPCPVo ctTPCP = new KQPHTinPhieuCTTPCPVo();
            while (itr.hasNext()) {
                ctTPCP = (KQPHTinPhieuCTTPCPVo)itr.next();
                ctTPCP.setKy_han(voQL.getKy_han());
                list.add(ctTPCP);
            }
            vo.setLstCTPH_TPCP(list);
            vo.setKy_han(voQL.getGuid());
            // kiem tra dot phat hanh : có đúng định dạng
            //            String dot_ph = (String)f.getDot_ph();
            //            String[] words = dot_ph.split("/");
            //            String array1 = words[1].substring(2);
            //            String ma_tpcp = vo.getMa_tpcp().trim();
            //            String ma_tp = ma_tpcp.substring(4, 6);
            //            if (!array1.equals(ma_tp)) {
            //                throw new TPCPException().createException("TPCP-0016", dot_ph);
            //            }


            vo.setNguoi_tao(nUserID);
            vo.setDot_ph(f.getDot_ph());
            BeanUtils.copyProperties(f, vo);
            request.setAttribute("sDot_ph", vo.getDot_ph());
            request.setAttribute("sNgay_ph", vo.getNgay_ph());
            request.setAttribute("sNgay_dao_han", vo.getNgay_dao_han());
            request.setAttribute("sDot_dtThem", vo.getDot_dt());
            request.setAttribute("sKyhan_Them", vo.getKy_han());
            request.setAttribute("listCTSHTinPhieu", f.getListCTSHTinPhieu());
            request.setAttribute("listCTPH_TPCP", f.getLstCTPH_TPCP());
            session.setAttribute("FileData", file.getFileData());

            DMKyHanDelegate dmKyHanDelegate = new DMKyHanDelegate(conn);
            HashMap<String, Object> map = new HashMap<String, Object>();
            //            map.put("LOAI_TPCP", "TIN_PHIEU");
            List lstKyHan = (List)dmKyHanDelegate.getDMKyHan(map);
            request.setAttribute("lstKyHan", lstKyHan);
            this.saveToken(request);
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

            // check xem KQPHTinPHieu có tồn tại
            KQPHTinPhieuDelegate kqphTinPhieuDelegate = new KQPHTinPhieuDelegate(conn);
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("GUID", guid);
            KQPHTinPhieuVo kqphTinPhieuVo = new KQPHTinPhieuVo();
            kqphTinPhieuVo = kqphTinPhieuDelegate.getQLyKQPhatHanhTinPhieuObject(map);
            HashMap<String, Object> mapKH = new HashMap<String, Object>();

            //            mapKH.put("KY_HAN", kqphTinPhieuVo.getKy_han());
            //            DMKyHanDelegate deleKy_han= new DMKyHanDelegate(conn);
            //            DMKyHanVO Ky_HanVo= new DMKyHanVO();
            //            Ky_HanVo =(DMKyHanVO) deleKy_han.getDMKyHanObject(mapKH);
            //            KQPHTinPhieuCTTPCPVo cttpcpVo= new KQPHTinPhieuCTTPCPVo();
            //            cttpcpVo= (KQPHTinPhieuCTTPCPVo)kqphTinPhieuVo.getLstCTPH_TPCP();
            //            cttpcpVo.setKy_han((String)Ky_HanVo.getMo_ta());


            if (kqphTinPhieuVo.getTong_tien_ban() != null) {
                kqphTinPhieuVo.setTong_tien_ban(StringUtil.convertNumberToString(kqphTinPhieuVo.getTong_tien_ban(), "VND"));
            }
            if (kqphTinPhieuVo.getTong_kl_trung_thau() != null) {
                kqphTinPhieuVo.setTong_kl_trung_thau(StringUtil.convertNumberToString(kqphTinPhieuVo.getTong_kl_trung_thau(), "VND"));
            }
            // get listCTSHTinPhieu và get listCTietTPCP
            Map<String, Object> mapSH = new HashMap<String, Object>();
            mapSH.put("TP_KQPH_TIN_PHIEU_ID", kqphTinPhieuVo.getGuid());
            kqphTinPhieuVo.setListCTSHTinPhieu(kqphTinPhieuDelegate.getListQLyKQPhatHanh_CTIET_SOHUU(mapSH));
            // xử lý các dấu chấm

            Iterator itr = kqphTinPhieuVo.getListCTSHTinPhieu().iterator();
            KQPHTinPhieuCTSoHuuVo voSH = new KQPHTinPhieuCTSoHuuVo();
            Collection result1 = new ArrayList();
            while (itr.hasNext()) {
                voSH = (KQPHTinPhieuCTSoHuuVo)itr.next();
                if (voSH.getKl_trung_thau() != null) {
                    voSH.setKl_trung_thau(StringUtil.convertNumberToString(voSH.getKl_trung_thau(), "VND"));
                }
                if (voSH.getTien_tt_mua() != null) {
                    voSH.setTien_tt_mua(StringUtil.convertNumberToString(voSH.getTien_tt_mua(), "VND"));
                }
                if (voSH.getLs_trung_thau() != null) {
                    voSH.setLs_trung_thau(StringUtil.convertNumberToString(voSH.getLs_trung_thau(), "VND"));
                }
                result1.add(voSH);
            }
            kqphTinPhieuVo.setListCTSHTinPhieu(result1);
            Map<String, Object> mapS = new HashMap<String, Object>();
            mapS.put("GUID", kqphTinPhieuVo.getGuid());
            kqphTinPhieuVo.setLstCTPH_TPCP(kqphTinPhieuDelegate.getListQLyKQPhatHanh_CTIET_TPCP(mapSH));
            KQPHTinPhieuCTTPCPVo ctTPCPVo = null;
            Collection result2 = new ArrayList();
            Iterator itr1 = kqphTinPhieuVo.getLstCTPH_TPCP().iterator();

            while (itr1.hasNext()) {
                ctTPCPVo = (KQPHTinPhieuCTTPCPVo)itr1.next();
                if (ctTPCPVo.getLs_binh_quan() != null) {
                    ctTPCPVo.setLs_binh_quan(StringUtil.convertNumberToString(ctTPCPVo.getLs_binh_quan(), "VND"));
                }
                if (ctTPCPVo.getKlph() != null) {
                    ctTPCPVo.setKlph(StringUtil.convertNumberToString(ctTPCPVo.getKlph(), "VND"));
                }
                if (ctTPCPVo.getTong_klph() != null) {
                    ctTPCPVo.setTong_klph(StringUtil.convertNumberToString(ctTPCPVo.getTong_klph(), "VND"));
                }
                if (ctTPCPVo.getKlph_them() != null) {
                    ctTPCPVo.setKlph_them(StringUtil.convertNumberToString(ctTPCPVo.getKlph_them(), "VND"));
                }

                result2.add(ctTPCPVo);
            }
            kqphTinPhieuVo.setLstCTPH_TPCP(result2);
            KQPHTinPhieuForm f = (KQPHTinPhieuForm)form;

            BeanUtils.copyProperties(f, kqphTinPhieuVo);
            request.setAttribute("KQPHTinPhieu", kqphTinPhieuVo);

        } catch (Exception e) {
            throw e;
        } finally {
            close(conn);
        }
        return mapping.findForward(SUCCESS);
    }

    public ActionForward addExc(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        Connection conn = null;
        String errMess = "";
        try {
            conn = getConnection();
            KQPHTinPhieuForm f = (KQPHTinPhieuForm)form;
            KQPHTinPhieuVo vo = new KQPHTinPhieuVo();
            BeanUtils.copyProperties(vo, f);
            if (vo.getTrang_thai().equals("")) {
                vo.setTrang_thai("00");
            }
            
            vo.setKy_han(vo.getKy_han().substring(0, 2));
            //Thông tin đấu thầu phát hành tín phiếu
            //            String[] ma_tpcp = request.getParameterValues("ma_tpcp");
            String[] ky_han = request.getParameterValues("ky_han");
            String[] ngay_ph = request.getParameterValues("ngay_ph");
            String[] ngay_dao_han = request.getParameterValues("ngay_dao_han");
            String[] ls_binh_quan = request.getParameterValues("ls_binh_quan");
            String[] klph = request.getParameterValues("klph");
            String[] klph_them = request.getParameterValues("klph_them");
            String[] tong_klph = request.getParameterValues("tong_klph");
            int m = 0;
            if (ls_binh_quan != null && ls_binh_quan.length > 0) {
                m = ls_binh_quan.length;
            }
            int i = 0;
            KQPHTinPhieuCTTPCPVo voz = null;
            Collection listCTTP = new ArrayList<KQPHTinPhieuCTTPCPVo>();
            while (i < m) {
                voz = new KQPHTinPhieuCTTPCPVo();
                voz.setMa_tpcp(f.getMa_tpcp());
                //set ky han luc update theo VO cha
                
             //   voz.setKy_han(ky_han[i+1]);
                voz.setNgay_ph(ngay_ph[i]);
                voz.setNgay_dao_han(ngay_dao_han[i]);
                voz.setLs_binh_quan(ls_binh_quan[i]);
                voz.setKlph(klph[i]);
                voz.setKlph_them(klph_them[i]);
                voz.setTong_klph(tong_klph[i]);
                listCTTP.add(voz);
                i++;
            }
            vo.setLstCTPH_TPCP(listCTTP);
            HttpSession session = request.getSession();
            String nUserID = session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString();
            if (vo.getTrang_thai() == null || "".equals(vo.getTrang_thai())) {
                vo.setTrang_thai("00");
            }

            vo.setNguoi_tao(nUserID);

            XuLyPhanChiTietSoHuu(request, vo);
            BeanUtils.copyProperties(f, vo);
            KQPHTinPhieuDelegate delegate = new KQPHTinPhieuDelegate(conn);
         
            long idAdd = delegate.update(vo);
            //insert vao bang temp
            if (idAdd > 0) {
                String ngayTimeStamp = StringUtil.convertDateToString(Calendar.getInstance(), "DDMMYYYYHH24MISS");
                byte[] byteData = (byte[])session.getAttribute("FileData");
                session.removeAttribute("FileData");
                delegate.insertTP_KQPH_TIN_PHIEU_FILE(byteData, idAdd, ngayTimeStamp, new Long(nUserID));
            }
            //insert history
            UserHistoryVO userHisVO = new UserHistoryVO();
            userHisVO.setNguoi_tdoi(new Long(nUserID));
            userHisVO.setNoi_dung_thaydoi("Tai thong tin ket qua phat hanh " + idAdd);
            userHisVO.setNsd_id(idAdd);
            delegate.insertHistoryUser(userHisVO);
            if (idAdd != 0) {
                errMess = "kqphathanh.add.succ";
            } else {
                errMess = "kqphathanh.update.error";
            }
            saveMessage(request, new TPCPException(errMess));
            f.reset(mapping, request);
        } catch (Exception e) {
            throw e;
        } finally {
            close(conn);
        }
        return mapping.findForward(SUCCESS);

    }

    //Xóa kqtp

    public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Connection conn = null;
        String errMess = "";
        try {
            conn = getConnection();
            HttpSession session = request.getSession();
            String nUserID = session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString();
            String guid = request.getParameter("longid").trim();
            KQPHTinPhieuDelegate delegate = new KQPHTinPhieuDelegate(conn);
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("GUID", guid);
            KQPHTinPhieuVo voQLPhatHanh = delegate.getQLyKQPhatHanhTinPhieuObject(map);
            if (voQLPhatHanh == null) {
                saveMessage(request, new TPCPException("kqphathanh.list.norecord"));
                return mapping.findForward(FAILURE);
            } else {
                KQPHTinPhieuForm f = (KQPHTinPhieuForm)form;
                long idAdd = delegate.deleteQLyKQPhatHanh(voQLPhatHanh);
                //insert history
                UserHistoryVO userHisVO = new UserHistoryVO();
                userHisVO.setNguoi_tdoi(new Long(nUserID));
                userHisVO.setNoi_dung_thaydoi("Xoa ma kqphathanh " + idAdd);
                userHisVO.setNsd_id(idAdd);
                delegate.insertHistoryUser(userHisVO);
                f.reset(mapping, request);
                if (idAdd != 0) {
                    errMess = "kqphathanh.delete.succ";
                } else {
                    errMess = "kqphathanh.delete.error";
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

    public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Connection conn = null;
        try {
            conn = getConnection();
            HttpSession session = request.getSession();
            String guid = request.getParameter("longid").trim();
            //check xem TPCP tÃ¡Â»â€œn tÃ¡ÂºÂ¡i?
            KQPHTinPhieuDelegate delegate = new KQPHTinPhieuDelegate(conn);
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("GUID", guid);
            KQPHTinPhieuVo voQLPhatHanh = delegate.getQLyKQPhatHanhTinPhieuObject(map);
            session.setAttribute("sKy_han", voQLPhatHanh.getKy_han());
            if (voQLPhatHanh == null) {
                saveMessage(request, new TPCPException("KQPhathanh.list.norecord"));
                return mapping.findForward(FAILURE);
            }
            if (voQLPhatHanh.getTong_tien_ban() != null) {
                String tongtienban = voQLPhatHanh.getTong_tien_ban();
                voQLPhatHanh.setTong_tien_ban(StringUtil.convertNumberToString(tongtienban, "VND"));
            }
            if (voQLPhatHanh.getTong_klph() != null) {
                String tong_klph = voQLPhatHanh.getTong_klph();
                voQLPhatHanh.setTong_klph(StringUtil.convertNumberToString(tong_klph, "VND"));
            }
            if (voQLPhatHanh.getTong_klph_them() != null) {
                String tong_klph_them = voQLPhatHanh.getTong_klph_them();
                voQLPhatHanh.setTong_klph_them(StringUtil.convertNumberToString(tong_klph_them, "VND"));
            }

            if (voQLPhatHanh.getTong_kl_trung_thau() != null) {
                String tong_kl_trung_thau = voQLPhatHanh.getTong_kl_trung_thau();
                voQLPhatHanh.setTong_kl_trung_thau(StringUtil.convertNumberToString(tong_kl_trung_thau, "VND"));
            }
            KQPHTinPhieuForm f = (KQPHTinPhieuForm)form;
            BeanUtils.copyProperties(f, voQLPhatHanh);
            //Ky Han
            DMKyHanDelegate dmKyHanDelegate = new DMKyHanDelegate(conn);
            //get list ky han
            HashMap<String, Object> map_kyhan = new HashMap<String, Object>();
            map_kyhan.put("LOAI_TPCP", "TIN_PHIEU");
            List lstKyHan = (List)dmKyHanDelegate.getDMKyHan(map_kyhan);
            request.setAttribute("lstKyHan", lstKyHan);
            //Chi tiet - TPCP
            HashMap<String, Object> map_chitiet = new HashMap<String, Object>();
            map_chitiet.put("TP_KQPH_TIN_PHIEU_ID", guid);
            Collection lstCTiet_TPCPVO = null;
            lstCTiet_TPCPVO = delegate.getListQLyKQPhatHanh_CTIET_TPCP(map_chitiet);
            Collection lstCTiet_TPCPForm = new ArrayList();
            Iterator itoTPCP = lstCTiet_TPCPVO.iterator();

            KQPHTinPhieuCTTPCPVo ctiet_TPCPVO = null;


            while (itoTPCP.hasNext()) {
                ctiet_TPCPVO = new KQPHTinPhieuCTTPCPVo();

                ctiet_TPCPVO = (KQPHTinPhieuCTTPCPVo)itoTPCP.next();
                if (ctiet_TPCPVO.getLs_binh_quan() != null) {
                    ctiet_TPCPVO.setLs_binh_quan(StringUtil.convertNumberToString(ctiet_TPCPVO.getLs_binh_quan(), "VND"));
                }

                if (ctiet_TPCPVO.getKlph() != null) {
                    String Klph = ctiet_TPCPVO.getKlph();
                    ctiet_TPCPVO.setKlph(StringUtil.convertNumberToString(Klph, "VND"));
                }
                if (ctiet_TPCPVO.getKlph_them() != null) {
                    String Klph_them = ctiet_TPCPVO.getKlph_them();
                    ctiet_TPCPVO.setKlph_them(StringUtil.convertNumberToString(Klph_them, "VND"));
                }
                if (ctiet_TPCPVO.getTong_klph() != null) {
                    String Tong_klph = ctiet_TPCPVO.getTong_klph();
                    ctiet_TPCPVO.setTong_klph(StringUtil.convertNumberToString(Tong_klph, "VND"));
                }

                lstCTiet_TPCPForm.add(ctiet_TPCPVO);
            }

            f.setLstCTPH_TPCP(lstCTiet_TPCPForm);
            session.setAttribute("lstKQDT_CTiet_TPCP", f.getLstCTPH_TPCP());
            //End Chitiet tpcp

            // Chi tiet so huu
            Collection lstCTiet_SoHuuVO = null;
            lstCTiet_SoHuuVO = delegate.getListQLyKQPhatHanh_CTIET_SOHUU(map_chitiet);
            Collection lstCTiet_SoHuuForm = new ArrayList();
            Iterator itoSoHuu = lstCTiet_SoHuuVO.iterator();
            KQPHTinPhieuCTSoHuuVo ctiet_SoHuuVO = null;

            int dem_sohuu = 0;
            while (itoSoHuu.hasNext()) {
                ctiet_SoHuuVO = new KQPHTinPhieuCTSoHuuVo();
                dem_sohuu++;
                ctiet_SoHuuVO = (KQPHTinPhieuCTSoHuuVo)itoSoHuu.next();
                ctiet_SoHuuVO.setStt(dem_sohuu + "");
                if (ctiet_SoHuuVO.getKl_trung_thau() != null) {
                    String Kl_trung_thau = ctiet_SoHuuVO.getKl_trung_thau();
                    ctiet_SoHuuVO.setKl_trung_thau(StringUtil.convertNumberToString(Kl_trung_thau, "VND"));
                }
                if (ctiet_SoHuuVO.getTien_tt_mua() != null) {
                    String Tien_tt_mua = ctiet_SoHuuVO.getTien_tt_mua();
                    ctiet_SoHuuVO.setTien_tt_mua(StringUtil.convertNumberToString(Tien_tt_mua, "VND"));
                }
                if (ctiet_SoHuuVO.getLs_trung_thau() != null) {
                    ctiet_SoHuuVO.setLs_trung_thau(StringUtil.convertNumberToString(ctiet_SoHuuVO.getLs_trung_thau(), "VND"));
                }
                lstCTiet_SoHuuForm.add(ctiet_SoHuuVO);

            }
            f.setListCTSHTinPhieu(lstCTiet_SoHuuForm);
            //            f.setLstKQPH_CTiet_SoHuu(lstCTiet_SoHuuForm);
            //End Chitiet tpcp
            //End chi tiet so huu
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
            conn = getConnection();
            KQPHTinPhieuForm f = (KQPHTinPhieuForm)form;
            KQPHTinPhieuVo vo = new KQPHTinPhieuVo();

            BeanUtils.copyProperties(vo, f);
            String[] ky_han = request.getParameterValues("ky_han");
            String[] ngay_ph = request.getParameterValues("ngay_ph");
            String[] ngay_dao_han = request.getParameterValues("ngay_dao_han");
            String[] ls_binh_quan = request.getParameterValues("ls_binh_quan");
            String[] klph = request.getParameterValues("klph");
            String[] klph_them = request.getParameterValues("klph_them");
            String[] tong_klph = request.getParameterValues("tong_klph");
            int m = 0;
            if (ls_binh_quan != null && ls_binh_quan.length > 0) {
                m = ls_binh_quan.length;
            }
            int i = 0;
            // cắt chuỗi kỳ hạn
            String[] arrKH = vo.getKy_han().split(" ");
            vo.setKy_han(arrKH[0]);
            KQPHTinPhieuCTTPCPVo voz = null;
            Collection listCTTP = new ArrayList<KQPHTinPhieuCTTPCPVo>();
            while (i < m) {
                String[] word = ky_han[i].split(" ");
                voz = new KQPHTinPhieuCTTPCPVo();
                voz.setMa_tpcp(f.getMa_tpcp());
                voz.setKy_han(word[0].trim());
                voz.setNgay_ph(ngay_ph[i]);
                voz.setNgay_dao_han(ngay_dao_han[i]);
                voz.setLs_binh_quan(ls_binh_quan[i]);
                voz.setKlph(klph[i]);
                voz.setKlph_them(klph_them[i]);
                voz.setTong_klph(tong_klph[i]);
                listCTTP.add(voz);
                i++;
            }
            vo.setLstCTPH_TPCP(listCTTP);
            //Thông tin chi tiết kết quả phát hành tín phiếu
            String[] stt = request.getParameterValues("stt");
            String[] thanh_vien_dt = request.getParameterValues("thanh_vien_dt");
            String[] ten_chu_so_huu = request.getParameterValues("ten_chu_so_huu");
            String[] so_tk_tt = request.getParameterValues("so_tk_tt");
            //          String[] tong_kl_trung_thau =request.getParameterValues("tong_kl_trung_thau");
            String[] kl_trung_thau = request.getParameterValues("kl_trung_thau");
            String[] ls_trung_thau = request.getParameterValues("ls_trung_thau");
            String[] tien_tt_mua = request.getParameterValues("tien_tt_mua");
            KQPHTinPhieuCTSoHuuVo kqphTinPhieuVo = null;
            int j = 0;
            int k = stt.length;
            Collection lstSH = new ArrayList<KQPHTinPhieuCTSoHuuVo>();
            while (j < k) {
                kqphTinPhieuVo = new KQPHTinPhieuCTSoHuuVo();
                kqphTinPhieuVo.setStt(stt[j]);
                kqphTinPhieuVo.setThanh_vien_dt(thanh_vien_dt[j]);
                kqphTinPhieuVo.setTen_chu_so_huu(ten_chu_so_huu[j]);
                kqphTinPhieuVo.setSo_tk_tt(so_tk_tt[j]);
                kqphTinPhieuVo.setKl_trung_thau(kl_trung_thau[j]);
                kqphTinPhieuVo.setLs_trung_thau(StringUtil.convertNumberToString(ls_trung_thau[j], "VND"));
                kqphTinPhieuVo.setTien_tt_mua(tien_tt_mua[j]);
                lstSH.add(kqphTinPhieuVo);
                j++;
            }
            vo.setListCTSHTinPhieu(lstSH);
            KQPHTinPhieuDelegate delegate = new KQPHTinPhieuDelegate(conn);
            HttpSession session = request.getSession();
            String nUserID = session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString();
            XuLyPhanChiTietSoHuu(request, vo);
            long idAdd = delegate.update(vo);

            UserHistoryVO userHisVO = new UserHistoryVO();
            userHisVO.setNguoi_tdoi(new Long(nUserID));
            userHisVO.setNoi_dung_thaydoi("Update TTDT" + idAdd);
            userHisVO.setNsd_id(idAdd);
            delegate.insertHistoryUser(userHisVO);
            f.reset(mapping, request);

            if (idAdd != 0) {
                errMess = "kqphathanh.update.succ";
            } else {
                errMess = "kqphathanh.update.error";
            }
            saveMessage(request, new TPCPException(errMess));
        } catch (Exception ex) {
            throw ex;
        } finally {
            close(conn);
        }
        return mapping.findForward(SUCCESS);
    }

    //Lấy thông tin từ file excel

    public String GetGuidDMKyHan(String sKyHan, Connection conn) throws Exception {
        String Guid = "";
        DMKyHanDelegate dmKyHanDelegate = new DMKyHanDelegate(conn);
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("KY_HAN", sKyHan);
        DMKyHanVO voDMKyHan = dmKyHanDelegate.getDMKyHanObject(map);
        Guid = voDMKyHan.getGuid();
        return Guid;
    }

    private KQPHTinPhieuVo getDataFromFile(byte[] byteData, String type) throws Exception {
        Connection conn = null;
        conn = getConnection();
        KQPHTinPhieuVo vo = new KQPHTinPhieuVo();
        KQPHTinPhieuCTSoHuuVo voCTSoHuu = null;
        KQPHTinPhieuCTTPCPVo voCTTPCP = null;
        Collection listCTSHTinPhieu = new ArrayList();
        DMTraiChuDelegate delegate = new DMTraiChuDelegate(conn);
        Collection listCTTPCP = new ArrayList();
        QLyKQPhatHanhCTiet_TpcpVO ctiet_tpcpVO = new QLyKQPhatHanhCTiet_TpcpVO();
        QLyKQPhatHanhCTiet_SoHuuVO ctiet_sohuuVO = null;

        Collection lstCTietPhatHanh_tpcp = new ArrayList();
        Workbook workbook = null;
        String cellVal = "";
        Cell cell = null;
        try {
            vo = new KQPHTinPhieuVo();
            try {
                if (type.equals("xlsx")) {
                    workbook = new XSSFWorkbook(new ByteArrayInputStream(byteData));
                } else {
                    workbook = new HSSFWorkbook(new ByteArrayInputStream(byteData));
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                throw new TPCPException().createException("TPCP-0004");
            }
            String ma_tpcp = "";
            Sheet sheet = workbook.getSheetAt(0);
            System.out.print(sheet);
            //1.Lay thong tin chi bang TP_KQPH_TIN_PHIEU
            vo.setMa_tpcp(sheet.getRow(15).getCell(2).getStringCellValue());
            //    cellVal = String.valueOf(sheet.getRow(15).getCell(5).getStringCellValue());


            //       String ky_hancheck=cellVal;
            //       String Ky_Han = GetGuidDMKyHan(ky_hancheck, conn);
            //          vo.setKy_han(Ky_Han);
            //vo.setNgay_ph(sheet.getRow(15).getCell(7).getStringCellValue());
            String sMa_tin_phieu = (String)sheet.getRow(17).getCell(7).getStringCellValue();
            vo.setNgay_dao_han(sheet.getRow(15).getCell(9).getStringCellValue());
            vo.setNgay_ph(sheet.getRow(15).getCell(7).getStringCellValue());
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date ngay_ph_mili = sdf.parse(vo.getNgay_ph());
            long time_mili = ngay_ph_mili.getTime() - 86400000;
            Date ngay_to_chuc_ph = new Date(time_mili);
            vo.setNgay_to_chuc_ph(sdf.format(ngay_to_chuc_ph));

            String sLoai_tien = sheet.getRow(17).getCell(19).getStringCellValue();
            String[] str = sLoai_tien.split(":");
            vo.setLoai_tien(str[1].trim().toUpperCase());

            //2.Lay thong tin chi bang TP_KQ_PH_CTIET_TPCP
            voCTTPCP = new KQPHTinPhieuCTTPCPVo();
            voCTTPCP.setMa_tpcp(sheet.getRow(15).getCell(2).getStringCellValue());
            //
            if (!voCTTPCP.getMa_tpcp().equals(sMa_tin_phieu) || "".equals(voCTTPCP.getMa_tpcp())) {
                return null;
            }

            //          voCTTPCP.setKy_han(String.valueOf(new BigDecimal(sheet.getRow(15).getCell(5).getNumericCellValue())));
            //  voCTTPCP.setKy_han(Ky_Han);
            voCTTPCP.setNgay_ph(sheet.getRow(15).getCell(7).getStringCellValue());
            voCTTPCP.setNgay_dao_han(sheet.getRow(15).getCell(9).getStringCellValue());
            DataFormatter formatter = new DataFormatter();
            voCTTPCP.setLs_binh_quan(formatter.formatCellValue(sheet.getRow(15).getCell(14)));
            voCTTPCP.setLs_binh_quan(StringUtil.convertNumberToString(voCTTPCP.getLs_binh_quan(), "VND"));
            voCTTPCP.setKlph(StringUtil.convertNumberToString(String.valueOf(new BigDecimal(sheet.getRow(15).getCell(15).getNumericCellValue())), "VND"));
            voCTTPCP.setKlph_them(StringUtil.convertNumberToString(String.valueOf(new BigDecimal(sheet.getRow(15).getCell(18).getNumericCellValue())), "VND"));
            voCTTPCP.setTong_klph(StringUtil.convertNumberToString(String.valueOf(new BigDecimal(sheet.getRow(15).getCell(22).getNumericCellValue())), "VND"));

            lstCTietPhatHanh_tpcp.add(voCTTPCP);

            //3.Lay thong tin chi bang TP_KQ_PH_CTIET_SO_HUU
            int i = 20;
            cellVal = "";
            while (!"Tổng cộng".equals(cellVal)) {
                voCTSoHuu = new KQPHTinPhieuCTSoHuuVo();
                voCTSoHuu.setStt(String.valueOf(new BigDecimal(sheet.getRow(i).getCell(2).getNumericCellValue())));
                voCTSoHuu.setThanh_vien_dt(sheet.getRow(i).getCell(4).getStringCellValue());
                voCTSoHuu.setTen_chu_so_huu(sheet.getRow(i).getCell(6).getStringCellValue());
                voCTSoHuu.setSo_tk_tt(sheet.getRow(i).getCell(12).getStringCellValue());
                voCTSoHuu.setKl_trung_thau(StringUtil.convertNumberToString(String.valueOf(new BigDecimal(sheet.getRow(i).getCell(16).getNumericCellValue())), "VND"));
                Cell cell2 = sheet.getRow(i).getCell(22);
                if (cell2.getCellType() == HSSFCell.CELL_TYPE_NUMERIC || cell2.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
                    voCTSoHuu.setLs_trung_thau(StringUtil.convertNumberToString(String.valueOf(cell2.getNumericCellValue()), "VND"));
                } else {
                    voCTSoHuu.setLs_trung_thau(StringUtil.convertNumberToString(cell2.getStringCellValue(), "VND"));
                }

                voCTSoHuu.setTien_tt_mua(StringUtil.convertNumberToString(String.valueOf(new BigDecimal(sheet.getRow(i).getCell(24).getNumericCellValue())), "VND"));
                Map<String, Object> map = new HashMap();
                map.put("TEN_DVI_SO_HUU", voCTSoHuu.getTen_chu_so_huu());
                DMTraiChuVO traiChuVo = null;
                traiChuVo = delegate.getDMTraiChuVO(map);
                if (traiChuVo != null) {
                    voCTSoHuu.setMa_chu_so_huu(traiChuVo.getMa_chu_so_huu());
                }
                listCTSHTinPhieu.add(voCTSoHuu);
                i++;
                cell = sheet.getRow(i).getCell(2);
                if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC || cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
                    cellVal = String.valueOf(cell.getNumericCellValue());
                } else {
                    cellVal = cell.getStringCellValue();
                }
            }
            String TongKhoiLuong = String.valueOf(new BigDecimal(sheet.getRow(i).getCell(16).getNumericCellValue()));
            String TongTienTT = String.valueOf(new BigDecimal(sheet.getRow(i).getCell(23).getNumericCellValue()));
            vo.setTong_kl_trung_thau(StringUtil.convertNumberToString(TongKhoiLuong, "VND"));
            vo.setTong_tien_ban(StringUtil.convertNumberToString(TongTienTT, "VND"));
            vo.setLstCTPH_TPCP(lstCTietPhatHanh_tpcp);
            vo.setListCTSHTinPhieu(listCTSHTinPhieu);
        } catch (Exception ex) {
            ex.printStackTrace();
            if (cell != null) {
                throw TPCPException.createException("TPCP-0001", "Dữ liệu không đúng định dạng. Dòng " + (cell.getRowIndex() + 1));
            } else
                throw TPCPException.createException("TPCP-0001", "Lỗi đọc dữ liệu");
            //throw ex;
        } finally {
            close(conn);
        }
        return vo;
    }

    public void XuLyPhanChiTietSoHuu(HttpServletRequest request, KQPHTinPhieuVo vo) throws Exception {


        Collection lstKQPH_CTiet_SoHuu = new ArrayList();
        KQPHTinPhieuCTSoHuuVo voSoHuu = new KQPHTinPhieuCTSoHuuVo();

        //Add ban le chi tiet
        String[] stt = request.getParameterValues("stt");
        String[] sThanh_vien_dau_thau = request.getParameterValues("thanh_vien_dt");
        String[] sTen_chu_so_huu = request.getParameterValues("ten_chu_so_huu");
        String[] sMa_chu_so_huu = request.getParameterValues("ma_chu_so_huu");
        String[] sSo_tk_tt = request.getParameterValues("so_tk_tt");
        String[] sKl_trung_thau = request.getParameterValues("kl_trung_thau");
        String[] sLs_trung_thau = request.getParameterValues("ls_trung_thau");
        String[] sTien_tt_mua = request.getParameterValues("tien_tt_mua");
        String Ma_tpcp = "";
        if (vo.getMa_tpcp() != null) {
            Ma_tpcp = vo.getMa_tpcp();
        }
        if (sThanh_vien_dau_thau.length > 0) {
            for (int i = 0; i < sThanh_vien_dau_thau.length; i++) {
                KQPHTinPhieuCTSoHuuVo voChiTiet = new KQPHTinPhieuCTSoHuuVo();
                String thanh_vien_dau_thau = "";
                if (!sThanh_vien_dau_thau[i].toString().trim().equals("")) {
                    thanh_vien_dau_thau = sThanh_vien_dau_thau[i].toString().trim();
                }
                String ten_nguoi_so_huu = "";
                if (!sTen_chu_so_huu[i].toString().trim().equals("")) {
                    ten_nguoi_so_huu = sTen_chu_so_huu[i].toString().trim();
                }
                String ma_nguoi_so_huu = "";
                if (!sMa_chu_so_huu[i].toString().trim().equals("")) {
                    ma_nguoi_so_huu = sMa_chu_so_huu[i].toString().trim();
                }
                String so_tk_tt = "";
                if (!sSo_tk_tt[i].toString().trim().equals("")) {
                    so_tk_tt = sSo_tk_tt[i].toString().trim();
                }
                String kl_trung_thau = "";
                if (!sKl_trung_thau[i].toString().trim().equals("")) {
                    kl_trung_thau = sKl_trung_thau[i].toString().trim();
                }
                String ls_trung_thau = "";
                if (!sLs_trung_thau[i].toString().trim().equals("")) {
                    ls_trung_thau = sLs_trung_thau[i].toString().trim();
                }
                String tien_tt_mua = "";
                if (!sTien_tt_mua[i].toString().trim().equals("")) {
                    tien_tt_mua = sTien_tt_mua[i].toString().trim();
                }
                voChiTiet.setStt(stt[i]);
                voChiTiet.setMa_tpcp(Ma_tpcp);
                voChiTiet.setThanh_vien_dt(thanh_vien_dau_thau);
                voChiTiet.setTen_chu_so_huu(ten_nguoi_so_huu);
                voChiTiet.setMa_chu_so_huu(ma_nguoi_so_huu);
                voChiTiet.setSo_tk_tt(so_tk_tt);
                voChiTiet.setKl_trung_thau(kl_trung_thau);
                voChiTiet.setLs_trung_thau(ls_trung_thau);
                voChiTiet.setTien_tt_mua(tien_tt_mua);
                lstKQPH_CTiet_SoHuu.add(voChiTiet);
            }
            vo.setListCTSHTinPhieu(lstKQPH_CTiet_SoHuu);
        }
    }
    //End add ban le chi tiet


    //manhvv

    public ActionForward trinhAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Connection conn = null;
        String errMess = "";
        try {
            conn = getConnection();
            KQPHTinPhieuForm f = (KQPHTinPhieuForm)form;
            KQPHTinPhieuVo vo = new KQPHTinPhieuVo();

            KQPHTinPhieuDelegate kqphTinPhieuDelegate = new KQPHTinPhieuDelegate(conn);
            HttpSession session = request.getSession();
            String nUserID = session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString();
            BeanUtils.copyProperties(vo, f);

            long idAdd = kqphTinPhieuDelegate.update(vo);
            //insert history
            UserHistoryVO userHisVO = new UserHistoryVO();
            userHisVO.setNguoi_tdoi(new Long(nUserID));
            userHisVO.setNoi_dung_thaydoi("Trinh KQPHTinPhieu" + idAdd);
            userHisVO.setNsd_id(idAdd);
            kqphTinPhieuDelegate.insertHistoryUser(userHisVO);
            f.reset(mapping, request);
            if (idAdd != 0) {
                errMess = "kqphtinphieu.update.succ";
            } else
                errMess = "kqphtinphieu.update.error";
            saveMessage(request, new TPCPException(errMess));
        } catch (Exception e) {
            throw e;
        } finally {
            close(conn);
        }
        return mapping.findForward(SUCCESS);
    }

    public ActionForward pheDuyetAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Connection conn = null;
        String errMess = "";
        try {
            conn = getConnection();
            KQPHTinPhieuForm f = (KQPHTinPhieuForm)form;
            KQPHTinPhieuVo kqphTinPhieuVo = new KQPHTinPhieuVo();
            BeanUtils.copyProperties(kqphTinPhieuVo, f);
            KQPHTinPhieuDelegate kqphTinPhieuDelegate = new KQPHTinPhieuDelegate(conn);

            long idAdd = kqphTinPhieuDelegate.update(kqphTinPhieuVo);


            String trang_thai = f.getTrang_thai();

            HttpSession session = request.getSession();
            String nUserID = session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString();
            UserHistoryVO userHisVO = new UserHistoryVO();
            userHisVO.setNguoi_tdoi(new Long(nUserID));
            userHisVO.setNoi_dung_thaydoi("Phe Duyet KQPHTinPhieu" + idAdd);
            userHisVO.setNsd_id(idAdd);
            kqphTinPhieuDelegate.insertHistoryUser(userHisVO);
            f.reset(mapping, request);

            if (idAdd != 0) {
                if (trang_thai.equals("03")) {
                    errMess = "kqphtinphieu.tuchoi.succ";
                } else {
                    errMess = "kqphtinphieu.pheduyet.succ";
                }
            } else
                errMess = "kqphtinphieu.pheduyet.error";
            saveMessage(request, new TPCPException(errMess));
        } catch (Exception e) {
            throw e;
        } finally {
            close(conn);
        }
        return mapping.findForward(SUCCESS);
    }
}
