package com.seatech.framework.utils;


import com.seatech.framework.datamanager.AppDAO;
import com.seatech.framework.datamanager.Parameter;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

import java.util.Date;
import java.util.Vector;


public class TPCPUtils extends AppDAO {
    private Connection conn = null;

    public TPCPUtils(Connection conn) {
        this.conn = conn;
    }

    

    /**
     * @param: ten action và nsd ID
     * @return: bolean: true = co quyen; false = khong co quyen
     * @see: Ham kt quyen tren chuc nang
     * */
    public boolean checkPermisFunction(String strUserID,
                                       String strActionname) throws Exception {
        Vector v_param = new Vector();
        v_param.add(new Parameter("", false));
        v_param.add(new Parameter(strUserID, true));
        v_param.add(new Parameter(strActionname, true));

        String strFunctionName = "TP_UTIL_PKG.fnc_check_permistion";
        int nKetQua =
            Integer.parseInt(executeFunction(strFunctionName, v_param,
                                             this.conn).toString());
        if (nKetQua > 0)
            return true;
        else
            return false;
    }

   
   


    public String LPAD(String strSeq, int nLenght) {
        while (strSeq.length() < nLenght) {
            strSeq = "0" + strSeq;
        }
        return strSeq;
    }

    public void grantAccess() throws Exception {
        CallableStatement cstmt = 
            conn.prepareCall("{ call TPCP_OWNER.GRANT_ACCESS() }");
        cstmt.execute();
    }

    

    /**
     * @param:
     * @return:
     * @see: Lock cho viec update
     * */
    public String setLock(String strID, String strType,
                          Long strUserID) throws Exception {
        CallableStatement cs = null;
        cs =
 conn.prepareCall("{call tp_check_forupdate_pkg.proc_setlock(?,?,?)}");
        cs.setString(1, strID + strType);
        cs.setLong(2, strUserID);
        cs.registerOutParameter(3, java.sql.Types.VARCHAR);
        cs.execute();

        return cs.getString(3);
    }

    /**
     * @param:
     * @return:
     * @see: UNLock cho viec update
     * */
    public void unLock(String strID, String strType) throws Exception {
        CallableStatement cs = null;
        cs = conn.prepareCall("{call tp_check_forupdate_pkg.proc_unlock(?)}");
        cs.setString(1, strID + strType);
        cs.execute();
    }

   
}
