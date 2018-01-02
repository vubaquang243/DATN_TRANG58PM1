package com.seatech.tp.qlykqphtinphieu.action;

import com.seatech.framework.datamanager.BaseDelegate;
import com.seatech.framework.exception.TPCPException;
import com.seatech.tp.kqphathanh.action.QLyKQPhatHanhDelegate;
import com.seatech.tp.kqphathanh.dao.QLyKQPhatHanhDAO;
import com.seatech.tp.kqphathanh.vo.QLyKQPhatHanhVO;

import com.seatech.tp.qlykqphtinphieu.dao.KQPHTinPhieuDao;
import com.seatech.tp.qlykqphtinphieu.vo.KQPHTinPhieuCTSoHuuVo;
import com.seatech.tp.qlykqphtinphieu.vo.KQPHTinPhieuVo;

import java.sql.Connection;

import java.util.Collection;
import java.util.Map;

public class KQPHTinPhieuDelegate extends BaseDelegate {
    public KQPHTinPhieuDelegate(Connection conn) {
        super();
        this.conn = conn;
    }

    public long update(KQPHTinPhieuVo vo) throws Exception, TPCPException {
        long id = 0;
        try {
            KQPHTinPhieuDao kqphDAO = new KQPHTinPhieuDao(conn);
            id = kqphDAO.update(vo);
            conn.commit();
        } catch (Exception ex) {
            conn.rollback();
            throw new TPCPException().createException("kqphathanh-0001",
                                                      "KhÃƒÂ´ng thÃ¡Â»Æ’ cÃ¡ÂºÂ­p nhÃ¡ÂºÂ­t CÃ†Â¡ sÃ¡Â»Å¸ dÃ¡Â»Â¯ liÃ¡Â»â€¡u");
        }
        return id;
    }

    public void insertTP_KQPH_TIN_PHIEU_FILE(byte[] kqphPDFFile, long id_kq_ph,
                                             String ngay_imp,
                                             long nguoi_imp) throws Exception {
        try {
            KQPHTinPhieuDao kqphDAO = new KQPHTinPhieuDao(conn);
            kqphDAO.insertTP_KQPH_TIN_PHIEU_FILE(kqphPDFFile, id_kq_ph,
                                                 ngay_imp, nguoi_imp);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public Collection getListKQPHPaging(KQPHTinPhieuVo vo, Integer page,
                                        Integer count,
                                        Integer[] totalCount) throws Exception {
        Collection reval = null;

        try {
            KQPHTinPhieuDao kqphDAO = new KQPHTinPhieuDao(conn);
            reval =
                    kqphDAO.getListKQPHTinPhieuPaging(vo, page, count, totalCount);

        } catch (Exception ex) {
            throw ex;
        }
        return reval;
    }

    public Collection getListKQPHTinPhieuPheDuyetPagings(KQPHTinPhieuVo vo,
                                                         Integer page,
                                                         Integer count,
                                                         Integer[] totalCount) throws Exception {
        Collection reval = null;

        try {
            KQPHTinPhieuDao kqphDAO = new KQPHTinPhieuDao(conn);
            reval =
                    kqphDAO.getListKQPHTinPhieuPheDuyetPagings(vo, page, count, totalCount);

        } catch (Exception ex) {
            throw ex;
        }
        return reval;
    }

    public KQPHTinPhieuVo getQLyKQPhatHanhTinPhieuObject(Map<String, Object> map) throws Exception,
                                                                                         TPCPException {
        KQPHTinPhieuVo reval = null;
        try {
            KQPHTinPhieuDao kqphDAO = new KQPHTinPhieuDao(conn);
            reval = kqphDAO.gettKQPHTinPhieuObject(map);
        } catch (Exception ex) {
            throw ex;
        }
        return reval;
    }


    public Collection getListQLyKQPhatHanh_CTIET_TPCP(Map<String, Object> map) throws Exception,

            TPCPException {
        Collection reval = null;

        try {
            KQPHTinPhieuDao quanlykqphathanhDAO = new KQPHTinPhieuDao(conn);
            reval = quanlykqphathanhDAO.getListKQPH_CTIET_TPCP(map);
        } catch (Exception ex) {
            throw ex;
        }
        return reval;
    }

    public Collection getListQLyKQPhatHanh_CTIET_SOHUU(Map<String, Object> map) throws Exception,

            TPCPException {
        Collection reval = null;

        try {
            KQPHTinPhieuDao quanlykqphathanhDAO = new KQPHTinPhieuDao(conn);
            reval = quanlykqphathanhDAO.getListKQPH_CTIET_SHTPCP(map);
        } catch (Exception ex) {
            throw ex;
        }
        return reval;
    }

    public long deleteQLyKQPhatHanh(KQPHTinPhieuVo vo) throws Exception,
                                                              TPCPException {
        long id = 0;
        long idChiTiet_TPCP = 0;
        long idChiTiet_SoHuu = 0;
        long id_kqph_file = 0;
        try {
            KQPHTinPhieuDao kqphDAO = new KQPHTinPhieuDao(conn);
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


}
