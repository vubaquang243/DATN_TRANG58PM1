package com.seatech.tp.banletraiphieutw.action;

import com.seatech.framework.datamanager.BaseDelegate;
import com.seatech.framework.exception.TPCPException;
import com.seatech.tp.banletraiphieutw.dao.BanLeTraiPhieuTwDAO;
import com.seatech.tp.banletraiphieutw.vo.BanLeTraiPhieuTwVO;

import java.sql.Connection;

import java.util.Collection;
import java.util.Map;

public class BanLeTraiPhieuTwDelegate extends BaseDelegate {

    public BanLeTraiPhieuTwDelegate(Connection conn) {
        super();
        this.conn = conn;
    }

    public long update(BanLeTraiPhieuTwVO vo) throws Exception, TPCPException {
        long id = 0;
        try {
            BanLeTraiPhieuTwDAO banletwDAO = new BanLeTraiPhieuTwDAO(conn);
            id = banletwDAO.update(vo);
            conn.commit();
        } catch (Exception ex) {
            conn.rollback();
            throw new TPCPException().createException("BanLeTw-0001",
                                                      "Không thể cập nhật Cơ sở dữ liệu");
        }
        return id;
    }

    public Collection getListBanLeTraiPhieuTwPaging(BanLeTraiPhieuTwVO vo,
                                                    Integer page,
                                                    Integer count,
                                                    Integer[] totalCount) throws Exception {
        Collection reval = null;

        try {
            BanLeTraiPhieuTwDAO banletwDAO = new BanLeTraiPhieuTwDAO(conn);
            reval =
                    banletwDAO.getListBanLeTwPaging(vo, page, count, totalCount);

        } catch (Exception ex) {
            throw ex;
        }
        return reval;
    }
    
    public Collection getAllBanLe(Map<String,Object>map)throws Exception{
      Collection reval= null;
      try{
        BanLeTraiPhieuTwDAO banleDao= new BanLeTraiPhieuTwDAO(conn);
          reval=banleDao.getAllBanLe(map);
      }catch(Exception ex){
        throw ex;
      }
      return reval;
    }
    
  public Collection getListBanLeTraiPhieuTwPagingPD(BanLeTraiPhieuTwVO vo,
                                                  Integer page,
                                                  Integer count,
                                                  Integer[] totalCount) throws Exception {
      Collection reval = null;

      try {
          BanLeTraiPhieuTwDAO banletwDAO = new BanLeTraiPhieuTwDAO(conn);
          reval =
                  banletwDAO.getListBanLeTwPagingPD(vo, page, count, totalCount);

      } catch (Exception ex) {
          throw ex;
      }
      return reval;
  }
    
  

    public BanLeTraiPhieuTwVO getBanLeTraiPhieuTwObject(Map<String, Object> map) throws Exception,
                                                                           TPCPException {
        BanLeTraiPhieuTwVO reval = null;
        try {
          BanLeTraiPhieuTwDAO banletwDAO = new BanLeTraiPhieuTwDAO(conn);
          reval = banletwDAO.getBanLeTwObject(map);
        } catch (Exception ex) {
            throw ex;
        }
        return reval;
    }
    
  public BanLeTraiPhieuTwVO getBanLeTraiPhieuTwObjectHienThi(Map<String, Object> map) throws Exception,
                                                                         TPCPException {
      BanLeTraiPhieuTwVO reval = null;
      try {
        BanLeTraiPhieuTwDAO banletwDAO = new BanLeTraiPhieuTwDAO(conn);
        reval = banletwDAO.getBanLeTwObjectHienThi(map);
      } catch (Exception ex) {
          throw ex;
      }
      return reval;
  }

    public long deleteBanLeTraiPhieuTw(BanLeTraiPhieuTwVO vo) throws Exception,
                                                        TPCPException {
        long id = 0;
        long idChiTiet = 0;
        try {
            BanLeTraiPhieuTwDAO banletwDAO = new BanLeTraiPhieuTwDAO(conn);
            id = banletwDAO.delete(vo);
            idChiTiet= banletwDAO.deleteChiTiet(vo);
            conn.commit();
        } catch (Exception ex) {
            conn.rollback();
            throw new TPCPException().createException(" BanLeTraiPhieuTw-0001",
                                                      "Không thể cập nhật Cơ sở dữ liệu");
        }
        return id;
    }
    
  public long deleteBanLeTraiPhieuChiTietTw(BanLeTraiPhieuTwVO vo) throws Exception,
                                                      TPCPException {
      long idChiTiet = 0;
      try {
          BanLeTraiPhieuTwDAO banletwDAO = new BanLeTraiPhieuTwDAO(conn);
          idChiTiet= banletwDAO.deleteChiTiet(vo);
          conn.commit();
      } catch (Exception ex) {
          conn.rollback();
          throw new TPCPException().createException(" BanLeTraiPhieuTw-0001",
                                                    "Không thể cập nhật Cơ sở dữ liệu");
      }
      return idChiTiet;
  }
    
}
