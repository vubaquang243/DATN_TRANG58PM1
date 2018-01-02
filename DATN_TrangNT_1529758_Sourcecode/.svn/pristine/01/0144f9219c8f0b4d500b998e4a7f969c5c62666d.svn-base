package com.seatech.tp.qlykehoach.action;

import com.seatech.framework.datamanager.BaseDelegate;
import com.seatech.framework.exception.TPCPException;
import com.seatech.tp.qlykehoach.dao.QuanLyKeHoachDao;
import com.seatech.tp.qlykehoach.vo.QuanLyKeHoachVo;

import com.seatech.tp.qlykehoach.action.QuanLyKeHoachDelegate;
import com.seatech.tp.qlykehoach.dao.QuanLyKeHoachDao;
import com.seatech.tp.quanlykehoachquy.dao.QuanLyKeHoachQuyDao;
import com.seatech.tp.qlykehoach.vo.KHPHChiTietNamVO;

import com.seatech.tp.quanlykehoachquy.vo.KHPHChiTietVO;
import com.seatech.tp.quanlykehoachquy.vo.QuanLyKeHoachQuyVO;

import java.sql.Connection;

import java.sql.ResultSet;

import java.util.Collection;
import java.util.Map;

public class QuanLyKeHoachDelegate extends BaseDelegate {
  public QuanLyKeHoachDelegate(Connection conn) {
      super();
      this.conn = conn;
  }

  public long update(QuanLyKeHoachVo vo) throws Exception, TPCPException {
      long id = 0;
      try {
          QuanLyKeHoachDao ttindthauDAO = new QuanLyKeHoachDao(conn);
          id = ttindthauDAO.insert(vo);
          conn.commit();
      } catch (Exception ex) {
          ex.printStackTrace();
          conn.rollback();
          throw new TPCPException().createException("TPCP-0001",
                                                    "Không thể cập nhật Cơ sở dữ liệu");
      }
      return id;
  }

  public long updateKHCT(KHPHChiTietNamVO vo) throws Exception, TPCPException {
      long id = 0;
      try {
          QuanLyKeHoachDao ttindthauDAO = new QuanLyKeHoachDao(conn);
          id = ttindthauDAO.insertKHCT(vo);
          conn.commit();
      } catch (Exception ex) {
          conn.rollback();
          throw new TPCPException().createException("TPCP-0001",
                                                    "Không thể cập nhật Cơ sở dữ liệu");
      }
      return id;
  }

  public Collection getListQLKHPaging(QuanLyKeHoachVo vo, Integer page,
                                      Integer count,
                                      Integer[] totalCount) throws Exception {
      Collection reval = null;

      try {
          QuanLyKeHoachDao ttindthauDAO = new QuanLyKeHoachDao(conn);
          reval =
                  ttindthauDAO.getListQLKHPaging(vo, page, count, totalCount);

      } catch (Exception ex) {
          throw ex;
      }
      return reval;
  }
  
  
  
  public Collection getListQLKHPagingCheck(QuanLyKeHoachVo vo, Integer page,
                                    Integer count,
                                    Integer[] totalCount) throws Exception {
    Collection reval = null;

    try {
        QuanLyKeHoachDao ttindthauDAO = new QuanLyKeHoachDao(conn);
        reval =
                ttindthauDAO.getListQLKHPagingCheck(vo, page, count, totalCount);

    } catch (Exception ex) {
        throw ex;
    }
    return reval;
  }
  public Collection getListPD(QuanLyKeHoachVo vo, Integer page,
                              Integer count,
                              Integer[] totalCount) throws Exception {
      Collection reval = null;

      try {
          QuanLyKeHoachDao ttindthauDAO = new QuanLyKeHoachDao(conn);
          reval = ttindthauDAO.getListPD(vo, page, count, totalCount);

      } catch (Exception ex) {
          throw ex;
      }
      return reval;
  }

  public QuanLyKeHoachVo getQLKHObject(Map<String, Object> map) throws Exception,
                                                                          TPCPException {
      QuanLyKeHoachVo reval = null;
      try {
          QuanLyKeHoachDao ttindthauDAO = new QuanLyKeHoachDao(conn);
          reval = ttindthauDAO.getQLKHObject(map);
      } catch (Exception ex) {
          throw ex;
      }
      return reval;
  }

  public QuanLyKeHoachVo getTpKeHoachObject(String guid) throws Exception,
                                                                   TPCPException {
      QuanLyKeHoachVo reval = null;
      try {
          QuanLyKeHoachDao tpcpDAO = new QuanLyKeHoachDao(conn);
          reval = tpcpDAO.getQLKHObject(guid);
      } catch (Exception ex) {
          throw ex;
      }
      return reval;
  }
  
  // by manhvv
  public QuanLyKeHoachVo getQLKHCheckObject(Map<String,Object> map) throws Exception ,TPCPException{
    QuanLyKeHoachVo reval= null;
    try{
        QuanLyKeHoachDao tpcpDao= new QuanLyKeHoachDao(conn);
        reval=tpcpDao.getQLKHCheckObject(map);
    }catch(Exception e){
      throw e;
    }
    return reval;
  }
  public QuanLyKeHoachVo getTpKeHoachCheckObject(String sNamph, String sThoigianph) throws Exception,
                                                                 TPCPException {
    QuanLyKeHoachVo reval = null;
    try {
        QuanLyKeHoachDao tpcpDAO = new QuanLyKeHoachDao(conn);
        reval = tpcpDAO.getTpKeHoachCheckObject(sNamph,sThoigianph);
    } catch (Exception ex) {
        throw ex;
    }
    return reval;
  }

  public Collection getListKHPHCT(KHPHChiTietNamVO vo,
                                  String guid) throws Exception,
                                                      TPCPException {
      Collection reval = null;
      try {
          QuanLyKeHoachDao tpcpDAO = new QuanLyKeHoachDao(conn);
          reval = tpcpDAO.getListKHPHCT(vo, guid);
      } catch (Exception ex) {
          throw ex;
      }
      return reval;
  }

  public long deleteTTDT(QuanLyKeHoachVo vo) throws Exception,
                                                       TPCPException {
      long id = 0;
      long idChiTiet = 0;
      try {
          QuanLyKeHoachDao ttindthauDAO = new QuanLyKeHoachDao(conn);
          idChiTiet = ttindthauDAO.deleteChiTiet(vo);
          id = ttindthauDAO.delete(vo);
          conn.commit();
      } catch (Exception ex) {
          conn.rollback();
          throw new TPCPException().createException("TPCP-0001",
                                                    "Không thể cập nhật Cơ sở dữ liệu");
      }
      return id;
  }
  
  public long UpdateNgayHieuLuc_KeHoachCu(String sGuid, String sNgayHetHan)  throws Exception,
                                                     TPCPException {
    long id = 0;
    try {
        QuanLyKeHoachDao ttindthauDAO = new QuanLyKeHoachDao(conn);
        id = ttindthauDAO.UpdateNgayHieuLuc_KeHoachCu(sGuid,sNgayHetHan);
        conn.commit();
    } catch (Exception ex) {
        conn.rollback();
        throw new TPCPException().createException("TPCP-0001",
                                                  "Không thể cập nhật Cơ sở dữ liệu");
    }
    return id;
  }

  public ResultSet getKHQuyRS(String id) throws Exception {
    ResultSet reval = null;
    try {
        QuanLyKeHoachDao dao = new QuanLyKeHoachDao(conn);
        reval = dao.getKHQuyRS(id);
    } catch (Exception ex) {
        throw ex;
    }
    return reval;
  }
  public ResultSet getKHQuyRSUSD(String id) throws Exception {
    ResultSet reval = null;
    try {
        QuanLyKeHoachDao dao = new QuanLyKeHoachDao(conn);
        reval = dao.getKHQuyRSUSD(id);
    } catch (Exception ex) {
        throw ex;
    }
    return reval;
  }     
}
