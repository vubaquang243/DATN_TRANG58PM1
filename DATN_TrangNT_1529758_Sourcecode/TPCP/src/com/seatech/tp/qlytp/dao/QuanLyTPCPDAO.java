package com.seatech.tp.qlytp.dao;

import com.seatech.framework.datamanager.AppDAO;
import com.seatech.framework.datamanager.DateParameter;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.exception.DAOException;
import com.seatech.framework.exception.TPCPException;
import com.seatech.framework.utils.StringUtil;
import com.seatech.tp.qlytp.vo.PhuongThucPhatHanhVO;
import com.seatech.tp.qlytp.vo.QuanLyTPCPVO;

import java.sql.Connection;

import java.text.SimpleDateFormat;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

public class QuanLyTPCPDAO extends AppDAO {
    private Connection conn = null;
    private String CLASS_NAME_DAO = "com.seatech.tp.qlytp.dao.QuanLyTPCPDAO";
    private String CLASS_NAME_VO = "com.seatech.tp.qlytp.vo.QuanLyTPCPVO";
    private String CLASS_NAME_PTPH_VO = "com.seatech.tp.qlytp.vo.PhuongThucPhatHanhVO";
    private static String STRING_EMPTY = "";

    public QuanLyTPCPDAO(Connection conn) {
        this.conn = conn;
    }

    public Long insert(QuanLyTPCPVO vo) {

        Vector v_param = new Vector();
        //L?y ID t? seq
        Long lID = new Long("0");
        try {
            StringBuffer strSQL = new StringBuffer();
            String sql = "";
            if (vo.getGuid() != null && !vo.getGuid().equals("")) {
                sql = generateSQLUpdate(vo, v_param, "TP_DM_TPCP", conn);
                strSQL.append(sql);
                strSQL.append("WHERE GUID = ?");
                v_param.add(new Parameter(vo.getGuid(), true));
                lID = new Long(vo.getGuid());
            } else {
                lID = getSeqNextValue("TP_MA_TPCP_SEQ", conn);
                vo.setGuid(String.valueOf(lID));
                sql = generateSQLInsert(vo, v_param, "TP_DM_TPCP", conn);
                strSQL.append(sql);
            }

            if (executeStatement(strSQL.toString(), v_param, conn) == 1)
                return lID;
            else
                return new Long("0");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Long("0");
    }

    public QuanLyTPCPVO getDMTPCP(String Ma_TPCP) throws Exception, TPCPException {
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append("SELECT MA_TP FROM TP_DM_TPCP WHERE MA_TP = '" + Ma_TPCP + "'");
            return (QuanLyTPCPVO)findByPK(strSQL.toString(), null, CLASS_NAME_VO, conn);
        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO + ".getMaTPCPbject(): " + ex.getMessage(), ex);
        }
    }

    public Collection getListPTPH(PhuongThucPhatHanhVO vo) throws Exception {
        Collection reval = null;
        StringBuffer strSQL = new StringBuffer();
        try {

            strSQL.append("SELECT GUID,  MA_PHUONG_THUC,  TEN_PHUONG_THUC,  TRANG_THAI,NGAY_TAO,  NGUOI_TAO" + "  FROM TP_PHUONG_THUC_PHANH a");
            strSQL.append(" ORDER BY a.TEN_PHUONG_THUC");
            reval = executeSelectStatement(strSQL.toString(), null, CLASS_NAME_PTPH_VO);

        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO + ".getListPTPH(): " + ex.getMessage(), ex);
        }
        return reval;
    }


    public Collection getListTPCPPaging(QuanLyTPCPVO vo, Integer page, Integer count, Integer[] totalCount) throws Exception {
        Collection reval = null;
        String whereClause = "";
        Vector vParam = new Vector();
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append("SELECT a.GUID,  a.MA_TP,  a.PHUONG_THUC_PH, b.TEN_PHUONG_THUC TEN_PHUONG_THUC_PH,  a.NOI_CAP, (c.KY_HAN || ' ' || c.LOAI_KY_HAN) KY_HAN," +
                    "a.TRANG_THAI,  a.NGUOI_TAO,to_char(a.NGAY_TAO,'dd/MM/yyyy') NGAY_TAO, a.NGUOI_SUA_CUOI,a.DAC_BIET,\n" +
                    "to_char(a.NGAY_SUA_CUOI,'dd/MM/yyyy')  NGAY_SUA_CUOI" +
                    "  FROM TP_DM_TPCP a, TP_PHUONG_THUC_PHANH b, TP_DM_KY_HAN c where a.PHUONG_THUC_PH = b.MA_PHUONG_THUC and a.KY_HAN = c.GUID ");
            if (vo.getMa_tp() != null && !"".equals(vo.getMa_tp())) {
                whereClause += " and  upper(a.MA_TP) like '%" + vo.getMa_tp().toUpperCase() + "%'";
            }
            if (vo.getNoi_cap() != null && !"".equals(vo.getNoi_cap())) {
                whereClause += " and  a.NOI_CAP  = ?";
                vParam.add(new Parameter(vo.getNoi_cap(), true));
            }
            if (vo.getKy_han() != null && !"".equals(vo.getKy_han())) {
                whereClause += " and  a.KY_HAN  = ?";
                vParam.add(new Parameter(vo.getKy_han(), true));
            }
            if (vo.getNgay_tao_tu_ngay() != null && !"".equals(vo.getNgay_tao_tu_ngay()) && vo.getNgay_tao_den_ngay() != null && !"".equals(vo.getNgay_tao_den_ngay())) {
                whereClause += " and trunc(a.NGAY_TAO) >= ? and trunc(a.NGAY_TAO)<= ?";

                vParam.add(new DateParameter(StringUtil.StringToDate(vo.getNgay_tao_tu_ngay(), "dd/MM/yyyy"), true));
                vParam.add(new DateParameter(StringUtil.StringToDate(vo.getNgay_tao_den_ngay(), "dd/MM/yyyy"), true));
            }
            if (whereClause != null && !whereClause.equalsIgnoreCase("")) {
                strSQL.append(whereClause);
            }
            strSQL.append(" ORDER BY a.NGAY_TAO DESC");
            reval = executeSelectWithPaging(conn, strSQL.toString(), vParam, CLASS_NAME_VO, page, count, totalCount);
        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO + ".getListTPCPPaging(): " + ex.getMessage(), ex);
        }
        return reval;
    }

    public QuanLyTPCPVO getTPCPObject(String guid) throws Exception, TPCPException {
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append("SELECT GUID,  MA_TP,  PHUONG_THUC_PH,  NOI_CAP,KY_HAN, " +
                    "TRANG_THAI,  NGUOI_TAO,to_char(NGAY_TAO,'dd/MM/yyyy') NGAY_TAO,  NGUOI_SUA_CUOI, DAC_BIET,\n" +
                    "to_char(NGAY_SUA_CUOI,'dd/MM/yyyy')  NGAY_SUA_CUOI, LOAI_TPCP,DVI_MUA  FROM TP_DM_TPCP");
            if (guid != null && !STRING_EMPTY.equals(guid)) {
                strSQL.append(" WHERE GUID = '" + guid + "'");
            }
            return (QuanLyTPCPVO)findByPK(strSQL.toString(), null, CLASS_NAME_VO, conn);
        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO + ".getTPCPObject(): " + ex.getMessage(), ex);
        }
    }

    public QuanLyTPCPVO getTPCPObjectTin_Phieu(Map<String, Object> map) throws Exception, TPCPException {
        StringBuffer strSQL = new StringBuffer();
        StringBuffer strSQL2 = new StringBuffer();
        Vector vParam = new Vector();
        try {
            strSQL.append("SELECT c.GUID,  a.MA_TP,  a.PHUONG_THUC_PH,  a.NOI_CAP, (c.KY_HAN || ' ' || c.LOAI_KY_HAN) KY_HAN," +
                    " a.TRANG_THAI,  a.NGUOI_TAO, to_char(a.NGAY_TAO,'dd/MM/yyyy') NGAY_TAO,  a.NGUOI_SUA_CUOI,\n" +
                    " to_char(a.NGAY_SUA_CUOI,'dd/MM/yyyy')  NGAY_SUA_CUOI, a.DAC_BIET " + " FROM TP_DM_TPCP a , TP_DM_KY_HAN c ");
            Set<String> keySet = map.keySet();
            Iterator<String> keySetIterator = keySet.iterator();
            strSQL2.append(" and a.KY_HAN = c.GUID ");
            while (keySetIterator.hasNext()) {
                String key = keySetIterator.next();
                String value = map.get(key).toString();
                if (value != null && !STRING_EMPTY.equals(value) && key != "TRANG_THAI") {
                    strSQL2.append(" and a." + key + " = ? ");
                    vParam.add(new Parameter(value, true));
                }
                if (key == "TRANG_THAI") {
                    strSQL2.append(" and a." + key + " != ? ");
                    vParam.add(new Parameter(value, true));
                }
            }

            if (strSQL2.toString().length() > 0) {
                strSQL.append(" WHERE 1 = 1 " + strSQL2.toString());
            }
            return (QuanLyTPCPVO)findByPK(strSQL.toString(), vParam, CLASS_NAME_VO, conn);
        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO + ".getTPCPObjectTin_Phieu(): " + ex.getMessage(), ex);
        }
    }

    public QuanLyTPCPVO getTTDTObject(Map<String, Object> map) throws Exception, TPCPException {
        StringBuffer strSQL = new StringBuffer();
        StringBuffer strSQL2 = new StringBuffer();
        Vector vParam = new Vector();
        try {
            strSQL.append("SELECT GUID,  MA_TP,  PHUONG_THUC_PH,  NOI_CAP, KY_HAN," + "TRANG_THAI,  NGUOI_TAO,to_char(NGAY_TAO,'dd/MM/yyyy') NGAY_TAO,  NGUOI_SUA_CUOI,\n" +
                    "to_char(NGAY_SUA_CUOI,'dd/MM/yyyy')  NGAY_SUA_CUOI, DAC_BIET " + " FROM TP_DM_TPCP  ");
            Set<String> keySet = map.keySet();
            Iterator<String> keySetIterator = keySet.iterator();
            while (keySetIterator.hasNext()) {
                String key = keySetIterator.next();
                String value = map.get(key).toString();
                if (value != null && !STRING_EMPTY.equals(value) && key != "KY_HAN") {
                    strSQL2.append("and " + key + " = ? ");
                    vParam.add(new Parameter(value, true));
                } else if (key == "KY_HAN") {
                    strSQL2.append(" and  KY_HAN IN (SELECT GUID FROM TP_DM_KY_HAN WHERE LOAI_TPCP='TIN_PHIEU')");
                }

            }

            if (strSQL2.toString().length() > 0) {
                strSQL.append(" WHERE 1 = 1 " + strSQL2.toString());
            }
            return (QuanLyTPCPVO)findByPK(strSQL.toString(), vParam, CLASS_NAME_VO, conn);
        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO + ".getTTDTObject(): " + ex.getMessage(), ex);
        }
    }


    public Collection getLstTPCP_TIN_PHIEU() throws Exception {
        Collection reval = null;
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append(" SELECT a.GUID,  a.MA_TP,  a.PHUONG_THUC_PH,  a.NOI_CAP, a.KY_HAN," + " a.TRANG_THAI,   a.NGUOI_SUA_CUOI,\n" +
                    "  a.DAC_BIET " + " FROM TP_DM_TPCP a" + " WHERE a.PHUONG_THUC_PH = 'PHTT' AND a.TRANG_THAI != '03' " + " AND a.MA_TP " +
                    " NOT IN (SELECT c.MA_TP FROM TP_HD_BAN_TIN_PHIEU c)");
            reval = executeSelectStatement(strSQL.toString(), null, CLASS_NAME_VO);
        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO + ".getLstTPCP_TIN_PHIEU(): " + ex.getMessage(), ex);
        }
        return reval;
    }

    public Collection getAllListTPCP_TIN_PHIEU() throws Exception {
        Collection reval = null;
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append(" SELECT a.GUID,  a.MA_TP,  a.PHUONG_THUC_PH,  a.NOI_CAP, a.KY_HAN, " + "  a.TRANG_THAI,   a.NGUOI_SUA_CUOI,\n" +
                    "  a.DAC_BIET " + "  FROM TP_DM_TPCP a " + "  WHERE a.PHUONG_THUC_PH = 'PHTT' ");
            reval = executeSelectStatement(strSQL.toString(), null, CLASS_NAME_VO);
        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO + ".getLstTPCP_TIN_PHIEU(): " + ex.getMessage(), ex);
        }
        return reval;
    }

    public Collection getAllListTPCP() throws Exception {
        Collection reval = null;
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append(" SELECT GUID,  MA_TP,  PHUONG_THUC_PH,  NOI_CAP,KY_HAN," + "TRANG_THAI,  NGUOI_TAO,to_char(NGAY_TAO,'dd/MM/yyyy') NGAY_TAO,  NGUOI_SUA_CUOI,\n" +
                    " to_char(NGAY_SUA_CUOI,'dd/MM/yyyy')  NGAY_SUA_CUOI, DAC_BIET" + " FROM TP_DM_TPCP a");
            strSQL.append(" ORDER BY a.NGAY_TAO DESC");
            reval = executeSelectStatement(strSQL.toString(), null, CLASS_NAME_VO);
        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO + ".getAllListTPCP(): " + ex.getMessage(), ex);
        }
        return reval;
    }

    public Collection getAllListTPCP_Ban_Le() throws Exception {
        Collection reval = null;
        Vector vParam = null;
        StringBuffer strSQL = new StringBuffer();
        try {
            Date timeCurrent= new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yy");
            String date = formatter.format(timeCurrent);
            strSQL.append(" SELECT GUID,  MA_TP,  PHUONG_THUC_PH,  NOI_CAP,KY_HAN," + "TRANG_THAI,  NGUOI_TAO,to_char(NGAY_TAO,'dd/MM/yyyy') NGAY_TAO,  NGUOI_SUA_CUOI,\n" +
                    " to_char(NGAY_SUA_CUOI,'dd/MM/yyyy')  NGAY_SUA_CUOI, DAC_BIET " +
                    " FROM TP_DM_TPCP a" +
                    " WHERE PHUONG_THUC_PH IN ('TWSC', 'TL', 'TWBH') AND trang_thai != '03' " +
                    " AND a.MA_TP NOT IN(SELECT b.MA_TPCP FROM tp_kqph_ban_le b WHERE b.NGAY_DAO_HAN <= '"+date+"')");
            strSQL.append(" ORDER BY a.NGAY_TAO DESC");
            reval = executeSelectStatement(strSQL.toString(), vParam, CLASS_NAME_VO);
        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO + ".getAllListTPCP_Ban_le(): " + ex.getMessage(), ex);
        }
        return reval;
    }

    public Collection getAllListTPCP_DT() throws Exception {
        Collection reval = null;
        StringBuffer strSQL = new StringBuffer();
        try {
          Date timeCurrent= new Date();
          SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yy");
          String date = formatter.format(timeCurrent);
            strSQL.append("SELECT GUID,  MA_TP,  PHUONG_THUC_PH,  NOI_CAP,KY_HAN," + "TRANG_THAI,  NGUOI_TAO,to_char(NGAY_TAO,'dd/MM/yyyy') NGAY_TAO,  NGUOI_SUA_CUOI,\n" +
                    "to_char(NGAY_SUA_CUOI,'dd/MM/yyyy')  NGAY_SUA_CUOI" + "  FROM TP_DM_TPCP a WHERE a.PHUONG_THUC_PH= 'TD' " +
                   "AND a.MA_TP NOT IN(SELECT b.MA_TPCP FROM TP_THONG_TIN_DAU_THAU b WHERE b.NGAY_DAO_HAN  <= '"+date+"')");
            strSQL.append(" ORDER BY a.NGAY_TAO DESC");
            reval = executeSelectStatement(strSQL.toString(), null, CLASS_NAME_VO);
        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO + ".getAllListTPCP_DT(): " + ex.getMessage(), ex);
        }
        return reval;
    }

    public Long delete(QuanLyTPCPVO vo) throws Exception {
        Vector v_param = new Vector();
        StringBuffer strSQL = new StringBuffer();
        strSQL.append(" DELETE TP_DM_TPCP WHERE GUID = ?");
        int nExc = 0;
        if (vo.getGuid() != null) {
            v_param.add(new Parameter(vo.getGuid(), true));
            nExc = executeStatement(strSQL.toString(), v_param, conn);
            if (nExc == 1)
                return Long.parseLong(vo.getGuid());
        }
        return new Long("0");
    }

    public Collection getAllListTPCPFromDotPH(String dot_ph) throws Exception {
        Collection reval = null;
        Vector v_param = new Vector();
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append("SELECT DISTINCT b.MA_TPCP ma_tp\n" +
                    "FROM TP_DM_TPCP a,\n" +
                    "  TP_KQPH b\n" +
                    "WHERE a.MA_TP = b.MA_TPCP and b.TRANG_THAI = '02'\n" +
                    "AND b.DOT_DT  = ? \n" +
                    "UNION ALL\n" +
                    "SELECT DISTINCT c.MA_TPCP ma_tp FROM TP_KQPH_BAN_LE c WHERE c.TRANG_THAI = '02' and c.DOT_PH = ? " + 
                    "UNION ALL \n" +
                    "select DISTINCT MA_TPCP ma_tp from TP_KQPH_TIN_PHIEU where TRANG_THAI = '02' and DOT_PH = ? " +
                    "UNION ALL \n" +
                    "select DISTINCT ma_tp from TP_HD_BAN_TIN_PHIEU where TRANG_THAI = '02' and SO_HD = ? ");
            v_param.add(new Parameter(dot_ph, true));
            v_param.add(new Parameter(dot_ph, true));
            v_param.add(new Parameter(dot_ph, true));
            v_param.add(new Parameter(dot_ph, true));
            reval = executeSelectStatement(strSQL.toString(), v_param, CLASS_NAME_VO);
        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO + ".getAllListTPCPFromDotPH(): " + ex.getMessage(), ex);
        }
        return reval;
    }

    public Collection getAllListTPCPFromNgayPH(String ngay_ph) throws Exception {
        Collection reval = null;
        Vector v_param = new Vector();
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append("select DISTINCT MA_TPCP  MA_TP from TP_KQPH ph where ph.TRANG_THAI = '02' AND trunc(ph.NGAY_PH) = ? " + 
            "union all\n" + 
            "select DISTINCT MA_TPCP MA_TP from TP_KQPH_BAN_LE bl where bl.TRANG_THAI = '02' AND  trunc(bl.NGAY_PH) = ? " + 
            "union all\n" + 
            "select DISTINCT MA_TPCP MA_TP from TP_KQPH_TIN_PHIEU tp where tp.TRANG_THAI = '02' AND  trunc(tp.NGAY_PH) = ? " + 
            "union all\n" + 
            "select DISTINCT hd.MA_TP MA_TP from TP_HD_BAN_TIN_PHIEU hd where hd.TRANG_THAI = '02' AND  trunc(hd.NGAY_PH) = ? ");
            v_param.add(new DateParameter(StringUtil.StringToDate(ngay_ph, "dd/MM/yyyy"), true));
            v_param.add(new DateParameter(StringUtil.StringToDate(ngay_ph, "dd/MM/yyyy"), true));
            v_param.add(new DateParameter(StringUtil.StringToDate(ngay_ph, "dd/MM/yyyy"), true));
            v_param.add(new DateParameter(StringUtil.StringToDate(ngay_ph, "dd/MM/yyyy"), true));
            reval = executeSelectStatement(strSQL.toString(), v_param, CLASS_NAME_VO);
        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO + ".getAllListTPCPFromNgayPH(): " + ex.getMessage(), ex);
        }
        return reval;
    }
}

