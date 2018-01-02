package com.seatech.tp.dmtraichu.action;

import com.seatech.framework.datamanager.BaseDelegate;

import com.seatech.framework.exception.TPCPException;
import com.seatech.tp.banletraiphieutw.dao.BanLeTraiPhieuTwDAO;
import com.seatech.tp.banletraiphieutw.vo.BanLeTraiPhieuTwVO;
import com.seatech.tp.dmtraichu.dao.DMTraiChuDAO;

import com.seatech.tp.dmtraichu.vo.DMTraiChuVO;

import com.seatech.tp.qlytp.dao.QuanLyTPCPDAO;
import com.seatech.tp.qlytp.vo.QuanLyTPCPVO;

import java.sql.Connection;

import java.util.Collection;
import java.util.Map;

public class DMTraiChuDelegate extends BaseDelegate {
    public DMTraiChuDelegate(Connection conn) {
        super();
        this.conn = conn;
    }

    public long insertDVSH(DMTraiChuVO vo) throws Exception, TPCPException {
        long id = 0;
        try {
            DMTraiChuDAO dmTraiChuDAO = new DMTraiChuDAO(conn);
            id = dmTraiChuDAO.insert(vo);
            conn.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            conn.rollback();
            throw new TPCPException().createException("TPCP-0001", "Không thể cập nhật Cơ sở dữ liệu");
        }
        return id;
    }

    public long delete(DMTraiChuVO vo) throws Exception, TPCPException {
        long id = 0;
        try {
            DMTraiChuDAO dmTraiChuDAO = new DMTraiChuDAO(conn);
            id = dmTraiChuDAO.delete(vo);
            conn.commit();
        } catch (Exception ex) {
            conn.rollback();
            throw new TPCPException().createException("TPCP-0001", "Không thể cập nhật Cơ sở dữ liệu");
        }
        return id;
    }

    public Collection getDMTraiChu(Map<String, Object> map) throws Exception {
        Collection reval = null;

        try {
            DMTraiChuDAO dmTraiChuDAO = new DMTraiChuDAO(conn);
            reval = dmTraiChuDAO.getDMTraiChu(map);

        } catch (Exception ex) {
            throw ex;
        }
        return reval;
    }

    public Collection getDMTraiChuPaging(DMTraiChuVO vo, Integer page, Integer count, Integer[] totalCount) throws Exception {
        Collection reval = null;

        try {
            DMTraiChuDAO dmTraiChuDAO = new DMTraiChuDAO(conn);
            reval = dmTraiChuDAO.getDMTraiChuPaging(vo, page, count, totalCount);

        } catch (Exception ex) {
            throw ex;
        }
        return reval;
    }

    public Collection getDMTraiChu() throws Exception {
        Collection reval = null;

        try {
            DMTraiChuDAO dmTraiChuDAO = new DMTraiChuDAO(conn);
            reval = dmTraiChuDAO.getDMTraiChu();

        } catch (Exception ex) {
            throw ex;
        }
        return reval;
    }

    public Collection getDMTraiChuBanLe() throws Exception {
        Collection reval = null;

        try {
            DMTraiChuDAO dmTraiChuDAO = new DMTraiChuDAO(conn);
            reval = dmTraiChuDAO.getDMTraiChuBanLe();

        } catch (Exception ex) {
            throw ex;
        }
        return reval;
    }

    public DMTraiChuVO getDMTraiChuVO(Map<String, Object> map) throws Exception, TPCPException {
        DMTraiChuVO reval = null;
        try {
            DMTraiChuDAO dmTraiChuDAO = new DMTraiChuDAO(conn);
            reval = dmTraiChuDAO.getDMTraiChuObject(map);
        } catch (Exception ex) {
            throw ex;
        }
        return reval;
    }
}
