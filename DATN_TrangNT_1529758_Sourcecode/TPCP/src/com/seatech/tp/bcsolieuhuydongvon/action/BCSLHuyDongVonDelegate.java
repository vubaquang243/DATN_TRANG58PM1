package com.seatech.tp.bcsolieuhuydongvon.action;

import com.seatech.framework.datamanager.BaseDelegate;
import com.seatech.tp.bcsolieuhuydongvon.dao.BCSLHuyDongVonDAO;
import com.seatech.tp.bcsolieuhuydongvon.vo.BCSLHuyDongVonVo;
import com.seatech.tp.sotonghoptpcp.action.SoTongHopTpcpDelegate;
import com.seatech.tp.sotonghoptpcp.dao.SoTongHopTpcpDao;
import com.seatech.tp.sotonghoptpcp.vo.SoTongHopTpcpVO;

import java.sql.Connection;
import java.sql.ResultSet;

import java.util.Collection;

public class BCSLHuyDongVonDelegate  extends BaseDelegate {

  public BCSLHuyDongVonDelegate(Connection conn) {
      super();
      this.conn = conn;
  }

  public Collection getBCSLHuyDongVonPaging(BCSLHuyDongVonVo vo,
                                               Integer page, Integer count,
                                               Integer[] totalCount) throws Exception {
      Collection reval = null;

      try {
          BCSLHuyDongVonDAO BCSLHuyDongVon = new BCSLHuyDongVonDAO(conn);
          reval =
                  BCSLHuyDongVon.getBCSLHuyDongVonPaging(vo, page, count, totalCount);

      } catch (Exception ex) {
          throw ex;
      }
      return reval;
  }

  public ResultSet getBCSLHuyDongVonList(BCSLHuyDongVonVo vo) throws Exception {
      ResultSet reval = null;

      try {
          BCSLHuyDongVonDAO BCSLHuyDongVon = new BCSLHuyDongVonDAO(conn);
          reval = BCSLHuyDongVon.getBCSLHuyDongVonList(vo);

      } catch (Exception ex) {
          throw ex;
      }
      return reval;
  }
}
