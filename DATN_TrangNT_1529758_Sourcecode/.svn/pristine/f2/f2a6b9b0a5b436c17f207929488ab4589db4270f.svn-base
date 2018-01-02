package com.seatech.tp.qlytp.action;

import com.seatech.framework.AppConstants;
import com.seatech.framework.common.jsp.PagingBean;
import com.seatech.framework.exception.TPCPException;
import com.seatech.framework.strustx.AppAction;
import com.seatech.tp.banletraiphieutw.form.BanLeTraiPhieuTwForm;
import com.seatech.tp.dmkyhan.action.DMKyHanDelegate;
import com.seatech.tp.dmkyhan.vo.DMKyHanVO;
import com.seatech.tp.dmtraichu.action.DMTraiChuDelegate;
import com.seatech.tp.qlykehoach.action.QuanLyKeHoachDelegate;
import com.seatech.tp.qlykehoach.form.QuanLyKeHoachForm;
import com.seatech.tp.qlykehoach.vo.QuanLyKeHoachVo;
import com.seatech.tp.qlytp.form.QuanLyTPCPForm;
import com.seatech.tp.qlytp.vo.PhuongThucPhatHanhVO;
import com.seatech.tp.qlytp.vo.QuanLyTPCPVO;
import com.seatech.tp.ttindthau.vo.ThongTinDauThauVO;
import com.seatech.tp.user.UserHistoryVO;

import java.sql.Connection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.time.FastDateFormat;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class QuanLyTPCPAction extends AppAction {
    private static String SUCCESS = "success";
    private static String FAILURE = "failure";

    public ActionForward list(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        
      Connection conn = null;
      try {
          QuanLyTPCPForm f = (QuanLyTPCPForm)form;
          f.reset(mapping, request);
          return search(mapping, form, request, response);
      } catch (Exception e) {
          conn.rollback();
          throw e;
      } finally {
          close(conn);
      }
        
      
    }

    public ActionForward search(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
      //check quyen
      Connection conn = null;
      try {
          conn = getConnection(request);
          QuanLyTPCPForm f = (QuanLyTPCPForm)form;
          int phantrang = (AppConstants.APP_NUMBER_ROW_ON_PAGE);
          // khai bao bien phan trang.
          String page = f.getPageNumber();
          if (page == null)
              page = "1";
          Integer currentPage = new Integer(page);
          Integer numberRowOnPage = phantrang;
          Integer totalCount[] = new Integer[1];
          QuanLyTPCPVO vo = new QuanLyTPCPVO();
          BeanUtils.copyProperties(vo, f);
          QuanLyTPCPDelegate delegate = new QuanLyTPCPDelegate(conn);
          List listTPCP = null;
          listTPCP =
                  (List)delegate.getListTTDTPaging(vo, currentPage, numberRowOnPage,
                                                   totalCount);
          PagingBean pagingBean = new PagingBean();
          pagingBean.setCurrentPage(currentPage);
          pagingBean.setNumberOfRow((totalCount[0] == null) ? 0 :
                                    totalCount[0].intValue());
          pagingBean.setRowOnPage(numberRowOnPage);
          request.setAttribute("PAGE_KEY", pagingBean);
          request.setAttribute("lstTPCP", listTPCP);
          //get dm ky_han
          DMKyHanDelegate dmKyHanDelegate = new DMKyHanDelegate(conn);
          List lstKyHan = (List)dmKyHanDelegate.getDMKyHanAdd(null);
          request.setAttribute("lstKyHan", lstKyHan);
          //end dm ky han
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
            QuanLyTPCPForm f = (QuanLyTPCPForm)form;
            f.setLoai_tpcp("0");
            conn = getConnection(request);            
            DMTraiChuDelegate delegateTP = new DMTraiChuDelegate(conn);
            List listDviBanLe = new ArrayList();
            listDviBanLe = (List)delegateTP.getDMTraiChuBanLe();
            request.setAttribute("listDviBanLe", listDviBanLe);
            //get list ky han
            //
            DMKyHanDelegate dmKyHanDelegate = new DMKyHanDelegate(conn);
            List lstKyHan = (List)dmKyHanDelegate.getDMKyHanAdd(null);
            List lstKyHanTPCP = new ArrayList();
            List lstKyHanTPKB = new ArrayList();
            Iterator ito = lstKyHan.iterator();
            while(ito.hasNext()){
              DMKyHanVO vo = (DMKyHanVO)ito.next();
                if(vo.getLoai_tpcp().equalsIgnoreCase("TRAI_PHIEU")){
                  lstKyHanTPCP.add(vo);
                }else{
                  lstKyHanTPKB.add(vo);
                }
            }
            request.setAttribute("lstKyHanTPCP", lstKyHanTPCP);
            request.setAttribute("lstKyHanTPKB", lstKyHanTPKB);
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
        //        if (!checkPermissionOnFunction(request, "SYS.QLY_NSD.THEMMOI")) {
        //            return mapping.findForward("errorQuyen");
        //        }
        Connection conn = null;
        try {
            conn = getConnection(request);
            HttpSession session = request.getSession();
            String nUserID =
                session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString();
            QuanLyTPCPForm f = (QuanLyTPCPForm)form;
            QuanLyTPCPVO vo = new QuanLyTPCPVO();
            BeanUtils.copyProperties(vo, f);
            QuanLyTPCPDelegate delegate = new QuanLyTPCPDelegate(conn);
            QuanLyTPCPVO voTPCP = delegate.getDMTPCP(f.getMa_tp());
            if (voTPCP != null) {
              throw new TPCPException().createException("TPCP-0009",
                                                        f.getMa_tp());
            }
            String sDacBiet = "0";
            if (!f.getDac_biet().toString().trim().equals("")) {
                sDacBiet = "1";
            }
            vo.setDac_biet(sDacBiet);
            vo.setNguoi_tao(nUserID);
            vo.setTrang_thai("01");
            long idAdd = delegate.insertTPCP(vo);
            //insert history
            UserHistoryVO userHisVO = new UserHistoryVO();
            userHisVO.setNguoi_tdoi(new Long(nUserID));
            userHisVO.setNoi_dung_thaydoi("Them moi ma TPCP" + idAdd);
            userHisVO.setNsd_id(idAdd);
            delegate.insertHistoryUser(userHisVO);
            f.reset(mapping, request);
            saveMessage(request, new TPCPException("quanlytp.add.succ"));
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            close(conn);
        }
        return mapping.findForward(SUCCESS);
    }

    protected ActionForward executeAction(ActionMapping mapping,
                                          ActionForm form,
                                          HttpServletRequest request,
                                          HttpServletResponse response) throws Exception {
        Connection conn = null;
        try {
            QuanLyTPCPForm f = (QuanLyTPCPForm)form;
            f.reset(mapping, request);
            return list(mapping, form, request, response);
        } catch (Exception e) {
            throw e;
        } finally {
            close(conn);
        }
    }

    public ActionForward update(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
        Connection conn = null;
        try {
            conn = getConnection();
            String guid = request.getParameter("longid").trim();
            //check xem TPCP tồn tại?
            QuanLyTPCPDelegate delegate = new QuanLyTPCPDelegate(conn);
            QuanLyTPCPVO vo = delegate.getTTDTObject(guid);
            DMTraiChuDelegate delegateTP = new DMTraiChuDelegate(conn);
            List listDviBanLe = new ArrayList();
            listDviBanLe = (List)delegateTP.getDMTraiChuBanLe();
            request.setAttribute("listDviBanLe", listDviBanLe);
            //get list ky han
            DMKyHanDelegate dmKyHanDelegate = new DMKyHanDelegate(conn);
            List lstKyHan = (List)dmKyHanDelegate.getDMKyHanAdd(null);
            List lstKyHanTPCP = new ArrayList();
            List lstKyHanTPKB = new ArrayList();
            Iterator ito = lstKyHan.iterator();
            while(ito.hasNext()){
              DMKyHanVO vo2 = (DMKyHanVO)ito.next();
                if(vo2.getLoai_tpcp().equalsIgnoreCase("TRAI_PHIEU")){
                  lstKyHanTPCP.add(vo2);
                }else{
                  lstKyHanTPKB.add(vo2);
                }
            }
            request.setAttribute("lstKyHanTPCP", lstKyHanTPCP);
            request.setAttribute("lstKyHanTPKB", lstKyHanTPKB);
            if (vo == null) {
                saveMessage(request,
                            new TPCPException("quanlytp.list.notfount"));
                return mapping.findForward(FAILURE);
            }
            QuanLyTPCPForm f = (QuanLyTPCPForm)form;
            BeanUtils.copyProperties(f, vo);
            if (vo.getDac_biet().toString().trim().equals("1")) {
                f.setDac_biet("on");
            } else {
                f.setDac_biet("");
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
            QuanLyTPCPForm f = (QuanLyTPCPForm)form;
            QuanLyTPCPVO vo = new QuanLyTPCPVO();
            BeanUtils.copyProperties(vo, f);
            String sDacBiet = "0";
            if (!f.getDac_biet().toString().trim().equals("")) {
                sDacBiet = "1";
            }
            vo.setDac_biet(sDacBiet);
            vo.setNgay_sua_cuoi(getDate());
            vo.setNguoi_sua_cuoi(nUserID);
            QuanLyTPCPDelegate delegate = new QuanLyTPCPDelegate(conn);
            long idAdd = delegate.updateTPCP(vo);
            //insert history
            UserHistoryVO userHisVO = new UserHistoryVO();
            userHisVO.setNguoi_tdoi(new Long(nUserID));
            userHisVO.setNoi_dung_thaydoi("Cập nhật ma TPCP" + idAdd);
            userHisVO.setNsd_id(idAdd);
            delegate.insertHistoryUser(userHisVO);
            f.reset(mapping, request);
            saveMessage(request, new TPCPException("quanlytp.update.succ"));
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
        Connection conn = null;
        String errMess = "";
        try {
            conn = getConnection();
            HttpSession session = request.getSession();
            String nUserID =
                session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString();
            String guid = request.getParameter("longid").trim();
            //check xem TPCP tồn tại?
            QuanLyTPCPDelegate delegate = new QuanLyTPCPDelegate(conn);
            QuanLyTPCPVO vo = delegate.getTTDTObject(guid);
            if (vo == null) {
                saveMessage(request,
                            new TPCPException("quanlytp.list.notfount"));
                return mapping.findForward(FAILURE);
            } else {
                QuanLyTPCPForm f = (QuanLyTPCPForm)form;
                long idAdd = delegate.delete(vo);
                //insert history
                UserHistoryVO userHisVO = new UserHistoryVO();
                userHisVO.setNguoi_tdoi(new Long(nUserID));
                userHisVO.setNoi_dung_thaydoi("Xoa ma TPCP " + idAdd);
                userHisVO.setNsd_id(idAdd);
                delegate.insertHistoryUser(userHisVO);
                f.reset(mapping, request);
                if (idAdd != 0) {
                    errMess = "quanlytp.delete.succ";
                } else
                    errMess = "quanlytp.delete.error";
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
}
