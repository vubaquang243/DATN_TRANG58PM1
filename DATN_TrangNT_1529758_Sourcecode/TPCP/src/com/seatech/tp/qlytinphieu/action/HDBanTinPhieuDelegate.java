package com.seatech.tp.qlytinphieu.action;

import com.seatech.framework.datamanager.BaseDelegate;
import com.seatech.framework.exception.TPCPException;
import com.seatech.tp.qlytinphieu.dao.HDBanTinPhieuDao;
import com.seatech.tp.qlytinphieu.vo.HDBanTinPhieuVo;
import com.seatech.tp.ttindthau.action.QLyTTinDauThauDelegate;
import com.seatech.tp.ttindthau.dao.QLyTTinDauThauDAO;
import com.seatech.tp.ttindthau.vo.ThongTinDauThauVO;

import java.sql.Connection;

import java.util.Collection;
import java.util.Map;

public class HDBanTinPhieuDelegate extends BaseDelegate {
    public HDBanTinPhieuDelegate(Connection conn) {
        super();
        this.conn = conn;
    }

    public long update(HDBanTinPhieuVo vo) throws Exception, TPCPException {
        long id = 0;
        try {
            HDBanTinPhieuDao ttindthauDAO = new HDBanTinPhieuDao(conn);
            id = ttindthauDAO.insert(vo);
            conn.commit();
        } catch (Exception ex) {
//            ex.printStackTrace();
            conn.rollback();
            throw new TPCPException().createException("TPCP-0001",
                                                      "Không thể cập nhật Cơ sở dữ liệu");
        }
        return id;
    }

    public Collection getLisHDTin_PhieuPDPaging(HDBanTinPhieuVo vo,
                                                Integer page, Integer count,
                                                Integer[] totalCount) throws Exception {
        Collection reval = null;

        try {
            HDBanTinPhieuDao hdTinPhieuDao = new HDBanTinPhieuDao(conn);
            reval =
                    hdTinPhieuDao.getLisHDTin_PhieuPDPaging(vo, page, count, totalCount);

        } catch (Exception ex) {
            throw ex;
        }
        return reval;
    }

    public Collection getLisHDTin_PhieuPaging(HDBanTinPhieuVo vo, Integer page,
                                              Integer count,
                                              Integer[] totalCount) throws Exception {
        Collection reval = null;

        try {
            HDBanTinPhieuDao hdTinPhieuDao = new HDBanTinPhieuDao(conn);
            reval =
                    hdTinPhieuDao.getLisHDTin_PhieuPaging(vo, page, count, totalCount);

        } catch (Exception ex) {
            throw ex;
        }
        return reval;
    }

    public HDBanTinPhieuVo getHDTinPhieuObject(Map<String, Object> map) throws Exception,
                                                                               TPCPException {
        HDBanTinPhieuVo reval = null;
        try {
            HDBanTinPhieuDao hdTinPhieuDao = new HDBanTinPhieuDao(conn);
            reval = hdTinPhieuDao.getHDTinPhieuObject(map);
        } catch (Exception ex) {
            throw ex;
        }
        return reval;
    }

    public HDBanTinPhieuVo getHDTinPhieuObject2(Map<String, Object> map) throws Exception,
                                                                                TPCPException {
        HDBanTinPhieuVo reval = null;
        try {
            HDBanTinPhieuDao hdTinPhieuDao = new HDBanTinPhieuDao(conn);
            reval = hdTinPhieuDao.getHDTinPhieuObject2(map);
        } catch (Exception ex) {
            throw ex;
        }
        return reval;
    }

    public long deleteTTDT(HDBanTinPhieuVo vo) throws Exception,
                                                      TPCPException {
        long id = 0;
        try {
            HDBanTinPhieuDao hdTinPhieuDao = new HDBanTinPhieuDao(conn);
            id = hdTinPhieuDao.delete(vo);
            conn.commit();
        } catch (Exception ex) {
            conn.rollback();
            throw new TPCPException().createException("TPCP-0001",
                                                      "Không thể cập nhật Cơ sở dữ liệu");
        }
        return id;
    }
}
