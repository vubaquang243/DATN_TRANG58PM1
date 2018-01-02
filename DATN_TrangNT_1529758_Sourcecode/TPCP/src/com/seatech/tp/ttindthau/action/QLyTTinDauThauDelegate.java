package com.seatech.tp.ttindthau.action;

import com.seatech.framework.datamanager.BaseDelegate;

import com.seatech.framework.exception.TPCPException;

import com.seatech.tp.qlytp.dao.QuanLyTPCPDAO;
import com.seatech.tp.ttindthau.dao.QLyTTinDauThauDAO;
import com.seatech.tp.ttindthau.vo.ThongBaoVO;
import com.seatech.tp.ttindthau.vo.ThongTinDauThauVO;

import java.sql.Connection;

import java.util.Collection;
import java.util.Map;

public class QLyTTinDauThauDelegate extends BaseDelegate {
    public QLyTTinDauThauDelegate(Connection conn) {
        super();
        this.conn = conn;
    }

    public long update(ThongTinDauThauVO vo) throws Exception, TPCPException {
        long id = 0;
        try {
            QLyTTinDauThauDAO ttindthauDAO = new QLyTTinDauThauDAO(conn);
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
    public ThongBaoVO getObject(Map<String,Object> mapp) throws Exception{
        ThongBaoVO vo= null;
        try{
          QLyTTinDauThauDAO ttindthauDAO = new QLyTTinDauThauDAO(conn);
          vo = ttindthauDAO.getTbaoObject(mapp);
          
        }catch(Exception ex){
         throw ex; 
        }
        return vo;
    }

    public Collection getListTTDTPaging(ThongTinDauThauVO vo, Integer page,
                                        Integer count,
                                        Integer[] totalCount) throws Exception {
        Collection reval = null;

        try {
            QLyTTinDauThauDAO ttindthauDAO = new QLyTTinDauThauDAO(conn);
            reval = ttindthauDAO.getListTTDTPaging(vo, page, count, totalCount);

        } catch (Exception ex) {
            throw ex;
        }
        return reval;
    }
  public Collection getListTTDT_PDPaging(ThongTinDauThauVO vo, Integer page,
                                      Integer count,
                                      Integer[] totalCount) throws Exception {
      Collection reval = null;

      try {
          QLyTTinDauThauDAO ttindthauDAO = new QLyTTinDauThauDAO(conn);
          reval = ttindthauDAO.getListTTDT_PDPaging(vo, page, count, totalCount);

      } catch (Exception ex) {
          throw ex;
      }
      return reval;
  }

    public ThongTinDauThauVO getTTDTObject(Map<String, Object> map) throws Exception,
                                                                    TPCPException {
        ThongTinDauThauVO reval = null;
        try {
            QLyTTinDauThauDAO ttindthauDAO = new QLyTTinDauThauDAO(conn);
            reval = ttindthauDAO.getTTDTObject(map);
        } catch (Exception ex) {
            throw ex;
        }
        return reval;
    }
    
  public ThongTinDauThauVO getTTDTObject_check(Map<String, Object> map,String... kqph) throws Exception,
                                                                  TPCPException {
      ThongTinDauThauVO reval = null;
      try {
          QLyTTinDauThauDAO ttindthauDAO = new QLyTTinDauThauDAO(conn);
          reval = ttindthauDAO.getTTDTObject_check(map,kqph);
      } catch (Exception ex) {
          throw ex;
      }
      return reval;
  }
    
  public ThongTinDauThauVO getTTDTObject2(Map<String, Object> map) throws Exception,
                                                                  TPCPException {
      ThongTinDauThauVO reval = null;
      try {
          QLyTTinDauThauDAO ttindthauDAO = new QLyTTinDauThauDAO(conn);
          reval = ttindthauDAO.getTTDTObject2(map);
      } catch (Exception ex) {
          throw ex;
      }
      return reval;
  }
  public ThongTinDauThauVO getTong_kl_da_goi_thau(Map<String, Object> map) throws Exception,
                                                                  TPCPException {
      ThongTinDauThauVO reval = null;
      try {
          QLyTTinDauThauDAO ttindthauDAO = new QLyTTinDauThauDAO(conn);
          reval = ttindthauDAO.getTong_kl_da_goi_thau(map);
      } catch (Exception ex) {
          throw ex;
      }
      return reval;
  }
  public ThongTinDauThauVO getTTDTDotDauFromDotBS(String ma_tpcp,String dot_bo_sung, String phuong_thuc_ph) throws Exception,
                                                                  TPCPException {
      ThongTinDauThauVO reval = null;
      try {
          QLyTTinDauThauDAO ttindthauDAO = new QLyTTinDauThauDAO(conn);
          reval = ttindthauDAO.getTTDTDotDauFromDotBS(ma_tpcp,dot_bo_sung, phuong_thuc_ph);
      } catch (Exception ex) {
          throw ex;
      }
      return reval;
  }

  public Collection getTTDTObject_DotDT() throws Exception {
      Collection reval = null;

      try {
        QLyTTinDauThauDAO ttindthauDAO = new QLyTTinDauThauDAO(conn);
        reval = ttindthauDAO.getTTDTObject_DotDT();
      } catch (Exception ex) {
          throw ex;
      }
      return reval;
  }
  
  public Collection getTTDTObject_DotDuThau() throws Exception {
      Collection reval = null;

      try {
        QLyTTinDauThauDAO ttindthauDAO = new QLyTTinDauThauDAO(conn);
        reval = ttindthauDAO.getTTDTObject_DotDuThau();
      } catch (Exception ex) {
          throw ex;
      }
      return reval;
  }
  
    public long deleteTTDT(ThongTinDauThauVO vo) throws Exception, TPCPException {
        long id = 0;
        try {
            QLyTTinDauThauDAO ttindthauDAO = new QLyTTinDauThauDAO(conn);
            id = ttindthauDAO.delete(vo);
            conn.commit();
        } catch (Exception ex) {
            conn.rollback();
            throw new TPCPException().createException("TPCP-0001",
                                                      "Không thể cập nhật Cơ sở dữ liệu");
        }
        return id;
    }
    public Collection getListTTDT(Map<String, Object> map) throws Exception,
                                                                    TPCPException {
        Collection reval = null;
        try {
            QLyTTinDauThauDAO ttindthauDAO = new QLyTTinDauThauDAO(conn);
            reval = ttindthauDAO.getListTTDT(map);
        } catch (Exception ex) {
            throw ex;
        }
        return reval;
    }
}
