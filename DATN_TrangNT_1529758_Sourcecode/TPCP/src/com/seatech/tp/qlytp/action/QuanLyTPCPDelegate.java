package com.seatech.tp.qlytp.action;

import com.seatech.framework.datamanager.BaseDelegate;
import com.seatech.framework.exception.TPCPException;
import com.seatech.tp.qlykehoach.dao.QuanLyKeHoachDao;
import com.seatech.tp.qlykehoach.vo.QuanLyKeHoachVo;
import com.seatech.tp.qlytp.dao.QuanLyTPCPDAO;
import com.seatech.tp.qlytp.vo.PhuongThucPhatHanhVO;
import com.seatech.tp.qlytp.vo.QuanLyTPCPVO;

import com.seatech.tp.ttindthau.dao.QLyTTinDauThauDAO;
import com.seatech.tp.ttindthau.vo.ThongTinDauThauVO;

import java.sql.Connection;

import java.util.Collection;
import java.util.Map;

public class QuanLyTPCPDelegate extends BaseDelegate {
    public QuanLyTPCPDelegate(Connection conn) {
        super();
        this.conn = conn;
    }

    public long update(QuanLyTPCPVO vo) throws Exception, TPCPException {
        long id = 0;
        try {
            QuanLyTPCPDAO ttindthauDAO = new QuanLyTPCPDAO(conn);
            id = ttindthauDAO.insert(vo);
            conn.commit();
        } catch (Exception ex) {
            conn.rollback();
            throw new TPCPException().createException("TPCP-0001", "Không thể cập nhật Cơ sở dữ liệu");
        }
        return id;
    }

    public long insertTPCP(QuanLyTPCPVO vo) throws Exception, TPCPException {
        long id = 0;
        try {
            QuanLyTPCPDAO tpcpDAO = new QuanLyTPCPDAO(conn);
            id = tpcpDAO.insert(vo);
            conn.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            conn.rollback();
            throw new TPCPException().createException("TPCP-0001", "Không thể cập nhật Cơ sở dữ liệu");
        }
        return id;
    }

    public long updateTPCP(QuanLyTPCPVO vo) throws Exception, TPCPException {
        long id = 0;
        try {
            QuanLyTPCPDAO tpcpDAO = new QuanLyTPCPDAO(conn);
            id = tpcpDAO.insert(vo);
            conn.commit();
        } catch (Exception ex) {
            conn.rollback();
            throw new TPCPException().createException("TPCP-0001", "Không thể cập nhật Cơ sở dữ liệu");
        }
        return id;
    }

    public Collection getListTTDTPaging(QuanLyTPCPVO vo, Integer page, Integer count, Integer[] totalCount) throws Exception {
        Collection reval = null;

        try {
            QuanLyTPCPDAO ttindthauDAO = new QuanLyTPCPDAO(conn);
            reval = ttindthauDAO.getListTPCPPaging(vo, page, count, totalCount);

        } catch (Exception ex) {
            throw ex;
        }
        return reval;
    }

    public Collection getListPTPH(PhuongThucPhatHanhVO vo) throws Exception {
        Collection reval = null;

        try {
            QuanLyTPCPDAO ttindthauDAO = new QuanLyTPCPDAO(conn);
            reval = ttindthauDAO.getListPTPH(vo);

        } catch (Exception ex) {
            throw ex;
        }
        return reval;
    }

    public QuanLyTPCPVO getTTDTObject(String guid) throws Exception, TPCPException {
        QuanLyTPCPVO reval = null;
        try {
            QuanLyTPCPDAO ttindthauDAO = new QuanLyTPCPDAO(conn);
            reval = ttindthauDAO.getTPCPObject(guid);
        } catch (Exception ex) {
            throw ex;
        }
        return reval;
    }

    public QuanLyTPCPVO getTTDTObject(Map<String, Object> map) throws Exception, TPCPException {
        QuanLyTPCPVO reval = null;
        try {
            QuanLyTPCPDAO ttindthauDAO = new QuanLyTPCPDAO(conn);
            reval = ttindthauDAO.getTTDTObject(map);
        } catch (Exception ex) {
            throw ex;
        }
        return reval;
    }


    //manhvv

    public Collection getLstTPCP_TIN_PHIEU() throws Exception {
        Collection reval = null;
        try {
            QuanLyTPCPDAO tpcpDao = new QuanLyTPCPDAO(conn);
            reval = tpcpDao.getLstTPCP_TIN_PHIEU();
        } catch (Exception ex) {
            throw ex;
        }
        return reval;
    }

    public Collection getAllListTPCP_TIN_PHIEU() throws Exception {
        Collection reval = null;
        try {
            QuanLyTPCPDAO tpcpDao = new QuanLyTPCPDAO(conn);
            reval = tpcpDao.getAllListTPCP_TIN_PHIEU();
        } catch (Exception ex) {
            throw ex;
        }
        return reval;
    }

    public QuanLyTPCPVO getTPCPObjectTin_Phieu(Map<String, Object> map) throws Exception {
        QuanLyTPCPVO reval = null;
        try {
            QuanLyTPCPDAO ttindthauDAO = new QuanLyTPCPDAO(conn);
            reval = ttindthauDAO.getTPCPObjectTin_Phieu(map);
        } catch (Exception ex) {
            throw ex;
        }
        return reval;
    }


    public QuanLyTPCPVO getDMTPCP(String Ma_TPCP) throws Exception, TPCPException {
        QuanLyTPCPVO reval = null;
        try {
            QuanLyTPCPDAO ttindthauDAO = new QuanLyTPCPDAO(conn);
            reval = ttindthauDAO.getDMTPCP(Ma_TPCP);
        } catch (Exception ex) {
            throw ex;
        }
        return reval;
    }


    public Collection getAllListTPCP() throws Exception {
        Collection reval = null;

        try {
            QuanLyTPCPDAO tpcpDAO = new QuanLyTPCPDAO(conn);
            reval = tpcpDAO.getAllListTPCP();

        } catch (Exception ex) {
            throw ex;
        }
        return reval;
    }

    public Collection getAllListTPCP_Ban_Le() throws Exception {
        Collection reval = null;
        try {
            QuanLyTPCPDAO tpcpDAO = new QuanLyTPCPDAO(conn);
            reval = tpcpDAO.getAllListTPCP_Ban_Le();
        } catch (Exception ex) {
            throw ex;
        }
        return reval;
    }

    public Collection getAllListTPCPFromDotPH(String dot_ph) throws Exception {
        Collection reval = null;

        try {
            QuanLyTPCPDAO tpcpDAO = new QuanLyTPCPDAO(conn);
            reval = tpcpDAO.getAllListTPCPFromDotPH(dot_ph);

        } catch (Exception ex) {
            throw ex;
        }
        return reval;
    }

    public Collection getAllListTPCPFromNgayPH(String ngay_ph) throws Exception {
        Collection reval = null;

        try {
            QuanLyTPCPDAO tpcpDAO = new QuanLyTPCPDAO(conn);
            reval = tpcpDAO.getAllListTPCPFromNgayPH(ngay_ph);

        } catch (Exception ex) {
            throw ex;
        }
        return reval;
    }

    public Collection getAllListTPCP_DT() throws Exception {
        Collection reval = null;

        try {
            QuanLyTPCPDAO tpcpDAO = new QuanLyTPCPDAO(conn);
            reval = tpcpDAO.getAllListTPCP_DT();

        } catch (Exception ex) {
            throw ex;
        }
        return reval;
    }

    public long delete(QuanLyTPCPVO vo) throws Exception, TPCPException {
        long id = 0;
        try {
            QuanLyTPCPDAO ttindthauDAO = new QuanLyTPCPDAO(conn);
            id = ttindthauDAO.delete(vo);
            conn.commit();
        } catch (Exception ex) {
            conn.rollback();
            throw new TPCPException().createException("TPCP-0020", vo.getMa_tp());
        }
        return id;
    }
}
