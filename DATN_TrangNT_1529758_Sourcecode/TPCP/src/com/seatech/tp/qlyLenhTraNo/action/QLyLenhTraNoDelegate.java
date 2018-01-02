package com.seatech.tp.qlyLenhTraNo.action;

import com.seatech.framework.datamanager.BaseDelegate;
import com.seatech.framework.exception.TPCPException;
import com.seatech.tp.sotonghoptpcp.dao.SoTongHopTpcpDao;
import com.seatech.tp.ttindthau.dao.QLyTTinDauThauDAO;
import com.seatech.tp.ttindthau.vo.ThongTinDauThauVO;
import com.seatech.tp.ttlaigoc.action.TTLaiGocDelegate;
import com.seatech.tp.ttlaigoc.dao.TTLaiGocDao;
import com.seatech.tp.ttlaigoc.vo.TPLenhTriTraNoVo;
import com.seatech.tp.ttlaigoc.vo.TTLaiGocVo;

import java.sql.Connection;

import java.sql.ResultSet;

import java.util.Collection;

public class QLyLenhTraNoDelegate extends BaseDelegate {
    public QLyLenhTraNoDelegate(Connection conn) {
        super();
        this.conn = conn;
    }

    public Collection getListTTDTPaging(TPLenhTriTraNoVo vo, Integer page,
                                        Integer count,
                                        Integer[] totalCount) throws Exception {
        Collection reval = null;

        try {
            TTLaiGocDao dao = new TTLaiGocDao(conn);
            reval = dao.getListLenhTriTraNoPaging(vo, page, count, totalCount);

        } catch (Exception ex) {
            throw ex;
        }
        return reval;
    }
    
  public Collection getListLenhTraNoPDPaging(TPLenhTriTraNoVo vo, Integer page,
                                      Integer count,
                                      Integer[] totalCount) throws Exception {
      Collection reval = null;

      try {
          TTLaiGocDao dao = new TTLaiGocDao(conn);
          reval = dao.getListLenhTraNoPDPaging(vo, page, count, totalCount);

      } catch (Exception ex) {
          throw ex;
      }
      return reval;
  }
    
  public Collection getListLenhTriTraNoUSDPaging(TPLenhTriTraNoVo vo, Integer page,
                                      Integer count,
                                      Integer[] totalCount) throws Exception {
      Collection reval = null;

      try {
          TTLaiGocDao dao = new TTLaiGocDao(conn);
          reval = dao.getListLenhTriTraNoUSDPaging(vo, page, count, totalCount);

      } catch (Exception ex) {
          throw ex;
      }
      return reval;
  }
    public Collection getListLenhTriTraNoPDUSDPaging(TPLenhTriTraNoVo vo, Integer page,
                                        Integer count,
                                        Integer[] totalCount) throws Exception {
        Collection reval = null;

        try {
            TTLaiGocDao dao = new TTLaiGocDao(conn);
            reval = dao.getListLenhTriTraNoPDUSDPaging(vo, page, count, totalCount);

        } catch (Exception ex) {
            throw ex;
        }
        return reval;
    }

    public TPLenhTriTraNoVo getListLenhTriTraNoCT(String idLenh) throws Exception {
        TPLenhTriTraNoVo reval = null;

        try {
            TTLaiGocDao dao = new TTLaiGocDao(conn);
            reval = dao.getListLenhTriTraNoCT(idLenh);

        } catch (Exception ex) {
            throw ex;
        }
        return reval;
    }

    public long update(TPLenhTriTraNoVo vo) throws Exception, TPCPException {
        long id = 0;
        try {
            TTLaiGocDao dao = new TTLaiGocDao(conn);
            id = dao.insert(vo);
            conn.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            conn.rollback();
            throw new TPCPException().createException("TPCP-0001",
                                                      "Không thể cập nhật Cơ sở dữ liệu");
        }
        return id;
    }

    public long delete(TPLenhTriTraNoVo vo) throws Exception,
                                                        TPCPException {
        long id = 0;
        try {
            TTLaiGocDao dao = new TTLaiGocDao(conn);
            id = dao.delete(vo);
            conn.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            conn.rollback();
            throw new TPCPException().createException("TPCP-0001",
                                                      "Không thể cập nhật Cơ sở dữ liệu");
        }
        return id;
    }

    public ResultSet getLenhChiTraNoCTList(String id_lenh) throws Exception {
      ResultSet reval = null;

      try {
          TTLaiGocDao dao = new TTLaiGocDao(conn);
          reval = dao.getLenhChiTraNoCTList(id_lenh);

      } catch (Exception ex) {
          throw ex;
      }
      return reval;
    }
  public Collection getLenhChiTraNoCTList2(String id_lenh) throws Exception {
    Collection reval = null;

    try {
        TTLaiGocDao dao = new TTLaiGocDao(conn);
        reval = dao.getLenhChiTraNoCTList2(id_lenh);

    } catch (Exception ex) {
        throw ex;
    }
    return reval;
  }
  public Collection getList() throws Exception {
    Collection reval = null;

    try {
        TTLaiGocDao dao = new TTLaiGocDao(conn);
        //reval = dao.getLenhChiTraNoCTList2(String id_lenh);

    } catch (Exception ex) {
        throw ex;
    }
    return reval;
  }
}
