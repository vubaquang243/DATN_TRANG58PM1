package com.seatech.tp.tiente.action;

import com.seatech.framework.AppConstants;
import com.seatech.framework.strustx.AppAction;

import com.seatech.tp.tiente.form.TienTeForm;
import com.seatech.tp.tiente.vo.TienTeVo;

import com.seatech.tp.ttindthau.form.ThongTinDauThauForm;

import java.sql.Connection;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class TienTeAction extends AppAction {
    
    @Override
    public ActionForward list(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        
        Connection conn = null;
        try {
          conn = getConnection(request);
          TienTeDelegate delegate=new TienTeDelegate(conn);
          TienTeVo vo=new TienTeVo();
          TienTeForm f = (TienTeForm)form;
          BeanUtils.copyProperties(vo, f);
          
          int phantrang = (AppConstants.APP_NUMBER_ROW_ON_PAGE);
          // khai bao bien phan trang.
          String page = f.getPageNumber();
          if (page == null)
              page = "1";
          Integer currentPage = new Integer(page);
          Integer numberRowOnPage = phantrang;
          Integer totalCount[] = new Integer[1];
           
          Collection ketqua=delegate.getListTienTe(vo,currentPage, numberRowOnPage,
                                                     totalCount);
          request.setAttribute("ketqua", ketqua);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(conn);
        }
        return mapping.findForward(AppConstants.SUCCESS);
    }
//    public ActionForward delete(ActionMapping mapping, ActionForm form,
//                                HttpServletRequest request , HttpServletResponse response) throws Exception{
//          Connection conn = null;
//          try{
//              }catch(Exception ẽ)
//          
//        }
                                                                                                                
}
