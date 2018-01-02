package com.seatech.tp.tttmua.dao;

import com.seatech.framework.datamanager.AppDAO;
import com.seatech.framework.datamanager.DateParameter;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.exception.DAOException;
import com.seatech.framework.exception.TPCPException;
import com.seatech.framework.utils.StringUtil;
import com.seatech.tp.tttmua.vo.QLyLapBangKeCTietVO;
import com.seatech.tp.tttmua.vo.QLyLapBangKeVO;

import java.sql.Connection;

import java.sql.ResultSet;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

public class QLyLapBangKeDAO extends AppDAO {
    private String CLASS_NAME_DAO = "com.seatech.tp.tttmua.action.dao.QLyLapBangKeDAO";
    private String CLASS_NAME_VO = "com.seatech.tp.tttmua.vo.QLyLapBangKeVO";
    private String CLASS_NAME_VO_CTIET = "com.seatech.tp.tttmua.vo.QLyLapBangKeCTietVO";
    private String CLASS_NAME_DVI_SO_HUU_VO = "com.seatech.tp.kqphathanh.vo.QLyKQPhatHanhCTiet_SoHuuVO";
    private static String STRING_EMPTY = "";

    public QLyLapBangKeDAO(Connection conn) {
        this.conn = conn;
    }

    public Collection getListBangKeXacNhan(QLyLapBangKeVO vo, Integer page, Integer count, Integer[] totalCount) throws Exception {
        Collection reval = null;
        Vector vParam = new Vector();
        StringBuffer strSQL = new StringBuffer();
        String whereClause = "";
        try {
            strSQL.append("SELECT a.GUID,  a.DOT_PH, a.PHUONG_THUC_PH, to_char(a.NGAY_DT,'dd/MM/yyyy') NGAY_DT,  to_char(a.NGAY_PH,'dd/MM/yyyy') NGAY_PH,  to_char(a.NGAY_TT,'dd/MM/yyyy') NGAY_TT,  a.MA_TPCP,  a.KY_HAN,  a.KL_TRUNG_THAU,to_char(decode(a.LS_TRUNG_THAU,'0','',a.LS_TRUNG_THAU) , 'fm9999.00') LS_TRUNG_THAU,to_char(decode(a.LS_DANH_NGHIA,'0','',a.LS_DANH_NGHIA) , 'fm9999.00') LS_DANH_NGHIA,  a.TONG_SO_TIEN_TT, \n" +
                    "a.LOAI_TIEN,  c.NAME TEN_TRANG_THAI, a.TRANG_THAI,  a.NGUOI_TAO,  a.NGAY_TAO,  a.NGUOI_SUA_CUOI,  a.NGAY_SUA_CUOI,  a.SO_BKE, (b.KY_HAN || ' ' || b.LOAI_KY_HAN) name_ky_han " +
                    "FROM TP_BKE_CHUYEN_TIEN a, TP_DM_KY_HAN b, TP_DM_TRANG_THAI c  WHERE a.KY_HAN = b.GUID and a.TRANG_THAI = c.ID_STATUS ");
            if (vo.getMa_tpcp() != null && !"".equals(vo.getMa_tpcp())) {
                whereClause += " and  a.MA_TPCP  = ?";
                vParam.add(new Parameter(vo.getMa_tpcp(), true));
            }
            if (vo.getDot_ph() != null && !"".equals(vo.getDot_ph())) {
                whereClause += " and  a.DOT_PH  = ?";
                vParam.add(new Parameter(vo.getDot_ph(), true));
            }
            if (vo.getKy_han() != null && !"".equals(vo.getKy_han())) {
                whereClause += " and  a.KY_HAN  = ?";
                vParam.add(new Parameter(vo.getKy_han(), true));
            }
            if (vo.getLs_danh_nghia() != null && !"".equals(vo.getLs_danh_nghia())) {
                whereClause += " and  a.LS_DANH_NGHIA  = ?";
                vParam.add(new Parameter(vo.getLs_danh_nghia().replaceAll("\\.", "").replaceAll(",", "."), true));
            }
            if (vo.getTrang_thai() != null && !"".equals(vo.getTrang_thai())) {
                whereClause += " and  a.TRANG_THAI  = ?";
                vParam.add(new Parameter(vo.getTrang_thai(), true));
            }

            if (vo.getTu_ngay_ph() != null && !"".equals(vo.getTu_ngay_ph())) {
                whereClause += " and trunc(a.NGAY_PH) >= ?";

                vParam.add(new DateParameter(StringUtil.StringToDate(vo.getTu_ngay_ph(), "dd/MM/yyyy"), true));
            }
            if (vo.getDen_ngay_ph() != null && !"".equals(vo.getDen_ngay_ph())) {
                whereClause += " and trunc(a.NGAY_PH) <= ?";

                vParam.add(new DateParameter(StringUtil.StringToDate(vo.getDen_ngay_ph(), "dd/MM/yyyy"), true));
            }
            if (whereClause != null && !whereClause.equalsIgnoreCase("")) {
                strSQL.append(whereClause);
            }
            strSQL.append(" ORDER BY a.NGAY_TAO DESC");

            reval = executeSelectWithPaging(strSQL.toString(), vParam, CLASS_NAME_VO, page, count, totalCount);
        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO + ".getListBangKeXacNhan(): " + ex.getMessage(), ex);
        }
        return reval;
    }

    public QLyLapBangKeVO getTTinDThauFromKQPH(Map<String, Object> map, String ptph_tp) throws Exception {
        StringBuffer strSQL = null;
        StringBuffer strSQL2 = new StringBuffer();
        Vector vParam = new Vector();
        try {
            if (ptph_tp != null && "TD".equals(ptph_tp)) {
                strSQL = new StringBuffer();
                strSQL.append(" select * from(SELECT   a.DOT_DT DOT_PH, a.MA_TPCP,(select NOI_CAP from TP_DM_TPCP t where t.MA_TP = A.MA_TPCP) NOI_CAP, a.KY_HAN, to_char(a.NGAY_DT,'dd/MM/yyyy') NGAY_DT, to_char(a.NGAY_PH,'dd/MM/yyyy') NGAY_PH, \n" +
                        " a.TONG_KL_TRUNG_THAU KL_TRUNG_THAU, to_char(decode(b.LS_TRUNG_THAU,'0','',b.LS_TRUNG_THAU) , 'fm9999.00') LS_TRUNG_THAU,to_char(decode(b.LS_DANH_NGHIA,'0','',b.LS_DANH_NGHIA) , 'fm9999.00') LS_DANH_NGHIA, to_char(c.NGAY_TT_TIEN_MUA,'dd/MM/yyyy') NGAY_TT, c.LOAI_TIEN, c.HINH_THUC_DT,c.MENH_GIA,c.KY_TRA_LAI, to_char(c.NGAY_TT_LAI_1,'dd/MM/yyyy') NGAY_TT_LAI_1," +
                        " to_char(c.NGAY_TT_LAI_2,'dd/MM/yyyy') NGAY_TT_LAI_2 , to_char(c.NGAY_TT_TIEN_MUA,'dd/MM/yyyy') NGAY_TT_TIEN_MUA, to_char(c.NGAY_DAO_HAN,'dd/MM/yyyy') NGAY_DAO_HAN \n" +
                        " FROM TP_KQPH a, TP_KQPH_CTIET_TPCP b, TP_THONG_TIN_DAU_THAU c WHERE a.MA_TPCP = b.MA_TPCP and trunc(a.ngay_ph) = trunc(b.ngay_ph) and a.MA_TPCP = c.MA_TPCP and a.DOT_DT = c.DOT_DAU_THAU " +
                        " AND a.TRANG_THAI = '02') where 1= 1 ");
            } else if (ptph_tp != null && "TL".equals(ptph_tp)) {
                strSQL = new StringBuffer();
                strSQL.append("SELECT a.DOT_PH,\n" +
                        "  a.MA_TPCP,  (SELECT NOI_CAP FROM TP_DM_TPCP t WHERE t.MA_TP = a.MA_TPCP\n" +
                        "  ) NOI_CAP, nvl(a.KHOI_LUONG,0) KL_TRUNG_THAU, a.KY_HAN,  TO_CHAR(a.NGAY_PH,'dd/MM/yyyy') NGAY_PH,to_char(decode(a.LAI_SUAT,'0','',a.LAI_SUAT) , 'fm9999.00')   LS_TRUNG_THAU,\n" +
                        "  to_char(decode(a.LAI_SUAT,'0','',a.LAI_SUAT) , 'fm9999.00') LS_DANH_NGHIA,  TO_CHAR(a.NGAY_TT_TIEN_MUA,'dd/MM/yyyy') NGAY_TT,  LOAI_TIEN,  '' HINH_THUC_DT,\n" +
                        "  a.MENH_GIA,  a.KY_TT_LAI KY_TRA_LAI,  TO_CHAR(a.NGAY_TT_LAI_1,'dd/MM/yyyy') NGAY_TT_LAI_1,  TO_CHAR(a.NGAY_TT_LAI_2,'dd/MM/yyyy') NGAY_TT_LAI_2 , TO_CHAR(a.NGAY_DAO_HAN,'dd/MM/yyyy')NGAY_DAO_HAN,\n" +
                        "  TO_CHAR(a.NGAY_TT_TIEN_MUA,'dd/MM/yyyy') NGAY_TT_TIEN_MUA FROM TP_KQPH_BAN_LE a where 1= 1 ");
            } else if (ptph_tp != null && "TPKB".equals(ptph_tp)) {
                strSQL = new StringBuffer();
                strSQL.append("select * from (SELECT a.DOT_PH,\n" +
                        "  a.MA_TPCP,  (SELECT NOI_CAP FROM TP_DM_TPCP t WHERE t.MA_TP = a.MA_TPCP\n" +
                        "  ) NOI_CAP,  a.KY_HAN, TO_CHAR(a.NGAY_TO_CHUC_PH,'dd/MM/yyyy') NGAY_DT, TO_CHAR(b.NGAY_PH,'dd/MM/yyyy') NGAY_PH,  nvl(a.TONG_KL_TRUNG_THAU,0) KL_TRUNG_THAU,to_char(decode(c.LS_TRUNG_THAU,'0','',c.LS_TRUNG_THAU) , 'fm9999.00') LS_TRUNG_THAU,\n" +
                        "  to_char(decode(c.LS_TRUNG_THAU,'0','',c.LS_TRUNG_THAU) , 'fm9999.00') LS_DANH_NGHIA,  '' NGAY_TT,  LOAI_TIEN,  '' HINH_THUC_DT,\n" +
                        "  0 MENH_GIA,  '' KY_TRA_LAI,  '' NGAY_TT_LAI_1,  '' NGAY_TT_LAI_2 ,TO_CHAR(a.NGAY_DAO_HAN,'dd/MM/yyyy')NGAY_DAO_HAN,\n" +
                        "  '' NGAY_TT_TIEN_MUA FROM TP_KQPH_TIN_PHIEU a ,\n" +
                        "  TP_KQPH_TIN_PHIEU_CTIET_TPCP b, TP_KQPH_TIN_PHIEU_CTIET_SO_HUU c where a.GUID = b.TP_KQPH_TIN_PHIEU_ID and a.GUID = c.TP_KQPH_TIN_PHIEU_ID) where 1= 1 ");
            } else if (ptph_tp != null && "PHTT".equals(ptph_tp)) {
                strSQL = new StringBuffer();
                strSQL.append("select * from (SELECT a.SO_HD DOT_PH,\n" +
                        "  a.MA_TP MA_TPCP,  (SELECT NOI_CAP FROM TP_DM_TPCP t WHERE t.MA_TP = a.MA_TP\n" +
                        "  ) NOI_CAP,  a.KY_HAN, TO_CHAR(a.NGAY_HD,'dd/MM/yyyy') NGAY_DT, TO_CHAR(a.NGAY_PH,'dd/MM/yyyy') NGAY_PH,  nvl(a.KL_TP,0) KL_TRUNG_THAU, \n" +
                        "  to_char(decode(a.LAI_SUAT,'0','',a.LAI_SUAT) , 'fm9999.00') LS_TRUNG_THAU,\n" +
                        "  to_char(decode(a.LAI_SUAT,'0','',a.LAI_SUAT) , 'fm9999.00') LS_DANH_NGHIA,  '' NGAY_TT,  'VND' LOAI_TIEN,  '' HINH_THUC_DT,\n" +
                        "  a.GIA_BAN MENH_GIA,  '' KY_TRA_LAI,  '' NGAY_TT_LAI_1,  '' NGAY_TT_LAI_2 ,TO_CHAR(a.NGAY_DAO_HAN,'dd/MM/yyyy')NGAY_DAO_HAN,\n" +
                        "  '' NGAY_TT_TIEN_MUA FROM TP_HD_BAN_TIN_PHIEU a where a.TRANG_THAI = '02') where 1= 1 ");
            } else
                return null;

            Set<String> keySet = null;
            Iterator<String> keySetIterator = null;
            if (map != null) {
                keySet = map.keySet();
                keySetIterator = keySet.iterator();
                while (keySetIterator.hasNext()) {
                    String key = keySetIterator.next();
                    String value = map.get(key).toString();
                    if (value != null && !STRING_EMPTY.equals(value)) {
                        strSQL2.append(" and " + key + " = ? ");
                        vParam.add(new Parameter(value, true));
                    }
                }
            }
            if (strSQL2.toString().length() > 0) {
                strSQL.append(strSQL2.toString());
            }
            return (QLyLapBangKeVO)findByPK(strSQL.toString(), vParam, CLASS_NAME_VO, conn);
        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO + ".getTTinDThauFromKQPH(): " + ex.getMessage(), ex);
        }
    }

    public QLyLapBangKeVO getTTinBangKeObject(String guid) throws Exception {
        StringBuffer strSQL = new StringBuffer();
        StringBuffer strSQL2 = new StringBuffer();
        QLyLapBangKeVO vo = null;
        Collection reval = null;
        Vector vParam = new Vector();
        try {
            strSQL.append("SELECT a.GUID, a.SO_BKE, a.DOT_PH,  to_char(a.NGAY_DT,'dd/MM/yyyy') NGAY_DT, to_char(a.NGAY_PH,'dd/MM/yyyy') NGAY_PH,  to_char(a.NGAY_TT,'dd/MM/yyyy') NGAY_TT,  \n" +
                    "  a.MA_TPCP,  a.KY_HAN,  a.KL_TRUNG_THAU,  to_char(decode(a.LS_TRUNG_THAU,'0','',a.LS_TRUNG_THAU) , 'fm9999.00') LS_TRUNG_THAU,to_char(decode(a.LS_DANH_NGHIA,'0','',a.LS_DANH_NGHIA) , 'fm9999.00') LS_DANH_NGHIA,  a.TONG_SO_TIEN_TT,\n" +
                    "  a.LOAI_TIEN,  a.TRANG_THAI,  a.NGUOI_TAO,  a.NGAY_TAO,  a.NGUOI_SUA_CUOI,  a.NGAY_SUA_CUOI, a.LY_DO_TU_CHOI, b.NOI_CAP,b.PHUONG_THUC_PH , a.LIST_ID_SO_HUU \n" +
                    "FROM TP_BKE_CHUYEN_TIEN a , TP_DM_TPCP b WHERE a.MA_TPCP = b.MA_TP AND a.GUID = '" + guid + "' ");
            vo = (QLyLapBangKeVO)findByPK(strSQL.toString(), vParam, CLASS_NAME_VO, conn);
            if (vo != null) {
                strSQL2.append("SELECT  MA_TRAI_CHU MA_NGUOI_SO_HUU,  TEN_TRAI_CHU TEN_NGUOI_SO_HUU,\n" +
                        "  KL_TRUNG_THAU ,  SO_TIEN_PHAI_TT TIEN_TT_MUA, nvl(SO_TIEN_DA_TT,0) SO_TIEN_DA_TT,  decode(to_char(NGAY_CHUYEN_TIEN,'HH24:MI'),'00:00',to_char(NGAY_CHUYEN_TIEN,'DD/MM/YYYY'),to_char(NGAY_CHUYEN_TIEN,'DD/MM/YYYY HH24:MI')) NGAY_CHUYEN_TIEN,  GHI_CHU\n" +
                        "FROM TP_BKE_CHUYEN_TIEN_CTIET ");
                strSQL2.append(" WHERE  BKE_CHUYEN_TIEN_ID = '" + vo.getGuid() + "'");
                reval = executeSelectStatement(strSQL2.toString(), null, CLASS_NAME_DVI_SO_HUU_VO, conn);
                vo.setLstCTietBKeCTien(reval);
            }
        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO + ".getTTinBangKeObject(): " + ex.getMessage(), ex);
        }
        return vo;
    }

    public QLyLapBangKeVO getTTinBangKeObject(String ma_tpcp, String dot_ph, String ngay_ph) throws Exception {
        StringBuffer strSQL = new StringBuffer();
        QLyLapBangKeVO vo = null;
        Vector vParam = new Vector();
        try {
            strSQL.append("select nvl(max(LAN_TRA_LAI),0) LAN_TRA_LAI, to_char(max(NGAY_TT_LAI_1),'DD/MM/YYYY') NGAY_TT_LAI_1,to_char(max(NGAY_TT_LAI_2),'DD/MM/YYYY') NGAY_TT_LAI_2 from TP_BKE_CHUYEN_TIEN " +
                    " where DOT_PH = ? and MA_TPCP = ? and trunc(NGAY_PH) = ? ");
            vParam.add(new Parameter(ma_tpcp, true));
            vParam.add(new Parameter(dot_ph, true));
            vParam.add(new DateParameter(StringUtil.StringToDate(ngay_ph, "dd/MM/yyyy"), true));
            vo = (QLyLapBangKeVO)findByPK(strSQL.toString(), vParam, CLASS_NAME_VO, conn);
        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO + ".getTTinBangKeObject(): " + ex.getMessage(), ex);
        }
        return vo;
    }

    public ResultSet getDVSoHuuObject(String guid) throws Exception {
        StringBuffer strSQL = new StringBuffer();
        StringBuffer strSQL2 = new StringBuffer();
        ResultSet reval = null;
        Vector vParam = new Vector();
        try {
            strSQL2.append("SELECT  MA_TRAI_CHU as MA_NGUOI_SO_HUU,  TEN_TRAI_CHU as TEN_NGUOI_SO_HUU,\n" +
                    "  KL_TRUNG_THAU ,  SO_TIEN_PHAI_TT TIEN_TT_MUA, nvl(SO_TIEN_DA_TT,0) SO_TIEN_DA_TT,  decode(to_char(NGAY_CHUYEN_TIEN,'HH24:MI'),'00:00',to_char(NGAY_CHUYEN_TIEN,'DD/MM/YYYY'),to_char(NGAY_CHUYEN_TIEN,'DD/MM/YYYY HH24:MI')) NGAY_CHUYEN_TIEN,  GHI_CHU\n" +
                    "FROM TP_BKE_CHUYEN_TIEN_CTIET ");
            strSQL2.append(" WHERE  BKE_CHUYEN_TIEN_ID = '" + guid + "'");
            reval = executeSelectStatement(strSQL2.toString(), vParam, conn);
        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO + ".getDVSoHuuObject(): " + ex.getMessage(), ex);
        }
        return reval;
    }

    public long delete(QLyLapBangKeVO vo) throws Exception, TPCPException {
        Vector v_param = new Vector();
        StringBuffer strSQL = new StringBuffer();
        strSQL.append(" DELETE TP_BKE_CHUYEN_TIEN WHERE GUID = ?");
        int nExc = 0;
        if (vo.getGuid() != null) {
            v_param.add(new Parameter(vo.getGuid(), true));
            nExc = executeStatement(strSQL.toString(), v_param, conn);
            if (nExc == 1) {
                strSQL = new StringBuffer();
                v_param = new Vector();
                strSQL.append(" DELETE TP_BKE_CHUYEN_TIEN_CTIET WHERE BKE_CHUYEN_TIEN_ID = ?");
                v_param.add(new Parameter(vo.getGuid(), true));
                executeStatement(strSQL.toString(), v_param, conn);
            }
            return Long.parseLong(vo.getGuid());
        }
        return new Long("0");
    }

    public void updateTrangThaiLapBangKeDviSoHuu(String lstGUID, String ptph_tpcp, String trang_thai) throws Exception {
        Vector v_param = new Vector();
        StringBuffer strSQL = new StringBuffer();
        String sql = "";
        try {
            if (lstGUID != null && !lstGUID.equals("")) {
                if (ptph_tpcp != null && "TD".equals(ptph_tpcp)) {
                    sql = "UPDATE TP_KQPH_CTIET_SO_HUU SET TRANG_THAI_TT = '" + trang_thai + "' WHERE GUID IN (" + lstGUID + ") ";
                } else if (ptph_tpcp != null && "TL".equals(ptph_tpcp)) {
                    sql = "UPDATE TP_KQPH_BAN_LE_CTIET SET TRANG_THAI_TT = '" + trang_thai + "' WHERE GUID IN (" + lstGUID + ") ";
                } else if (ptph_tpcp != null && "TPKB".equals(ptph_tpcp)) {
                    sql = "UPDATE TP_KQPH_TIN_PHIEU_CTIET_SO_HUU SET TRANG_THAI_TT = '" + trang_thai + "' WHERE GUID IN (" + lstGUID + ") ";
                } else if (ptph_tpcp != null && "PHTT".equals(ptph_tpcp)) {
                    sql = "UPDATE TP_HD_BAN_TIN_PHIEU SET TRANG_THAI_TT = '" + trang_thai + "' WHERE GUID IN (" + lstGUID + ") ";
                }
                strSQL.append(sql);
                executeStatement(strSQL.toString(), v_param, conn);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void updateTrangThaiThanhToanKeDviSoHuu(String so_bke, String dot_ph, String ma_tpcp, String ngay_ph, String lstMaDvi) throws Exception {
        Vector v_param = new Vector();
        StringBuffer strSQL = new StringBuffer();
        String sql = "";
        try {
            if (lstMaDvi != null && !lstMaDvi.equals("")) {
                sql =
"UPDATE TP_TT_TIEN_MUA SET SO_BKE = '" + so_bke + "', TRANG_THAI_TT = '01' WHERE MA_NGUOI_SO_HUU IN (" + lstMaDvi + ") and DOT_PH = ? and MA_TPCP = ? and trunc(NGAY_PH) = ? ";
                strSQL.append(sql);
                v_param.add(new Parameter(dot_ph, true));
                v_param.add(new Parameter(ma_tpcp, true));
                v_param.add(new DateParameter(StringUtil.StringToDate(ngay_ph, "dd/MM/yyyy"), true));
                executeStatement(strSQL.toString(), v_param, conn);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void updateTrangThaiThanhToanXoaBKeDviSoHuu(String so_bke, String dot_ph, String ma_tpcp, String ngay_ph) throws Exception {
        Vector v_param = new Vector();
        StringBuffer strSQL = new StringBuffer();
        String sql = "";
        try {
            sql = "UPDATE TP_TT_TIEN_MUA SET TRANG_THAI_TT = '00' WHERE SO_BKE  = ? and DOT_PH = ? and MA_TPCP = ? and trunc(NGAY_PH) = ? ";
            strSQL.append(sql);
            v_param.add(new Parameter(so_bke, true));
            v_param.add(new Parameter(dot_ph, true));
            v_param.add(new Parameter(ma_tpcp, true));
            v_param.add(new DateParameter(StringUtil.StringToDate(ngay_ph, "dd/MM/yyyy"), true));
            executeStatement(strSQL.toString(), v_param, conn);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public long update(QLyLapBangKeVO vo) throws Exception, TPCPException {
        Vector v_param = new Vector();
        Vector v_param2 = null;
        Long lID = new Long("0");
        Long sobke = new Long("0");
        try {
            StringBuffer strSQL = new StringBuffer();
            String sql = "";
            if (vo.getGuid() != null && !vo.getGuid().equals("")) {
                sql = generateSQLUpdate2(vo, v_param, "TP_BKE_CHUYEN_TIEN", conn);
                strSQL.append(sql);
                strSQL.append("WHERE GUID = ?");
                v_param.add(new Parameter(vo.getGuid(), true));
                lID = new Long(vo.getGuid());
            } else {
                lID = getSeqNextValue("TP_BKE_CHUYEN_TIEN_SEQ", conn);
                sobke = getSeqNextValue("so_bke_seq", conn);
                vo.setSo_bke("BKE" + sobke);
                vo.setGuid(String.valueOf(lID));
                sql = generateSQLInsert(vo, v_param, "TP_BKE_CHUYEN_TIEN", conn);
                strSQL.append(sql);
            }
            if (executeStatement(strSQL.toString(), v_param, conn) == 1) {
                //insert list ctiet
                if (vo.getLstCTietBKeCTien() != null && !vo.getLstCTietBKeCTien().isEmpty()) {
                    //delete first
                    executeStatement("DELETE TP_BKE_CHUYEN_TIEN_CTIET WHERE BKE_CHUYEN_TIEN_ID = '" + lID + "'", v_param2, conn);
                    QLyLapBangKeCTietVO ctietVO = null;
                    Iterator ito = vo.getLstCTietBKeCTien().iterator();
                    while (ito.hasNext()) {
                        v_param2 = new Vector();
                        ctietVO = (QLyLapBangKeCTietVO)ito.next();
                        ctietVO.setBke_chuyen_tien_id(String.valueOf(lID));
                        ctietVO.setGuid(String.valueOf(getSeqNextValue("TP_BKE_CHUYEN_TIEN_SEQ", conn)));
                        sql = generateSQLInsert(ctietVO, v_param2, "TP_BKE_CHUYEN_TIEN_CTIET", conn);
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
}
