package com.seatech.tp.tttmua.action;

import com.seatech.framework.datamanager.BaseDelegate;
import com.seatech.framework.exception.TPCPException;
import com.seatech.tp.tttmua.dao.QLyLapBangKeDAO;
import com.seatech.tp.tttmua.vo.QLyLapBangKeVO;

import java.sql.Connection;

import java.sql.ResultSet;

import java.sql.SQLException;

import java.util.Collection;
import java.util.Map;

public class QLyLapBangKeDelegate extends BaseDelegate {
    public QLyLapBangKeDelegate(Connection conn) {
        super();
        this.conn = conn;
    }

    public Collection getListBangKeXacNhan(QLyLapBangKeVO vo, Integer page, Integer count, Integer[] totalCount) throws Exception {
        Collection reval = null;
        try {
            QLyLapBangKeDAO qlyTToanTienMuaDAO = new QLyLapBangKeDAO(conn);
            reval = qlyTToanTienMuaDAO.getListBangKeXacNhan(vo, page, count, totalCount);
        } catch (Exception ex) {
            throw ex;
        }
        return reval;
    }

    public QLyLapBangKeVO getTTinDThauFromKQPH(Map<String, Object> map, String ptph_tp) throws Exception {
        QLyLapBangKeVO reval = null;
        try {
            QLyLapBangKeDAO qlyLapBangKeDAO = new QLyLapBangKeDAO(conn);
            reval = qlyLapBangKeDAO.getTTinDThauFromKQPH(map, ptph_tp);
        } catch (Exception ex) {
            throw ex;
        }
        return reval;
    }

    public QLyLapBangKeVO getTTinBangKeObject(String guid) throws Exception {
        QLyLapBangKeVO reval = null;
        try {
            QLyLapBangKeDAO qlyLapBangKeDAO = new QLyLapBangKeDAO(conn);
            reval = qlyLapBangKeDAO.getTTinBangKeObject(guid);
        } catch (Exception ex) {
            throw ex;
        }
        return reval;
    }

    public QLyLapBangKeVO getTTinBangKeObject(String ma_tpcp, String dot_ph, String ngay_ph) throws Exception {
        QLyLapBangKeVO reval = null;
        try {
            QLyLapBangKeDAO qlyLapBangKeDAO = new QLyLapBangKeDAO(conn);
            reval = qlyLapBangKeDAO.getTTinBangKeObject(ma_tpcp, dot_ph, ngay_ph);
        } catch (Exception ex) {
            throw ex;
        }
        return reval;
    }

    public long update(QLyLapBangKeVO vo, String lstMaDvi) throws Exception, TPCPException {
        long id = 0;
        try {
            QLyLapBangKeDAO qlyLapBangKeDAO = new QLyLapBangKeDAO(conn);
            String insertGUID = vo.getGuid();
            id = qlyLapBangKeDAO.update(vo);
            if (insertGUID == null || insertGUID.equals("")) {
                //update trang thai thanh toan
                qlyLapBangKeDAO.updateTrangThaiLapBangKeDviSoHuu(vo.getListIdSoHuu(), vo.getPtph_tpcp(), "03");
                //update thong tin thanh toan
                qlyLapBangKeDAO.updateTrangThaiThanhToanKeDviSoHuu(vo.getSo_bke(), vo.getDot_ph(), vo.getMa_tpcp(), vo.getNgay_ph(), lstMaDvi);
                //update lít id dơn vi so huu
                QLyLapBangKeVO vo2 = new QLyLapBangKeVO();
                vo2.setList_id_so_huu(vo.getListIdSoHuu());
                vo2.setGuid(vo.getGuid());
                qlyLapBangKeDAO.update(vo2);
            }
            conn.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            conn.rollback();
            throw new TPCPException().createException("TPCP-0001", "Không thể cập nhật Cơ sở dữ liệu");
        }
        return id;
    }

    public long delete(QLyLapBangKeVO vo) throws Exception, TPCPException {
        long id = 0;
        try {
            QLyLapBangKeDAO qlyLapBangKeDAO = new QLyLapBangKeDAO(conn);
            id = qlyLapBangKeDAO.delete(vo);
            if (id != 0) {
                //update trang thai lap bang ke cua don vi so huu
                qlyLapBangKeDAO.updateTrangThaiLapBangKeDviSoHuu(vo.getList_id_so_huu(), vo.getPhuong_thuc_ph(), "02");
                //update thong tin bang thanh toan tien mua
                qlyLapBangKeDAO.updateTrangThaiThanhToanXoaBKeDviSoHuu(vo.getSo_bke(), vo.getDot_ph(), vo.getMa_tpcp(), vo.getNgay_ph());
            }
            conn.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            conn.rollback();
            throw new TPCPException().createException("TPCP-0001", "Không thể cập nhật Cơ sở dữ liệu");
        }
        return id;
    }

    public ResultSet getDVSoHuuObject(String guid) throws SQLException, TPCPException {
        ResultSet rs = null;
        try {
            QLyLapBangKeDAO qlyLapBangKeDAO = new QLyLapBangKeDAO(conn);
            rs = qlyLapBangKeDAO.getDVSoHuuObject(guid);
            conn.commit();
        } catch (Exception ex) {
            conn.rollback();
            throw new TPCPException().createException("TPCP-0001", "Không thể cập nhật Cơ sở dữ liệu");
        }
        return rs;
    }
}
