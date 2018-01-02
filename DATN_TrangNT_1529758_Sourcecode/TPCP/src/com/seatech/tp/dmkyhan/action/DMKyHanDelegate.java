package com.seatech.tp.dmkyhan.action;

import com.seatech.framework.datamanager.BaseDelegate;
import com.seatech.framework.exception.TPCPException;
import com.seatech.tp.banletraiphieutw.dao.BanLeTraiPhieuTwDAO;
import com.seatech.tp.banletraiphieutw.vo.BanLeTraiPhieuTwVO;
import com.seatech.tp.dmkyhan.dao.DMKyHanDAO;

import com.seatech.tp.dmkyhan.vo.DMKyHanVO;

import java.sql.Connection;

import java.util.Collection;
import java.util.Map;

public class DMKyHanDelegate extends BaseDelegate {
    public DMKyHanDelegate(Connection conn) {
        super();
        this.conn = conn;
    }

    public Collection getDMKyHan(Map<String, Object> map) throws Exception {
        Collection reval = null;

        try {
            DMKyHanDAO dmKyHanDAO = new DMKyHanDAO(conn);
            reval = dmKyHanDAO.getDMKyHan(map);

        } catch (Exception ex) {
            throw ex;
        }
        return reval;
    }
    
  public Collection getDMKyHanAdd(Map<String, Object> map) throws Exception {
      Collection reval = null;

      try {
          DMKyHanDAO dmKyHanDAO = new DMKyHanDAO(conn);
          reval = dmKyHanDAO.getDMKyHanAdd(map);

      } catch (Exception ex) {
          throw ex;
      }
      return reval;
  }
    
  public DMKyHanVO getDMKyHanObject(Map<String, Object> map) throws Exception,
                                                                         TPCPException {
      DMKyHanVO reval = null;
      try {
        DMKyHanDAO dmKyHanDAO = new DMKyHanDAO(conn);
        reval = dmKyHanDAO.getDMKYHanObject(map);
      } catch (Exception ex) {
          throw ex;
      }
      return reval;
  }
}
