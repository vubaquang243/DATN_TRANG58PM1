package com.seatech.tp.sotonghoptpcp.action;

import com.seatech.framework.datamanager.BaseDelegate;
import com.seatech.tp.banletraiphieutw.action.BanLeTraiPhieuTwDelegate;

import com.seatech.tp.banletraiphieutw.dao.BanLeTraiPhieuTwDAO;
import com.seatech.tp.banletraiphieutw.vo.BanLeTraiPhieuTwVO;

import com.seatech.tp.sotonghoptpcp.dao.SoTongHopTpcpDao;
import com.seatech.tp.sotonghoptpcp.vo.SoTongHopTpcpVO;

import java.sql.Connection;

import java.sql.ResultSet;

import java.util.Collection;
import java.util.Vector;

public class SoTongHopTpcpDelegate extends BaseDelegate {

    public SoTongHopTpcpDelegate(Connection conn) {
        super();
        this.conn = conn;
    }

    public Collection getListSoTongHopTpcpPaging(SoTongHopTpcpVO vo,
                                                 Integer page, Integer count,
                                                 Integer[] totalCount) throws Exception {
        Collection reval = null;

        try {
            SoTongHopTpcpDao SoTongHopTpcp = new SoTongHopTpcpDao(conn);
            reval =
                    SoTongHopTpcp.getSoTongHopTpcpPaging(vo, page, count, totalCount);

        } catch (Exception ex) {
            throw ex;
        }
        return reval;
    }

    public ResultSet getSoTongHopTpcpList(SoTongHopTpcpVO vo) throws Exception {
        ResultSet reval = null;

        try {
            SoTongHopTpcpDao SoTongHopTpcp = new SoTongHopTpcpDao(conn);
            reval = SoTongHopTpcp.getSoTongHopTpcpList(vo);

        } catch (Exception ex) {
            throw ex;
        }
        return reval;
    }


}
