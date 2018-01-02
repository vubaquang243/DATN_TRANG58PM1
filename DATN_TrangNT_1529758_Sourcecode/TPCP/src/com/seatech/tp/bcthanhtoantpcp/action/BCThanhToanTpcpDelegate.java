package com.seatech.tp.bcthanhtoantpcp.action;

import com.seatech.framework.datamanager.BaseDelegate;
import com.seatech.tp.bcsolieuhuydongvon.action.BCSLHuyDongVonDelegate;
import com.seatech.tp.bcsolieuhuydongvon.dao.BCSLHuyDongVonDAO;
import com.seatech.tp.bcsolieuhuydongvon.vo.BCSLHuyDongVonVo;

import com.seatech.tp.bcthanhtoantpcp.dao.BCThanhToanTpcpDao;
import com.seatech.tp.bcthanhtoantpcp.vo.BCThanhToanTpcpVo;

import java.sql.Connection;
import java.sql.ResultSet;

import java.util.Collection;

public class BCThanhToanTpcpDelegate extends BaseDelegate{
    
  public BCThanhToanTpcpDelegate(Connection conn) {
      super();
      this.conn = conn;
  }

  public Collection getBCThanhToanTpcpPaging(BCThanhToanTpcpVo vo,
                                               Integer page, Integer count,
                                               Integer[] totalCount) throws Exception {
      Collection reval = null;
      try {
          BCThanhToanTpcpDao _BCThanhToanTpcpDao = new BCThanhToanTpcpDao(conn);
          reval =
                  _BCThanhToanTpcpDao.getBCThanhToanTpcpPaging(vo, page, count, totalCount);

      } catch (Exception ex) {
          throw ex;
      }
      return reval;
  }

  public ResultSet getBCThanhToanTpcpList(BCThanhToanTpcpVo vo) throws Exception {
      ResultSet reval = null;
      try {
        BCThanhToanTpcpDao _BCThanhToanTpcpDao = new BCThanhToanTpcpDao(conn);
          reval = _BCThanhToanTpcpDao.getBCThanhToanTpcpList(vo);

      } catch (Exception ex) {
          throw ex;
      }
      return reval;
  }
}
