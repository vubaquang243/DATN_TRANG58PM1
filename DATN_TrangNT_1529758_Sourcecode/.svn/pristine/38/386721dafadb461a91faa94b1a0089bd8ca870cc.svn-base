package com.seatech.tp.kqduthau.action;

import com.seatech.framework.AppConstants;
import com.seatech.framework.common.jsp.PagingBean;
import com.seatech.framework.exception.TPCPException;
import com.seatech.framework.strustx.AppAction;
import com.seatech.framework.utils.StringUtil;
import com.seatech.tp.dmkyhan.action.DMKyHanDelegate;
import com.seatech.tp.dmkyhan.vo.DMKyHanVO;
import com.seatech.tp.dmtraichu.action.DMTraiChuDelegate;
import com.seatech.tp.dmtraichu.vo.DMTraiChuVO;
import com.seatech.tp.kqduthau.form.QLyKQDuThauCTietForm;
import com.seatech.tp.kqduthau.form.QLyKQDuThauForm;
import com.seatech.tp.kqduthau.vo.QLyKQDuThauCTietVO;
import com.seatech.tp.kqduthau.vo.QLyKQDuThauVO;
import com.seatech.tp.ttindthau.action.QLyTTinDauThauDelegate;
import com.seatech.tp.ttindthau.vo.ThongTinDauThauVO;
import com.seatech.tp.user.UserHistoryVO;

import java.io.ByteArrayInputStream;

import java.math.BigDecimal;

import java.sql.Connection;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.time.FastDateFormat;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

public class QLyKQDuThauAction extends AppAction {
    private static String SUCCESS = "success";
    private static String FAILURE = "failure";

    protected ActionForward executeAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Connection conn = null;
        try {
            conn = getConnection(request);
            QLyTTinDauThauDelegate delegate = new QLyTTinDauThauDelegate(conn);
            List listDot_DT = new ArrayList();
            listDot_DT = (List)delegate.getTTDTObject_DotDuThau();
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

    public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Connection conn = null;
        try {
            QLyKQDuThauForm f = (QLyKQDuThauForm)form;
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
            QLyKQDuThauForm f = (QLyKQDuThauForm)form;
            int phantrang = (AppConstants.APP_NUMBER_ROW_ON_PAGE);
            // khai bao bien phan trang.
            String page = f.getPageNumber();
            if (page == null)
                page = "1";
            Integer currentPage = new Integer(page);
            Integer numberRowOnPage = phantrang;
            Integer totalCount[] = new Integer[1];
            QLyKQDuThauVO vo = new QLyKQDuThauVO();
            BeanUtils.copyProperties(vo, f);
            QLyKQDuThauDelegate delegate = new QLyKQDuThauDelegate(conn);
            //get list KQDT
            List listKQDT = null;
            listKQDT = (List)delegate.getListKQDTPaging(vo, currentPage, numberRowOnPage, totalCount);
            Iterator ito = listKQDT.iterator();
            Collection resultKQDT = new ArrayList();
            while (ito.hasNext()) {
                vo = (QLyKQDuThauVO)ito.next();
                if (vo.getKl_goi_thau() != null) {
                    String khoiLuong = vo.getKl_goi_thau();
                    vo.setKl_goi_thau(StringUtil.convertNumberToString(khoiLuong, "VND"));
                }
             
                resultKQDT.add(vo);
            }
            PagingBean pagingBean = new PagingBean();
            pagingBean.setCurrentPage(currentPage);
            pagingBean.setNumberOfRow((totalCount[0] == null) ? 0 : totalCount[0].intValue());
            pagingBean.setRowOnPage(numberRowOnPage);
            request.setAttribute("PAGE_KEY", pagingBean);
            request.setAttribute("listKQDT", resultKQDT);
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
            QLyKQDuThauForm f = (QLyKQDuThauForm)form;
            f.reset(mapping, request);
            return executeAction(mapping, form, request, response);
        } catch (Exception e) {
            throw e;
        } finally {
            close(conn);
        }
    }

    public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Connection conn = null;
        try {
            conn = getConnection();
            String guid = request.getParameter("longid").trim();
            //check xem TPCP tÃ¡Â»â€œn tÃ¡ÂºÂ¡i?
            QLyKQDuThauDelegate delegate = new QLyKQDuThauDelegate(conn);
            QLyKQDuThauVO voDuThau = delegate.getQLKQDuThauObject(guid);
            if (voDuThau == null) {
                saveMessage(request, new TPCPException("KQPhathanh.list.norecord"));
                return mapping.findForward(FAILURE);
            }
            if (voDuThau.getKl_goi_thau() != null) {
                String Kl_goi_thau = voDuThau.getKl_goi_thau();
                voDuThau.setKl_goi_thau(StringUtil.convertNumberToString(Kl_goi_thau, "VND"));
            }
            if (voDuThau.getKl_goi_thau_them() != null) {
                String Kl_goi_thau_them = voDuThau.getKl_goi_thau_them();
                voDuThau.setKl_goi_thau_them(StringUtil.convertNumberToString(Kl_goi_thau_them, "VND"));
            }
            if (voDuThau.getLai_du_thau_thap_nhat() != null) {
                String Lai_du_thau_thap_nhat = voDuThau.getLai_du_thau_thap_nhat();
                voDuThau.setLai_du_thau_thap_nhat(StringUtil.convertNumberToString(Lai_du_thau_thap_nhat, "VND"));
            }

            if (voDuThau.getLai_du_thau_cao_nhat() != null) {
                String Lai_du_thau_cao_nhat = voDuThau.getLai_du_thau_cao_nhat();
                voDuThau.setLai_du_thau_cao_nhat(StringUtil.convertNumberToString(Lai_du_thau_cao_nhat, "VND"));
            }
            if (voDuThau.getTong_kldk_ko_ctls_bs() != null) {
                String Tong_kldk_ko_ctls_bs = voDuThau.getTong_kldk_ko_ctls_bs();
                voDuThau.setTong_kldk_ko_ctls_bs(StringUtil.convertNumberToString(Tong_kldk_ko_ctls_bs, "VND"));
            }
            if (voDuThau.getTong_kldk_ctls_bs() != null) {
                String Tong_kldk_ctls_bs = voDuThau.getTong_kldk_ctls_bs();
                voDuThau.setTong_kldk_ctls_bs(StringUtil.convertNumberToString(Tong_kldk_ctls_bs, "VND"));
            }
            if (voDuThau.getTong_kldk_ko_ctls() != null) {
                String Tong_kldk_ko_ctls = voDuThau.getTong_kldk_ko_ctls();
                voDuThau.setTong_kldk_ko_ctls(StringUtil.convertNumberToString(Tong_kldk_ko_ctls, "VND"));
            }
            if (voDuThau.getTong_kldk_ctls() != null) {
                String Tong_kldk_ctls = voDuThau.getTong_kldk_ctls();
                voDuThau.setTong_kldk_ctls(StringUtil.convertNumberToString(Tong_kldk_ctls, "VND"));
            }
            QLyKQDuThauForm f = (QLyKQDuThauForm)form;
            BeanUtils.copyProperties(f, voDuThau);

            //Chi tiet - DuThau
            Collection listKQDTChiTiet = null;
            listKQDTChiTiet = delegate.getListKQDTChiTiet_BuoiSang(guid);
            Collection lstlistKQDTChiTietForm = new ArrayList();
            Iterator itoDuThau = listKQDTChiTiet.iterator();
            QLyKQDuThauCTietVO ctiet_DuThauVO = null;
            QLyKQDuThauCTietForm ctiet_DuThauForm = null;
            int dem = 0;
            while (itoDuThau.hasNext()) {
                ctiet_DuThauForm = new QLyKQDuThauCTietForm();
                dem++;
                ctiet_DuThauVO = (QLyKQDuThauCTietVO)itoDuThau.next();
                ctiet_DuThauVO.setStt(dem + "");
                if (ctiet_DuThauVO.getKl_dk_khong_ctls() != null) {
                    String Kl_dk_khong_ctls = ctiet_DuThauVO.getKl_dk_khong_ctls();
                    ctiet_DuThauVO.setKl_dk_khong_ctls(StringUtil.convertNumberToString(Kl_dk_khong_ctls, "VND"));
                }
                if (ctiet_DuThauVO.getKl_cong_don() != null) {
                    String Kl_cong_don = ctiet_DuThauVO.getKl_cong_don();
                    ctiet_DuThauVO.setKl_cong_don(StringUtil.convertNumberToString(Kl_cong_don, "VND"));
                }
                if (ctiet_DuThauVO.getLai_suat() != null) {
                    String Lai_suat = ctiet_DuThauVO.getLai_suat();
                    ctiet_DuThauVO.setLai_suat(StringUtil.convertNumberToString(Lai_suat, "VND"));
                }
                if (ctiet_DuThauVO.getKl_dtct() != null) {
                    String Kl_dtct = ctiet_DuThauVO.getKl_dtct();
                    ctiet_DuThauVO.setKl_dtct(StringUtil.convertNumberToString(Kl_dtct, "VND"));
                }
                if (ctiet_DuThauVO.getKl_cong_don_ctls() != null) {
                    String Kl_cong_don_ctls = ctiet_DuThauVO.getKl_cong_don_ctls();
                    ctiet_DuThauVO.setKl_cong_don_ctls(StringUtil.convertNumberToString(Kl_cong_don_ctls, "VND"));
                }
                if (ctiet_DuThauVO.getKl_dk_ctls() != null) {
                    String Kl_dk_khong_ctls = ctiet_DuThauVO.getKl_dk_khong_ctls();
                    ctiet_DuThauVO.setKl_dk_khong_ctls(StringUtil.convertNumberToString(Kl_dk_khong_ctls, "VND"));
                }
                BeanUtils.copyProperties(ctiet_DuThauForm, ctiet_DuThauVO);
                lstlistKQDTChiTietForm.add(ctiet_DuThauForm);
            }
            f.setLstKQDT_CTiet(lstlistKQDTChiTietForm);
            //End
            //Get chi tiet them
            Collection lstCTietduthauForm_Them = new ArrayList();
            Collection lstCTiet_duthauVO_Them = null; // goi DAO lay dl tu bang chi tiet theo GUID
            lstCTiet_duthauVO_Them = delegate.getListKQDTChiTiet_Them(String.valueOf(guid));
            Iterator ito_duthau_Them = lstCTiet_duthauVO_Them.iterator();
            QLyKQDuThauCTietVO ctiet_duthauVO_Them = null;
            QLyKQDuThauCTietForm ctiet_duthauForm_Them = null;
            int dem_duthau_Them = 0;
            while (ito_duthau_Them.hasNext()) {
                ctiet_duthauForm_Them = new QLyKQDuThauCTietForm();
                dem_duthau_Them++;
                ctiet_duthauVO_Them = (QLyKQDuThauCTietVO)ito_duthau_Them.next();
                ctiet_duthauVO_Them.setStt_them(dem_duthau_Them + "");
                if (ctiet_duthauVO_Them.getTen_nha_dau_tu() != null) {
                    ctiet_duthauVO_Them.setTen_nha_dau_tu_them(ctiet_duthauVO_Them.getTen_nha_dau_tu());
                }
                if (ctiet_duthauVO_Them.getMa_nha_dau_tu() != null) {
                    ctiet_duthauVO_Them.setMa_nha_dau_tu_them(ctiet_duthauVO_Them.getMa_nha_dau_tu());
                }
                if (ctiet_duthauVO_Them.getKl_dk_khong_ctls() != null) {
                    String Kl_dk_khong_ctls = ctiet_duthauVO_Them.getKl_dk_khong_ctls();
                    ctiet_duthauVO_Them.setKl_dk_khong_ctls_them(StringUtil.convertNumberToString(Kl_dk_khong_ctls, "VND"));
                }
                if (ctiet_duthauVO_Them.getLai_suat() != null) {
                    String Lai_suat = ctiet_duthauVO_Them.getLai_suat();
                    ctiet_duthauVO_Them.setLai_suat_them(StringUtil.convertNumberToString(Lai_suat, "VND"));
                }
                if (ctiet_duthauVO_Them.getKl_dtct() != null) {
                    String Kl_dtct = ctiet_duthauVO_Them.getKl_dtct();
                    ctiet_duthauVO_Them.setKl_dtct_them(StringUtil.convertNumberToString(Kl_dtct, "VND"));
                }
                if (ctiet_duthauVO_Them.getKl_cong_don_ctls() != null) {
                    String Kl_cong_don_ctls = ctiet_duthauVO_Them.getKl_cong_don_ctls();
                    ctiet_duthauVO_Them.setKl_cong_don_ctls_them(StringUtil.convertNumberToString(Kl_cong_don_ctls, "VND"));
                }
                if (ctiet_duthauVO_Them.getKl_cong_don() != null) {
                    String Kl_cong_don = ctiet_duthauVO_Them.getKl_cong_don();
                    ctiet_duthauVO_Them.setKl_cong_don_them(StringUtil.convertNumberToString(Kl_cong_don, "VND"));
                }
                BeanUtils.copyProperties(ctiet_duthauForm_Them, ctiet_duthauVO_Them);
                lstCTietduthauForm_Them.add(ctiet_duthauForm_Them);
            }
            f.setLstKQDT_CTiet_Them(lstCTietduthauForm_Them);
            //end
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
        try {
            conn = getConnection(request);
            HttpSession session = request.getSession();
            String nUserID = session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString();
            QLyKQDuThauForm f = (QLyKQDuThauForm)form;
            QLyKQDuThauVO vo = new QLyKQDuThauVO();
            BeanUtils.copyProperties(vo, f);
            XuLyPhanChiTiet(request, vo);
            XuLyPhanChiTiet_Them(request, vo);
            QLyKQDuThauDelegate delegate = new QLyKQDuThauDelegate(conn);
            if (vo.getKl_goi_thau() != null) {
                vo.setKl_goi_thau(StringUtil.convertNumberToString(vo.getKl_goi_thau(), "VND").replace(",", "."));
            }
            vo.setNguoi_sua_cuoi(nUserID);
            vo.setNgay_sua_cuoi(getDate());
            long idAdd = delegate.update(vo);
            //insert history
            UserHistoryVO userHisVO = new UserHistoryVO();
            userHisVO.setNguoi_tdoi(new Long(nUserID));
            userHisVO.setNoi_dung_thaydoi("Cập nhật KQDT" + idAdd);
            userHisVO.setNsd_id(idAdd);
            delegate.insertHistoryUser(userHisVO);
            f.reset(mapping, request);
            saveMessage(request, new TPCPException("kqduthau.update.succ"));
        } catch (Exception e) {
            throw e;
        } finally {
            close(conn);
        }
        return mapping.findForward(SUCCESS);
    }

    private DMTraiChuVO searchObject(List dmucDviSoHuu, String ten_tv) throws Exception {
        Comparator<DMTraiChuVO> comparator = new Comparator<DMTraiChuVO>() {
            public int compare(DMTraiChuVO emp1, DMTraiChuVO emp2) {
                return emp1.getTen_dvi_so_huu().toLowerCase().trim().compareTo(emp2.getTen_dvi_so_huu().toLowerCase().trim());
            }
        };
        Collections.sort(dmucDviSoHuu, comparator);
        DMTraiChuVO keyEmp = new DMTraiChuVO();
        keyEmp.setTen_dvi_so_huu(ten_tv);
        try {
            int index = Collections.binarySearch(dmucDviSoHuu, keyEmp, comparator);
            if (index >= 0) {
                keyEmp = (DMTraiChuVO)dmucDviSoHuu.get(index);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return keyEmp;
    }

    public ActionForward viewUploadAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Connection conn = null;
        try {
            conn = getConnection();
            QLyKQDuThauForm f = (QLyKQDuThauForm)form;
            QLyTTinDauThauDelegate delegateTTDT = new QLyTTinDauThauDelegate(conn);
            FormFile file = f.getFileUpload();
            if (file.getFileSize() == 0) {
                throw new TPCPException().createException("TPCP-0004");
            }
            //only allow excel to upload
            String endType = file.getFileName().substring(file.getFileName().lastIndexOf(".") + 1, file.getFileName().length());
            //            if (!"xlsx".equals(endType) || !"xls".equals(endType)) {
            //                throw new TPCPException().createException("TPCP-0004");
            //            }
            //file size cant larger than 10kb
            if (file.getFileSize() > 102400) { //10kb
                throw new TPCPException().createException("TPCP-0005");
            }
            QLyKQDuThauVO vo = getDataFromFile(file.getFileData(), endType, conn);
            HashMap<String, Object> map_DDT = new HashMap<String, Object>();
            map_DDT.put("DOT_DAU_THAU", vo.getDot_dt().trim());
            ThongTinDauThauVO voDDT = delegateTTDT.getTTDTObject_check(map_DDT);
            if (voDDT == null) {
                throw new TPCPException().createException("TPCP-0006", vo.getDot_dt());
            }
            if (!voDDT.getMa_tpcp().trim().equals(vo.getMa_tpcp().trim())) {
                throw new TPCPException().createException("TPCP-0018", vo.getMa_tpcp(), vo.getDot_dt());
            }
            vo.setLoai_tien(voDDT.getLoai_tien());
            BeanUtils.copyProperties(f, vo);
            HttpSession session = request.getSession();
            session.setAttribute("lstKQDuThau", f.getLstKQDT_CTiet());
            session.setAttribute("FileData", file.getFileData());
            this.saveToken(request);
        } catch (Exception e) {
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

    public ActionForward addExc(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Connection conn = null;
        try {
            conn = getConnection();
            QLyKQDuThauForm f = (QLyKQDuThauForm)form;
            QLyKQDuThauVO vo = new QLyKQDuThauVO();
            BeanUtils.copyProperties(vo, f);
            HttpSession session = request.getSession();
            XuLyPhanChiTiet(request, vo);
            XuLyPhanChiTiet_Them(request, vo);
            vo.setTrang_thai("00");
            vo.setLoai_tien("VND");
            String nUserID = session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString();
            vo.setNgay_tao(getDate());
            vo.setNguoi_tao(nUserID);
            QLyKQDuThauDelegate delegate = new QLyKQDuThauDelegate(conn);
            long idAdd = delegate.update(vo);
            //insert vao bang temp
            if (idAdd > 0) {
                String ngayTimeStamp = StringUtil.convertDateToString(Calendar.getInstance(), "DDMMYYYYHH24MISS");
                byte[] byteData = (byte[])session.getAttribute("FileData");
                session.removeAttribute("FileData");
                delegate.insertTP_KQDT_FILE(byteData, idAdd, ngayTimeStamp, new Long(nUserID));
            }
            //insert history
            UserHistoryVO userHisVO = new UserHistoryVO();
            userHisVO.setNguoi_tdoi(new Long(nUserID));
            userHisVO.setNoi_dung_thaydoi("Tai thong tin ket qua du thau " + idAdd);
            userHisVO.setNsd_id(idAdd);
            delegate.insertHistoryUser(userHisVO);
            f.reset(mapping, request);
            saveMessage(request, new TPCPException("kqduthau.add.succ"));
        } catch (Exception e) {
            throw e;
        } finally {
            close(conn);
        }
        return mapping.findForward(SUCCESS);
    }

    private QLyKQDuThauVO getDataFromFile(byte[] byteData, String type, Connection conn) throws Exception {
        QLyKQDuThauVO vo = null;
        QLyKQDuThauCTietVO ctietVO = null;
        Collection lstCTietDuTau = new ArrayList();
        Collection lstCTietDuTau_Them = new ArrayList();
        Workbook workbook = null;
        String cellVal = "";
        Cell cell = null;
        Calendar cal = null;
        try {
            vo = new QLyKQDuThauVO();
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

            Sheet sheet = workbook.getSheetAt(0);
            //set common information
            //dot dau thau
            cellVal = sheet.getRow(7).getCell(5).getStringCellValue();
            String[] strdot_dau_thau= cellVal.split("/");
            String strdt="";
            if(strdot_dau_thau[0].length() == 1){
              strdt="00"+ cellVal ;
            }else if(strdot_dau_thau[0].length() == 2){
              strdt="0"+ cellVal ;
            }else{
              strdt= cellVal;
            }
            vo.setDot_dt(strdt);
            //ma TPCP
            cellVal = sheet.getRow(9).getCell(5).getStringCellValue();
            vo.setMa_tpcp(cellVal);
            //ten TPCP
            cellVal = sheet.getRow(8).getCell(5).getStringCellValue();
            vo.setTen_tpcp(cellVal);
            //ngay TCPH      
            cell = sheet.getRow(10).getCell(5);
            if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING || cell.getCellType() == XSSFCell.CELL_TYPE_STRING) {
                cellVal = cell.getStringCellValue();
            } else {
                cal = Calendar.getInstance();
                cal.setTime(cell.getDateCellValue());
                cellVal = StringUtil.convertDateToString(cal, "DD/MM/YYYY") ;
            }            
            vo.setNgay_to_chuc_ph(cellVal);
            //ngay PH
           // cellVal = sheet.getRow(11).getCell(5).getStringCellValue();
            cell = sheet.getRow(11).getCell(5);
            if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING || cell.getCellType() == XSSFCell.CELL_TYPE_STRING) {
                cellVal = cell.getStringCellValue();
            } else {
                cal = Calendar.getInstance();
                cal.setTime(cell.getDateCellValue());
                cellVal = StringUtil.convertDateToString(cal, "DD/MM/YYYY") ;
            }   
            vo.setNgay_ph(cellVal);
            //ngay Thanh toán
            //cellVal = sheet.getRow(7).getCell(16).getStringCellValue();
            cell = sheet.getRow(7).getCell(16);
            if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING || cell.getCellType() == XSSFCell.CELL_TYPE_STRING) {
                cellVal = cell.getStringCellValue();
            } else {
                cal = Calendar.getInstance();
                cal.setTime(cell.getDateCellValue());
                cellVal = StringUtil.convertDateToString(cal, "DD/MM/YYYY") ;
            }   
            vo.setNgay_tt_tien_mua(cellVal);
            //Ngày đáo hạn
            //cellVal = sheet.getRow(8).getCell(16).getStringCellValue();
            cell = sheet.getRow(8).getCell(16);
            if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING || cell.getCellType() == XSSFCell.CELL_TYPE_STRING) {
                cellVal = cell.getStringCellValue();
            } else {
                cal = Calendar.getInstance();
                cal.setTime(cell.getDateCellValue());
                cellVal = StringUtil.convertDateToString(cal, "DD/MM/YYYY") ;
            }   
            vo.setNgay_dao_han(cellVal);
            //Kỳ hạn:
            cellVal = sheet.getRow(9).getCell(16).getStringCellValue();
           // String Ky_Han = GetGuidDMKyHan(cellVal, conn);
            vo.setKy_han(cellVal);
            //Khối lượng gói thầu
            //            cellVal = sheet.getRow(10).getCell(16).getStringCellValue();
            //            vo.setKl_goi_thau(cellVal);
            
            cell = sheet.getRow(10).getCell(16);
            if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC || cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
                cellVal = String.valueOf(new BigDecimal(cell.getNumericCellValue()));
            } else {
                cellVal = cell.getStringCellValue().replaceAll("[VND,USD]", "");
            }
            vo.setKl_goi_thau(StringUtil.convertNumberToString(cellVal, "VND"));
            //Gói thầu thêm
            //            cellVal = sheet.getRow(11).getCell(16).getStringCellValue();
            //            vo.setKl_goi_thau_them(cellVal);
            cell = sheet.getRow(11).getCell(16);
            if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC || cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
                cellVal = String.valueOf(new BigDecimal(cell.getNumericCellValue()));
            } else {
                cellVal = cell.getStringCellValue().replaceAll("[VND,USD]", "");
            }
            vo.setKl_goi_thau_them(StringUtil.convertNumberToString(cellVal, "VND"));
            //Loai tiền
            //vo.setLoai_tien("VND");
            int i = 17;
            cellVal = "";
            cellVal = String.valueOf(sheet.getRow(i).getCell(9).getStringCellValue());
            if (cellVal.equals(""))
                cellVal = "0";
            double minLS = new Double(cellVal);
            double maxLS = new Double(cellVal);
            double ls = 0.0;
            List dmucDviSoHuu = (List)new DMTraiChuDelegate(conn).getDMTraiChu();
            ArrayList<Double> lstLS = new ArrayList<Double>();
            while (!"Tổng".equals(cellVal)) {
                ctietVO = new QLyKQDuThauCTietVO();
                ctietVO.setStt(String.valueOf(new BigDecimal(sheet.getRow(i).getCell(2).getNumericCellValue())));
                ctietVO.setTen_nha_dau_tu(sheet.getRow(i).getCell(3).getStringCellValue());
                DMTraiChuVO ma_chu_so_huu = searchObject(dmucDviSoHuu, ctietVO.getTen_nha_dau_tu());
                ctietVO.setMa_nha_dau_tu(ma_chu_so_huu.getMa_chu_so_huu());
                ctietVO.setMa_so(sheet.getRow(i).getCell(4).getStringCellValue());
                cellVal = String.valueOf(new BigDecimal(sheet.getRow(i).getCell(7).getNumericCellValue()));
                ctietVO.setKl_dk_khong_ctls(StringUtil.convertNumberToString(cellVal, "VND"));
                cellVal = String.valueOf(sheet.getRow(i).getCell(9).getStringCellValue());
                ctietVO.setLai_suat(StringUtil.convertNumberToString(cellVal, "VND"));
                if (cellVal.equals(""))
                    cellVal = "0";
                ls = new Double(cellVal);
                if (ls != 0)
                    lstLS.add(ls);
                cellVal = String.valueOf(new BigDecimal(sheet.getRow(i).getCell(12).getNumericCellValue()));
                ctietVO.setKl_dtct(StringUtil.convertNumberToString(cellVal, "VND"));
                cellVal = String.valueOf(new BigDecimal(sheet.getRow(i).getCell(13).getNumericCellValue()));
                ctietVO.setKl_cong_don_ctls(StringUtil.convertNumberToString(cellVal, "VND"));
                cellVal = String.valueOf(new BigDecimal(sheet.getRow(i).getCell(18).getNumericCellValue()));
                ctietVO.setKl_cong_don(StringUtil.convertNumberToString(cellVal, "VND"));
                ctietVO.setPhien_dt("S");
                lstCTietDuTau.add(ctietVO);
                i++;
                cell = sheet.getRow(i).getCell(2);
                if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC || cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
                    cellVal = String.valueOf(cell.getNumericCellValue());
                } else {
                    cellVal = cell.getStringCellValue();
                }
            }

            vo.setLstKQDT_CTiet(lstCTietDuTau);
            String tong_kldk_ko_ctls_bs = String.valueOf(new BigDecimal(sheet.getRow(i).getCell(7).getNumericCellValue()));
            String tong_kldk_ctls_bs = String.valueOf(new BigDecimal(sheet.getRow(i).getCell(12).getNumericCellValue()));
            vo.setTong_kldk_ko_ctls_bs(StringUtil.convertNumberToString(tong_kldk_ko_ctls_bs, "VND"));
            vo.setTong_kldk_ctls_bs(StringUtil.convertNumberToString(tong_kldk_ctls_bs, "VND"));
            String phiendauthau = sheet.getRow(i + 1).getCell(3).getStringCellValue();
            if (!phiendauthau.equals("")) {
                //Neu co phien dau thau them
                int j = i + 2;
                String cellVal_them = "";
                while (!"Tổng".equals(cellVal_them)) {
                    ctietVO = new QLyKQDuThauCTietVO();
                    ctietVO.setStt_them(String.valueOf(new BigDecimal(sheet.getRow(j).getCell(2).getNumericCellValue())));
                    ctietVO.setTen_nha_dau_tu_them(sheet.getRow(j).getCell(3).getStringCellValue());
                    DMTraiChuVO ma_chu_so_huu = searchObject(dmucDviSoHuu, ctietVO.getTen_nha_dau_tu_them());
                    ctietVO.setMa_nha_dau_tu_them(ma_chu_so_huu.getMa_chu_so_huu());
                    ctietVO.setMa_so_them(sheet.getRow(j).getCell(4).getStringCellValue());
                    cellVal_them = String.valueOf(new BigDecimal(sheet.getRow(j).getCell(7).getNumericCellValue()));
                    ctietVO.setKl_dk_khong_ctls_them(StringUtil.convertNumberToString(cellVal_them, "VND"));
                    cellVal_them = String.valueOf(sheet.getRow(j).getCell(9).getStringCellValue());
                    ctietVO.setLai_suat_them(StringUtil.convertNumberToString(cellVal_them, "VND"));
                    if (cellVal_them.equals(""))
                        cellVal_them = "0";
                    ls = new Double(cellVal_them);
                    if (ls != 0)
                        lstLS.add(ls);
                    cellVal_them = String.valueOf(new BigDecimal(sheet.getRow(j).getCell(12).getNumericCellValue()));
                    ctietVO.setKl_dtct_them(StringUtil.convertNumberToString(cellVal_them, "VND"));
                    cellVal_them = String.valueOf(new BigDecimal(sheet.getRow(j).getCell(13).getNumericCellValue()));
                    ctietVO.setKl_cong_don_ctls_them(StringUtil.convertNumberToString(cellVal_them, "VND"));
                    cellVal_them = String.valueOf(new BigDecimal(sheet.getRow(j).getCell(18).getNumericCellValue()));
                    ctietVO.setKl_cong_don_them(StringUtil.convertNumberToString(cellVal_them, "VND"));
                    ctietVO.setPhien_dt_them("T");
                    lstCTietDuTau_Them.add(ctietVO);
                    j++;
                    cell = sheet.getRow(j).getCell(2);
                    if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC || cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
                        cellVal_them = String.valueOf(cell.getNumericCellValue());
                    } else {
                        cellVal_them = cell.getStringCellValue();
                    }
                }
                vo.setLstKQDT_CTiet_Them(lstCTietDuTau_Them);
                String tong_kldk_ko_ctls_them = String.valueOf(new BigDecimal(sheet.getRow(j).getCell(7).getNumericCellValue()));
                String tong_kldk_ctls_them = String.valueOf(new BigDecimal(sheet.getRow(j).getCell(12).getNumericCellValue()));
                vo.setTong_kldk_ko_ctls_them(StringUtil.convertNumberToString(tong_kldk_ko_ctls_them, "VND"));
                vo.setTong_kldk_ctls_them(StringUtil.convertNumberToString(tong_kldk_ctls_them, "VND"));
                String tong_kldk_ko_ctls = String.valueOf(new BigDecimal(sheet.getRow(j + 1).getCell(7).getNumericCellValue()));
                String tong_kldk_ctls = String.valueOf(new BigDecimal(sheet.getRow(j + 1).getCell(12).getNumericCellValue()));
                vo.setTong_kldk_ko_ctls(StringUtil.convertNumberToString(tong_kldk_ko_ctls, "VND"));
                vo.setTong_kldk_ctls(StringUtil.convertNumberToString(tong_kldk_ctls, "VND"));
            } else {
                String tong_kldk_ko_ctls = String.valueOf(new BigDecimal(sheet.getRow(i + 1).getCell(7).getNumericCellValue()));
                String tong_kldk_ctls = String.valueOf(new BigDecimal(sheet.getRow(i + 1).getCell(12).getNumericCellValue()));
                vo.setTong_kldk_ko_ctls(StringUtil.convertNumberToString(tong_kldk_ko_ctls, "VND"));
                vo.setTong_kldk_ctls(StringUtil.convertNumberToString(tong_kldk_ctls, "VND"));
            }
            minLS = Collections.min(lstLS);
            maxLS = Collections.max(lstLS);
            vo.setLai_du_thau_cao_nhat(StringUtil.convertNumberToString(String.valueOf(maxLS), "VND"));
            vo.setLai_du_thau_thap_nhat(StringUtil.convertNumberToString(String.valueOf(minLS), "VND"));
        } catch (Exception ex) {
            ex.printStackTrace();
            if(cell!=null){
              throw TPCPException.createException("TPCP-0001","Dữ liệu không đúng định dạng. Dòng " + (cell.getRowIndex() + 1));
            }else throw TPCPException.createException("TPCP-0001","Lỗi đọc dữ liệu");
            
            //throw ex;
        }
        return vo;
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
            QLyKQDuThauDelegate delegate = new QLyKQDuThauDelegate(conn);
            QLyKQDuThauVO vo = delegate.getQLKQDuThauObject(guid);
            if (vo == null) {
                saveMessage(request, new TPCPException("qlkqduthau.list.notfount"));
                return mapping.findForward(FAILURE);
            } else {
                QLyKQDuThauForm f = (QLyKQDuThauForm)form;
                long idAdd = delegate.deleteKQDT(vo, guid);
                //insert history
                UserHistoryVO userHisVO = new UserHistoryVO();
                userHisVO.setNguoi_tdoi(new Long(nUserID));
                userHisVO.setNoi_dung_thaydoi("Xoa ma kq du thau " + idAdd);
                userHisVO.setNsd_id(idAdd);
                delegate.insertHistoryUser(userHisVO);
                f.reset(mapping, request);
                if (idAdd != 0) {
                    errMess = "kqduthau.delete.succ";
                } else
                    errMess = "kqduthau.delete.error";
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

    public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Connection conn = null;
        try {
            conn = getConnection();
            String guid = request.getParameter("longid").trim();
            QLyKQDuThauDelegate delegate = new QLyKQDuThauDelegate(conn);
            QLyKQDuThauVO voQLyKQDuThau = delegate.getQLKQDuThauObject(guid);
            if (voQLyKQDuThau == null) {
                saveMessage(request, new TPCPException("KQDuthau.list.notfount"));
                return mapping.findForward(FAILURE);
            }
            QLyKQDuThauForm f = (QLyKQDuThauForm)form;
            if (voQLyKQDuThau.getKl_goi_thau() != null) {
                String Kl_goi_thau = voQLyKQDuThau.getKl_goi_thau();
                voQLyKQDuThau.setKl_goi_thau(StringUtil.convertNumberToString(Kl_goi_thau, "VND"));
            }
            if (voQLyKQDuThau.getKl_goi_thau_them() != null) {
                String Kl_goi_thau_them = voQLyKQDuThau.getKl_goi_thau_them();
                voQLyKQDuThau.setKl_goi_thau_them(StringUtil.convertNumberToString(Kl_goi_thau_them, "VND"));
            }
            if (voQLyKQDuThau.getTong_kldk_ko_ctls_bs() != null) {
                String Tong_kldk_ko_ctls_bs = voQLyKQDuThau.getTong_kldk_ko_ctls_bs();
                voQLyKQDuThau.setTong_kldk_ko_ctls_bs(StringUtil.convertNumberToString(Tong_kldk_ko_ctls_bs, "VND"));
            }
            if (voQLyKQDuThau.getTong_kldk_ctls_bs() != null) {
                String Tong_kldk_ctls_bs = voQLyKQDuThau.getTong_kldk_ctls_bs();
                voQLyKQDuThau.setTong_kldk_ctls_bs(StringUtil.convertNumberToString(Tong_kldk_ctls_bs, "VND"));
            }
            if (voQLyKQDuThau.getTong_kldk_ko_ctls() != null) {
                String Tong_kldk_ko_ctls = voQLyKQDuThau.getTong_kldk_ko_ctls();
                voQLyKQDuThau.setTong_kldk_ko_ctls(StringUtil.convertNumberToString(Tong_kldk_ko_ctls, "VND"));
            }
            if (voQLyKQDuThau.getTong_kldk_ctls() != null) {
                String Tong_kldk_ctls = voQLyKQDuThau.getTong_kldk_ctls();
                voQLyKQDuThau.setTong_kldk_ctls(StringUtil.convertNumberToString(Tong_kldk_ctls, "VND"));
            }
            if (voQLyKQDuThau.getLai_du_thau_cao_nhat() != null) {
                voQLyKQDuThau.setLai_du_thau_cao_nhat(StringUtil.convertNumberToString(voQLyKQDuThau.getLai_du_thau_cao_nhat(), "VND"));
            }
            if (voQLyKQDuThau.getLai_du_thau_thap_nhat() != null) {
                voQLyKQDuThau.setLai_du_thau_thap_nhat(StringUtil.convertNumberToString(voQLyKQDuThau.getLai_du_thau_thap_nhat(), "VND"));
            }
            BeanUtils.copyProperties(f, voQLyKQDuThau);
            //End

            //Get chi tiet buoi sang
            Collection lstCTietduthauForm = new ArrayList();
            Collection lstCTiet_duthauVO = null; // goi DAO lay dl tu bang chi tiet theo GUID
            lstCTiet_duthauVO = delegate.getListKQDTChiTiet_BuoiSang(String.valueOf(guid));
            Iterator ito_duthau = lstCTiet_duthauVO.iterator();
            QLyKQDuThauCTietVO ctiet_duthauVO = null;
            QLyKQDuThauCTietForm ctiet_duthauForm = null;
            int dem_duthau = 0;
            while (ito_duthau.hasNext()) {
                ctiet_duthauForm = new QLyKQDuThauCTietForm();
                dem_duthau++;
                ctiet_duthauVO = (QLyKQDuThauCTietVO)ito_duthau.next();
                ctiet_duthauVO.setStt(dem_duthau + "");
                if (ctiet_duthauVO.getKl_dk_khong_ctls() != null) {
                    String Kl_dk_khong_ctls = ctiet_duthauVO.getKl_dk_khong_ctls();
                    ctiet_duthauVO.setKl_dk_khong_ctls(StringUtil.convertNumberToString(Kl_dk_khong_ctls, "VND"));
                }
                if (ctiet_duthauVO.getLai_suat() != null) {
                    String Lai_suat = ctiet_duthauVO.getLai_suat();
                    ctiet_duthauVO.setLai_suat(StringUtil.convertNumberToString(Lai_suat, "VND"));
                }
                if (ctiet_duthauVO.getKl_dtct() != null) {
                    String Kl_dtct = ctiet_duthauVO.getKl_dtct();
                    ctiet_duthauVO.setKl_dtct(StringUtil.convertNumberToString(Kl_dtct, "VND"));
                }
                if (ctiet_duthauVO.getKl_cong_don_ctls() != null) {
                    String Kl_cong_don_ctls = ctiet_duthauVO.getKl_cong_don_ctls();
                    ctiet_duthauVO.setKl_cong_don_ctls(StringUtil.convertNumberToString(Kl_cong_don_ctls, "VND"));
                }
                if (ctiet_duthauVO.getKl_cong_don() != null) {
                    String Kl_cong_don = ctiet_duthauVO.getKl_cong_don();
                    ctiet_duthauVO.setKl_cong_don(StringUtil.convertNumberToString(Kl_cong_don, "VND"));
                }
                BeanUtils.copyProperties(ctiet_duthauForm, ctiet_duthauVO);
                lstCTietduthauForm.add(ctiet_duthauForm);
            }
            f.setLstKQDT_CTiet(lstCTietduthauForm);
            //end

            //Get chi tiet them
            Collection lstCTietduthauForm_Them = new ArrayList();
            Collection lstCTiet_duthauVO_Them = null; // goi DAO lay dl tu bang chi tiet theo GUID
            lstCTiet_duthauVO_Them = delegate.getListKQDTChiTiet_Them(String.valueOf(guid));
            Iterator ito_duthau_Them = lstCTiet_duthauVO_Them.iterator();
            QLyKQDuThauCTietVO ctiet_duthauVO_Them = null;
            QLyKQDuThauCTietForm ctiet_duthauForm_Them = null;
            int dem_duthau_Them = 0;
            while (ito_duthau_Them.hasNext()) {
                ctiet_duthauForm_Them = new QLyKQDuThauCTietForm();
                dem_duthau_Them++;
                ctiet_duthauVO_Them = (QLyKQDuThauCTietVO)ito_duthau_Them.next();
                ctiet_duthauVO_Them.setStt(dem_duthau_Them + "");
                if (ctiet_duthauVO_Them.getKl_dk_khong_ctls() != null) {
                    String Kl_dk_khong_ctls = ctiet_duthauVO_Them.getKl_dk_khong_ctls();
                    ctiet_duthauVO_Them.setKl_dk_khong_ctls(StringUtil.convertNumberToString(Kl_dk_khong_ctls, "VND"));
                }
                if (ctiet_duthauVO_Them.getLai_suat() != null) {
                    String Lai_suat = ctiet_duthauVO_Them.getLai_suat();
                    ctiet_duthauVO_Them.setLai_suat(StringUtil.convertNumberToString(Lai_suat, "VND"));
                }
                if (ctiet_duthauVO_Them.getKl_dtct() != null) {
                    String Kl_dtct = ctiet_duthauVO_Them.getKl_dtct();
                    ctiet_duthauVO_Them.setKl_dtct(StringUtil.convertNumberToString(Kl_dtct, "VND"));
                }
                if (ctiet_duthauVO_Them.getKl_cong_don_ctls() != null) {
                    String Kl_cong_don_ctls = ctiet_duthauVO_Them.getKl_cong_don_ctls();
                    ctiet_duthauVO_Them.setKl_cong_don_ctls(StringUtil.convertNumberToString(Kl_cong_don_ctls, "VND"));
                }
                if (ctiet_duthauVO_Them.getKl_cong_don() != null) {
                    String Kl_cong_don = ctiet_duthauVO_Them.getKl_cong_don();
                    ctiet_duthauVO_Them.setKl_cong_don(StringUtil.convertNumberToString(Kl_cong_don, "VND"));
                }
                BeanUtils.copyProperties(ctiet_duthauForm_Them, ctiet_duthauVO_Them);
                lstCTietduthauForm_Them.add(ctiet_duthauForm_Them);
            }
            f.setLstKQDT_CTiet_Them(lstCTietduthauForm_Them);
            //end

        } catch (Exception e) {
            conn.rollback();
            throw e;
        } finally {
            close(conn);
        }
        return mapping.findForward(SUCCESS);
    }

    public void XuLyPhanChiTiet(HttpServletRequest request, QLyKQDuThauVO voQLyKQDuThau) throws Exception {
        {
            Collection lstKQDTCTiet = new ArrayList();
            //Add ban le chi tiet
            String[] sTen_nha_dau_tu = request.getParameterValues("ten_nha_dau_tu");
            String[] sMa_nha_dau_tu = request.getParameterValues("ma_nha_dau_tu");
            String[] sKl_dk_khong_ctls = request.getParameterValues("kl_dk_khong_ctls");
            String[] sLai_suat = request.getParameterValues("lai_suat");
            String[] sKl_dtct = request.getParameterValues("kl_dtct");
            String[] sKl_cong_don_ctls = request.getParameterValues("kl_cong_don_ctls");
            String[] sKl_cong_don = request.getParameterValues("kl_cong_don");
            if (sTen_nha_dau_tu.length > 0 && sTen_nha_dau_tu != null) {
                for (int i = 0; i < sTen_nha_dau_tu.length; i++) {
                    QLyKQDuThauCTietVO voChiTiet = new QLyKQDuThauCTietVO();
                    String ten_nha_dau_tu = "";
                    if (!sTen_nha_dau_tu[i].toString().trim().equals("")) {
                        ten_nha_dau_tu = sTen_nha_dau_tu[i].toString().trim();
                    }
                    String ma_nha_dau_tu = "";
                    if (!sMa_nha_dau_tu[i].toString().trim().equals("")) {
                        ma_nha_dau_tu = sMa_nha_dau_tu[i].toString().trim();
                    }
                    String kl_dk_khong_ctls = "0";
                    if (!sKl_dk_khong_ctls[i].toString().trim().equals("")) {
                        kl_dk_khong_ctls = sKl_dk_khong_ctls[i].toString().trim();
                    }
                    String lai_suat = "";
                    if (!sLai_suat[i].toString().trim().equals("")) {
                        lai_suat = sLai_suat[i].toString().trim();
                    }
                    String kl_dtct = "0";
                    if (!sKl_dtct[i].toString().trim().equals("")) {
                        kl_dtct = sKl_dtct[i].toString().trim();
                    }
                    String kl_cong_don_ctls = "0";
                    if (!sKl_cong_don_ctls[i].toString().trim().equals("")) {
                        kl_cong_don_ctls = sKl_cong_don_ctls[i].toString().trim();
                    }
                    String kl_cong_don = "0";
                    if (!sKl_cong_don[i].toString().trim().equals("")) {
                        kl_cong_don = sKl_cong_don[i].toString().trim();
                    }
                    voChiTiet.setStt(String.valueOf(i + 1));
                    voChiTiet.setTen_nha_dau_tu(ten_nha_dau_tu);
                    voChiTiet.setMa_nha_dau_tu(ma_nha_dau_tu);
                    voChiTiet.setKl_dk_khong_ctls(StringUtil.convertNumberToString(kl_dk_khong_ctls, "VND").replaceAll(",", "."));
                    voChiTiet.setLai_suat(lai_suat);
                    voChiTiet.setKl_dtct(StringUtil.convertNumberToString(kl_dtct, "VND").replaceAll(",", "."));
                    voChiTiet.setKl_cong_don_ctls(StringUtil.convertNumberToString(kl_cong_don_ctls, "VND").replaceAll(",", "."));
                    voChiTiet.setKl_cong_don(StringUtil.convertNumberToString(kl_cong_don, "VND").replaceAll(",", "."));
                    voChiTiet.setPhien_dt("S");
                    lstKQDTCTiet.add(voChiTiet);
                }
                voQLyKQDuThau.setLstKQDT_CTiet(lstKQDTCTiet);
            }
        }
    }

    public void XuLyPhanChiTiet_Them(HttpServletRequest request, QLyKQDuThauVO voQLyKQDuThau) throws Exception {
        {
            Collection lstKQDTCTiet = new ArrayList();
            //Add ban le chi tiet
            String[] sTen_nha_dau_tu = request.getParameterValues("ten_nha_dau_tu_them");
            String[] sMa_nha_dau_tu = request.getParameterValues("ma_nha_dau_tu_them");
            String[] sKl_dk_khong_ctls = request.getParameterValues("kl_dk_khong_ctls_them");
            String[] sLai_suat = request.getParameterValues("lai_suat_them");
            String[] sKl_dtct = request.getParameterValues("kl_dtct_them");
            String[] sKl_cong_don_ctls = request.getParameterValues("kl_cong_don_ctls_them");
            String[] sKl_cong_don = request.getParameterValues("kl_cong_don_them");
            if (sTen_nha_dau_tu != null) {
                for (int i = 0; i < sTen_nha_dau_tu.length; i++) {
                    QLyKQDuThauCTietVO voChiTiet = new QLyKQDuThauCTietVO();
                    String ten_nha_dau_tu = "";
                    if (!sTen_nha_dau_tu[i].toString().trim().equals("")) {
                        ten_nha_dau_tu = sTen_nha_dau_tu[i].toString().trim();
                    }
                    String ma_nha_dau_tu = "";
                    if (!sMa_nha_dau_tu[i].toString().trim().equals("")) {
                        ma_nha_dau_tu = sMa_nha_dau_tu[i].toString().trim();
                    }
                    String kl_dk_khong_ctls = "0";
                    if (!sKl_dk_khong_ctls[i].toString().trim().equals("")) {
                        kl_dk_khong_ctls = sKl_dk_khong_ctls[i].toString().trim();
                    }
                    String lai_suat = "";
                    if (!sLai_suat[i].toString().trim().equals("")) {
                        lai_suat = sLai_suat[i].toString().trim();
                    }
                    String kl_dtct = "0";
                    if (!sKl_dtct[i].toString().trim().equals("")) {
                        kl_dtct = sKl_dtct[i].toString().trim();
                    }
                    String kl_cong_don_ctls = "0";
                    if (!sKl_cong_don_ctls[i].toString().trim().equals("")) {
                        kl_cong_don_ctls = sKl_cong_don_ctls[i].toString().trim();
                    }
                    String kl_cong_don = "0";
                    if (!sKl_cong_don[i].toString().trim().equals("")) {
                        kl_cong_don = sKl_cong_don[i].toString().trim();
                    }
                    voChiTiet.setStt(String.valueOf(i + 1));
                    voChiTiet.setTen_nha_dau_tu(ten_nha_dau_tu);
                    voChiTiet.setMa_nha_dau_tu(ma_nha_dau_tu);
                    voChiTiet.setKl_dk_khong_ctls(StringUtil.convertNumberToString(kl_dk_khong_ctls, "VND").replaceAll(",", "."));
                    voChiTiet.setLai_suat(lai_suat);
                    voChiTiet.setKl_dtct(StringUtil.convertNumberToString(kl_dtct, "VND").replaceAll(",", "."));
                    voChiTiet.setKl_cong_don_ctls(StringUtil.convertNumberToString(kl_cong_don_ctls, "VND").replaceAll(",", "."));
                    voChiTiet.setKl_cong_don(StringUtil.convertNumberToString(kl_cong_don, "VND").replaceAll(",", "."));
                    voChiTiet.setPhien_dt("T");
                    lstKQDTCTiet.add(voChiTiet);
                }
                voQLyKQDuThau.setLstKQDT_CTiet_Them(lstKQDTCTiet);
            }
        }
    }


    public ActionForward trinhAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Connection conn = null;
        String errMess = "";
        try {

            conn = getConnection(request);
            QLyKQDuThauForm f = (QLyKQDuThauForm)form;
            QLyKQDuThauVO vo = new QLyKQDuThauVO();
            HttpSession session = request.getSession();
            String nUserID = session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString();
            BeanUtils.copyProperties(vo, f);
            XuLyPhanChiTiet(request, vo);
            XuLyPhanChiTiet_Them(request, vo);
            if (vo.getKl_goi_thau() != null) {
                vo.setKl_goi_thau(StringUtil.convertNumberToString(vo.getKl_goi_thau(), "VND").replace(",", "."));
            }
            vo.setNguoi_sua_cuoi(nUserID);
            vo.setNgay_sua_cuoi(getDate());
            //BeanUtils.copyProperties(vo, f);
            vo.setTrang_thai("01");
            QLyKQDuThauDelegate delegate = new QLyKQDuThauDelegate(conn);
            long idAdd = delegate.update(vo);
            //insert history
            UserHistoryVO userHisVO = new UserHistoryVO();
            userHisVO.setNguoi_tdoi(new Long(nUserID));
            userHisVO.setNoi_dung_thaydoi("Trinh ket qua du thau" + idAdd);
            userHisVO.setNsd_id(idAdd);
            delegate.insertHistoryUser(userHisVO);
            f.reset(mapping, request);
            if (idAdd != 0) {
                errMess = "kqduthau.trinhkq.succ";
            } else
                errMess = "kqduthau.trinhkq.error";
            saveMessage(request, new TPCPException(errMess));
        } catch (Exception e) {
            conn.rollback();
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

            conn = getConnection(request);
            QLyKQDuThauForm f = (QLyKQDuThauForm)form;
            QLyKQDuThauVO vo = new QLyKQDuThauVO();
            HttpSession session = request.getSession();
            String nUserID = session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString();
            BeanUtils.copyProperties(vo, f);
            vo.setTrang_thai("02");
            QLyKQDuThauDelegate delegate = new QLyKQDuThauDelegate(conn);
            vo.setNgay_duyet(getDate());
            vo.setNguoi_duyet(nUserID);
            long idAdd = delegate.update(vo);
            //insert history
            UserHistoryVO userHisVO = new UserHistoryVO();
            userHisVO.setNguoi_tdoi(new Long(nUserID));
            userHisVO.setNoi_dung_thaydoi("Phe duyet ket qua du thau" + idAdd);
            userHisVO.setNsd_id(idAdd);
            delegate.insertHistoryUser(userHisVO);
            f.reset(mapping, request);
            if (idAdd != 0) {
                errMess = "kqduthau.pheduyet.succ";
            } else
                errMess = "kqduthau.pheduyet.error";
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
            QLyKQDuThauForm f = (QLyKQDuThauForm)form;
            QLyKQDuThauVO vo = new QLyKQDuThauVO();
            HttpSession session = request.getSession();
            String nUserID = session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString();
            BeanUtils.copyProperties(vo, f);
            vo.setTrang_thai("03");
            vo.setNgay_duyet(getDate());
            vo.setNguoi_duyet(nUserID);
            QLyKQDuThauDelegate delegate = new QLyKQDuThauDelegate(conn);
            long idAdd = delegate.update(vo);
            //insert history
            UserHistoryVO userHisVO = new UserHistoryVO();
            userHisVO.setNguoi_tdoi(new Long(nUserID));
            userHisVO.setNoi_dung_thaydoi("Tu choi ket qua du thau" + idAdd);
            userHisVO.setNsd_id(idAdd);
            delegate.insertHistoryUser(userHisVO);
            f.reset(mapping, request);
            if (idAdd != 0) {
                errMess = "kqduthau.tuchoi.succ";
            } else
                errMess = "kqduthau.tuchoi.error";
            saveMessage(request, new TPCPException(errMess));
        } catch (Exception e) {
            conn.rollback();
            throw e;
        } finally {
            close(conn);
        }
        return mapping.findForward(SUCCESS);
    }

    public String GetGuidDMKyHan(String sKyHan, Connection conn) throws Exception {
        String Guid = "";
        DMKyHanDelegate dmKyHanDelegate = new DMKyHanDelegate(conn);
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("KY_HAN", sKyHan);
        DMKyHanVO voDMKyHan = dmKyHanDelegate.getDMKyHanObject(map);
        if(voDMKyHan!=null){
        Guid = voDMKyHan.getGuid();
        }else Guid ="";
        return Guid;
    }
}
