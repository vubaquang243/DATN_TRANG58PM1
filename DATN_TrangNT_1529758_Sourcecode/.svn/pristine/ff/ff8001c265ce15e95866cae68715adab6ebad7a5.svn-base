package com.seatech.tp.dmtraichu.dao;

import com.seatech.framework.datamanager.AppDAO;
import com.seatech.framework.datamanager.DateParameter;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.exception.DAOException;
import com.seatech.framework.exception.TPCPException;
import com.seatech.tp.dmtraichu.vo.DMTraiChuVO;

import java.sql.Connection;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

public class DMTraiChuDAO extends AppDAO {
    private Connection conn = null;
    private String CLASS_NAME_DAO = "com.seatech.tp.dmtraichu.dao.DMTraiChuDAO";
    private String CLASS_NAME_VO = "com.seatech.tp.dmtraichu.vo.DMTraiChuVO";
    private static String STRING_EMPTY = "";

    public DMTraiChuDAO(Connection conn) {
        this.conn = conn;
    }

    public Long delete(DMTraiChuVO vo) throws Exception {
        Vector v_param = new Vector();
        StringBuffer strSQL = new StringBuffer();
        strSQL.append(" DELETE TP_DM_DVI_SO_HUU WHERE GUID = ?");
        int nExc = 0;
        if (vo.getGuid() != null) {
            v_param.add(new Parameter(vo.getGuid(), true));
            nExc = executeStatement(strSQL.toString(), v_param, conn);
            if (nExc == 1)
                return Long.parseLong(vo.getGuid());
        }
        return new Long("0");
    }

    public Long insert(DMTraiChuVO vo) {

        Vector v_param = new Vector();
        //L?y ID t? seq
        Long lID = new Long("0");
        try {
            StringBuffer strSQL = new StringBuffer();
            String sql = "";
            if (vo.getGuid() != null && !vo.getGuid().equals("")) {
                sql = generateSQLUpdate2(vo, v_param, "TP_DM_DVI_SO_HUU", conn);
                strSQL.append(sql);
                strSQL.append("WHERE GUID = ?");
                v_param.add(new Parameter(vo.getGuid(), true));
                lID = new Long(vo.getGuid());
            } else {
                lID = getSeqNextValue("TP_DM_DVI_SO_HUU_SEQ", conn);
                vo.setGuid(String.valueOf(lID));
                sql = generateSQLInsert(vo, v_param, "TP_DM_DVI_SO_HUU", conn);
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

    public Collection getDMTraiChu(Map<String, Object> map) throws Exception, TPCPException {
        StringBuffer strSQL = new StringBuffer();
        StringBuffer strSQL2 = new StringBuffer();
        Vector vParam = new Vector();
        Collection reval = null;
        try {
            strSQL.append("SELECT GUID,  nvl(MA_TV,'') MA_TV ,  MA_CHU_SO_HUU,  TEN_DVI_SO_HUU,  LOAI_HINH,  CO_CAU,  TRANG_THAI FROM TP_DM_DVI_SO_HUU ");
            if (map != null) {
                Set<String> keySet = map.keySet();
                Iterator<String> keySetIterator = keySet.iterator();
                while (keySetIterator.hasNext()) {
                    String key = keySetIterator.next();
                    String value = map.get(key).toString();
                    if (key != null && "TEN_DVI_SO_HUU".equalsIgnoreCase(key.toUpperCase())) {
                        strSQL2.append("AND lower(TEN_DVI_SO_HUU) like '%" + value.toLowerCase() + "%'");
                    } else {
                        if (value != null && !STRING_EMPTY.equals(value)) {
                            strSQL2.append(" AND " + key + " = ? ");
                            vParam.add(new Parameter(value, true));
                        }
                    }
                }
                if (strSQL2.toString().length() > 0) {
                    strSQL.append(" WHERE 1 = 1 " + strSQL2.toString());
                }
            }
            reval = executeSelectStatement(strSQL.toString(), vParam, CLASS_NAME_VO, conn);
        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO + ".getDmTraiChuObject(): " + ex.getMessage(), ex);
        }
        return reval;
    }

    public Collection getDMTraiChu() throws Exception, TPCPException {
        StringBuffer strSQL = new StringBuffer();
        StringBuffer strSQL2 = new StringBuffer();
        Vector vParam = new Vector();
        Collection reval = null;
        try {
            strSQL.append("SELECT GUID,  MA_TV,  MA_CHU_SO_HUU,  TEN_DVI_SO_HUU,  LOAI_HINH,  CO_CAU,  TRANG_THAI FROM TP_DM_DVI_SO_HUU ");
            strSQL.append(" WHERE  1= 1");
            reval = executeSelectStatement(strSQL.toString(), vParam, CLASS_NAME_VO, conn);
        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO + ".getDmTraiChuObject(): " + ex.getMessage(), ex);
        }
        return reval;
    }

    public Collection getDMTraiChuBanLe() throws Exception, TPCPException {
        StringBuffer strSQL = new StringBuffer();
        StringBuffer strSQL2 = new StringBuffer();
        Vector vParam = new Vector();
        Collection reval = null;
        try {
            strSQL.append("SELECT GUID,  MA_TV,  MA_CHU_SO_HUU,  TEN_DVI_SO_HUU,  LOAI_HINH,  CO_CAU,  TRANG_THAI FROM TP_DM_DVI_SO_HUU ");
            strSQL.append(" WHERE BAN_LE  = '1'");
            reval = executeSelectStatement(strSQL.toString(), vParam, CLASS_NAME_VO, conn);
        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO + ".getDmTraiChuObject(): " + ex.getMessage(), ex);
        }
        return reval;
    }

    public Collection getDMTraiChuPaging(DMTraiChuVO vo, Integer page, Integer count, Integer[] totalCount) throws Exception {
        Collection reval = null;
        String whereClause = "";
        Vector vParam = new Vector();
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append("SELECT GUID,  MA_TV,  MA_CHU_SO_HUU,  TEN_DVI_SO_HUU,  LOAI_HINH,  CO_CAU,  TRANG_THAI, BAN_LE FROM TP_DM_DVI_SO_HUU a ");
            strSQL.append("WHERE 1=1 ");
            if (vo.getMa_chu_so_huu() != null && !"".equals(vo.getMa_chu_so_huu())) {
                whereClause += " and  upper(a.MA_CHU_SO_HUU) like '%" + vo.getMa_chu_so_huu().toUpperCase() + "%'";
            }
            if (vo.getTen_dvi_so_huu() != null && !"".equals(vo.getTen_dvi_so_huu())) {
                whereClause += " and  upper(a.TEN_DVI_SO_HUU) like '%" + vo.getTen_dvi_so_huu().toUpperCase() + "%'";
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

    public DMTraiChuVO getDMTraiChuObject(Map<String, Object> map) throws Exception, TPCPException {
        StringBuffer strSQL = new StringBuffer();
        StringBuffer strSQL2 = new StringBuffer();
        Vector vParam = new Vector();
        try {
            strSQL.append("SELECT GUID,  MA_TV,  MA_CHU_SO_HUU,  TEN_DVI_SO_HUU,  LOAI_HINH,  CO_CAU,  TRANG_THAI, BAN_LE FROM TP_DM_DVI_SO_HUU");
            Set<String> keySet = map.keySet();
            Iterator<String> keySetIterator = keySet.iterator();
            while (keySetIterator.hasNext()) {
                String key = keySetIterator.next();
                String value = map.get(key).toString();
                if (value != null && !STRING_EMPTY.equals(value)) {
                    strSQL2.append("and upper(" + key + ") = ? ");
                    vParam.add(new Parameter(value.toUpperCase(), true));
                }
            }
            if (strSQL2.toString().length() > 0) {
                strSQL.append(" WHERE 1 = 1 " + strSQL2.toString());
            }
            return (DMTraiChuVO)findByPK(strSQL.toString(), vParam, CLASS_NAME_VO, conn);
        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO + ".getDmTraiChuObject(): " + ex.getMessage(), ex);
        }
    }
}
