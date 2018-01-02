package com.seatech.tp.dmdonvitt.action;

import com.seatech.framework.datamanager.BaseDelegate;

import com.seatech.tp.dmdonvitt.dao.DMDonViTTDao;

import com.seatech.tp.ttlaigoc.vo.TPLenhTriTraNoVo;

import java.sql.Connection;

import java.util.Collection;
import java.util.Map;

public class DMDonViTTDelegate extends BaseDelegate {
    public DMDonViTTDelegate(Connection conn) {
        super();
        this.conn = conn;
    }
  public Collection getListDmDonViTT(Map<String, Object> map) throws Exception {
      Collection reval = null;

      try {
          DMDonViTTDao dao = new DMDonViTTDao(conn);
          reval = dao.getListDmDonViTT(map);

      } catch (Exception ex) {
          throw ex;
      }
      return reval;
  }
  public Collection getListDmDonViTTAll() throws Exception {
      Collection reval = null;

      try {
          DMDonViTTDao dao = new DMDonViTTDao(conn);
          reval = dao.getListDmDonViTTAll();

      } catch (Exception ex) {
          throw ex;
      }
      return reval;
  }
}
