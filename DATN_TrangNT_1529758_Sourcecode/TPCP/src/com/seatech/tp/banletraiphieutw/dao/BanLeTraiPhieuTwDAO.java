package com.seatech.tp.banletraiphieutw.dao;

import com.seatech.framework.datamanager.AppDAO;
import com.seatech.framework.datamanager.DateParameter;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.exception.DAOException;
import com.seatech.framework.exception.TPCPException;
import com.seatech.framework.utils.StringUtil;
import com.seatech.tp.banletraiphieutw.vo.BanLeTraiPhieuTwChiTietVO;
import com.seatech.tp.banletraiphieutw.vo.BanLeTraiPhieuTwVO;

import com.seatech.tp.kqduthau.vo.QLyKQDuThauCTietVO;

import java.sql.Connection;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

public class BanLeTraiPhieuTwDAO extends AppDAO {

    private Connection conn = null;
    private String CLASS_NAME_DAO =
        "com.seatech.tp.banletraiphieutw.dao.BanLeTraiPhieuTwDAO";
    private String CLASS_NAME_VO =
        "com.seatech.tp.banletraiphieutw.vo.BanLeTraiPhieuTwVO";
    private static String STRING_EMPTY = "";

    public BanLeTraiPhieuTwDAO(Connection conn) {
        super();
        this.conn = conn;
    }

    public Long update(BanLeTraiPhieuTwVO vo) throws Exception {
        Vector v_param = new Vector();
        Vector v_param2 = null;
        //Lay ID tu seq
        Long lID = new Long("0");
        try {
            StringBuffer strSQL = new StringBuffer();
            String sql = "";
            if (vo.getGuid() != null && !vo.getGuid().equals("")) {
                sql = generateSQLUpdate(vo, v_param, "TP_KQPH_BAN_LE", conn);
                strSQL.append(sql);
                strSQL.append("WHERE GUID = ?");
                v_param.add(new Parameter(vo.getGuid(), true));
                lID = new Long(vo.getGuid());
            } else {
                lID = getSeqNextValue("TP_MA_BANLETW_SEQ", conn);
                vo.setGuid(String.valueOf(lID));
                sql = generateSQLInsert(vo, v_param, "TP_KQPH_BAN_LE", conn);
                strSQL.append(sql);
            }
            if (executeStatement(strSQL.toString(), v_param, conn) == 1) {
                //insert list ctiet
                if (vo.getLstKQBanLe_CTiet() != null &&
                    !vo.getLstKQBanLe_CTiet().isEmpty()) {
                    //delete first
                    executeStatement("DELETE TP_KQPH_BAN_LE_CTIET WHERE KQPH_BAN_LE_ID = '" +
                                     lID + "'", v_param2, conn);
                    BanLeTraiPhieuTwChiTietVO ctietVO = null;
                    Iterator ito = vo.getLstKQBanLe_CTiet().iterator();
                    while (ito.hasNext()) {
                        v_param2 = new Vector();
                        ctietVO = (BanLeTraiPhieuTwChiTietVO)ito.next();
                        ctietVO.setKqph_ban_le_id(String.valueOf(lID));
                        ctietVO.setGuid(String.valueOf(getSeqNextValue("TP_MA_BANLETWCTIET_SEQ",
                                                                       conn)));
                        sql =
                              generateSQLInsert(ctietVO, v_param2, "TP_KQPH_BAN_LE_CTIET", conn);
                        executeStatement(sql, v_param2, conn);
                    }
                }
                return lID;
            } else
                return new Long("0");
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
   
    public Collection getListBanLeTwPaging(BanLeTraiPhieuTwVO vo, Integer page,
                                           Integer count,
                                           Integer[] totalCount) throws Exception {
        Collection reval = null;
        String whereClause = "";
        Vector vParam = new Vector();
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append(" SELECT a.guid,a.dot_ph, a.MA_TPCP ,a.Khoi_luong, TO_CHAR(a.NGAY_PH, 'dd/MM/yyyy') NGAY_PH,(b.KY_HAN || ' ' || b.LOAI_KY_HAN) KY_HAN,a.TRANG_THAI,a.TRANG_THAI, c.NAME as TEN_TRANG_THAI ,LOAI_TIEN\n" +
                    " FROM TP_KQPH_BAN_LE a " +
                    " inner join TP_DM_KY_HAN b  \n" + " on a.ky_han = b.guid " +
                    " inner join TP_DM_TRANG_THAI c on a.TRANG_THAI = c.ID_STATUS ");
            if (vo.getDot_ph() != null && !"".equals(vo.getDot_ph())) {
                whereClause +=
                        " and  a.DOT_PH like '%" + vo.getDot_ph() + "%'";
            }
            if (vo.getTrang_thai() != null && !"".equals(vo.getTrang_thai())) {
                whereClause += " and  a.TRANG_THAI = ?";
                vParam.add(new Parameter(vo.getTrang_thai(), true));
            }
            if (vo.getMa_tpcp() != null && !"".equals(vo.getMa_tpcp())) {
                whereClause +=
                        " and  a.MA_TPCP like '%" + vo.getMa_tpcp().trim() + "%'";
            }
      
          if (vo.getNgay_phat_hanh_tu_ngay() != null && !"".equals(vo.getNgay_phat_hanh_tu_ngay())) {
              whereClause += " and trunc(a.NGAY_PH) >= ?";

              vParam.add(new DateParameter(StringUtil.StringToDate(vo.getNgay_phat_hanh_tu_ngay(),
                                                                   "dd/MM/yyyy"),
                                           true));
          }
          if (vo.getNgay_phat_hanh_den_ngay() != null && !"".equals(vo.getNgay_phat_hanh_den_ngay())) {
              whereClause += " and trunc(a.NGAY_PH) <= ?";

              vParam.add(new DateParameter(StringUtil.StringToDate(vo.getNgay_phat_hanh_den_ngay(),
                                                                   "dd/MM/yyyy"),
                                           true));
          }
            if (vo.getKy_han() != null && !"".equals(vo.getKy_han())) {
                whereClause += " and  a.KY_HAN  = ?";
                vParam.add(new Parameter(vo.getKy_han(), true));
            }
            if (whereClause != null && !whereClause.equalsIgnoreCase("")) {
                strSQL.append(" WHERE 1=1 " + whereClause);
            }
            strSQL.append(" ORDER BY a.NGAY_TAO DESC");
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

    public Collection getListBanLeTwPagingPD(BanLeTraiPhieuTwVO vo,
                                             Integer page, Integer count,
                                             Integer[] totalCount) throws Exception {
        Collection reval = null;
        String whereClause = ""; //"and a.TRANG_THAI != '00'";
        Vector vParam = new Vector();
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append("SELECT a.guid,a.dot_ph, a.MA_TPCP,a.Khoi_luong, TO_CHAR(a.NGAY_PH, 'dd/MM/yyyy') NGAY_PH,(b.KY_HAN || ' ' || b.LOAI_KY_HAN) KY_HAN,a.TRANG_THAI,a.TRANG_THAI, c.NAME as TEN_TRANG_THAI,LOAI_TIEN\n" +
                    "FROM TP_KQPH_BAN_LE a inner join TP_DM_KY_HAN b  \n" +
                    "on a.ky_han = b.guid inner join TP_DM_TRANG_THAI c on a.TRANG_THAI = c.ID_STATUS ");
            if (vo.getDot_ph() != null && !"".equals(vo.getDot_ph())) {
                whereClause += " and  a.DOT_PH  = ? ";
                vParam.add(new Parameter(vo.getDot_ph(), true));
            }
            if (vo.getTrang_thai() != null && !"".equals(vo.getTrang_thai())) {
                whereClause += " and  a.TRANG_THAI = ?";
                vParam.add(new Parameter(vo.getTrang_thai(), true));
            }else{
                  whereClause +=" and a.TRANG_THAI != '00'"; 
                }
            
            if (vo.getMa_tpcp() != null && !"".equals(vo.getMa_tpcp())) {
                whereClause +=
                        " and  a.MA_TPCP like '%" + vo.getMa_tpcp() + "%'";
            }
 
          if (vo.getNgay_phat_hanh_tu_ngay() != null && !"".equals(vo.getNgay_phat_hanh_tu_ngay())) {
              whereClause += " and trunc(a.NGAY_PH) >= ?";

            vParam.add(new DateParameter(StringUtil.StringToDate(vo.getNgay_phat_hanh_tu_ngay(),
                                                                 "dd/MM/yyyy"),
                                         true));
          }
            
          if (vo.getNgay_phat_hanh_den_ngay() != null && !"".equals(vo.getNgay_phat_hanh_den_ngay())) {
              whereClause += " and trunc(a.NGAY_PH) <= ?";

              vParam.add(new DateParameter(StringUtil.StringToDate(vo.getNgay_phat_hanh_den_ngay(),
                                                                   "dd/MM/yyyy"),
                                           true));
          }
            if (vo.getKy_han() != null && !"".equals(vo.getKy_han())) {
                whereClause += " and  a.KY_HAN  = ?";
                vParam.add(new Parameter(vo.getKy_han(), true));
            }
            if (whereClause != null && !whereClause.equals("")) {
                strSQL.append(" WHERE 1=1 " + whereClause);
            }
            strSQL.append(" ORDER BY a.NGAY_TAO DESC");
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

    public BanLeTraiPhieuTwVO getBanLeTwObject(Map<String, Object> map) throws Exception,
                                                                               TPCPException {
        StringBuffer strSQL = new StringBuffer();
        StringBuffer strSQL2 = new StringBuffer();
        Vector vParam = new Vector();
        try {
            strSQL.append("SELECT  GUID, DOT_PH,DOT_BO_SUNG, MA_TPCP,MENH_GIA,KY_HAN,to_char(NGAY_PH,'dd/MM/yyyy') NGAY_PH,to_char(NGAY_TT_TIEN_MUA,'dd/MM/yyyy') NGAY_TT_TIEN_MUA\n" +
                    ",to_char(NGAY_DAO_HAN,'dd/MM/yyyy') NGAY_DAO_HAN ,to_char(decode(LAI_SUAT,'0','',LAI_SUAT) , 'fm9999.00') LAI_SUAT ,KHOI_LUONG, TONG_SO_TT, KY_TT_LAI,to_char(NGAY_TT_LAI_1,'dd/MM/yyyy') NGAY_TT_LAI_1,SO_LUONG,to_char(NGAY_TT_LAI_2,'dd/MM/yyyy') NGAY_TT_LAI_2,TRANG_THAI, decode(TRANG_THAI, '00','Dự thảo','01','Chờ duyệt','02','Đã duyệt','03','Từ chối','04','Đã hủy','') as TEN_TRANG_THAI, LY_DO_TU_CHOI,LOAI_TIEN\n" +
                    "FROM TP_KQPH_BAN_LE");
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
            if (strSQL2.toString().length() > 0) {
                strSQL.append(" WHERE 1 = 1 " + strSQL2.toString());
            }
            return (BanLeTraiPhieuTwVO)findByPK(strSQL.toString(), vParam,
                                                CLASS_NAME_VO, conn);
        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO + ".getBanLeTwObject(): " +
                                   ex.getMessage(), ex);
        }
    }



    public Collection getAllBanLe(Map<String,Object>map)throws Exception{
      StringBuffer strSQL = new StringBuffer();
      StringBuffer strSQL2= new StringBuffer();
      Vector vParam = new Vector();
      Collection reval= null;
      try{
         strSQL.append("SELECT GUID, DOT_PH, MA_TPCP,MENH_GIA,KY_HAN,to_char(NGAY_PH,'dd/MM/yyyy') NGAY_PH,to_char(NGAY_TT_TIEN_MUA,'dd/MM/yyyy') NGAY_TT_TIEN_MUA \n" +
                      ",to_char(NGAY_DAO_HAN,'dd/MM/yyyy') NGAY_DAO_HAN ,to_char(decode(LAI_SUAT,'0','',LAI_SUAT) , 'fm9999.00') LAI_SUAT ,KHOI_LUONG, TONG_SO_TT, KY_TT_LAI,to_char(NGAY_TT_LAI_1,'dd/MM/yyyy') NGAY_TT_LAI_1,SO_LUONG,to_char(NGAY_TT_LAI_2,'dd/MM/yyyy') NGAY_TT_LAI_2,TRANG_THAI, " +
                      "decode(TRANG_THAI, '00','Dự thảo','01','Chờ duyệt','02','Đã duyệt','03','Từ chối','04','Đã hủy','') as TEN_TRANG_THAI, LY_DO_TU_CHOI,LOAI_TIEN\n" +
                     "FROM TP_KQPH_BAN_LE");
         Set<String> setkey = map.keySet();
         Iterator itr= setkey.iterator();
          while(itr.hasNext()){
            String key = itr.next().toString();
            String value= map.get(key).toString();
              if(value!=null && !STRING_EMPTY.equals(value)){
                strSQL2.append(" and "+key +" = ? ");
                vParam.add(new Parameter(value,true));
              }
          }
          if(strSQL2.toString().length()>0){
            strSQL.append(" Where 1=1 "+strSQL2.toString());
          }
        Integer tatol[]= new Integer[1];
        reval = executeSelectStatement(strSQL.toString(), vParam, CLASS_NAME_VO,  conn);
      }catch(Exception ex){
        throw ex;
        }
      return reval;
    }

    public BanLeTraiPhieuTwVO getBanLeTwObjectHienThi(Map<String, Object> map) throws Exception,
                                                                                      TPCPException {
        StringBuffer strSQL = new StringBuffer();
        StringBuffer strSQL2 = new StringBuffer();
        Vector vParam = new Vector();
        try {
            strSQL.append("SELECT  a.GUID, a.DOT_PH, a.MA_TPCP,a.MENH_GIA,(b.KY_HAN || ' ' || b.LOAI_KY_HAN) KY_HAN ,to_char(a.NGAY_PH,'dd/MM/yyyy') NGAY_PH,to_char(a.NGAY_TT_TIEN_MUA,'dd/MM/yyyy') NGAY_TT_TIEN_MUA\n" +
                    ",to_char(a.NGAY_DAO_HAN,'dd/MM/yyyy') NGAY_DAO_HAN ,to_char(decode(a.LAI_SUAT,'0','',a.LAI_SUAT) , 'fm9999.00') LAI_SUAT ,a.KHOI_LUONG, a.TONG_SO_TT, decode(a.KY_TT_LAI, 1,'0 lần',2,'1 lần',3,'2 lần','') as KY_TT_LAI, SO_LUONG,to_char(a.NGAY_TT_LAI_1,'dd/MM/yyyy') NGAY_TT_LAI_1,to_char(a.NGAY_TT_LAI_2,'dd/MM/yyyy') NGAY_TT_LAI_2,a.TRANG_THAI, decode(a.TRANG_THAI, '00','Mới lập','01','Chờ phê duyệt','02','Đã duyệt','03','Từ chối','04','Đã hủy','') as TEN_TRANG_THAI, LY_DO_TU_CHOI ,LOAI_TIEN\n" +
                    "FROM TP_KQPH_BAN_LE a inner join TP_DM_KY_HAN b  \n" +
                    "on a.ky_han = b.guid");
            Set<String> keySet = map.keySet();
            Iterator<String> keySetIterator = keySet.iterator();
            while (keySetIterator.hasNext()) {
                String key = keySetIterator.next();
                String value = map.get(key).toString();
                if (value != null && !STRING_EMPTY.equals(value)) {
                    strSQL2.append("and a." + key + " = ? ");
                    vParam.add(new Parameter(value, true));
                }
            }
            if (strSQL2.toString().length() > 0) {
                strSQL.append(" WHERE 1 = 1 " + strSQL2.toString());
            }
            return (BanLeTraiPhieuTwVO)findByPK(strSQL.toString(), vParam,
                                                CLASS_NAME_VO, conn);
        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO + ".getBanLeTwObject(): " +
                                   ex.getMessage(), ex);
        }
    }

    public Long delete(BanLeTraiPhieuTwVO vo) throws Exception {
        Vector v_param = new Vector();
        StringBuffer strSQL = new StringBuffer();
        strSQL.append(" DELETE TP_KQPH_BAN_LE WHERE GUID = ?");
        int nExc = 0;
        if (vo.getGuid() != null) {
            v_param.add(new Parameter(vo.getGuid(), true));
            nExc = executeStatement(strSQL.toString(), v_param, conn);
            if (nExc == 1)
                return Long.parseLong(vo.getGuid());
        }
        return new Long("0");
    }

    public Long deleteChiTiet(BanLeTraiPhieuTwVO vo) throws Exception {
        Vector v_param = new Vector();
        StringBuffer strSQLChiTiet = new StringBuffer();
        strSQLChiTiet.append("DELETE TP_KQPH_BAN_LE_CTIET WHERE KQPH_BAN_LE_ID = ?");
        int nExc = 0;
        if (vo.getGuid() != null) {
            v_param.add(new Parameter(vo.getGuid(), true));
            nExc = executeStatement(strSQLChiTiet.toString(), v_param, conn);
            if (nExc == 1)
                return Long.parseLong(vo.getGuid());
        }
        return new Long("0");
    }

}
