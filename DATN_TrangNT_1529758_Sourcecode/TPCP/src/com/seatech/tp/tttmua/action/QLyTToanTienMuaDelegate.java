package com.seatech.tp.tttmua.action;

import com.seatech.framework.datamanager.BaseDelegate;
import com.seatech.framework.exception.TPCPException;
import com.seatech.tp.ttindthau.dao.QLyTTinDauThauDAO;
import com.seatech.tp.ttindthau.vo.ThongTinDauThauVO;
import com.seatech.tp.tttmua.dao.QLyTToanTienMuaDAO;
import com.seatech.tp.tttmua.vo.QLyTToanTienMuaVO;

import com.seatech.tp.tttmua.vo.TTinDViSoHuuVO;

import java.sql.Connection;

import java.util.Collection;
import java.util.Iterator;

public class QLyTToanTienMuaDelegate extends BaseDelegate {
    public QLyTToanTienMuaDelegate(Connection conn) {
        super();
        this.conn = conn;
    }

    public String getDonViTinhFromMa(QLyTToanTienMuaVO vo) throws Exception {
        String reval = "đồng";
        try {
            QLyTToanTienMuaDAO qlyTToanTienMuaDAO = new QLyTToanTienMuaDAO(conn);
            reval = qlyTToanTienMuaDAO.getDonViTinhFromMa(vo);
        } catch (Exception ex) {
            throw ex;
        }
        return reval;
    }    
    public Collection getListDonViSoHuu(QLyTToanTienMuaVO vo) throws Exception {
        Collection reval = null;
        try {
            QLyTToanTienMuaDAO qlyTToanTienMuaDAO = new QLyTToanTienMuaDAO(conn);
            reval = qlyTToanTienMuaDAO.getListDonViSoHuuThanhToan(vo);
        } catch (Exception ex) {
            throw ex;
        }
        return reval;
    }

    public Collection traCuuTTinTToan(QLyTToanTienMuaVO vo, Integer page, Integer count, Integer[] totalCount) throws Exception {
        Collection reval = null;
        try {
            QLyTToanTienMuaDAO qlyTToanTienMuaDAO = new QLyTToanTienMuaDAO(conn);
            reval = qlyTToanTienMuaDAO.traCuuTTinTToan(vo, page, count, totalCount);
        } catch (Exception ex) {
            throw ex;
        }
        return reval;
    }


    public Collection getTTinTrungThauDViSoHuu(QLyTToanTienMuaVO vo) throws Exception {
        Collection reval = null;
        try {
            QLyTToanTienMuaDAO qlyTToanTienMuaDAO = new QLyTToanTienMuaDAO(conn);
            reval = qlyTToanTienMuaDAO.getTTinTrungThauDViSoHuu(vo);
        } catch (Exception ex) {
            throw ex;
        }
        return reval;
    }

    public Collection getTTinThanhToanDViSoHuu(QLyTToanTienMuaVO vo) throws Exception {
        Collection reval = null;
        try {
            QLyTToanTienMuaDAO qlyTToanTienMuaDAO = new QLyTToanTienMuaDAO(conn);
            reval = qlyTToanTienMuaDAO.getTTinThanhToanDViSoHuu(vo);
        } catch (Exception ex) {
            throw ex;
        }
        return reval;
    }

    public long update(Collection lstTTinTToan,QLyTToanTienMuaVO vo, String lstMaDviCu) throws Exception, TPCPException {
        long id = 0;
        try {
            QLyTToanTienMuaDAO qlyTToanTienMuaDAO = new QLyTToanTienMuaDAO(conn);
            qlyTToanTienMuaDAO.update(lstTTinTToan,vo,lstMaDviCu);            
            conn.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            conn.rollback();
            throw new TPCPException().createException("TPCP-0001", "Không thể cập nhật Cơ sở dữ liệu");
        }
        return id;
    }
    public long update(Collection lstTTinTToan,Collection lstTToanDvi, QLyTToanTienMuaVO vo) throws Exception, TPCPException {
        long id = 0;
        try {
            QLyTToanTienMuaDAO qlyTToanTienMuaDAO = new QLyTToanTienMuaDAO(conn);
            qlyTToanTienMuaDAO.update(lstTTinTToan,lstTToanDvi,vo);            
            conn.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            conn.rollback();
            throw new TPCPException().createException("TPCP-0001", "Không thể cập nhật Cơ sở dữ liệu");
        }
        return id;
    }

    public Collection getListDonViLapBKe(QLyTToanTienMuaVO vo, Integer page, Integer count, Integer[] totalCount) throws Exception {
        Collection reval = null;
        try {
            QLyTToanTienMuaDAO qlyTToanTienMuaDAO = new QLyTToanTienMuaDAO(conn);
            reval = qlyTToanTienMuaDAO.getListDonViLapBKe(vo, page, count, totalCount);
        } catch (Exception ex) {
            throw ex;
        }
        return reval;
    }
    public Collection getListDonViLapBKe(QLyTToanTienMuaVO vo) throws Exception {
        Collection reval = null;
        try {
            QLyTToanTienMuaDAO qlyTToanTienMuaDAO = new QLyTToanTienMuaDAO(conn);
            reval = qlyTToanTienMuaDAO.getListDonViLapBKe(vo);
        } catch (Exception ex) {
            throw ex;
        }
        return reval;
    }
    public Collection getListDonViLapBKe(String ptph_tpcp, String ma_tpcp,String dot_ph, String lstDviSoHuu) throws Exception {
        Collection reval = null;
        try {
            QLyTToanTienMuaDAO qlyTToanTienMuaDAO = new QLyTToanTienMuaDAO(conn);
            reval = qlyTToanTienMuaDAO.getListDonViLapBKe(ptph_tpcp, ma_tpcp,dot_ph, lstDviSoHuu);
        } catch (Exception ex) {
            throw ex;
        }
        return reval;
    }

    public QLyTToanTienMuaVO getThongTinPH(String ma_tpcp, String ngay_ph) throws Exception {
        QLyTToanTienMuaVO reval = null;
        try {
            QLyTToanTienMuaDAO qlyTToanTienMuaDAO = new QLyTToanTienMuaDAO(conn);
            reval = qlyTToanTienMuaDAO.getThongTinPH(ma_tpcp, ngay_ph);
        } catch (Exception ex) {
            throw ex;
        }
        return reval;
    }
}
