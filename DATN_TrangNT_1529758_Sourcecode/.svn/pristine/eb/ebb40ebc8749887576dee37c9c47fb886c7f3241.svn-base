package com.seatech.tp.ttlaigoc.dao;

import com.seatech.framework.datamanager.AppDAO;
import com.seatech.framework.datamanager.DateParameter;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.exception.DAOException;
import com.seatech.framework.utils.StringUtil;
import com.seatech.tp.qlytp.vo.QuanLyTPCPVO;
import com.seatech.tp.sotonghoptpcp.vo.SoTongHopTpcpVO;
import com.seatech.tp.ttindthau.vo.ThongTinDauThauVO;
import com.seatech.tp.ttlaigoc.vo.TPLenhTriTraNoCTVo;
import com.seatech.tp.ttlaigoc.vo.TPLenhTriTraNoVo;
import com.seatech.tp.ttlaigoc.vo.TTLaiGocVo;

import java.math.BigDecimal;

import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.Connection;

import java.sql.ResultSet;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import java.util.Iterator;

import java.util.Map;

import oracle.jdbc.OracleTypes;

import org.codehaus.groovy.runtime.typehandling.BigDecimalMath;

public class TTLaiGocDao extends AppDAO {
    private Connection conn = null;
    private String CLASS_NAME_DAO = "com.seatech.tp.ttlaigocvnd.dao.TTLaiGocVndDao";
    private String CLASS_NAME_VO = "com.seatech.tp.ttlaigoc.vo.TTLaiGocVo";
    private String CLASS_NAME_VO_LENH_TTN = "com.seatech.tp.ttlaigoc.vo.TPLenhTriTraNoVo";
    private String CLASS_NAME_VO_LENH_TTN_CT = "com.seatech.tp.ttlaigoc.vo.TPLenhTriTraNoCTVo";
    private static String STRING_EMPTY = "";

    public TTLaiGocDao(Connection conn) {
        super();
        this.conn = conn;
    }


    public Collection getListLenhTraNoPDPaging(TPLenhTriTraNoVo vo, Integer page, Integer count, Integer[] totalCount) throws Exception {
        Collection reval = null;
        String whereClause = "";
        Vector vParam = new Vector();
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append("SELECT \n" +
                    "   T.CAN_CU, T.CAP_NS, T.DVI_NHAN, T.DVQHNS, T.GUID, T.LOAI_TIEN, \n" +
                    "   T.MA_TPCP, T.NAM_NS, to_char(T.NGAY_CHUYEN,'dd/MM/yyyy') NGAY_CHUYEN, \n" +
                    "   to_char(T.NGAY_DEN_HAN_TT,'dd/MM/yyyy') NGAY_DEN_HAN_TT, to_char(T.NGAY_DUYET,'dd/MM/yyyy') NGAY_DUYET, to_char(T.NGAY_SUA_CUOI,'dd/MM/yyyy') NGAY_SUA_CUOI, \n" +
                    "   to_char(T.NGAY_TAO,'dd/MM/yyyy') NGAY_TAO, T.NGUOI_DUYET, T.NGUOI_SUA_CUOI, \n" +
                    "   T.NGUOI_TAO, T.NH_NHAN, T.PTHUC_PH, T.SO_LENH, T.SO_TIEN_NGTE, T.SO_TIEN_VND, \n" +
                    "   T.SO_TK_NHAN, T.TY_GIA , T.DOT_PH , T.TRANG_THAI ," + "(SELECT B.NAME FROM TP_DM_TRANG_THAI B WHERE T.TRANG_THAI=B.ID_STATUS ) AS TEN_TRANG_THAI ," +
                    " T.SO_TIEN_VND AS TONG_TIEN\n" +
                    " FROM TP_LENH_CHI_TRA_NO T WHERE T.LOAI_TIEN ='VND' AND T.TRANG_THAI in ('01','02','03')");
            if (vo.getSo_lenh() != null && !"".equals(vo.getSo_lenh())) {
                whereClause += " and  T.SO_LENH  = ?";
                vParam.add(new Parameter(vo.getSo_lenh(), true));
            }

            if (vo.getNam_ns() != null && !"".equals(vo.getNam_ns())) {
                whereClause += " and  T.NAM_NS  = ?";
                vParam.add(new Parameter(vo.getNam_ns(), true));
            }

            if (vo.getDvi_nhan() != null && !"".equals(vo.getDvi_nhan())) {
                whereClause += " and  T.DVI_NHAN  = ?";
                vParam.add(new Parameter(vo.getDvi_nhan(), true));
            }

            if (vo.getTrang_thai() != null && !"".equals(vo.getTrang_thai())) {
                whereClause += " and  T.TRANG_THAI  = ?";
                vParam.add(new Parameter(vo.getTrang_thai(), true));
            }

            if (vo.getNgay_chuyen_tu_ngay() != null && !"".equals(vo.getNgay_chuyen_tu_ngay())) {
                whereClause += " and trunc(T.NGAY_DEN_HAN_TT) >= ?";

                vParam.add(new DateParameter(StringUtil.StringToDate(vo.getNgay_chuyen_tu_ngay(), "dd/MM/yyyy"), true));
            }
            if (vo.getNgay_chuyen_den_ngay() != null && !"".equals(vo.getNgay_chuyen_den_ngay())) {
                whereClause += " and trunc(T.NGAY_DEN_HAN_TT) <= ?";

                vParam.add(new DateParameter(StringUtil.StringToDate(vo.getNgay_chuyen_den_ngay(), "dd/MM/yyyy"), true));
            }
            if (whereClause != null && !whereClause.equalsIgnoreCase("")) {
                strSQL.append(whereClause);
            }
            strSQL.append(" ORDER BY T.NGAY_TAO DESC");
            reval = executeSelectWithPaging(conn, strSQL.toString(), vParam, CLASS_NAME_VO_LENH_TTN, page, count, totalCount);
        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO + ".getListLenhTriTraNoPaging(): " + ex.getMessage(), ex);
        }
        return reval;
    }


    //Thanh toan phi qua HNX

    public Collection tinhLSDN(TTLaiGocVo vo, Integer page, Integer count, Integer[] totalCount) throws Exception {
        Collection listLenhTraNo = new ArrayList();
        String sProcedureName = "{call pro_interest_calculator2(?,?,?,?,?,?,?)}";
        Vector vParam = new Vector();
        ResultSet rs = null;
        CallableStatement callableStatement = null;

        StringBuffer strSQL = new StringBuffer();
        try {
            callableStatement = conn.prepareCall(sProcedureName);
            callableStatement.setString(1, vo.getMa_tpcp()!=null?vo.getMa_tpcp().trim():"");
            callableStatement.setString(2, vo.getNgay_thanh_toan_tu_ngay());
            callableStatement.setString(3, vo.getNgay_thanh_toan_den_ngay());
            callableStatement.setString(4, vo.getPhuong_thuc_ph());
            callableStatement.setString(5, vo.getLoai_tien());
            callableStatement.setString(6, vo.getTy_gia()!=null?vo.getTy_gia().trim().replaceAll("\\.", "").replaceAll(",", "."):"");
            callableStatement.registerOutParameter(7, Types.VARCHAR);
            callableStatement.executeUpdate();
            String guid_lenh = callableStatement.getString(7);
            if (guid_lenh != null && !guid_lenh.isEmpty()) {
                strSQL.append("SELECT \n" +
                        "   T.CAN_CU, T.CAP_NS, T.DVI_NHAN, T.DVQHNS, T.GUID, T.LOAI_TIEN, \n" +
                        "   T.MA_TPCP, T.NAM_NS, to_char(T.NGAY_CHUYEN,'dd/MM/yyyy') NGAY_CHUYEN, \n" +
                        "   to_char(T.NGAY_DEN_HAN_TT,'dd/MM/yyyy') NGAY_DEN_HAN_TT, to_char(T.NGAY_DUYET,'dd/MM/yyyy') NGAY_DUYET, to_char(T.NGAY_SUA_CUOI,'dd/MM/yyyy') NGAY_SUA_CUOI, \n" +
                        "   to_char(T.NGAY_TAO,'dd/MM/yyyy') NGAY_TAO, T.NGUOI_DUYET, T.NGUOI_SUA_CUOI, \n" +
                        "   T.NGUOI_TAO, T.NH_NHAN, T.PTHUC_PH, T.SO_LENH, T.SO_TIEN_NGTE, T.SO_TIEN_VND, \n" +
                        "   T.SO_TK_NHAN, T.TY_GIA , T.DOT_PH , T.TRANG_THAI \n" +
                        " FROM TP_LENH_CHI_TRA_NO T WHERE T.GUID in " + guid_lenh);
                listLenhTraNo = executeSelectWithPaging(conn, strSQL.toString(), null, CLASS_NAME_VO_LENH_TTN, page, count, totalCount);
                if (listLenhTraNo != null && listLenhTraNo.size() > 0) {
                    Iterator ito = listLenhTraNo.iterator();
                    TPLenhTriTraNoVo voTraNo = null;
                    while (ito.hasNext()) {
                        voTraNo = (TPLenhTriTraNoVo)ito.next();
                        Collection listLenhTNCT = new ArrayList();
                        strSQL.delete(0, strSQL.length());
                        if (voTraNo.getGuid() != null && !voTraNo.getGuid().equals("")) {
                            strSQL.append("SELECT \n" +
                                    "T.CHUONG, T.CTMT, T.GUID, \n" +
                                    "   T.ID_LENH_CHI_TRA_NO, T.NDKT, T.NGANH_KT, \n" +
                                    "   T.NGUON, T.NOI_DUNG, T.SO_TIEN_NGTE, \n" +
                                    "   T.SO_TIEN_VND\n" +
                                    "FROM TPCP_OWNER.TP_LENH_CHI_TRA_NO_CTIET T WHERE T.ID_LENH_CHI_TRA_NO =" +
                                    voTraNo.getGuid() + " ");
                            listLenhTNCT = executeSelectStatement(strSQL.toString(), new Vector(), CLASS_NAME_VO_LENH_TTN_CT, conn);

                        }
                        if (listLenhTNCT != null) {
                            voTraNo.setRows("" + (listLenhTNCT.size() + 1));
                            Iterator itoCT = listLenhTNCT.iterator();
                            TPLenhTriTraNoCTVo voTraNoCT = null;
                            while (itoCT.hasNext()) {
                                voTraNoCT = (TPLenhTriTraNoCTVo)itoCT.next();
                                if (voTraNoCT.getSo_tien_vnd() != null)
                                    voTraNoCT.setSo_tien_vnd(StringUtil.convertNumberToString(voTraNoCT.getSo_tien_vnd(), "VND"));
                            }
                        }
                        voTraNo.setList_Lenh_tra_no_ct(listLenhTNCT);
                    }

                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new DAOException(CLASS_NAME_DAO + ".tinhLSDN(): " + ex.getMessage(), ex);
        }
        return listLenhTraNo;
    }

    public Collection getListLenhTriTraNoPaging(TPLenhTriTraNoVo vo, Integer page, Integer count, Integer[] totalCount) throws Exception {
        Collection reval = null;
        String whereClause = "";
        Vector vParam = new Vector();
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append("SELECT \n" +
                    "   T.CAN_CU, T.CAP_NS, T.DVI_NHAN, T.DVQHNS, T.GUID, T.LOAI_TIEN, \n" +
                    "   T.MA_TPCP, T.NAM_NS, to_char(T.NGAY_CHUYEN,'dd/MM/yyyy') NGAY_CHUYEN, \n" +
                    "   to_char(T.NGAY_DEN_HAN_TT,'dd/MM/yyyy') NGAY_DEN_HAN_TT, to_char(T.NGAY_DUYET,'dd/MM/yyyy') NGAY_DUYET, to_char(T.NGAY_SUA_CUOI,'dd/MM/yyyy') NGAY_SUA_CUOI, \n" +
                    "   to_char(T.NGAY_TAO,'dd/MM/yyyy') NGAY_TAO, T.NGUOI_DUYET, T.NGUOI_SUA_CUOI, \n" +
                    "   T.NGUOI_TAO, T.NH_NHAN, T.PTHUC_PH, T.SO_LENH, T.SO_TIEN_NGTE, T.SO_TIEN_VND,T.LOAI_THANH_TOAN, \n" +
                    "   T.SO_TK_NHAN, T.TY_GIA , T.DOT_PH , T.TRANG_THAI ," + "(SELECT B.NAME FROM TP_DM_TRANG_THAI B WHERE T.TRANG_THAI=B.ID_STATUS ) AS TEN_TRANG_THAI ," +
                    " T.SO_TIEN_VND AS TONG_TIEN\n " + " FROM TP_LENH_CHI_TRA_NO T WHERE T.LOAI_TIEN ='VND' AND T.TRANG_THAI != '04'");
            if (vo.getSo_lenh() != null && !"".equals(vo.getSo_lenh())) {
                whereClause += " and  T.SO_LENH  = ?";
                vParam.add(new Parameter(vo.getSo_lenh(), true));
            }

            if (vo.getNam_ns() != null && !"".equals(vo.getNam_ns())) {
                whereClause += " and  T.NAM_NS  = ?";
                vParam.add(new Parameter(vo.getNam_ns(), true));
            }

            if (vo.getDvi_nhan() != null && !"".equals(vo.getDvi_nhan())) {
                whereClause += " and  T.DVI_NHAN  = ?";
                vParam.add(new Parameter(vo.getDvi_nhan(), true));
            }

            if (vo.getTrang_thai() != null && !"".equals(vo.getTrang_thai())) {
                whereClause += " and  T.TRANG_THAI  = ?";
                vParam.add(new Parameter(vo.getTrang_thai(), true));
            }

            if (vo.getNgay_chuyen_tu_ngay() != null && !"".equals(vo.getNgay_chuyen_tu_ngay())) {
                whereClause += " and trunc(T.NGAY_DEN_HAN_TT) >= ?";

                vParam.add(new DateParameter(StringUtil.StringToDate(vo.getNgay_chuyen_tu_ngay(), "dd/MM/yyyy"), true));
            }
            if (vo.getNgay_chuyen_den_ngay() != null && !"".equals(vo.getNgay_chuyen_den_ngay())) {
                whereClause += " and trunc(T.NGAY_DEN_HAN_TT) <= ?";

                vParam.add(new DateParameter(StringUtil.StringToDate(vo.getNgay_chuyen_den_ngay(), "dd/MM/yyyy"), true));
            }
            if (whereClause != null && !whereClause.equalsIgnoreCase("")) {
                strSQL.append(whereClause);
            }
            strSQL.append(" ORDER BY T.NGAY_TAO DESC");
            reval = executeSelectWithPaging(conn, strSQL.toString(), vParam, CLASS_NAME_VO_LENH_TTN, page, count, totalCount);
        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO + ".getListLenhTriTraNoPaging(): " + ex.getMessage(), ex);
        }
        return reval;
    }

    public Collection getListLenhTriTraNoUSDPaging(TPLenhTriTraNoVo vo, Integer page, Integer count, Integer[] totalCount) throws Exception {
        Collection reval = null;
        String whereClause = "";
        Vector vParam = new Vector();
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append("SELECT \n" +
                    "   T.CAN_CU,T.DOT_PH, T.CAP_NS, T.DVI_NHAN, T.DVQHNS, T.GUID, T.LOAI_TIEN, \n" +
                    "   T.MA_TPCP, T.NAM_NS, to_char(T.NGAY_CHUYEN,'dd/MM/yyyy') NGAY_CHUYEN, \n" +
                    "   to_char(T.NGAY_DEN_HAN_TT,'dd/MM/yyyy') NGAY_DEN_HAN_TT, to_char(T.NGAY_DUYET,'dd/MM/yyyy') NGAY_DUYET, to_char(T.NGAY_SUA_CUOI,'dd/MM/yyyy') NGAY_SUA_CUOI, \n" +
                    "   to_char(T.NGAY_TAO,'dd/MM/yyyy') NGAY_TAO, T.NGUOI_DUYET, T.NGUOI_SUA_CUOI, \n" +
                    "   T.NGUOI_TAO, T.NH_NHAN, T.PTHUC_PH, T.SO_LENH, T.SO_TIEN_NGTE, T.SO_TIEN_VND,T.LOAI_THANH_TOAN, \n" +
                    "   T.SO_TK_NHAN, T.TY_GIA , T.DOT_PH , T.TRANG_THAI ," + " (SELECT B.NAME FROM TP_DM_TRANG_THAI B WHERE T.TRANG_THAI=B.ID_STATUS ) AS TEN_TRANG_THAI ," +
                    "  T.SO_TIEN_VND AS TONG_TIEN ," + " T.SO_TIEN_NGTE AS TONG_TIEN_USD, LOAI_THANH_TOAN \n" +
                    " FROM TP_LENH_CHI_TRA_NO T WHERE T.TRANG_THAI != '04' ");
            if (vo.getSo_lenh() != null && !"".equals(vo.getSo_lenh())) {
                whereClause += " and  T.SO_LENH  = ?";
                vParam.add(new Parameter(vo.getSo_lenh(), true));
            }

            if (vo.getMa_tpcp() != null && !"".equals(vo.getMa_tpcp())) {
                whereClause += " and  T.MA_TPCP  = ?";
                vParam.add(new Parameter(vo.getMa_tpcp(), true));
            }
            if (vo.getDot_ph() != null && !"".equals(vo.getDot_ph())) {
                whereClause += " and  T.DOT_PH  = ?";
                vParam.add(new Parameter(vo.getDot_ph(), true));
            }
            if (vo.getDvi_nhan() != null && !"".equals(vo.getDvi_nhan())) {
                whereClause += " and  T.DVI_NHAN  = ?";
                vParam.add(new Parameter(vo.getDvi_nhan(), true));
            }

            if (vo.getTrang_thai() != null && !"".equals(vo.getTrang_thai())) {
                whereClause += " and  T.TRANG_THAI  = ?";
                vParam.add(new Parameter(vo.getTrang_thai(), true));
            }

            if (vo.getLoai_tien() != null && !"".equals(vo.getLoai_tien())) {
                whereClause += " and  T.LOAI_TIEN  = ?";
                vParam.add(new Parameter(vo.getLoai_tien(), true));
            }

            if (vo.getNgay_chuyen_tu_ngay() != null && !"".equals(vo.getNgay_chuyen_tu_ngay())) {
                whereClause += " and trunc(T.NGAY_DEN_HAN_TT) >= ?";

                vParam.add(new DateParameter(StringUtil.StringToDate(vo.getNgay_chuyen_tu_ngay(), "dd/MM/yyyy"), true));
            }
            if (vo.getNgay_chuyen_den_ngay() != null && !"".equals(vo.getNgay_chuyen_den_ngay())) {
                whereClause += " and trunc(T.NGAY_DEN_HAN_TT) <= ?";

                vParam.add(new DateParameter(StringUtil.StringToDate(vo.getNgay_chuyen_den_ngay(), "dd/MM/yyyy"), true));
            }
            if (whereClause != null && !whereClause.equalsIgnoreCase("")) {
                strSQL.append(whereClause);
            }
            strSQL.append(" ORDER BY T.NGAY_TAO DESC");
            reval = executeSelectWithPaging(conn, strSQL.toString(), vParam, CLASS_NAME_VO_LENH_TTN, page, count, totalCount);
        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO + ".getListLenhTriTraNoPaging(): " + ex.getMessage(), ex);
        }
        return reval;
    }

    public Collection getListLenhTriTraNoPDUSDPaging(TPLenhTriTraNoVo vo, Integer page, Integer count, Integer[] totalCount) throws Exception {
        Collection reval = null;
        String whereClause = "";
        Vector vParam = new Vector();
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append("SELECT \n" +
                    "   T.CAN_CU,T.DOT_PH, T.CAP_NS, T.DVI_NHAN, T.DVQHNS, T.GUID, T.LOAI_TIEN, \n" +
                    "   T.MA_TPCP, T.NAM_NS, to_char(T.NGAY_CHUYEN,'dd/MM/yyyy') NGAY_CHUYEN, \n" +
                    "   to_char(T.NGAY_DEN_HAN_TT,'dd/MM/yyyy') NGAY_DEN_HAN_TT, to_char(T.NGAY_DUYET,'dd/MM/yyyy') NGAY_DUYET, to_char(T.NGAY_SUA_CUOI,'dd/MM/yyyy') NGAY_SUA_CUOI, \n" +
                    "   to_char(T.NGAY_TAO,'dd/MM/yyyy') NGAY_TAO, T.NGUOI_DUYET, T.NGUOI_SUA_CUOI, \n" +
                    "   T.NGUOI_TAO, T.NH_NHAN, T.PTHUC_PH, T.SO_LENH, T.SO_TIEN_NGTE, T.SO_TIEN_VND, \n" +
                    "   T.SO_TK_NHAN, T.TY_GIA , T.DOT_PH , T.TRANG_THAI ," + " (SELECT B.NAME FROM TP_DM_TRANG_THAI B WHERE T.TRANG_THAI=B.ID_STATUS ) AS TEN_TRANG_THAI ," +
                    " T.SO_TIEN_VND AS TONG_TIEN ," + " T.SO_TIEN_NGTE AS TONG_TIEN_USD,LOAI_THANH_TOAN \n" +
                    " FROM TP_LENH_CHI_TRA_NO T WHERE T.TRANG_THAI in ('01','02','03') ");
            if (vo.getSo_lenh() != null && !"".equals(vo.getSo_lenh())) {
                whereClause += " and  T.SO_LENH  = ?";
                vParam.add(new Parameter(vo.getSo_lenh(), true));
            }

            if (vo.getMa_tpcp() != null && !"".equals(vo.getMa_tpcp())) {
                whereClause += " and  T.MA_TPCP  = ?";
                vParam.add(new Parameter(vo.getMa_tpcp(), true));
            }
            if (vo.getDot_ph() != null && !"".equals(vo.getDot_ph())) {
                whereClause += " and  T.DOT_PH  = ?";
                vParam.add(new Parameter(vo.getDot_ph(), true));
            }
            if (vo.getDvi_nhan() != null && !"".equals(vo.getDvi_nhan())) {
                whereClause += " and  T.DVI_NHAN  = ?";
                vParam.add(new Parameter(vo.getDvi_nhan(), true));
            }

            if (vo.getTrang_thai() != null && !"".equals(vo.getTrang_thai())) {
                whereClause += " and  T.TRANG_THAI  = ?";
                vParam.add(new Parameter(vo.getTrang_thai(), true));
            }

            if (vo.getLoai_tien() != null && !"".equals(vo.getLoai_tien())) {
                whereClause += " and  T.LOAI_TIEN  = ?";
                vParam.add(new Parameter(vo.getLoai_tien(), true));
            }

            if (vo.getNgay_chuyen_tu_ngay() != null && !"".equals(vo.getNgay_chuyen_tu_ngay())) {
                whereClause += " and trunc(T.NGAY_DEN_HAN_TT) >= ?";

                vParam.add(new DateParameter(StringUtil.StringToDate(vo.getNgay_chuyen_tu_ngay(), "dd/MM/yyyy"), true));
            }
            if (vo.getNgay_chuyen_den_ngay() != null && !"".equals(vo.getNgay_chuyen_den_ngay())) {
                whereClause += " and trunc(T.NGAY_DEN_HAN_TT) <= ?";

                vParam.add(new DateParameter(StringUtil.StringToDate(vo.getNgay_chuyen_den_ngay(), "dd/MM/yyyy"), true));
            }
            if (whereClause != null && !whereClause.equalsIgnoreCase("")) {
                strSQL.append(whereClause);
            }
            strSQL.append(" ORDER BY T.NGAY_TAO DESC");
            reval = executeSelectWithPaging(conn, strSQL.toString(), vParam, CLASS_NAME_VO_LENH_TTN, page, count, totalCount);
        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO + ".getListLenhTriTraNoPaging(): " + ex.getMessage(), ex);
        }
        return reval;
    }

    public TPLenhTriTraNoVo getListLenhTriTraNoCT(String idLenh) throws DAOException {
        TPLenhTriTraNoVo voTraNo = new TPLenhTriTraNoVo();
        Collection reval = null;
        String whereClause = "";
        Vector vParam = new Vector();
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append("SELECT \n" +
                    "   T.CAN_CU, T.CAP_NS, T.DVI_NHAN, T.DVQHNS, T.GUID, T.LOAI_TIEN, \n" +
                    "   T.MA_TPCP, T.NAM_NS, to_char(T.NGAY_CHUYEN,'dd/MM/yyyy') NGAY_CHUYEN, \n" +
                    "   to_char(T.NGAY_DEN_HAN_TT,'dd/MM/yyyy') NGAY_DEN_HAN_TT, to_char(T.NGAY_DUYET,'dd/MM/yyyy') NGAY_DUYET, to_char(T.NGAY_SUA_CUOI,'dd/MM/yyyy') NGAY_SUA_CUOI, \n" +
                    "   to_char(T.NGAY_TAO,'dd/MM/yyyy') NGAY_TAO, T.NGUOI_DUYET, T.NGUOI_SUA_CUOI, \n" +
                    "   T.NGUOI_TAO, T.NH_NHAN, T.PTHUC_PH, T.SO_LENH, T.SO_TIEN_NGTE, T.SO_TIEN_VND,T.LOAI_THANH_TOAN, \n" +
                    "   T.SO_TK_NHAN, T.TY_GIA , T.DOT_PH , T.TRANG_THAI ,LY_DO_TU_CHOI, " +
                    "(SELECT B.NAME FROM TP_DM_TRANG_THAI B WHERE T.TRANG_THAI=B.ID_STATUS ) AS TEN_TRANG_THAI ," + " T.SO_TIEN_VND AS TONG_TIEN , \n" +
                    " T.SO_TIEN_NGTE AS TONG_TIEN_USD\n" +
                    " FROM TP_LENH_CHI_TRA_NO T WHERE T.GUID = " + idLenh);
            voTraNo = (TPLenhTriTraNoVo)findByPK(strSQL.toString(), vParam, CLASS_NAME_VO_LENH_TTN, conn);
            if (!"VND".equals(voTraNo.getLoai_tien())) {
                if (voTraNo.getTy_gia() != null && !"".equals(voTraNo.getTy_gia())) {
                    BigDecimal ty_gia = new BigDecimal(voTraNo.getTy_gia());
                    BigDecimal so_tien_ngte = new BigDecimal(voTraNo.getSo_tien_ngte());
                    String so_tien_vnd = StringUtil.convertNumberToString(so_tien_ngte.multiply(ty_gia).toString(), "VND");
                    voTraNo.setTong_tien(so_tien_vnd);
                }
            }else voTraNo.setTong_tien(StringUtil.convertNumberToString(voTraNo.getTong_tien(), "VND"));
            if (voTraNo != null) {

                strSQL.delete(0, strSQL.length());
                strSQL.append("SELECT \n" +
                        "T.CHUONG, T.CTMT, T.GUID,T.GUID as GUID_CT, \n" +
                        "   T.ID_LENH_CHI_TRA_NO, T.NDKT, T.NGANH_KT, \n" +
                        "   T.NGUON, T.NOI_DUNG, T.SO_TIEN_NGTE, \n" +
                        "   T.SO_TIEN_VND \n" +
                        "FROM TP_LENH_CHI_TRA_NO_CTIET T WHERE T.ID_LENH_CHI_TRA_NO =" + idLenh);
                reval = executeSelectStatement(strSQL.toString(), vParam, CLASS_NAME_VO_LENH_TTN_CT, conn);
                Iterator ito = reval.iterator();
                TPLenhTriTraNoCTVo vo = null;
                while (ito.hasNext()) {
                    vo = (TPLenhTriTraNoCTVo)ito.next();
                    if (!"VND".equals(voTraNo.getLoai_tien())) {
                        if (voTraNo.getTy_gia() != null && !"".equals(voTraNo.getTy_gia())) {
                            BigDecimal ty_gia = new BigDecimal(voTraNo.getTy_gia());
                            BigDecimal so_tien_ngte = new BigDecimal(vo.getSo_tien_ngte());
                            String so_tien_vnd = so_tien_ngte.multiply(ty_gia).toString();
                            vo.setSo_tien_vnd(so_tien_vnd);
                        }
                    }
                    if (vo.getSo_tien_vnd() != null && !vo.getSo_tien_vnd().equals("")) {
                        vo.setSo_tien_vnd(StringUtil.convertNumberToString(vo.getSo_tien_vnd(), "VND"));
                    }
                }
            }
            voTraNo.setList_Lenh_tra_no_ct(reval);
            //            voTraNo.setTong_tien(StringUtil.convertNumberToString(voTraNo.getTong_tien(),
            //                                                                  "VND"));
        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO + ".getListLenhTriTraNoCT(): " + ex.getMessage(), ex);
        }
        return voTraNo;
    }

    public Long insert(TPLenhTriTraNoVo vo) throws Exception {
        Vector v_param = new Vector();
        //L?y ID t? seq
        Long lID = new Long("0");
        StringBuffer strSQL = new StringBuffer();
        String sql = "";
        if (vo.getGuid() != null && !vo.getGuid().equals("")) {
            sql = generateSQLUpdate(vo, v_param, "TP_LENH_CHI_TRA_NO", conn);
            strSQL.append(sql);
            strSQL.append("WHERE GUID = ?");
            v_param.add(new Parameter(vo.getGuid(), true));
            lID = new Long(vo.getGuid());
        }
        if (executeStatement(strSQL.toString(), v_param, conn) == 1) {
            //update so lenh bang ke chuyen tien
            if ("TT_LAI_LAN1".equalsIgnoreCase(vo.getLoai_thanh_toan())) {
                strSQL = new StringBuffer();
                strSQL.append("UPDATE TP_BKE_CHUYEN_TIEN a SET a.so_lenh = '" + vo.getSo_lenh() + "' WHERE a.so_lenh = '" + vo.getSo_lenh_cu() + "' ");
                executeStatement(strSQL.toString(), null, conn);
            } else if ("TT_LAI_LAN2".equalsIgnoreCase(vo.getLoai_thanh_toan())) {
                strSQL = new StringBuffer();
                strSQL.append("UPDATE TP_BKE_CHUYEN_TIEN a SET a.so_lenh = '"+ vo.getSo_lenh() +"' WHERE a.so_lenh = '" + vo.getSo_lenh_cu() + "' ");
                executeStatement(strSQL.toString(), null, conn);
            }else if ("PHIHNX".equalsIgnoreCase(vo.getLoai_thanh_toan())) {
                strSQL = new StringBuffer();
                strSQL.append("UPDATE TP_BKE_CHUYEN_TIEN a SET a.so_lenh_phi = '"+vo.getSo_lenh()+"' WHERE a.PHUONG_THUC_PH = 'TD' and a.so_lenh_phi = '" + vo.getSo_lenh_cu() + "' ");
                executeStatement(strSQL.toString(), null, conn);
            } else if ("PHITPKB".equalsIgnoreCase(vo.getLoai_thanh_toan())) {
                strSQL = new StringBuffer();
                strSQL.append("UPDATE TP_BKE_CHUYEN_TIEN a SET a.so_lenh_phi = '"+vo.getSo_lenh()+"' WHERE a.PHUONG_THUC_PH = 'TPKB' and  a.so_lenh_phi = '" + vo.getSo_lenh_cu() + "' ");
                executeStatement(strSQL.toString(), null, conn);
            }else if ("TT_LAI_GOC".equalsIgnoreCase(vo.getLoai_thanh_toan())) {
                strSQL = new StringBuffer();
                strSQL.append("UPDATE TP_BKE_CHUYEN_TIEN a SET a.so_lenh = '"+ vo.getSo_lenh() +"' WHERE a.so_lenh = '" + vo.getSo_lenh_cu() + "' ");
                executeStatement(strSQL.toString(), null, conn);
            }else if ("GOCTINPHIEU".equalsIgnoreCase(vo.getLoai_thanh_toan())) {
                strSQL = new StringBuffer();
                strSQL.append("UPDATE TP_BKE_CHUYEN_TIEN a SET a.so_lenh = '"+ vo.getSo_lenh() +"' WHERE a.so_lenh = '" + vo.getSo_lenh_cu() + "' ");
                executeStatement(strSQL.toString(), null, conn);
            }
            Iterator ito = vo.getList_Lenh_tra_no_ct().iterator();
            TPLenhTriTraNoCTVo ctVo = new TPLenhTriTraNoCTVo();
            while (ito.hasNext()) {
                ctVo = (TPLenhTriTraNoCTVo)ito.next();
                Vector v_param2 = new Vector();
                sql = "";
                strSQL = new StringBuffer();
                sql = generateSQLUpdate(ctVo, v_param2, "TP_LENH_CHI_TRA_NO_CTIET", conn);
                strSQL.append(sql);
                strSQL.append("WHERE GUID = ?");
                v_param2.add(new Parameter(ctVo.getGuid(), true));
                int rs = executeStatement(strSQL.toString(), v_param2, conn);
            }
            return lID;
        } else
            return new Long("0");
    }

    public Long delete(TPLenhTriTraNoVo vo) throws Exception {
        Vector v_param = new Vector();
        StringBuffer strSQL = new StringBuffer();
        strSQL.append(" DELETE TP_LENH_CHI_TRA_NO WHERE GUID = ?");
        int nExc = 0;
        if (vo.getGuid() != null) {
            v_param.add(new Parameter(vo.getGuid(), true));
            nExc = executeStatement(strSQL.toString(), v_param, conn);
            if (nExc == 1) {
                if (vo.getGuid() != null) {
                    //xoa chi tiet
                    strSQL = new StringBuffer();
                    strSQL.append(" DELETE TP_LENH_CHI_TRA_NO_CTIET WHERE ID_LENH_CHI_TRA_NO = ?");
                    executeStatement(strSQL.toString(), v_param, conn);
                    //update bang ke de co the thanh toan lai
                    if ("TT_LAI_LAN1".equalsIgnoreCase(vo.getLoai_thanh_toan())) {
                        strSQL = new StringBuffer();
                        strSQL.append("UPDATE TP_BKE_CHUYEN_TIEN a SET a.so_lenh = null,a.TT_GOC = 0, a.LAN_TRA_LAI = a.LAN_TRA_LAI -1 , a.NGAY_TT_LAI_1 = a.NGAY_TT_LAI_1 - interval '1' YEAR WHERE a.so_lenh = '" +
                                vo.getSo_lenh() + "' ");
                        executeStatement(strSQL.toString(), null, conn);
                    } else if ("TT_LAI_LAN2".equalsIgnoreCase(vo.getLoai_thanh_toan())) {
                        strSQL = new StringBuffer();
                        strSQL.append("UPDATE TP_BKE_CHUYEN_TIEN a SET a.so_lenh = null, a.TT_GOC = 0, a.LAN_TRA_LAI = a.LAN_TRA_LAI -1 , a.NGAY_TT_LAI_2 = a.NGAY_TT_LAI_2 - interval '1' YEAR WHERE a.so_lenh = '" +
                                vo.getSo_lenh() + "' ");
                        executeStatement(strSQL.toString(), null, conn);
                    } else if ("TT_LAI_GOC".equalsIgnoreCase(vo.getLoai_thanh_toan())) {
                        strSQL = new StringBuffer();
                        strSQL.append("UPDATE TP_BKE_CHUYEN_TIEN a SET a.TT_GOC = 0 WHERE a.so_lenh = '" + vo.getSo_lenh() + "' ");
                        executeStatement(strSQL.toString(), null, conn);
                    } else if ("PHIHNX".equalsIgnoreCase(vo.getLoai_thanh_toan())) {
                        strSQL = new StringBuffer();
                        strSQL.append("UPDATE TP_BKE_CHUYEN_TIEN a SET a.so_lenh_phi = null WHERE a.PHUONG_THUC_PH = 'TD' and a.so_lenh_phi = '" + vo.getSo_lenh() + "' ");
                        executeStatement(strSQL.toString(), null, conn);
                    } else if ("PHITPKB".equalsIgnoreCase(vo.getLoai_thanh_toan())) {
                        strSQL = new StringBuffer();
                        strSQL.append("UPDATE TP_BKE_CHUYEN_TIEN a SET a.so_lenh_phi = null WHERE a.PHUONG_THUC_PH = 'TPKB' and  a.so_lenh_phi = '" + vo.getSo_lenh() + "' ");
                        executeStatement(strSQL.toString(), null, conn);
                    } else if ("GOCTINPHIEU".equalsIgnoreCase(vo.getLoai_thanh_toan())) {
                        strSQL = new StringBuffer();
                        strSQL.append("UPDATE TP_BKE_CHUYEN_TIEN a SET a.TT_GOC = 0 WHERE a.PHUONG_THUC_PH IN ('TPKB','PHTT') and  a.so_lenh = '" + vo.getSo_lenh() + "' ");
                        executeStatement(strSQL.toString(), null, conn);
                    }
                }
            }
            return (long)nExc;
        }

        return new Long("0");
    }

    public void updateHNX(String so_lenh) throws Exception {
        Vector v_param = new Vector();
        //L?y ID t? seq
        Long lID = new Long("0");
        StringBuffer strSQL = new StringBuffer();
        strSQL.append("UPDATE TP_KQPH a SET SO_LENH = null \n" +
                "WHERE a.so_lenh = '" + so_lenh + "'");

        executeStatement(strSQL.toString(), v_param, conn);
    }

    public void updateTPKB(String so_lenh) throws Exception {
        Vector v_param = new Vector();
        //L?y ID t? seq
        Long lID = new Long("0");
        StringBuffer strSQL = new StringBuffer();
        strSQL.append("UPDATE TP_KQPH_TIN_PHIEU a SET SO_LENH_PHI = null \n" +
                "WHERE a.SO_LENH_PHI = '" + so_lenh + "'");

        executeStatement(strSQL.toString(), v_param, conn);
    }

    public ResultSet getLenhChiTraNoCTList(String idLenh) throws Exception {
        ResultSet reval = null;
        String whereClause = "";
        Vector vParam = new Vector();
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append("SELECT \n" +
                    "T.CHUONG, T.CTMT, T.GUID, \n" +
                    "   T.ID_LENH_CHI_TRA_NO, T.NDKT, T.NGANH_KT, \n" +
                    "   T.NGUON, T.NOI_DUNG, T.SO_TIEN_NGTE, \n" +
                    "   T.SO_TIEN_VND \n" +
                    "FROM TPCP_OWNER.TP_LENH_CHI_TRA_NO_CTIET T WHERE T.ID_LENH_CHI_TRA_NO =" + idLenh);
            reval = executeSelectStatement(strSQL.toString(), vParam, conn);

        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO + ".getLenhChiTraNoCTList(): " + ex.getMessage(), ex);
        }
        return reval;
    }

    public Collection getLenhChiTraNoCTList2(String idLenh) throws Exception {
        Collection reval = null;
        String whereClause = "";
        Vector vParam = new Vector();
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append("SELECT \n" +
                    "T.CHUONG, T.CTMT, T.GUID, \n" +
                    "   T.ID_LENH_CHI_TRA_NO, T.NDKT, T.NGANH_KT, \n" +
                    "   T.NGUON, T.NOI_DUNG, T.SO_TIEN_NGTE, \n" +
                    "   T.SO_TIEN_VND,A.LOAI_TT \n" +
                    "FROM TPCP_OWNER.TP_LENH_CHI_TRA_NO_CTIET T INNER JOIN TP_TSO_HACH_TOAN A ON A.NGANH_KT =T.NGANH_KT " + "WHERE T.ID_LENH_CHI_TRA_NO =" + idLenh);
            reval = executeSelectStatement(strSQL.toString(), vParam, CLASS_NAME_VO_LENH_TTN_CT, conn);

        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO + ".getLenhChiTraNoCTList2(): " + ex.getMessage(), ex);
        }
        return reval;
    }
    //Thanh toan phi qua HNX

    public Collection getListTTPhiHNX(Map<String, Object> map, Integer page, Integer count, Integer[] totalCount) throws Exception {
        Collection listLenhTraNo = new ArrayList();
        String sProcedureName = "{call THANHTOAN.thanh_toan_phi_hnx(?,?,?,?,?,?,?)}";
        Vector vParam = new Vector();
        ResultSet rs = null;
        CallableStatement callableStatement = null;

        StringBuffer strSQL = new StringBuffer();
        try {
            callableStatement = conn.prepareCall(sProcedureName);
            callableStatement.setString(1, String.valueOf(map.get("ngay_to_chuc_ph_tu_ngay")));
            callableStatement.setString(2, String.valueOf(map.get("ngay_to_chuc_ph_den_ngay")));
            callableStatement.setString(3, String.valueOf(map.get("ngay_ph_tu_ngay")));
            callableStatement.setString(4, String.valueOf(map.get("ngay_ph_den_ngay")));
            callableStatement.registerOutParameter(5, Types.VARCHAR);
            callableStatement.registerOutParameter(6, Types.VARCHAR);
            callableStatement.registerOutParameter(7, Types.VARCHAR);
            callableStatement.executeUpdate();
            String guid_lenh = callableStatement.getString(5);
            String strErrCode = callableStatement.getString(6);
            String strErrDesc = callableStatement.getString(7);
            if (guid_lenh != null && !guid_lenh.isEmpty()) {
                strSQL.append("SELECT \n" +
                        "   T.CAN_CU, T.CAP_NS, T.DVI_NHAN, T.DVQHNS, T.GUID, T.LOAI_TIEN, \n" +
                        "   T.MA_TPCP, T.NAM_NS, to_char(T.NGAY_CHUYEN,'dd/MM/yyyy') NGAY_CHUYEN, \n" +
                        "   to_char(T.NGAY_DEN_HAN_TT,'dd/MM/yyyy') NGAY_DEN_HAN_TT, to_char(T.NGAY_DUYET,'dd/MM/yyyy') NGAY_DUYET, to_char(T.NGAY_SUA_CUOI,'dd/MM/yyyy') NGAY_SUA_CUOI, \n" +
                        "   to_char(T.NGAY_TAO,'dd/MM/yyyy') NGAY_TAO, T.NGUOI_DUYET, T.NGUOI_SUA_CUOI, \n" +
                        "   T.NGUOI_TAO, T.NH_NHAN, T.PTHUC_PH, T.SO_LENH, T.SO_TIEN_NGTE, T.SO_TIEN_VND, \n" +
                        "   T.SO_TK_NHAN, T.TY_GIA , T.DOT_PH , T.TRANG_THAI,LY_DO_TU_CHOI ," +
                        "(SELECT B.NAME FROM TP_DM_TRANG_THAI B WHERE T.TRANG_THAI=B.ID_STATUS ) AS TEN_TRANG_THAI ," + " T.SO_TIEN_VND AS TONG_TIEN , \n" +
                        " T.SO_TIEN_NGTE AS TONG_TIEN_USD\n" +
                        " FROM TP_LENH_CHI_TRA_NO T WHERE T.GUID in " + guid_lenh);
                listLenhTraNo = executeSelectStatement(strSQL.toString(), vParam, CLASS_NAME_VO_LENH_TTN, conn);
            }
        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO + ".getListTTPhiHNX(): " + ex.getMessage(), ex);
        }
        return listLenhTraNo;
    }

    public Collection getListGocTinPhieu(Map<String, Object> map, Integer page, Integer count, Integer[] totalCount) throws DAOException {
        Collection listLenhTraNo = new ArrayList();
        String sProcedureName = "{call THANHTOAN.thanh_toan_goc_tin_phieu(?,?,?,?,?)}";
        Vector vParam = new Vector();
        ResultSet rs = null;
        CallableStatement callableStatement = null;

        StringBuffer strSQL = new StringBuffer();
        try {
            callableStatement = conn.prepareCall(sProcedureName);
            callableStatement.setString(1, String.valueOf(map.get("ma_tpcp")));
            callableStatement.setString(2, String.valueOf(map.get("phuong_thuc_ph")));
            callableStatement.setString(3, String.valueOf(map.get("tu_ngay")));
            callableStatement.setString(4, String.valueOf(map.get("den_ngay")));
            callableStatement.registerOutParameter(5, Types.VARCHAR);
            callableStatement.executeUpdate();
            String guid_lenh = callableStatement.getString(5);
            if (guid_lenh != null && !guid_lenh.isEmpty()) {
                strSQL.append("SELECT \n" +
                        "   T.CAN_CU, T.CAP_NS, T.DVI_NHAN, T.DVQHNS, T.GUID, T.LOAI_TIEN, \n" +
                        "   T.MA_TPCP, T.NAM_NS, to_char(T.NGAY_CHUYEN,'dd/MM/yyyy') NGAY_CHUYEN, \n" +
                        "   to_char(T.NGAY_DEN_HAN_TT,'dd/MM/yyyy') NGAY_DEN_HAN_TT, to_char(T.NGAY_DUYET,'dd/MM/yyyy') NGAY_DUYET, to_char(T.NGAY_SUA_CUOI,'dd/MM/yyyy') NGAY_SUA_CUOI, \n" +
                        "   to_char(T.NGAY_TAO,'dd/MM/yyyy') NGAY_TAO, T.NGUOI_DUYET, T.NGUOI_SUA_CUOI, \n" +
                        "   T.NGUOI_TAO, T.NH_NHAN, T.PTHUC_PH, T.SO_LENH, T.SO_TIEN_NGTE, T.SO_TIEN_VND, \n" +
                        "   T.SO_TK_NHAN, T.TY_GIA , T.DOT_PH , T.TRANG_THAI,LY_DO_TU_CHOI ," +
                        "(SELECT B.NAME FROM TP_DM_TRANG_THAI B WHERE T.TRANG_THAI=B.ID_STATUS ) AS TEN_TRANG_THAI ," + " T.SO_TIEN_VND AS TONG_TIEN , \n" +
                        " T.SO_TIEN_NGTE AS TONG_TIEN_USD\n" +
                        " FROM TP_LENH_CHI_TRA_NO T WHERE T.GUID in " + guid_lenh);
                listLenhTraNo = executeSelectStatement(strSQL.toString(), vParam, CLASS_NAME_VO_LENH_TTN, conn);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new DAOException(CLASS_NAME_DAO + ".getListGocTinPhieu(): " + ex.getMessage(), ex);
        }
        return listLenhTraNo;
    }

    public Collection getListPhiTinPhieu(Map<String, Object> map, Integer page, Integer count, Integer[] totalCount) throws DAOException {
        Collection listLenhTraNo = new ArrayList();
        String sProcedureName = "{call THANHTOAN.thanh_toan_phi_tin_phieu(?,?,?,?,?)}";
        Vector vParam = new Vector();
        ResultSet rs = null;
        CallableStatement callableStatement = null;

        StringBuffer strSQL = new StringBuffer();
        try {
            callableStatement = conn.prepareCall(sProcedureName);
            callableStatement.setString(1, String.valueOf(map.get("tu_ngay_tcph")));
            callableStatement.setString(2, String.valueOf(map.get("den_ngay_tcph")));
            callableStatement.setString(3, String.valueOf(map.get("tu_ngay_ph")));
            callableStatement.setString(4, String.valueOf(map.get("den_ngay_ph")));
            callableStatement.registerOutParameter(5, Types.VARCHAR);
            callableStatement.executeUpdate();
            String guid_lenh = callableStatement.getString(5);
            if (guid_lenh != null && !guid_lenh.isEmpty()) {
                strSQL.append("SELECT \n" +
                        "   T.CAN_CU, T.CAP_NS, T.DVI_NHAN, T.DVQHNS, T.GUID, T.LOAI_TIEN, \n" +
                        "   T.MA_TPCP, T.NAM_NS, to_char(T.NGAY_CHUYEN,'dd/MM/yyyy') NGAY_CHUYEN, \n" +
                        "   to_char(T.NGAY_DEN_HAN_TT,'dd/MM/yyyy') NGAY_DEN_HAN_TT, to_char(T.NGAY_DUYET,'dd/MM/yyyy') NGAY_DUYET, to_char(T.NGAY_SUA_CUOI,'dd/MM/yyyy') NGAY_SUA_CUOI, \n" +
                        "   to_char(T.NGAY_TAO,'dd/MM/yyyy') NGAY_TAO, T.NGUOI_DUYET, T.NGUOI_SUA_CUOI, \n" +
                        "   T.NGUOI_TAO, T.NH_NHAN, T.PTHUC_PH, T.SO_LENH, T.SO_TIEN_NGTE, T.SO_TIEN_VND, \n" +
                        "   T.SO_TK_NHAN, T.TY_GIA , (SELECT A.DOT_PH FROM TP_BKE_CHUYEN_TIEN A WHERE T.MA_TPCP=A.MA_TPCP GROUP BY A.DOT_PH) AS DOT_PH , T.TRANG_THAI,LY_DO_TU_CHOI ," +
                        "(SELECT B.NAME FROM TP_DM_TRANG_THAI B WHERE T.TRANG_THAI=B.ID_STATUS ) AS TEN_TRANG_THAI ," +
                        " (SELECT SUM(C.SO_TIEN_VND) FROM TP_LENH_CHI_TRA_NO_CTIET C WHERE T.GUID=C.ID_LENH_CHI_TRA_NO ) AS TONG_TIEN , \n" +
                        " (SELECT SUM(C.SO_TIEN_NGTE) FROM TP_LENH_CHI_TRA_NO_CTIET C WHERE T.GUID=C.ID_LENH_CHI_TRA_NO ) AS TONG_TIEN_USD\n" +
                        " FROM TP_LENH_CHI_TRA_NO T WHERE T.GUID in " + guid_lenh);
                listLenhTraNo = executeSelectStatement(strSQL.toString(), vParam, CLASS_NAME_VO_LENH_TTN, conn);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new DAOException(CLASS_NAME_DAO + ".getListPhiTinPhieu(): " + ex.getMessage(), ex);
        }
        return listLenhTraNo;
    }
}
