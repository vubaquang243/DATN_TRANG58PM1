package com.seatech.framework.strustx;


import com.google.gson.JsonObject;

import com.seatech.framework.AppConstants;
import com.seatech.framework.AppKeys;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.exception.TPCPException;
import com.seatech.framework.utils.TPCPUtils;
import com.seatech.tp.chucnang.ChucNangVO;
import com.seatech.tp.lstruycap.LSuTruyCapDAO;
import com.seatech.tp.thamso.ThamSoHThong;
import com.seatech.tp.user.UserDAO;
import com.seatech.tp.user.UserVO;

import com.thoughtworks.xstream.XStream;

import java.io.PrintWriter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import javax.sql.DataSource;

import oracle.jdbc.OracleConnection;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;


public class AppAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            String strMethodName = mapping.getParameter();

            if (!"login".equalsIgnoreCase(strMethodName)) { // &&
                // !"logout".equalsIgnoreCase(strMethodName)) {
                HttpSession ss = request.getSession();
                if (ss.getAttribute(AppConstants.APP_USER_ID_SESSION) == null) {
                    return mapping.findForward("login");
                }
                UserVO vo = getUserInfo(request.getSession().getAttribute(AppConstants.APP_USER_ID_SESSION).toString());

                String strCurSessionID = request.getSession().getId();
                if (strCurSessionID != null) {
                    if (!strCurSessionID.equalsIgnoreCase(vo.getSession_id())) {
                        saveError(request, TPCPException.createException("TPCP-0002", vo.getMa_nsd(), vo.getIp_truycap()));
                        request.getSession().invalidate();
                        String accept = request.getHeader("Accept");
                        if (accept.indexOf(AppConstants.CONTENT_TYPE_JSON) != -1) {
                            response.setContentType(AppConstants.CONTENT_TYPE_JSON);
                            JsonObject strJson = new JsonObject();
                            strJson.addProperty("logout", true);
                            strJson.addProperty("ma_nsd", vo.getMa_nsd());
                            strJson.addProperty("ip_truycap", vo.getIp_truycap());
                            PrintWriter out = response.getWriter();
                            out.println(strJson.toString());
                            out.flush();
                            out.close();
                            return null;
                        } else {
                            return mapping.findForward("login");
                        }
                    }
                } else {
                    return mapping.findForward("login");
                }
            }
            if (strMethodName != null) {
                //check quyen
//                if (!checkPermissionOnFunction(request, mapping.getPath(), strMethodName)) {
//                    return mapping.findForward("errorQuyen");
//                }
                request.setAttribute("actionName", strMethodName);
                if (strMethodName.equalsIgnoreCase("list")) {
                    return list(mapping, form, request, response);
                } else if (strMethodName.equalsIgnoreCase("add")) {
                    this.saveToken(request);
                    return add(mapping, form, request, response);
                } else if (strMethodName.equalsIgnoreCase("addExc")) {
                    if (this.isTokenValid(request)) {
                        resetToken(request);
                    } else if (!this.isTokenValid(request)) {
                        System.out.println("Page resubmit");
                        return mapping.findForward("success");
                    }
                    return addExc(mapping, form, request, response);
                } else if (strMethodName.equalsIgnoreCase("update")) {
                    this.saveToken(request);
                    return update(mapping, form, request, response);
                } else if (strMethodName.equalsIgnoreCase("updateExc")) {
                    if (this.isTokenValid(request)) {
                        resetToken(request);
                    } else if (!this.isTokenValid(request)) {
                        System.out.println("Page resubmit");
                        return mapping.findForward("success");
                    }
                    return updateExc(mapping, form, request, response);
                } else if (strMethodName.equalsIgnoreCase("search")) {
                    return search(mapping, form, request, response);
                } else if (strMethodName.equalsIgnoreCase("delete")) {
                    return delete(mapping, form, request, response);
                } else if (strMethodName.equalsIgnoreCase("view")) {
                    this.saveToken(request);
                    return view(mapping, form, request, response);
                } else if (strMethodName.equalsIgnoreCase("print")) {
                    if (this.isTokenValid(request)) {
                        resetToken(request);
                    }
                    return printAction(mapping, form, request, response);
                } else if (strMethodName.equalsIgnoreCase("trinh")) {
                    if (this.isTokenValid(request)) {
                        resetToken(request);
                    }
                    return trinhAction(mapping, form, request, response);
                } else if (strMethodName.equalsIgnoreCase("pheduyet")) {
                    if (this.isTokenValid(request)) {
                        resetToken(request);
                    }
                    return pheDuyetAction(mapping, form, request, response);
                } else if (strMethodName.equalsIgnoreCase("tuchoi")) {
                    if (this.isTokenValid(request)) {
                        resetToken(request);
                    }
                    return tuchoiAction(mapping, form, request, response);
                } else if (strMethodName.equalsIgnoreCase("viewUpload")) {
                    if (this.isTokenValid(request)) {
                        resetToken(request);
                    } else if (!this.isTokenValid(request)) {
                        System.out.println("Page resubmit");
                        return mapping.findForward("failure");
                    }
                    return viewUploadAction(mapping, form, request, response);
                } else {
                    return executeAction(mapping, form, request, response);
                }
            } else {
                return executeAction(mapping, form, request, response);
            }
        } catch (TPCPException ex) {
            //            ex.printStackTrace();
            ServletContext context = servlet.getServletContext();
            HashMap errorMap = (HashMap)context.getAttribute(AppKeys.ERROR_LIST_APPLICATION_KEY);
            String msgError = "";
            if (ex.getMessage() != null) {
                msgError = ex.getMessage();
            } else {
                msgError = ex.getMessage(errorMap);
            }
            System.err.println(msgError);
            saveError(request, ex);
            return mapping.findForward("failure");
        } catch (Exception ex) {
            throw ex;
        }
    }

    protected ActionForward executeAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        return null;
    }

    public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return null;
    }

    public ActionForward trinhAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return null;
    }

    public ActionForward tuchoiAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return null;
    }

    public ActionForward viewUploadAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return null;
    }

    public ActionForward pheDuyetAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return null;
    }

    public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return null;
    }

    public ActionForward addExc(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return null;
    }

    public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return null;
    }

    public ActionForward updateExc(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return null;
    }

    public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return null;
    }

    public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return null;
    }


    protected ActionForward printAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        return null;
    }

    protected Connection getConnection() throws Exception {
        ServletContext context = servlet.getServletContext();
        DataSource ds = (DataSource)context.getAttribute(AppKeys.DATASOURCE_APPLICATION_KEY);
        Connection conn = null;
        conn = ds.getConnection();
        conn.setAutoCommit(false);
        //Set quyen cho user cua ung dung
        TPCPUtils tpcpUtils = new TPCPUtils(conn);
        tpcpUtils.grantAccess();

        return conn;
    }

    protected Connection getConnection(HttpServletRequest request) throws Exception {
        ServletContext context = servlet.getServletContext();
        DataSource ds = (DataSource)context.getAttribute(AppKeys.DATASOURCE_APPLICATION_KEY);
        Connection conn = null;
        conn = ds.getConnection();
        conn.setAutoCommit(false);
        //Set quyen cho user cua ung dung
        TPCPUtils tpcpUtils = new TPCPUtils(conn);
        tpcpUtils.grantAccess();

        setEndToEndMetrics(request, conn);

        return conn;
    }

    protected void saveError(HttpServletRequest request, String key) {
        ActionErrors errors = new ActionErrors();
        errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(key));
        saveErrors(request, errors);
    }

    protected void saveError(HttpServletRequest request, String key, String value0) {
        ActionErrors errors = new ActionErrors();
        errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(key, value0));
        saveErrors(request, errors);
    }

    protected void saveError(HttpServletRequest request, String key, String value0, String value1) {
        ActionErrors errors = new ActionErrors();
        errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(key, value0, value1));
        saveErrors(request, errors);
    }

    protected void saveError(HttpServletRequest request, String key, String value0, String value1, String value2) {
        ActionErrors errors = new ActionErrors();
        errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(key, value0, value1, value2));
        saveErrors(request, errors);
    }

    protected void saveError(HttpServletRequest request, String key, String value0, String value1, String value2, String value3) {
        ActionErrors errors = new ActionErrors();
        errors.add(ActionErrors.GLOBAL_ERROR, new ActionError(key, value0, value1, value2, value3));
        saveErrors(request, errors);
    }

    protected void saveError(HttpServletRequest request, TPCPException ex) {
        ServletContext context = servlet.getServletContext();
        HashMap errorMap = (HashMap)context.getAttribute(AppKeys.ERROR_LIST_APPLICATION_KEY);
        String msgError = ex.getMessage(errorMap);
        ActionErrors errors = new ActionErrors();
        errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("errors", msgError));
        saveErrors(request, errors);

    }

    protected void saveError2(HttpServletRequest request, TPCPException ex) {
        ServletContext context = servlet.getServletContext();
        HashMap errorMap = (HashMap)context.getAttribute(AppKeys.ERROR_LIST_APPLICATION_KEY);
        String strErrorHeader = ex.getMoTaLoi(errorMap).replace(":", "").replace("{0}", "").replace("{1}", "").replace("{2}", "");
        String strErrorDetail = ex.getMessage(errorMap);

        ActionErrors errors = new ActionErrors();
        errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("errors", strErrorHeader, strErrorDetail));
        saveErrors(request, errors);
    }

    protected void close(Connection conn) {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conn = null;
        }
    }
    // close ResultSet

    protected void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                rs = null;
            }

        }
    }

    // close PreparedStatement

    protected void close(PreparedStatement pstmt) {
        if (pstmt != null) {
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                pstmt = null;
            }

        }
    }

    // close Statement

    protected void close(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                stmt = null;
            }
        }
    }

    protected String populateBeanToXml(Object vo, String strValueObject, String rootElement) throws Exception {

        XStream xstream = new XStream();

        xstream.alias(rootElement, Class.forName(strValueObject));

        String xml = xstream.toXML(vo);

        return xml;
    }

    /**
     * @see: Kiem tra quyen cua User tren action
     * */
    protected boolean checkPermissionOnFunction(HttpServletRequest request, String strAction, String strMethodName) throws Exception {
        HttpSession session = request.getSession();

        if ("".equals(strAction))
            return false;
        strAction = strAction.substring(1) + ".do";
        if ("loginAction.do".equals(strAction) || "logoutAction.do".equals(strAction) || "QuanLyNSDExecuteAction.do".equals(strAction))
            return true;
        if ("view".equals(strMethodName) || "trinh".equals(strMethodName) || "print".equals(strMethodName) || "pheduyet".equals(strMethodName) || "tuchoi".equals(strMethodName) ||
            "viewUpload".equals(strMethodName) || "addExc".equals(strMethodName) || "updateExc".equals(strMethodName) || "search".equals(strMethodName))
            return true;
        Collection colCNang = (Collection)session.getAttribute(AppConstants.APP_CHUC_NANG_LIST_SESSION);
        if (null == colCNang)
            return false;
        Iterator iter = colCNang.iterator();
        ChucNangVO cnangVO = null;
        while (iter.hasNext()) {
            cnangVO = (ChucNangVO)iter.next();
            if (strAction.equalsIgnoreCase(cnangVO.getTen_action())) {
                return true;
            }
        }
        return false;
    }
//  protected boolean checkPermissionOnFunction(HttpServletRequest request, String strAction) throws Exception {
//      HttpSession session = request.getSession();
//
//      if ("".equals(strAction))
//          return false;
//      strAction = strAction.substring(1) + ".do";
//      if ("loginAction.do".equals(strAction) || "logoutAction.do".equals(strAction))
//          return true;      
//      Collection colCNang = (Collection)session.getAttribute(AppConstants.APP_CHUC_NANG_LIST_SESSION);
//      if (null == colCNang)
//          return false;
//      Iterator iter = colCNang.iterator();
//      ChucNangVO cnangVO = null;
//      while (iter.hasNext()) {
//          cnangVO = (ChucNangVO)iter.next();
//          if (strAction.equalsIgnoreCase(cnangVO.getTen_action())) {
//              return true;
//          }
//      }
//      return false;
//  }

    /**
     * @see: Ghi log truy cap doi voi moi action
     * */
    //    protected void saveVisitLog(Connection conn, String strUserID,
    //                                String strAction, String strIP,
    //                                String strMoTa, String strOSUser, String strComputerName) throws Exception {
    //        LSuTruyCapDAO lSuTruyCapDAO = new LSuTruyCapDAO(conn);
    //        lSuTruyCapDAO.saveVisitLog(strUserID, strAction, strIP, strMoTa, strOSUser, strComputerName);
    //    }
    protected void saveVisitLog(Connection conn, HttpSession session, String strAction, String strMoTa) throws Exception {
        LSuTruyCapDAO lSuTruyCapDAO = new LSuTruyCapDAO(conn);
        String strUserID = session.getAttribute(AppConstants.APP_USER_ID_SESSION).toString();
        String strIP = session.getAttribute(AppConstants.APP_IP_SESSION).toString();
        String strOSUser = session.getAttribute(AppConstants.APP_OS_USER_SESSION).toString();
        String strComputerName = session.getAttribute(AppConstants.APP_COMPUTER_NAME_SESSION).toString();
        lSuTruyCapDAO.saveVisitLog(strUserID, strAction, strIP, strMoTa, strOSUser, strComputerName);
    }

    /**
     * @see: Lay tham so he thong
     * @param:
     * @return: Tra ve danh sach tham so he thong
     * */
    protected Collection getThamSoHThong(HttpSession session) {
        return (Collection)session.getAttribute(AppConstants.APP_THAM_SO_SESSION);
    }
    //    protected Collection getThamSoHThong() {
    //        ServletContext context = servlet.getServletContext();
    //        return (Collection)context.getAttribute(AppKeys.PARAM_LIST_APPLICATION_KEY);
    //    }

    /**
     * @see: Lay tham so he thong
     * @param: truyen vao ten TSo
     * @return: Tra ve gia tri tham so he thong
     * */
    protected String getThamSoHThong(String strTenTSo, HttpSession session) {
        ThamSoHThong tso = new ThamSoHThong();
        Collection collThamSoList = (Collection)session.getAttribute(AppConstants.APP_THAM_SO_SESSION);
        return tso.getThamSoHThong(strTenTSo, collThamSoList);
    }
    //    protected String getThamSoHThong(String strTenTSo) {
    //        ServletContext context = servlet.getServletContext();
    //        ThamSoHThong tso = new ThamSoHThong();
    //        Collection collThamSoList =
    //            (Collection)context.getAttribute(AppKeys.PARAM_LIST_APPLICATION_KEY);
    //        return tso.getThamSoHThong(strTenTSo, collThamSoList);
    //    }

    /**
     * @see: Lay tham so he thong rieng cua moi kho bac
     * @param: Truyen vao ten TSo va ID cua KB (ID cua bang tp_DM_HTKB)
     * @return: Tra ve gia tri TSo he thong
     * */
    protected String getThamSoHThong(String strTenTSo, Long nKBacID, HttpSession session) {

        ThamSoHThong tso = new ThamSoHThong();
        Collection collThamSoList = (Collection)session.getAttribute(AppConstants.APP_THAM_SO_SESSION);
        return tso.getThamSoHThong(strTenTSo, nKBacID, collThamSoList);
    }
    //    protected String getThamSoHThong(String strTenTSo, Long nKBacID) {
    //        ServletContext context = servlet.getServletContext();
    //        ThamSoHThong tso = new ThamSoHThong();
    //        Collection collThamSoList =
    //            (Collection)context.getAttribute(AppKeys.PARAM_LIST_APPLICATION_KEY);
    //        return tso.getThamSoHThong(strTenTSo, nKBacID, collThamSoList);
    //    }

    /**
     * @see: Lay tham so he thong rieng cua moi kho bac
     * @param: Truyen vao ten TSo va ID cua KB (ID cua bang tp_DM_HTKB)
     * @return: Tra ve gia tri TSo he thong
     * */
    protected UserVO getUserInfo(String strUserID) throws Exception {
        Connection conn = null;
        try {
            conn = getConnection();
            UserDAO ssDAO = new UserDAO(conn);
            Vector vParams = new Vector();
            vParams.add(new Parameter(strUserID, true));
            UserVO vo = ssDAO.getUser("a.id = ?", vParams);
            return vo;
        } finally {
            close(conn);
        }
    }

    protected List getDSachCNang(HttpServletRequest request, String strAction) {
        List lstCNang = null;
        if ("".equals(strAction))
            return lstCNang;
        HttpSession session = request.getSession();
        Collection colCNang = (Collection)session.getAttribute(AppConstants.APP_CHUC_NANG_LIST_SESSION);
        Iterator iter = colCNang.iterator();
        ChucNangVO cnangVO = null;
        //Lay ID chuc nang cha
        boolean checkExist = false;
        while (iter.hasNext()) {
            cnangVO = (ChucNangVO)iter.next();
            if (strAction.equalsIgnoreCase(cnangVO.getKy_hieu_cnang())) {
                checkExist = true;
                break;
            }
        }
        if (!checkExist)
            return lstCNang;
        long nCNangChaID = cnangVO.getId().longValue();
        //Lay cac chuc nang con
        iter = colCNang.iterator();
        lstCNang = new ArrayList();
        while (iter.hasNext()) {
            cnangVO = (ChucNangVO)iter.next();
            if (cnangVO.getCnang_cha() == null)
                continue;
            if (nCNangChaID == cnangVO.getCnang_cha().longValue()) {
                lstCNang.add(cnangVO);
            }
        }
        return lstCNang;
    }

    public void setEndToEndMetrics(HttpServletRequest request, Connection conn) throws Exception {
        String[] metrics = new String[OracleConnection.END_TO_END_STATE_INDEX_MAX];
        //      metrics[OracleConnection.END_TO_END_ACTION_INDEX] = "ManhNVACtion";
        metrics[OracleConnection.END_TO_END_CLIENTID_INDEX] = request.getSession().getAttribute(AppConstants.APP_USER_ID_SESSION).toString();
        metrics[OracleConnection.END_TO_END_ECID_INDEX] = request.getSession().getAttribute(AppConstants.APP_IP_SESSION).toString();

        ((OracleConnection)conn).setEndToEndMetrics(metrics, (short)0);

    }

    public ActionForward search(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return null;
    }

    protected void saveMessage(HttpServletRequest request, TPCPException ex) {
        String msgError = ex.getMessage();
        ActionMessages errorMess = new ActionMessages();
        errorMess.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage("messages", msgError));
        saveMessages(request, errorMess);

    }
}
