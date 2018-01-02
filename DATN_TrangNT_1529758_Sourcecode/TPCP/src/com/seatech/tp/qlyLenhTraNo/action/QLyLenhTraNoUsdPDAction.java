package com.seatech.tp.qlyLenhTraNo.action;

import com.seatech.framework.AppConstants;
import com.seatech.framework.common.jsp.PagingBean;
import com.seatech.framework.exception.TPCPException;
import com.seatech.framework.strustx.AppAction;
import com.seatech.framework.utils.StringUtil;
import com.seatech.tp.dmdonvitt.action.DMDonViTTDelegate;
import com.seatech.tp.qlyLenhTraNo.form.LenhTraNoForm;
import com.seatech.tp.ttlaigoc.vo.TPLenhTriTraNoVo;
import com.seatech.tp.user.UserHistoryVO;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class QLyLenhTraNoUsdPDAction extends AppAction {
    private static String SUCCESS = "success";
    private static String FAILURE = "failure";

    public QLyLenhTraNoUsdPDAction() {
        super();
    }

    public ActionForward list(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        Connection conn = null;
        try {
            LenhTraNoForm f = (LenhTraNoForm)form;
            f.reset(mapping, request);
            if(f.getTrang_thai()==null|| f.getTrang_thai().equals(""))            
                f.setTrang_thai("01");
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
            LenhTraNoForm f = (LenhTraNoForm)form;
            TPLenhTriTraNoVo vo = new TPLenhTriTraNoVo();
            BeanUtils.copyProperties(vo, f);
            int phantrang = (AppConstants.APP_NUMBER_ROW_ON_PAGE);
            // khai bao bien phan trang.
            String page = f.getPageNumber();
            if (page == null)
                page = "1";
            Integer currentPage = new Integer(page);
            Integer numberRowOnPage = phantrang;
            Integer totalCount[] = new Integer[1];

            Collection listLenhTriTraNo = new ArrayList();
            QLyLenhTraNoDelegate delegate = new QLyLenhTraNoDelegate(conn);
            listLenhTriTraNo =
                    delegate.getListLenhTriTraNoPDUSDPaging(vo, currentPage, numberRowOnPage,
                                               totalCount);
            List LenhTriTraNo= new ArrayList();
            Iterator itr= listLenhTriTraNo.iterator();
            TPLenhTriTraNoVo vocheck = null;
            while(itr.hasNext()){
              vocheck =(TPLenhTriTraNoVo) itr.next();
              vocheck.setTong_tien(StringUtil.convertNumberToString(vocheck.getTong_tien(), "VND"));
              
            }
          
            PagingBean pagingBean = new PagingBean();
            pagingBean.setCurrentPage(currentPage);
            pagingBean.setNumberOfRow((totalCount[0] == null) ? 0 :
                                      totalCount[0].intValue());
            pagingBean.setRowOnPage(numberRowOnPage);
            request.setAttribute("PAGE_KEY", pagingBean);
            request.setAttribute("listLenhTriTraNo", listLenhTriTraNo);
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
            String idLenh = request.getParameter("longid").trim();
            LenhTraNoForm f = (LenhTraNoForm)form;
            //
            TPLenhTriTraNoVo vo = new TPLenhTriTraNoVo();
            QLyLenhTraNoDelegate delegate = new QLyLenhTraNoDelegate(conn);
            vo = delegate.getListLenhTriTraNoCT(idLenh);
            BeanUtils.copyProperties(f, vo);
          DMDonViTTDelegate dMDonViTTDelegate =new DMDonViTTDelegate(conn);
          Collection listDonViTT=dMDonViTTDelegate.getListDmDonViTTAll();
          request.setAttribute("listDonViTT", listDonViTT);
        } catch (Exception e) {
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
      String errMess = "";
      try {
          conn = getConnection();
          LenhTraNoForm f = (LenhTraNoForm)form;
          TPLenhTriTraNoVo vo = new TPLenhTriTraNoVo();
          BeanUtils.copyProperties(vo, f);
          QLyLenhTraNoDelegate delegate = new QLyLenhTraNoDelegate(conn);
          long idAdd = delegate.update(vo);
          //insert history
          HttpSession session = request.getSession();
          String nUserID =session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString();
          UserHistoryVO userHisVO = new UserHistoryVO();
          userHisVO.setNguoi_tdoi(new Long(nUserID));
          userHisVO.setNoi_dung_thaydoi("Phê duyệt Lệnh tri trả nợ ngoại tệ " + idAdd);
          userHisVO.setNsd_id(idAdd);
          delegate.insertHistoryUser(userHisVO);
          f.reset(mapping, request);
          if (idAdd != 0) {
              errMess = "thanhtoanlaigoc.update.succ";
          } else
              errMess = "thanhtoanlaigoc.update.error";
          saveMessage(request, new TPCPException(errMess));
      } catch (Exception e) {
          throw e;
      } finally {
          close(conn);
      }
      return mapping.findForward(SUCCESS);
  }
}
