package com.seatech.tp.tiente.dao;

import com.seatech.framework.datamanager.AppDAO;
import com.seatech.framework.datamanager.DateParameter;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.exception.DAOException;
import com.seatech.framework.utils.StringUtil;
import com.seatech.tp.tiente.vo.TienTeVo;
import com.seatech.tp.ttindthau.dao.QLyTTinDauThauDAO;
import com.seatech.tp.ttindthau.vo.ThongTinDauThauVO;

import java.sql.Connection;

import java.util.Collection;
import java.util.Vector;

public class TienTeDao extends AppDAO {
    private Connection conn = null;
    private String CLASS_NAME_DAO ="com.seatech.tp.tiente.dao.TienTeDao";
    private String CLASS_NAME_VO ="com.seatech.tp.tiente.vo.TienTeVo";
    private static String STRING_EMPTY = "";
    
    public TienTeDao(Connection conn) {
        super();
        this.conn = conn;
    }
  public Collection getListTienTe(TienTeVo vo, Integer page,
                                      Integer count,
                                      Integer[] totalCount) throws Exception {
      Collection reval = null;
      String whereClause = "";
      Vector vParam = new Vector();
      StringBuffer strSQL = new StringBuffer();
      try {
          strSQL.append("SELECT ID,MA,TEN FROM TP_DM_TIENTE ");
          reval =executeSelectWithPaging(conn, strSQL.toString(), vParam, CLASS_NAME_VO,page, count, totalCount);
      } catch (Exception ex) {
          throw new DAOException(CLASS_NAME_DAO + ".getListTPCPPaging(): " +
                                 ex.getMessage(), ex);
      }
      return reval;
  }
}
