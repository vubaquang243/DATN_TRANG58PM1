
package com.seatech.tp.qlykqphtinphieu.action;

import com.seatech.framework.AppConstants;
import com.seatech.framework.common.jsp.PagingBean;
import com.seatech.framework.exception.TPCPException;
import com.seatech.framework.strustx.AppAction;
import com.seatech.framework.utils.StringUtil;
import com.seatech.tp.dmkyhan.action.DMKyHanDelegate;
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
import com.seatech.tp.ttindthau.action.QLyTTinDauThauDelegate;
import com.seatech.tp.ttindthau.vo.ThongTinDauThauVO;

import com.seatech.tp.user.UserHistoryVO;

import java.io.ByteArrayInputStream;

import java.math.BigDecimal;

import java.sql.Connection;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

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

import com.seatech.framework.strustx.AppAction;
import com.seatech.tp.qlytinphieu.action.HDBanTinPhieuDelegate;
import com.seatech.tp.qlytinphieu.form.HDBanTinPhieuForm;
import com.seatech.tp.qlytinphieu.vo.HDBanTinPhieuVo;
import com.seatech.tp.qlytp.action.QuanLyTPCPDelegate;

public class KQPHTinPhieuPheDuyetAction extends AppAction {
    private static String SUCCESS = "success";
    private static String FAILURE = "failure";

    public KQPHTinPhieuPheDuyetAction() {
        super();
    }

    public ActionForward list(ActionMapping mapping, ActionForm form,
                              HttpServletRequest request,
                              HttpServletResponse response) throws Exception {
        try {
            KQPHTinPhieuForm f = (KQPHTinPhieuForm)form;
            f.reset(mapping, request);
            if (f.getTrang_thai() == null || f.getTrang_thai().equals("")) {
                f.setTrang_thai("01");
            }
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
            KQPHTinPhieuForm f = (KQPHTinPhieuForm)form;
            int phantrang = (AppConstants.APP_NUMBER_ROW_ON_PAGE);
            // khai bao bien phan trang.
            String page = f.getPageNumber();
            if (page == null)
                page = "1";
            Integer currentPage = new Integer(page);
            Integer numberRowOnPage = phantrang;
            Integer totalCount[] = new Integer[1];
            KQPHTinPhieuVo vo = new KQPHTinPhieuVo();
            BeanUtils.copyProperties(vo, f);
            KQPHTinPhieuDelegate delegate = new KQPHTinPhieuDelegate(conn);
            List lstKQphathanh = null;
            lstKQphathanh =
                    (List)delegate.getListKQPHTinPhieuPheDuyetPagings(vo,
                                                                      currentPage,
                                                                      numberRowOnPage,
                                                                      totalCount);
            Iterator ito = lstKQphathanh.iterator();
            Collection resultKQPhathanh = new ArrayList();
            while (ito.hasNext()) {
                vo = (KQPHTinPhieuVo)ito.next();
                if (vo.getTong_klph() != null) {
                    vo.setTong_klph(StringUtil.convertNumberToString(vo.getTong_klph(),
                                                                     "VND"));
                }
                if (vo.getLs_binh_quan() != null) {
                    vo.setLs_binh_quan(StringUtil.convertNumberToString(vo.getLs_binh_quan(),
                                                                        "VND"));
                }
              if(vo.getTong_kl_trung_thau()!=null){
                vo.setTong_kl_trung_thau(StringUtil.convertNumberToString(vo.getTong_kl_trung_thau(),
                                                                    "VND"));
              }
                resultKQPhathanh.add(vo);
            }
            PagingBean pagingBean = new PagingBean();
            pagingBean.setCurrentPage(currentPage);
            pagingBean.setNumberOfRow((totalCount[0] == null) ? 0 :
                                      totalCount[0].intValue());
            pagingBean.setRowOnPage(numberRowOnPage);
            QuanLyTPCPDelegate deleg = new QuanLyTPCPDelegate(conn);
            List listTPCP = new ArrayList();
            listTPCP = (List)deleg.getAllListTPCP_TIN_PHIEU();
            request.setAttribute("listTinPhieu", listTPCP);
            request.setAttribute("PAGE_KEY", pagingBean);
            request.setAttribute("listKQphathanh", resultKQPhathanh);
        } catch (Exception e) {
            conn.rollback();
            throw e;
        } finally {
            close(conn);
        }
        return mapping.findForward(SUCCESS);
    }

}
