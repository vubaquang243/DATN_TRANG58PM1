package com.seatech.tp.bcsolieuhuydongvon.dao;

import com.seatech.framework.datamanager.AppDAO;

import com.seatech.framework.datamanager.DateParameter;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.exception.DAOException;
import com.seatech.framework.utils.StringUtil;
import com.seatech.tp.bcsolieuhuydongvon.vo.BCSLHuyDongVonVo;
import com.seatech.tp.sotonghoptpcp.dao.SoTongHopTpcpDao;
import com.seatech.tp.sotonghoptpcp.vo.SoTongHopTpcpVO;

import java.sql.Connection;
import java.sql.ResultSet;

import java.util.Collection;
import java.util.Vector;

public class BCSLHuyDongVonDAO  extends AppDAO{ 
    
  private Connection conn = null;
  private String CLASS_NAME_DAO =
      "com.seatech.tp.bcsolieuhuydongvon.dao.BCSLHuyDongVonDAO";
  private String CLASS_NAME_VO =
      "com.seatech.tp.bcsolieuhuydongvon.vo.BCSLHuyDongVonVo";
  private static String STRING_EMPTY = "";
    
  public BCSLHuyDongVonDAO(Connection conn) {
      super();
      this.conn = conn;
  }

  public Collection getBCSLHuyDongVonPaging(BCSLHuyDongVonVo vo, Integer page,
                                           Integer count,
                                           Integer[] totalCount) throws Exception {
      Collection reval = null;
      String whereClause = "";
      Vector vParam = new Vector();
      StringBuffer strSQL = new StringBuffer();
      try {
          strSQL.append("select stt,  chi_tieu, dau_ky,  phat_hanh_trong_ky, tra_goc, tra_lai,\n" + 
          "           tong_cong, cuoi_ky from TP_BCHUYDONGVON_VIEW");
//          if (vo.getNgay_phat_hanh_tu_ngay() != null &&
//              !"".equals(vo.getNgay_phat_hanh_tu_ngay()) &&
//              vo.getNgay_phat_hanh_den_ngay() != null &&
//              !"".equals(vo.getNgay_phat_hanh_den_ngay())) {
//              whereClause +=
//                      " and trunc(NGAY_PH) >= ? and trunc(NGAY_PH)<= ?";
//
//              vParam.add(new DateParameter(StringUtil.StringToDate(vo.getNgay_phat_hanh_tu_ngay(),
//                                                                   "dd/MM/yyyy"),
//                                           true));
//              vParam.add(new DateParameter(StringUtil.StringToDate(vo.getNgay_phat_hanh_den_ngay(),
//                                                                   "dd/MM/yyyy"),
//                                           true));
//          }
//          if (whereClause != null && !whereClause.equalsIgnoreCase("")) {
//              strSQL.append(" WHERE 1=1 " + whereClause);
//          }
          reval =
                  executeSelectWithPaging(conn, strSQL.toString(), vParam, CLASS_NAME_VO,
                                          page, count, totalCount);
      } catch (Exception ex) {
          throw new DAOException(CLASS_NAME_DAO +
                                 ".getListBanLeTwPaging(): " +
                                 ex.getMessage(), ex);
      }
      return reval;
  }


  public ResultSet getBCSLHuyDongVonList(BCSLHuyDongVonVo vo) throws Exception {
      ResultSet reval = null;
      String whereClause = "";
      Vector vParam = new Vector();
      StringBuffer strSQL = new StringBuffer();
      try {
        strSQL.append("select stt,  chi_tieu, dau_ky,  phat_hanh_trong_ky, tra_goc, tra_lai,\n" + 
        "           tong_cong, cuoi_ky from TP_BCHUYDONGVON_VIEW");
//          if (vo.getNgay_phat_hanh_tu_ngay() != null &&
//              !"".equals(vo.getNgay_phat_hanh_tu_ngay()) &&
//              vo.getNgay_phat_hanh_den_ngay() != null &&
//              !"".equals(vo.getNgay_phat_hanh_den_ngay())) {
//              whereClause +=
//                      " and trunc(NGAY_PH) >= ? and trunc(NGAY_PH)<= ?";
//
//              vParam.add(new DateParameter(StringUtil.StringToDate(vo.getNgay_phat_hanh_tu_ngay(),
//                                                                   "dd/MM/yyyy"),
//                                           true));
//              vParam.add(new DateParameter(StringUtil.StringToDate(vo.getNgay_phat_hanh_den_ngay(),
//                                                                   "dd/MM/yyyy"),
//                                           true));
//          }
//          if (whereClause != null && !whereClause.equalsIgnoreCase("")) {
//              strSQL.append(" WHERE 1=1 " + whereClause);
//          }
          reval = executeSelectStatement(strSQL.toString(), vParam, conn);

      } catch (Exception ex) {
          throw new DAOException(CLASS_NAME_DAO + ".getUserList(): " +
                                 ex.getMessage(), ex);
      }

      return reval;
  }
    
    
}



