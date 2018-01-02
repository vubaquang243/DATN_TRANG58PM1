package com.seatech.tp.quanlykehoachquy.action;

import com.seatech.framework.datamanager.BaseDelegate;

import com.seatech.framework.exception.TPCPException;
import com.seatech.tp.quanlykehoachquy.dao.QuanLyKeHoachQuyDao;
import com.seatech.tp.quanlykehoachquy.vo.KHPHChiTietVO;
import com.seatech.tp.quanlykehoachquy.vo.QuanLyKeHoachQuyVO;

import java.sql.Connection;

import java.sql.ResultSet;

import java.util.Collection;
import java.util.Map;

public class QuanLyKeHoachQuyDelegate extends BaseDelegate {
    public QuanLyKeHoachQuyDelegate(Connection conn) {
        super();
        this.conn = conn;
    }

    public long update(QuanLyKeHoachQuyVO vo) throws Exception, TPCPException {
        long id = 0;
        try {
            QuanLyKeHoachQuyDao ttindthauDAO = new QuanLyKeHoachQuyDao(conn);
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

    public long updateKHCT(KHPHChiTietVO vo) throws Exception, TPCPException {
        long id = 0;
        try {
            QuanLyKeHoachQuyDao ttindthauDAO = new QuanLyKeHoachQuyDao(conn);
            id = ttindthauDAO.insertKHCT(vo);
            conn.commit();
        } catch (Exception ex) {
            conn.rollback();
            throw new TPCPException().createException("TPCP-0001",
                                                      "Không thể cập nhật Cơ sở dữ liệu");
        }
        return id;
    }

    public Collection getListQLKHPaging(QuanLyKeHoachQuyVO vo, Integer page,
                                        Integer count,
                                        Integer[] totalCount) throws Exception {
        Collection reval = null;

        try {
            QuanLyKeHoachQuyDao ttindthauDAO = new QuanLyKeHoachQuyDao(conn);
            reval =
                    ttindthauDAO.getListQLKHPaging(vo, page, count, totalCount);

        } catch (Exception ex) {
            throw ex;
        }
        return reval;
    }
    
    
    
    public Collection getListQLKHPagingCheck(QuanLyKeHoachQuyVO vo, Integer page,
                                      Integer count,
                                      Integer[] totalCount) throws Exception {
      Collection reval = null;

      try {
          QuanLyKeHoachQuyDao ttindthauDAO = new QuanLyKeHoachQuyDao(conn);
          reval =
                  ttindthauDAO.getListQLKHPagingCheck(vo, page, count, totalCount);

      } catch (Exception ex) {
          throw ex;
      }
      return reval;
  }
    public Collection getListPD(QuanLyKeHoachQuyVO vo, Integer page,
                                Integer count,
                                Integer[] totalCount) throws Exception {
        Collection reval = null;

        try {
            QuanLyKeHoachQuyDao ttindthauDAO = new QuanLyKeHoachQuyDao(conn);
            reval = ttindthauDAO.getListPD(vo, page, count, totalCount);

        } catch (Exception ex) {
            throw ex;
        }
        return reval;
    }

    public QuanLyKeHoachQuyVO getQLKHObject(Map<String, Object> map) throws Exception,
                                                                            TPCPException {
        QuanLyKeHoachQuyVO reval = null;
        try {
            QuanLyKeHoachQuyDao ttindthauDAO = new QuanLyKeHoachQuyDao(conn);
            reval = ttindthauDAO.getQLKHObject(map);
        } catch (Exception ex) {
            throw ex;
        }
        return reval;
    }

    public QuanLyKeHoachQuyVO getTpKeHoachObject(String guid) throws Exception,
                                                                     TPCPException {
        QuanLyKeHoachQuyVO reval = null;
        try {
            QuanLyKeHoachQuyDao tpcpDAO = new QuanLyKeHoachQuyDao(conn);
            reval = tpcpDAO.getQLKHObject(guid);
        } catch (Exception ex) {
            throw ex;
        }
        return reval;
    }
    
    // by manhvv
    public QuanLyKeHoachQuyVO getQLKHCheckObject(Map<String,Object> map) throws Exception ,TPCPException{
      QuanLyKeHoachQuyVO reval= null;
      try{
          QuanLyKeHoachQuyDao tpcpDao= new QuanLyKeHoachQuyDao(conn);
          reval=tpcpDao.getQLKHCheckObject(map);
      }catch(Exception e){
        throw e;
      }
      return reval;
    }
    public QuanLyKeHoachQuyVO getTpKeHoachCheckObject(String sNamph, String sThoigianph) throws Exception,
                                                                   TPCPException {
      QuanLyKeHoachQuyVO reval = null;
      try {
          QuanLyKeHoachQuyDao tpcpDAO = new QuanLyKeHoachQuyDao(conn);
          reval = tpcpDAO.getTpKeHoachCheckObject(sNamph,sThoigianph);
      } catch (Exception ex) {
          throw ex;
      }
      return reval;
   }

    public Collection getListKHPHCT(KHPHChiTietVO vo,
                                    String guid) throws Exception,
                                                        TPCPException {
        Collection reval = null;
        try {
            QuanLyKeHoachQuyDao tpcpDAO = new QuanLyKeHoachQuyDao(conn);
            reval = tpcpDAO.getListKHPHCT(vo, guid);
        } catch (Exception ex) {
            throw ex;
        }
        return reval;
    }

    public long deleteTTDT(QuanLyKeHoachQuyVO vo) throws Exception,
                                                         TPCPException {
        long id = 0;
        long idChiTiet = 0;
        try {
            QuanLyKeHoachQuyDao ttindthauDAO = new QuanLyKeHoachQuyDao(conn);
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
          QuanLyKeHoachQuyDao ttindthauDAO = new QuanLyKeHoachQuyDao(conn);
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
          QuanLyKeHoachQuyDao dao = new QuanLyKeHoachQuyDao(conn);
          reval = dao.getKHQuyRS(id);
      } catch (Exception ex) {
          throw ex;
      }
      return reval;
    }
    public ResultSet getKHQuyRSUSD(String id) throws Exception {
      ResultSet reval = null;
      try {
          QuanLyKeHoachQuyDao dao = new QuanLyKeHoachQuyDao(conn);
          reval = dao.getKHQuyRSUSD(id);
      } catch (Exception ex) {
          throw ex;
      }
      return reval;
    }     
}
