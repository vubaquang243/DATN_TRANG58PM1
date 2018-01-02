package com.seatech.tp.user.action;


import com.seatech.framework.AppConstants;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.exception.TPCPException;
import com.seatech.framework.strustx.AppAction;
import com.seatech.tp.chucnang.ChucNangDAO;
import com.seatech.tp.dmkb.DMKBacDAO;
import com.seatech.tp.dmkb.DMKBacVO;
import com.seatech.tp.nhomnsd.NhomNSDDAO;
import com.seatech.tp.nhomnsd.NhomNSDVO;
import com.seatech.tp.proxy.ad.ActiveDirectory;
import com.seatech.tp.thamso.ThamSoHThongDAO;
import com.seatech.tp.user.UserDAO;
import com.seatech.tp.user.UserVO;
import com.seatech.tp.user.form.UserForm;

import java.sql.Connection;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class LoginAction extends AppAction {
    private static String SUCCESS = "success";
    private static String FAILURE = "failure";
    private static String CONFIRM = "confirm";

    protected ActionForward executeAction(ActionMapping mapping,
                                          ActionForm form,
                                          HttpServletRequest request,
                                          HttpServletResponse response) throws Exception {
        Connection conn = null;
        try {
            try {
                conn = getConnection();
            } catch (Exception ex) {
                ActionErrors errors = new ActionErrors();
                errors.add(ActionErrors.GLOBAL_ERROR,
                           new ActionError("errors", "Loi ket noi CSDL: " +
                                           ex.getMessage()));
                saveErrors(request, errors);
                return mapping.findForward(FAILURE);
            }
            HttpSession session = request.getSession();
            if (session.getAttribute(AppConstants.APP_USER_ID_SESSION) !=
                null) {
                return mapping.findForward(SUCCESS);
            }
            if (request.getParameter("logout") != null &&
                request.getParameter("logout").toString().equalsIgnoreCase("true")) {
                throw TPCPException.createException("TPCP-0002",
                                                    request.getParameter("ma_nsd").toString(),
                                                    request.getParameter("ip_truycap").toString());
            } else {
                //Load tham so len session
                ThamSoHThongDAO paramDAO = new ThamSoHThongDAO(conn);
                Collection appParam = paramDAO.getThamSoList(null, null);
                session.setAttribute(AppConstants.APP_THAM_SO_SESSION,
                                     appParam);


                UserForm f = (UserForm)form;

                UserDAO userDAO = new UserDAO(conn);
                UserVO userVO = null;

                String strPassword = f.getMat_khau();
                String strUsername = f.getMa_nsd();
                String strDomain = f.getDomain();
                

                if (strUsername == null) {
                    return mapping.findForward(FAILURE);
                } else {
                    String strWhere =
                        " UPPER (a.ma_nsd) = ? and trang_thai = '01'";
                    Vector vParam = new Vector();
                    vParam.add(new Parameter(strUsername.toUpperCase(), true));
                    userVO = userDAO.getUser(strWhere, vParam);

                    if (userVO == null) {
                      
                        throw TPCPException.createException("TPCP-0001",
                                                            "Ng&#432;&#7901;i s&#7917; d&#7909;ng &#273;&#227; b&#7883; KH&#211;A " +
                                                            "ho&#7863;c b&#7883; NG&#7914;NG HO&#7840;T &#272;&#7896;NG " +
                                                            "ho&#7863;c CH&#431;A &#272;&#431;&#7906;C T&#7840;O T&#192;I KHO&#7842;N " +
                                                            "trong h&#7879; th&#7889;ng TPCP. " +
                                                            "H&#227;y li&#234;n h&#7879; v&#7899;i qu&#7843;n tr&#7883; &#273;&#7875; bi&#7871;t r&#245; h&#417;n.");
                    } else {                        
                        //********************
                        if (userVO.getSession_id() == null ||
                            "".equals(userVO.getSession_id())) {
                        } else {
                            String strOldIP =
                                userVO.getIp_truycap() == null ? "" :
                                userVO.getIp_truycap().trim();
                            if (!strOldIP.equals(f.getIp_truycap() == null ?
                                                 "" :
                                                 f.getIp_truycap().trim())) {
                                if (!"confirm".equalsIgnoreCase(f.getLogin_status())) {
                                    request.setAttribute("ip_dang_truycap",
                                                         userVO.getIp_truycap());
                                    return mapping.findForward(CONFIRM);
                                }
                                f.setLogin_status("");
                            }
                        }
                        String strWSDL =
                            getThamSoHThong("WSDL_ACTIVE_DIRECTORY", session);
                        
                        
                        ActiveDirectory ad = new ActiveDirectory(strWSDL);
                        boolean bAuthen =
                            ad.authenticateUser(strDomain + "\\" + strUsername,
                                                strPassword);
                        
                        int iSoLanChoPhepDangNhapSai = Integer.parseInt(getThamSoHThong(AppConstants.PARAM_SO_LAN_DANG_NHAP_SAI,session));                        
                        long iSoLanDangNhapSai = userVO.getLogin_failure().longValue() + 1;
                        if (!bAuthen) {
                            UserVO vo = new UserVO();
                            String strThongBaoKhoa = "";
                            if (userVO.getLogin_failure().longValue() + 1 >= iSoLanChoPhepDangNhapSai) {
                                vo.setId(userVO.getId());
                                vo.setLogin_failure(new Long(userVO.getLogin_failure().longValue() +
                                                             1));
                                vo.setTrang_thai("02");
                                strThongBaoKhoa =
                                        "B&#7841;n &#273;&#227; &#273;&#259;ng nh&#7853;p sai " + iSoLanChoPhepDangNhapSai +
                                        " l&#7847;n li&#234;n ti&#7871;p. T&#224;i kho&#7843;n c&#7911;a b&#7841;n &#273;&#227; b&#7883; kh&#243;a t&#7921; &#273;&#7897;ng.";
                            } else {
                                vo.setId(userVO.getId());
                                vo.setLogin_failure(new Long(userVO.getLogin_failure().longValue() +
                                                             1));
                                if(iSoLanDangNhapSai > 3){
                                  strThongBaoKhoa =  "B&#7841;n &#273;&#227; &#273;&#259;ng nh&#7853;p sai " + 
                                                     iSoLanDangNhapSai 
                                                     +" l&#7847;n li&#234;n ti&#7871;p. T&#224;i kho&#7843;n s&#7869; b&#7883; kh&#243;a t&#7921; &#273;&#7897;ng n&#7871;u &#273;&#259;ng nh&#7853;p sai li&#234;n ti&#7871;p " + iSoLanChoPhepDangNhapSai + " l&#7847;n";
                                }
                            }
                            userDAO.update(vo);
                            conn.commit();
                            throw TPCPException.createException("TPCP-0001",
                                                                "Sai m&#227; ng&#432;&#7901;i s&#7917; d&#7909;ng ho&#7863;c sai m&#7853;t kh&#7849;u. \n" +
                                    strThongBaoKhoa);
                        }
                    }
                    Long nID = userVO.getId();
                    //Day len session
                    session.setAttribute(AppConstants.APP_USER_ID_SESSION,
                                         nID);
                    session.setAttribute(AppConstants.APP_IP_SESSION,
                                         f.getIp_truycap());
                    session.setAttribute(AppConstants.APP_OS_USER_SESSION,
                                         f.getUser_may_truycap());
                    session.setAttribute(AppConstants.APP_COMPUTER_NAME_SESSION,
                                         f.getTen_may_truycap());


                    setEndToEndMetrics(request, conn);

                    //Get danh sach chuc nang
                    ChucNangDAO cnangDAO = new ChucNangDAO(conn);
                    Collection colChucNang =
                        cnangDAO.getChucNangListByUserID(userVO.getId());
                    session.setAttribute(AppConstants.APP_CHUC_NANG_LIST_SESSION,
                                         colChucNang);
                    List lstMenu =
                        cnangDAO.getCNangMenuFromCNangList(colChucNang);
                    session.setAttribute(AppConstants.APP_MENU_LIST_SESSION,
                                         lstMenu);
                    //
                    DMKBacDAO dmkbDAO = new DMKBacDAO(conn);
                    //Set thong tin NSD vao AppForm
                    f.setG_ma_nsd(userVO.getMa_nsd());
                    f.setG_ten_nsd(userVO.getTen_nsd());
                    f.setG_nsd_id(String.valueOf(userVO.getId()));
                    
 
                    //Lay DMKB
                    strWhere = " AND a.id = ? ";
                    vParam = new Vector();
                    vParam.add(new Parameter(userVO.getKb_id(), true));
                    DMKBacVO dmkbVO = dmkbDAO.getDMKB(strWhere, vParam);

                    if(dmkbVO != null){
                      //Set thong tin KB vao AppForm
                      f.setG_ma_kb(dmkbVO.getMa());
                      f.setG_ten_kb(dmkbVO.getTen());
                      f.setG_kb_id(dmkbVO.getId().toString());
                      
                      session.setAttribute(AppConstants.APP_KB_ID_SESSION,
                                           dmkbVO.getId());
                      session.setAttribute(AppConstants.APP_KB_CODE_SESSION,
                                           dmkbVO.getMa());
                      session.setAttribute(AppConstants.APP_KB_NAME_SESSION,
                                           dmkbVO.getTen());  
                    }
                   
 
                    //Thong tin user
                    session.setAttribute(AppConstants.APP_USER_CODE_SESSION,
                                         userVO.getMa_nsd());
                    session.setAttribute(AppConstants.APP_USER_NAME_SESSION,
                                         userVO.getTen_nsd());
                    session.setAttribute(AppConstants.APP_DOMAIN_SESSION,
                                         userVO.getDomain().toLowerCase());
                    session.setAttribute("PASSWORD",f.getMat_khau());
                    //Thong tin kho bac
                                  
                    //Set role vao session
                    NhomNSDDAO nhomDAO = new NhomNSDDAO(conn);
                    Collection colNhom = nhomDAO.getNhomNSDListByUserID(nID);
                    Iterator iter = colNhom.iterator();
                    NhomNSDVO nhomVO = null;
                    StringBuffer szRole = new StringBuffer();
                    int nCounter = 0;
                    while (iter.hasNext()) {
                        nhomVO = (NhomNSDVO)iter.next();
                        if (nCounter > 0)
                            szRole.append("|");
                        szRole.append(nhomVO.getLoai_nhom());
                        nCounter++;
                    }
                    session.setAttribute(AppConstants.APP_ROLE_LIST_SESSION,
                                         szRole.toString());

                   
                                      
                    //Thiet lap timeout                    
                    int iTimeOut = 0;
                    try {
                        String strTimeOut = getThamSoHThong("WSDL_ACTIVE_DIRECTORY", session);
                        iTimeOut = Integer.parseInt(strTimeOut);
                    } catch (Exception ex) {
                        iTimeOut = 30;
                    }
                    session.setMaxInactiveInterval(iTimeOut * 60);
                    //---------------

                    //Luu thong tin dang nhap vao CSDL
                    userVO = new UserVO();
                    userVO.setIp_truycap(f.getIp_truycap());
                    userVO.setSession_id(session.getId());
                    userVO.setTgian_truycap(new Date());
                    userVO.setUser_may_truycap(f.getUser_may_truycap());
                    userVO.setTen_may_truycap(f.getTen_may_truycap());
                    userVO.setId(nID);
                    userVO.setLogin_failure(new Long(0));

                    userDAO.update(userVO);

                    saveVisitLog(conn, session, "SYS.LOGIN", "");
                    
                    conn.commit();
                }
            }
        }catch(Exception ex){
          throw ex;
        } finally {
            close(conn);
        }
        return mapping.findForward(SUCCESS);
    }

}
