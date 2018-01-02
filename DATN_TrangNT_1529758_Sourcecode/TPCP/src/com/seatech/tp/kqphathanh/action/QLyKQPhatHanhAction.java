package com.seatech.tp.kqphathanh.action;

import com.seatech.framework.AppConstants;
import com.seatech.framework.common.jsp.PagingBean;
import com.seatech.framework.exception.TPCPException;
import com.seatech.framework.strustx.AppAction;
import com.seatech.framework.utils.StringUtil;
import com.seatech.tp.dmkyhan.action.DMKyHanDelegate;
import com.seatech.tp.dmkyhan.vo.DMKyHanVO;

import com.seatech.tp.dmtraichu.action.DMTraiChuDelegate;
import com.seatech.tp.dmtraichu.vo.DMTraiChuVO;
import com.seatech.tp.kqphathanh.form.QLyKQPhatHanhCTiet_SoHuuForm;
import com.seatech.tp.kqphathanh.form.QLyKQPhatHanhCTiet_TpcpForm;
import com.seatech.tp.kqphathanh.form.QLyKQPhatHanhForm;
import com.seatech.tp.kqphathanh.vo.QLyKQPhatHanhCTiet_SoHuuVO;
import com.seatech.tp.kqphathanh.vo.QLyKQPhatHanhCTiet_TpcpVO;
import com.seatech.tp.kqphathanh.vo.QLyKQPhatHanhVO;

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

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import java.util.Map;

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

public class QLyKQPhatHanhAction extends AppAction {
    private static String SUCCESS = "success";
    private static String FAILURE = "failure";


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

    public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Connection conn = null;
        try {
            QLyKQPhatHanhForm f = (QLyKQPhatHanhForm)form;
            f.reset(mapping, request);
            return search(mapping, form, request, response);
        } catch (Exception e) {
            conn.rollback();
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
            QLyKQPhatHanhForm f = (QLyKQPhatHanhForm)form;
            int phantrang = (AppConstants.APP_NUMBER_ROW_ON_PAGE);
            // khai bao bien phan trang.
            String page = f.getPageNumber();
            if (page == null)
                page = "1";
            Integer currentPage = new Integer(page);
            Integer numberRowOnPage = phantrang;
            Integer totalCount[] = new Integer[1];
            QLyKQPhatHanhVO vo = new QLyKQPhatHanhVO();
            BeanUtils.copyProperties(vo, f);
            QLyKQPhatHanhDelegate delegate = new QLyKQPhatHanhDelegate(conn);
            List lstKQphathanh = null;
            lstKQphathanh = (List)delegate.getListKQPHPaging(vo, currentPage, numberRowOnPage, totalCount);
            Iterator ito = lstKQphathanh.iterator();
            Collection resultKQPhathanh = new ArrayList();
            
            while (ito.hasNext()) {
                vo = (QLyKQPhatHanhVO)ito.next();      
                if(vo.getLs_danh_nghia()!= null){                  
                  vo.setLs_danh_nghia(StringUtil.convertNumberToString(vo.getLs_danh_nghia(), "VND"));
                }
                if (vo.getTong_klph() != null) {
                    String khoiLuong = vo.getTong_klph();
                    vo.setTong_klph(StringUtil.convertNumberToString(khoiLuong, "VND"));
                }
              if (vo.getTong_kl_trung_thau() != null) {
                  String khoiLuong =vo.getTong_kl_trung_thau();
                  vo.setTong_kl_trung_thau(StringUtil.convertNumberToString(khoiLuong, "VND"));
              }
              resultKQPhathanh.add(vo);
            }
            PagingBean pagingBean = new PagingBean();
            pagingBean.setCurrentPage(currentPage);
            pagingBean.setNumberOfRow((totalCount[0] == null) ? 0 : totalCount[0].intValue());
            pagingBean.setRowOnPage(numberRowOnPage);
            request.setAttribute("PAGE_KEY", pagingBean);
            request.setAttribute("lstKQphathanh", resultKQPhathanh);
        } catch (Exception e) {
            conn.rollback();
            throw e;
        } finally {
            close(conn);
        }
        return mapping.findForward(SUCCESS);
    }

    public void XuLyPhanChiTietSoHuu(HttpServletRequest request, QLyKQPhatHanhVO vQLyKQPhatHanh) throws Exception {
        {
            Collection lstKQPH_CTiet_SoHuu = new ArrayList();
            //Add ban le chi tiet
            String[] sThanh_vien_dau_thau = request.getParameterValues("thanh_vien_dau_thau");
            String[] sTen_nguoi_so_huu = request.getParameterValues("ten_nguoi_so_huu");
            String[] sMa_nguoi_so_huu = request.getParameterValues("ma_nguoi_so_huu");
            String[] sSo_tk_tt = request.getParameterValues("so_tk_tt");
            String[] sKl_trung_thau = request.getParameterValues("kl_trung_thau");
            String[] sLs_trung_thau = request.getParameterValues("ls_trung_thau");
            String[] sTien_tt_mua = request.getParameterValues("tien_tt_mua");
            String Ma_tpcp = "";
            if (vQLyKQPhatHanh.getMa_tpcp() != null) {
                Ma_tpcp = vQLyKQPhatHanh.getMa_tpcp();
            }
            if (sThanh_vien_dau_thau.length > 0) {
                for (int i = 0; i < sThanh_vien_dau_thau.length; i++) {
                    QLyKQPhatHanhCTiet_SoHuuVO voChiTiet = new QLyKQPhatHanhCTiet_SoHuuVO();
                    String thanh_vien_dau_thau = "";
                    if (!sThanh_vien_dau_thau[i].toString().trim().equals("")) {
                        thanh_vien_dau_thau = sThanh_vien_dau_thau[i].toString().trim();
                    }
                    String ten_nguoi_so_huu = "";
                    if (!sTen_nguoi_so_huu[i].toString().trim().equals("")) {
                        ten_nguoi_so_huu = sTen_nguoi_so_huu[i].toString().trim();
                    }
                    String ma_nguoi_so_huu = "";
                    if (!sMa_nguoi_so_huu[i].toString().trim().equals("")) {
                        ma_nguoi_so_huu = sMa_nguoi_so_huu[i].toString().trim();
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
                    
                    Map<String,Object> mapSH = new HashMap();
                  //  mapSH.put("ten_nguoi_so_huu", )
                    
                    voChiTiet.setStt(String.valueOf(i + 1));
                    voChiTiet.setMa_tpcp(Ma_tpcp);
                    voChiTiet.setThanh_vien_dau_thau(thanh_vien_dau_thau);
                    voChiTiet.setTen_nguoi_so_huu(ten_nguoi_so_huu);
                    voChiTiet.setMa_nguoi_so_huu(ma_nguoi_so_huu);
                    voChiTiet.setSo_tk_tt(so_tk_tt);
                    voChiTiet.setKl_trung_thau(StringUtil.convertNumberToString(kl_trung_thau, "VND").replaceAll(",", "."));
                    voChiTiet.setLs_trung_thau(ls_trung_thau);
                    voChiTiet.setTien_tt_mua(StringUtil.convertNumberToString(tien_tt_mua, "VND").replaceAll(",", "."));
                    lstKQPH_CTiet_SoHuu.add(voChiTiet);
                }
                vQLyKQPhatHanh.setLstKQPH_CTiet_SoHuu(lstKQPH_CTiet_SoHuu);
            }
        }
        //End add ban le chi tiet
    }

    public ActionForward addExc(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        Connection conn = null;
        String errMess="";
        try {
            conn = getConnection();
            QLyKQPhatHanhForm f = (QLyKQPhatHanhForm)form;
            QLyKQPhatHanhVO vo = new QLyKQPhatHanhVO();
            BeanUtils.copyProperties(vo, f);
            HttpSession session = request.getSession();
            String nUserID = session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString();
            //vo.setTrang_thai("00");
            vo.setNguoi_tao(nUserID);
            String kyhan = (String)session.getAttribute("sKyhan_Them");
            session.removeAttribute("sKyhan_Them");
            String dot_dt = (String)session.getAttribute("sDot_dtThem");
            session.removeAttribute("sDot_dtThem");
            vo.setDot_dt(dot_dt);
            vo.setKy_han(kyhan);
            vo.setLstKQDT_CTiet_TPCP((Collection)session.getAttribute("lstKQDT_CTiet_TPCP"));
            session.removeAttribute("lstKQDT_CTiet_TPCP");
            XuLyPhanChiTietSoHuu(request, vo);
            QLyKQPhatHanhDelegate delegate = new QLyKQPhatHanhDelegate(conn);
            long idAdd = delegate.update(vo);
            //insert vao bang temp
            if (idAdd > 0) {
                String ngayTimeStamp = StringUtil.convertDateToString(Calendar.getInstance(), "DDMMYYYYHH24MISS");
                byte[] byteData = (byte[])session.getAttribute("FileData");
                session.removeAttribute("FileData");
                delegate.insertTP_KQPH_FILE(byteData, idAdd, ngayTimeStamp, new Long(nUserID));
            }
            //insert history
            UserHistoryVO userHisVO = new UserHistoryVO();
            userHisVO.setNguoi_tdoi(new Long(nUserID));
            userHisVO.setNoi_dung_thaydoi("Tai thong tin ket qua phat hanh " + idAdd);
            userHisVO.setNsd_id(idAdd);
            delegate.insertHistoryUser(userHisVO);
            if(idAdd > 0 && "01".equals(vo.getTrang_thai())){
              errMess = "kqphathanh.add.succ";
            }else {
              errMess = "kqphathanh.add.succ1";
            }
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
            QLyKQPhatHanhForm f = (QLyKQPhatHanhForm)form;
            f.reset(mapping, request);
            return executeAction(mapping, form, request, response);
        } catch (Exception e) {
            throw e;
        } finally {
            close(conn);
        }
    }

    public String GetGuidDMKyHan(String sKyHan, Connection conn, QLyKQPhatHanhCTiet_TpcpVO ctvo) throws Exception {
        String Guid = "";
        DMKyHanDelegate dmKyHanDelegate = new DMKyHanDelegate(conn);
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("KY_HAN", sKyHan);
        DMKyHanVO voDMKyHan = dmKyHanDelegate.getDMKyHanObject(map);
        Guid = voDMKyHan.getGuid();
        ctvo.setKy_han(voDMKyHan.getMo_ta());
        return Guid;
    }

    public ActionForward viewUploadAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Connection conn = null;
        try {
            conn = getConnection();
            QLyKQPhatHanhForm f = (QLyKQPhatHanhForm)form;

            QLyTTinDauThauDelegate delegateTTDT = new QLyTTinDauThauDelegate(conn);
            HashMap<String, Object> map_DDT = new HashMap<String, Object>();
            map_DDT.put("DOT_DAU_THAU", f.getDot_ph());
            ThongTinDauThauVO voDDT = delegateTTDT.getTTDTObject_check(map_DDT, "kqph");
            if (voDDT == null) {
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
            QLyKQPhatHanhVO vo = getDataFromFile(file.getFileData(), endType);
            
            HashMap<String, Object> map_dot_dt = new HashMap<String, Object>();
            QLyKQPhatHanhDelegate deleKQ= new QLyKQPhatHanhDelegate(conn);    
            if(voDDT.getDot_bo_sung()!=null && !"".equals(voDDT.getDot_bo_sung())){
              String dot_dt= voDDT.getDot_bo_sung();
              QLyKQPhatHanhCTiet_TpcpVO ctietVO=deleKQ.getKQPH_CTIET_TPCP(dot_dt);
              ctietVO.setLs_danh_nghia(StringUtil.convertNumberToString(ctietVO.getLs_danh_nghia(), "VND"));
              if(!ctietVO.getLs_danh_nghia().trim().equals(vo.getLs_danh_nghia().trim())){
                throw new TPCPException().createException("TPCP-0038");
             
              }
            }
            // check ngay_dao_han
            ArrayList tpcp_CT= (ArrayList)vo.getLstKQDT_CTiet_TPCP();
            QLyKQPhatHanhCTiet_TpcpVO ctietTPCPVO= (QLyKQPhatHanhCTiet_TpcpVO)tpcp_CT.get(0);
            if(!voDDT.getNgay_dao_han().equals(ctietTPCPVO.getNgay_dao_han())){
              throw new TPCPException().createException("TPCP-0040", ctietTPCPVO.getNgay_dao_han(),voDDT.getNgay_dao_han() );
            }
            if (!voDDT.getMa_tpcp().trim().equals(vo.getMa_tpcp().trim())) {
                throw new TPCPException().createException("TPCP-0018", vo.getMa_tpcp(), f.getDot_ph());
            }
           
          
            //check ngay phat hanh
            if (!voDDT.getNgay_ph().trim().equals(vo.getNgay_ph().trim())) {
                throw new TPCPException().createException("TPCP-0035", vo.getNgay_ph(),voDDT.getNgay_ph());
            }
            //Neu co dot dau thau thi lay luon thong tin cua dot dau thau da chon
            vo.setDot_dt(voDDT.getDot_dau_thau());
            vo.setNgay_dt(voDDT.getNgay_to_chuc_ph());
            vo.setNgay_ph(voDDT.getNgay_ph());
            //vo.setKy_han(voDDT.getKy_han()); hoi lai
            vo.setLoai_tien(voDDT.getLoai_tien());
            // vo.setMa_tpcp(voDDT.getMa_tpcp()); hoi lai
            vo.setNgay_tb_de_nghi_ph(voDDT.getNgay_tbao_goi_thau());
            vo.setSo_tb_de_nghi_ph(voDDT.getSo_tbao_goi_thau());
            //
            vo.setNguoi_tao(nUserID);
            BeanUtils.copyProperties(f, vo);
            session.setAttribute("sDot_dtThem", vo.getDot_dt());
            session.setAttribute("sKyhan_Them", vo.getKy_han());
            session.setAttribute("lstKQDT_CTiet_TPCP", f.getLstKQDT_CTiet_TPCP());
            session.setAttribute("lstKQPH_CTiet_SoHuu", f.getLstKQPH_CTiet_SoHuu());
            session.setAttribute("FileData", file.getFileData());

            DMKyHanDelegate dmKyHanDelegate = new DMKyHanDelegate(conn);
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("LOAI_TPCP", "TRAI_PHIEU");
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

    private DMTraiChuVO searchObject(List dmucDviSoHuu, String ma_tv) throws Exception {
        Comparator<DMTraiChuVO> comparator = new Comparator<DMTraiChuVO>() {
            public int compare(DMTraiChuVO emp1, DMTraiChuVO emp2) {
                return emp1.getTen_dvi_so_huu().toLowerCase().trim().compareTo(emp2.getTen_dvi_so_huu().toLowerCase().trim());
            }
        };
        Collections.sort(dmucDviSoHuu, comparator);
        DMTraiChuVO keyEmp = new DMTraiChuVO();
        keyEmp.setTen_dvi_so_huu(ma_tv);
        int index = Collections.binarySearch(dmucDviSoHuu, keyEmp, comparator);

        if (index >= 0) {
            keyEmp = (DMTraiChuVO)dmucDviSoHuu.get(index);
        }
        return keyEmp;
    }

    private QLyKQPhatHanhVO getDataFromFile(byte[] byteData, String type) throws Exception {
        Connection conn = null;
        conn = getConnection();
        QLyKQPhatHanhVO vo = new QLyKQPhatHanhVO();
        QLyKQPhatHanhCTiet_TpcpVO ctiet_tpcpVO = new QLyKQPhatHanhCTiet_TpcpVO();
        QLyKQPhatHanhCTiet_SoHuuVO ctiet_sohuuVO = null;
        Collection lstCTietPhatHanh_sohuu = new ArrayList();
        Collection lstCTietPhatHanh_tpcp = new ArrayList();
        Workbook workbook = null;
        String cellVal = "";
        Cell cell = null;
        try {
            vo = new QLyKQPhatHanhVO();
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
            //1.Lay thong tin chi bang TP_KQPH
            cell = sheet.getRow(15).getCell(2);
            cellVal =cell.getStringCellValue();
            vo.setMa_tpcp(cellVal!=null?cellVal.trim():"");
            ctiet_tpcpVO.setMa_tpcp(cellVal!=null?cellVal.trim():"");
            ma_tpcp = cellVal;
            cellVal = String.valueOf(new BigDecimal(sheet.getRow(15).getCell(5).getNumericCellValue()));
            String Ky_Han = GetGuidDMKyHan(cellVal, conn, ctiet_tpcpVO);
            vo.setKy_han(Ky_Han);
            //ctiet_tpcpVO.setKy_han(Ky_Han);
            //2.Lay thong tin chi bang TP_KQ_PH_CTIET_TPCP
            ctiet_tpcpVO.setStt("1");
            //MA_TPCP

            //KY_HAN
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            
            //NGAY_PH
            cell = sheet.getRow(15).getCell(7);
            cellVal = cell.getStringCellValue();
            Date date = formatter.parse(cellVal);// format to date
            String date_ph =formatter.format(date); // format to string
            ctiet_tpcpVO.setNgay_ph(date_ph);
            vo.setNgay_ph(date_ph);
            //NGAY_DAO_HAN
            cell = sheet.getRow(15).getCell(9);
            cellVal = cell.getStringCellValue();
            Date date2 = formatter.parse(cellVal);
            String date_dh =formatter.format(date2);
              
            ctiet_tpcpVO.setNgay_dao_han(date_dh);
            //LS_TRUNG_THAU
            cell = sheet.getRow(15).getCell(14);
            cellVal = cell.getStringCellValue();
            ctiet_tpcpVO.setLs_trung_thau(StringUtil.convertNumberToString(cellVal, "VND").replace(".", ""));
            //LS_DANH_NGHIA
            cell = sheet.getRow(15).getCell(15);
            cellVal = cell.getStringCellValue();
            ctiet_tpcpVO.setLs_danh_nghia(StringUtil.convertNumberToString(cellVal, "VND").replace(".", ""));
            vo.setLs_danh_nghia(StringUtil.convertNumberToString(cellVal, "VND"));

            //KLPH
            cell = sheet.getRow(15).getCell(17);
            cellVal = String.valueOf(new BigDecimal(cell.getNumericCellValue()));
            ctiet_tpcpVO.setKlph(StringUtil.convertNumberToString(cellVal, "VND"));
            //KLPH_THEM
            cell = sheet.getRow(15).getCell(20);
            cellVal = String.valueOf(new BigDecimal(cell.getNumericCellValue()));
            ctiet_tpcpVO.setKlph_them(StringUtil.convertNumberToString(cellVal, "VND"));
            vo.setTong_klph_them(StringUtil.convertNumberToString(cellVal, "VND"));
            //TONG_KLPH
            cell = sheet.getRow(15).getCell(25);
            cellVal = String.valueOf(new BigDecimal(cell.getNumericCellValue()));
            ctiet_tpcpVO.setTong_klph(StringUtil.convertNumberToString(cellVal, "VND"));
            vo.setTong_klph(StringUtil.convertNumberToString(cellVal, "VND"));

            //add vao list_Tpcp
            lstCTietPhatHanh_tpcp.add(ctiet_tpcpVO);
            vo.setLstKQDT_CTiet_TPCP(lstCTietPhatHanh_tpcp);
            //3.Lay thong tin chi bang TP_KQ_PH_CTIET_SO_HUU
            int i = 20;
            cellVal = "";            
            List dmucDviSoHuu = (List)new DMTraiChuDelegate(conn).getDMTraiChu();
            while (!"Tổng cộng".equals(cellVal)) {
                ctiet_sohuuVO = new QLyKQPhatHanhCTiet_SoHuuVO();
                ctiet_sohuuVO.setStt(String.valueOf(new BigDecimal(sheet.getRow(i).getCell(2).getNumericCellValue())));
                cellVal = sheet.getRow(i).getCell(4).getStringCellValue();
                ctiet_sohuuVO.setThanh_vien_dau_thau(cellVal);                
                cellVal = sheet.getRow(i).getCell(6).getStringCellValue();
                ctiet_sohuuVO.setTen_nguoi_so_huu(cellVal);
                DMTraiChuVO ma_chu_so_huu = searchObject(dmucDviSoHuu, cellVal);
                ctiet_sohuuVO.setMa_nguoi_so_huu(ma_chu_so_huu.getMa_chu_so_huu());
                cellVal = sheet.getRow(i).getCell(11).getStringCellValue();
                ctiet_sohuuVO.setSo_tk_tt(cellVal);
                cellVal = String.valueOf(new BigDecimal(sheet.getRow(i).getCell(16).getNumericCellValue()));
                ctiet_sohuuVO.setKl_trung_thau(StringUtil.convertNumberToString(cellVal, "VND"));
                cellVal = sheet.getRow(i).getCell(22).getStringCellValue();
                ctiet_sohuuVO.setLs_trung_thau(StringUtil.convertNumberToString(cellVal, "VND").replace(".", ""));
                cellVal = String.valueOf(new BigDecimal(sheet.getRow(i).getCell(24).getNumericCellValue()));
                ctiet_sohuuVO.setTien_tt_mua(StringUtil.convertNumberToString(cellVal, "VND"));
                lstCTietPhatHanh_sohuu.add(ctiet_sohuuVO);
                i++;
                cell = sheet.getRow(i).getCell(2);
                if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC || cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
                    cellVal = String.valueOf(cell.getNumericCellValue());
                } else {
                    cellVal = cell.getStringCellValue();
                }
            }
            String TongKhoiLuong = String.valueOf(new BigDecimal(sheet.getRow(i).getCell(16).getNumericCellValue()));
            String TongTienTT = String.valueOf(new BigDecimal(sheet.getRow(i).getCell(24).getNumericCellValue()));
            vo.setTong_kl_trung_thau(StringUtil.convertNumberToString(TongKhoiLuong, "VND"));
            vo.setTong_tien_ban(StringUtil.convertNumberToString(TongTienTT, "VND"));
            vo.setLstKQPH_CTiet_SoHuu(lstCTietPhatHanh_sohuu);
        } catch (Exception ex) {
            ex.printStackTrace();
            if(cell!=null){
              throw TPCPException.createException("TPCP-0001","Dữ liệu không đúng định dạng. Dòng " + (cell.getRowIndex() + 1));
            }else throw TPCPException.createException("TPCP-0001","Lỗi đọc dữ liệu");            
            //throw ex;
        } finally {
            close(conn);
        }
        return vo;
    }

    public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Connection conn = null;
        try {
            conn = getConnection();
            String guid = request.getParameter("longid").trim();
            //check xem TPCP tÃƒÂ¡Ã‚Â»Ã¢â‚¬Å“n tÃƒÂ¡Ã‚ÂºÃ‚Â¡i?
            QLyKQPhatHanhDelegate delegate = new QLyKQPhatHanhDelegate(conn);
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("GUID", guid);
            QLyKQPhatHanhVO voQLyKQPhatHanh = delegate.getQLyKQPhatHanhObjectHienThi(map);
            if (voQLyKQPhatHanh == null) {
                saveMessage(request, new TPCPException("KQPhathanh.list.notfount"));
                return mapping.findForward(FAILURE);
            }
            QLyKQPhatHanhForm f = (QLyKQPhatHanhForm)form;
            if (voQLyKQPhatHanh.getTong_klph() != null) {
                String tong_klph = voQLyKQPhatHanh.getTong_klph();
                voQLyKQPhatHanh.setTong_klph(StringUtil.convertNumberToString(tong_klph, "VND"));
            }
            if (voQLyKQPhatHanh.getTong_klph_them() != null) {
                String tong_klph_them = voQLyKQPhatHanh.getTong_klph_them();
                voQLyKQPhatHanh.setTong_klph_them(StringUtil.convertNumberToString(tong_klph_them, "VND"));
            }
            if (voQLyKQPhatHanh.getTong_kl_trung_thau() != null) {
                String tong_kl_trung_thau = voQLyKQPhatHanh.getTong_kl_trung_thau();
                voQLyKQPhatHanh.setTong_kl_trung_thau(StringUtil.convertNumberToString(tong_kl_trung_thau, "VND"));
            }
            if (voQLyKQPhatHanh.getTong_tien_ban() != null) {
                String tong_tien_ban = voQLyKQPhatHanh.getTong_tien_ban();
                voQLyKQPhatHanh.setTong_tien_ban(StringUtil.convertNumberToString(tong_tien_ban, "VND"));
            }
            BeanUtils.copyProperties(f, voQLyKQPhatHanh);
            //End
            //Chi tiet_TPCP
            HashMap<String, Object> map_chitiet_tpcp = new HashMap<String, Object>();
            map_chitiet_tpcp.put("TP_KQ_PH_ID", guid);
            Collection lstCTiet_tpcpVO = null; // goi DAO lay dl tu bang chi tiet theo GUID
            lstCTiet_tpcpVO = delegate.getListKQPH_CTIET_TPCP_HIENTHI(map_chitiet_tpcp);
            Collection lstCTiet_TPCPForm = new ArrayList();
            Iterator ito_tpcp = lstCTiet_tpcpVO.iterator();
            QLyKQPhatHanhCTiet_TpcpVO ctiet_tpcpVO = null;
            QLyKQPhatHanhCTiet_TpcpForm ctiet_tpcpForm = null;
            int dem_tpcp = 0;
            while (ito_tpcp.hasNext()) {
                ctiet_tpcpForm = new QLyKQPhatHanhCTiet_TpcpForm();
                dem_tpcp++;
                ctiet_tpcpVO = (QLyKQPhatHanhCTiet_TpcpVO)ito_tpcp.next();
                ctiet_tpcpVO.setStt(dem_tpcp + "");
                if (ctiet_tpcpVO.getKlph() != null) {
                    String Klph = ctiet_tpcpVO.getKlph();
                    ctiet_tpcpVO.setKlph(StringUtil.convertNumberToString(Klph, "VND"));
                }
                if (ctiet_tpcpVO.getKlph_them() != null) {
                    String Klph_them = ctiet_tpcpVO.getKlph_them();
                    ctiet_tpcpVO.setKlph_them(StringUtil.convertNumberToString(Klph_them, "VND"));
                }
                if (ctiet_tpcpVO.getTong_klph() != null) {
                    String Tong_klph = ctiet_tpcpVO.getTong_klph();
                    ctiet_tpcpVO.setTong_klph(StringUtil.convertNumberToString(Tong_klph, "VND"));
                }
                if (ctiet_tpcpVO.getLs_trung_thau() != null) {
                    String Ls_trung_thau = ctiet_tpcpVO.getLs_trung_thau();
                    ctiet_tpcpVO.setLs_trung_thau(StringUtil.convertNumberToString(Ls_trung_thau, "VND"));
                }
                if (ctiet_tpcpVO.getLs_danh_nghia() != null) {
                    String Ls_danh_nghia = ctiet_tpcpVO.getLs_danh_nghia();
                    ctiet_tpcpVO.setLs_danh_nghia(StringUtil.convertNumberToString(Ls_danh_nghia, "VND"));
                }
                BeanUtils.copyProperties(ctiet_tpcpForm, ctiet_tpcpVO);
                lstCTiet_TPCPForm.add(ctiet_tpcpForm);
            }
            f.setLstKQDT_CTiet_TPCP(lstCTiet_TPCPForm);
            //End Chitiet_TPCP

            //Chi tiet_SOHUU
            HashMap<String, Object> map_chitiet_sohuu = new HashMap<String, Object>();
            map_chitiet_sohuu.put("TP_KQ_PH_ID", guid);
            Collection lstCTietVO = null; // goi DAO lay dl tu bang chi tiet theo GUID
            lstCTietVO = delegate.getListQLyKQPhatHanh_CTIET_SOHUU(map_chitiet_sohuu);
            Collection lstCTietSoHuuForm = new ArrayList();
            Iterator ito_sohuu = lstCTietVO.iterator();
            QLyKQPhatHanhCTiet_SoHuuVO ctiet_sohuuVO = null;
            QLyKQPhatHanhCTiet_SoHuuForm ctiet_sohuuForm = null;
            int dem_sohuu = 0;
            while (ito_sohuu.hasNext()) {
                ctiet_sohuuForm = new QLyKQPhatHanhCTiet_SoHuuForm();
                dem_sohuu++;
                ctiet_sohuuVO = (QLyKQPhatHanhCTiet_SoHuuVO)ito_sohuu.next();
                ctiet_sohuuVO.setStt(dem_sohuu + "");
                if (ctiet_sohuuVO.getKl_trung_thau() != null) {
                    String Kl_trung_thau = ctiet_sohuuVO.getKl_trung_thau();
                    ctiet_sohuuVO.setKl_trung_thau(StringUtil.convertNumberToString(Kl_trung_thau, "VND"));
                }
                if (ctiet_sohuuVO.getLs_trung_thau() != null) {
                    String Ls_trung_thau = ctiet_sohuuVO.getLs_trung_thau();
                    ctiet_sohuuVO.setLs_trung_thau(StringUtil.convertNumberToString(Ls_trung_thau, "VND"));
                }
                if (ctiet_sohuuVO.getTien_tt_mua() != null) {
                    String Tien_tt_mua = ctiet_sohuuVO.getTien_tt_mua();
                    ctiet_sohuuVO.setTien_tt_mua(StringUtil.convertNumberToString(Tien_tt_mua, "VND"));
                }
                BeanUtils.copyProperties(ctiet_sohuuForm, ctiet_sohuuVO);
                lstCTietSoHuuForm.add(ctiet_sohuuForm);
            }
            f.setLstKQPH_CTiet_SoHuu(lstCTietSoHuuForm);
            //End Chitiet_SOHUU
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
            QLyKQPhatHanhForm f = (QLyKQPhatHanhForm)form;
            QLyKQPhatHanhVO vo = new QLyKQPhatHanhVO();
            HttpSession session = request.getSession();
            String nUserID = session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString();
            BeanUtils.copyProperties(vo, f);
            vo.setTrang_thai("01");
            QLyKQPhatHanhDelegate delegate = new QLyKQPhatHanhDelegate(conn);
            long idAdd = delegate.update(vo);
            //insert history
            UserHistoryVO userHisVO = new UserHistoryVO();
            userHisVO.setNguoi_tdoi(new Long(nUserID));
            userHisVO.setNoi_dung_thaydoi("Trinh ket qua phat hanh trai phieu tw" + idAdd);
            userHisVO.setNsd_id(idAdd);
            delegate.insertHistoryUser(userHisVO);
            f.reset(mapping, request);
            if (idAdd != 0) {
                errMess = "kqphathanh.trinhkq.succ";
            } else
                errMess = "kqphathanh.trinhkq.error";
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
            QLyKQPhatHanhForm f = (QLyKQPhatHanhForm)form;
            QLyKQPhatHanhVO vo = new QLyKQPhatHanhVO();
            HttpSession session = request.getSession();
            String nUserID = session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString();
            BeanUtils.copyProperties(vo, f);
            vo.setTrang_thai("02");
            vo.setNgay_duyet(getDate());
            vo.setNguoi_duyet(nUserID);
            QLyKQPhatHanhDelegate delegate = new QLyKQPhatHanhDelegate(conn);
            long idAdd = delegate.update(vo);
            //insert history
            UserHistoryVO userHisVO = new UserHistoryVO();
            userHisVO.setNguoi_tdoi(new Long(nUserID));
            userHisVO.setNoi_dung_thaydoi("Phe duyet ket qua phat hanh trai phieu tw" + idAdd);
            userHisVO.setNsd_id(idAdd);
            delegate.insertHistoryUser(userHisVO);
            f.reset(mapping, request);
            if (idAdd != 0) {
                errMess = "kqphathanh.pheduyet.succ";
            } else
                errMess = "kqphathanh.pheduyet.error";
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
            QLyKQPhatHanhForm f = (QLyKQPhatHanhForm)form;
            QLyKQPhatHanhVO vo = new QLyKQPhatHanhVO();
            HttpSession session = request.getSession();
            String nUserID = session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString();
            BeanUtils.copyProperties(vo, f);
            vo.setTrang_thai("03");
            vo.setNgay_duyet(getDate());
            vo.setNguoi_duyet(nUserID);
            QLyKQPhatHanhDelegate delegate = new QLyKQPhatHanhDelegate(conn);
            long idAdd = delegate.update(vo);
            //insert history
            UserHistoryVO userHisVO = new UserHistoryVO();
            userHisVO.setNguoi_tdoi(new Long(nUserID));
            userHisVO.setNoi_dung_thaydoi("Tu choi ket qua phat hanh trai phieu tw" + idAdd);
            userHisVO.setNsd_id(idAdd);
            delegate.insertHistoryUser(userHisVO);
            f.reset(mapping, request);
            if (idAdd != 0) {
                errMess = "kqphathanh.tuchoi.succ";
            } else
                errMess = "kqphathanh.tuchoi.error";
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
            QLyKQPhatHanhDelegate delegate = new QLyKQPhatHanhDelegate(conn);
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("GUID", guid);
            QLyKQPhatHanhVO voQLPhatHanh = delegate.getQLyKQPhatHanhObject(map);
            if (voQLPhatHanh == null) {
                saveMessage(request, new TPCPException("kqphathanh.list.norecord"));
                return mapping.findForward(FAILURE);
            } else {
                QLyKQPhatHanhForm f = (QLyKQPhatHanhForm)form;
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
            QLyKQPhatHanhDelegate delegate = new QLyKQPhatHanhDelegate(conn);
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("GUID", guid);
            QLyKQPhatHanhVO voQLPhatHanh = delegate.getQLyKQPhatHanhObject(map);
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
            QLyKQPhatHanhForm f = (QLyKQPhatHanhForm)form;
            BeanUtils.copyProperties(f, voQLPhatHanh);
            //Ky Han
            DMKyHanDelegate dmKyHanDelegate = new DMKyHanDelegate(conn);
            //get list ky han
            HashMap<String, Object> map_kyhan = new HashMap<String, Object>();
            map_kyhan.put("LOAI_TPCP", "TRAI_PHIEU");
            List lstKyHan = (List)dmKyHanDelegate.getDMKyHan(map_kyhan);
            request.setAttribute("lstKyHan", lstKyHan);
            //Chi tiet - TPCP
            HashMap<String, Object> map_chitiet = new HashMap<String, Object>();
            map_chitiet.put("TP_KQ_PH_ID", guid);
            Collection lstCTiet_TPCPVO = null;
            lstCTiet_TPCPVO = delegate.getListKQPH_CTIET_TPCP_HIENTHI(map_chitiet);
            Collection lstCTiet_TPCPForm = new ArrayList();
            Iterator itoTPCP = lstCTiet_TPCPVO.iterator();
            QLyKQPhatHanhCTiet_TpcpVO ctiet_TPCPVO = null;
            QLyKQPhatHanhCTiet_TpcpForm ctiet_TPCPForm = null;
            int dem = 0;
            while (itoTPCP.hasNext()) {
                ctiet_TPCPForm = new QLyKQPhatHanhCTiet_TpcpForm();
                dem++;
                ctiet_TPCPVO = (QLyKQPhatHanhCTiet_TpcpVO)itoTPCP.next();
                ctiet_TPCPVO.setStt(dem + "");
                if (ctiet_TPCPVO.getLs_trung_thau() != null) {
                    String Klph = ctiet_TPCPVO.getLs_trung_thau();
                    ctiet_TPCPVO.setLs_trung_thau(StringUtil.convertNumberToString(Klph, "VND"));
                }
                if (ctiet_TPCPVO.getLs_danh_nghia() != null) {
                    String Klph = ctiet_TPCPVO.getLs_danh_nghia();
                    ctiet_TPCPVO.setLs_danh_nghia(StringUtil.convertNumberToString(Klph, "VND"));
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
                BeanUtils.copyProperties(ctiet_TPCPForm, ctiet_TPCPVO);
                lstCTiet_TPCPForm.add(ctiet_TPCPForm);
            }
            f.setLstKQDT_CTiet_TPCP(lstCTiet_TPCPForm);
            session.setAttribute("lstKQDT_CTiet_TPCP", f.getLstKQDT_CTiet_TPCP());
            //End Chitiet tpcp

            // Chi tiet so huu
            Collection lstCTiet_SoHuuVO = null;
            lstCTiet_SoHuuVO = delegate.getListQLyKQPhatHanh_CTIET_SOHUU(map_chitiet);
            Collection lstCTiet_SoHuuForm = new ArrayList();
            Iterator itoSoHuu = lstCTiet_SoHuuVO.iterator();
            QLyKQPhatHanhCTiet_SoHuuVO ctiet_SoHuuVO = null;
            QLyKQPhatHanhCTiet_SoHuuForm ctiet_SoHuuForm = null;
            int dem_sohuu = 0;
            while (itoSoHuu.hasNext()) {
                ctiet_SoHuuForm = new QLyKQPhatHanhCTiet_SoHuuForm();
                dem_sohuu++;
                ctiet_SoHuuVO = (QLyKQPhatHanhCTiet_SoHuuVO)itoSoHuu.next();
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
                    String Kl_trung_thau = ctiet_SoHuuVO.getLs_trung_thau();
                    ctiet_SoHuuVO.setLs_trung_thau(StringUtil.convertNumberToString(Kl_trung_thau, "VND"));
                }
                BeanUtils.copyProperties(ctiet_SoHuuForm, ctiet_SoHuuVO);
                lstCTiet_SoHuuForm.add(ctiet_SoHuuForm);
            }
            f.setLstKQPH_CTiet_SoHuu(lstCTiet_SoHuuForm);
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

    public String getDate() {
        String date = FastDateFormat.getInstance("dd/MM/yyyy").format(System.currentTimeMillis());
        return date;
    }

    public ActionForward updateExc(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Connection conn = null;
        String errMess = "";
        try {
            conn = getConnection(request);
            QLyKQPhatHanhForm f = (QLyKQPhatHanhForm)form;
            QLyKQPhatHanhVO vo = new QLyKQPhatHanhVO();
            HttpSession session = request.getSession();
            String nUserID = session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString();
            BeanUtils.copyProperties(vo, f);
            vo.setLstKQDT_CTiet_TPCP((Collection)session.getAttribute("lstKQDT_CTiet_TPCP"));
            session.removeAttribute("lstKQDT_CTiet_TPCP");
            vo.setKy_han((String)session.getAttribute("sKyhan"));
            session.removeAttribute("sKyhan");
            vo.setNgay_sua_cuoi(getDate());
            vo.setNguoi_sua_cuoi(nUserID);
            //Add ban le chi tiet_SoHuu
            XuLyPhanChiTietSoHuu(request, vo);
            //End add ban le chi tiet
            QLyKQPhatHanhDelegate delegate = new QLyKQPhatHanhDelegate(conn);
            long idAdd = delegate.update(vo);
            UserHistoryVO userHisVO = new UserHistoryVO();
            userHisVO.setNguoi_tdoi(new Long(nUserID));
            userHisVO.setNoi_dung_thaydoi("Update kq phat hanh trai phieu tw" + idAdd);
            userHisVO.setNsd_id(idAdd);
            delegate.insertHistoryUser(userHisVO);
            f.reset(mapping, request);
            if (idAdd != 0) {
                errMess = "kqphathanh.update.succ";
            } else
                errMess = "kqphathanh.update.error";
            saveMessage(request, new TPCPException(errMess));
            return mapping.findForward(SUCCESS);
        } catch (Exception e) {
            conn.rollback();
            throw e;
        } finally {
            close(conn);
        }

    }
}
