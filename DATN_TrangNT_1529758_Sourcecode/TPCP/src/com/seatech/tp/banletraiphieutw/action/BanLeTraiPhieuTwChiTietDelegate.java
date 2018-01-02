package com.seatech.tp.banletraiphieutw.action;

import com.seatech.framework.datamanager.BaseDelegate;
import com.seatech.framework.exception.TPCPException;
import com.seatech.tp.banletraiphieutw.dao.BanLeTraiPhieuTwChiTietDAO;
import com.seatech.tp.banletraiphieutw.vo.BanLeTraiPhieuTwChiTietVO;

import java.sql.Connection;

import java.util.Collection;
import java.util.Map;

public class BanLeTraiPhieuTwChiTietDelegate extends BaseDelegate {

    public BanLeTraiPhieuTwChiTietDelegate(Connection conn) {
        super();
        this.conn = conn;
    }

    public long update(BanLeTraiPhieuTwChiTietVO vo) throws Exception,
                                                            TPCPException {
        long id = 0;
        try {
            BanLeTraiPhieuTwChiTietDAO banletwchitetDAO =
                new BanLeTraiPhieuTwChiTietDAO(conn);
            id = banletwchitetDAO.insert(vo);
            conn.commit();
        } catch (Exception ex) {
            conn.rollback();
            throw new TPCPException().createException("BanLeTwchitiet-0001",
                                                      "Không thể cập nhật Cơ sở dữ liệu");
        }
        return id;
    }

    public Collection getListBanLeTraiPhieuTwPaging(BanLeTraiPhieuTwChiTietVO vo,
                                                    Integer page,
                                                    Integer count,
                                                    Integer[] totalCount) throws Exception {
        Collection reval = null;

        try {
            BanLeTraiPhieuTwChiTietDAO banletwchitetDAO =
                new BanLeTraiPhieuTwChiTietDAO(conn);
            reval =
                    banletwchitetDAO.getListBanLeTwChiTietPaging(vo, page, count,
                                                                 totalCount);

        } catch (Exception ex) {
            throw ex;
        }
        return reval;
    }

    public Collection getListBanLeTraiPhieuChiTietTw(Map<String, Object> map,
                                                     Integer trangthai) throws Exception,

            TPCPException {
        // trang thai = 0 -> update
        // trang thai = 1 -> view
        Collection reval = null;

        try {
            BanLeTraiPhieuTwChiTietDAO banletwchitetDAO =
                new BanLeTraiPhieuTwChiTietDAO(conn);
            if (trangthai == 0) {
                reval = banletwchitetDAO.getListBanLeTwChiTietUpdate(map);
            } else {
                reval = banletwchitetDAO.getListBanLeTwChiTietView(map);
            }
        } catch (Exception ex) {
            throw ex;
        }
        return reval;
    }


    public BanLeTraiPhieuTwChiTietVO getBanLeTraiPhieuTwChiTietObject(Map<String, Object> map) throws Exception,
                                                                                                      TPCPException {
        BanLeTraiPhieuTwChiTietVO reval = null;
        try {
            BanLeTraiPhieuTwChiTietDAO banletwchitetDAO =
                new BanLeTraiPhieuTwChiTietDAO(conn);
            reval = banletwchitetDAO.getBanLeTwChiTietObject(map);
        } catch (Exception ex) {
            throw ex;
        }
        return reval;
    }

    public long deleteBanLeTraiPhieuTwChiTiet(BanLeTraiPhieuTwChiTietVO vo) throws Exception,
                                                                                   TPCPException {
        long id = 0;
        try {
            BanLeTraiPhieuTwChiTietDAO banletwchitetDAO =
                new BanLeTraiPhieuTwChiTietDAO(conn);
            id = banletwchitetDAO.delete(vo);
            conn.commit();
        } catch (Exception ex) {
            conn.rollback();
            throw new TPCPException().createException("BanLeTraiPhieuTwChiTiet-0001",
                                                      "Không thể cập nhật Cơ sở dữ liệu");
        }
        return id;
    }
}
