package com.seatech.tp.kqduthau.action;

import com.seatech.framework.datamanager.BaseDelegate;
import com.seatech.framework.exception.TPCPException;
import com.seatech.tp.banletraiphieutw.dao.BanLeTraiPhieuTwChiTietDAO;
import com.seatech.tp.banletraiphieutw.vo.BanLeTraiPhieuTwChiTietVO;
import com.seatech.tp.kqduthau.dao.QLyKQDuThauDAO;
import com.seatech.tp.kqduthau.vo.QLyKQDuThauCTietVO;
import com.seatech.tp.kqduthau.vo.QLyKQDuThauVO;

import com.seatech.tp.kqphathanh.dao.QLyKQPhatHanhDAO;
import com.seatech.tp.quanlykehoachquy.dao.QuanLyKeHoachQuyDao;
import com.seatech.tp.quanlykehoachquy.vo.QuanLyKeHoachQuyVO;

import java.sql.Connection;

import java.util.Collection;

public class QLyKQDuThauDelegate extends BaseDelegate {
    public QLyKQDuThauDelegate(Connection conn) {
        super();
        this.conn = conn;
    }

    public long update(QLyKQDuThauVO vo) throws Exception, TPCPException {
        long id = 0;
        try {
            QLyKQDuThauDAO ttindthauDAO = new QLyKQDuThauDAO(conn);
            id = ttindthauDAO.update(vo);
            conn.commit();
        } catch (Exception ex) {
            conn.rollback();
            throw new TPCPException().createException("TPCP-0001",
                                                      "Không thể cập nhật Cơ sở dữ liệu");
        }
        return id;
    }

    public Collection getListKQDTPaging(QLyKQDuThauVO vo, Integer page,
                                        Integer count,
                                        Integer[] totalCount) throws Exception {
        Collection reval = null;

        try {
            QLyKQDuThauDAO kqdtDAO = new QLyKQDuThauDAO(conn);
            reval = kqdtDAO.getListKQDT(vo, page, count, totalCount);

        } catch (Exception ex) {
            throw ex;
        }
        return reval;
    }
    
  public Collection getListKQDTPaging_PD(QLyKQDuThauVO vo, Integer page,
                                      Integer count,
                                      Integer[] totalCount) throws Exception {
      Collection reval = null;

      try {
          QLyKQDuThauDAO kqdtDAO = new QLyKQDuThauDAO(conn);
          reval = kqdtDAO.getListKQDT_PD(vo, page, count, totalCount);

      } catch (Exception ex) {
          throw ex;
      }
      return reval;
  }

    public Collection getListKQDTObject(QLyKQDuThauVO vo,
                                        String guid) throws Exception {
        Collection reval = null;

        try {
            QLyKQDuThauDAO kqdtDAO = new QLyKQDuThauDAO(conn);
            reval = kqdtDAO.getListKQDTObject(vo, guid);
        } catch (Exception ex) {
            throw ex;
        }
        return reval;
    }

    public Collection getListKQDTChiTiet_BuoiSang(String guid) throws Exception {
        Collection reval = null;

        try {
            QLyKQDuThauDAO kqdtDAO = new QLyKQDuThauDAO(conn);
            reval = kqdtDAO.getListKQDTChiTiet_BuoiSang(guid);

        } catch (Exception ex) {
            throw ex;
        }
        return reval;
    }
    
  public Collection getListKQDTChiTiet_Them(String guid) throws Exception {
      Collection reval = null;

      try {
          QLyKQDuThauDAO kqdtDAO = new QLyKQDuThauDAO(conn);
          reval = kqdtDAO.getListKQDTChiTiet_Them(guid);

      } catch (Exception ex) {
          throw ex;
      }
      return reval;
  }

    public QLyKQDuThauVO getQLKQDuThauObject(String guid) throws Exception,
                                                                 TPCPException {
        QLyKQDuThauVO reval = null;
        try {
            QLyKQDuThauDAO tpcpDAO = new QLyKQDuThauDAO(conn);
            reval = tpcpDAO.getQLKQDuThauObject(guid);
        } catch (Exception ex) {
            throw ex;
        }
        return reval;
    }

    public long deleteKQDT(QLyKQDuThauVO vo, String guid) throws Exception,
                                                                 TPCPException {
        long id = 0;
        try {
            QLyKQDuThauDAO ttindthauDAO = new QLyKQDuThauDAO(conn);
            id = ttindthauDAO.delete(vo, guid);
            conn.commit();
        } catch (Exception ex) {
            conn.rollback();
            throw new TPCPException().createException("TPCP-0001",
                                                      "Không thể cập nhật Cơ sở dữ liệu");
        }
        return id;
    }
  // Luu file

  public void insertTP_KQDT_FILE(byte[] kqdtFile, long id_kq_dt,
                                 String ngay_imp,
                                 long nguoi_imp) throws Exception {
      try {
          QLyKQDuThauDAO dthauDAO = new QLyKQDuThauDAO(conn);
          dthauDAO.insertTP_KQDT_FILE(kqdtFile, id_kq_dt, ngay_imp,
                                     nguoi_imp);
      } catch (Exception ex) {
          throw ex;
      }
  }
}
