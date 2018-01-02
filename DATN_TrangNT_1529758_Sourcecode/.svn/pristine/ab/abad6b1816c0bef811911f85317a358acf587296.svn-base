package com.seatech.tp.nhomnsd.action;


import com.seatech.framework.AppConstants;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.strustx.AppAction;
import com.seatech.tp.dmkb.DMKBacDAO;
import com.seatech.tp.dmkb.DMKBacVO;
import com.seatech.tp.nhomnsd.NhomNSDDAO;
import com.seatech.tp.nhomnsd.PhanNhomDAO;
import com.seatech.tp.nhomnsd.PhanNhomVO;
import com.seatech.tp.nhomnsd.form.PhanNhomForm;
import com.seatech.tp.user.UserDAO;
import com.seatech.tp.user.UserHistoryDAO;
import com.seatech.tp.user.UserHistoryVO;

import java.sql.Connection;

import java.util.Collection;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class PhanNhomAction extends AppAction {
    public ActionForward executeAction(ActionMapping mapping, ActionForm form,
                                       HttpServletRequest request,
                                       HttpServletResponse response) throws Exception {
        Connection conn = null;
        String strWhere = "";
        Vector vParam = null;
        PhanNhomForm f = null;
        Collection colNSDNgoaiNhom = null;
        Collection colNSDThuocNhom = null;
        String quyen = null;
        String strKBId = "";
        int iCheck = 0;
        try {
//            if (!checkPermissionOnFunction(request,
//                                           "SYS.QLY_NHOM.PHAN_NHOM")) {
//                return mapping.findForward(AppConstants.MAPING_NO_RIGHT);
//            }
            conn = getConnection(request);
            f = (PhanNhomForm)form;
            //lay ra quyen su dung
            HttpSession session = request.getSession();
            quyen =
                    (session.getAttribute(AppConstants.APP_ROLE_LIST_SESSION).toString());
            //Lay ra nhom NSD fill to select box
            NhomNSDDAO nhomDAO = new NhomNSDDAO(conn);
 
            Collection colNhomNSD = nhomDAO.getNhomNSDList(strWhere, vParam);

            UserDAO uDAO = new UserDAO(conn);
            if (f.getNhom_id() != null && iCheck == 0) {
                //Lay ra danh sach NSD ngoai nhom
//                strWhere = "  a.kb_id = ? ";
                strWhere += " a.trang_thai = 01 ";

                strWhere +=
                        "and a.id not in ( select nsd_id from tp_nsd_nhom where nhom_id = ? )";
                vParam = new Vector();
//                vParam.add(new Parameter(f.getKb_id(), true));
                vParam.add(new Parameter(f.getNhom_id(), true));

                colNSDNgoaiNhom = uDAO.getUserList(strWhere, vParam);

                //Lay ra danh sach NSD thuoc nhom
                strWhere = "";

                strWhere += " a.trang_thai = 01 ";
                strWhere +=
                        "and a.id in ( select nsd_id from tp_nsd_nhom where nhom_id = ? )";
                vParam = new Vector();
//                vParam.add(new Parameter(f.getKb_id(), true));
                vParam.add(new Parameter(f.getNhom_id(), true));
                colNSDThuocNhom = uDAO.getUserList(strWhere, vParam);
            }
            //Set attribute
            request.setAttribute("colNhomNSD", colNhomNSD);
            request.setAttribute("colNSDNgoaiNhom", colNSDNgoaiNhom);
            request.setAttribute("colNSDThuocNhom", colNSDThuocNhom);
            resetToken(request);
            saveToken(request);
        } catch (Exception e) {
            throw e;
        } finally {
            close(conn);
        }
        return mapping.findForward(AppConstants.SUCCESS);
    }

    public ActionForward addExc(ActionMapping mapping, ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
        Connection conn = null;
        String strNhanVienID = null;
        String strCurrentUserID = null;

        try {           
            HttpSession session = request.getSession();
            if (session == null)
                return mapping.findForward("login");
            //Lay phan nhom tren session
            strCurrentUserID =
                    session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString();
            String strAddNSD = request.getParameter("arrAddF");
            String strRemoveNSD = request.getParameter("arrRemoveF");
            Object[] arrAddNSD = strAddNSD.split(",~");
            Object[] arrRemoveNSD = strRemoveNSD.split(",~");
            PhanNhomForm f = (PhanNhomForm)form;
            conn = getConnection(request);
            PhanNhomDAO phannhomDAO = new PhanNhomDAO(conn);
            //Them NSD vao nhom
            PhanNhomVO phanNhomVO = null;
            UserHistoryVO userHisVO = null;
            UserHistoryDAO userHisDAO = new UserHistoryDAO(conn);
            for (int i = 0; i < arrAddNSD.length; i++) {
                if (arrAddNSD[i] == null)
                    continue;
                strNhanVienID = arrAddNSD[i].toString();
                if ("".equalsIgnoreCase(strNhanVienID.trim()))
                    continue;
                //Build VO
                phanNhomVO = new PhanNhomVO();
                phanNhomVO.setNsd_id(new Long(strNhanVienID));
                phanNhomVO.setNhom_id(new Long(f.getNhom_id()));
                phanNhomVO.setCreated_by(new Long(strCurrentUserID));

                phannhomDAO.insertNsd_nhom(phanNhomVO);
                // Luu lich su thay doi
                String strNoiDung = "Them NSD vao nhom id = " + f.getNhom_id();
                userHisVO = new UserHistoryVO();
                userHisVO.setNguoi_tdoi(new Long(session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString()));
                userHisVO.setNoi_dung_thaydoi(strNoiDung);
                userHisVO.setNsd_id(new Long(strNhanVienID));
                userHisDAO.insert(userHisVO);

            }
            //Xoa NSD khoi nhom
            for (int i = 0; i < arrRemoveNSD.length; i++) {
                if (arrRemoveNSD[i] == null)
                    continue;
                strNhanVienID = arrRemoveNSD[i].toString();
                if ("".equalsIgnoreCase(strNhanVienID.trim()))
                    continue;
                phannhomDAO.delete(new Long(strNhanVienID),
                                   new Long(f.getNhom_id()));

                // Luu lich su thay doi
                String strNoiDung = "Xoa NSD khoi nhom id = " + f.getNhom_id();
                userHisVO = new UserHistoryVO();
                userHisVO.setNguoi_tdoi(new Long(session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString()));
                userHisVO.setNoi_dung_thaydoi(strNoiDung);
                userHisVO.setNsd_id(new Long(strNhanVienID));
                userHisDAO.insert(userHisVO);
            }
            saveVisitLog(conn, session, "SYS.QLY_NHOM.PHAN_NHOM",
                         "Phan nhom cho NSD");

            conn.commit();
           
        } catch (Exception ex) {
            throw new Exception("PhanNhomAction: " + ex);
        } finally {
            close(conn);
        }
        return executeAction(mapping, form, request, response);
    }

}
