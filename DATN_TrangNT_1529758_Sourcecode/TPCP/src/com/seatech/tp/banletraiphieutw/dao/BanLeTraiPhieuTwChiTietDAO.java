package com.seatech.tp.banletraiphieutw.dao;

import com.seatech.framework.datamanager.AppDAO;

import com.seatech.framework.datamanager.AppDAO;
import com.seatech.framework.datamanager.DateParameter;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.exception.DAOException;
import com.seatech.framework.exception.TPCPException;
import com.seatech.framework.utils.StringUtil;
import com.seatech.tp.banletraiphieutw.vo.BanLeTraiPhieuTwChiTietVO;
import com.seatech.tp.banletraiphieutw.vo.BanLeTraiPhieuTwVO;

import java.sql.Connection;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

public class BanLeTraiPhieuTwChiTietDAO extends AppDAO {
    private Connection conn = null;
    private String CLASS_NAME_DAO =
        "com.seatech.tp.banletraiphieutw.dao.BanLeTraiPhieuTwChiTietDAO";
    private String CLASS_NAME_VO =
        "com.seatech.tp.banletraiphieutw.vo.BanLeTraiPhieuTwChiTietVO";
    private static String STRING_EMPTY = "";

    public BanLeTraiPhieuTwChiTietDAO(Connection conn) {
        super();
        this.conn = conn;
    }

    public Long insert(BanLeTraiPhieuTwChiTietVO vo) throws Exception {
        Vector v_param = new Vector();
        //L?y ID t? seq
        Long lID = new Long("0");
        StringBuffer strSQL = new StringBuffer();
        String sql = "";
        if (vo.getGuid() != null && !vo.getGuid().equals("")) {
            sql = generateSQLUpdate(vo, v_param, "TP_KQPH_BAN_LE_CTIET", conn);
            strSQL.append(sql);
            strSQL.append("WHERE GUID = ?");
            v_param.add(new Parameter(vo.getGuid(), true));
            lID = new Long(vo.getGuid());
        } else {
            lID = getSeqNextValue("TP_MA_BANLETWCTIET_SEQ", conn);
            vo.setGuid(String.valueOf(lID));
            sql = generateSQLInsert(vo, v_param, "TP_KQPH_BAN_LE_CTIET", conn);
            strSQL.append(sql);
        }
        if (executeStatement(strSQL.toString(), v_param, conn) == 1)
            return lID;
        else
            return new Long("0");
    }

    public Collection getListBanLeTwChiTietPaging(BanLeTraiPhieuTwChiTietVO vo,
                                                  Integer page, Integer count,
                                                  Integer[] totalCount) throws Exception {
        Collection reval = null;
        Vector vParam = new Vector();
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append("SELECT * FROM TP_KQPH_BAN_LE_CTIET");
            reval =
                    executeSelectWithPaging(conn, strSQL.toString(), vParam, CLASS_NAME_VO,
                                            page, count, totalCount);
        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO +
                                   ".getListBanLeTwChiTietPaging(): " +
                                   ex.getMessage(), ex);
        }
        return reval;
    }


    public Collection getListBanLeTwChiTietUpdate(Map<String, Object> map) throws Exception,
                                                                                   TPCPException {
        Collection reval = null;
        StringBuffer strSQL = new StringBuffer();
        StringBuffer strSQL2 = new StringBuffer();
        Vector vParam = new Vector();
        try {
            strSQL.append("SELECT GUID, DVI_SO_HUU, SO_DKSH, to_char(NGAY_CAP_DKSH,'dd/MM/yyyy') NGAY_CAP_DKSH, NO_CAP_DKSH,LOAI_HINH,QUOC_TICH, DIA_CHI,KL_DKY_MUA, SL_DKY_MUA,SO_TIEN_TT,MA_DVI_SO_HUU  FROM TP_KQPH_BAN_LE_CTIET");
            Set<String> keySet = map.keySet();
            Iterator<String> keySetIterator = keySet.iterator();
            while (keySetIterator.hasNext()) {
                String key = keySetIterator.next();
                String value = map.get(key).toString();
                if (value != null && !STRING_EMPTY.equals(value)) {
                    strSQL2.append("and " + key + " = ? ");
                    vParam.add(new Parameter(value, true));
                }
            }
            if (strSQL2.toString().length() > 0) {
                strSQL.append(" WHERE 1 = 1 " + strSQL2.toString());
            }
            reval = executeSelectStatement(strSQL.toString(), vParam,
                                          CLASS_NAME_VO, this.conn);
            return reval;
        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO +
                                   ".getBanLeTwChiTietObject(): " +
                                   ex.getMessage(), ex);
        }
    }
    
  public Collection getListBanLeTwChiTietView(Map<String, Object> map) throws Exception,
                                                                                 TPCPException {
      Collection reval = null;
      StringBuffer strSQL = new StringBuffer();
      StringBuffer strSQL2 = new StringBuffer();
      Vector vParam = new Vector();
      try {
          strSQL.append("SELECT GUID, DVI_SO_HUU, SO_DKSH, to_char(NGAY_CAP_DKSH,'dd/MM/yyyy') NGAY_CAP_DKSH," +
                       " NO_CAP_DKSH,decode(LOAI_HINH, 'TN','Trong nước','NN','Ngoài nước','') as LOAI_HINH,QUOC_TICH, DIA_CHI, SL_DKY_MUA,KL_DKY_MUA,SO_TIEN_TT,MA_DVI_SO_HUU  FROM TP_KQPH_BAN_LE_CTIET");
          Set<String> keySet = map.keySet();
          Iterator<String> keySetIterator = keySet.iterator();
          while (keySetIterator.hasNext()) {
              String key = keySetIterator.next();
              String value = map.get(key).toString();
              if (value != null && !STRING_EMPTY.equals(value)) {
                  strSQL2.append("and " + key + " = ? ");
                  vParam.add(new Parameter(value, true));
              }
          }
          if (strSQL2.toString().length() > 0) {
              strSQL.append(" WHERE 1 = 1 " + strSQL2.toString());
          }
          reval = executeSelectStatement(strSQL.toString(), vParam,
                                        CLASS_NAME_VO, this.conn);
          return reval;
      } catch (Exception ex) {
          throw new DAOException(CLASS_NAME_DAO +
                                 ".getBanLeTwChiTietObject(): " +
                                 ex.getMessage(), ex);
      }
  }


    public BanLeTraiPhieuTwChiTietVO getBanLeTwChiTietObject(Map<String, Object> map) throws Exception,
                                                                                             TPCPException {
        StringBuffer strSQL = new StringBuffer();
        StringBuffer strSQL2 = new StringBuffer();
        Vector vParam = new Vector();
        try {
            strSQL.append("SELECT * FROM TP_KQPH_BAN_LE_CTIET");
            Set<String> keySet = map.keySet();
            Iterator<String> keySetIterator = keySet.iterator();
            while (keySetIterator.hasNext()) {
                String key = keySetIterator.next();
                String value = map.get(key).toString();
                if (value != null && !STRING_EMPTY.equals(value)) {
                    strSQL2.append("and " + key + " = ? ");
                    vParam.add(new Parameter(value, true));
                }
            }
            if (strSQL2.toString().length() > 0) {
                strSQL.append(" WHERE 1 = 1 " + strSQL2.toString());
            }
            return (BanLeTraiPhieuTwChiTietVO)findByPK(strSQL.toString(),
                                                       vParam, CLASS_NAME_VO,
                                                       conn);
        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO +
                                   ".getBanLeTwChiTietObject(): " +
                                   ex.getMessage(), ex);
        }
    }

    public Long delete(BanLeTraiPhieuTwChiTietVO vo) throws Exception {
        Vector v_param = new Vector();
        StringBuffer strSQL = new StringBuffer();
        strSQL.append(" DELETE TP_KQPH_BAN_LE_CTIET WHERE GUID = ?");
        int nExc = 0;
        if (vo.getGuid() != null) {
            v_param.add(new Parameter(vo.getGuid(), true));
            nExc = executeStatement(strSQL.toString(), v_param, conn);
            if (nExc == 1)
                return Long.parseLong(vo.getGuid());
        }
        return new Long("0");
    }


}
