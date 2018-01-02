package com.seatech.tp.dmdonvitt.dao;

import com.seatech.framework.datamanager.AppDAO;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.exception.DAOException;

import java.sql.Connection;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

public class DMDonViTTDao extends AppDAO {
    private Connection conn = null;
    private String CLASS_NAME_DAO =
        "com.seatech.tp.dmdonvitt.dao.DMDonViTTDao";
    private String CLASS_NAME_VO = "com.seatech.tp.dmdonvitt.vo.DMDonViTTVo";
    private static String STRING_EMPTY = "";

    public DMDonViTTDao(Connection conn) {
        super();
        this.conn = conn;
    }

    public Collection getListDmDonViTT(Map<String, Object> map) throws Exception {
        Collection reval = null;
        String whereClause = "";
        Vector vParam = new Vector();
        StringBuffer strSQL = new StringBuffer();
        StringBuffer strSQL2 = new StringBuffer();
        try {
            strSQL.append("SELECT \n" +
                    "T.GUID, T.MA, T.TEN,T.DVQHNS, T.SO_TK, T.MA_NH,T.TEN_NH, T.TRANG_THAI \n" +
                    "FROM TP_DM_DVI_TT T ");

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
                strSQL.append(" WHERE 1 = 1 and t.trang_thai = '00' " + strSQL2.toString());
            }
            reval =
                    executeSelectStatement(strSQL.toString(), vParam, CLASS_NAME_VO,
                                           conn);

        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO + ".getListDmDonViTT(): " +
                                   ex.getMessage(), ex);
        }
        return reval;
    }
  public Collection getListDmDonViTTAll() throws Exception {
      Collection reval = null;
      Vector vParam = new Vector();
      StringBuffer strSQL = new StringBuffer();
      StringBuffer strSQL2 = new StringBuffer();
      try {
          strSQL.append("SELECT DISTINCT T.MA, T.TEN \n" +
                  " FROM TP_DM_DVI_TT T WHERE TRANG_THAI = '00'");
          reval =
                  executeSelectStatement(strSQL.toString(), vParam, CLASS_NAME_VO,
                                         conn);

      } catch (Exception ex) {
          throw new DAOException(CLASS_NAME_DAO + ".getListDmDonViTT(): " +
                                 ex.getMessage(), ex);
      }
      return reval;
  }
}
