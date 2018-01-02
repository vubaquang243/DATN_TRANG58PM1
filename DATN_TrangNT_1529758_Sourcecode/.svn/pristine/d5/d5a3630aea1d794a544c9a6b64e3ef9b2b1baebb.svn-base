package com.seatech.tp.kqphathanh.action;

import com.seatech.framework.datamanager.BaseDelegate;
import com.seatech.framework.exception.TPCPException;
import com.seatech.tp.banletraiphieutw.action.BanLeTraiPhieuTwDelegate;
import com.seatech.tp.banletraiphieutw.dao.BanLeTraiPhieuTwChiTietDAO;
import com.seatech.tp.banletraiphieutw.dao.BanLeTraiPhieuTwDAO;
import com.seatech.tp.banletraiphieutw.vo.BanLeTraiPhieuTwChiTietVO;
import com.seatech.tp.banletraiphieutw.vo.BanLeTraiPhieuTwVO;

import com.seatech.tp.kqphathanh.dao.QLyKQPhatHanhDAO;
import com.seatech.tp.kqphathanh.vo.QLyKQPhatHanhCTiet_SoHuuVO;
import com.seatech.tp.kqphathanh.vo.QLyKQPhatHanhCTiet_TpcpVO;
import com.seatech.tp.kqphathanh.vo.QLyKQPhatHanhCTiet_TpcpVO;
import com.seatech.tp.kqphathanh.vo.QLyKQPhatHanhVO;

import java.sql.Blob;
import java.sql.Connection;

import java.sql.PreparedStatement;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class QLyKQPhatHanhDelegate extends BaseDelegate {
    public QLyKQPhatHanhDelegate(Connection conn) {
        super();
        this.conn = conn;
    }

    public long update(QLyKQPhatHanhVO vo) throws Exception, TPCPException {
        long id = 0;
        try {
            QLyKQPhatHanhDAO kqphDAO = new QLyKQPhatHanhDAO(conn);
            id = kqphDAO.update(vo);
            conn.commit();
        } catch (Exception ex) {
            conn.rollback();
            throw new TPCPException().createException("TPCP-0001",
                                                      "Không thể cập nhật dữ liệu");
        }
        return id;
    }

    public Collection getListKQPHPaging(QLyKQPhatHanhVO vo, Integer page,
                                        Integer count,
                                        Integer[] totalCount) throws Exception {
        Collection reval = null;

        try {
            QLyKQPhatHanhDAO kqphDAO = new QLyKQPhatHanhDAO(conn);
            reval = kqphDAO.getListKQPHPaging(vo, page, count, totalCount);

        } catch (Exception ex) {
            throw ex;
        }
        return reval;
    }
    
  public Collection getListKQPHPagingPD(QLyKQPhatHanhVO vo, Integer page,
                                      Integer count,
                                      Integer[] totalCount) throws Exception {
      Collection reval = null;

      try {
          QLyKQPhatHanhDAO kqphDAO = new QLyKQPhatHanhDAO(conn);
          reval = kqphDAO.getListKQPHPagingPD(vo, page, count, totalCount);

      } catch (Exception ex) {
          throw ex;
      }
      return reval;
  }


    public QLyKQPhatHanhVO getQLyKQPhatHanhObject(Map<String, Object> map) throws Exception,
                                                                                  TPCPException {
        QLyKQPhatHanhVO reval = null;
        try {
            QLyKQPhatHanhDAO kqphDAO = new QLyKQPhatHanhDAO(conn);
            reval = kqphDAO.gettKQPHObject(map);
        } catch (Exception ex) {
            throw ex;
        }
        return reval;
    }

    public QLyKQPhatHanhVO getQLyKQPhatHanhObjectHienThi(Map<String, Object> map) throws Exception,
                                                                                         TPCPException {
        QLyKQPhatHanhVO reval = null;
        try {
            QLyKQPhatHanhDAO kqphDAO = new QLyKQPhatHanhDAO(conn);
            reval = kqphDAO.getPhatHanhObjectHienThi(map);
        } catch (Exception ex) {
            throw ex;
        }
        return reval;
    }

    public long deleteQLyKQPhatHanh(QLyKQPhatHanhVO vo) throws Exception,
                                                               TPCPException {
        long id = 0;
        long idChiTiet_TPCP = 0;
        long idChiTiet_SoHuu = 0;
        long id_kqph_file = 0;
        try {
            QLyKQPhatHanhDAO kqphDAO = new QLyKQPhatHanhDAO(conn);
            idChiTiet_TPCP = kqphDAO.deleteChiTiet_TPCP(vo);
            idChiTiet_SoHuu = kqphDAO.deleteChiTiet_SoHuu(vo);
            id_kqph_file = kqphDAO.deleteTP_KQPH_FILE(vo);
            id = kqphDAO.delete(vo);
            conn.commit();
        } catch (Exception ex) {
            conn.rollback();
            throw new TPCPException().createException("kqphathanh-0001",
                                                      "KhÃƒÂ´ng thÃ¡Â»Æ’ cÃ¡ÂºÂ­p nhÃ¡ÂºÂ­t CÃ†Â¡ sÃ¡Â»Å¸ dÃ¡Â»Â¯ liÃ¡Â»â€¡u");
        }
        return id;
    }

    //Chi tiet TPTP

    public Collection getListQLyKQPhatHanh_CTIET_TPCP(Map<String, Object> map) throws Exception,

            TPCPException {
        Collection reval = null;

        try {
            QLyKQPhatHanhDAO quanlykqphathanhDAO = new QLyKQPhatHanhDAO(conn);
            reval = quanlykqphathanhDAO.getListKQPH_CTIET_TPCP(map);
        } catch (Exception ex) {
            throw ex;
        }
        return reval;
    }
    
  public Collection getListKQPH_CTIET_TPCP_HIENTHI(Map<String, Object> map) throws Exception,

          TPCPException {
      Collection reval = null;

      try {
          QLyKQPhatHanhDAO quanlykqphathanhDAO = new QLyKQPhatHanhDAO(conn);
          reval = quanlykqphathanhDAO.getListKQPH_CTIET_TPCP_HIENTHI(map);
      } catch (Exception ex) {
          throw ex;
      }
      return reval;
  }
  

    public QLyKQPhatHanhCTiet_TpcpVO geQLyKQPhatHanhCTiet_TpcpObject(Map<String, Object> map) throws Exception,
                                                                                                     TPCPException {
        QLyKQPhatHanhCTiet_TpcpVO reval = null;
        try {
            QLyKQPhatHanhDAO quanlykqphathanhDAO = new QLyKQPhatHanhDAO(conn);
            reval = quanlykqphathanhDAO.getKQPH_CTIET_TPCPObject(map);
        } catch (Exception ex) {
            throw ex;
        }
        return reval;
    }

    //End

    //Chi tiet So huu

    public Collection getListQLyKQPhatHanh_CTIET_SOHUU(Map<String, Object> map) throws Exception,

            TPCPException {
        Collection reval = null;

        try {
            QLyKQPhatHanhDAO quanlykqphathanhDAO = new QLyKQPhatHanhDAO(conn);
            reval = quanlykqphathanhDAO.getListKQPH_CTIET_SoHuu(map);
        } catch (Exception ex) {
            throw ex;
        }
        return reval;
    }


    public QLyKQPhatHanhCTiet_SoHuuVO geQLyKQPhatHanhCTiet_SoHuuObject(Map<String, Object> map) throws Exception,
                                                                                                       TPCPException {
        QLyKQPhatHanhCTiet_SoHuuVO reval = null;
        try {
            QLyKQPhatHanhDAO quanlykqphathanhDAO = new QLyKQPhatHanhDAO(conn);
            reval = quanlykqphathanhDAO.getKQPH_SoHuu_TPCPObject(map);
        } catch (Exception ex) {
            throw ex;
        }
        return reval;
    }
    //End

    public QLyKQPhatHanhCTiet_TpcpVO getKQPH_CTIET_TPCP(String map) throws Exception {
        QLyKQPhatHanhCTiet_TpcpVO reval = null;
        try {
            QLyKQPhatHanhDAO kqphDAO = new QLyKQPhatHanhDAO(conn);
            reval = kqphDAO.getKQPH_CTIET_TPCP(map);
        } catch (Exception ex) {
            throw ex;
        }
        return reval;
    }
    // Luu file

    public void insertTP_KQPH_FILE(byte[] kqphPDFFile, long id_kq_ph,
                                   String ngay_imp,
                                   long nguoi_imp) throws Exception {
        try {
            QLyKQPhatHanhDAO kqphDAO = new QLyKQPhatHanhDAO(conn);
            kqphDAO.insertTP_KQPH_FILE(kqphPDFFile, id_kq_ph, ngay_imp,
                                       nguoi_imp);
        } catch (Exception ex) {
            throw ex;
        }
    }
}
