package com.seatech.tp.quanlykehoachquy.dao;

import com.seatech.framework.datamanager.AppDAO;

import com.seatech.framework.datamanager.DateParameter;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.exception.DAOException;
import com.seatech.framework.exception.TPCPException;
import com.seatech.framework.utils.StringUtil;
import com.seatech.tp.kqduthau.vo.QLyKQDuThauCTietVO;
import com.seatech.tp.qlykehoach.vo.QuanLyKeHoachVo;
import com.seatech.tp.qlytp.vo.PhuongThucPhatHanhVO;
import com.seatech.tp.quanlykehoachquy.vo.KHPHChiTietVO;
import com.seatech.tp.quanlykehoachquy.vo.QuanLyKeHoachQuyVO;


import java.sql.Connection;

import java.sql.ResultSet;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

public class QuanLyKeHoachQuyDao extends AppDAO {
    private Connection conn = null;
    private String CLASS_NAME_DAO =
        "com.seatech.tp.quanlykehoachquy.dao.QuanLyKeHoachQuyDao";
    private String CLASS_NAME_VO =
        "com.seatech.tp.quanlykehoachquy.vo.QuanLyKeHoachQuyVO";
    private String CLASS_KHPH_VO =
        "com.seatech.tp.quanlykehoachquy.vo.KHPHChiTietVO";
    private static String STRING_EMPTY = "";

    public QuanLyKeHoachQuyDao(Connection conn) {
        super();
        this.conn = conn;
    }

    public Long insert(QuanLyKeHoachQuyVO vo) throws Exception {
        Vector v_param = new Vector();
        Vector v_param2 = null;
        //L?y ID t? seq
        Long lID = new Long("0");
        StringBuffer strSQL = new StringBuffer();
        String sql = "";
        if (vo.getGuid() != null && !vo.getGuid().equals("")) {
            sql = generateSQLUpdate(vo, v_param, "TP_KH_PH", conn);
            strSQL.append(sql);
            strSQL.append("WHERE GUID = ?");
            v_param.add(new Parameter(vo.getGuid(), true));
            lID = new Long(vo.getGuid());
        } else {
            lID = getSeqNextValue("TP_MA_TPCP_SEQ", conn);
            vo.setGuid(String.valueOf(lID));
            sql = generateSQLInsert(vo, v_param, "TP_KH_PH", conn);
            strSQL.append(sql);
        }
        if (executeStatement(strSQL.toString(), v_param, conn) == 1) {
            if (vo.getListKHPH_CTiet() != null &&
                !vo.getListKHPH_CTiet().isEmpty()) {
                //delete first
                executeStatement("DELETE TP_KH_PH_CTIET WHERE KH_ID = '" + lID +
                                 "'", v_param2, conn);
                KHPHChiTietVO ctietVO = null;
                Iterator ito = vo.getListKHPH_CTiet().iterator();

                while (ito.hasNext()) {
                    v_param2 = new Vector();
                    long guidCT = getSeqNextValue("TP_KH_PH_CTIET_SEQ", conn);
                    ctietVO = (KHPHChiTietVO)ito.next();
                    ctietVO.setGuid(String.valueOf(guidCT));
                    ctietVO.setKh_id(String.valueOf(lID));
                    sql =
generateSQLInsert(ctietVO, v_param2, "TP_KH_PH_CTIET", conn);
                    executeStatement(sql, v_param2, conn);
                }
            }
            return lID;
        } else
            return new Long("0");
    }

    public Long insertKHCT(KHPHChiTietVO vo) throws Exception {
        Vector v_param = new Vector();
        //L?y ID t? seq
        Long lID = new Long("0");
        Long khID = new Long("0");
        StringBuffer strSQL = new StringBuffer();
        String sql = "";
        if (vo.getGuid() != null && !vo.getGuid().equals("")) {
            sql = generateSQLUpdate(vo, v_param, "TP_KH_PH_CTIET", conn);
            strSQL.append(sql);
            strSQL.append("WHERE GUID = ?");
            v_param.add(new Parameter(vo.getGuid(), true));
            lID = new Long(vo.getGuid());
        } else {
            lID = getSeqNextValue("TP_KH_PH_CTIET_SEQ", conn);
            khID = getSeqNextValue("TP_MA_TPCP_SEQ", conn);
            vo.setKh_id(String.valueOf(khID));
            vo.setGuid(String.valueOf(lID));
            sql = generateSQLInsert(vo, v_param, "TP_KH_PH_CTIET", conn);
            strSQL.append(sql);
        }
        if (executeStatement(strSQL.toString(), v_param, conn) == 1)
            return lID;
        else
            return new Long("0");
    }

    public Collection getListQLKHPaging(QuanLyKeHoachQuyVO vo, Integer page,
                                        Integer count,
                                        Integer[] totalCount) throws Exception {
        Collection reval = null;
        String whereClause = "";
        Vector vParam = new Vector();
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append("SELECT a.GUID,a.THOI_GIAN_PH,a.NAM_PH, a.LOAI_KH,a.TONG_KLPH, a.LOAI_TIEN ,SO_TBAO_KH_CU ," +
                          "to_char(a.NGAY_HIEU_LUC,'dd/MM/yyyy') NGAY_HIEU_LUC,to_char(a.NGAY_HET_HAN,'dd/MM/yyyy') NGAY_HET_HAN,  " +
                          " a.TRANG_THAI, b.Name as ten_trang_thai, c.ten_nsd " +
                          "  FROM TP_KH_PH a inner join TP_DM_trang_thai b on a.trang_thai = b.id_status inner join TP_NSD c on  a.NGUOI_TAO = c.id ");
            whereClause += " and  a.loai_kh  = 'QU' ";
            if (vo.getThoi_gian_ph() != null &&
                !"".equals(vo.getThoi_gian_ph())) {
                whereClause += " and  a.THOI_GIAN_PH  = ?";
                vParam.add(new Parameter(vo.getThoi_gian_ph(), true));
            }
            if (vo.getNam_ph() != null && !"".equals(vo.getNam_ph())) {
                whereClause += " and  a.NAM_PH  = ?";
                vParam.add(new Parameter(vo.getNam_ph(), true));
            }
            if (vo.getTrang_thai() != null && !"".equals(vo.getTrang_thai())) {
                whereClause += " and  a.TRANG_THAI = ?";
                vParam.add(new Parameter(vo.getTrang_thai(), true));
            }
            if(vo.getLoai_tien() != null && !"".equals(vo.getLoai_tien())){
              whereClause+= " and a.LOAI_TIEN = ? ";
              vParam.add(new Parameter(vo.getLoai_tien(),true));
            }
            if (whereClause != null && !whereClause.equalsIgnoreCase("")) {
                strSQL.append(" WHERE 1=1 " + whereClause);
            }
            strSQL.append(" ORDER BY a.NAM_PH  DESC , a.THOI_GIAN_PH DESC" );
            reval =
                    executeSelectWithPaging(conn, strSQL.toString(), vParam, CLASS_NAME_VO,
                                            page, count, totalCount);
        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO + ".getListQLKHPaging(): " +
                                   ex.getMessage(), ex);
        }
        return reval;
    }
    
    
    public Collection getListQLKHPagingCheck(QuanLyKeHoachQuyVO vo, Integer page,
                                      Integer count,
                                      Integer[] totalCount) throws Exception {
      Collection reval = null;
      String whereClause = " and loai_kh='QU' ";
      Vector vParam = new Vector();
      StringBuffer strSQL = new StringBuffer();
      try {
          strSQL.append("SELECT GUID,THOI_GIAN_PH,NAM_PH,  LOAI_TIEN " +
                        "  FROM TP_KH_PH  ");
          
          if (vo.getThoi_gian_ph() != null &&
              !"".equals(vo.getThoi_gian_ph())) {
              whereClause += " and  THOI_GIAN_PH  = ?";
              vParam.add(new Parameter(vo.getThoi_gian_ph(), true));
          }
          if (vo.getNam_ph() != null && !"".equals(vo.getNam_ph())) {
              whereClause += " and  NAM_PH  = ?";
              vParam.add(new Parameter(vo.getNam_ph(), true));
          }

          if(vo.getLoai_tien() != null && !"".equals(vo.getLoai_tien())){
            whereClause+= " and LOAI_TIEN = ? ";
            vParam.add(new Parameter(vo.getLoai_tien(),true));
          }
          if (whereClause != null && !whereClause.equalsIgnoreCase("")) {
              strSQL.append(" WHERE 1=1 " + whereClause);
          }
          strSQL.append(" GROUP BY GUID ,THOI_GIAN_PH,NAM_PH,LOAI_TIEN" );
          reval =
                  executeSelectWithPaging(conn, strSQL.toString(), vParam, CLASS_NAME_VO,
                                          page, count, totalCount);
      } catch (Exception ex) {
          throw new DAOException(CLASS_NAME_DAO + ".getListQLKHPaging(): " +
                                 ex.getMessage(), ex);
      }
      return reval;
  }
  
  
    public Collection getListPD(QuanLyKeHoachQuyVO vo, Integer page,
                                Integer count,
                                Integer[] totalCount) throws Exception {
        Collection reval = null;
        String whereClause = "and a.TRANG_THAI != '00' and a.loai_kh= 'QU' ";
        Vector vParam = new Vector();
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append(" SELECT a.GUID,a.THOI_GIAN_PH,a.NAM_PH, a.LOAI_KH,a.TONG_KLPH,a.LOAI_TIEN ,SO_TBAO_KH_CU ," +
                          " to_char(a.NGAY_HIEU_LUC,'dd/MM/yyyy') NGAY_HIEU_LUC,to_char(a.NGAY_HET_HAN,'dd/MM/yyyy') NGAY_HET_HAN,  " +
                          " a.TRANG_THAI, b.Name as ten_trang_thai, c.ten_nsd " +
                          " FROM TP_KH_PH a " +
                          " inner join TP_DM_trang_thai b on a.trang_thai = b.id_status " +
                          " inner join TP_NSD c on  a.NGUOI_TAO = c.id ");
            if (vo.getThoi_gian_ph() != null &&
                !"".equals(vo.getThoi_gian_ph())) {
                whereClause += " and  a.THOI_GIAN_PH  = ?";
                vParam.add(new Parameter(vo.getThoi_gian_ph(), true));
            }
            if (vo.getNam_ph() != null && !"".equals(vo.getNam_ph())) {
                whereClause += " and  a.NAM_PH  = ?";
                vParam.add(new Parameter(vo.getNam_ph(), true));
            }
            if (vo.getTrang_thai() != null && !"".equals(vo.getTrang_thai())) {
                whereClause += " and  a.TRANG_THAI = ?";
                vParam.add(new Parameter(vo.getTrang_thai(), true));
            }else {
              whereClause += " and  a.TRANG_THAI != '06'";
            }
          
            if (whereClause != null && !whereClause.equalsIgnoreCase("")) {
                strSQL.append(" WHERE 1=1 " + whereClause);
            }
            strSQL.append(" ORDER BY a.NGAY_TAO DESC");
            reval =
                    executeSelectWithPaging(conn, strSQL.toString(), vParam, CLASS_NAME_VO,
                                            page, count, totalCount);
        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO + ".getListQLKHPaging(): " +
                                   ex.getMessage(), ex);
        }
        return reval;
    }

    public QuanLyKeHoachQuyVO getQLKHObject(Map<String, Object> map) throws Exception,
                                                                            TPCPException {
        StringBuffer strSQL = new StringBuffer();
        StringBuffer strSQL2 = new StringBuffer();
        Vector vParam = new Vector();
        try {
            strSQL.append("SELECT GUID,NAM_PH, LOAI_KH,  THOI_GIAN_PH,  TONG_KLPH,  LOAI_TIEN,SO_TBAO_KH_CU ," +
                          "  to_char(NGAY_HIEU_LUC,'dd/MM/yyyy') NGAY_HIEU_LUC,  to_char(NGAY_HET_HAN,'dd/MM/yyyy') NGAY_HET_HAN,  " +
                          "  KH_GOC,  SO_TBAO_KH,to_char(NGAY_TBAO_KH,'dd/MM/yyyy') NGAY_TBAO_KH,  " +
                          "  MO_TA,  TRANG_THAI,  NGUOI_TAO, to_char(NGAY_TAO,'dd/MM/yyyy') NGAY_TAO,  NGUOI_SUA_CUOI,to_char(NGAY_SUA_CUOI,'dd/MM/yyyy')  NGAY_SUA_CUOI, " +
                          "  NGUOI_DUYET, to_char(NGAY_DUYET,'dd/MM/yyyy') NGAY_DUYET,  LY_DO_TU_CHOI" +
                          "  FROM TP_KH_PH");
            Set<String> keySet = map.keySet();
            Iterator<String> keySetIterator = keySet.iterator();
            while (keySetIterator.hasNext()) {
                String key = keySetIterator.next();
                String value = map.get(key).toString();
                if (value != null && !STRING_EMPTY.equals(value)) {
                    strSQL2.append("and " + key + " = ? ");
                    vParam.add(new Parameter(value, true));
                }
            }
            strSQL2.append(" and loai_kh='QU' ");
            if (strSQL2.toString().length() > 0) {
                strSQL.append(" WHERE 1 = 1 " + strSQL2.toString());
            }
            return (QuanLyKeHoachQuyVO)findByPK(strSQL.toString(), vParam,
                                                CLASS_NAME_VO, conn);
        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO + ".getQLKHObject(): " +
                                   ex.getMessage(), ex);
        }
    }
  
  
 
    public QuanLyKeHoachQuyVO getQLKHCheckObject(Map<String, Object> map) throws Exception , TPCPException{
          StringBuffer strSQL = new StringBuffer();
          StringBuffer strSQL1 = new StringBuffer();
          Vector vParam = new Vector();
          try{
            strSQL.append("SELECT GUID, NAM_PH,  THOI_GIAN_PH,  TONG_KLPH,  LOAI_TIEN,SO_TBAO_KH_CU ," +
                          "  to_char(NGAY_HIEU_LUC,'dd/MM/yyyy') NGAY_HIEU_LUC,  to_char(NGAY_HET_HAN,'dd/MM/yyyy') NGAY_HET_HAN,  " +
                          "  KH_GOC,  SO_TBAO_KH,to_char(NGAY_TBAO_KH,'dd/MM/yyyy') NGAY_TBAO_KH,  " +
                          "  MO_TA,  TRANG_THAI,  NGUOI_TAO, to_char(NGAY_TAO,'dd/MM/yyyy') NGAY_TAO,  NGUOI_SUA_CUOI,to_char(NGAY_SUA_CUOI,'dd/MM/yyyy')  NGAY_SUA_CUOI, " +
                           "  NGUOI_DUYET, to_char(NGAY_DUYET,'dd/MM/yyyy') NGAY_DUYET,  LY_DO_TU_CHOI" +
                          "  FROM TP_KH_PH ");
              Set<String> keySet =map.keySet();
              Iterator<String> keySetItr= keySet.iterator();
              while(keySetItr.hasNext()){
                String key = keySetItr.next();
                  String value= map.get(key).toString();
                  if(value != null && !STRING_EMPTY.equals(value) && key != "KH_GOC"){
                      strSQL1.append(" and " + key +" = ? ");   
                      vParam.add(new Parameter(value,true));
                  }else{
                      strSQL1.append(" and KH_GOC is NULL ");
                      }
                  
              }
              strSQL1.append(" and loai_kh='QU' ");
              if (strSQL1.toString().length() > 0) {
                  strSQL.append(" WHERE 1 = 1 " + strSQL1.toString());
              }
              return (QuanLyKeHoachQuyVO)findByPK(strSQL.toString(), vParam,
                                                  CLASS_NAME_VO, conn);
            }catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO + ".getQLKHObject(): " +
                                   ex.getMessage(), ex);
            }
    }
    public QuanLyKeHoachQuyVO getTpKeHoachCheckObject(String sNamph, String sThoigianph) throws Exception,
                                                              TPCPException {
      StringBuffer strSQL = new StringBuffer();
      try {
          strSQL.append("SELECT GUID, NAM_PH,  THOI_GIAN_PH,  TONG_KLPH,  LOAI_TIEN,SO_TBAO_KH_CU ," +
                        "  to_char(NGAY_HIEU_LUC,'dd/MM/yyyy') NGAY_HIEU_LUC,  to_char(NGAY_HET_HAN,'dd/MM/yyyy') NGAY_HET_HAN,  " +
                        "  KH_GOC,  SO_TBAO_KH,to_char(NGAY_TBAO_KH,'dd/MM/yyyy') NGAY_TBAO_KH,  " +
                        "  MO_TA,  TRANG_THAI,  NGUOI_TAO, to_char(NGAY_TAO,'dd/MM/yyyy') NGAY_TAO,  NGUOI_SUA_CUOI,to_char(NGAY_SUA_CUOI,'dd/MM/yyyy')  NGAY_SUA_CUOI, " +
                        "  NGUOI_DUYET, to_char(NGAY_DUYET,'dd/MM/yyyy') NGAY_DUYET,  LY_DO_TU_CHOI" +
                        "  FROM TP_KH_PH WHERE NAM_PH = '" + sNamph + "'" +  " and THOI_GIAN_PH = '" + sThoigianph + "' and loai_kh='QU' ");
          
          return (QuanLyKeHoachQuyVO)findByPK(strSQL.toString(), null,
                                              CLASS_NAME_VO, conn);
      } catch (Exception ex) {
          throw new DAOException(CLASS_NAME_DAO + ".getQLKHObject(): " +
                                 ex.getMessage(), ex);
      }
  } 
    public QuanLyKeHoachQuyVO getQLKHObject(String guid) throws Exception,
                                                                TPCPException {
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append("SELECT GUID, NAM_PH,  LOAI_KH,  THOI_GIAN_PH,  TONG_KLPH,  LOAI_TIEN,SO_TBAO_KH_CU ," +
                          "  to_char(NGAY_HIEU_LUC,'dd/MM/yyyy') NGAY_HIEU_LUC,  to_char(NGAY_HET_HAN,'dd/MM/yyyy') NGAY_HET_HAN,  " +
                          "  KH_GOC,  SO_TBAO_KH,to_char(NGAY_TBAO_KH,'dd/MM/yyyy') NGAY_TBAO_KH,  " +
                          "  MO_TA,  TRANG_THAI,  NGUOI_TAO, to_char(NGAY_TAO,'dd/MM/yyyy') NGAY_TAO,  NGUOI_SUA_CUOI,to_char(NGAY_SUA_CUOI,'dd/MM/yyyy')  NGAY_SUA_CUOI, " +
                          "  NGUOI_DUYET, to_char(NGAY_DUYET,'dd/MM/yyyy') NGAY_DUYET,  LY_DO_TU_CHOI" +
                          "  FROM TP_KH_PH");
            if (guid != null && !STRING_EMPTY.equals(guid)) {
                strSQL.append(" WHERE GUID = '" + guid + "'");
            }
            return (QuanLyKeHoachQuyVO)findByPK(strSQL.toString(), null,
                                                CLASS_NAME_VO, conn);
        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO + ".getQLKHObject(): " +
                                   ex.getMessage(), ex);
        }
    }

    public Collection getListKHPHCT(KHPHChiTietVO vo,
                                    String guid) throws Exception,
                                                        TPCPException {
        Collection reval = null;
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append("SELECT GUID,  KH_ID,  KY_HAN,  KLPH" +
                          "  FROM TP_KH_PH_CTIET WHERE KH_ID = " + guid + "");
            //            strSQL.append(" ORDER BY a.KH_ID DESC");
            reval =
                    executeSelectStatement(strSQL.toString(), null, CLASS_KHPH_VO);
        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO + ".getListKHPHCT(): " +
                                   ex.getMessage(), ex);
        }
        return reval;
    }

    public Long deleteChiTiet(QuanLyKeHoachQuyVO vo) throws Exception {
        Vector v_param = new Vector();
        StringBuffer strSQL = new StringBuffer();
        strSQL.append(" DELETE TP_KH_PH_CTIET WHERE KH_ID = ?");
        int nExc = 0;
        if (vo.getGuid() != null) {
            v_param.add(new Parameter(vo.getGuid(), true));
            nExc = executeStatement(strSQL.toString(), v_param, conn);
            if (nExc == 1)
                return Long.parseLong(vo.getGuid());
        }
        return new Long("0");
    }
    
  public Long delete(QuanLyKeHoachQuyVO vo) throws Exception {
      Vector v_param = new Vector();
      StringBuffer strSQL = new StringBuffer();
      strSQL.append(" DELETE TP_KH_PH WHERE GUID = ?");
      int nExc = 0;
      if (vo.getGuid() != null) {
          v_param.add(new Parameter(vo.getGuid(), true));
          nExc = executeStatement(strSQL.toString(), v_param, conn);
          if (nExc == 1)
              return Long.parseLong(vo.getGuid());
      }
      return new Long("0");
  }
  
  public Long UpdateNgayHieuLuc_KeHoachCu(String sGuid, String sNgayHetHan) throws Exception {
      Vector v_param = new Vector();
      StringBuffer strSQL = new StringBuffer();
      strSQL.append(" UPDATE TP_KH_PH SET NGAY_HET_HAN =  to_date('" + sNgayHetHan + "', 'DD/MM/YYYY'),TRANG_THAI='06' WHERE GUID = ?");
      int nExc = 0;
      if (!sGuid.toString().trim().equals("")) {
          v_param.add(new Parameter(sGuid, true));
          nExc = executeStatement(strSQL.toString(), v_param, conn);
          if (nExc == 1)
              return Long.parseLong(sGuid);
      }
      return new Long("0");
  }

    public ResultSet getKHQuyRS(String id) throws DAOException {
      ResultSet reval = null;
      String whereClause = "";
      Vector vParam = new Vector();
      StringBuffer strSQL = new StringBuffer();
      try {
          strSQL.append("SELECT LOWER(b.KY_HAN || ' ' || b.LOAI_KY_HAN) KY_HAN , (KLPH/1000000000) as KLPH \n" + 
                        " FROM TP_KH_PH_CTIET a \n" + 
                        " inner join TP_DM_KY_HAN b on  a.KY_HAN = b.GUID \n" + 
                        " WHERE a.KLPH != '0'  and a.KH_ID =" + id +" ORDER BY b.KY_HAN ASC");
          reval = executeSelectStatement(strSQL.toString(), vParam, conn);

      } catch (Exception ex) {
          throw new DAOException(CLASS_NAME_DAO +
                                 ".getKHQuyRS(): " +
                                 ex.getMessage(), ex);
      }
      return reval;
    }
    public ResultSet getKHQuyRSUSD(String id) throws DAOException {
      ResultSet reval = null;
      String whereClause = "";
      Vector vParam = new Vector();
      StringBuffer strSQL = new StringBuffer();
      try {
          strSQL.append("SELECT LOWER(b.KY_HAN || ' ' || b.LOAI_KY_HAN) KY_HAN , (KLPH/1000000) as KLPH \n" + 
                        " FROM TP_KH_PH_CTIET a \n" + 
                        " inner join TP_DM_KY_HAN b on  a.KY_HAN = b.GUID \n" + 
                        " WHERE a.KLPH != '0'  and a.KH_ID =" + id +" ORDER BY b.KY_HAN ASC");
          reval = executeSelectStatement(strSQL.toString(), vParam, conn);
  
      } catch (Exception ex) {
          throw new DAOException(CLASS_NAME_DAO +
                                 ".getKHQuyRS(): " +
                                 ex.getMessage(), ex);
      }
      return reval;
    }
}
