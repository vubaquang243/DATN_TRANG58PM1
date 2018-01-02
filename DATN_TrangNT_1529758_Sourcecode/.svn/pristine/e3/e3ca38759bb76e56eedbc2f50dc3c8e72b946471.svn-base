package com.seatech.tp.bcthanhtoantpcp.dao;

import com.seatech.framework.datamanager.AppDAO;
import com.seatech.framework.exception.DAOException;

import com.seatech.tp.bcthanhtoantpcp.vo.BCThanhToanTpcpVo;

import java.sql.Connection;
import java.sql.ResultSet;

import java.util.Collection;
import java.util.Vector;

public class BCThanhToanTpcpDao extends AppDAO{
    private Connection conn = null;
    private String CLASS_NAME_DAO =
        "com.seatech.tp.bcthanhtoantpcp.dao.BCThanhToanTpcpDao";
    private String CLASS_NAME_VO =
        "com.seatech.tp.bcthanhtoantpcp.vo.BCThanhToanTpcpVo";
    private static String STRING_EMPTY = "";

    public BCThanhToanTpcpDao(Connection conn) {
        super();
        this.conn = conn;
    }
    
  public Collection getBCThanhToanTpcpPaging(BCThanhToanTpcpVo vo, Integer page,
                                           Integer count,
                                           Integer[] totalCount) throws Exception {
      Collection reval = null;
      String whereClause = "";
      Vector vParam = new Vector();
      StringBuffer strSQL = new StringBuffer();
      try {
          strSQL.append("SELECT  STT, CHI_TIEU, LAI_SUAT, KHOI_LUONG,  LUY_KE, TONG_SO  FROM TP_BCTHANHTOANTPCP_VIEW");
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
                                 ".getListBCThanhToanPaging(): " +
                                 ex.getMessage(), ex);
      }
      return reval;
  }


  public ResultSet getBCThanhToanTpcpList(BCThanhToanTpcpVo vo) throws Exception {
      ResultSet reval = null;
      String whereClause = "";
      Vector vParam = new Vector();
      StringBuffer strSQL = new StringBuffer();
      try {
         strSQL.append("SELECT  STT, CHI_TIEU, LAI_SUAT, KHOI_LUONG,  LUY_KE, TONG_SO  FROM TP_BCTHANHTOANTPCP_VIEW");
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
          throw new DAOException(CLASS_NAME_DAO + ".getListBCThanhToan(): " +
                                 ex.getMessage(), ex);
      }

      return reval;
  }
}
