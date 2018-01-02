package com.seatech.tp.bccocaunhadautu.dao;

import com.seatech.framework.datamanager.AppDAO;
import com.seatech.framework.exception.DAOException;
import com.seatech.tp.bccocaunhadautu.vo.BCCoCauNhaDauTuVo;

import java.sql.Connection;
import java.sql.ResultSet;

import java.util.Collection;
import java.util.Vector;

public class BCCoCauNhaDauTuDao extends AppDAO{
    
  private Connection conn = null;
  private String CLASS_NAME_DAO =
       "com.seatech.tp.bccocaunhadautu.dao.BCCoCauNhaDauTuDao";
  private String CLASS_NAME_VO =
       "com.seatech.tp.bccocaunhadautu.vo.BCCoCauNhaDauTuVo";
  private static String STRING_EMPTY = "";
  
  public BCCoCauNhaDauTuDao(Connection conn) {
      super();
      this.conn = conn;
  }
  
  public Collection getBCCoCauNhaDauTuPaging(BCCoCauNhaDauTuVo vo, Integer page,
                                           Integer count,
                                           Integer[] totalCount) throws Exception {
      Collection reval = null;
      Vector vParam = new Vector();
      StringBuffer strSQL = new StringBuffer();
      try {
          strSQL.append("SELECT STT,\n" + 
          "  TEN_CHI_TIEU,\n" + 
          "  KHOI_LUONG,\n" + 
          "  LOAI_HINH,\n" + 
          "  CO_CAU,\n" + 
          "  TY_TRONG\n" + 
          "FROM TP_TMP_BAO_CAO_2 order by CO_CAU desc");  
          reval =
                  executeSelectWithPaging(conn, strSQL.toString(), vParam, CLASS_NAME_VO,
                                          page, count, totalCount);
      } catch (Exception ex) {
          throw new DAOException(CLASS_NAME_DAO +
                                 ".getListBanLeTwPaging(): " +
                                 ex.getMessage(), ex);
      }
      return reval;
  }


  public ResultSet getBCCoCauNhaDauTuList(BCCoCauNhaDauTuVo vo) throws Exception {
      ResultSet reval = null;
      String whereClause = "";
      Vector vParam = new Vector();
      StringBuffer strSQL = new StringBuffer();
      try {
        strSQL.append("SELECT STT,\n" + 
        "  TEN_CHI_TIEU,\n" + 
        "  KHOI_LUONG,\n" + 
        "  LOAI_HINH,\n" + 
        "  CO_CAU,\n" + 
        "  TY_TRONG\n" + 
        "FROM TP_TMP_BAO_CAO_2 order by CO_CAU desc");
          reval = executeSelectStatement(strSQL.toString(), vParam, conn);

      } catch (Exception ex) {
          throw new DAOException(CLASS_NAME_DAO + ".getUserList(): " +
                                 ex.getMessage(), ex);
      }

      return reval;
  }
}
