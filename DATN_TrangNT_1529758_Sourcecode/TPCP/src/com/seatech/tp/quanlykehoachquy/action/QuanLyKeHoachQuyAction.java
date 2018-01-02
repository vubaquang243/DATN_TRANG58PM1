package com.seatech.tp.quanlykehoachquy.action;


import com.seatech.framework.AppConstants;
import com.seatech.framework.common.jsp.PagingBean;
import com.seatech.framework.datamanager.ReportUtility;
import com.seatech.framework.exception.TPCPException;
import com.seatech.framework.strustx.AppAction;
import com.seatech.framework.utils.StringUtil;
import com.seatech.tp.dmkyhan.action.DMKyHanDelegate;
import com.seatech.tp.dmkyhan.vo.DMKyHanVO;
import com.seatech.tp.kqphathanh.vo.QLyKQPhatHanhCTiet_SoHuuVO;
import com.seatech.tp.kqphathanh.vo.QLyKQPhatHanhVO;
import com.seatech.tp.qlytp.action.QuanLyTPCPDelegate;
import com.seatech.tp.quanlykehoachquy.form.QuanLyKeHoachQuyForm;
import com.seatech.tp.quanlykehoachquy.vo.KHPHChiTietVO;
import com.seatech.tp.quanlykehoachquy.vo.QuanLyKeHoachQuyVO;
import com.seatech.tp.ttindthau.action.QLyTTinDauThauDelegate;
import com.seatech.tp.ttindthau.form.ThongTinDauThauForm;
import com.seatech.tp.ttindthau.vo.ThongTinDauThauVO;
import com.seatech.tp.user.UserHistoryVO;

import java.io.InputStream;

import java.math.BigDecimal;

import java.sql.Connection;

import java.sql.ResultSet;

import java.text.SimpleDateFormat;

import java.util.AbstractCollection;
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
import org.apache.commons.lang.time.FastDateFormat;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class QuanLyKeHoachQuyAction extends AppAction {
    private static String SUCCESS = "success";
    private static String FAILURE = "failure";

    public QuanLyKeHoachQuyAction() {
        super();
    }

    public ActionForward list(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        Connection conn = null;
        try {
            QuanLyKeHoachQuyForm f = (QuanLyKeHoachQuyForm)form;
            f.reset(mapping, request);
           
        } catch (Exception e) {
            throw e;
        } finally {
            close(conn);
        }
      return search(mapping, form, request, response);
    }

    public ActionForward search(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
        //check quyen

        Connection conn = null;
        try {

            conn = getConnection(request);
            QuanLyKeHoachQuyForm f = (QuanLyKeHoachQuyForm)form;
            int phantrang = (AppConstants.APP_NUMBER_ROW_ON_PAGE);
            // khai bao bien phan trang.
            String page = f.getPageNumber();
            if (page == null)
                page = "1";
            Integer currentPage = new Integer(page);
            Integer numberRowOnPage = phantrang;
            Integer totalCount[] = new Integer[1];
            QuanLyKeHoachQuyVO vo = new QuanLyKeHoachQuyVO();
            BeanUtils.copyProperties(vo, f);
            QuanLyKeHoachQuyDelegate delegate =
                new QuanLyKeHoachQuyDelegate(conn);
            List listKHQuy = null;
            listKHQuy =
                    (List)delegate.getListQLKHPaging(vo, currentPage, numberRowOnPage,
                                                     totalCount);
            Iterator ito = listKHQuy.iterator();
            Collection resultKH = new ArrayList();
            while (ito.hasNext()) {
                vo = (QuanLyKeHoachQuyVO)ito.next();
                if (vo.getTong_klph() != null) {
                    BigDecimal bTongKLTP;
                    bTongKLTP = new BigDecimal(vo.getTong_klph());
                    BigDecimal giatri;
                    if("VND".equals(vo.getLoai_tien())){
                     giatri = new BigDecimal(1000000000);
                    }else{
                      giatri = new BigDecimal(1000000);
                    }
                    bTongKLTP = bTongKLTP.divide(giatri);
                    String khoiLuong = String.valueOf(bTongKLTP);
                    vo.setTong_klph(StringUtil.convertNumberToString(khoiLuong,
                                                                     "VND"));
                }
                if("02".equals(vo.getTrang_thai())&&new Date().before(StringUtil.StringToDate(vo.getNgay_het_han(), "dd/MM/yyyy"))){
                    vo.setIsReplaced(true);
                }
                resultKH.add(vo);
            }
            PagingBean pagingBean = new PagingBean();
            pagingBean.setCurrentPage(currentPage);
            pagingBean.setNumberOfRow((totalCount[0] == null) ? 0 :
                                      totalCount[0].intValue());
            pagingBean.setRowOnPage(numberRowOnPage);
            request.setAttribute("PAGE_KEY", pagingBean);
            request.setAttribute("lstTTDT", listKHQuy);
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
            QuanLyKeHoachQuyForm f = (QuanLyKeHoachQuyForm)form;
            //f.reset(mapping, request);
            if(f.getThoi_gian_ph()!=null && !"".equals(f.getThoi_gian_ph())){
            String[] id_ky_han = request.getParameterValues("id_ky_han");
            String[] so_tien = request.getParameterValues("so_tien");
            String[] name_ky_han = request.getParameterValues("name_ky_han");
            int i =id_ky_han.length;
            int j=0;
            
            Collection result1= new ArrayList();
            while(j < i){
              DMKyHanVO vokh= new DMKyHanVO();
              vokh.setId_ky_han(id_ky_han[j]);
              vokh.setName_ky_han(name_ky_han[j]);
              vokh.setSo_tien(so_tien[j]);
              result1.add(vokh);
              j++;
            }
            f.setLisKH(result1);
            }else{
            DMKyHanDelegate delegate = new DMKyHanDelegate(conn);
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("LOAI_TPCP", "TRAI_PHIEU");
            Collection listDMKH = null;
            listDMKH = delegate.getDMKyHan(map);
            f.setLisKH(listDMKH);
                }
            
        } catch (Exception e) {
            throw e;
        } finally {
            close(conn);
        }
      return mapping.findForward(SUCCESS);
    }

    public ActionForward addExc(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws Exception {

        Connection conn = null;
        try {
            conn = getConnection(request);
            HttpSession session = request.getSession();
            String nUserID =
                session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString();
            String sGuid_kh_cu = "";
            if (session.getAttribute("sGuid") != null) {
                sGuid_kh_cu = (String)session.getAttribute("sGuid");
                session.removeAttribute("sGuid");
            }
            String sThoiGian_ph_cu = "";
            if (session.getAttribute("sThoigianPH") != null) {
                sThoiGian_ph_cu = (String)session.getAttribute("sThoigianPH");
                session.removeAttribute("sThoigianPH");
            }
            QuanLyKeHoachQuyForm f = (QuanLyKeHoachQuyForm)form;
            QuanLyKeHoachQuyVO vo = new QuanLyKeHoachQuyVO();
            QuanLyKeHoachQuyDelegate delegate = new QuanLyKeHoachQuyDelegate(conn);
            BigDecimal giatri ;
            if("VND".equals(f.getLoai_tien())){
                giatri = new BigDecimal(1000000000);
            }else{
                giatri = new BigDecimal(1000000);
            }
                
            XuLyPhanChiTiet(request, vo, giatri);
            f.setListKHPH_CTiet(vo.getListKHPH_CTiet());
            Map<String, Object> map= new HashMap<String, Object>();
            if(!f.getKh_goc().equals("")){
              map.put("GUID",f.getKh_goc());
              QuanLyKeHoachQuyVO vo2= delegate.getQLKHCheckObject(map);
              // kiểm tra loại tiền trong kế hoạch thay thế đã đc phát hành chưa 
              if(!vo2.getLoai_tien().equals(f.getLoai_tien())){
                Map<String, Object> map1= new HashMap<String, Object>();
                map1.put("KH_GOC","");
                map1.put("NAM_PH",f.getNam_ph());
                map1.put("thoi_gian_ph",f.getThoi_gian_ph());
                map1.put("LOAI_TIEN", f.getLoai_tien());
                
                QuanLyKeHoachQuyVO vo4= delegate.getQLKHCheckObject(map1);
                if(vo4!=null){
                  throw new TPCPException().createException("TPCP-0024", f.getLoai_tien());
                }
              }
              
            }else{
                
                map.put("NAM_PH",f.getNam_ph());
                map.put("thoi_gian_ph",f.getThoi_gian_ph());
                map.put("LOAI_TIEN", f.getLoai_tien());
                map.put("KH_GOC",f.getKh_goc());
                QuanLyKeHoachQuyVO vo3= delegate.getQLKHCheckObject(map);
                if(vo3!=null){
                  throw new TPCPException().createException("TPCP-0024", f.getLoai_tien());
                }
                
             }
            
            // check số kế hoạch
            Map<String,Object> map1= new HashMap<String,Object>();
            if(!"".equals(f.getSo_tbao_kh())){
            map1.put("SO_TBAO_KH", f.getSo_tbao_kh());
            QuanLyKeHoachQuyVO voKHQ_checkSoTB = delegate.getQLKHCheckObject(map1);
            if(voKHQ_checkSoTB!= null){
              throw new TPCPException().createException("TPCP-0023",f.getSo_tbao_kh());
            }
            }
            // check ngày hiệu lực 
            if(!"".equals(f.getNgay_hieu_luc())){
            Date ngay_hieu_lucQuy= 
                 StringUtil.getFirstDayOfQuarter(Integer.parseInt(f.getThoi_gian_ph()),Integer.parseInt(f.getNam_ph()));
            Date ngay_het_hanQuy =
                  StringUtil.getLastDayOfQuarter(Integer.parseInt(f.getThoi_gian_ph()),Integer.parseInt(f.getNam_ph()));
            SimpleDateFormat format= new SimpleDateFormat("dd/MM/yyyy");
            Date ngay_hieu_lucCheck = format.parse(f.getNgay_hieu_luc());
            if( ngay_hieu_lucCheck.before(ngay_hieu_lucQuy)){
              throw new TPCPException().createException("TPCP-0029",format.format(ngay_hieu_lucQuy));
            }
            if( ngay_hieu_lucCheck.after(ngay_het_hanQuy)){
              throw new TPCPException().createException("TPCP-0030",format.format(ngay_het_hanQuy));
            }}
            BeanUtils.copyProperties(vo, f);
            vo.setNguoi_tao(nUserID);
            vo.setLoai_kh("QU");
            if( "".equals(vo.getTrang_thai())){
            vo.setTrang_thai("00");
            }
            
            //Nhân 1 ty tong KLTP
            String tongKHPH = vo.getTong_klph()==null?"0":vo.getTong_klph().replaceAll("\\.","");
            BigDecimal bTongKLTP;
            bTongKLTP = new BigDecimal(tongKHPH);
            
            bTongKLTP = bTongKLTP.multiply(giatri);
            vo.setTong_klph(String.valueOf(bTongKLTP));
            //End
            if (!"".equals(vo.getNgay_hieu_luc())) {
                int iQuy = Integer.parseInt(vo.getThoi_gian_ph());         
                String sNgay_het_han =
                    FastDateFormat.getInstance("dd/MM/yyyy").format(StringUtil.getLastDayOfQuarter(iQuy,Integer.parseInt(vo.getNam_ph())));
                vo.setNgay_het_han(sNgay_het_han);
            } else {
                //Lay ngay ph quy
                int iQuy = Integer.parseInt(f.getThoi_gian_ph());
                String sNgay_hieu_luc =
                    FastDateFormat.getInstance("dd/MM/yyyy").format(StringUtil.getFirstDayOfQuarter(iQuy,Integer.parseInt(vo.getNam_ph())));
                String sNgay_het_han =
                    FastDateFormat.getInstance("dd/MM/yyyy").format(StringUtil.getLastDayOfQuarter(iQuy,Integer.parseInt(vo.getNam_ph())));
                vo.setNgay_hieu_luc(sNgay_hieu_luc);
                vo.setNgay_het_han(sNgay_het_han);
            }
            //End
            

            long idAdd = delegate.update(vo);
            if (!"".equals(vo.getKh_goc())) {
                String guids = (String) vo.getKh_goc();
                SimpleDateFormat format= new SimpleDateFormat("dd/MM/yyyy");
                Date date= StringUtil.StringToDate(vo.getNgay_hieu_luc().toString(), "dd/MM/yyyy");
                Long time=date.getTime()-86400000;
                Date ngay_het_han= new Date(time);
                
                String sNgay_het_han =format.format(ngay_het_han);
                delegate.UpdateNgayHieuLuc_KeHoachCu(guids,sNgay_het_han);
            }
            //insert history
            UserHistoryVO userHisVO = new UserHistoryVO();
            userHisVO.setNguoi_tdoi(new Long(nUserID));
            userHisVO.setNoi_dung_thaydoi("Them moi ma TPKeHoach" + idAdd);
            userHisVO.setNsd_id(idAdd);
            delegate.insertHistoryUser(userHisVO);
            f.reset(mapping, request);
            if (vo.getKh_goc().toString().trim().equals("")) {
                saveMessage(request, new TPCPException("qlykehoach.add.succ"));
            } else {
                String errMess = "";
                if (idAdd != 0) {
                    errMess = "qlykehoach.thaythe.succ";
                } else
                    errMess = "qlykehoach.thaythe.error";
                saveMessage(request, new TPCPException(errMess));
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            close(conn);
        }
        return mapping.findForward(SUCCESS);
    }

    public void XuLyPhanChiTiet(HttpServletRequest request,
                                QuanLyKeHoachQuyVO vo,
                                BigDecimal giatri) throws Exception {
        {
            String[] id_ky_han = request.getParameterValues("id_ky_han");
            String[] so_tien = request.getParameterValues("so_tien");
            BigDecimal bSoTien;
            Collection KH = new ArrayList();
            for (int i = 0; i < so_tien.length; i++) {
                KHPHChiTietVO voKH = new KHPHChiTietVO();
                String sSoTien = "0";
                if (so_tien[i]==null || so_tien[i].equals("")) {
                    sSoTien = "0";
                }else sSoTien = so_tien[i].replaceAll("\\.","");               
                bSoTien = new BigDecimal(sSoTien);
                bSoTien = bSoTien.multiply(giatri);
                voKH.setKlph(String.valueOf(bSoTien));
                voKH.setKy_han(id_ky_han[i]);
                KH.add(voKH);
            }
            vo.setListKHPH_CTiet(KH);
        }
        //End add ban le chi tiet
    }

    public ActionForward update(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
        Connection conn = null;
        try {
            conn = getConnection();
            QuanLyKeHoachQuyForm f= (QuanLyKeHoachQuyForm) form;
            String guid=null; 
            if(f.getGuid()== null || "".equals(f.getGuid())){
            guid= request.getParameter("longid").trim();    
            }else{
                  guid = (String)f.getGuid();
                }
            QuanLyKeHoachQuyDelegate delegate =new QuanLyKeHoachQuyDelegate(conn);
            QuanLyKeHoachQuyVO vo = delegate.getTpKeHoachObject(guid);
            if (!vo.getTrang_thai().toString().trim().equals("02")) {
                if (vo == null) {
                    saveMessage(request,
                                new TPCPException("qlykehoach.list.notfount"));
                    return mapping.findForward(FAILURE);
                }
                BigDecimal bTongKLTP;
                bTongKLTP = new BigDecimal(vo.getTong_klph());
                BigDecimal giatri ;
                if("VND".equals(vo.getLoai_tien())){
                    giatri = new BigDecimal(1000000000);
                }else{
                    giatri = new BigDecimal(1000000);
                }
                bTongKLTP = bTongKLTP.divide(giatri);
                String khoiLuong = String.valueOf(bTongKLTP);
                vo.setTong_klph(StringUtil.convertNumberToString(khoiLuong,
                                                                 "VND"));
                vo.setTong_chi_tiet(StringUtil.convertNumberToString(khoiLuong,
                                                                     "VND"));
//                QuanLyKeHoachQuyForm f = (QuanLyKeHoachQuyForm)form;
                DMKyHanDelegate delegateKH = new DMKyHanDelegate(conn);
                HashMap<String, Object> map = new HashMap<String, Object>();
                map.put("LOAI_TPCP", "TRAI_PHIEU");
                List listDMKH = null;
                listDMKH = (List)delegateKH.getDMKyHan(map);
                request.setAttribute("listDMKH", listDMKH);


                KHPHChiTietVO voCT = new KHPHChiTietVO();
                List QLKHChiTiet = new ArrayList();

                QLKHChiTiet = (List)delegate.getListKHPHCT(voCT, guid);
                Collection result = new ArrayList();
                Iterator ito = QLKHChiTiet.iterator();
                Iterator itoKH = listDMKH.iterator();
                KHPHChiTietVO voCT1 = null;
                DMKyHanVO voKH1 = null;
                while (ito.hasNext()) {
                    voCT1 = (KHPHChiTietVO)ito.next();
                    itoKH = listDMKH.iterator();
                    while (itoKH.hasNext()) {
                        voKH1 = (DMKyHanVO)itoKH.next();
                        if (voCT1.getKy_han().equals(voKH1.getGuid())) {
                            voKH1.setId_ky_han(voCT1.getKy_han());
                            //
                            BigDecimal bSoTien;
                            bSoTien = new BigDecimal(voCT1.getKlph());
                            bSoTien = bSoTien.divide(giatri);
                            String sSotien = String.valueOf(bSoTien);
                            voKH1.setSo_tien(StringUtil.convertNumberToString(sSotien,
                                                                              "VND"));
                            //
                            result.add(voKH1);
                        }

                    }
                }
                f.setLisKH(result);
                BeanUtils.copyProperties(f, vo);
            } else {
                HttpSession session = request.getSession();
//                session.setAttribute("sGuid", guid);
//                session.setAttribute("sThoigianPH", vo.getThoi_gian_ph());
//                QuanLyKeHoachQuyForm f = (QuanLyKeHoachQuyForm)form;
                f.reset(mapping, request);
                HashMap<String,Object>mapp= new HashMap();
                mapp.put("GUID",guid);
                QuanLyKeHoachQuyVO voRe= delegate.getQLKHObject(mapp);
                request.setAttribute("QuanLyKeHoachQuyVO", voRe);
                f.setLoai_tien(voRe.getLoai_tien());
                f.setNam_ph(voRe.getNam_ph());
                f.setThoi_gian_ph(voRe.getThoi_gian_ph());
                f.setKh_goc(guid);
                f.setSo_tbao_kh_cu(voRe.getSo_tbao_kh());
                DMKyHanDelegate delegate_KH = new DMKyHanDelegate(conn);
                HashMap<String, Object> map = new HashMap<String, Object>();
                map.put("LOAI_TPCP", "TRAI_PHIEU");
                Collection listDMKH = null;
                listDMKH = delegate_KH.getDMKyHan(map);
                f.setLisKH(listDMKH);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            close(conn);
        }
        return mapping.findForward(SUCCESS);
    }

    public String getDate() {
        String date =
            FastDateFormat.getInstance("dd/MM/yyyy").format(System.currentTimeMillis());
        return date;
    }

    public ActionForward updateExc(ActionMapping mapping, ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response) throws Exception {
        Connection conn = null;
        try {
            conn = getConnection(request);
            HttpSession session = request.getSession();
            String nUserID =
                session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString();
            QuanLyKeHoachQuyDelegate delegate =
                new QuanLyKeHoachQuyDelegate(conn);
            QuanLyKeHoachQuyForm f = (QuanLyKeHoachQuyForm)form;
            Map<String,Object> map= new HashMap();
            map.put("GUID", f.getGuid());
            QuanLyKeHoachQuyVO voCheck= delegate.getQLKHObject(map);
            QuanLyKeHoachQuyVO voCheck1 = new QuanLyKeHoachQuyVO();
            
            if(!voCheck.getLoai_tien().equals(f.getLoai_tien())){
              Map<String, Object> mapp=new HashMap();
              mapp.put("NAM_PH",f.getNam_ph());
              mapp.put("THOI_GIAN_PH",f.getThoi_gian_ph());
              mapp.put("LOAI_TIEN",f.getLoai_tien());
              voCheck1= delegate.getQLKHObject(mapp);
              if(voCheck1!=null){
                throw new TPCPException().createException("TPCP-0024",f.getLoai_tien());
              }
            }
            if(!"".equals(f.getNgay_hieu_luc())){
            Date ngay_hieu_lucQuy= 
                 StringUtil.getFirstDayOfQuarter(Integer.parseInt(f.getThoi_gian_ph()),Integer.parseInt(f.getNam_ph()));
            Date ngay_het_hanQuy =
                  StringUtil.getLastDayOfQuarter(Integer.parseInt(f.getThoi_gian_ph()),Integer.parseInt(f.getNam_ph()));
            SimpleDateFormat format= new SimpleDateFormat("dd/MM/yyyy");
            Date ngay_hieu_lucCheck = format.parse(f.getNgay_hieu_luc());
            if( ngay_hieu_lucCheck.before(ngay_hieu_lucQuy)){
              throw new TPCPException().createException("TPCP-0029",format.format(ngay_hieu_lucQuy));
            }
            if( ngay_hieu_lucCheck.after(ngay_het_hanQuy)){
              throw new TPCPException().createException("TPCP-0030",format.format(ngay_het_hanQuy));
            }}
            Map<String,Object> map1= new HashMap<String,Object>();
            
            // so thong bao
            if(voCheck.getSo_tbao_kh() != null && !"".equals(f.getSo_tbao_kh())){
                if(!f.getSo_tbao_kh().equals(voCheck.getSo_tbao_kh())){
                  map1.put("SO_TBAO_KH", f.getSo_tbao_kh().trim());
                  QuanLyKeHoachQuyVO voCheckso_tb= delegate.getQLKHObject(map1);
                  if(voCheckso_tb!= null){
                    throw new TPCPException().createException("TPCP-0023",f.getSo_tbao_kh());
                  }
                }
            }else if(voCheck.getSo_tbao_kh() == null && !"".equals(f.getSo_tbao_kh())){
              if(!f.getSo_tbao_kh().equals(voCheck.getSo_tbao_kh())){
                map1.put("SO_TBAO_KH", f.getSo_tbao_kh().trim());
                QuanLyKeHoachQuyVO voCheckso_tb1= delegate.getQLKHObject(map1);
                if(voCheckso_tb1!= null){
                  throw new TPCPException().createException("TPCP-0023",f.getSo_tbao_kh());
                }
              }
            }
                  
            
            QuanLyKeHoachQuyVO vo = new QuanLyKeHoachQuyVO();
//            QuanLyKeHoachQuyVO voKHQ_Check =
//                delegate.getTpKeHoachCheckObject(f.getNam_ph(),
//                                                 f.getThoi_gian_ph());
            if("".equals(f.getSo_tbao_kh())){
              f.setSo_tbao_kh(" ");
            }
            BeanUtils.copyProperties(vo, f);

            
            //Nhân 1 ty tong KLTP
            BigDecimal bTongKLTP;
            bTongKLTP = new BigDecimal(vo.getTong_klph());
            BigDecimal giatri ;
            if("VND".equals(f.getLoai_tien())){
                giatri = new BigDecimal(1000000000);
            }else{
                giatri = new BigDecimal(1000000);
            }
            
            bTongKLTP = bTongKLTP.multiply(giatri);
            XuLyPhanChiTiet(request, vo, giatri);

            vo.setTong_klph(String.valueOf(bTongKLTP));
            int iQuy = Integer.parseInt(f.getThoi_gian_ph());
            
//            String sNgay_hieu_luc =
//                FastDateFormat.getInstance("dd/MM/yyyy").format(StringUtil.getFirstDayOfQuarter(iQuy,Integer.parseInt(vo.getNam_ph())));
//            String sNgay_het_han =
//                FastDateFormat.getInstance("dd/MM/yyyy").format(StringUtil.getLastDayOfQuarter(iQuy,Integer.parseInt(vo.getNam_ph())));
//            vo.setNgay_hieu_luc(sNgay_hieu_luc);
//            vo.setNgay_het_han(sNgay_het_han);
            
            
            
            vo.setNgay_sua_cuoi(getDate());
            vo.setNguoi_sua_cuoi(nUserID);
         
            long idAdd = delegate.update(vo);
            if(!"".equals(vo.getNgay_hieu_luc() )){
             String guids = (String) vo.getKh_goc();
             SimpleDateFormat format= new SimpleDateFormat("dd/MM/yyyy");
             Date date= StringUtil.StringToDate(vo.getNgay_hieu_luc().toString(), "dd/MM/yyyy");
             Long time=date.getTime()-86400000;
             Date ngay_het_han= new Date(time);
             
             String sNgay_het_han =format.format(ngay_het_han);
             
            long update= delegate.UpdateNgayHieuLuc_KeHoachCu(vo.getKh_goc(),sNgay_het_han);
           }
            //insert history
            UserHistoryVO userHisVO = new UserHistoryVO();
            userHisVO.setNguoi_tdoi(new Long(nUserID));
            userHisVO.setNoi_dung_thaydoi("Cap nhat ma ke hoach" + idAdd);
            userHisVO.setNsd_id(idAdd);
            delegate.insertHistoryUser(userHisVO);
            f.reset(mapping, request);
            if("00".equals(vo.getTrang_thai())){
            saveMessage(request, new TPCPException("qlykehoach.update.succ"));
            }else{
              saveMessage(request, new TPCPException("qlykehoach.updateandsub.succ"));
            }
                      
            
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
        Connection conn = null;
        try {
            conn = getConnection();
            QuanLyKeHoachQuyForm f = (QuanLyKeHoachQuyForm)form;
            QuanLyKeHoachQuyVO vo = new QuanLyKeHoachQuyVO();
            QuanLyKeHoachQuyDelegate delegate =
                new QuanLyKeHoachQuyDelegate(conn);
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("GUID", f.getGuid());
            QuanLyKeHoachQuyVO voTP = delegate.getQLKHObject(map);

            if (voTP == null) {
                saveMessage(request,
                            new TPCPException("qlykehoach.list.notfount"));
                return mapping.findForward(FAILURE);
            }

            HttpSession session = request.getSession();
            String nUserID =
                session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString();
            BeanUtils.copyProperties(vo, f);
            vo.setTrang_thai("01");
            //Nhân 1 ty tong KLTP
            BigDecimal bTongKLTP;
            bTongKLTP = new BigDecimal(vo.getTong_klph());
            BigDecimal giatri ;
            if("VND".equals(f.getLoai_tien())){
                giatri = new BigDecimal(1000000000);
            }else{
                giatri = new BigDecimal(1000000);
            }
            
            bTongKLTP = bTongKLTP.multiply(giatri);
            vo.setTong_klph(String.valueOf(bTongKLTP));
            //End

            long idAdd = delegate.update(vo);
            //insert history
            UserHistoryVO userHisVO = new UserHistoryVO();
            userHisVO.setNguoi_tdoi(new Long(nUserID));
            userHisVO.setNoi_dung_thaydoi("Trinhkq quan ly ke hoach Quy" +
                                          idAdd);
            userHisVO.setNsd_id(idAdd);
            delegate.insertHistoryUser(userHisVO);
            f.reset(mapping, request);
            String errMess = "";
            if (idAdd != 0) {
                errMess = "qlykehoach.trinhkq.succ";
            } else
                errMess = "qlykehoach.trinhkq.error";
            saveMessage(request, new TPCPException(errMess));
        } catch (Exception e) {
            conn.rollback();
            throw e;
        } finally {
            close(conn);
        }
        return mapping.findForward(SUCCESS);
    }

    public ActionForward pheDuyetAction(ActionMapping mapping, ActionForm form,
                                        HttpServletRequest request,
                                        HttpServletResponse response) throws Exception {
        Connection conn = null;
        try {
            conn = getConnection();
            QuanLyKeHoachQuyForm f = (QuanLyKeHoachQuyForm)form;
            QuanLyKeHoachQuyVO vo = new QuanLyKeHoachQuyVO();
            QuanLyKeHoachQuyDelegate delegate =
                new QuanLyKeHoachQuyDelegate(conn);
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("GUID", f.getGuid());
            QuanLyKeHoachQuyVO voTP = delegate.getQLKHObject(map);
            if (voTP == null) {
                saveMessage(request,
                            new TPCPException("qlykehoach.list.notfount"));
                return mapping.findForward(FAILURE);
            }
            HttpSession session = request.getSession();
            String nUserID =
                session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString();
            BeanUtils.copyProperties(vo, f);
            vo.setTrang_thai("02");
            vo.setNgay_duyet(getDate());
            vo.setNguoi_duyet(nUserID);
            //Nhân 1 ty tong KLTP
            BigDecimal bTongKLTP;
            bTongKLTP = new BigDecimal(vo.getTong_klph());
            BigDecimal giatri ;
            if("VND".equals(f.getLoai_tien())){
                giatri = new BigDecimal(1000000000);
            }else{
                giatri = new BigDecimal(1000000);
            }
            
            bTongKLTP = bTongKLTP.multiply(giatri);
            vo.setTong_klph(String.valueOf(bTongKLTP));
            //End
            long idAdd = delegate.update(vo);
            //insert history
            UserHistoryVO userHisVO = new UserHistoryVO();
            userHisVO.setNguoi_tdoi(new Long(nUserID));
            userHisVO.setNoi_dung_thaydoi("Phe duyet ke hoach Quy" + idAdd);
            userHisVO.setNsd_id(idAdd);
            delegate.insertHistoryUser(userHisVO);
            f.reset(mapping, request);
            String errMess = "";
            if (idAdd != 0) {
                errMess = "qlykehoach.pheduyet.succ";
            } else
                errMess = "qlykehoach.pheduyet.error";
            saveMessage(request, new TPCPException(errMess));
        } catch (Exception e) {
            conn.rollback();
            throw e;
        } finally {
            close(conn);
        }
        return mapping.findForward(SUCCESS);
    }

    public ActionForward tuchoiAction(ActionMapping mapping, ActionForm form,
                                      HttpServletRequest request,
                                      HttpServletResponse response) throws Exception {
        Connection conn = null;
        try {
            conn = getConnection();
            QuanLyKeHoachQuyForm f = (QuanLyKeHoachQuyForm)form;
            QuanLyKeHoachQuyVO vo = new QuanLyKeHoachQuyVO();
            QuanLyKeHoachQuyDelegate delegate =
                new QuanLyKeHoachQuyDelegate(conn);
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("GUID", f.getGuid());
            QuanLyKeHoachQuyVO voTP = delegate.getQLKHObject(map);
            if (voTP == null) {
                saveMessage(request,
                            new TPCPException("qlykehoach.list.notfount"));
                return mapping.findForward(FAILURE);
            }
            HttpSession session = request.getSession();
            String nUserID =
                session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString();
            BeanUtils.copyProperties(vo, f);
            vo.setTrang_thai("03");
            vo.setNgay_duyet(getDate());
            vo.setNguoi_duyet(nUserID);
            //Nhân 1 ty tong KLTP
            BigDecimal bTongKLTP;
            bTongKLTP = new BigDecimal(vo.getTong_klph());
            BigDecimal giatri;
            if("VND".equals(vo.getLoai_tien())){
             giatri = new BigDecimal(1000000000);
            }else{
              giatri = new BigDecimal(1000000);
            }
            bTongKLTP = bTongKLTP.multiply(giatri);
            vo.setTong_klph(String.valueOf(bTongKLTP));
            //End
            long idAdd = delegate.update(vo);
            //insert history
            UserHistoryVO userHisVO = new UserHistoryVO();
            userHisVO.setNguoi_tdoi(new Long(nUserID));
            userHisVO.setNoi_dung_thaydoi("Phe duyet ke hoach Quy" + idAdd);
            userHisVO.setNsd_id(idAdd);
            delegate.insertHistoryUser(userHisVO);
            f.reset(mapping, request);
            String errMess = "";
            if (idAdd != 0) {
                errMess = "qlykehoach.tuchoi.succ";
            } else
                errMess = "qlykehoach.tuchoi.error";
            saveMessage(request, new TPCPException(errMess));
        } catch (Exception e) {
            conn.rollback();
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
            QuanLyKeHoachQuyDelegate delegate =
                new QuanLyKeHoachQuyDelegate(conn);
            QuanLyKeHoachQuyVO vo = delegate.getTpKeHoachObject(guid);
            if (vo == null) {
                saveMessage(request,
                            new TPCPException("qlykehoach.list.notfount"));
                return mapping.findForward(FAILURE);
            } else {
                QuanLyKeHoachQuyForm f = (QuanLyKeHoachQuyForm)form;
                long idAdd = delegate.deleteTTDT(vo);
                //insert history
                UserHistoryVO userHisVO = new UserHistoryVO();
                userHisVO.setNguoi_tdoi(new Long(nUserID));
                userHisVO.setNoi_dung_thaydoi("Xoa ma KeHoach " + idAdd);
                userHisVO.setNsd_id(idAdd);
                delegate.insertHistoryUser(userHisVO);
                f.reset(mapping, request);
                if (idAdd != 0) {
                    errMess = "qlykehoach.delete.succ";
                } else
                    errMess = "qlykehoach.delete.error";
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
            QuanLyKeHoachQuyDelegate delegate =
                new QuanLyKeHoachQuyDelegate(conn);
            QuanLyKeHoachQuyVO vo = delegate.getTpKeHoachObject(guid);
            if (vo == null) {
                saveMessage(request,
                            new TPCPException("qlykehoach.list.notfount"));
                return mapping.findForward(FAILURE);
            }
            BigDecimal bTongKLTP;
            bTongKLTP = new BigDecimal(vo.getTong_klph());
            BigDecimal giatri ;
            if("VND".equals(vo.getLoai_tien())){
             giatri = new BigDecimal(1000000000);
            }else{
              giatri = new BigDecimal(1000000);
            }
            bTongKLTP = bTongKLTP.divide(giatri);
            String khoiLuong = String.valueOf(bTongKLTP);
            vo.setTong_klph(StringUtil.convertNumberToString(khoiLuong,
                                                             "VND"));
            vo.setTong_chi_tiet(StringUtil.convertNumberToString(khoiLuong,
                                                                 "VND"));
            QuanLyKeHoachQuyForm f = (QuanLyKeHoachQuyForm)form;
            DMKyHanDelegate delegateKH = new DMKyHanDelegate(conn);
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("LOAI_TPCP", "TRAI_PHIEU");
            List listDMKH = null;
            listDMKH = (List)delegateKH.getDMKyHan(map);
            request.setAttribute("listDMKH", listDMKH);


            KHPHChiTietVO voCT = new KHPHChiTietVO();
            List QLKHChiTiet = new ArrayList();

            QLKHChiTiet = (List)delegate.getListKHPHCT(voCT, guid);
            Collection result = new ArrayList();
            Iterator ito = QLKHChiTiet.iterator();
            Iterator itoKH = listDMKH.iterator();
            KHPHChiTietVO voCT1 = null;
            DMKyHanVO voKH1 = null;
            while (ito.hasNext()) {
                voCT1 = (KHPHChiTietVO)ito.next();
                itoKH = listDMKH.iterator();
                while (itoKH.hasNext()) {
                    voKH1 = (DMKyHanVO)itoKH.next();
                    if (voCT1.getKy_han().equals(voKH1.getGuid())) {
                        voKH1.setId_ky_han(voCT1.getKy_han());
                        //
                        BigDecimal bSoTien;
                        bSoTien = new BigDecimal(voCT1.getKlph());
                        bSoTien = bSoTien.divide(giatri);
                        String sSotien = String.valueOf(bSoTien);
                        voKH1.setSo_tien(StringUtil.convertNumberToString(sSotien,
                                                                          "VND"));
                        //
                        result.add(voKH1);
                    }

                }
            }
            f.setLisKH(result);
            BeanUtils.copyProperties(f, vo);
        } catch (Exception e) {
            throw e;
        } finally {
            close(conn);
        }
        return mapping.findForward(SUCCESS);
    }
    
    
  
    public static final String REPORT_DIRECTORY = "/report";
    public static final String strFontTimeRoman = "/font/times.ttf";

    
    public ActionForward printAction(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response) throws Exception {
        String reportName = "";
        Connection conn = null;
        InputStream reportStream = null;
        StringBuffer sbSubHTML = new StringBuffer();
        try {
            conn = getConnection();
            QuanLyKeHoachQuyForm f = (QuanLyKeHoachQuyForm)form;
            QuanLyKeHoachQuyDelegate  delegate= new QuanLyKeHoachQuyDelegate(conn);
            QuanLyKeHoachQuyVO vo = new QuanLyKeHoachQuyVO();
            String fileName="";
            if("VND".equals(f.getLoai_tien())){
              fileName="/ThongBaoKHQ";
                reportName = "/ThongBaoKHQ";
            }else{
              fileName="/ThongBaoKHQUSD";
                reportName = "/ThongBaoKHQUSD";
            }
                
  
            BeanUtils.copyProperties(vo, f);

            sbSubHTML.append("<input type=\"hidden\" name=\"tong_klph\" value=\"" +
                             f.getTong_klph() +
                             "\" id=\"tong_klph\"></input>");
            sbSubHTML.append("<input type=\"hidden\" name=\"thoi_gian_ph\" value=\"" +
                             f.getThoi_gian_ph() +
                             "\" id=\"thoi_gian_ph\"></input>");
            sbSubHTML.append("<input type=\"hidden\" name=\"guid\" value=\"" +
                             f.getGuid() +
                             "\" id=\"guid\"></input>");
            sbSubHTML.append("<input type=\"hidden\" name=\"nam_ph\" value=\"" +
                           f.getNam_ph() +
                           "\" id=\"nam_ph\"></input>");
            sbSubHTML.append("<input type=\"hidden\" name=\"loai_tien\" value=\"" +
                         f.getLoai_tien() +
                         "\" id=\"loai_tien\"></input>");
            
            ResultSet rsNSD = null;
            if("VND".equals(f.getLoai_tien())){
            rsNSD = delegate.getKHQuyRS(f.getGuid());
            }else{
              rsNSD = delegate.getKHQuyRSUSD(f.getGuid());
            }
            if (rsNSD != null) {
              JasperPrint jasperPrint = null;
              Map<String, Object> parameterMap =
                    new HashMap<String, Object>();
              String quy=null;  
              if("1".equals(f.getThoi_gian_ph())){
                    quy="I";
                    }
              if("2".equals(f.getThoi_gian_ph())){
                    quy="II";
                  }
              if("3".equals(f.getThoi_gian_ph())){
                    quy="III";
                  }
              if("4".equals(f.getThoi_gian_ph())){
                    quy="IV";
                  }
                parameterMap.put("pThoi_gian_ph",quy) ;
                parameterMap.put("pTong_klph",f.getTong_klph() );
                parameterMap.put("pGuid",f.getGuid() );
                parameterMap.put("pNam_ph",f.getNam_ph() );
                parameterMap.put("pLoai_tien",f.getLoai_tien() );
                if("VND".equals(f.getLoai_tien())){
                  parameterMap.put("pGia_tri","tỷ đồng" );
                }else{
                  parameterMap.put("pGia_tri","triệu đô" );
                }
                reportStream =
                        getServlet().getServletConfig().getServletContext().getResourceAsStream(REPORT_DIRECTORY +
                                                                                                reportName +
                                                                                                ".jasper");
                JRDataSource jrDS = new JRResultSetDataSource(rsNSD);
                jasperPrint =
                        JasperFillManager.fillReport(reportStream, parameterMap,conn);

                ReportUtility rpUtilites = new ReportUtility();

                String strTypePrintAction =
                    request.getParameter(AppConstants.REQUEST_ACTION) == null ?
                    "" :
                    request.getParameter(AppConstants.REQUEST_ACTION).toString();
                String strActionName = "PrintKQQuanLyKHQuyAction.do";
                String strParameter = "";
                String strPathFont =
                    getServlet().getServletContext().getContextPath() +
                    REPORT_DIRECTORY + strFontTimeRoman;

                rpUtilites.exportReport(jasperPrint, strTypePrintAction,
                                        response, fileName, strPathFont,
                                        strActionName, sbSubHTML.toString(),
                                        strParameter);
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
