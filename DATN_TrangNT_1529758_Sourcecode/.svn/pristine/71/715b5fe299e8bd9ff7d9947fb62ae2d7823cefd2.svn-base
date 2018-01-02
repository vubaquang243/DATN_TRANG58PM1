package com.seatech.tp.bccocaunhadautu.action;

import com.seatech.framework.datamanager.BaseDelegate;
import com.seatech.tp.bccocaunhadautu.dao.BCCoCauNhaDauTuDao;
import com.seatech.tp.bccocaunhadautu.vo.BCCoCauNhaDauTuVo;
import com.seatech.tp.bcsolieuhuydongvon.dao.BCSLHuyDongVonDAO;
import com.seatech.tp.bcsolieuhuydongvon.vo.BCSLHuyDongVonVo;

import java.sql.Connection;
import java.sql.ResultSet;

import java.util.Collection;

public class BCCoCauNhaDauTuDelegate extends BaseDelegate{
  public BCCoCauNhaDauTuDelegate(Connection conn) {
      super();
      this.conn = conn;
  }

  public Collection getBCCoCauNhaDauTuPaging(BCCoCauNhaDauTuVo vo,
                                               Integer page, Integer count,
                                               Integer[] totalCount) throws Exception {
      Collection reval = null;
    
      try {
          BCCoCauNhaDauTuDao _BCCoCauNhaDauTuDao = new BCCoCauNhaDauTuDao(conn);
          reval =
                  _BCCoCauNhaDauTuDao.getBCCoCauNhaDauTuPaging(vo, page, count, totalCount);

      } catch (Exception ex) {
          throw ex;
      }
      return reval;
  }

  public ResultSet getBCCoCauNhaDauTuList(BCCoCauNhaDauTuVo vo) throws Exception {
      ResultSet reval = null;

      try {
          BCCoCauNhaDauTuDao _BCCoCauNhaDauTuDao = new BCCoCauNhaDauTuDao(conn);
          reval = _BCCoCauNhaDauTuDao.getBCCoCauNhaDauTuList(vo);

      } catch (Exception ex) {
          throw ex;
      }
      return reval;
  }
}
