package com.seatech.tp.kqduthau.dao;

import com.seatech.framework.datamanager.AppDAO;
import com.seatech.framework.datamanager.DateParameter;
import com.seatech.framework.datamanager.Parameter;
import com.seatech.framework.exception.DAOException;
import com.seatech.framework.exception.TPCPException;
import com.seatech.framework.utils.StringUtil;
import com.seatech.tp.kqduthau.vo.QLyKQDuThauCTietVO;
import com.seatech.tp.kqduthau.vo.QLyKQDuThauVO;

import com.seatech.tp.kqphathanh.vo.QLyKQPhatHanhVO;
import com.seatech.tp.quanlykehoachquy.vo.KHPHChiTietVO;
import com.seatech.tp.quanlykehoachquy.vo.QuanLyKeHoachQuyVO;

import java.sql.Blob;
import java.sql.Connection;

import java.sql.PreparedStatement;

import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

public class QLyKQDuThauDAO extends AppDAO {
    private Connection conn = null;
    private static String STRING_EMPTY = "";
    private String CLASS_NAME_DAO = "com.seatech.tp.kqduthau.dao.QLyKQDuThauDAO";
    private String CLASS_NAME_VO = "com.seatech.tp.kqduthau.vo.QLyKQDuThauVO";
    private String CLASS_NAME_VO_CHI_TIET = "com.seatech.tp.kqduthau.vo.QLyKQDuThauCTietVO";

    public QLyKQDuThauDAO(Connection conn) {
        this.conn = conn;
    }

    public Long update(QLyKQDuThauVO vo) throws Exception {

        Vector v_param = new Vector();
        Vector v_param2 = null;
        Vector v_param3 = null;
        Long lID = new Long("0");
        try {
            StringBuffer strSQL = new StringBuffer();
            String sql = "";
            if (vo.getGuid() != null && !vo.getGuid().equals("")) {
                sql = generateSQLUpdate(vo, v_param, "TP_KQ_DU_THAU", conn);
                strSQL.append(sql);
                strSQL.append("WHERE GUID = ?");
                v_param.add(new Parameter(vo.getGuid(), true));
                lID = new Long(vo.getGuid());
            } else {
                lID = getSeqNextValue("TP_KQ_DU_THAU_SEQ", conn);
                vo.setGuid(String.valueOf(lID));
                sql = generateSQLInsert(vo, v_param, "TP_KQ_DU_THAU", conn);
                strSQL.append(sql);
            }
            if (executeStatement(strSQL.toString(), v_param, conn) == 1) {
                //insert list ctiet
                if (vo.getLstKQDT_CTiet() != null && !vo.getLstKQDT_CTiet().isEmpty()) {
                    //delete first
                    executeStatement("DELETE TP_KQ_DU_THAU_CTIET WHERE PHIEN_DT = 'S' AND TP_KQ_DU_THAU_ID = '" + lID + "'", v_param2, conn);
                    QLyKQDuThauCTietVO ctietVO = null;
                    Iterator ito = vo.getLstKQDT_CTiet().iterator();
                    while (ito.hasNext()) {
                        v_param2 = new Vector();
                        ctietVO = (QLyKQDuThauCTietVO)ito.next();
                        ctietVO.setTp_kq_du_thau_id(String.valueOf(lID));
                        ctietVO.setGuid(String.valueOf(getSeqNextValue("TP_KQ_DU_THAU_CTIET_SEQ", conn)));
                        sql = generateSQLInsert(ctietVO, v_param2, "TP_KQ_DU_THAU_CTIET", conn);
                        executeStatement(sql, v_param2, conn);
                    }
                }
                //insert list ctiet_them
                if (vo.getLstKQDT_CTiet_Them() != null && !vo.getLstKQDT_CTiet_Them().isEmpty()) {
                    //delete first
                    executeStatement("DELETE TP_KQ_DU_THAU_CTIET WHERE PHIEN_DT = 'T' AND TP_KQ_DU_THAU_ID = '" + lID + "'", v_param3, conn);
                    QLyKQDuThauCTietVO ctietVO = null;
                    Iterator ito = vo.getLstKQDT_CTiet_Them().iterator();
                    while (ito.hasNext()) {
                        v_param3 = new Vector();
                        ctietVO = (QLyKQDuThauCTietVO)ito.next();
                        ctietVO.setTp_kq_du_thau_id(String.valueOf(lID));
                        ctietVO.setGuid(String.valueOf(getSeqNextValue("TP_KQ_DU_THAU_CTIET_SEQ", conn)));
                        sql = generateSQLInsert(ctietVO, v_param3, "TP_KQ_DU_THAU_CTIET", conn);
                        executeStatement(sql, v_param3, conn);
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


    public Collection getListKQDT(QLyKQDuThauVO vo, Integer page, Integer count, Integer[] totalCount) throws Exception {
        Collection reval = null;
        String whereClause = "";
        Vector vParam = new Vector();
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append("SELECT a.GUID,a.DOT_DT,a.MA_TPCP,a.TEN_TPCP," + "to_char(a.NGAY_TO_CHUC_PH,'dd/MM/yyyy') NGAY_TO_CHUC_PH,to_char(a.NGAY_PH,'dd/MM/yyyy')NGAY_PH," +
                    "to_char(a.NGAY_TT_TIEN_MUA,'dd/MM/yyyy')NGAY_TT_TIEN_MUA,to_char(a.NGAY_DAO_HAN,'dd/MM/yyyy')NGAY_DAO_HAN, " +
                    "  a.KY_HAN, a.KL_GOI_THAU,a.KL_GOI_THAU_THEM,a.TONG_KLDK_KO_CTLS_BS,a.TONG_KLDK_CTLS_BS,a.TONG_KLDK_KO_CTLS_THEM,  " +
                    "  a.TONG_KLDK_CTLS_THEM,  a.TONG_KLDK_KO_CTLS,  a.TONG_KLDK_CTLS, a.TONG_KL_DU_THAU, to_char(a.LAI_DU_THAU_CAO_NHAT, 'fm9999.00')  LAI_DU_THAU_CAO_NHAT, to_char(a.LAI_DU_THAU_THAP_NHAT, 'fm9999.00') LAI_DU_THAU_THAP_NHAT, " +
                    "  a.LOAI_TIEN,a.TRANG_THAI,b.NAME as TEN_TRANG_THAI, a.NGUOI_TAO, to_char(NGAY_TAO,'dd/MM/yyyy') NGAY_TAO,a.NGUOI_SUA_CUOI,to_char(a.NGAY_SUA_CUOI,'dd/MM/yyyy') NGAY_SUA_CUOI,NGUOI_DUYET,to_char(a.NGAY_DUYET,'dd/MM/yyyy') NGAY_DUYET,  a.LY_DO_TU_CHOI" +
                    "  FROM TP_KQ_DU_THAU a inner join TP_DM_TRANG_THAI b on a.TRANG_THAI = b.ID_STATUS ");
            if (vo.getDot_dt() != null && !"".equals(vo.getDot_dt())) {
                whereClause += " and  a.DOT_DT like '%" + vo.getDot_dt() + "%'";
            }

            if (vo.getTrang_thai() != null && !"".equals(vo.getTrang_thai())) {
                whereClause += " and  a.TRANG_THAI = ?";
                vParam.add(new Parameter(vo.getTrang_thai(), true));
            }
            if (vo.getMa_tpcp() != null && !"".equals(vo.getMa_tpcp())) {
                whereClause += " and  upper(a.MA_TPCP) like '%" + vo.getMa_tpcp().toUpperCase() + "%'";
            }
            if (vo.getNgay_to_chuc_ph_tu_ngay() != null && !"".equals(vo.getNgay_to_chuc_ph_tu_ngay())) {
                whereClause += " and trunc(a.NGAY_TO_CHUC_PH) >= ? ";
                vParam.add(new DateParameter(StringUtil.StringToDate(vo.getNgay_to_chuc_ph_tu_ngay(), "dd/MM/yyyy"), true));
            }
            if (vo.getNgay_to_chuc_ph_den_ngay() != null && !"".equals(vo.getNgay_to_chuc_ph_den_ngay())) {
                whereClause += " and trunc(a.NGAY_TO_CHUC_PH) <= ?";
                vParam.add(new DateParameter(StringUtil.StringToDate(vo.getNgay_to_chuc_ph_den_ngay(), "dd/MM/yyyy"), true));
            }
            if (whereClause != null && !whereClause.equalsIgnoreCase("")) {
                strSQL.append(" WHERE 1=1 " + whereClause);
            }
            strSQL.append(" ORDER BY a.NGAY_TAO DESC");
            reval = executeSelectWithPaging(conn, strSQL.toString(), vParam, CLASS_NAME_VO, page, count, totalCount);
        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO + "getListKQDT(): " + ex.getMessage(), ex);
        }
        return reval;
    }

    public Collection getListKQDT_PD(QLyKQDuThauVO vo, Integer page, Integer count, Integer[] totalCount) throws Exception {
        Collection reval = null;
        String whereClause = " and  a.TRANG_THAI != '00'";
        Vector vParam = new Vector();
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append("SELECT a.GUID,a.DOT_DT,a.MA_TPCP,a.TEN_TPCP," + "to_char(a.NGAY_TO_CHUC_PH,'dd/MM/yyyy') NGAY_TO_CHUC_PH,to_char(a.NGAY_PH,'dd/MM/yyyy')NGAY_PH," +
                    "to_char(a.NGAY_TT_TIEN_MUA,'dd/MM/yyyy')NGAY_TT_TIEN_MUA,to_char(a.NGAY_DAO_HAN,'dd/MM/yyyy')NGAY_DAO_HAN, " +
                    "  a.KY_HAN,  a.KL_GOI_THAU,a.KL_GOI_THAU_THEM,a.TONG_KLDK_KO_CTLS_BS,a.TONG_KLDK_CTLS_BS,a.TONG_KLDK_KO_CTLS_THEM,  " +
                    "  a.TONG_KLDK_CTLS_THEM,  a.TONG_KLDK_KO_CTLS,  a.TONG_KLDK_CTLS, a.TONG_KL_DU_THAU,  to_char(a.LAI_DU_THAU_CAO_NHAT, 'fm9999.00')  LAI_DU_THAU_CAO_NHAT, to_char(a.LAI_DU_THAU_THAP_NHAT, 'fm9999.00') LAI_DU_THAU_THAP_NHAT, " +
                    "  a.LOAI_TIEN,a.TRANG_THAI,b.NAME as TEN_TRANG_THAI, a.NGUOI_TAO, to_char(NGAY_TAO,'dd/MM/yyyy') NGAY_TAO,a.NGUOI_SUA_CUOI,to_char(a.NGAY_SUA_CUOI,'dd/MM/yyyy') NGAY_SUA_CUOI,NGUOI_DUYET,to_char(a.NGAY_DUYET,'dd/MM/yyyy') NGAY_DUYET,  a.LY_DO_TU_CHOI" +
                    "  FROM TP_KQ_DU_THAU a inner join TP_DM_TRANG_THAI b on a.TRANG_THAI = b.ID_STATUS ");
            if (vo.getDot_dt() != null && !"".equals(vo.getDot_dt())) {
                whereClause += " and  a.DOT_DT  = ?";
                vParam.add(new Parameter(vo.getDot_dt(), true));
            }
            if (vo.getTrang_thai() != null && !"".equals(vo.getTrang_thai())) {
                whereClause += " and  a.TRANG_THAI = ?";
                vParam.add(new Parameter(vo.getTrang_thai(), true));
            }
            if (vo.getMa_tpcp() != null && !"".equals(vo.getMa_tpcp())) {
                whereClause += " and  a.MA_TPCP like '%" + vo.getMa_tpcp() + "%'";
            }
            if (vo.getNgay_to_chuc_ph_tu_ngay() != null && !"".equals(vo.getNgay_to_chuc_ph_tu_ngay())) {
                whereClause += " and trunc(a.NGAY_TO_CHUC_PH) >= ? ";

                vParam.add(new DateParameter(StringUtil.StringToDate(vo.getNgay_to_chuc_ph_tu_ngay(), "dd/MM/yyyy"), true));
            }
            if (vo.getNgay_to_chuc_ph_den_ngay() != null && !"".equals(vo.getNgay_to_chuc_ph_den_ngay())) {
                whereClause += " and trunc(a.NGAY_TO_CHUC_PH)<= ?";
                vParam.add(new DateParameter(StringUtil.StringToDate(vo.getNgay_to_chuc_ph_den_ngay(), "dd/MM/yyyy"), true));
            }
            if (whereClause != null && !whereClause.equalsIgnoreCase("")) {
                strSQL.append(" WHERE 1=1 " + whereClause);
            }
            strSQL.append(" ORDER BY a.NGAY_TAO DESC");
            reval = executeSelectWithPaging(conn, strSQL.toString(), vParam, CLASS_NAME_VO, page, count, totalCount);
        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO + "getListKQDT(): " + ex.getMessage(), ex);
        }
        return reval;
    }

    public Collection getListKQDTObject(QLyKQDuThauVO vo, String guid) throws Exception, TPCPException {
        Collection reval = null;
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append("SELECT GUID,DOT_DT,MA_TPCP,TEN_TPCP," + "to_char(NGAY_TO_CHUC_PH,'dd/MM/yyyy') NGAY_TO_CHUC_PH,to_char(NGAY_PH,'dd/MM/yyyy')NGAY_PH," +
                    "to_char(NGAY_TT_TIEN_MUA,'dd/MM/yyyy')NGAY_TT_TIEN_MUA,to_char(NGAY_DAO_HAN,'dd/MM/yyyy')NGAY_DAO_HAN, " +
                    "  KY_HAN,  KL_GOI_THAU,KL_GOI_THAU_THEM,TONG_KLDK_KO_CTLS_BS,TONG_KLDK_CTLS_BS,TONG_KLDK_KO_CTLS_THEM,  " +
                    "  TONG_KLDK_CTLS_THEM,  TONG_KLDK_KO_CTLS,  TONG_KLDK_CTLS, TONG_KL_DU_THAU,  to_char(LAI_DU_THAU_CAO_NHAT, 'fm9999.00')  LAI_DU_THAU_CAO_NHAT, to_char(LAI_DU_THAU_THAP_NHAT, 'fm9999.00') LAI_DU_THAU_THAP_NHAT, " +
                    "  LOAI_TIEN,TRANG_THAI,NGUOI_TAO, to_char(NGAY_TAO,'dd/MM/yyyy') NGAY_TAO,NGUOI_SUA_CUOI,to_char(NGAY_SUA_CUOI,'dd/MM/yyyy') NGAY_SUA_CUOI,NGUOI_DUYET,to_char(NGAY_DUYET,'dd/MM/yyyy') NGAY_DUYET,  LY_DO_TU_CHOI" +
                    "  FROM TP_KQ_DU_THAU WHERE GUID = " + guid + "");
            reval = executeSelectStatement(strSQL.toString(), null, CLASS_NAME_VO);
        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO + ".getListKQDTObject(): " + ex.getMessage(), ex);
        }
        return reval;
    }

    public Collection getListKQDTChiTiet_BuoiSang(String guid) throws Exception, TPCPException {
        Collection reval = null;
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append("SELECT GUID,TP_KQ_DU_THAU_ID,PHIEN_DT,STT," + "  TEN_NHA_DAU_TU,  MA_SO,KL_DK_KHONG_CTLS,KL_DK_CTLS,to_char(decode(LAI_SUAT,'0','',LAI_SUAT), 'fm9999.00') LAI_SUAT,KL_DTCT,  " +
                    "  KL_CONG_DON_CTLS,  KL_CONG_DON,  MA_NHA_DAU_TU " + "  FROM TP_KQ_DU_THAU_CTIET WHERE PHIEN_DT = 'S' AND TP_KQ_DU_THAU_ID = " + guid + "");
            reval = executeSelectStatement(strSQL.toString(), null, CLASS_NAME_VO_CHI_TIET);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new DAOException(CLASS_NAME_DAO + ".getListKQDTChiTiet(): " + ex.getMessage(), ex);
        }
        return reval;
    }

    public Collection getListKQDTChiTiet_Them(String guid) throws Exception, TPCPException {
        Collection reval = null;
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append("SELECT GUID,TP_KQ_DU_THAU_ID,PHIEN_DT,STT," + "  TEN_NHA_DAU_TU,  MA_SO,KL_DK_KHONG_CTLS,KL_DK_CTLS,to_char(decode(LAI_SUAT,'0','',LAI_SUAT) , 'fm9999.00') LAI_SUAT,KL_DTCT,  " +
                    "  KL_CONG_DON_CTLS,  KL_CONG_DON,  MA_NHA_DAU_TU " + "  FROM TP_KQ_DU_THAU_CTIET WHERE PHIEN_DT = 'T' AND TP_KQ_DU_THAU_ID = " + guid + "");
            reval = executeSelectStatement(strSQL.toString(), null, CLASS_NAME_VO_CHI_TIET);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new DAOException(CLASS_NAME_DAO + ".getListKQDTChiTiet(): " + ex.getMessage(), ex);
        }
        return reval;
    }

    public QLyKQDuThauVO getQLKQDuThauObject(String guid) throws Exception, TPCPException {
        StringBuffer strSQL = new StringBuffer();
        try {
            strSQL.append("SELECT GUID,DOT_DT,MA_TPCP,TEN_TPCP," + "to_char(NGAY_TO_CHUC_PH,'dd/MM/yyyy') NGAY_TO_CHUC_PH,to_char(NGAY_PH,'dd/MM/yyyy')NGAY_PH," +
                    "to_char(NGAY_TT_TIEN_MUA,'dd/MM/yyyy')NGAY_TT_TIEN_MUA,to_char(NGAY_DAO_HAN,'dd/MM/yyyy')NGAY_DAO_HAN, " +
                    "  KY_HAN,  KL_GOI_THAU,KL_GOI_THAU_THEM,TONG_KLDK_KO_CTLS_BS,TONG_KLDK_CTLS_BS,TONG_KLDK_KO_CTLS_THEM,  " +
                    "  TONG_KLDK_CTLS_THEM,  TONG_KLDK_KO_CTLS,  TONG_KLDK_CTLS, TONG_KL_DU_THAU, to_char(LAI_DU_THAU_CAO_NHAT, 'fm9999.00')  LAI_DU_THAU_CAO_NHAT, to_char(LAI_DU_THAU_THAP_NHAT, 'fm9999.00') LAI_DU_THAU_THAP_NHAT, " +
                    "  LOAI_TIEN,TRANG_THAI,NGUOI_TAO, to_char(NGAY_TAO,'dd/MM/yyyy') NGAY_TAO,NGUOI_SUA_CUOI,to_char(NGAY_SUA_CUOI,'dd/MM/yyyy') NGAY_SUA_CUOI,NGUOI_DUYET,to_char(NGAY_DUYET,'dd/MM/yyyy') NGAY_DUYET,  LY_DO_TU_CHOI" +
                    "  FROM TP_KQ_DU_THAU");
            if (guid != null && !STRING_EMPTY.equals(guid)) {
                strSQL.append(" WHERE GUID = '" + guid + "'");
            }
            return (QLyKQDuThauVO)findByPK(strSQL.toString(), null, CLASS_NAME_VO, conn);
        } catch (Exception ex) {
            throw new DAOException(CLASS_NAME_DAO + ".getKQDTObject(): " + ex.getMessage(), ex);
        }
    }

    public Long delete(QLyKQDuThauVO vo, String guid) throws Exception {
        Vector v_param = new Vector();
        Vector v_param2 = null;
        StringBuffer sql = new StringBuffer();
        StringBuffer strSQL = new StringBuffer();
        strSQL.append(" DELETE TP_KQ_DU_THAU WHERE GUID = ?");

        int nExc = 0;
        if (vo.getGuid() != null) {
            v_param.add(new Parameter(vo.getGuid(), true));
            nExc = executeStatement(strSQL.toString(), v_param, conn);
            if (nExc == 1) {
                sql.append(" DELETE TP_KQ_DU_THAU_CTIET WHERE TP_KQ_DU_THAU_ID = " + guid + "");
                executeStatement(sql.toString(), v_param2, conn);
                deleteTP_KQPH_FILE(vo);
                return Long.parseLong(vo.getGuid());
            }
        }
        return new Long("0");
    }

    //Bang TP_KQPH_FILE

    public void insertTP_KQDT_FILE(byte[] kqdtFile, long id_kq_dt, String ngay_imp, long nguoi_imp) throws Exception {

        PreparedStatement pstm = null;
        StringBuffer sql = new StringBuffer();
        Blob fileBLOB;
        sql.append("INSERT INTO TP_KQDT_FILE(KQDT_ID,NGAY_IMP,NGUOI_IMP,FILE_IMP) values(");
        sql.append(id_kq_dt);
        sql.append(", to_date('" + ngay_imp + "', 'DDMMRRRRHH24MISS')");
        sql.append(", ");
        sql.append(nguoi_imp);
        sql.append(",?)");
        pstm = conn.prepareStatement(sql.toString());
        fileBLOB = conn.createBlob();
        fileBLOB.setBytes(1, kqdtFile);
        pstm.setBlob(1, fileBLOB);
        pstm.execute();
        pstm.close();
        fileBLOB = null;
    }

    public Long deleteTP_KQPH_FILE(QLyKQDuThauVO vo) throws Exception {
        Vector v_param = new Vector();
        StringBuffer strSQLChiTiet = new StringBuffer();
        strSQLChiTiet.append("DELETE TP_KQDT_FILE WHERE KQDT_ID = ?");
        int nExc = 0;
        if (vo.getGuid() != null) {
            v_param.add(new Parameter(vo.getGuid(), true));
            nExc = executeStatement(strSQLChiTiet.toString(), v_param, conn);
            if (nExc == 1)
                return Long.parseLong(vo.getGuid());
        }
        return new Long("0");
    }
    //END TP_KQDT_FILE
}
