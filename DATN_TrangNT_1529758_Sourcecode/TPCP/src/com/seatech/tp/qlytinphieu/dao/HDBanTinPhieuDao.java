package com.seatech.tp.qlytinphieu.dao;

import com.seatech.framework.datamanager.AppDAO;
import com.seatech.framework.datamanager.DateParameter;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.exception.DAOException;
import com.seatech.framework.exception.TPCPException;
import com.seatech.framework.utils.StringUtil;
import com.seatech.tp.qlytinphieu.vo.HDBanTinPhieuVo;
import com.seatech.tp.ttindthau.dao.QLyTTinDauThauDAO;
import com.seatech.tp.ttindthau.vo.ThongTinDauThauVO;

import java.sql.Connection;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

public class HDBanTinPhieuDao extends AppDAO {
    private Connection conn = null;
    private String CLASS_NAME_DAO =
        "com.seatech.tp.qlytinphieu.dao.HDBanTinPhieuDao";
    private String CLASS_NAME_VO =
        "com.seatech.tp.qlytinphieu.vo.HDBanTinPhieuVo";
    private static String STRING_EMPTY = "";

    public HDBanTinPhieuDao(Connection conn) {
        super();
        this.conn = conn;
    }

    public Long insert(HDBanTinPhieuVo vo) throws Exception {
        Vector v_param = new Vector();
        //L?y ID t? seq
        Long lID = new Long("0");
        StringBuffer strSQL = new StringBuffer();
        String sql = "";
        if (vo.getGuid() != null && !vo.getGuid().equals("")) {
            sql = generateSQLUpdate(vo, v_param, "TP_HD_BAN_TIN_PHIEU", conn);
            strSQL.append(sql);
            strSQL.append("WHERE GUID = ?");
            v_param.add(new Parameter(vo.getGuid(), true));
            lID = new Long(vo.getGuid());
        } else {
            lID = getSeqNextValue("TP_HD_BAN_TIN_PHIEU_SEQ", conn);
            vo.setGuid(String.valueOf(lID));
            sql = generateSQLInsert(vo, v_param, "TP_HD_BAN_TIN_PHIEU", conn);
            strSQL.append(sql);
        }
        if (executeStatement(strSQL.toString(), v_param, conn) == 1)
            return lID;
        else
            return new Long("0");
    }

    public Collection getLisHDTin_PhieuPaging(HDBanTinPhieuVo vo, Integer page,
                                              Integer count,
                                              Integer[] totalCount) throws Exception {
        Collection reval = null;
        String whereClause = "";
        Vector vParam = new Vector();
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append("SELECT GUID,  SO_HD,  NGAY_HD,  MA_TP,  KL_TP, to_char(decode(LAI_SUAT,'0','',LAI_SUAT) , 'fm9999.00') LAI_SUAT ,(SELECT (KY_HAN || ' ' ||LOAI_KY_HAN) NAME_KY_HAN FROM TP_DM_KY_HAN where GUID=a.KY_HAN) AS  KY_HAN," +
                          "  GIA_BAN,  to_char(NGAY_PH,'dd/MM/yyyy') NGAY_PH,  " +
                          "  to_char(NGAY_TT_TIEN_MUA,'dd/MM/yyyy') NGAY_TT_TIEN_MUA,  " +
                          "  to_char(NGAY_DAO_HAN,'dd/MM/yyyy') NGAY_DAO_HAN, TT_DKY_LKY,  LY_DO_TU_CHOI, TRANG_THAI, NGUOI_TAO, " +
                          "   to_char(NGAY_TAO,'dd/MM/yyyy') NGAY_TAO,  NGUOI_SUA_CUOI,to_char(NGAY_SUA_CUOI,'dd/MM/yyyy')  NGAY_SUA_CUOI," +
                          " (select tt.name from TP_DM_TRANG_THAI tt where tt.ID_STATUS= a.TRANG_THAI) as TEN_TRANG_THAI " +
                          "  FROM TP_HD_BAN_TIN_PHIEU a ");

            if (vo.getMa_tp() != null && !"".equals(vo.getMa_tp())) {
                whereClause += " and  a.MA_TP  like '%"+vo.getMa_tp()+"%' ";
               
            }

            if (vo.getTrang_thai() != null && !"".equals(vo.getTrang_thai())) {
                whereClause += " and  a.TRANG_THAI  = ?";
                vParam.add(new Parameter(vo.getTrang_thai(), true));
            }

            if (vo.getTu_ngay() != null && !"".equals(vo.getTu_ngay())) {
                whereClause += " and trunc(a.NGAY_PH) >= ?";

                vParam.add(new DateParameter(StringUtil.StringToDate(vo.getTu_ngay(),
                                                                     "dd/MM/yyyy"),
                                             true));
            }
            if (vo.getDen_ngay() != null && !"".equals(vo.getDen_ngay())) {
                whereClause += " and trunc(a.NGAY_PH) <= ?";

                vParam.add(new DateParameter(StringUtil.StringToDate(vo.getDen_ngay(),
                                                                     "dd/MM/yyyy"),
                                             true));
            }

            //   manhvv
            if (vo.getSo_hd() != null && !"".equals(vo.getSo_hd())) {
                whereClause += " and a.SO_HD like '%" + vo.getSo_hd()+"%' ";
               
            }
            if (vo.getKl_tp() != null && !"".equals(vo.getKl_tp())) {
                whereClause += " and a.KL_TP = ?";
                vParam.add(new Parameter(vo.getKl_tp(), true));
            }
            if (vo.getLai_suat() != null && !"".equals(vo.getLai_suat())) {
                whereClause += " and a.LAI_SUAT = ?";
                vParam.add(new Parameter(vo.getLai_suat(), true));
            }

            if (vo.getKy_han() != null && !"".equals(vo.getKy_han())) {
                whereClause += " and a.KY_HAN = ? ";
                vParam.add(new Parameter(vo.getKy_han(), true));
            }


            if (whereClause != null && !whereClause.equalsIgnoreCase("")) {
                strSQL.append(" WHERE 1=1 " + whereClause);
            }
            strSQL.append(" ORDER BY a.NGAY_TAO DESC ");
            reval =
                    executeSelectWithPaging(conn, strSQL.toString(), vParam, CLASS_NAME_VO,
                                            page, count, totalCount);
        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO + ".getListTPCPPaging(): " +
                                   ex.getMessage(), ex);
        }
        return reval;
    }

    public Collection getLisHDTin_PhieuPDPaging(HDBanTinPhieuVo vo,
                                                Integer page, Integer count,
                                                Integer[] totalCount) throws Exception {
        Collection reval = null;
        String whereClause = "";
        Vector vParam = new Vector();
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append("SELECT GUID,  SO_HD,  NGAY_HD,  MA_TP,  KL_TP,  to_char(decode(LAI_SUAT,'0','',LAI_SUAT) , 'fm9999.00') LAI_SUAT ,(SELECT (KY_HAN || ' ' ||LOAI_KY_HAN) NAME_KY_HAN FROM TP_DM_KY_HAN where GUID=a.KY_HAN) AS  KY_HAN," +
                          "  GIA_BAN,  to_char(NGAY_PH,'dd/MM/yyyy') NGAY_PH,  " +
                          "  to_char(NGAY_TT_TIEN_MUA,'dd/MM/yyyy') NGAY_TT_TIEN_MUA,  " +
                          "  to_char(NGAY_DAO_HAN,'dd/MM/yyyy') NGAY_DAO_HAN, TT_DKY_LKY,  LY_DO_TU_CHOI, TRANG_THAI, NGUOI_TAO, " +
                          "   to_char(NGAY_TAO,'dd/MM/yyyy') NGAY_TAO,  NGUOI_SUA_CUOI,to_char(NGAY_SUA_CUOI,'dd/MM/yyyy')  NGAY_SUA_CUOI," +
                          " (select tt.name from TP_DM_TRANG_THAI tt where tt.ID_STATUS= a.TRANG_THAI) as TEN_TRANG_THAI " +
                          "  FROM TP_HD_BAN_TIN_PHIEU a ");

            if (vo.getTrang_thai() == null ||
                "".equals(vo.getTrang_thai().trim())) {
                whereClause += " and a.TRANG_THAI != '00' ";

            }
            if (vo.getMa_tp() != null && !"".equals(vo.getMa_tp())) {
              whereClause += " and  a.MA_TP  like '%"+vo.getMa_tp()+"%' ";
            }

            if (vo.getTrang_thai() != null && !"".equals(vo.getTrang_thai())) {
                whereClause += " and  a.TRANG_THAI  = ?";
                vParam.add(new Parameter(vo.getTrang_thai(), true));
            }

            if (vo.getTu_ngay() != null && !"".equals(vo.getTu_ngay())) {
                whereClause += " and trunc(a.NGAY_PH) >= ?";

                vParam.add(new DateParameter(StringUtil.StringToDate(vo.getTu_ngay(),
                                                                     "dd/MM/yyyy"),
                                             true));
            }
            if (vo.getDen_ngay() != null && !"".equals(vo.getDen_ngay())) {
                whereClause += " and trunc(a.NGAY_PH) <= ?";

                vParam.add(new DateParameter(StringUtil.StringToDate(vo.getDen_ngay(),
                                                                     "dd/MM/yyyy"),
                                             true));
            }

            //   manhvv
            if (vo.getSo_hd() != null && !"".equals(vo.getSo_hd())) {
                whereClause += " and a.SO_HD like '%" + vo.getSo_hd()+"%' ";
            }
            if (vo.getKl_tp() != null && !"".equals(vo.getKl_tp())) {
                whereClause += " and a.KL_TP = ?";
                vParam.add(new Parameter(vo.getKl_tp(), true));
            }
            if (vo.getLai_suat() != null && !"".equals(vo.getLai_suat())) {
                whereClause += " and a.LAI_SUAT = ?";
                vParam.add(new Parameter(vo.getLai_suat(), true));
            }

            if (vo.getKy_han() != null && !"".equals(vo.getKy_han())) {
                whereClause += " and a.KY_HAN = ? ";
                vParam.add(new Parameter(vo.getKy_han(), true));
            }


            if (whereClause != null && !whereClause.equalsIgnoreCase("")) {
                strSQL.append(" WHERE 1=1 " + whereClause);
            }
            strSQL.append(" ORDER BY a.SO_HD DESC");
            reval =
                    executeSelectWithPaging(conn, strSQL.toString(), vParam, CLASS_NAME_VO,
                                            page, count, totalCount);
        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO + ".getListTPCPPaging(): " +
                                   ex.getMessage(), ex);
        }
        return reval;
    }

    public HDBanTinPhieuVo getHDTinPhieuObject(Map<String, Object> map) throws Exception,
                                                                               TPCPException {
        StringBuffer strSQL = new StringBuffer();
        StringBuffer strSQL2 = new StringBuffer();
        Vector vParam = new Vector();
        int valuecheck = 1;
        try {
            // da sua .
            strSQL.append("SELECT GUID,  SO_HD,  to_char(NGAY_HD,'dd/MM/yyyy') NGAY_HD,  MA_TP,  KL_TP,  to_char(decode(LAI_SUAT,'0','',LAI_SUAT) , 'fm9999.00') LAI_SUAT, KY_HAN," +
                          "  GIA_BAN,  to_char(NGAY_PH,'dd/MM/yyyy') NGAY_PH,  " +
                          "  to_char(NGAY_TT_TIEN_MUA,'dd/MM/yyyy') NGAY_TT_TIEN_MUA,  " +
                          "  to_char(NGAY_DAO_HAN,'dd/MM/yyyy') NGAY_DAO_HAN, TT_DKY_LKY,  LY_DO_TU_CHOI, TRANG_THAI, NGUOI_TAO, " +
                          "  to_char(NGAY_TAO,'dd/MM/yyyy') NGAY_TAO,  NGUOI_SUA_CUOI, to_char(NGAY_SUA_CUOI,'dd/MM/yyyy') NGAY_SUA_CUOI " +
                          "  FROM TP_HD_BAN_TIN_PHIEU  ");
            Set<String> keySet = map.keySet();
            Iterator<String> keySetIterator = keySet.iterator();
            while (keySetIterator.hasNext()) {
                String key = keySetIterator.next();
                String value = map.get(key).toString();
                if (value != null && !STRING_EMPTY.equals(value)) {
                    strSQL2.append(" OR " + key + " = ? ");
                    vParam.add(new Parameter(value, true));
                }
            }
            if (strSQL2.toString().length() > 0) {
                strSQL.append(" WHERE 1 = 0 " + strSQL2.toString());
            }
            return (HDBanTinPhieuVo)findByPK(strSQL.toString(), vParam,
                                             CLASS_NAME_VO, conn);
        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO +
                                   ".getHDTinPhieuObject(): " +
                                   ex.getMessage(), ex);
        }
    }

    public HDBanTinPhieuVo getHDTinPhieuObject2(Map<String, Object> map) throws Exception,
                                                                                TPCPException {
        StringBuffer strSQL = new StringBuffer();
        StringBuffer strSQL2 = new StringBuffer();
        Vector vParam = new Vector();
        try {
            strSQL.append("SELECT GUID,  SO_HD,  to_char(NGAY_HD,'dd/MM/yyyy') NGAY_HD,  MA_TP,  KL_TP,  to_char(decode(LAI_SUAT,'0','',LAI_SUAT) , 'fm9999.00') LAI_SUAT, (SELECT (KY_HAN || ' ' ||LOAI_KY_HAN) NAME_KY_HAN FROM TP_DM_KY_HAN where GUID=a.KY_HAN) AS  KY_HAN," +
                          "  GIA_BAN,  to_char(NGAY_PH,'dd/MM/yyyy') NGAY_PH,  " +
                          "  to_char(NGAY_TT_TIEN_MUA,'dd/MM/yyyy') NGAY_TT_TIEN_MUA,  " +
                          "  to_char(NGAY_DAO_HAN,'dd/MM/yyyy') NGAY_DAO_HAN, TT_DKY_LKY,  LY_DO_TU_CHOI, TRANG_THAI, NGUOI_TAO, " +
                          "   to_char(NGAY_TAO,'dd/MM/yyyy') NGAY_TAO,  NGUOI_SUA_CUOI,to_char(NGAY_SUA_CUOI,'dd/MM/yyyy')  NGAY_SUA_CUOI " +
                          "  FROM TP_HD_BAN_TIN_PHIEU ");
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
            return (HDBanTinPhieuVo)findByPK(strSQL.toString(), vParam,
                                             CLASS_NAME_VO, conn);
        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO +
                                   ".getHDTinPhieuObject(): " +
                                   ex.getMessage(), ex);
        }
    }

    public Long delete(HDBanTinPhieuVo vo) throws Exception {
        Vector v_param = new Vector();
        StringBuffer strSQL = new StringBuffer();
        strSQL.append(" DELETE TP_HD_BAN_TIN_PHIEU WHERE GUID = ?");
        int nExc = 0;
        if (vo.getGuid() != null) {
            v_param.add(new Parameter(vo.getGuid(), true));
            nExc = executeStatement(strSQL.toString(), v_param, conn);
            if (nExc == 1)
                return Long.parseLong(vo.getGuid());
        }
        return new Long("0");
    }
}
