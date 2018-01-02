package com.seatech.tp.dmkyhan.dao;

import com.seatech.framework.datamanager.AppDAO;

import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.exception.DAOException;
import com.seatech.framework.exception.TPCPException;

import com.seatech.tp.banletraiphieutw.vo.BanLeTraiPhieuTwVO;

import com.seatech.tp.dmkyhan.vo.DMKyHanVO;

import java.sql.Connection;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

public class DMKyHanDAO extends AppDAO {
    private Connection conn = null;
    private String CLASS_NAME_DAO = "com.seatech.tp.dmkyhan.dao.DMKyHanDAO";
    private String CLASS_NAME_VO = "com.seatech.tp.dmkyhan.vo.DMKyHanVO";
    private static String STRING_EMPTY = "";

    public DMKyHanDAO(Connection conn) {
        this.conn = conn;
    }

    public Collection getDMKyHan(Map<String, Object> map) throws Exception,
                                                                      TPCPException {
        StringBuffer strSQL = new StringBuffer();
        StringBuffer strSQL2 = new StringBuffer();
        Vector vParam = new Vector();
        Collection reval = null;
        try {
            strSQL.append("SELECT GUID, LOAI_KY_HAN, KY_HAN, MO_TA NAME_KY_HAN, LOAI_TPCP, TRANG_THAI, MO_TA,GUID as id_ky_han " +
                          " FROM TP_DM_KY_HAN ");
            if(map!=null){
                Set<String> keySet = map.keySet();
                Iterator<String> keySetIterator = keySet.iterator();
                while (keySetIterator.hasNext()) {
                    String key = keySetIterator.next();
                    String value = map.get(key).toString();
                    if (value != null && !STRING_EMPTY.equals(value)) {
                        strSQL2.append(" AND " + key + " = ? ");
                        vParam.add(new Parameter(value, true));
                    }
                }
                if (strSQL2.toString().length() > 0) {
                    strSQL.append(" WHERE 1 = 1 " + strSQL2.toString());
                }
                
            }   
            strSQL.append(" ORDER BY LOAI_TPCP,KY_HAN");
            reval = executeSelectStatement(strSQL.toString(), vParam,
                                          CLASS_NAME_VO, conn);
        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO + ".getTTDTObject(): " +
                                   ex.getMessage(), ex);
        }
        return reval;
    }
    
  public Collection getDMKyHanAdd(Map<String, Object> map) throws Exception,
                                                                    TPCPException {
      StringBuffer strSQL = new StringBuffer();
      Vector vParam = new Vector();
      Collection reval = null;
      try {
          strSQL.append("SELECT GUID, LOAI_KY_HAN, KY_HAN, MO_TA NAME_KY_HAN, LOAI_TPCP, TRANG_THAI, MO_TA,GUID as id_ky_han FROM TP_DM_KY_HAN ORDER BY LOAI_TPCP desc, KY_HAN asc");         
          reval = executeSelectStatement(strSQL.toString(), vParam,
                                        CLASS_NAME_VO, conn);
      } catch (Exception ex) {
          throw new DAOException(CLASS_NAME_DAO + ".getTTDTObject(): " +
                                 ex.getMessage(), ex);
      }
      return reval;
  }
    
  public DMKyHanVO getDMKYHanObject(Map<String, Object> map) throws Exception,
                                                                             TPCPException {
      StringBuffer strSQL = new StringBuffer();
      StringBuffer strSQL2 = new StringBuffer();
      Vector vParam = new Vector();
      try {
          strSQL.append("SELECT \n" + 
          "T.GUID, T.KY_HAN, T.LOAI_KY_HAN, \n" + 
          "   T.LOAI_TPCP, T.MO_TA, T.TRANG_THAI\n" + 
          "FROM TPCP_OWNER.TP_DM_KY_HAN T\n");
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
          return (DMKyHanVO)findByPK(strSQL.toString(), vParam,
                                              CLASS_NAME_VO, conn);
      } catch (Exception ex) {
          throw new DAOException(CLASS_NAME_DAO + ".getKyHanObject(): " +
                                 ex.getMessage(), ex);
      }
  }
}
